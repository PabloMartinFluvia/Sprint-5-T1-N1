package com.martinfluviapablo.s5t1n1.model.services;

import com.martinfluviapablo.s5t1n1.model.domain.Sucursal;
import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import com.martinfluviapablo.s5t1n1.model.repository.SucursalRepository;

import com.martinfluviapablo.s5t1n1.model.services.exceptions.DataConflictException;
import com.martinfluviapablo.s5t1n1.model.services.exceptions.SucursalNotFoundException;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SucursalCRUDService { //suggested: here conversion Sucursal <-> SucursalDto

    private final SucursalRepository repository;

    private final UEService ueService;

    private final ModelMapper modelMapper;

    @Autowired
    public SucursalCRUDService(SucursalRepository repository, UEService ueService, ModelMapper modelMapper) {
        this.repository = repository;
        this.ueService = ueService;
        this.modelMapper = modelMapper;
    }

    public SucursalDto addNew(SucursalDto dto){
        Sucursal sucursal = toSucursal(dto);
        checkDataConflict(sucursal);
        return toDto(repository.save(sucursal));
    }

    /*
     * It's not allowed to have many sucursals with same name and country and distinct id
     * If any client tries to do it a DataConflictException is thrown.
     *
     */
    private void checkDataConflict(Sucursal newSucursal){
        //if found old sucursal with same name and country && id distint to new Sucursal -> conflict exception
        String nom = newSucursal.getNomSucursal();
        String pais = newSucursal.getPaisSucursal();
        Optional<Sucursal> oldSucursal = repository.findByNomSucursalAndPaisSucursal(nom,pais);
        if(oldSucursal.isPresent()
                && !Objects.equals(oldSucursal.get().getPk_SucursalID(), newSucursal.getPk_SucursalID()))
            throw new DataConflictException("ERROR: Ja existeix un registre amb nom " + nom + " en el país " + pais);
    }

    // If the id of the entity to update is not found throws SucursalNotFoundException
    public SucursalDto replaceOne(SucursalDto dto){
        Integer id = dto.getPk_SucursalID();
        Sucursal updatedSucursal = repository.findById(id)
                .map(sucursal ->  toSucursal(dto))
                .orElseThrow(() -> new SucursalNotFoundException(
                        "ERROR: No s'ha pogut actualitzar. No existeix cap sucursal amb id: "+id));
        checkDataConflict(updatedSucursal);
        return toDto(repository.save(updatedSucursal));
    }

    // If the id of the entity to update is not found throws SucursalNotFoundException
    public SucursalDto findOne(Integer id){
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new SucursalNotFoundException("ERROR: No s'ha trobat cap sucursal amb id: "+id));
    }

    public List<SucursalDto> findAll(){
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }


     // If id not found it's ok (won't exist an entity with such id)
    public void deleteOne(Integer id){
        repository.deleteById(id);
    }

    private SucursalDto toDto (Sucursal sucursal){
        SucursalDto dto = modelMapper.map(sucursal, SucursalDto.class);
        ueService.setUeInfo(dto);
        return dto;
    }

    private Sucursal toSucursal(SucursalDto dto){
        return modelMapper.map(dto, Sucursal.class);
    }
}
