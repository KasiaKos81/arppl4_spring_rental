package pl.sda.arppl4.springrental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.model.CarRental;
import pl.sda.arppl4.springrental.model.dto.CarDTO;
import pl.sda.arppl4.springrental.model.dto.RentCatRequest;
import pl.sda.arppl4.springrental.service.CarRentalService;
import pl.sda.arppl4.springrental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/carRental")
@RequiredArgsConstructor


public class CarRentalControler {

    private final CarRentalService carRentalService;

    @GetMapping("/available")
    public List<CarDTO> getAvailableCars() {
        log.info("Requested rental available cars");
        return carRentalService.getAllAvailableCars();
    }

    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.CREATED)
    public void rentCar(@RequestParam Long carId, @RequestBody RentCatRequest request) {
        log.info("Requested rental of car with id: " + carId);
        carRentalService.rentCar(carId, request);
    }

    @PatchMapping("/return")
    public void returnCar(@RequestParam Long carId) {
        log.info("Requested return of car with id: " + carId);
        carRentalService.retunCar(carId);

    }

}
