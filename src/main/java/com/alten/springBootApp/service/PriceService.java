package com.alten.springBootApp.service;

import com.alten.springBootApp.model.Price;
import com.alten.springBootApp.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getSelectedPrice(LocalDateTime fecha, Long productoId, Long cadenaId) {
        List<Price> prices = priceRepository.findByProductIdAndBrandIdAndDateRange(productoId, cadenaId, fecha);

        // Ordenar las tarifas por prioridad de forma descendente
        prices.sort(Comparator.comparingInt(Price::getPriority).reversed());

        return prices.isEmpty() ? null : prices.get(0); // La primera tarifa es la de mayor prioridad
    }

}
