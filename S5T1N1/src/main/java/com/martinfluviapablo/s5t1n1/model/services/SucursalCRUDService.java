package com.martinfluviapablo.s5t1n1.model.services;

import com.martinfluviapablo.s5t1n1.model.domain.Sucursal;
import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import com.martinfluviapablo.s5t1n1.model.repository.SucursalRepository;

import com.martinfluviapablo.s5t1n1.model.services.exceptions.DataConflictException;
import com.martinfluviapablo.s5t1n1.model.services.exceptions.SucursalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalCRUDService { //suggested: here conversion Sucursal <-> SucursalDto

    private SucursalRepository repository;

    private ModelMapper modelMapper;

    @Autowired
    public SucursalCRUDService(SucursalRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public SucursalDto addNew(SucursalDto dto){
        Sucursal sucursal = toSucursal(dto);
        checkDataConflict(sucursal);
        return toDto(repository.save(sucursal));
    }

    /**
     * It's not allowed to have many sucursals with same name and country.
     * If any client tryes to do it a DataConflictException is thrown.
     * @param sucursal
     */
    private void checkDataConflict(Sucursal sucursal){
        String nom = sucursal.getNomSucursal();
        String pais = sucursal.getPaisSucursal();
        if(repository.findByNomSucursalAndPaisSucursal(nom,pais).isPresent()){
            throw new DataConflictException("Ja existeix un registre amb nom "+nom+" en el país "+pais);
        }
    }

    /**
     * If the id of the entity to update is not found throws SucursalNotFoundException
     * @param dto
     * @return
     */
    public SucursalDto replaceOne(SucursalDto dto){
        Integer id = dto.getPk_SucursalID();
        Sucursal updatedSucursal = repository.findById(id)
                .map(sucursal ->  toSucursal(dto))
                .orElseThrow(() -> new SucursalNotFoundException(
                        "No s'ha pogut actualitzar. No existeix cap sucursal amb id: "+id));
        checkDataConflict(updatedSucursal);
        return toDto(repository.save(updatedSucursal));
    }

    /**
     * If the id of the entity to update is not found throws SucursalNotFoundException
     * @param id
     * @return
     */
    public SucursalDto findOne(Integer id){
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new SucursalNotFoundException("No s'ha trovat cap sucursal amb id: "+id));
    }

    public List<SucursalDto> findAll(){
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }

    /**
     * If id not found it's ok (won't exist an entity with such id)
     * @param id
     */
    public void deleteOne(Integer id){
        repository.deleteById(id);
    }

    //TODO test if modelMapper works properly
    private SucursalDto toDto (Sucursal sucursal){
        return modelMapper.map(sucursal, SucursalDto.class);
    }

    private Sucursal toSucursal(SucursalDto dto){
        return modelMapper.map(dto, Sucursal.class);
    }
}
