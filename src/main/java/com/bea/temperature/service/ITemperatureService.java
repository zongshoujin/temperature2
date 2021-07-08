package com.bea.temperature.service;

import com.bea.temperature.domain.WeatherInfoEntity;

/**
 * Created by zsj on 2021/7/8.
 */
public interface ITemperatureService {

    WeatherInfoEntity genWeatherInfoEntity(String destinationCode);
}
