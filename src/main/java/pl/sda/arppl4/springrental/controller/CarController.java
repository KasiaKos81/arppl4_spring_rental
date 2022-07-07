package pl.sda.arppl4.springrental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor

public class CarController {

    private final CarService carService;

    @GetMapping("list/")
    public List<Car> getAllCars(){
        log.info("Get the list of all cars");
        List<Car> list = carService.getAllCars();
        return list;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car){
        log.info("Post a car " + car);
        carService.addCar(car);
    }










}
