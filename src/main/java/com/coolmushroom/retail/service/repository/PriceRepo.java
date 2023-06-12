package com.coolmushroom.retail.service.repository;

import com.coolmushroom.retail.core.entity.Price;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface PriceRepo extends R2dbcRepository<Price, Long> {
    @Query(value =
    """
    SELECT * FROM prices p
        INNER JOIN brands b ON p.brand_id = b.id
    WHERE :date BETWEEN start_date and end_date
        AND p.brand_id = :brandId
        AND p.product_id = :productId
    ORDER BY p.priority DESC
    LIMIT 1
    """)
    Mono<Price> findPriceByApplicationDateProductAndBrand(LocalDateTime date, Long productId, Long brandId);
}
