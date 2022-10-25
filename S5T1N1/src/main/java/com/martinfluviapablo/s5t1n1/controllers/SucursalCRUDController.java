package com.martinfluviapablo.s5t1n1.controllers;

import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import com.martinfluviapablo.s5t1n1.model.services.SucursalCRUDService;

import com.martinfluviapablo.s5t1n1.model.services.exceptions.IdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
//TODO: inform Joan path in plural
@RequestMapping("/sucursals")// resources should be in path with plural names
public class SucursalCRUDController {//suggested: work only with SucursalDto

    private SucursalCRUDService sucursalService;

    @Autowired
    public SucursalCRUDController(SucursalCRUDService service) {
        this.sucursalService = service;
    }

    /*
    Validacions id,
    Si es fan les requests via web no hi ha possibilitat d'error, però s'implementen per
    a actuar de tallafocs en cas d'errors o requests errones realitzades des de fora.
    No es fan a nivell de dto, ja que la restricció és diferent segons un post un put.
    Tampoc es fan amb anotacions en arguments dels mètodes, ja que les excepcions que es llancen
    no em convencen.
    ValidationException or ConstraintValidationException (simple 400)
     */

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("sucursal") SucursalDto dto,
                         BindingResult bindingResult, Model model){
        checkIdNull(dto.getPk_SucursalID());
        model.addAttribute("target","new");
        if(!bindingResult.hasErrors()){
            sucursalService.addNew(dto);
            model.addAttribute("success","Sucursal guardada amb èxit!");
            model.addAttribute("sucursal",new SucursalDto());
        }
        return "formulari";
    }

    @PutMapping("/update")
    public String replace (@Valid @ModelAttribute("sucursal") SucursalDto dto,
                           BindingResult bindingResult, Model model) {
        checkIdNotNull(dto.getPk_SucursalID());
        model.addAttribute("target","update");
        if (!bindingResult.hasErrors()) {
            model.addAttribute("sucursal",sucursalService.replaceOne(dto));
            model.addAttribute("success","Sucursal actualitzada amb èxit!");
        }
        return "formulari";
    }

    @GetMapping("/getOne/{id}")
    public ModelAndView getOne(@PathVariable Integer id){
        checkIdNotNull(id);
        SucursalDto sucursal = sucursalService.findOne(id);
        ModelAndView mav = new ModelAndView("llista");
        mav.addObject("resultat",sucursal);
        return mav;
    }

    @GetMapping("/getAll")
    public ModelAndView all(){
        List<SucursalDto> sucursals = sucursalService.findAll();
        ModelAndView mav = new ModelAndView("llista");
        mav.addObject("resultat",sucursals);
        mav.addObject("total",sucursals.size());
        return mav;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOne(@PathVariable Integer id, Model model){
        checkIdNotNull(id);
        sucursalService.deleteOne(id);
        return "redirect:/sucursals/getAll";
    }

    private void checkIdNotNull(Integer id){
        if(id == null){
            throw new IdException("ERROR: No s'ha proporcionat un identificador de la sucursal.");
        }
    }

    private void checkIdNull(Integer id){
        if(!((id == null) || id.equals(0))){
            throw new IdException("ERROR: S'ha intentat crear una sucursal amb un id ja assignat.");
        }
    }

}
