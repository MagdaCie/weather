package pl.sda.magcie.weather.httpclient;

import pl.sda.magcie.weather.model.CurrentWeatherData;

public interface CurrentWeatherClient {
    CurrentWeatherData fetchCurrentWeatherData(double lat, double lon);
}
