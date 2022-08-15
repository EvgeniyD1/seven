package com.example.seven.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "item_collections")
public class ItemCollection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imgUrl;

    @Column
    private String type;

    @Column(name = "field_one")
    private String fieldOne;

    @Column(name = "field_two")
    private String fieldTwo;

    @Column(name = "field_three")
    private String fieldThree;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
}
