package com.alelo.frota.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alelo.frota.entidades.Vehicle;
import com.alelo.frota.services.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/vehicles")
@Api(value = "API REST")
public class VehicleRest {
	
	@Autowired
	private VehicleService service;
	
	
	@GetMapping
	@ApiOperation(value = "Retorna uma busca paginada")
	@RequestMapping(value = "paginator")
	public ResponseEntity<Page<Vehicle>> findPaginator(HttpServletRequest request){
		Page page = service.findPaginator(request.getParameter("page"), request.getParameter("limit"));
		return ResponseEntity.ok().body(page);
	}
	
	
	@GetMapping
	@ApiOperation(value = "Retorna um veículo buscando pela placa")
	@RequestMapping(value = "filterPlate")
	public ResponseEntity<Vehicle> findByPlate(HttpServletRequest request) {
		Vehicle ve = service.findByPlate(request.getParameter("filter"));
		return ResponseEntity.ok().body(ve);
	}
	
	
	@GetMapping
	@ApiOperation(value = "Retorna um veículo buscando pelo status")
	@RequestMapping(value = "filterStatus")
	public ResponseEntity<List<Vehicle>> findByStatus(HttpServletRequest request) {
		List<Vehicle> vehicles = service.findAllByStatus(Boolean.parseBoolean(request.getParameter("filter")));
		return ResponseEntity.ok().body(vehicles);
	}
	
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna um veículo buscando pelo id")
	public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
		Vehicle veiVehicle = service.findById(id);
		return ResponseEntity.ok().body(veiVehicle);
	}
	
	
	@PatchMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza as informações do veiculo")
	public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle){
		Vehicle v = service.update(id, vehicle);
		return ResponseEntity.ok().body(v);
	}
	
	
	@PostMapping
	@ApiOperation(value = "Insere as informações do veiculo")
	public ResponseEntity<Vehicle> insert(@RequestBody Vehicle vehicle){
		Vehicle v = service.insert(vehicle);
		return ResponseEntity.ok().body(v);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deleta veiculo")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
