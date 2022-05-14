package pl.sda.magcie.weather.service;

import org.springframework.stereotype.Service;
import pl.sda.magcie.weather.httpclient.LocationNameClient;
import pl.sda.magcie.weather.httpcontroller.CreateLocationRequest;
import pl.sda.magcie.weather.httpcontroller.LocationDTO;
import pl.sda.magcie.weather.repository.LocationEntity;
import pl.sda.magcie.weather.repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationNameClient locationNameClient;
    private final LocationRepository repository;

    public LocationService(LocationNameClient locationNameClient, LocationRepository repository) {
        this.locationNameClient = locationNameClient;
        this.repository = repository;
    }

    public LocationDTO createLocation(CreateLocationRequest request) {
        String name = locationNameClient.getLocationNameByGeoPosition(request.latitude, request.longitude);
        LocationEntity locationEntity = new LocationEntity(request.latitude, request.longitude, name, request.label);
        repository.saveAndFlush(locationEntity);
        return new LocationDTO(locationEntity.getId(), request.latitude, request.longitude, name, request.label);
    }

    public List<LocationDTO> getAllLocations() {
        return repository.findAll().stream()
                .map(LocationDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
