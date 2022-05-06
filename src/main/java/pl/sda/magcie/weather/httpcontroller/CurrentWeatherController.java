package pl.sda.magcie.weather.httpcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.service.CurrentWeatherService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CurrentWeatherController {

    private final CurrentWeatherService currentWeatherService;

    @Autowired
    public CurrentWeatherController(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @RequestMapping(path = "/currentweather", method = GET)
    CurrentWeatherData getCurrentWeather(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        return currentWeatherService.getCurrentWeather(lat, lon);
    }
}
