package pl.sda.arppl4.springrental.controller;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.sda.arppl4.springrental.Arppl4SpringRentalApplication;
import pl.sda.arppl4.springrental.model.BodyType;
import pl.sda.arppl4.springrental.model.Car;
import pl.sda.arppl4.springrental.model.dto.CarDTO;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Arppl4SpringRentalApplication.class)

public class CarControllerTest {

    @LocalServerPort
    public int randomServerPort;

    @Test
    public void test_emptyListAfterStartDoesNotThrowErrorsAndReturnsStatusCodeOK() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<List<CarDTO>> responseEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car/list", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<CarDTO>>() {
        });
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<CarDTO> carDTOS = responseEntity.getBody();
        Assertions.assertEquals(0, carDTOS.size());
    }

    @Test
    public void test_addFirstObjectDoesNotThrowErrorsAndReturnsStatusCodeCreated() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Void> addResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + randomServerPort + "/api/car/add", HttpMethod.POST,
                new HttpEntity<>(Car.builder()
                        .name("Hondzix")
                        .brand("Honda")
                        .bodyType(BodyType.SEDAN)
                        .seatsNo(4)
                        .capacity(1.7)
                        .build()
                ),
                Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, addResponseEntity.getStatusCode());
        ResponseEntity<List<CarDTO>> responseEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car/list", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<CarDTO>>() {
        });
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<CarDTO> carDTOS = responseEntity.getBody();
        Assertions.assertEquals(1, carDTOS.size());

        CarDTO theOnlyElement = carDTOS.get(0);

        Assertions.assertEquals("Hondzix", theOnlyElement.getName());
        Assertions.assertEquals("Honda", theOnlyElement.getMake());
        Assertions.assertEquals(BodyType.SEDAN, theOnlyElement.getBodyType());
        Assertions.assertEquals(4, theOnlyElement.getSeats());
        Assertions.assertEquals(1.7, theOnlyElement.getCapacity());
        Assertions.assertNull(theOnlyElement.getTransmission());
        Assertions.assertNull(theOnlyElement.getProductionDate());
        Assertions.assertNotNull(theOnlyElement.getCarId());


        // TODO: wyciągnąć z listy ten 1 element i zwreryfikować że powinien mieć odpowiednią nazwę,
        //  make, body, skrzynię(podchwytliwe), seats, engine....
    }


}
