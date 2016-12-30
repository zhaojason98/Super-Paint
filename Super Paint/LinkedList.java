class LinkedList<T> {
    private int numberOfNodes = 0; 
    private ListNode<T> front = null;
    private ListNode<T> tail = null;
    
    // Returns true if the linked list has no nodes, or false otherwise.
    public boolean isEmpty() {
        return (front == null);
    }
    
    // Deletes all of the nodes in the linked list.
    // Note: ListNode objects will be automatically garbage collected by JVM.
    public void makeEmpty() {
        front = null;
        numberOfNodes = 0;
    }
    
    // Returns the number of nodes in the linked list
    public int size() {
        return numberOfNodes;
    }
    
    // Adds a node to the front of the linked list.
    public void addFront( T element ) {
        front = new ListNode<T>( element, front );
        numberOfNodes++;
    }
    
    // Removes a node from the front of the linked list (if there is one).
// Returns a reference to the data in the first node, or null if the list is empty.
    @SuppressWarnings("unchecked")
    public T removeFront() {
        T tempData;
        
        if (isEmpty()) 
            return null;
        
        tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }
    
    public T first() {
        if (isEmpty()) 
            return null;
        
        return front.getData();
    }
    
    // Returns true if the linked list contains a certain element, or false otherwise.
    @SuppressWarnings("unchecked")
    public boolean contains( T key ) {
        ListNode<T> searchNode;
        searchNode = front;
        while ( (searchNode != null) && (!key.equals(searchNode.getData())) ) {
            searchNode = searchNode.getNext();
        }
        return (searchNode != null);
    }
    @SuppressWarnings("unchecked")    
    public T indexValue(int index){
        ListNode<T> searchNode;
        searchNode = front;
        for(int i = 1;i<=index;i++){
            searchNode = searchNode.getNext();
            if (i == index){
                return searchNode.getData();
            }
        }
        return searchNode.getData();
    }
    
    // Insert a node in the list with a given key value
    @SuppressWarnings("unchecked")
    public void insert( Comparable key ) {
        ListNode<T> before = null;
        ListNode<T> after = front;
        ListNode<T> newNode;        
        
        // Traverse the list to find the ListNode before and after our insertion point.
        while ((after != null) && (key.compareTo(after.getData()) > 0)) {
            before = after;
            after = after.getNext();
        }
        
        // Create the new node with link pointing to after
        newNode = new ListNode<T>( (T)key, after);
        
        // Adjust front of the list or set before's link to point to new node, as appropriate
        if (before == null) {
            front = newNode;
        }
        else {
            before.setNext(newNode);
        }
        numberOfNodes++;
    }
    
    //Add to the end of the list
    public void addEnd(T element) {
      ListNode<T> tempTail = new ListNode<T>(element, null);
      if (tail != null) {
        tail.setNext(tempTail);
      }
      else {
        front = tempTail;
      }
      tail = tempTail;
      numberOfNodes++;
    }
    
    //Remove from end
    public void removeEnd() {
    }
// Return String representation of the linked list.
    @SuppressWarnings("unchecked")
    public String toString() {
        ListNode<T> node;
        String linkedList = "FRONT ==> ";
        
        node = front;
        while (node != null) {
            linkedList += "[ " + node.getData() + " ] ==> ";
            node = node.getNext();
        }
        
        return linkedList + "NULL";
    }
    
}