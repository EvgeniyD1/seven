package com.example.seven.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "clusters")
public class Cluster implements Serializable {

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

    @Column (name = "fields_type")
    private String fieldsType;

    @Column(name = "field_one")
    private String fieldOne;

    @Column(name = "field_two")
    private String fieldTwo;

    @Column(name = "field_three")
    private String fieldThree;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;
}
