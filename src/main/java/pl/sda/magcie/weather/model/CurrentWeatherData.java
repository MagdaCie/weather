package pl.sda.magcie.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CurrentWeatherData {
    //celsius degrees
    private final double temperature;
    //hPa
    private final double pressure;
    //%
    private final double humidity;
    //km/h
    private final Wind wind;

}
