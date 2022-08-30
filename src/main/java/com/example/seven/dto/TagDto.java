package com.example.seven.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagDto implements Serializable {

    private Long id;
    private String name;

}
