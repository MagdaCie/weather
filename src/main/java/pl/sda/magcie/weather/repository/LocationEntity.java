package pl.sda.magcie.weather.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Location")
public class LocationEntity {

    @Id
    @Type(type="uuid-char")
    private final UUID id = UUID.randomUUID();
    private double latitude;
    private double longitude;
    private String name;
    @Column(unique = true)
    private String label;
}
