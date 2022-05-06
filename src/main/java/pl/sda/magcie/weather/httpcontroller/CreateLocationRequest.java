package pl.sda.magcie.weather.httpcontroller;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateLocationRequest {
    public double latitude;
    public double longitude;
    public String label;
}
