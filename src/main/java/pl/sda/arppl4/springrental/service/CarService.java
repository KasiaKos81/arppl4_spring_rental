package pl.sda.arppl4.springrental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.springrental.repository.CarRepository;

@Slf4j
@Service
@RequiredArgsConstructor

public class CarService {

    private final CarRepository carRepository;

}