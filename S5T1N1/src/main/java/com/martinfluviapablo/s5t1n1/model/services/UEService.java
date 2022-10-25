package com.martinfluviapablo.s5t1n1.model.services;

import com.martinfluviapablo.s5t1n1.model.domain.InfoUE;
import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UEService {

    private final InfoUE infoUE;

    @Autowired
    public UEService(InfoUE ueMembers) {
        this.infoUE = ueMembers;
    }

    public void setUeInfo(SucursalDto dto){
        if(infoUE.isMember(dto.getPaisSucursal())){
            dto.setTipusSucursal("UE");
        }else{
            dto.setTipusSucursal("Fora UE");
        }
    }

    public List<String> findAllUeMembers(){
        return infoUE.getPaisosSorted();
    }
}
