package com.martinfluviapablo.s5t1n1.model.services;

import com.martinfluviapablo.s5t1n1.model.domain.InfoUE;
import com.martinfluviapablo.s5t1n1.model.domain.Sucursal;
import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import com.martinfluviapablo.s5t1n1.model.repository.SucursalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public class SucursalCRUDService { //suggested: here conversion Sucursal <-> SucursalDto

    private ApplicationContext context;
    private SucursalRepository repository;

    @Autowired
    public SucursalCRUDService(SucursalRepository repository, ApplicationContext context) {
        this.context = context;
        this.repository = repository;
    }

    //TODO: check id is null before calling this method
    public SucursalDto create (SucursalDto dto){
        Sucursal sucursal = toSucursal(dto);
        checkDataConflict(sucursal);
        return toDto(repository.save(sucursal));
    }

    /**
     * It's not allowed to have many sucursals with same name and country.
     * If any client tryes to do it a DataConflictiveException is thrown.
     * @param sucursal
     */
    private void checkDataConflict(Sucursal sucursal){
        String nom = sucursal.getNomSucursal();
        String pais = sucursal.getPaisSucursal();
        if(repository.findByNomSucursalAndPaisSucursal(nom,pais).isPresent()){
            throw new DataConflictiveException("Ja existeix un registre amb nom: "+nom+" en el pais: "+pais);
        }
    }

    //TODO: check id > 0 before calling this method
    /**
     * If the id of the entity to update is not found throws SucursalNotFoundException
     * @param dto
     * @return
     */
    public SucursalDto replace(SucursalDto dto){
        Integer id = dto.getPk_SucursalID();
        return  repository.findById(id)
                .map(oldUpdatable -> {
                    oldUpdatable.setNomSucursal(dto.getNomSucursal());
                    oldUpdatable.setPaisSucursal(dto.getPaisSucursal());
                    checkDataConflict(oldUpdatable);
                    return toDto(repository.save(oldUpdatable));
                }).orElseThrow(() -> new SucursalNotFoundException(
                        "No es pot actualitzar. No existeix cap sucursal amb id: "+id));
    }

    //TODO: check method it's not called with null id
    public SucursalDto one (Integer id){
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new SucursalNotFoundException("No s'ha trovat cap sucursal amb id: "+id));
    }

    public List<SucursalDto> all(){
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }

    //TODO: check method it's not called with null id
    /**
     * If id not found it's ok (won't exist an entity with such id)
     * @param id
     */
    public void delete(Integer id){
        repository.deleteById(id);
    }

    //TODO check if in both private methods BeanUtils.copyProperties work
    private SucursalDto toDto (Sucursal sucursal){
        SucursalDto dto = context.getBean("dto",SucursalDto.class); //scope protorype
        BeanUtils.copyProperties(sucursal,dto);
        return dto;
    }

    private Sucursal toSucursal(SucursalDto dto){
        Sucursal sucursal = context.getBean("sucursal",Sucursal.class); //scope protorype
        BeanUtils.copyProperties(dto,sucursal);
        return sucursal;
    }
}
