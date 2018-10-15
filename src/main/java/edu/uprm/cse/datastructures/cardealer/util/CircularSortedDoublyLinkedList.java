package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Iterator;

import edu.uprm.cse.datastructures.cardealer.CarManager.CarManagerBuilder;
import edu.uprm.cse.datastructures.cardealer.model.Car;
import javassist.bytecode.analysis.ControlFlow.Node;

public class CircularSortedDoublyLinkedList<CarManagerBuilder> implements SortedList<CarManagerBuilder>{
private class DNode<CarManagerBuilder> {
	private CarManagerBuilder value;
	private DNode<CarManagerBuilder> next;
	private DNode<CarManagerBuilder> prev;
	
	public CarManagerBuilder getElement() {
		return value;
	}
	public void setElement(CarManagerBuilder newCar) {
		this.value = newCar;
	}
	public DNode<CarManagerBuilder> getNext() {
		return next;
	}
	public void setNext(DNode<CarManagerBuilder> next) {
		this.next = next;
	}
	public DNode<CarManagerBuilder> getPrev() {
		return prev;
	}
	public void setPrev(DNode<CarManagerBuilder> prev) {
		this.prev = prev;
	}
	
}
	private int currentSize;
	private DNode<CarManagerBuilder> head, last = null;
	
	
	@Override
	public Iterator<CarManagerBuilder> iterator() {
		// TODO Auto-generated method stub
//		public boolean hasNext() {
//		      return index < N;
//		    }
//		    public E next() {
//		        if (!hasNext()) throw new NoSuchElementException();
//
//		    }
//		    public void remove() { throw new UnsupportedOperationException(); }
		
		return null;
	}
	
	public boolean add(CarManagerBuilder carManagerBuilder) {
		// TODO Auto-generated method stub
		if (carManagerBuilder == null){
			return false;
		}
		
		DNode<CarManagerBuilder> temp = null;
		for (temp = head; temp.getNext() != null; temp = temp.getNext());
		DNode<CarManagerBuilder> newNode = new DNode();
		newNode.setElement(carManagerBuilder);
		newNode.setPrev(temp);
		temp.setNext(newNode);
		this.currentSize++;
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}

	@Override
	public boolean remove(CarManagerBuilder car) {
		// TODO Auto-generated method stub
		if (car == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		DNode<CarManagerBuilder> temp = null;
		for (temp = head.getNext(); temp != null; temp = temp.getNext()){
			if (temp.getElement().equals(car)){
				// found first copy
				if (temp.getNext() != null){
					temp.getNext().setPrev(temp.getPrev());
				}
				temp.getPrev().setNext(temp.getNext());
				temp.setElement(null);
				temp.setNext(null);
				temp.setPrev(null);
				this.currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		
		DNode<CarManagerBuilder> temp = null;
		int counter = 0;
		for (temp = head.getNext(); counter < index; temp = temp.getNext(), counter++);
		if (temp.getNext() != null){
			// i am not at the end of list
			temp.getNext().setPrev(temp.getPrev()); // null if temp is the last one
		}
		temp.getPrev().setNext(temp.getNext());
		temp.setElement(null);
		temp.setNext(null);
		temp.setPrev(null);
		this.currentSize--;
		return true;
	}

	@Override
	public int removeAll(CarManagerBuilder car) {
		// TODO Auto-generated method stub
		int numCopies = 0;
		while(this.remove(car)){
			numCopies++;
		}
		return numCopies;
	}

	@Override
	public CarManagerBuilder first() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		
		return head.getElement();
	}

	@Override
	public CarManagerBuilder last() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		
		return last.getElement();
	}

	@Override
	public CarManagerBuilder get(int index) {
		// TODO Auto-generated method stub
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		else {
			int counter = 0;
			DNode<CarManagerBuilder> temp = null;
			for (temp = head.getNext(); counter < index; temp = temp.getNext(), counter++);
			return temp.getElement();
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub	
		while(!this.isEmpty()) {
			this.remove(0);
		}
	}

	@Override
	public boolean contains(CarManagerBuilder car) {
		// TODO Auto-generated method stub
		return this.firstIndex(car) >= 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size() == 0;
	}

	@Override
	public int firstIndex(CarManagerBuilder car) {
		// TODO Auto-generated method stub
		if (car == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter = 0;
			DNode<CarManagerBuilder> temp = null;
			for (temp = head.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (temp.getElement().equals(car)){
					return counter;
				}
			}
			return -1;
		}
	}

	@Override
	public int lastIndex(CarManagerBuilder car) {
		// TODO Auto-generated method stub
		if (car == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter =0, lastSeen = -1;
			DNode<CarManagerBuilder> temp = null;
			for (temp = head.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (temp.getElement().equals(car)){
					lastSeen = counter;
				}
			}
			return lastSeen;
		}
	}

	
}
