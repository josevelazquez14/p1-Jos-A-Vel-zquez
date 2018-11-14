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


@Path("/appoitment")

public class AppointmentManager {

	private final static int list_size = 5;
	private static AppointmentPositionalList<Appointment>[] appointmentList = 
			new AppointmentPositionalList[list_size];
	
	
	static AppointmentPositionalList<Appointment> mL = new AppointmentPositionalList<>(); //monday
	static AppointmentPositionalList<Appointment> tL = new AppointmentPositionalList<>();  //tusday
	static AppointmentPositionalList<Appointment> wL = new AppointmentPositionalList<>();  //wednesday
	static AppointmentPositionalList<Appointment> thL = new AppointmentPositionalList<>();  //thursday
	static AppointmentPositionalList<Appointment> fL = new AppointmentPositionalList<>(); //friday
	
	
	public AppointmentManager() {
		appointmentList[0] = mL;
		appointmentList[1] = tL;
		appointmentList[2] = wL;
		appointmentList[3] = thL;
		appointmentList[4] = fL;
		
		
	}
	
	
	
	
	@GET
    @Path("")
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
    @Path("day/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    public AppointmentPositionalList<Appointment> getAppointmentByDay(@PathParam("day") int day){
    	      
    	if(day<0)
    		throw new IndexOutOfBoundsException();
    	else {
    		return appointmentList[day];
    	}
      
    }      
	
    @POST
  @Path("/add/{day}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addAppointment(Appointment newAppointment, @PathParam("day") int day) {
		for(int i=0; i<list_size; i++) {
			 for(Appointment app: appointmentList[i]){
				 if(app.getAppointmentId() == newAppointment.getAppointmentId()){
					 return Response.status(Response.Status.BAD_REQUEST).build();
				 }
			 }
		}
		
		
    if(day<0 || day>4)
    	throw new IndexOutOfBoundsException();
    
   try {
	   appointmentList[day].addLast(newAppointment);
   } catch (Exception e) {
	   return Response.status(Response.Status.BAD_REQUEST).build();
   }
    	
    	return Response.status(Response.Status.CREATED).build();
    
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