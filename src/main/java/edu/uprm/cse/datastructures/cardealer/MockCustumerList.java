package edu.uprm.cse.datastructures.cardealer;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import edu.uprm.cse.datastructures.cardealer.CarManager.CarManagerBuilder;
import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

public class MockCustumerList {
  private static final CircularSortedDoublyLinkedList<CarManagerBuilder> carList = new CircularSortedDoublyLinkedList<>();

  static {
    // Create list of customers
    carList.add(
        new CarManager.CarManagerBuilder().carID(01)
        .carBrand("Toyota")
        .carModel("Corolla")
        .carModelOption("CE")
        .carPrice(25000)
    );

    
    carList.add(
            new CarManager.CarManagerBuilder().carID(01)
            .carBrand("Toyota")
            .carModel("Corolla")
            .carModelOption("S")
            .carPrice(30000)
        );
    
    carList.add(
            new CarManager.CarManagerBuilder().carID(01)
            .carBrand("Ford")
            .carModel("Fiesta")
            .carModelOption("RS")
            .carPrice(28000)
        );
    
    carList.add(
            new CarManager.CarManagerBuilder().carID(01)
            .carBrand("Nisan")
            .carModel("Juke")
            .carModelOption("R Nismo")
            .carPrice(45000)
        );
    

  }
  
  private MockCustumerList(){}
  
  public static CircularSortedDoublyLinkedList<CarManagerBuilder> getInstance(){
    return carList;
  }
  
  
}                                
