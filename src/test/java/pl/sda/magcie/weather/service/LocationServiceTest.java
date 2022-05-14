package pl.sda.magcie.weather.service;

import org.junit.jupiter.api.Test;
import pl.sda.magcie.weather.httpclient.LocationNameClient;
import pl.sda.magcie.weather.httpcontroller.CreateLocationRequest;
import pl.sda.magcie.weather.httpcontroller.LocationDTO;
import pl.sda.magcie.weather.repository.LocationEntity;
import pl.sda.magcie.weather.repository.LocationRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class LocationServiceTest {

    private final LocationNameClient locationNameClient = mock(LocationNameClient.class);
    private final LocationRepository repository = mock(LocationRepository.class);

    @Test
    void createLocation_shouldPopulateValuesFromRequest() {
        //given
        CreateLocationRequest request = new CreateLocationRequest(1, 2, "testLabel");
        LocationService locationService = new LocationService(locationNameClient, repository);
        //when
        LocationDTO location = locationService.createLocation(request);
        //then
        assertThat(location.latitude).isEqualTo(1);
        assertThat(location.longitude).isEqualTo(2);
        assertThat(location.label).isEqualTo("testLabel");
    }

    @Test
    void createLocation_shouldPopulateName() {
        //given
        CreateLocationRequest request = new CreateLocationRequest(1, 2, "testLabel");
        LocationService locationService = new LocationService(locationNameClient, repository);
        given(locationNameClient.getLocationNameByGeoPosition(1, 2)).willReturn("testName");
        //when
        LocationDTO location = locationService.createLocation(request);
        //then
        assertThat(location.name).isEqualTo("testName");
    }

    @Test
    void createLocation_shouldSaveEntity() {
        //given
        CreateLocationRequest request = new CreateLocationRequest(1, 2, "testLabel");
        LocationService locationService = new LocationService(locationNameClient, repository);
        //when
        locationService.createLocation(request);
        //then
        then(repository).should().saveAndFlush(any());
    }

    @Test
    void getAllLocations() {
        //given
        LocationEntity locationEntity = new LocationEntity(1, 2, "testName", "testLabel");
        List<LocationEntity> locations = List.of(locationEntity);
        given(repository.findAll()).willReturn(locations);
        LocationService locationService = new LocationService(locationNameClient, repository);
        //when
        List<LocationDTO> allLocations = locationService.getAllLocations();
        //then
        assertThat(allLocations).usingRecursiveFieldByFieldElementComparator().containsOnly(LocationDTO.fromEntity(locationEntity));
    }
}