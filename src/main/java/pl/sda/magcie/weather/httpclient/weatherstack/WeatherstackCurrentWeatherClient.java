package pl.sda.magcie.weather.httpclient.weatherstack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.magcie.weather.convert.PolishToEnglishLettersConverter;
import pl.sda.magcie.weather.httpclient.CurrentWeatherClient;
import pl.sda.magcie.weather.httpclient.LocationNameClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Wind;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherstackCurrentWeatherClient implements CurrentWeatherClient {

    @Value("${app.weatherstack.api-key}")
    private String apiKeyWS;

    private final LocationNameClient locationNameClient;

    public WeatherstackCurrentWeatherClient(LocationNameClient locationNameClient) {
        this.locationNameClient = locationNameClient;
    }

    @Override
    public CurrentWeatherData fetchCurrentWeatherData(double lat, double lon) {
        String url = "http://api.weatherstack.com/current?access_key={apiKeyWS}&query={locationName}";
        String nonEnglishLettersLocation = locationNameClient.getLocationNameByGeoPosition(lat, lon);
        String locationName = PolishToEnglishLettersConverter.convert(nonEnglishLettersLocation);
        Map<String, String> uriParameters = createCurrentConditionsUriParams(locationName);
        RestTemplate restTemplate = new RestTemplate();
        WeatherDTO weatherDTO = restTemplate.getForObject(url, WeatherDTO.class, uriParameters);
        if (weatherDTO != null) {
            return createCurrentWeatherData(weatherDTO);
        }
        return null;
    }

    private CurrentWeatherData createCurrentWeatherData(WeatherDTO weatherDTO) {
        Wind wind = new Wind(weatherDTO.current.windSpeed, weatherDTO.current.windDegree);
        return new CurrentWeatherData(
                weatherDTO.current.temperature, weatherDTO.current.pressure, weatherDTO.current.humidity, wind);
    }


    private Map<String, String> createCurrentConditionsUriParams(String locationName) {
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("apiKeyWS", apiKeyWS);
        uriParameters.put("locationName", locationName);
        return uriParameters;
    }

}