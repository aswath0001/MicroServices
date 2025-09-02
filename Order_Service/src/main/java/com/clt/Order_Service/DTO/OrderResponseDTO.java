package com.clt.Order_Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long OrderId;
    private Long ProductId;
    private int Quantity;
    private Double TotalPrice;

    private String ProductName;
    private Double ProductPrice;
}
