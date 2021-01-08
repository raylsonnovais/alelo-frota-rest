package com.alelo.frota.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alelo.frota.entidades.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Vehicle findByPlate(String plate);
	
	List<Vehicle> findByStatus(Boolean status);
	
	Page<Vehicle> findAll(Pageable pageable);
}
