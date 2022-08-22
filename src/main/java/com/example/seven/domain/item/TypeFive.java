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
import java.util.Date;

@Data
@Entity
@Table(name = "type_five")
/*object with additional date fields*/
public class TypeFive implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long id;

    @Column(name = "field_one")
    private Date fieldOne;

    @Column(name = "field_two")
    private Date fieldTwo;

    @Column(name = "field_three")
    private Date fieldThree;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Item item;
}
