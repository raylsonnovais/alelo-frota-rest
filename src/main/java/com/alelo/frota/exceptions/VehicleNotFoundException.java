package com.alelo.frota.exceptions;

public class VehicleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(Object id) {
		super("Vehicle not found. Id " + id);
	}

}
