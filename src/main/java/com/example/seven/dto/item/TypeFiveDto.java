package com.example.seven.dto.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TypeFiveDto implements Serializable {

    private Long id;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date fieldOne;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date fieldTwo;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date fieldThree;

}
