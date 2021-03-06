package edu.uprm.cse.datastructures.cardealer;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

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
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
	@Path("/cars")
public class CarManager {
//		private final long carID;
//		  private final String carBrand;
//		  private final String carModel;
//		  private final String carModelOption;
//		  private final double carPrice;
//		  private static final AtomicLong counter = new AtomicLong(100);
		
	private static CircularSortedDoublyLinkedList<Car> carList = new CircularSortedDoublyLinkedList<Car>(new CarComparator());	
		
//	private CarManager(CarManagerBuilder builder){
//	    this.carID = builder.carID;
//	    this.carBrand = builder.carBrand;
//	    this.carModel = builder.carModel;
//	    this.carModelOption = builder.carModelOption;
//	    this.carPrice = builder.carPrice;
//	  }
//	
//	public static class CarManagerBuilder{
//	    private long carID;
//	    private String carBrand = "";
//	    private String carModel = "";
//	    private String carModelOption = "";
//	    private double carPrice;
//    
//    public String toString(){
//	    return "Car ID: " + carID 
//	        + " Brand: " + carBrand
//	        + " Model: " + carModel + "\n"
//	        + "Model Option: " + carModelOption + "\n"
//	        + " Price: " + carPrice;
//    	}     
//  
//		  public CarManagerBuilder carID(long carID){
//		      this.carID = carID;
//		      return this;
//		    }
//	
//		    public CarManagerBuilder carModel(String carModel){
//		      this.carModel = carModel;
//		      return this;
//		    }
//		    
//		    public CarManagerBuilder carModelOption(String carModelOption){
//		      this.carModelOption = carModelOption;
//		      return this;
//		    }
//		    
//		    public CarManagerBuilder carPrice(double carPrice){
//		      this.carPrice = carPrice;
//		      return this;
//		    }
//		    
//		    public CarManagerBuilder carBrand(String carBrand) {
//		    	this.carBrand = carBrand ;
//		    	return this;
//		    }
//
//
//	}
    

//    public long getCarID(){
//        return this.carID;
//      }
//
//      public String getCarBrand() {
//        return this.carBrand;
//      }
//
//      public String getCarModel() {
//        return this.carModel;
//      }
//      
//      public String getcarModelOption(){
//        return this.carModelOption;
//      }
//
//      public double getcarPrice() {
//        return this.carPrice;
//      }    
    
     
        
	      
      @GET
      @Path("")
      @Produces(MediaType.APPLICATION_JSON)
      public Car[] getAllCar() {
    	  Car[] arr = new Car[carList.size()];
    	  int i = 0;
    	  for(Car car: carList){
    		  arr[i] = car;
    		  i++;
    	  }
      return arr;
      }            
	 
      @GET
      @Path("/{id}")
      @Produces(MediaType.APPLICATION_JSON)
      public Car getCar(@PathParam("id") long id){
        for(Car car: carList){
        	if(car.getCarId() == id){
        		return car;
        	}
        } 
        
          throw new NotFoundException();
        
      }   
      
      @GET
      @Path("brand/{brand}")
      @Produces(MediaType.APPLICATION_JSON)
      public ArrayList<Car> searchCarBrand(@PathParam("brand") String brand){
    	  ArrayList<Car> carArr =new ArrayList<Car>();

    	  for(int i =0; i<carList.size(); i++) {
    		  for(Car car: carList){
    			  if(car.getCarBrand().toLowerCase().equals(brand.toLowerCase())){
    				  carArr.add(car);
    			  }
    		  } 
    	  }
        
    	  return carArr;        
      }   
      
      @GET
      @Path("year/{year}")
      @Produces(MediaType.APPLICATION_JSON)
      public ArrayList<Car> searchCarYear(@PathParam("year") double year){
    	  ArrayList<Car> carArr =new ArrayList<Car>();

    	  for(int i =0; i<carList.size(); i++) {
    		  for(Car car: carList){
    			  if(car.getCarYear() == year){
    				  carArr.add(car);
    			  }
    		  } 
    	  }
        
    	  return carArr;        
      }   
      
      
	
	@POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCar(Car newCar){
		for(int i = 0; i< carList.size(); i++){
			if(carList.get(i).getCarId() == newCar.getCarId()){
				return Response.status(Response.Status.FORBIDDEN).build();				
			}
				
		}
		carList.add(newCar);
      return Response.status(201).build();
    }       
	
	 @PUT
	    @Path("/{id}/update")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response updateCar(Car newcar){
	      
	      for(Car car: carList){
	    	  if(car.getCarId() == newcar.getCarId()){
	    		  carList.remove(car);
	    		  carList.add(newcar);
	    		  return Response.status(Response.Status.OK).build();
	    	  } 
	      } 
	        return Response.status(Response.Status.NOT_FOUND).build();      
	    }      
	
	 @DELETE
	    @Path("/{id}/delete")
	    public void deleteCar(@PathParam("id") long id){
		 boolean removed = false;
	      for (Car car: carList) {
	    	  if(car.getCarId()== id){
	    		  carList.remove(car);
	    		 
	    		  break;
	    	  }
	    	  if(!removed)
	    		  throw new NotFoundException();
	      }
	    }      
}