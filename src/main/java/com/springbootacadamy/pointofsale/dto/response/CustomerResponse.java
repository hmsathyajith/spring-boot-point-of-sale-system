package com.springbootacadamy.pointofsale.dto.response;

import lombok.*;


// this customer response for only return salary address and id
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerResponse {
    private int customerId;
    private String customerAddress;
    private double salary;
}
