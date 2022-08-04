package com.example.seven.request;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String username;
    private String password;
    private String email;
}
