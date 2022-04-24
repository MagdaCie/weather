package pl.sda.magcie.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CurrentWeatherData {

    private final double temperature;
    private final int pressure;
    private final int humidity;
    private final Wind wind;

}
