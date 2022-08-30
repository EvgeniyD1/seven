package com.example.seven.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "type_five")
/*object with additional date fields*/
public class TypeFive implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long id;

    @Column(name = "field_one")
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date fieldOne;

    @Column(name = "field_two")
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date fieldTwo;

    @Column(name = "field_three")
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date fieldThree;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Item item;
}
