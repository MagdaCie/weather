package pl.sda.magcie.weather.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WeatherData")
public class WeatherDataEntity {
    @Id
    @Type(type="uuid-char")
    private final UUID id = UUID.randomUUID();
    private Timestamp date;
    private double temperature;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private double windDegree;
}
