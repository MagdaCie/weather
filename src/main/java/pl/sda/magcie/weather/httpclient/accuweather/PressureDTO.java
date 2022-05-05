package pl.sda.magcie.weather.httpclient.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class PressureDTO {
    @JsonProperty("Metric")
    public MetricDTO metric;
}
