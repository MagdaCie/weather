package pl.sda.magcie.weather.httpclient.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class WeatherDTO {
    public int RelativeHumidity;
    @JsonProperty("Wind")
    public WindDTO wind;
    @JsonProperty("Pressure")
    public PressureDTO pressure;
    @JsonProperty("Temperature")
    public TemperatureDTO temperature;

}
