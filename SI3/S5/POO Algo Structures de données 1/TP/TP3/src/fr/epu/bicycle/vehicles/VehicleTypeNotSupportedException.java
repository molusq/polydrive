package fr.epu.bicycle.vehicles;

public class VehicleTypeNotSupportedException extends IllegalArgumentException {
	public VehicleTypeNotSupportedException() {
		super("VehicleType not supported, please provide an existing one");
	}
}
