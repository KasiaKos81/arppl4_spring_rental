package pl.sda.arppl4.springrental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.springrental.exception.CarNotAvailableException;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.model.CarRental;
import pl.sda.arppl4.springrental.model.dto.CarDTO;
import pl.sda.arppl4.springrental.model.dto.RentCatRequest;
import pl.sda.arppl4.springrental.repository.CarRentalRepository;
import pl.sda.arppl4.springrental.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class CarRentalService {

    private final CarRentalRepository carRentalRepository;
    private final CarRepository carRepository;


    public List<CarDTO> getAllAvailableCars() {
        List<Car> carList = carRepository.findAll();

        List<CarDTO> cars = new ArrayList<>();
        for (Car car : carList) {
            if (!isRented(car)) {
                // samochód jest dostępny, zwróć go...
                cars.add(car.mapToCarDTO());
            }
        }
        return cars;
    }

    /**
     * Metoda sprawdza czy dany samochód jest wynajęty. Jeśli jego data zwrotu (dowolnego najmu)
     * jest równa null, to samochód jest wynajęty.
     * @param car - sprawdzany samochód.
     * @return informacja czy samochód jest wynajęty (true/false).
     */
    
    private boolean isRented(Car car){
        for (CarRental carRental : car.getCarRentals()) {
            if (carRental.getRentEndDate() == null) {
                return true;
            }
        }
        return false;
    }

    public void rentCar(Long carId, RentCatRequest request) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();

            // jeśli nie jest wynajęty (to jest ok)
            if (!isRented(car)) {
                CarRental carRental = mapRentCarRequestToCarRental(request);
                carRental.setCar(car);

                carRentalRepository.save(carRental);
                return;
            }
            throw new CarNotAvailableException("Car not available, id: " + carId);
        }
        throw new EntityNotFoundException("Unable to find car with id: " + carId);
    }

    private CarRental mapRentCarRequestToCarRental(RentCatRequest request) {
        return new CarRental(
                request.getNameOfTheClient(),
                request.getSurnameOfTheClient(),
                request.getHourlyPrice());
    }

    public void retunCar(Long carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if(carOptional.isPresent()) {
            Car car = carOptional.get();

            Optional<CarRental> optionalCarRental = findActiveCarRental(car);
            if(optionalCarRental.isPresent()) {
                CarRental carRental = optionalCarRental.get();
                carRental.setRentEndDate(LocalDateTime.now());

                carRentalRepository.save(carRental);
                return;
            }
            throw new CarNotAvailableException("Car not rented, id: " + carId);

        }
        throw new EntityNotFoundException("Unable to find car with id: " + carId);
    }

    private Optional<CarRental> findActiveCarRental(Car car){
        for (CarRental carRental : car.getCarRentals()) {
            if (carRental.getRentEndDate() == null) {
                return Optional.of(carRental);
            }
    }
        return Optional.empty();
}}


