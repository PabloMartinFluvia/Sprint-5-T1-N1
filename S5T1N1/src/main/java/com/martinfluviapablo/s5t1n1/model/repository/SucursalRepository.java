package com.martinfluviapablo.s5t1n1.model.repository;

import com.martinfluviapablo.s5t1n1.model.domain.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal,Integer> {
}
