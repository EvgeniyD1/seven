package com.example.seven.dto.item;

import com.example.seven.dto.ClusterDto;
import com.example.seven.dto.TagDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ItemDto implements Serializable {

    private Long id;
    private String name;
    private Set<TagDto> tags;
    private String imgUrl;
    private String fieldsType;

    /*integer fields*/
    private TypeOneDto typeOne;
    /*text fields*/
    private TypeTwoDto typeTwo;
    /*multiline text fields*/
    private TypeThreeDto typeThree;
    /*boolean fields*/
    private TypeFourDto typeFour;
    /*date fields*/
    private TypeFiveDto typeFive;

    private ClusterDto cluster;
}
