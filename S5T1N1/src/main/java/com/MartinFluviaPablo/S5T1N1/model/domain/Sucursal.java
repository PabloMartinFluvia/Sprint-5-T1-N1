package com.MartinFluviaPablo.S5T1N1.model.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sucursal {

    @Id
    private Integer pk_SucursalID;

    private String nomSucursal;

    private String paisSucursal;
}
