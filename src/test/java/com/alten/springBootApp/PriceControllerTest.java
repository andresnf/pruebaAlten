package com.alten.springBootApp;

import com.alten.springBootApp.model.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private LocalDateTime fechaAplicacion;
    private Long productoId;
    private Long cadenaId;

    @Before
    public void setup() {
        fechaAplicacion = LocalDateTime.of(2020, 6, 14, 10, 0);
        productoId = 35455L;
        cadenaId = 1L;
    }

    @Test
    public void testGetPrice1() {
        String url = buildUrl();

        ResponseEntity<Price> response = restTemplate.getForEntity(url, Price.class);

        assertPriceResponse(response, 1, 35.50);
    }

    @Test
    public void testGetPrice2() {
        fechaAplicacion = LocalDateTime.of(2020, 6, 14, 16, 0);

        String url = buildUrl();

        ResponseEntity<Price> response = restTemplate.getForEntity(url, Price.class);

        assertPriceResponse(response, 2, 25.45);
    }

    @Test
    public void testGetPrice3() {
        fechaAplicacion = LocalDateTime.of(2020, 6, 14, 21, 0);

        String url = buildUrl();

        ResponseEntity<Price> response = restTemplate.getForEntity(url, Price.class);

        assertPriceResponse(response, 1, 35.50);
    }

    @Test
    public void testGetPrice4() {
        fechaAplicacion = LocalDateTime.of(2020, 6, 15, 10, 0);

        String url = buildUrl();

        ResponseEntity<Price> response = restTemplate.getForEntity(url, Price.class);

        assertPriceResponse(response, 3, 30.50);
    }

    @Test
    public void testGetPrice5() {
        fechaAplicacion = LocalDateTime.of(2020, 6, 16, 21, 0);

        String url = buildUrl();

        ResponseEntity<Price> response = restTemplate.getForEntity(url, Price.class);

        assertPriceResponse(response, 4, 38.95);
    }

    private String buildUrl() {
        return "http://localhost:" + port + "/prices?fecha=" + fechaAplicacion +
                "&productoId=" + productoId + "&cadenaId=" + cadenaId;
    }

    private void assertPriceResponse(ResponseEntity<Price> response, Integer priceList, double price) {
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Price priceResponse = response.getBody();
        assertNotNull(priceResponse);
        assertEquals(productoId, priceResponse.getProductId());
        assertEquals(cadenaId, priceResponse.getBrandId());
        assertEquals(priceList, priceResponse.getPriceList());
        assertEquals(price, priceResponse.getPrice(), 0.001);
        assertEquals("EUR", priceResponse.getCurrency());
    }
}

