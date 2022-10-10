package com.martinfluviapablo.s5t1n1.controllers;

import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import com.martinfluviapablo.s5t1n1.model.services.SucursalCRUDService;
import com.martinfluviapablo.s5t1n1.model.services.UEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
//TODO: inform Joan path in plural
@RequestMapping("/sucursals")// resources should be in path with plural names
public class SucursalCRUDController {//suggested: work only with SucursalDto

    private SucursalCRUDService sucursalService;

    private UEService ueService;

    @Autowired
    public SucursalCRUDController(SucursalCRUDService service, UEService ueService) {
        this.sucursalService = service;
        this.ueService = ueService;
    }

    @PostMapping("/add")
    public String create(@Valid SucursalDto dto, BindingResult bindingResult){
        /*
        TODO:
            arguments and anotations?
            how check data valid?
            what is returned should be added tot Model?
                if affirmative -> should be added UE info?
            what I should return ?
         */
        if(bindingResult.hasErrors()){

        }
        sucursalService.create(dto);
        return null;
    }

    @PutMapping("/update")
    public String replace (@Valid SucursalDto dto, BindingResult bindingResult) {
        /*
        TODO:
            arguments and anotations?
            how check data valid?
                maybe save id + set id null + redirect to "/update/{id}" (where constraint is @Null)
            what is returned should be added tot Model?
                if affirmative -> should be added UE info?
            what I should return ?
         */
        if (bindingResult.hasErrors()) {

        }
        sucursalService.replace(dto); //if its not valid
        return null;
    }

    @GetMapping("/getOne/{id}")
    public String one(@PathVariable Integer id, Model model){
        /*
        TODO:
            arguments already ok?
            what I should return ?
         */
        SucursalDto sucursal = ueService.setComunitaryInfo(sucursalService.one(id));
        model.addAttribute("sucursal",sucursal);
        return null;
    }

    @GetMapping("/getAll")
    public String all(Model model){
        List<SucursalDto> sucursals = ueService.setComunitaryInfo(sucursalService.all());
        model.addAttribute("sucursals",sucursals);
        return null;
    }
}
