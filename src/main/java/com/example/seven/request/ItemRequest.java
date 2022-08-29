package com.example.seven.request;

import lombok.Data;

import java.util.Set;

@Data
public class ItemRequest {

    private Long id;
    private String name;
    private Set<String> tags;
    private String fieldsType;
    private String fieldOne;
    private String fieldTwo;
    private String fieldThree;
}
