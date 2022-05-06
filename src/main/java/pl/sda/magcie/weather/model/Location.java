package pl.sda.magcie.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;

@Getter
@RequiredArgsConstructor
public class Location {
    private final double latitude;
    private final double longitude;
    private final String name;
    private final String label;
}
