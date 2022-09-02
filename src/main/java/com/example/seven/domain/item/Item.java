package com.example.seven.domain.item;

import com.example.seven.domain.Cluster;
import com.example.seven.domain.FieldTypes;
import com.example.seven.domain.Tag;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
                @NamedAttributeNode("tags"),
                @NamedAttributeNode(value = "cluster", subgraph = "cluster_sub"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "cluster_sub",
                        attributeNodes = {
                                @NamedAttributeNode("user")
                        }
                )
        }
)
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonManagedReference
    @ManyToMany(mappedBy = "items", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "fields_type")
    @Enumerated(EnumType.STRING)
    private FieldTypes fieldsType;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    /*integer fields*/
    private TypeOne typeOne;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    /*text fields*/
    private TypeTwo typeTwo;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    /*multiline text fields*/
    private TypeThree typeThree;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    /*boolean fields*/
    private TypeFour typeFour;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    /*date fields*/
    private TypeFive typeFive;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "cluster_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cluster cluster;

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getItems().add(this);
    }
}
