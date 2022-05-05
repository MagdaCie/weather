package pl.sda.magcie.weather.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeatherDataEntity {
    @Id
    @Column(name = "id")
    @Type(type="uuid-char")
    private final UUID uuid = UUID.randomUUID();
    private Timestamp date;
    private double temperature;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private double windDegree;
}
