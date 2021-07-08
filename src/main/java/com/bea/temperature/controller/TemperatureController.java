package com.bea.temperature.controller;

import com.bea.temperature.cache.DestinationCache;
import com.bea.temperature.domain.Optional;
import com.bea.temperature.domain.WeatherInfoEntity;
import com.bea.temperature.exception.Asserts;
import com.bea.temperature.service.ITemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsj on 2021/7/8.
 */

@RestController
public class TemperatureController {

    @Autowired
    private ITemperatureService temperatureService;

    @RequestMapping("/temperature/get/{province}/{city}/{county}")
    public Optional<Integer> getTemperature(@PathVariable String province, @PathVariable String city,
                                            @PathVariable String county) {
        StringBuilder sb = new StringBuilder(province);
        sb.append(city).append(county);

        if (!DestinationCache.getInstance().containsDestination(sb.toString())) {
            String msg = "this is no data: " + sb.toString();
            Asserts.fail(msg);
            return Optional.failed(msg, -200);
        } else {
            WeatherInfoEntity weatherInfoEntity = temperatureService.genWeatherInfoEntity(sb.toString());
            if (weatherInfoEntity == WeatherInfoEntity.EMPTY) {
                Asserts.fail("gen weather info error!");
            }
            String temp = weatherInfoEntity.temp;
            return Optional.success(Double.valueOf(temp).intValue());
        }
    }
}
