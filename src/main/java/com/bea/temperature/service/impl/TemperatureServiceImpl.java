package com.bea.temperature.service.impl;

import com.alibaba.fastjson.JSON;
import com.bea.temperature.domain.WeatherInfoDTO;
import com.bea.temperature.domain.WeatherInfoEntity;
import com.bea.temperature.service.ITemperatureService;
import com.bea.temperature.utils.HttpRequestUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zsj on 2021/7/8.
 */

@Component
public class TemperatureServiceImpl implements ITemperatureService {

    @Override
    public WeatherInfoEntity genWeatherInfoEntity(String destinationCode) {
        if(!isNumeric(destinationCode)){
            throw new IllegalArgumentException("illegal args !!!");
        }
        String weatherInfo_url = "http://www.weather.com.cn/data/sk/{destinationCode}.html";
        try {
            String s = HttpRequestUtils.httpGet(weatherInfo_url.replace("{destinationCode}", destinationCode), 500);
            WeatherInfoDTO weatherInfoDTO = JSON.parseObject(s, WeatherInfoDTO.class);
            if (null != weatherInfoDTO) {
                return weatherInfoDTO.weatherinfo;
            }
        } catch (Exception e) {
            System.err.println("gen WeatherInfoEntity error !! info: " + e);
        }

        return WeatherInfoEntity.EMPTY;
    }

    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");//这个也行
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
