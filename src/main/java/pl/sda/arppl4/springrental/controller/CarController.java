package pl.sda.arppl4.springrental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.arppl4.springrental.service.CarService;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor

public class CarController {

    private final CarService carService;



}
