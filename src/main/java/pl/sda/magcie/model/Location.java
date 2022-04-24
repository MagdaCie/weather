package pl.sda.magcie.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Location {
    private final double latitude;
    private final double longitude;
}
