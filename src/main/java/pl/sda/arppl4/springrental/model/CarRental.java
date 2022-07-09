package pl.sda.arppl4.springrental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientSurname;

    @CreationTimestamp
    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;
    private Double pricePerHour;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Car car;

    public CarRental(String clientName, String clientSurname, Double pricePerHour) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.pricePerHour = pricePerHour;
    }
}

