package com.example.seven.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "type_four")
/*object with additional boolean fields*/
public class TypeFour implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long id;

    @Column(name = "field_one")
    private Boolean fieldOne;

    @Column(name = "field_two")
    private Boolean fieldTwo;

    @Column(name = "field_three")
    private Boolean fieldThree;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Item item;
}
