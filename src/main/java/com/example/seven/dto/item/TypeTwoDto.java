package com.example.seven.dto.item;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeTwoDto implements Serializable {

    private Long id;
    private String fieldOne;
    private String fieldTwo;
    private String fieldThree;

}
