package com.springbootacadamy.pointofsale.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemSaveRequestDTO {

    private double balanceQty;
    private String ItemName;
    private String measuringUnits;
    private double supplierPrice;
    private double sellingPrice;
}
