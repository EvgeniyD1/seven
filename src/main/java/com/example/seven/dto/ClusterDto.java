package com.example.seven.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClusterDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private String type;
    private String fieldsType;
    private String fieldOne;
    private String fieldTwo;
    private String fieldThree;
//    @JsonManagedReference
//    private UserDto user;
}
