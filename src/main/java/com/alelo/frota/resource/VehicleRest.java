package com.alelo.frota.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alelo.frota.entidades.Vehicle;
import com.alelo.frota.services.VehicleService;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleRest {
	
	@Autowired
	private VehicleService service;
	
	@GetMapping
	@RequestMapping(value = "paginator")
	public ResponseEntity<Page<Vehicle>> findPaginator(){
		Page page = service.findPaginator();
		return ResponseEntity.ok().body(page);
	}

}
