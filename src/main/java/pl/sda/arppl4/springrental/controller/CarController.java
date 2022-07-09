package pl.sda.arppl4.springrental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.model.dto.CarDTO;
import pl.sda.arppl4.springrental.service.CarService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor

public class CarController {

    private final CarService carService;

    @GetMapping("list/")
    public List<CarDTO> getAllCars(){
        log.info("Get the list of all cars");
        List<CarDTO> list = carService.getAllCars();
        return list;
    }

   // @GetMapping()
  //  public ResponseEntity<List<CarDTO>> list() {
    //    log.info("Received request: list");
     //   return ResponseEntity
        //        .status(HttpStatus.OK)
       //         .body(carService.findAll());
 //   }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car){
        log.info("Post a car " + car);
        carService.addCar(car);
    }

    @DeleteMapping("/delete/{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable(name="identifier") Long identyfikator){
        log.info("Delete car using id " + identyfikator);
        carService.deleteByID(identyfikator);
    }

    @PatchMapping("/update")
    public void upDateProduct(@RequestBody Car car){
        log.info("Car update " + car);
        carService.updateCar(car);
    }

}
