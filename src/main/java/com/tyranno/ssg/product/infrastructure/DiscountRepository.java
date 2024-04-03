package com.tyranno.ssg.product.infrastructure;

import com.tyranno.ssg.product.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findByProductId(Long usersId);
}
