package pl.sda.arppl4.springrental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RentCatRequest {

    private String surnameOfTheClient;
    private Double hourlyPrice;
    private String nameOfTheClient;

}
