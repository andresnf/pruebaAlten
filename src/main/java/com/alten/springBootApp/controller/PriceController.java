package com.alten.springBootApp.controller;

import com.alten.springBootApp.model.Price;
import com.alten.springBootApp.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<Price> getPrice(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha,
                                          @RequestParam("productoId") Long productoId,
                                          @RequestParam("cadenaId") Long cadenaId) {

        Price selectedPrice = priceService.getSelectedPrice(fecha, productoId, cadenaId);

        if (selectedPrice != null) {
            return ResponseEntity.ok(selectedPrice);
        }

        return ResponseEntity.notFound().build();
    }

}

