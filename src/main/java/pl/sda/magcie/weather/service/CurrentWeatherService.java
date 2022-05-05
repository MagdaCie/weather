package pl.sda.magcie.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.magcie.weather.httpclient.CurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Location;
import pl.sda.magcie.weather.model.Wind;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurrentWeatherService {

    private final Set<CurrentWeatherClient> clients;

    @Autowired
    public CurrentWeatherService(Set<CurrentWeatherClient> clients) {
        this.clients = clients;
    }

    public CurrentWeatherData getCurrentWeather(double lat, double lon) {
        Location location = new Location(lat, lon);
        Set<CurrentWeatherData> results = clients.stream()
                .map(client -> client.fetchCurrentWeatherData(location))
                .collect(Collectors.toSet());
        OptionalDouble averageTemperature = results.stream()
                .mapToDouble(CurrentWeatherData::getTemperature)
                .average();
        OptionalDouble averagePressure = results.stream()
                .mapToDouble(CurrentWeatherData::getPressure)
                .average();
        OptionalDouble averageHumidity = results.stream()
                .mapToDouble(CurrentWeatherData::getHumidity)
                .average();
        OptionalDouble averageWindSpeed = results.stream()
                .map(CurrentWeatherData::getWind)
                .mapToDouble(Wind::getSpeed).average();
        OptionalDouble averageWindDeg = results.stream()
                .map(CurrentWeatherData::getWind)
                .mapToDouble(Wind::getDeg).average();
        return new CurrentWeatherData(averageTemperature.orElse(0), averagePressure.orElse(0),
                averageHumidity.orElse(0), new Wind(averageWindSpeed.orElse(0), averageWindDeg.orElse(0)));
    }

}
