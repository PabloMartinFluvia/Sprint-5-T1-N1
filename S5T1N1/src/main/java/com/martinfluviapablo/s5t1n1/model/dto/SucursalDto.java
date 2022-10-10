package com.martinfluviapablo.s5t1n1.model.dto;

import com.martinfluviapablo.s5t1n1.model.domain.Sucursal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@ToString
public class SucursalDto {

    //TODO: test if this class needs to be a bean for be correctly validated

    //TODO: check if declared constuctors are problematic or if no args constructor is needed

    //TODO: define constraints for de id (in function of the end-points)
    private Integer pk_SucursalID;

    @NotBlank
    private String nomSucursal;

    @NotBlank
    private String paisSucursal;

    @Nullable
    private String tipusSucursal; //"UE" OR "Fora UE"; Not in Sucursal

    public SucursalDto(@Nullable Integer pk_SucursalID, String nomSucursal, String paisSucursal) {
        this.pk_SucursalID = pk_SucursalID;
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
    }

    public SucursalDto(Sucursal sucursal, String sucursalType){
        this.pk_SucursalID = sucursal.getPk_SucursalID();
        this.nomSucursal = sucursal.getNomSucursal();
        this.paisSucursal = sucursal.getPaisSucursal();
        this.tipusSucursal = sucursalType;
    }

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
