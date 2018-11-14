package edu.uprm.cse.datastructures.cardealer;

import java.util.ArrayList;

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

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.Person;
import edu.uprm.cse.datastructures.cardealer.model.PersonComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/person")

public class PersonManager {
	
	
	private static CircularSortedDoublyLinkedList<Person> personList = new CircularSortedDoublyLinkedList<Person>(new PersonComparator());
	
	@GET
//    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Person[] getAllPersons() {
  	  Person[] arr = new Person[personList.size()];
  	  int i = 0;
  	  for(Person person: personList){
  		  arr[i] = person;
  		  i++;
  	  }
    return arr;
    }            
	 
    @GET
    @Path("lastname/{lastname}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Person> getPersonByLastName(@PathParam("lastname") String lastName){
    	ArrayList<Person> personArr =new ArrayList<Person>();


  	  for(int i =0; i<personList.size(); i++) {
  		  for(Person person: personList){
  			  if(person.getLastName().toLowerCase().equals(lastName.toLowerCase())){
  				  personArr.add(person);
  			  }
  		  } 
  	  }
      
  	  return personArr;       
    }  
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") long id){
      for(Person person: personList){
      	if(person.getPersonId() == id){
      		return person;
      	}
      } 
      
        throw new NotFoundException();
      
    }   
	
	@POST
  @Path("/add")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addPerson(Person newPerson){
		for(int i = 0; i< personList.size(); i++){
			if(personList.get(i).getPersonId() == newPerson.getPersonId()){
				return Response.status(Response.Status.FORBIDDEN).build();
			}
				
		}
		
		personList.add(newPerson);
    return Response.status(201).build();
  }       
	
	 @PUT
	    @Path("/{id}/update")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response updatePerson(Person newPerson){
	      
	      for(Person person: personList){
	    	  if(person.getPersonId() == newPerson.getPersonId()){
	    		  personList.remove(person);
	    		  personList.add(newPerson);
	    		  return Response.status(Response.Status.OK).build();
	    	  } 
	      } 
	        return Response.status(Response.Status.NOT_FOUND).build();      
	    }      
	
	 @DELETE
	    @Path("/{id}/delete")
	    public void deletePerson(@PathParam("id") long id){
		 boolean removed = false;
	      for (Person person: personList) {
	    	  if(person.getPersonId()== id){
	    		  personList.remove(person);
	    		 
	    		  break;
	    	  }
	    	  if(!removed)
	    		  throw new NotFoundException();
	      }
	    }      
}
