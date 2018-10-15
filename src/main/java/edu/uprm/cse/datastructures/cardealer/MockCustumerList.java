package edu.uprm.cse.datastructures.cardealer;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import edu.uprm.cse.datastructures.cardealer.CarManager.CarManagerBuilder;
import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

public class MockCustumerList {
  private static final CircularSortedDoublyLinkedList<Car> carList = new CircularSortedDoublyLinkedList<>();

    

    

  
  
  private MockCustumerList(){}
  
  public static CircularSortedDoublyLinkedList<Car> getInstance(){
    return carList;
  }
  
  
  
}                                
