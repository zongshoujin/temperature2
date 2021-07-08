package com.bea.temperature.domain;

import java.util.Objects;

/**
 * Created by zsj on 2021/7/8.
 */
public class WeatherInfoEntity {

    public static WeatherInfoEntity EMPTY = new WeatherInfoEntity();

    public String city;

    public String cityId;

    public String temp;

    public String WD;

    public String WS;

    public String SD;

    public String AP;

    public String njd;

    public String WSE;

    public String time;

    public String sm;

    public String isRadar;

    public String Radar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfoEntity that = (WeatherInfoEntity) o;
        return Objects.equals(city, that.city) && Objects.equals(cityId, that.cityId) && Objects.equals(temp, that.temp) && Objects.equals(WD, that.WD) && Objects.equals(WS, that.WS) && Objects.equals(SD, that.SD) && Objects.equals(AP, that.AP) && Objects.equals(njd, that.njd) && Objects.equals(WSE, that.WSE) && Objects.equals(time, that.time) && Objects.equals(sm, that.sm) && Objects.equals(isRadar, that.isRadar) && Objects.equals(Radar, that.Radar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, cityId, temp, WD, WS, SD, AP, njd, WSE, time, sm, isRadar, Radar);
    }
}
