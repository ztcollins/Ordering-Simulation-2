import java.util.NoSuchElementException;

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

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * OrderPriorityQueue.
 * 
 * You MAY add additional public static boolean methods to this class if you like, and any private
 * static helper methods you need.
 */
public class OrderPriorityQueueTester {
  
  /**
   * Checks the correctness of the isEmpty method of OrderPriorityQueue.
   * 
   * You should, at least:
   * (1) create a new OrderPriorityQueue and verify that it is empty
   * (2) add a new Order to the queue and verify that it is NOT empty
   * (3) remove that Order from the queue and verify that it is empty again
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testIsEmpty() {
    Order.resetIDGenerator();
    
    // TODO implement scenario 1, then go write the constructor and isEmpty methods in your
    // OrderPriorityQueue class so that they pass the tests
    
    OrderPriorityQueue queue1 = new OrderPriorityQueue(5);
    
    if(!queue1.isEmpty()) {
      return false;
    }
    
    // TODO implement scenario 2, then go write enough of insert() to pass the tests
    
    queue1.insert(new Order("Food1", 20));
    
    if(queue1.isEmpty()) {
      return false;
    }
    
    // TODO implement scenario 3, then go write enough of remove() to pass the tests
    
    queue1.removeBest();
    
    if(!queue1.isEmpty()) {
      return false;
    }
    
    return true; // included to prevent compiler errors
  }
  
  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least:
   * (1) create a new OrderPriorityQueue and add a single order with a large prepTime to it
   * (2) use the OrderPriorityQueue toString method to verify that the queue's internal structure
   *     is a valid heap
   * (3) add at least three more orders with DECREASING prepTimes to the queue and repeat step 2.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testInsertBasic() {
    Order.resetIDGenerator();
    
    OrderPriorityQueue queue1 = new OrderPriorityQueue(5);
    queue1.insert(new Order("Fish", 200000));
    //System.out.println(queue1.toString()); //1001(200000)
    if(!queue1.toString().equals("1001(200000)")) {
      return false;
    }
    queue1.insert(new Order("Dog", 200));
    queue1.insert(new Order("Cat", 20));
    queue1.insert(new Order("Hat", 2));
    //System.out.println(queue1.toString()); //1001(200000), 1002(200), 1003(20), 1004(2)
    if(!queue1.toString().equals("1001(200000), 1002(200), 1003(20), 1004(2)")) {
      return false;
    }
    
    return true; // included to prevent compiler errors
  }
  
  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least:
   * (1) create an array of at least four Orders that represents a valid heap
   * (2) add a fifth order at the next available index that is NOT in a valid heap position
   * (3) pass this array to OrderPriorityQueue.percolateUp()
   * (4) verify that the resulting array is a valid heap
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPercolateUp() {
    Order.resetIDGenerator();
    
    Order[] array = new Order[5];
    array[0] = new Order("Fish", 200000);
    array[1] = new Order("Dog", 2000);
    array[2] = new Order("Cat", 20);
    array[3] = new Order("Hat", 2);
    array[4] = new Order("ErrorFood", 2001);
    OrderPriorityQueue.percolateUp(array, 4);
    /*for(int i = 0; i < array.length; i++) {
      System.out.println(array[i].toString());
    }*/
    if(!array[4].toString().equals("1002: Dog (2000)")) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least:
   * (1) create a new OrderPriorityQueue with at least 6 orders of varying prepTimes, adding them
   *     to the queue OUT of order
   * (2) use the OrderPriorityQueue toString method to verify that the queue's internal structure
   *     is a valid heap
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testInsertAdvanced() {
    Order.resetIDGenerator();
    
    OrderPriorityQueue queue1 = new OrderPriorityQueue(6);
    queue1.insert(new Order("Dog", 200));
    queue1.insert(new Order("Cat", 20));
    queue1.insert(new Order("Hat", 2));
    queue1.insert(new Order("Pog", 100));
    queue1.insert(new Order("Clog", 10));
    queue1.insert(new Order("Hog", 100000000));
    //System.out.println(queue1.toString());
    //1006(100000000), 1001(200), 1004(100), 1002(20), 1003(2), 1005(10)
    if(!queue1.toString().equals(
        "1006(100000000), 1004(100), 1001(200), 1002(20), 1005(10), 1003(2)")) {
      return false;
    }
    return true;
  }
  
  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least:
   * (1) create an array of at least five Orders where the Order at index 0 is NOT in valid heap
   *     position
   * (2) pass this array to OrderPriorityQueue.percolateDown()
   * (3) verify that the resulting array is a valid heap
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPercolateDown() {
    Order.resetIDGenerator();
    
    Order[] array = new Order[5];
    array[0] = new Order("ErrorFood", 2);
    array[1] = new Order("Dog", 2000);
    array[2] = new Order("Cat", 20);
    array[3] = new Order("Hat", 400);
    array[4] = new Order("Fish", 1);
    
    /*for(int i = 0; i < array.length; i++) {
      System.out.println(array[i].toString());
    }
    
    System.out.println("CHANGE ------------");*/
    
    OrderPriorityQueue.percolateDown(array, 0, 5);
    
    /*for(int i = 0; i < array.length; i++) {
      System.out.println(array[i].toString());
    }*/
    //1001: ErrorFood (2)
    if(!(array[3].getPrepTime() < array[2].getPrepTime())) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Checks the correctness of the removeBest and peekBest methods of OrderPriorityQueue.
   * 
   * You should, at least:
   * (1) create a new OrderPriorityQueue with at least 6 orders of varying prepTimes, adding them
   *     to the queue in whatever order you like
   * (2) remove all but one of the orders, verifying that each order has a SHORTER prepTime than
   *     the previously-removed order
   * (3) peek to see that the only order left is the one with the SHORTEST prepTime
   * (4) check isEmpty to verify that the queue has NOT been emptied
   * (5) remove the last order and check isEmpty to verify that the queue HAS been emptied
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPeekRemove() {
    Order.resetIDGenerator();
    
    OrderPriorityQueue queue1 = new OrderPriorityQueue(6);
    queue1.insert(new Order("Dog", 200));
    queue1.insert(new Order("Cat", 20));
    queue1.insert(new Order("Hat", 2));
    queue1.insert(new Order("Pog", 1000));
    queue1.insert(new Order("Clog", 10));
    queue1.insert(new Order("Hog", 100000000));
    
    //System.out.println(queue1.toString());
    
    Order previous = queue1.removeBest();;
    for(int i = 1; i < 5; i++) {
      Order temp = queue1.removeBest();
      if(previous.getPrepTime() < temp.getPrepTime()) {
        return false;
      }
      previous = temp;
    }
    if(queue1.peekBest().getPrepTime() > previous.getPrepTime()) {
      return false;
    }
    
    if(queue1.isEmpty()) {
      return false;
    }
    queue1.removeBest();
    //System.out.println(queue1.toString());
    //System.out.println(queue1.size());
    if(!queue1.isEmpty()) {
      return false;
    }
    
    return true; 
  }
  
  /**
   * Checks the correctness of the removeBest and peekBest methods, as well as the constructor of 
   * the OrderPriorityQueue class for erroneous inputs and/or states
   * 
   * You should, at least:
   * (1) create a new OrderPriorityQueue with an invalid capacity argument, and verify that the 
   *     correct exception is thrown
   * (2) call peekBest() on an OrderPriorityQueue with an invalid state for peeking, and verify that
   *     the correct exception is thrown
   * (3) call removeBest() on an OrderPriorityQueue with an invalid state for removing, and verify
   *     that the correct exception is thrown
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testErrors() {
    Order.resetIDGenerator();
    
    try {
      OrderPriorityQueue queue1 = new OrderPriorityQueue(-20);
    }
    catch(IllegalArgumentException e) {
      //System.out.println("caught!");
    }
    catch(Exception e) {
      return false;
    }
    
    try {
      OrderPriorityQueue queue1 = new OrderPriorityQueue(5);
      queue1.peekBest();
    }
    catch(NoSuchElementException e) {
      //System.out.println("caught2!");
    }
    catch(Exception e) {
      return false;
    }
    
    try {
      OrderPriorityQueue queue1 = new OrderPriorityQueue(5);
      queue1.removeBest();
    }
    catch(NoSuchElementException e) {
      //System.out.println("caught3!");
    }
    catch(Exception e) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Calls the test methods individually and displays their output
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("isEmpty: "+testIsEmpty());
    System.out.println("insert basic: "+testInsertBasic());
    System.out.println("percolate UP: "+testPercolateUp());
    System.out.println("insert advanced: "+testInsertAdvanced());
    System.out.println("percolate DOWN: "+testPercolateDown());
    System.out.println("peek/remove valid: "+testPeekRemove());
    System.out.println("error: "+testErrors());
  }

}
