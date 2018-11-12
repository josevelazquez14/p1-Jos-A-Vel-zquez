package edu.uprm.cse.datastructures.cardealer.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import edu.uprm.cse.datastructures.cardealer.MockCustumerList;
import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;


public class CircularSortedDoublyLinkedList<E> implements SortedList<E>{
private class DNode<E> {
	private E value;
	private DNode<E> next;
	private DNode<E> prev;
	
	public E getElement() {
		return value;
	}
	public void setElement(E newCar) {
		this.value = newCar;
	}
	public DNode<E> getNext() {
		return next;
	}
	public void setNext(DNode<E> next) {
		this.next = next;
	}
	public DNode<E> getPrev() {
		return prev;
	}
	public void setPrev(DNode<E> prev) {
		this.prev = prev;
	}
	
}
	private int currentSize;	
	private	DNode<E> head;
	private Comparator<E> c;
	
	
	public CircularSortedDoublyLinkedList(Comparator<E> comp) {
		head = new DNode<E>();
		this.head.setElement(null);
		this.head.setNext(head);
		this.head.setPrev(head);
		currentSize =0;
		c = comp;
	}
	
	private final CircularSortedDoublyLinkedList<Car> carList = MockCustumerList.getInstance();	
		
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		List<E> list = new ArrayList<E>();
		
		DNode<E> current = this.head.getNext();
		
		while(current != head) {
			list.add(current.getElement());
			current = current.getNext();
		}
		
		return list.iterator();
	}
	
	public boolean add(E obj) {
		// TODO Auto-generated method stub
		DNode<E> temp = new DNode<E>();
		temp.setElement(obj);
		
		if(obj == null) {
			return false;
		}
		
		if(this.contains(obj))
			return false;
		
		DNode<E> cursor = head.getNext();
		while(cursor != head) {
			if(c.compare(obj, cursor.getElement()) <=0) {
				temp.setNext(cursor);
				temp.setPrev(cursor.getPrev());
				cursor.getPrev().setNext(temp);
				cursor.setPrev(temp);
				this.currentSize++;
				return true;
			
			}			
			cursor = cursor.getNext();
		}
		
		
		temp.setNext(head);
		temp.setPrev(this.head.getPrev());
		this.head.setPrev(temp);
		temp.getPrev().setNext(temp);
		this.currentSize++;		
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}

	@Override
	public boolean remove(E car) {
		// TODO Auto-generated method stub
		if (car == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		DNode<E> temp = head.getNext();
		
		if(!this.contains(car))
			return false;
		
			while (temp != head){
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
		
		
		DNode<E> temp = null;
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
	public int removeAll(E car) {
		// TODO Auto-generated method stub
		int numCopies = 0;
		while(this.remove(car)){
			numCopies++;
		}
		return numCopies;
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		
		return head.getElement();
	}


	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		else {
			int counter = 0;
			DNode<E> temp = head.getNext() ;
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
	public boolean contains(E car) {
		// TODO Auto-generated method stub
		return this.firstIndex(car) >= 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size() == 0;
	}

	@Override
	public int firstIndex(E car) {
		// TODO Auto-generated method stub
		if (car == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter = 0;
			DNode<E> temp = head.getNext();
			while(temp != head) {
				if(temp.getElement().equals(car))
					return counter;
				temp = temp.getNext();
				counter++;
			}
			return -1;
		}
	}

	@Override
	public int lastIndex(E car) {
		// TODO Auto-generated method stub
		if (car == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter =0, lastSeen = -1;
			DNode<E> temp = head.getNext();
			while(temp.getNext() != head){
				if (temp.equals(car)){
					lastSeen = counter;
				}
			}
			return lastSeen;
		}
	}

	@Override
	public E last() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
