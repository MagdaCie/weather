package pl.sda.magcie.weather.httpclient;

public interface LocationNameClient {
    String getLocationNameByGeoPosition(double lat, double lon);
}
