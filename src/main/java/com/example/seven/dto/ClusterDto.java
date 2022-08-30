package com.example.seven.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClusterDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private String topic;
    private String fieldsType;
    private String fieldOne;
    private String fieldTwo;
    private String fieldThree;
    private UserDto user;
}
