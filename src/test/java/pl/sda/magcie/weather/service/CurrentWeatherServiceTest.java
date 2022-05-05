package pl.sda.magcie.weather.service;

import org.junit.jupiter.api.Test;
import pl.sda.magcie.weather.httpclient.CurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Location;
import pl.sda.magcie.weather.model.Wind;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentWeatherServiceTest {

    @Test
    void getCurrentWeather_singleAlwaysOneClient() {
        //given
        Set<CurrentWeatherClient> clients = Collections.singleton(new AlwaysOneCurrentWeatherClient());
        CurrentWeatherService currentWeatherService = new CurrentWeatherService(clients);
        //when
        CurrentWeatherData currentWeather = currentWeatherService.getCurrentWeather(1, 1);
        //then
        assertThat(currentWeather.getHumidity()).isEqualTo(1);
        assertThat(currentWeather.getTemperature()).isEqualTo(1);
        assertThat(currentWeather.getPressure()).isEqualTo(1);
        assertThat(currentWeather.getWind().getDegree()).isEqualTo(1);
        assertThat(currentWeather.getWind().getSpeed()).isEqualTo(1);
    }

    @Test
    void getCurrentWeather_singleAlwaysThreeClient() {
        //given
        Set<CurrentWeatherClient> clients = Collections.singleton(new AlwaysThreeCurrentWeatherClient());
        CurrentWeatherService currentWeatherService = new CurrentWeatherService(clients);
        //when
        CurrentWeatherData currentWeather = currentWeatherService.getCurrentWeather(1, 1);
        //then
        assertThat(currentWeather.getHumidity()).isEqualTo(3);
        assertThat(currentWeather.getTemperature()).isEqualTo(3);
        assertThat(currentWeather.getPressure()).isEqualTo(3);
        assertThat(currentWeather.getWind().getDegree()).isEqualTo(3);
        assertThat(currentWeather.getWind().getSpeed()).isEqualTo(3);
    }
    @Test
    void getCurrentWeather_multipleClients() {
        //given
        Set<CurrentWeatherClient> clients = new HashSet<>();
        clients.add(new AlwaysOneCurrentWeatherClient());
        clients.add(new AlwaysThreeCurrentWeatherClient());
        CurrentWeatherService currentWeatherService = new CurrentWeatherService(clients);
        //when
        CurrentWeatherData currentWeather = currentWeatherService.getCurrentWeather(1, 1);
        //then
        assertThat(currentWeather.getHumidity()).isEqualTo(2);
        assertThat(currentWeather.getTemperature()).isEqualTo(2);
        assertThat(currentWeather.getPressure()).isEqualTo(2);
        assertThat(currentWeather.getWind().getDegree()).isEqualTo(2);
        assertThat(currentWeather.getWind().getSpeed()).isEqualTo(2);
    }
    private static class AlwaysOneCurrentWeatherClient implements CurrentWeatherClient {
        @Override
        public CurrentWeatherData fetchCurrentWeatherData(Location location) {
            return new CurrentWeatherData(1, 1, 1, new Wind(1, 1));
        }
    }

    private static class AlwaysThreeCurrentWeatherClient implements CurrentWeatherClient {
        @Override
        public CurrentWeatherData fetchCurrentWeatherData(Location location) {
            return new CurrentWeatherData(3, 3, 3, new Wind(3, 3));
        }
    }


}
