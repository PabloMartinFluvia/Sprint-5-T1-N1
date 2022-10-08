package com.martinfluviapablo.s5t1n1.model.domain;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("NonAsciiCharacters")
@Component
public class InfoUE {

    private final Set<String> paisos; //Not in JSON nor Sucursal

    public InfoUE(){
        paisos = new HashSet<>(Set.of(
                "Alemanya","Àustria","Bèlgica","Bulgària","Croàcia","Dinamarca","Eslovàquia",
                "Eslovènia","Espanya","Estònia","Finlàndia","França","Grècia","Hongria",
                "Irlanda","Itàlia","Letònia","Lituània","Luxemburg","República de Malta",
                "Països Baixos","Polònia","Portugal","República Txeca","Romania","Suècia","Xipre")
        );
    }

    public boolean isMember(String pais){
        return  paisos.stream().anyMatch(s -> s.equalsIgnoreCase(pais));
    }
}
