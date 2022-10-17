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
    public String create(@Valid @ModelAttribute SucursalDto dto, BindingResult bindingResult, Model model){
       //TODO: define constraints for a valid SucursalDto on create
        //TODO: method arguments OK?
        if(bindingResult.hasErrors()){
            //TODO: it's necessary to bind to model the error?
            //TODO: what I should return?
            return null;
        }
        //TODO: dto must have NO id
        dto = sucursalService.addNew(dto);
        ueService.setComunitaryInfo(dto);
        //TODO: it's necessary bind to the model the dto?
        //TODO: what I should return?
        return null;
    }

    @PutMapping("/update")
    public String replace (@Valid @ModelAttribute SucursalDto dto, BindingResult bindingResult, Model model) {
        //TODO: define constraints for a valid SucursalDto on update
        //TODO: method arguments OK?
        if (bindingResult.hasErrors()) {
            //TODO: it's necessary to bind to model the error?
            //TODO: what I should return?
            return null;
        }
        //TODO assert id not null to avoid exception
        sucursalService.replaceOne(dto);
        //TODO: what I should return?
        return null;
    }

    @GetMapping("/getOne/{id}")
    public String one(@PathVariable Integer id, Model model){
        //TODO assert id not null to avoid exception
        SucursalDto sucursal = sucursalService.findOne(id);
        ueService.setComunitaryInfo(sucursal);
        model.addAttribute("sucursal",sucursal);
        //TODO: what I should return?
        return null;
    }

    @GetMapping("/getAll")
    public String all(Model model){
        List<SucursalDto> sucursals = sucursalService.findAll();
        ueService.setComunitaryInfo(sucursals);
        model.addAttribute("sucursals",sucursals);
        //TODO: what I should return?
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOne(@PathVariable Integer id){
        //TODO assert id not null to avoid exception
        sucursalService.deleteOne(id);
        //TODO: what I should return?
        return null;
    }
}
