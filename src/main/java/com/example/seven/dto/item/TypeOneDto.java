package com.example.seven.dto.item;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeOneDto implements Serializable {

    private Long id;
    private Integer fieldOne;
    private Integer fieldTwo;
    private Integer fieldThree;

}
