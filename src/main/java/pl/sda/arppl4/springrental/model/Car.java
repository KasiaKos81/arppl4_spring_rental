package pl.sda.arppl4.springrental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.springrental.model.dto.CarDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private LocalDate productionDate;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private Integer seatsNo;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private Double capacity;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<CarRental> carRentals;

    public CarDTO mapToCarDTO() {
        return new CarDTO(
                id,
                name,
                brand,
                productionDate,
                bodyType,
                seatsNo,
                transmission,
                capacity
        );
    }








}
