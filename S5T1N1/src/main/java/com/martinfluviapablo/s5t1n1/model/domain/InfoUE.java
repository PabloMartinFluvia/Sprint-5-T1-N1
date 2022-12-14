package com.martinfluviapablo.s5t1n1.model.domain;

import org.springframework.stereotype.Component;

import java.text.Collator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InfoUE {

    private final Set<String> paisos;

    private final Collator collator;

    public InfoUE(){
        paisos = new HashSet<>(Set.of(
                "Alemanya","Àustria","Bèlgica","Bulgària","Croàcia","Dinamarca","Eslovàquia",
                "Eslovènia","Espanya","Estònia","Finlàndia","França","Grècia","Hongria",
                "Irlanda","Itàlia","Letònia","Lituània","Luxemburg","República de Malta",
                "Països Baixos","Polònia","Portugal","República Txeca","Romania","Suècia","Xipre")
        );
        //to sort, compare, equals, etc... Strings ignoring accents and uppercase -> Á = a
        collator = Collator.getInstance();
        collator.setStrength(Collator.PRIMARY);
    }

    public boolean isMember(String pais){
        return  paisos.stream().anyMatch(s ->collator.equals(s,pais));
    }

    public List<String> getPaisosSorted() {
        return paisos.stream().sorted(collator).toList();
    }
}
