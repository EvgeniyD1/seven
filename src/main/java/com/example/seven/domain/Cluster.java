package com.example.seven.domain;

import com.example.seven.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private Topics topic;

    @Column (name = "fields_type")
    @Enumerated(EnumType.STRING)
    private FieldTypes fieldsType;

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

    @JsonBackReference
    @OneToMany(mappedBy = "cluster", fetch = FetchType.LAZY)
    private List<Item> items;
}
