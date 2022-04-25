package pl.sda.magcie.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CurrentWeatherData {
    //celsius degrees
    private final double temperature;
    //hPa
    private final int pressure;
    //%
    private final int humidity;
    //km/h
    private final Wind wind;

}
