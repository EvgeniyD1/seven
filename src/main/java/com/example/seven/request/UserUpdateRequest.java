package com.example.seven.request;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String username;
    private String email;
    private String about;
    private Boolean notLock;
    private String role;
}
