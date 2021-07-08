package com.bea.temperature.service.impl;

import com.bea.temperature.controller.TemperatureController;
import com.bea.temperature.domain.WeatherInfoEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by zsj00877 on 2021/7/8.
 */
class TemperatureServiceImplTest {

    @Test
    void genWeatherInfoEntity() {
        WeatherInfoEntity weatherInfoEntity = new WeatherInfoEntity();
        weatherInfoEntity.city="苏州";
        weatherInfoEntity.cityId="101190401";
        weatherInfoEntity.temp = "23.9";
        weatherInfoEntity.WD="东北风";
        weatherInfoEntity.WS="小于3级";
        weatherInfoEntity.SD="79%";
        weatherInfoEntity.AP="1004.9hPa";
        weatherInfoEntity.njd="暂无实况";
        weatherInfoEntity.WSE="<3";
        weatherInfoEntity.time="18:00";
        weatherInfoEntity.sm="1.5";
        weatherInfoEntity.isRadar="0";
        weatherInfoEntity.Radar="";

        assertEquals(weatherInfoEntity,new TemperatureServiceImpl().genWeatherInfoEntity("101190401"));

        assertEquals(WeatherInfoEntity.EMPTY,new TemperatureServiceImpl().genWeatherInfoEntity("0"));
        assertEquals(WeatherInfoEntity.EMPTY,new TemperatureServiceImpl().genWeatherInfoEntity(String.valueOf(Integer.MAX_VALUE)));
        assertEquals(WeatherInfoEntity.EMPTY,
                new TemperatureServiceImpl().genWeatherInfoEntity(String.valueOf(Integer.MIN_VALUE)));


        // Exception Test
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new TemperatureServiceImpl().genWeatherInfoEntity("AAAAAA");
        });
        assertEquals("illegal args !!!", exception.getMessage());
    }
}