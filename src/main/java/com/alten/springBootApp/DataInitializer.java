package com.alten.springBootApp;

import com.alten.springBootApp.model.Price;
import com.alten.springBootApp.repository.PriceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer {

    private final PriceRepository priceRepository;

    public DataInitializer(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @PostConstruct
    //Se ejecuta después de que la aplicación se haya iniciado correctamente
    public void initializeData() {
        // Crea y guarda los objetos Price en la base de datos
        Price price1 = new Price(1L, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455L, 0, 35.50, "EUR");
        priceRepository.save(price1);

        Price price2 = new Price(1L, LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455L, 1, 25.45, "EUR");
        priceRepository.save(price2);

        Price price3 = new Price(1L, LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455L, 1, 30.50, "EUR");
        priceRepository.save(price3);

        Price price4 = new Price(1L, LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455L, 1, 38.95, "EUR");
        priceRepository.save(price4);
    }
}

