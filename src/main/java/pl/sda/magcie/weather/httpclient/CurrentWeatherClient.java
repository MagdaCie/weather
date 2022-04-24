package pl.sda.magcie.weather.httpclient;

import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Location;

public interface CurrentWeatherClient {
    CurrentWeatherData fetchCurrentWeatherData(Location location);
}
