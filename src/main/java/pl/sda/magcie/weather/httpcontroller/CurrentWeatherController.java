package pl.sda.magcie.weather.httpcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.magcie.weather.model.CurrentWeatherData;
import pl.sda.magcie.weather.service.CurrentWeatherService;

@RestController
@RequestMapping(path = "/currentweather")
public class CurrentWeatherController {

    private final CurrentWeatherService currentWeatherService;

    public CurrentWeatherController(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping
    CurrentWeatherData getCurrentWeather(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        return currentWeatherService.getCurrentWeather(lat, lon);
    }
}
