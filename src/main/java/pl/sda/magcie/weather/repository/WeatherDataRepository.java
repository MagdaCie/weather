package pl.sda.magcie.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeatherDataRepository extends JpaRepository<WeatherDataEntity, UUID> {

}
