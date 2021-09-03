////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    P10 OrderUp2
//Course:   CS 300 Spring 2021
//
//Author:   Zachary Collins
//Email:    ztcollins@wisc.edu
//Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:         -
//Online Sources:  -
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A max-heap implementation of a priority queue for Orders, where the Order with the LONGEST prep 
 * time is returned first instead of the strict first-in-first-out queue as in P08.
 * 
 * TODO: Make sure Order implements Comparable<Order> so that this class can implement the
 * PriorityQueueADT without error!
 */
public class OrderPriorityQueue implements PriorityQueueADT<Order>{

  // Data fields; do not modify
  private Order[] queueHeap;
  private int size;
  
  /**
   * Constructs a PriorityQueue for Orders with the given capacity
   * 
   * @param capacity the initial capacity for the queue
   * @throws IllegalArgumentException if the given capacity is 0 or negative
   */
  public OrderPriorityQueue(int capacity) {
    if(capacity <= 0) {
      throw new IllegalArgumentException("capacity must be larger than 0!");
    }
    
    queueHeap = new Order[capacity];
    size = 0;
  }
  
  /**
   * Inserts a new Order into the queue in the appropriate position using a heap's add logic.
   * 
   * @param newOrder the Order to be added to the queue
   */
  @Override
  public void insert(Order newOrder) {
    // TODO If the queue is empty, insert the new order at the root of the heap
    if(isEmpty()) {
      queueHeap[0] = newOrder;
      size++;
      return;
    }
    
    // TODO If the queue is FULL, create a new Order array of double the current heap's size,
    // copy all elements of the current heap over and update the queueHeap reference
    // -> HINT: use Arrays.copyOf(), copying arrays is not the point of this assignment
    
    if(queueHeap.length == size) {
      queueHeap = Arrays.copyOf(queueHeap, queueHeap.length*2);
    }
    
    // TODO add the newOrder to the end of the heap and percolate up to ensure a valid heap, where
    // the Order with the LONGEST prep time is at the root of the heap
    
    
    queueHeap[size] = newOrder;
    percolateUp(queueHeap, size);
    size++;
  }
  
  /**
   * A utility method to percolate Order values UP through the heap; see figure 13.3.1 in zyBooks
   * for a pseudocode algorithm.
   * 
   * @param heap an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated up
   */
  protected static void percolateUp(Order[] heap, int orderIndex) {
    while(orderIndex > 0) {
      int parentIndex = (orderIndex-1)/2;
      if(heap[parentIndex] == null) {
        Order temp = heap[orderIndex];
        heap[orderIndex] = heap[orderIndex/2];
        heap[orderIndex/2] = temp;
        orderIndex = parentIndex;
        return;
      }
      if(heap[orderIndex] == null) {
        if(orderIndex*2+1 > heap.length) {
          return;
        }
        Order temp = heap[orderIndex];
        heap[orderIndex] = heap[orderIndex*2+1];
        heap[orderIndex*2+1] = temp;
        orderIndex = parentIndex;
        return;
      }
      if(heap[orderIndex].getPrepTime() <= heap[parentIndex].getPrepTime()) {
        return;
        }
      else {
        Order temp = heap[orderIndex];
        heap[orderIndex] = heap[(orderIndex-1)/2];
        heap[(orderIndex-1)/2] = temp;
        orderIndex = parentIndex;
      }
      }
    /*if(heap[(orderIndex-1)/2] == null) {
      Order temp = heap[orderIndex];
      heap[orderIndex] = heap[(orderIndex-1)/2];
      heap[(orderIndex-1)/2] = temp;
      return;
    }
    while(heap[orderIndex].getPrepTime() > heap[(orderIndex)/2].getPrepTime()) {
      Order temp = heap[orderIndex];
      heap[orderIndex] = heap[(orderIndex)/2];
      heap[(orderIndex)/2] = temp;
      orderIndex = (orderIndex)/2;
    }*/
  }
  
  /**
   * Return the Order with the longest prep time from the queue and adjust the queue accordingly
   * 
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order removeBest() {
    // TODO If the queue is empty, throw a NoSuchElementException
    if(isEmpty()) {
      throw new NoSuchElementException("ERROR: QUEUE IS EMPTY IN removeBest()");
    }
    
    // TODO Remove the root Order of the heap and re-structure the heap to ensure that its ordering
    // is valid, then return the previous root
    
    Order temp = queueHeap[0];
    queueHeap[0] = queueHeap[size-1];
    percolateDown(queueHeap, 0, size);
    size--;
    
    return temp; // included to prevent compiler errors
  }
  
  /**
   * A utility method to percolate Order values DOWN through the heap; see figure 13.3.2 in zyBooks
   * for a pseudocode algorithm.
   * 
   * @param heap an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated down
   * @param size the number of initialized elements in the heap
   */
  protected static void percolateDown(Order[] heap, int orderIndex, int size) {
    int childIndex = 2 * orderIndex + 1;
        Order value = heap[orderIndex];

        while (childIndex < size) {
           // Find the max among the node and all the node's children
           Order maxValue = value;
           int maxIndex = -1;
           for (int i = 0; i < 2 && i + childIndex < size; i++) {
              if (heap[i + childIndex].getPrepTime() > maxValue.getPrepTime()) {
                 maxValue = heap[i + childIndex];
                 maxIndex = i + childIndex;
              }
           }

           if (maxValue == value) {
              return;
           }
           else {
             Order temp = heap[orderIndex];
             heap[orderIndex] = heap[maxIndex];
             heap[maxIndex] = temp;
              orderIndex = maxIndex;
              childIndex = 2 * orderIndex + 1;
           }
        }
  }
  
  /**
   * Return the Order with the highest prep time from the queue without altering the queue
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order peekBest() {
    // TODO If the queue is empty, throw a NoSuchElementException
    if(isEmpty()) {
      throw new NoSuchElementException("ERROR QUEUE IS EMPTY IN: peekBest()");
    }
    return queueHeap[0]; 
  }
  
  /**
   * Returns true if the queue contains no Orders, false otherwise
   * @return true if the queue contains no Orders, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if(size == 0) {
      return true;
    }
    int count = 0;
    for(int i = 0; i < queueHeap.length; i++) {
      if(queueHeap[i] != null) {
        count++;
      }
    }
    if(count == 0) {
      return true;
    }
    return false;
  }
  
  /**
   * Returns the number of elements currently in the queue
   * @return the number of elements currently in the queue
   */
  public int size() {
    return size;
  }
  
  /**
   * Creates a String representation of this PriorityQueue. Do not modify this implementation; this
   * is the version that will be used by all provided OrderPriorityQueue implementations that your
   * tester code will be run against.
   * 
   * @return the String representation of this PriorityQueue, primarily for testing purposes
   */
  public String toString() {
    String toReturn = "";
    for (int i=0; i < this.size; i++) {
      toReturn += queueHeap[i].getID()+"("+queueHeap[i].getPrepTime()+")";
      if (i < this.size-1) toReturn += ", ";
    }
    return toReturn;
  }
  
}
