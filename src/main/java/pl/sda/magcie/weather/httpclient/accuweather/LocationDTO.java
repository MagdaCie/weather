package pl.sda.magcie.weather.httpclient.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;

class LocationDTO {
    @JsonProperty("Key")
    public String key;
    @JsonProperty("LocalizedName")
    public String localizedName;
}
