package edu.uprm.cse.datastructures.cardealer;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.util.AppointmentPositionalList;

public class appointmentLists  {

	private final static int list_size = 5;
	private static AppointmentPositionalList<Appointment>[] appointmentList = 
			new AppointmentPositionalList[list_size];


	static AppointmentPositionalList<Appointment> mL = new AppointmentPositionalList<>(); //monday
	static AppointmentPositionalList<Appointment> tL = new AppointmentPositionalList<>();  //tusday
	static AppointmentPositionalList<Appointment> wL = new AppointmentPositionalList<>();  //wednesday
	static AppointmentPositionalList<Appointment> thL = new AppointmentPositionalList<>();  //thursday
	static AppointmentPositionalList<Appointment> fL = new AppointmentPositionalList<>(); //friday

	public appointmentLists() {
			
	}

	
	public static AppointmentPositionalList<Appointment>[] getList() {
		// TODO Auto-generated method stub
		appointmentList[0] = mL;
		appointmentList[1] = tL;
		appointmentList[2] = wL;
		appointmentList[3] = thL;
		appointmentList[4] = fL;
		
		return appointmentList;
	}
}
