package br.com.btg.commerce.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
public class Item {

    private String name;

    private Integer amount;

    private BigDecimal price;

}