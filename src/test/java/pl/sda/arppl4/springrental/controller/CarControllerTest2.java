package pl.sda.arppl4.springrental.controller;

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

public class CarControllerTest2 {

    // TODO: stwórz nowy plik, skopiuj poprzedni test do nowego pliku, napisz następujący test:
    //          1. masz pustą listę, dodaj element (baza powinna mieć jeden element), potem usuń element, (baza nie powinna posiadać elementu)
    //              - masz pustą listę (opcjonalne)
    //              - dodaj element
    //              - pobierz listę
    //              - sprawdz czy element jest na liscie
    //              - usun element
    //              - pobierz liste
    //              - sprawdz czy element (nie) jest na liscie

    @LocalServerPort
    public int randomServerPort;

    @Test

    public void test_checkListSize() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<List<CarDTO>> responseEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car/list", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<CarDTO>>() {
        });
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<CarDTO> carDTOS = responseEntity.getBody();
        Assertions.assertEquals(0, carDTOS.size());
    }
    @Test

        public void test_addElementCheckSizeAndStatusCodeCreatedDeleteElementCheckStatusCodeNoContentCheckSize() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Void> addResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + randomServerPort + "/api/car/add", HttpMethod.POST,
                new HttpEntity<>(Car.builder()
                        .name("Maluszek")
                        .brand("Fiacik")
                        .bodyType(BodyType.HATCHBACK)
                        .seatsNo(3)
                        .capacity(1.0)
                        .build()
                ),
                Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, addResponseEntity.getStatusCode());
        ResponseEntity<List<CarDTO>> responseEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car/list", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<CarDTO>>() {
        });
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<CarDTO> carDTOS = responseEntity.getBody();
        Assertions.assertEquals(1, carDTOS.size());
        System.out.println(carDTOS);

        CarDTO theOnlyElement = carDTOS.get(0);

        Assertions.assertEquals("Maluch", theOnlyElement.getName());
        Assertions.assertEquals("Fiat", theOnlyElement.getMake());
        Assertions.assertEquals(BodyType.HATCHBACK, theOnlyElement.getBodyType());
        Assertions.assertEquals(3, theOnlyElement.getSeats());
        Assertions.assertEquals(1.0, theOnlyElement.getCapacity());
        Assertions.assertNull(theOnlyElement.getTransmission());
        Assertions.assertNull(theOnlyElement.getProductionDate());
        Assertions.assertNotNull(theOnlyElement.getCarId());

        Long carId = carDTOS.get(0).getCarId();

        ResponseEntity<Void> deleteEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car/delete/" + carId, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, deleteEntity.getStatusCode());

        ResponseEntity<List<CarDTO>> listEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car/list", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<CarDTO>>() {
        });
        Assertions.assertEquals(HttpStatus.OK, listEntity.getStatusCode());

        List<CarDTO> carsDTOS = listEntity.getBody();
        Assertions.assertEquals(0, carsDTOS.size());


    }


}
