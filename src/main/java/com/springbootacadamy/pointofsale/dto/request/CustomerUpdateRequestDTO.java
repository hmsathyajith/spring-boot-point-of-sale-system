package com.springbootacadamy.pointofsale.dto.request;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerUpdateRequestDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private double salary;
    private ArrayList contactNumbers;
    private String nic;
    private boolean activeState;
}
