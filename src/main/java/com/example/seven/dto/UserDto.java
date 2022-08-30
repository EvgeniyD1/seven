package com.example.seven.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserDto implements Serializable {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String about;
    private String role;
    private String status;
    private Boolean notLock;
    private String imgUrl;

}
