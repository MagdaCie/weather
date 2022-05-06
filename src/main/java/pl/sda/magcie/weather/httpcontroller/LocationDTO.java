package pl.sda.magcie.weather.httpcontroller;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class LocationDTO {
    public UUID id;
    public double latitude;
    public double longitude;
    public String name;
    public String label;
}
