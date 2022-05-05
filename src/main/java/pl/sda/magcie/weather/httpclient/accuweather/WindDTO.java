package pl.sda.magcie.weather.httpclient.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class WindDTO {
    @JsonProperty("Direction")
    public DirectionDTO direction;
    @JsonProperty("Speed")
    public SpeedDTO speed;
}
