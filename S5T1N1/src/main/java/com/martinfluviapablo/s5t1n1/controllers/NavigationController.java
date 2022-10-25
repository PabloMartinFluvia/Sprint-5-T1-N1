package com.martinfluviapablo.s5t1n1.controllers;

import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import com.martinfluviapablo.s5t1n1.model.services.SucursalCRUDService;

import com.martinfluviapablo.s5t1n1.model.services.UEService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

    private SucursalCRUDService sucursalService;

    private UEService ueService;

    @Autowired
    public NavigationController(SucursalCRUDService sucursalService, UEService ueService) {
        this.sucursalService = sucursalService;
        this.ueService = ueService;
    }

    /**
     * For redirect from id passed as parameter to id passed as path variable.
     * -> See buscar.html
     * @param id
     * @return
     */
    @GetMapping("/buscar/oneId")
    public String searchOne(@RequestParam Integer id){ //query param validated when submitted in form (buscar.html)
        return "redirect:/sucursals/getOne/"+id;
    }

    @GetMapping("/registrar")
    public ModelAndView showForm(){
        ModelAndView mav = new ModelAndView("formulari");
        mav.addObject("sucursal", new SucursalDto());
        mav.addObject("target","new");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView showForm(@PathVariable Integer id, Model model){
        ModelAndView mav = new ModelAndView("formulari");
        mav.addObject("sucursal", sucursalService.findOne(id));
        mav.addObject("target","update");
        return mav;
    }

    @GetMapping("/ueMemebrs")
    public ModelAndView showUeMembers(){
        ModelAndView mav = new ModelAndView("uemembers");
        mav.addObject("paisos",ueService.findAllUeMembers());
        return mav;
    }
}
