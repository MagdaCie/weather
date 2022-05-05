package pl.sda.magcie.weather.httpclient.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class SpeedDTO {
    @JsonProperty("Metric")
    public MetricDTO metric;
}
