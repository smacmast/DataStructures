import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Test;


public class MyMaxHeapTest {

	@Test
	public void testInsert() {
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.insert(5);
		assertEquals((Integer) 5, m.viewMax());
		m.insert(7);
		assertEquals((Integer) 7, m.viewMax());
		m.insert(2);
		assertEquals((Integer) 7, m.viewMax());
	}
	
	@Test
	public void testExtractMax() {
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.insert(5);
		assertEquals((Integer) 5, m.extractMax());
		m.insert(7);
		m.insert(2);
		assertEquals((Integer) 7, m.extractMax());
		assertEquals((Integer) 2, m.extractMax());
		assertTrue(m.isEmpty());
	}
	
	@Test
	public void testClearAndEmpty() {
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		assertTrue(m.isEmpty());
		m.insert(5);
		m.insert(7);
		m.insert(2);
		assertFalse(m.isEmpty());
		m.clear();
		assertTrue(m.isEmpty());
	}
	
	@Test
	public void testDelete() {
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.insert(5);
		assertEquals((Integer) 5, m.extractMax());
		m.insert(7);
		m.insert(2);
		assertFalse(m.isEmpty());
		m.clear();
		assertTrue(m.isEmpty());
	}
	
	@Test
	public void testUpdateKey(){
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.insert(5);
		m.updateKey(0, 7);
		assertEquals((Integer) 7, m.viewMax());
		m.insert(5);
		m.updateKey(1, 13);
		assertEquals((Integer) 13, m.viewMax());
	}

	@Test (expected = NoSuchElementException.class) 
	public void testExtractException1(){
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.extractMax();
	}
	
	@Test (expected = NoSuchElementException.class) 
	public void testDeleteMaxException(){
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.deleteMax();
	}
	
	@Test (expected = IndexOutOfBoundsException.class) 
	public void testDeleteException1(){
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.insert(5);
		m.delete(1);
	}
	
	@Test (expected = NoSuchElementException.class) 
	public void testDeleteException2(){
		MyMaxHeap<Integer> m = new MyMaxHeap<Integer>(new IntegerComparator());
		m.delete(-1);
	}
	
	private class IntegerComparator implements Comparator<Integer> {

	    public int compare(Integer a, Integer b) {
	        return a.intValue() - b.intValue();
	    }

	    public boolean equals(Object obj) {
	        return this.equals(obj);
	    }
	}
}
