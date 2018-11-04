package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarUnitComparator implements Comparator<carUnit>{

	@Override
	public int compare(carUnit carUnit1, carUnit carUnit2) {
		// TODO Auto-generated method stub
		
		String carUnitInfo1 = carUnit1.getVIN();
		String carUnitInfo2 = carUnit2.getVIN();
		
		return carUnitInfo1.compareTo(carUnitInfo2);
	}

}
