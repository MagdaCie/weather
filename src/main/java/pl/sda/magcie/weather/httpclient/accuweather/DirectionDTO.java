package pl.sda.magcie.weather.httpclient.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class DirectionDTO {
    @JsonProperty("Degrees")
    public int degree;
}
