package edu.uprm.cse.datastructures.cardealer.model;

public class carUnit {
	private long carUnitId; // internal id of the unit
	private long carId; // id of the car object that represents the general for the car. 
	// This Car from project 1.
	private String VIN; // vehicle identification number
	private String color; // car color
	private String carPlate; // car plate (null until sold)
	private long personId; // id of the person who purchased the car. (null until 
	//purchased)	
	
	public long getCarUnitId() {
		return carUnitId;
	}
	public void setCarUnitId(long carUnitId) {
		this.carUnitId = carUnitId;
	}
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}

}
