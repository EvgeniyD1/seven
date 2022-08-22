package com.example.seven.dto;

import com.example.seven.domain.Role;
import com.example.seven.domain.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserDto implements Serializable {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String about;
    private Role role;
    private Status status;
    private Boolean notLock;
    private String imgUrl;
//    @JsonBackReference
//    private List<ClusterDto> clusters;
}
