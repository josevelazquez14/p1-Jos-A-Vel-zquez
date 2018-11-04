package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{

	@Override
	public int compare(Person person1, Person person2) {
		// TODO Auto-generated method stub
		
		String personInfo1 = person1.getLastName() + person1.getFirstName();
		String personInfo2 = person2.getLastName() + person2.getFirstName();
 		
		return personInfo1.compareTo(personInfo2);
		
	}

}
