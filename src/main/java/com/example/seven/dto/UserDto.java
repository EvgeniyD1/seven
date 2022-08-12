package com.example.seven.dto;

import com.example.seven.domain.Role;
import com.example.seven.domain.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String about;
    private Role role;
    private Status status;
    private Boolean notLock;
    private String imgUrl;
}
