package pl.sda.magcie.weather.httpcontroller;

import org.springframework.web.bind.annotation.*;
import pl.sda.magcie.weather.service.LocationService;

import java.util.List;

@RestController
@RequestMapping(path = "/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public LocationDTO createLocation(@RequestBody final CreateLocationRequest createLocationRequest) {
        return locationService.createLocation(createLocationRequest);
    }

    @GetMapping
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }
}
