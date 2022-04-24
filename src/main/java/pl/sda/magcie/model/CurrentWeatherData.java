package pl.sda.magcie.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CurrentWeatherData {

    private final Long temperature;
    private final Long pressure;
    private final Long humidity;
    private final Wind wind;

}
