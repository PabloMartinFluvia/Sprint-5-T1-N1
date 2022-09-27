package com.MartinFluviaPablo.S5T1N1.model.repository;

import com.MartinFluviaPablo.S5T1N1.model.domain.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal,Integer> {
}
