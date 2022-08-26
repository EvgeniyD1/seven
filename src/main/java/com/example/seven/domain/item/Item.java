package com.example.seven.domain.item;

import com.example.seven.domain.Cluster;
import com.example.seven.domain.FieldTypes;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NamedEntityGraph(
        name = "item",
        attributeNodes = {
                @NamedAttributeNode("typeOne"),
                @NamedAttributeNode("typeTwo"),
                @NamedAttributeNode("typeThree"),
                @NamedAttributeNode("typeFour"),
                @NamedAttributeNode("typeFive"),
                @NamedAttributeNode("cluster"),
        }
)
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String tag;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "fields_type")
    @Enumerated(EnumType.STRING)
    private FieldTypes fieldsType;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    /*integer fields*/
    private TypeOne typeOne;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    /*text fields*/
    private TypeTwo typeTwo;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    /*multiline text fields*/
    private TypeThree typeThree;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    /*boolean fields*/
    private TypeFour typeFour;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    /*date fields*/
    private TypeFive typeFive;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "cluster_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cluster cluster;

}
