import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Spencer MacMaster
 * @description Implements a Doubly Linked List Data Structure.
 */
public class MyLinkedList<T> {
	private DataNode<T> front, back;
	private int size;
	
	/**
	 * Constructor of MyLinkedList Class
	 */
	public MyLinkedList() {
		this.front = null; this.back = null; this.size = 0;
	}

	/**
	 * Inserts an element into the end of the list
	 * @param data element to be stored into list
	 */
	public void add(T data){
		DataNode<T> node = new DataNode<T>(data);
		if(this.isEmpty()){ //start the list
			this.front = node;
			this.back = this.front;
		}else{ //Insert at end
			node.prev = this.back;
			this.back.next = node;
			this.back = node;
		}
		this.size++;
	}
	
	/**
	 * Inserts an element into the list at a specific index; 
	 * @param index location to insert data into the list
	 * @param data element to be stored into list
	 * @throws IndexOutOfBoundsException
	 */
	public void insert(int index, T data) throws IndexOutOfBoundsException{
		if(index < 0 || index > this.size){ throw new IndexOutOfBoundsException(); }

		if(index == 0){ this.addFirst(data); } //index is start of list
		else if(index == this.size){ this.add(data); } //index is end of list
		else{
			DataNode<T> temp = this.front, node = new DataNode<T>(data);;
			//find element prior to insert point
			for(int i = 1; i < index; ++i){ temp = temp.next; }
			node.prev = temp;
			node.next = temp.next;
			temp.next.prev = node;
			temp.next = node;
			this.size++;
		}

	}
	
	/**
	 * Inserts an element into the list at a the front; 
	 * @param data element to be stored into list
	 */
	public void addFirst(T data){
		DataNode<T> node = new DataNode<T>(data);
		if(this.isEmpty()){ //start the list
			this.front = node;
			this.back = this.front;
		}else{ //insert at front
			this.front.prev = node;
			node.next = this.front;
			this.front = node;
		}
		this.size++;
	}
	
	/**
	 * Removes everything from the list 
	 */
	public void clear(){ this.front = null; this.back = null; this.size = 0; }
	
	/**
	 * Searches the list for the first occurrence of the data
	 * @param data element search list for
	 * @return the index of the location if present else -1
	 */
	public int findElement(T data){
		int index = -1, i = 0;
		DataNode<T> temp = this.front;
		while(temp != null){
			if(temp.data.equals(data)){
				index = i;
				break;
			}
			temp = temp.next;
			++i;
		}
		return index;
	}
	
	/**
	 * Retrieves an element in the list at the specified location
	 * @param index location of data to retrieve
	 * @return the data at specified location
	 * @throws IndexOutOfBoundsException
	 */
	public T getElement(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size){ throw new IndexOutOfBoundsException(); }
		int i = 0;
		DataNode<T> temp = this.front;
		while(i < index){
			temp = temp.next;
			++i;
		}
		return temp.data;
	}
	
	/**
	 * Retrieves an element in the list at the front
	 * @return the data of the first element
	 * @throws NoSuchElementException
	 */
	public T getFront() throws NoSuchElementException{
		if(this.front == null){ throw new NoSuchElementException(); }
		return this.front.data;
	}
	
	/**
	 * Retrieves an element in the list at the back
	 * @return the data of the last element
	 * @throws NoSuchElementException
	 */
	public T getBack() throws NoSuchElementException{
		if(this.back == null){ throw new NoSuchElementException(); }
		return this.back.data;
	}
	
	/**
	 * Get the number of elements in the queue
	 * @return the number of elements
	 */
	public int getSize(){ return this.size; }
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * @param data data to be removed from list
	 * @throws NoSuchElementException
	 */
	public void removeElement(T data) throws NoSuchElementException {
		boolean found = false; int i = 0;
		DataNode<T> temp = this.front;
		while(temp != null){
			if(temp.data.equals(data)){ found = true; break; }
			temp = temp.next; i++;
		}
		if(!found){ throw new NoSuchElementException(); }
		else{
			if( i == 0){ this.removeFront(); } //element to delete is at the front of list
			else if(i == this.size -1){ this.removeBack(); } //element to delete is at the back of list
			else{ //element to delete is in the middle of list
				DataNode<T> prev = temp.prev;
				prev.next = temp.next;
				temp.next.prev = prev;
				this.size--;
			}
		}
	}
	
	/**
	 * Removes an element from a specific location in the list
	 * @param index location of element to be removed
	 * @throws IndexOutOfBoundsException
	 */
	public T remove(int index)  throws IndexOutOfBoundsException{
		if (index  >=  this.size || index < 0){ throw new IndexOutOfBoundsException(); } //index out of bounds
		int i = 0;
		DataNode<T> temp = this.front;
		while(temp != null){
			if(i == index){ break; }
			temp = temp.next;
			i++;
		}
		T data;
		if( i == 0){ data = this.removeFront(); } //element to delete is at the front of list
		else if(i == this.size -1){ data = this.removeBack(); } //element to delete is at the back of list
		else{ //element to delete is in the middle of list
			DataNode<T> prev = temp.prev;
			prev.next = temp.next;
			temp.next.prev = prev;
			this.size--;
			data = temp.data;
		}
		return data;
	}
		
	/**
	 * Removes an element from the front in the list
	 * @return data of the element that was removed
	 * @throws NoSuchElementException
	 */
	public T removeFront() throws NoSuchElementException{
		if(this.front == null){ throw new NoSuchElementException(); }
		T data =  this.front.data;
		this.front = this.front.next;
		if(this.front == null){ this.back = null; }
		this.size--;
		return data;
	}
	
	/**
	 * Removes an element from the back of the list
	 * @return data of the element that was removed
	 * @throws NoSuchElementException
	 */
	public T removeBack() throws NoSuchElementException{
		if(this.back == null){ throw new NoSuchElementException(); }
		T data =  this.front.data;
		this.back = this.back.prev;
		if(this.back == null){ this.front = null; }
		this.size--;
		return data;
	}
	
	/**
	 * Determines if the list is empty
	 * @return True if list is empty else false
	 */
	public boolean isEmpty(){
		return this.front == null;
	}
	
	/**
	 * Replaces the data at the index with the new data
	 * @param index location of element to replace
	 * @param data element to replace
	 * @throws IndexOutOfBoundsException
	 */
	public void set(int index, T data) throws IndexOutOfBoundsException{
		if (index  >=  this.size || index < 0){ throw new IndexOutOfBoundsException(); } //index out of bounds
		int i = 0;
		DataNode<T> temp = this.front;
		while(temp != null){
			if(i == index){ break; }
			temp = temp.next;
			i++;
		}
		temp.data = data;
	}

	/**
	 * Gives a object iterator of this instance;
	 * @return the iterator object
	 */
	public ListIterator<T> listIterator() {
		return new MyLinkedListIterator();
	}
	
	/**
	 * Gives a object iterator of this instance starting at the given index;
	 * @param index location to start the iterator
	 * @return the iterator object
	 * @throws IndexOutOfBoundsException
	 */
	public ListIterator<T> listIterator(int index) throws  IndexOutOfBoundsException {
		if (index  >=  this.size || index < 0){ throw new IndexOutOfBoundsException(); } //index out of bounds
		int i = 0;
		DataNode<T> temp = this.front;
		while(temp != null){
			if(i == index){ break; }
			temp = temp.next;
			i++;
		}
		return new MyLinkedListIterator(temp, index);
	}
	
	private class MyLinkedListIterator implements ListIterator<T> {
	   private DataNode<T> node;
	   private int index, lastRem;

	   /**
	    * Constructor for MyLinkedListIterator
	    */
	   public MyLinkedListIterator(){
		   this.node = front; this.index = 0; this.lastRem = -1;
	   }
	   
	   /**
	    * Constructor 2 for MyLinkedListIterator
	    * @param node node iterator starts at
	    * @param index index of current node
	    */
	   public MyLinkedListIterator(DataNode<T> node, int index){
		   this.node = node; this.index = index;this.lastRem = -1;
	   }

		/**
		 * Check if node has next element
		 * @return true if node has next element else false
		 */
		@Override
		public boolean hasNext() {
			return this.node.next != null;
		}

	    /**
	     * Checks if node has previous element
	     * @return true if node has previous else false
	     */
		@Override
		public boolean hasPrevious() {
			return this.index > 0;
		}
	    
		/**
		 * Iterate to previous element
		 * @return data of the element
		 */
		@Override
		public T previous(){
			T data = this.node.data;
			this.node = this.node.prev;
			index--;
			return data;
		}
	
		/**
		 * iterate to next element
		 * @return node.data
		 */
		@Override
		public T next(){
			T data = this.node.data;
			this.node = this.node.next;
			index++; this.lastRem = -1;
			return data;
		}

		/**
		 * Returns the index of next element
		 * @return Returns the index of next element or size if node is at the end of the list
		 */
		@Override
		public int nextIndex() {
			return index + 1;
		}

		/**
		 * Returns the index of previous element
		 * @return Returns the index of previous element or -1 if node is at the start of the list
		 */
		@Override
		public int previousIndex() {
			return index - 1;
		}
		
		/**
		 * Removes the current element from the list
		 * Can only be called once per iterator.next() call
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (this.lastRem == this.index){ throw new IllegalStateException(); }
			this.lastRem = this.index;
			MyLinkedList.this.remove(this.index);
		}

		/**
		 * Adds an element before the current element
		 * previous <-> new data <-> current <-> next
		 * @param data element to add
		 */
		@Override
		public void add(T data){
			MyLinkedList.this.insert(this.index, data);
			this.index++;
		}

		/**
		 * Sets the current element to the new value
		 * @param data element to replace current
		 */
		@Override
		public void set(T data){
			this.node.data = data;
		}
	}
	
}
