package pl.sda.arppl4.springrental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.repository.CarRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class CarService {

    private final CarRepository carRepository;

    public void addCar(Car car) {carRepository.save(car);}
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

}
