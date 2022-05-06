package pl.sda.magcie.weather.httpclient.openweather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.magcie.weather.httpclient.CurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Wind;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherCurrentWeatherClient implements CurrentWeatherClient {

    private static final int secondsInHour = 3600;
    private static final int metersInKilometer = 1000;

    @Value("${app.openweather.api-key}")
    private String apiKey;

    @Override
    public CurrentWeatherData fetchCurrentWeatherData(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
        Map<String, String> uriParameters = createUriParams(lat, lon);
        RestTemplate restTemplate = new RestTemplate();
        WeatherDTO weatherDTO = restTemplate.getForObject(url, WeatherDTO.class, uriParameters);
        if (weatherDTO != null) {
            return createCurrentWeatherData(weatherDTO);
        }
        return null;
    }

    private CurrentWeatherData createCurrentWeatherData(WeatherDTO weatherDTO) {
        Wind wind = new Wind(weatherDTO.wind.speed * secondsInHour / metersInKilometer, weatherDTO.wind.degree);
        return new CurrentWeatherData(
                weatherDTO.main.temperature - 272.15, weatherDTO.main.pressure, weatherDTO.main.humidity, wind);
    }

    private Map<String, String> createUriParams(double lat, double lon) {
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("lat", String.valueOf(lat));
        uriParameters.put("lon", String.valueOf(lon));
        uriParameters.put("apiKey", apiKey);
        return uriParameters;
    }
}
