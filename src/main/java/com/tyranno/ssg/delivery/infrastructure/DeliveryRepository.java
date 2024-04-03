package com.tyranno.ssg.delivery.infrastructure;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByUsersUuid(String uuid);

    Optional<Delivery> findByIsBaseDeliveryAndUsers(Byte isBaseDelivery, Users users);
}
