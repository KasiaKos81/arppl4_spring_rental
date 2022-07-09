package pl.sda.arppl4.springrental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.springrental.model.BodyType;
import pl.sda.arppl4.springrental.model.Transmission;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarDTO {


    private Long carId;
    private String name;
    private String make;
    private LocalDate productionDate;
    private BodyType bodyType;
    private Integer seats;
    private Transmission transmission;
    private Double capacity;

}
