package com.tyranno.ssg.delivery.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryModifyDto {
    @NotNull
    private Long id;
    @NotNull
    private String deliveryName;
    @NotNull
    private Integer zipCode;
    @NotNull
    private String deliveryBase;

    private String deliveryDetail;
    @NotNull
    private String receiverName;
    @NotNull
    private String phoneNumber;

    private String homeNumber;

    public Delivery toEntity(Delivery delivery) {
        return Delivery.builder()
                .id(delivery.getId())
                .users(delivery.getUsers())
                .isBaseDelivery(delivery.getIsBaseDelivery())
                .deliveryName(deliveryName)
                .zipCode(zipCode)
                .deliveryBase(deliveryBase)
                .deliveryDetail(deliveryDetail)
                .receiverName(receiverName)
                .phoneNumber(phoneNumber)
                .homeNumber(homeNumber)
                .build();
    }
}
