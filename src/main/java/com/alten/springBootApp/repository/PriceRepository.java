package com.alten.springBootApp.repository;

import com.alten.springBootApp.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "AND p.startDate <= :fecha " +
            "AND p.endDate >= :fecha")
    List<Price> findByProductIdAndBrandIdAndDateRange(@Param("productId") Long productId,
                                                      @Param("brandId") Long brandId,
                                                      @Param("fecha") LocalDateTime fecha);
}


