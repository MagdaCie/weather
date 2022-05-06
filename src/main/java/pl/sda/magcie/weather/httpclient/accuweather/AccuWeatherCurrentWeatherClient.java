package pl.sda.magcie.weather.httpclient.accuweather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.magcie.weather.httpclient.CurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Wind;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccuWeatherCurrentWeatherClient implements CurrentWeatherClient {

    @Value("${app.accuweather.api-key}")
    private String apiKeyAW;

    @Override
    public CurrentWeatherData fetchCurrentWeatherData(double lat, double lon) {
        String locationKey = getLocationKeyByGeoPosition(lat, lon);

        String url = "http://dataservice.accuweather.com/currentconditions/v1/" + locationKey + "?apikey={apiKeyAW}&details={details}";
        Map<String, String> uriParameters = createCurrentConditionsUriParams();
        RestTemplate restTemplate = new RestTemplate();
        WeatherDTO[] weatherDTOArray = restTemplate.getForObject(url, WeatherDTO[].class, uriParameters);
        if (weatherDTOArray == null) {
            return null;
        }
        WeatherDTO weatherDTO = weatherDTOArray[0];

        Wind wind = new Wind(weatherDTO.wind.speed.metric.value, weatherDTO.wind.direction.degree);
        return new CurrentWeatherData(
                weatherDTO.temperature.metric.value, (int) weatherDTO.pressure.metric.value, weatherDTO.RelativeHumidity, wind);


    }

    private Map<String, String> createCurrentConditionsUriParams() {
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("apiKeyAW", apiKeyAW);
        uriParameters.put("details", String.valueOf(true));
        return uriParameters;
    }

    private String getLocationKeyByGeoPosition(double lat, double lon) {
        LocationDTO locationDTO = getLocationDTO(lat, lon);
        if (locationDTO != null) {
            return locationDTO.key;
        }
        return null;
    }

    public String getLocationNameByGeoPosition(double lat, double lon) {
        LocationDTO locationDTO = getLocationDTO(lat, lon);
        if (locationDTO != null) {
            return locationDTO.localizedName;
        }
        return null;
    }

    private LocationDTO getLocationDTO(double lat, double lon) {
        String url = "http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey={apiKeyAW}&q={lat},{lon}";
        Map<String, String> uriParameters = createLocationUriParams(lat, lon);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, LocationDTO.class, uriParameters);
    }

    private Map<String, String> createLocationUriParams(double lat, double lon) {
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("lat", String.valueOf(lat));
        uriParameters.put("lon", String.valueOf(lon));
        uriParameters.put("apiKeyAW", apiKeyAW);
        return uriParameters;
    }

}
