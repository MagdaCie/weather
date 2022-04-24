package pl.sda.magcie.weather.httpclient.openweather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.magcie.weather.httpclient.CurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Location;
import pl.sda.magcie.weather.model.Wind;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherCurrentWeatherClient implements CurrentWeatherClient {

    @Value("${app.openweather.api-key}")
    private String apiKey;

    @Override
    public CurrentWeatherData fetchCurrentWeatherData(Location location) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("lat", String.valueOf(location.getLatitude()));
        uriParameters.put("lon", String.valueOf(location.getLongitude()));
        uriParameters.put("apiKey", apiKey);
        RestTemplate restTemplate = new RestTemplate();
        WeatherDTO weatherDTO = restTemplate.getForObject(url, WeatherDTO.class, uriParameters);
        if (weatherDTO != null) {
            Wind wind = new Wind(weatherDTO.wind.speed, weatherDTO.wind.deg);
            return new CurrentWeatherData(
                    weatherDTO.main.temp, weatherDTO.main.pressure, weatherDTO.main.humidity, wind);
        }
        return null;
    }
}
