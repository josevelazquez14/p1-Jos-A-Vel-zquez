package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Iterator;

import edu.uprm.cse.datastructures.cardealer.CarManager.CarManagerBuilder;
import edu.uprm.cse.datastructures.cardealer.model.Car;
import javassist.bytecode.analysis.ControlFlow.Node;

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
	private DNode<E> head, last = null;
	
	
	@Override
	public Iterator<E> iterator() {
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
	
	public boolean add(E e) {
		// TODO Auto-generated method stub
		if (e== null){
			return false;
		}
		
		DNode<E> temp = null;
		for (temp = head; temp.getNext() != null; temp = temp.getNext());
		DNode<E> newNode = new DNode();
		newNode.setElement(e);
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
	public boolean remove(E car) {
		// TODO Auto-generated method stub
		if (car == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		DNode<E> temp = null;
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
	public E last() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		
		return last.getElement();
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		else {
			int counter = 0;
			DNode<E> temp = null;
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
			DNode<E> temp = null;
			for (temp = head.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (temp.getElement().equals(car)){
					return counter;
				}
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
			DNode<E> temp = null;
			for (temp = head.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (temp.getElement().equals(car)){
					lastSeen = counter;
				}
			}
			return lastSeen;
		}
	}

	
}
