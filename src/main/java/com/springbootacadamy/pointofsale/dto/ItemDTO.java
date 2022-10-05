package com.springbootacadamy.pointofsale.dto;


import com.springbootacadamy.pointofsale.entity.enums.MeasuringUnits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {

    private int itemId;
    private String ItemName;
    private MeasuringUnits measuringUnits;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeState;
}
