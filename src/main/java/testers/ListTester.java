package testers;

import java.util.Comparator;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class ListTester {

	public static void main(String[] args) {
		//SortedList<Integer> list = new CircularSortedDoublyLinkedList<Integer>(new Comparator);
		
		//System.out.println("true : " + list.isEmpty());
		
		SortedList<Car> cars = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
		
		cars.add(new Car(1, "Toyota", "Rav4", "LE", 1, 0));
		cars.add(new Car(2, "Toyota", "Rav4", "SE", 1, 0));
		cars.add(new Car(3, "Honda", "Civic", "DX", 1, 0));
		cars.add(new Car(4, "Honda", "Accord", "LX", 1, 0));
		
		printList(cars);
		
//		cars.clear();
//		cars = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
//		((CircularSortedDoublyLinkedList<Car>)cars).setComparator((Comparator<Car>) new CarComparator());
		System.out.println("");
		Car civic = new Car(3, "Honda", "Civic", "DX", 1, 0);
		cars.add(new Car(1, "Toyota", "Rav4", "LE", 1, 0));
		cars.add(new Car(2, "Toyota", "Rav4", "SE", 1, 0));
		cars.add(civic);
		cars.add(new Car(4, "Honda", "Accord", "LX", 1, 0));
		
		printList(cars);
		
		Car car = new Car(5, "Nissan", "350z", "Track", 1, 0);
		cars.add(car);
		System.out.println("\n added one more car");
		printList(cars);
		
		System.out.println("\n remove that car");
		cars.remove(car);
		printList(cars);
		
		System.out.println("\n remove car at idx 2");
		cars.remove(2);
		printList(cars);
		
		System.out.println("\n remove a car that doesnt exist in list");
		System.out.println(cars.remove(car));
		
		System.out.println("\n remove car idx that is out of bounds");
		System.out.println(cars.remove(-1) + " | " + cars.remove(cars.size()+1));
		
		System.out.println("\n getting car at idx 1");
		System.out.println(cars.get(1));
		
		System.out.println("\n contains tester");
		System.out.println(" should be true: "+ cars.contains(civic) +
				"\n should be false: " + cars.contains(car));
		
		System.out.println("\n isEmpty tester");
		cars.clear();
		System.out.println("should be true: "+ cars.isEmpty());
		
		for (int i = 0; i < 3; i++) {
			cars.add(car);
			cars.add(civic);
		}
		
		System.out.println("firstIndex tester");
		System.out.println("\n civic first: " + cars.firstIndex(civic) + 
				"\n civic last: " + cars.lastIndex(civic) + 
				"\n nissan first: " + cars.firstIndex(car) + 
				"\n nissan last: " + cars.lastIndex(car));
		
		System.out.println("\n removeAll tester");
		System.out.println("expected: 3: result: " + cars.removeAll(civic));
		printList(cars);
		
	}

	private static void printList(SortedList<?> list) {
		for (Object object : list) {
				System.out.println(object);
		}
		
	}

}