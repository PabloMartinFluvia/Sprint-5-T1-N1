package com.martinfluviapablo.s5t1n1.model.services;

import com.martinfluviapablo.s5t1n1.model.domain.InfoUE;
import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UEService {

    private final InfoUE ueMembers;

    @Autowired
    public UEService(InfoUE ueMembers) {
        this.ueMembers = ueMembers;
    }

    public void setComunitaryInfo(SucursalDto dto){
        if(ueMembers.isMember(dto.getPaisSucursal())){
            dto.setTipusSucursal("UE");
        }else{
            dto.setTipusSucursal("Fora UE");
        }
    }

    public void setComunitaryInfo(List<SucursalDto> dtos){
        dtos.forEach(this::setComunitaryInfo);
    }
}
