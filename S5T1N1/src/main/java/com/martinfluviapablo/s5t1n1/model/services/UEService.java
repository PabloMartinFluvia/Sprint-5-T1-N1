package com.martinfluviapablo.s5t1n1.model.services;

import com.martinfluviapablo.s5t1n1.model.domain.InfoUE;
import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UEService {

    private InfoUE ueMembers;

    @Autowired
    public UEService(InfoUE ueMembers) {
        this.ueMembers = ueMembers;
    }

    public SucursalDto setComunitaryInfo(SucursalDto dto){
        if(ueMembers.isMember(dto.getPaisSucursal())){
            dto.setTipusSucursal("UE");
        }else{
            dto.setTipusSucursal("Fora UE");
        }
        return dto;
    }

    public List<SucursalDto> setComunitaryInfo(List<SucursalDto> dtos){
        //TODO: check if the previous function could act as a consumer
        dtos.forEach(this::setComunitaryInfo);
        return dtos;
    }
}
