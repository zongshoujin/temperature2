package com.bea.temperature.cache;

import com.alibaba.fastjson.JSONObject;
import com.bea.temperature.utils.HttpRequestUtils;

import java.util.*;

/**
 * Created by zsj on 2021/7/8.
 */
public class DestinationCache {

    private Set<String> exits_destination = new HashSet<>();

    private DestinationCache() {
        init();
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                init();
            }
        }, 1000 * 60 * 60 * 12, 1000 * 60 * 60 * 12);
    }

    private synchronized void init() {
        System.out.println("start load destination cache data ......");
        long startTime = System.currentTimeMillis();
        try {
            Map<String, Object> provinceMap = genAllProvince();
            Set<String> allProvinceIds = provinceMap.keySet();
            for (String provinceId : allProvinceIds) {
                exits_destination.addAll(genAllCityByProvinceId(provinceId));
            }
            System.out.println("end load destination cache data size: " + exits_destination.size()+", total time " +
                    "cost: " + (System.currentTimeMillis()-startTime)/1000+" s.");
        } catch (Exception e) {
            System.err.println("load destination cache data error ! occur exception: " + e);
        }
    }

    public boolean containsDestination(String destinationStr) {
        if (null == destinationStr || "".equals(destinationStr)) return false;
        return exits_destination.contains(destinationStr);
    }

    private Map<String, Object> genAllProvince() {
        final String province_url = "http://www.weather.com.cn/data/city3jdata/china.html";
        String s = HttpRequestUtils.httpGet(province_url, 500);
        return JSONObject.parseObject(s);
    }

    private Set<String> genAllCityByProvinceId(final String provinceId) {
        String city_url = "http://www.weather.com.cn/data/city3jdata/provshi/{provinceId}.html";
        String county_url = "http://www.weather.com.cn/data/city3jdata/station/{pro_city_id}.html";
        String s = HttpRequestUtils.httpGet(city_url.replace("{provinceId}", provinceId), 500);
        Map<String, Object> tmp = JSONObject.parseObject(s);
        Set<String> allCityIds = tmp.keySet();
        Set<String> allDestination = new HashSet<>();
        for (String cityId : allCityIds) {
            if (null == cityId || "".equals(cityId)) continue;
            String tmp_url = county_url.replace("{pro_city_id}", provinceId + cityId);
            String county_result = HttpRequestUtils.httpGet(tmp_url, 500);
            Map<String, Object> county_tmp = JSONObject.parseObject(county_result);
            Set<String> allCountyIds = county_tmp.keySet();
            for (String countyId : allCountyIds) {
                allDestination.add(provinceId + cityId + countyId);
            }
        }

        return allDestination;
    }

    public static DestinationCache getInstance() {
        return DestinationCacheHolder.instance;
    }

    private static class DestinationCacheHolder {
        private static DestinationCache instance = new DestinationCache();
    }
}
