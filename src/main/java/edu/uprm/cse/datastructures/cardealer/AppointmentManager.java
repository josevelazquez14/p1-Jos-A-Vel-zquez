package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.util.AppointmentPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.Position;


@Path("/appointment")

public class AppointmentManager {

	private final static int list_size = 5;
	private static AppointmentPositionalList<Appointment>[] appointmentList = 
			new AppointmentPositionalList[list_size];
	private boolean initialized = false;

	public AppointmentManager() {
		if(!initialized) {
			appointmentList[0] = new AppointmentPositionalList<Appointment>(); //monday
			appointmentList[1] = new AppointmentPositionalList<Appointment>(); //tusday
			appointmentList[2] = new AppointmentPositionalList<Appointment>(); //wednesday
			appointmentList[3] = new AppointmentPositionalList<Appointment>(); //thursday
			appointmentList[4] = new AppointmentPositionalList<Appointment>(); //friday

			initialized = true;
		}
	}




	@GET
//	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment[] getAllAppointment() {
		Appointment[] arr = new Appointment[appointmentList.length];
		int i = 0;
		for(int j=0; j<list_size; j++) {
			for(Appointment appointment: appointmentList[j]){
				arr[i] = appointment;
				i++;
			}

		}
		return arr;
	}            

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment getAppointment(@PathParam("id") long id){
		for(int i=0; i<list_size; i++) {
			for(Appointment appointment: appointmentList[i]){
				if(appointment.getAppointmentId() == id){
					return appointment;
				}
			} 
		}

		throw new NotFoundException();

	}    

	@GET
	@Path("/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppointmentPositionalList<Appointment> searchAppointmentByDay(@PathParam("day") String day){
		int pos = -1;
		switch(day) {
		case "Monday":
			pos = 0;
			break;
		case "Tusday":
			pos =1;
			break;
		case "Wednesday":
			pos =2;
			break;
		case "Thurday":
			pos =3;
			break;
		case "Friday":
			pos =4;
			break;	
		}

		if(pos<0)
			throw new NotFoundException();
		else {
			return appointmentList[pos];
		}

	}      

	@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointment(Appointment newAppointment, @PathParam("day") String day) {
		int pos = -1;
		switch(day) {
		case "Monday":
			pos = 0;
			break;
		case "Tusday":
			pos =1;
			break;
		case "Wednesday":
			pos =2;
			break;
		case "Thurday":
			pos =3;
			break;
		case "Friday":
			pos =4;
			break;	
		}

		if(pos<0)
			throw new IndexOutOfBoundsException();
		else 
			appointmentList[pos].addLast(newAppointment);


		return Response.status(201).build();
	}       

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAppointment(Appointment newAppointment){

		for(int i=0; i<list_size; i++) {
			for(Position<Appointment> pos: appointmentList[i].positions()){
				if(pos.getElement().getAppointmentId() == newAppointment.getAppointmentId()){
					Position<Appointment> newPos = appointmentList[i].addAfter(pos, newAppointment);

					appointmentList[i].remove(appointmentList[i].before(newPos));

					return Response.status(Response.Status.OK).build();
				} 
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();      
	}      

	@DELETE
	@Path("/{id}/delete")
	public void deleteAppointment(@PathParam("id") long id){
		boolean removed = false;
		for(int i=0; i<list_size; i++) {
			for(Position<Appointment> pos: appointmentList[i].positions()){
				if(pos.getElement().getAppointmentId() == id){
					appointmentList[i].remove(pos);
					removed = true;
					break;
				}
				if(!removed)
					throw new NotFoundException();
			}
		}
	}      

}
