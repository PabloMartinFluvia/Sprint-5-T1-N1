package com.martinfluviapablo.s5t1n1.model.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

//@Data -> could cause performance problems
@Getter
@Setter
@ToString
@RequiredArgsConstructor //TODO: check @RequiredArgsConstructor is enough
@Entity(name = "sucursals")
public class Sucursal { // suggested: only used in repository layer

    @Id
    @GeneratedValue
    @Column(name = "sucursal_id")
    private Integer pk_SucursalID;

    @Column(nullable = false)
    private String nomSucursal;

    @Column(nullable = false)
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
