package pl.sda.magcie.weather.httpcontroller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.magcie.weather.service.LocationService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LocationController {

   private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(path = "/locations", method = POST)
    public LocationDTO createLocation(@RequestBody final CreateLocationRequest createLocationRequest) {
        return locationService.createLocation(createLocationRequest);
    }

    @RequestMapping(path = "/locations", method = GET)
    public List<LocationDTO> getAllLocations(){
        return locationService.getAllLocations();
    }
}
