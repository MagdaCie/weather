package pl.sda.magcie.httpclient;

import pl.sda.magcie.model.CurrentWeatherData;
import pl.sda.magcie.model.Location;

public interface CurrentWeatherClient {
    CurrentWeatherData fetchCurrentWeatherData(Location location);
}
