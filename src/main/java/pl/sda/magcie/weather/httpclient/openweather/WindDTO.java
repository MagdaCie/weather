package pl.sda.magcie.weather.httpclient.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class WindDTO {

    public double speed;
    @JsonProperty("deg")
    public int degree;
}
