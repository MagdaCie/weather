package pl.sda.magcie.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.magcie.weather.httpclient.accuweather.AccuWeatherCurrentWeatherClient;
import pl.sda.magcie.weather.model.CurrentWeatherData;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccuWeatherClientIntegrationTests {

    @Autowired
    private AccuWeatherCurrentWeatherClient client;

    @Test
    void shouldFetchCurrentWeather() {
        //when
        CurrentWeatherData currentWeatherData = client.fetchCurrentWeatherData(50.0688, 22.2244);
        //then
        assertThat(currentWeatherData.getHumidity()).isGreaterThan(0);
    }
}
