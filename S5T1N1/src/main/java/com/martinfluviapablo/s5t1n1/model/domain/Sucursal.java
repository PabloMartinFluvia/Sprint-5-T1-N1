package com.martinfluviapablo.s5t1n1.model.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

//@Data -> could cause performance problems
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sucursals")
public class Sucursal { // suggested: only used in repository layer

    @Id
    @GeneratedValue
    @Column(name = "sucursal_id")
    private Integer pk_SucursalID;

    @Column(nullable = false, length = 65)
    private String nomSucursal;

    @Column(nullable = false, length = 45)
    private String paisSucursal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sucursal sucursal = (Sucursal) o;
        return pk_SucursalID != null && Objects.equals(pk_SucursalID, sucursal.pk_SucursalID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
