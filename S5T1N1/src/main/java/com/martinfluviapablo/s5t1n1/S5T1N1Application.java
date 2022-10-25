package com.martinfluviapablo.s5t1n1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
TODO: informar Joan
 	resource path in plural
	database schema "sucursals" es crea si no existeix

      En buscar.html:
      a) es demana que es mapegi endpoint .../sucursals/getOne/{id} -> path variable
      b) per a que el enllaç sigui dinàmic:
            ex: @{..../{x}(x=${nomVar})
      c) la variable ha de ser proporcionada pel model enviat a la vista
      d) He buscat de mil maneres com passar, en un path variable, un valor proporcionat per el usuari.
      e) No he trobat la solució, a tot arreu on mirava el tema de path variable amb thymeleaf són casos
        de (b+c). L'únic que he tret en clar és:
        There is no html/thymeleaf way (without javascript) to get a form
        field into the path part of a URL
      f) I tot el que he trobat, per a gestionar els inputs, és:
            g) Guardar la data en el model enviat a la vista i fer data-binding en el endpoint
                ex: típic cas en un formulari "nova entitat"
            h) Passar la data com a paràmetre en el path, i obtenir la data amb @RequestParam
                ex: típic cas de box de búsqueda
       -> CONCLUSIÓ: Opto per l'opció (h) + creo un endpoint amb @RequestParam  en NavigationController
            /buscar/oneId
            + redirecciono a sucursals/geOne/valorParàmetre,
            així el endpoint amb path variable està actiu

 */

/*
Validacions id:
    Si es fan les requests via web no hi ha possibilitat d'error, però s'implementen per
    a actuar de tallafocs en cas d'errors o requests errònies realitzades "des de fora".
    No es fan en l'àmbit de dto, ja que la restricció és diferent segons un post un put.
    Tampoc es fan amb anotacions en arguments dels mètodes, perquè les excepcions que es llancen
    no em convencen.
    ValidationException or ConstraintValidationException (simple 400)
 */

@SpringBootApplication
public class S5T1N1Application {
	public static void main(String[] args) {
		SpringApplication.run(S5T1N1Application.class, args);
	}
}
