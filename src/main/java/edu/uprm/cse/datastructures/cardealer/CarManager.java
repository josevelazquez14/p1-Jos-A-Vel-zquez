package edu.uprm.cse.datastructures.cardealer;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Path;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
	@Path("/cars")
public class CarManager {
		private final long carID;
		  private final String carBrand;
		  private final String carModel;
		  private final String carModelOption;
		  private final double carPrice;
		  private static final AtomicLong counter = new AtomicLong(100);
		
	private final CircularSortedDoublyLinkedList<CarManagerBuilder> carList = MockCustumerList.getInstance();	
		
	private CarManager(CarManagerBuilder builder){
	    this.carID = builder.carID;
	    this.carBrand = builder.carBrand;
	    this.carModel = builder.carModel;
	    this.carModelOption = builder.carModelOption;
	    this.carPrice = builder.carPrice;
	  }
	
	public static class CarManagerBuilder{
	    private long carID;
	    private String carBrand = "";
	    private String carModel = "";
	    private String carModelOption = "";
	    private double carPrice;
    
    public String toString(){
	    return "Car ID: " + carID 
	        + " Brand: " + carBrand
	        + " Model: " + carModel + "\n"
	        + "Model Option: " + carModelOption + "\n"
	        + " Price: " + carPrice;
    	}     
  
		  public CarManagerBuilder carID(long carID){
		      this.carID = carID;
		      return this;
		    }
	
		    public CarManagerBuilder carModel(String carModel){
		      this.carModel = carModel;
		      return this;
		    }
		    
		    public CarManagerBuilder carModelOption(String carModelOption){
		      this.carModelOption = carModelOption;
		      return this;
		    }
		    
		    public CarManagerBuilder carPrice(double carPrice){
		      this.carPrice = carPrice;
		      return this;
		    }
		    
		    public CarManagerBuilder carBrand(String carBrand) {
		    	this.carBrand = carBrand ;
		    	return this;
		    }


	}
    

    public long getCarID(){
        return this.carID;
      }

      public String getCarBrand() {
        return this.carBrand;
      }

      public String getCarModel() {
        return this.carModel;
      }
      
      public String getcarModelOption(){
        return this.carModelOption;
      }

      public double getcarPrice() {
        return this.carPrice;
      }    
    
     
        
	      
	public void readAll(Car[] cars) {
		
	}
	 
	public Object read(String carID) {
		
		return null;
	}
	
	public void add(CarManagerBuilder newCar) {
		carList.add(newCar);
		
	}
	
	public void update(Car car) {
		for(int i = 0; i<carList.size(); i++) {
//			if(car.getCarId() == getAllCars[i].getCarId()) {
//				carList.remove(i);
//				carList.add(car);
				
				
			}
		}
	}
	
	public Object delete(String carID) {
		return null;
	}
}
