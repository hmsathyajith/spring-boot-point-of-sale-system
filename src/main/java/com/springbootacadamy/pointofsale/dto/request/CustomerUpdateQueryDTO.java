package com.springbootacadamy.pointofsale.dto.request;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerUpdateQueryDTO {
        private String customerName;
        private String nic;
}
