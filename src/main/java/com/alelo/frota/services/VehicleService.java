package com.alelo.frota.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import com.alelo.frota.entidades.Vehicle;
import com.alelo.frota.exceptions.DatabaseException;
import com.alelo.frota.exceptions.VehicleNotFoundException;
import com.alelo.frota.repositories.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository repository;
	
	public List<Vehicle> findAll(){
		return repository.findAll();
	}
	
	public Vehicle findById(Long id) {
		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new VehicleNotFoundException(id));
	}
	
	public Vehicle insert(Vehicle vehicle) {
		return repository.save(vehicle);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException  e) {
			throw new VehicleNotFoundException(id);
		} catch (DataIntegrityViolationException  e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Vehicle update(Long id ,Vehicle vehicle) {
		try {
			Vehicle entity = findById(id);
			updateData(entity, vehicle);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new VehicleNotFoundException(id);
		}
	}
	
	public Vehicle findByPlate(String plate) {
		return repository.findByPlate(plate);	
	}
	
	public List<Vehicle> findAllByStatus(Boolean status){
		return repository.findByStatus(status);
	}
	
	
	public Page<Vehicle> findPaginator(String page, String limit){
		return repository.findAll(new QPageRequest(Integer.parseInt(page), Integer.parseInt(limit)));
	}
	
	private void updateData(Vehicle entity, Vehicle obj) {
		entity.setColor(obj.getColor());
		entity.setManufacturer(obj.getManufacturer());
		entity.setModel(obj.getModel());
		entity.setPlate(obj.getPlate());
		entity.setStatus(obj.getStatus());
	}

}
