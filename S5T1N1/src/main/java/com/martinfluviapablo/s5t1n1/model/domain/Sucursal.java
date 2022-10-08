package com.martinfluviapablo.s5t1n1.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
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
}
