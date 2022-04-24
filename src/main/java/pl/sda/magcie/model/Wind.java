package pl.sda.magcie.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Wind {

    private final float speed;
    private final int deg;
}
