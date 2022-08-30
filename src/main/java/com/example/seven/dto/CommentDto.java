package com.example.seven.dto;

import com.example.seven.dto.item.ItemDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDto implements Serializable {

    private Long id;
    private String text;
//    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonFormat(pattern="yyyy-MM-dd mm:ss")
    private Date date;

    private UserDto user;
    private ItemDto item;

//    @JsonManagedReference
//    private User user;
//    @JsonManagedReference
//    private Item item;
}
