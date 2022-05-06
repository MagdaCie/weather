package pl.sda.magcie.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.magcie.weather.httpclient.CurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Wind;
import pl.sda.magcie.weather.repository.WeatherDataEntity;
import pl.sda.magcie.weather.repository.WeatherDataRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurrentWeatherService {

    private final Set<CurrentWeatherClient> clients;
    private final WeatherDataRepository repository;

    @Autowired
    public CurrentWeatherService(Set<CurrentWeatherClient> clients, WeatherDataRepository repository) {
        this.clients = clients;
        this.repository = repository;
    }

    public CurrentWeatherData getCurrentWeather(double lat, double lon) {
        Set<CurrentWeatherData> results = clients.stream()
                .map(client -> client.fetchCurrentWeatherData(lat, lon))
                .collect(Collectors.toSet());
        double averageTemperature = results.stream()
                .mapToDouble(CurrentWeatherData::getTemperature)
                .average()
                .orElse(0);
        double averagePressure = results.stream()
                .mapToDouble(CurrentWeatherData::getPressure)
                .average()
                .orElse(0);
        double averageHumidity = results.stream()
                .mapToDouble(CurrentWeatherData::getHumidity)
                .average()
                .orElse(0);
        double averageWindSpeed = results.stream()
                .map(CurrentWeatherData::getWind)
                .mapToDouble(Wind::getSpeed)
                .average()
                .orElse(0);
        double averageWindDegree = results.stream()
                .map(CurrentWeatherData::getWind)
                .mapToDouble(Wind::getDegree)
                .average()
                .orElse(0);

        repository.saveAndFlush(new WeatherDataEntity(
                Timestamp.from(Instant.now()),
                averageTemperature,
                averagePressure,
                averageHumidity,
                averageWindSpeed,
                averageWindDegree));

        return new CurrentWeatherData(averageTemperature, averagePressure,
                averageHumidity, new Wind(averageWindSpeed, averageWindDegree));
    }

}
