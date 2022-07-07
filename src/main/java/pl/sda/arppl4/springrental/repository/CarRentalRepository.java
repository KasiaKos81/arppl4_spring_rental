package pl.sda.arppl4.springrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.model.CarRental;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {
}
