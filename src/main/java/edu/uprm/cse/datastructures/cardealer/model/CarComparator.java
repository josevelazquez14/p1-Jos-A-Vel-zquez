package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;
import java.util.Iterator;




public class CarComparator implements Comparator<Car> {

	

	public int compare(Car car1, Car car2) {
		// TODO Auto-generated method stub	
		String car1Info = car1.getCarBrand() + car1.getCarModel() + car1.getCarModelOption();
		String car2Info = car2.getCarBrand() + car2.getCarModel() + car2.getCarModelOption();
		
		return car1Info.compareTo(car2Info);
	}

	

}
