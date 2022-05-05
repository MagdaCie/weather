package pl.sda.magcie.weather.httpclient.weatherstack;

import com.fasterxml.jackson.annotation.JsonProperty;

class CurrentDTO {
    public double temperature;
    @JsonProperty("wind_speed")
    public double windSpeed;
    @JsonProperty("wind_degree")
    public int windDegree;
    public int pressure;
    public int humidity;

}
