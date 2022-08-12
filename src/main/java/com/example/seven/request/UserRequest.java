package com.example.seven.request;

import lombok.Data;

@Data
public class UserRequest {

    private Long id;
    private String username;
    private String email;
    private String about;
    private String role;
    private String status;
    private Boolean notLock;
    private String imgUrl;
}
