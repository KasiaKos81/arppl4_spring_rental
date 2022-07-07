package pl.sda.arppl4.springrental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class CarService {

    private final CarRepository carRepository;

    public void addCar(Car car) {carRepository.save(car);}
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteByID(Long identyfikator) {
        carRepository.deleteById(identyfikator);
    }

    public void updateCar(Car updateData) {
        Long identifier = updateData.getId(); // identyfikator obiektu edytowanego

        Optional<Car> carOptional = carRepository.findById(identifier);
        if (carOptional.isPresent()) {
            Car editedCar = carOptional.get();

            if (updateData.getName() != null) {
               editedCar.setName(updateData.getName());
            }
            if (updateData.getBrand() != null) {
                editedCar.setBrand(updateData.getBrand());
            }
            if (updateData.getProductionDate() != null) {
                editedCar.setProductionDate(updateData.getProductionDate());
            }
            if (updateData.getBodyType() != null) {
                editedCar.setBodyType(updateData.getBodyType());
            }
            if (updateData.getSeatsNo() != null) {
                editedCar.setSeatsNo(updateData.getSeatsNo());
            }
            if (updateData.getTransmission() != null) {
                editedCar.setTransmission(updateData.getTransmission());
            }
            if (updateData.getCapacity() != null) {
                editedCar.setCapacity(updateData.getCapacity());
            }

            carRepository.save(editedCar);
            log.info("Car update.");
            return;
        }
        throw new EntityNotFoundException("No car with id: " + updateData.getId());
    }



}
