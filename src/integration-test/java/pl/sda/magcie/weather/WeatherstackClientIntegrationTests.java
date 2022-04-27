package pl.sda.magcie.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.magcie.weather.httpclient.weatherstack.WeatherstackCurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.model.Location;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WeatherstackClientIntegrationTests {

    @Autowired
    private WeatherstackCurrentWeatherClient client;

    @Test
    void shouldFetchCurrentWeather() {
        //given
        Location lancutLocation = new Location(50.0688, 22.2244);
        //when
        CurrentWeatherData currentWeatherData = client.fetchCurrentWeatherData(lancutLocation);
        //then
        assertThat(currentWeatherData.getHumidity()).isGreaterThan(0);
    }
}
