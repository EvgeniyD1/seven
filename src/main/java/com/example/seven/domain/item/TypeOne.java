package com.example.seven.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "type_one")
/*object with additional integer fields*/
public class TypeOne implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long id;

    @Column(name = "field_one")
    private Integer fieldOne;

    @Column(name = "field_two")
    private Integer fieldTwo;

    @Column(name = "field_three")
    private Integer fieldThree;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Item item;
}
