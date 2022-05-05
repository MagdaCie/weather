package pl.sda.magcie.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Wind {

    private final double speed;
    private final double deg;
}
