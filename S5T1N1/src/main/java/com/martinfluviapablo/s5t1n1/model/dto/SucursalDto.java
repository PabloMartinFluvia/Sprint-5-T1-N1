package com.martinfluviapablo.s5t1n1.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@ToString
public class SucursalDto {

    //TODO: test if this class needs to be a bean for be correctly validated

    //TODO: define constraints for de id (in function of the end-points)
    private Integer pk_SucursalID;

    @NotBlank(message = "El nom ha de tenir almenys 1 caràcter.")
    private String nomSucursal;

    @NotBlank(message = "El país ha de tenir almenys 1 caràcter.")
    private String paisSucursal;

    @Nullable
    private String tipusSucursal; //"UE" OR "Fora UE"; Not in Sucursal

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SucursalDto that = (SucursalDto) o;
        return Objects.equals(pk_SucursalID, that.pk_SucursalID) && nomSucursal.equals(that.nomSucursal) && paisSucursal.equals(that.paisSucursal) && Objects.equals(tipusSucursal, that.tipusSucursal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk_SucursalID, nomSucursal, paisSucursal, tipusSucursal);
    }
}
