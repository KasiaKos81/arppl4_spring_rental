package pl.sda.arppl4.springrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.springrental.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {


        }


