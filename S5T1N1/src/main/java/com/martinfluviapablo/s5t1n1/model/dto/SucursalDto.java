package com.martinfluviapablo.s5t1n1.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@ToString
public class SucursalDto {

    //TODO: define constraints for de id and tipus sucursal (in function of the end-points)

    private Integer pk_SucursalID;

    @NotBlank(message = "El nom no pot estar en blanc")
    @Length(min = 3, max = 65, message = "El nom ha de tenir entre 3 i 65 caràcters")
    private String nomSucursal;

    @NotBlank(message = "El país no pot estar en blanc.")
    @Length(min = 2, max = 45, message = "El país ha de tenir entre 4 i 45 caràcters")
    private String paisSucursal;

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
