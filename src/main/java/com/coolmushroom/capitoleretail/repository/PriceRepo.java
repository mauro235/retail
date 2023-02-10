package com.coolmushroom.capitoleretail.repository;

import com.coolmushroom.capitoleretail.core.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {
    @Query(value =
    """
    SELECT * FROM prices p
        INNER JOIN brands b ON p.brand_id = b.id
    WHERE :date BETWEEN start_date and end_date
        AND p.brand_id = :brandId
        AND p.product_id = :productId
    ORDER BY p.priority DESC
    LIMIT 1
    """,
    nativeQuery = true)
    Optional<Price> findPriceByApplicationDateProductAndBrand(LocalDateTime date, Long productId, Long brandId);
}
