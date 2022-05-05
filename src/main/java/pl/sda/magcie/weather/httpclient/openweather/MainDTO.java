package pl.sda.magcie.weather.httpclient.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class MainDTO {
    @JsonProperty("temp")
    public double temperature;
    public int pressure;
    public int humidity;

}
