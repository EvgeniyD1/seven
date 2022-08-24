package com.example.seven.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDto implements Serializable {

    private Long id;
    private String text;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date date;

//    @JsonManagedReference
//    private User user;
//    @JsonManagedReference
//    private Item item;
}
