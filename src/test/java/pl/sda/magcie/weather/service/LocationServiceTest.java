package pl.sda.magcie.weather.service;

import org.junit.jupiter.api.Test;
import pl.sda.magcie.weather.httpclient.accuweather.AccuWeatherCurrentWeatherClient;
import pl.sda.magcie.weather.httpcontroller.CreateLocationRequest;
import pl.sda.magcie.weather.httpcontroller.LocationDTO;
import pl.sda.magcie.weather.repository.LocationRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class LocationServiceTest {

    private final AccuWeatherCurrentWeatherClient accuWeatherCurrentWeatherClient = mock(AccuWeatherCurrentWeatherClient.class);
    private final LocationRepository repository = mock(LocationRepository.class);

    @Test
    void createLocation_shouldPopulateValuesFromRequest() {
        //given
        CreateLocationRequest request = new CreateLocationRequest(1, 2, "testLabel");
        LocationService locationService = new LocationService(accuWeatherCurrentWeatherClient, repository);
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
        LocationService locationService = new LocationService(accuWeatherCurrentWeatherClient, repository);
        given(accuWeatherCurrentWeatherClient.getLocationNameByGeoPosition(1, 2)).willReturn("testName");
        //when
        LocationDTO location = locationService.createLocation(request);
        //then
        assertThat(location.name).isEqualTo("testName");
    }

    @Test
    void createLocation_shouldSaveEntity() {
        //given
        CreateLocationRequest request = new CreateLocationRequest(1, 2, "testLabel");
        LocationService locationService = new LocationService(accuWeatherCurrentWeatherClient, repository);
        //when
        locationService.createLocation(request);
        //then
        then(repository).should().saveAndFlush(any());
    }
}