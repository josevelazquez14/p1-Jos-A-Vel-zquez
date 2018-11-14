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


import edu.uprm.cse.datastructures.cardealer.model.CarUnitComparator;
import edu.uprm.cse.datastructures.cardealer.model.carUnit;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/carunit")

public class CarUnitManager {

	private static CircularSortedDoublyLinkedList<carUnit> carUnitList = new CircularSortedDoublyLinkedList<carUnit>(new CarUnitComparator());
	
	 @GET
     @Path("")
     @Produces(MediaType.APPLICATION_JSON)
     public carUnit[] getAllCarUnit() {
   	  carUnit[] arr = new carUnit[carUnitList.size()];
   	  int i = 0;
   	  for(carUnit carUnit: carUnitList){
   		  arr[i] = carUnit;
   		  i++;
   	  }
     return arr;
     }            
	 
     @GET
     @Path("/{id}")
     @Produces(MediaType.APPLICATION_JSON)
     public carUnit getCarUnit(@PathParam("id") long id){
       for(carUnit carUnit: carUnitList){
       	if(carUnit.getCarUnitId() == id){
       		return carUnit;
       	}
       } 
       
         throw new NotFoundException();
       
     }      
	
	@POST
   @Path("/add")
   @Produces(MediaType.APPLICATION_JSON)
   public Response addCarUnit(carUnit newCarUnit){
     carUnitList.add(newCarUnit);
     return Response.status(201).build();
   }       
	
	 @PUT
	    @Path("/{id}/update")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response updateCarUnit(carUnit newcarUnit){
	      
	      for(carUnit carUnit: carUnitList){
	    	  if(carUnit.getCarUnitId() == newcarUnit.getCarUnitId()){
	    		  carUnitList.remove(carUnit);
	    		  carUnitList.add(newcarUnit);
	    		  return Response.status(Response.Status.OK).build();
	    	  } 
	      } 
	        return Response.status(Response.Status.NOT_FOUND).build();      
	    }      
	
	 @DELETE
	    @Path("/{id}/delete")
	    public void deleteCarUnit(@PathParam("id") long id){
		 boolean removed = false;
	      for (carUnit carUnit: carUnitList) {
	    	  if(carUnit.getCarUnitId()== id){
	    		  carUnitList.remove(carUnit);
	    		 
	    		  break;
	    	  }
	    	  if(!removed)
	    		  throw new NotFoundException();
	      }
	    } 
}