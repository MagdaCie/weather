package pl.sda.magcie.weather.httpclient.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class MetricDTO {
    @JsonProperty("Value")
    public double value;
}
