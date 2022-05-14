package pl.sda.magcie.weather.httpcontroller;

import lombok.AllArgsConstructor;
import pl.sda.magcie.weather.repository.LocationEntity;

import java.util.UUID;

@AllArgsConstructor
public class LocationDTO {
    public UUID id;
    public double latitude;
    public double longitude;
    public String name;
    public String label;

    public static LocationDTO fromEntity(LocationEntity entity) {
        return new LocationDTO(entity.getId(), entity.getLatitude(), entity.getLongitude(), entity.getName(), entity.getLabel());
    }
}
