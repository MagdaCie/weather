package pl.sda.magcie.weather.service;

import org.springframework.stereotype.Service;
import pl.sda.magcie.weather.httpclient.accuweather.AccuWeatherCurrentWeatherClient;
import pl.sda.magcie.weather.httpcontroller.CreateLocationRequest;
import pl.sda.magcie.weather.httpcontroller.LocationDTO;
import pl.sda.magcie.weather.repository.LocationEntity;
import pl.sda.magcie.weather.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    private final AccuWeatherCurrentWeatherClient accuWeatherCurrentWeatherClient;
    private final LocationRepository repository;

    public LocationService(AccuWeatherCurrentWeatherClient accuWeatherCurrentWeatherClient, LocationRepository repository) {
        this.accuWeatherCurrentWeatherClient = accuWeatherCurrentWeatherClient;
        this.repository = repository;
    }

    public LocationDTO createLocation(CreateLocationRequest request) {
        String name = accuWeatherCurrentWeatherClient.getLocationNameByGeoPosition(request.latitude, request.longitude);
        LocationEntity locationEntity = new LocationEntity(request.latitude, request.longitude, name, request.label);
        repository.saveAndFlush(locationEntity);
        return new LocationDTO(locationEntity.getId(), request.latitude, request.longitude, name, request.label);
    }

    public List<LocationDTO> getAllLocations(){
        return null;
    }
}
