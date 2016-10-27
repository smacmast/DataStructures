import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Test;


public class MyBinaryTreeTest {

	@Test
	public void testInsert() {
		MyBinaryTree<Integer> m = new MyBinaryTree<Integer>(new MyIntComparator());
		
		m.insert(6); m.insert(2); m.insert(7);
		m.insert(1); m.insert(4); m.insert(3);
		m.insert(5); m.insert(9); m.insert(8); 
		assertEquals(m.inOrderRep().trim(), "1 2 3 4 5 6 7 8 9".trim());
		assertEquals(m.preOrderRep().trim(), "6 2 1 4 3 5 7 9 8".trim());
		assertEquals(m.postOrderRep().trim(), "1 3 5 4 2 8 9 7 6".trim());
	}

	@Test
	public void testContains() {
		MyBinaryTree<String> m = new MyBinaryTree<String>(new MyStringComparator());
		
		m.insert("F"); m.insert("B"); m.insert("G");
		m.insert("A"); m.insert("D"); m.insert("C");
		m.insert("E"); m.insert("I"); m.insert("H"); 
		assertTrue(m.contains("F"));
		assertTrue(m.contains("A"));
		assertTrue(m.contains("I"));
		assertTrue(m.contains("C"));
		assertFalse(m.contains("X"));
		assertFalse(m.contains("W"));
	}

	@Test
	public void testSizeClearEmpty() {
		MyBinaryTree<Integer> m = new MyBinaryTree<Integer>(new MyIntComparator());
		
		assertEquals(0, m.getSize());
		assertTrue(m.isEmpty());
		m.insert(6); m.insert(2); m.insert(7);
		assertEquals(3, m.getSize());
		assertFalse(m.isEmpty());
		
		m.clear();
		assertEquals(0, m.getSize());
		assertTrue(m.isEmpty());
		m.insert(5); m.insert(1);
		assertEquals(2, m.getSize());
		assertFalse(m.isEmpty());
		
		m.clear();
		assertTrue(m.isEmpty());
		
	}

	@Test
	public void testDelete1() {
		MyBinaryTree<Integer> m = new MyBinaryTree<Integer>(new MyIntComparator());
		
		//delete middle element with two children
		m.insert(6); m.insert(2); m.insert(7); m.insert(1); m.insert(3);
		assertEquals(m.preOrderRep().trim(), "6 2 1 3 7".trim());
		m.delete(2);
		assertEquals(m.preOrderRep().trim(), "6 1 3 7".trim());
		
		m.clear();
		//delete middle element with left child only
		m.insert(6); m.insert(2); m.insert(7); m.insert(1);
		assertEquals(m.preOrderRep().trim(), "6 2 1 7".trim());
		m.delete(2);
		assertEquals(m.preOrderRep().trim(), "6 1 7".trim());

		m.clear();
		//delete middle element with right child only
		m.insert(6); m.insert(2); m.insert(7); m.insert(3);
		assertEquals(m.preOrderRep().trim(), "6 2 3 7".trim());
		m.delete(2);
		assertEquals(m.preOrderRep().trim(), "6 3 7".trim());
		
		m.clear();
		//delete middle element without children
		m.insert(6); m.insert(2); m.insert(7);
		assertEquals(m.preOrderRep().trim(), "6 2 7".trim());
		m.delete(2);
		assertEquals(m.preOrderRep().trim(), "6 7".trim());
	}
	
	@Test
	public void testDelete2() {
		MyBinaryTree<Integer> m = new MyBinaryTree<Integer>(new MyIntComparator());
		
		//delete root with two children
		m.insert(6); m.insert(2); m.insert(7);
		assertEquals(m.preOrderRep().trim(), "6 2 7".trim());
		m.delete(6);
		assertEquals(m.preOrderRep().trim(), "2 7".trim());
		
		m.clear();
		//delete root with left child only
		m.insert(6); m.insert(2);
		assertEquals(m.preOrderRep().trim(), "6 2".trim());
		m.delete(6);
		assertEquals(m.preOrderRep().trim(), "2".trim());

		m.clear();
		//delete root with right child only
		m.insert(6); m.insert(7);
		assertEquals(m.preOrderRep().trim(), "6 7".trim());
		m.delete(6);
		assertEquals(m.preOrderRep().trim(), "7".trim());
		
		m.clear();
		//delete root without children
		m.insert(6);
		assertEquals(m.preOrderRep().trim(), "6".trim());
		m.delete(6);
		assertEquals(m.preOrderRep().trim(), "".trim());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testDeleteException(){
		MyBinaryTree<Integer> m = new MyBinaryTree<Integer>(new MyIntComparator());
		m.insert(6); m.insert(2); m.insert(7);
		m.delete(1); //1  not in tree should error
	}

	private static class MyIntComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return  arg0.intValue() < arg1.intValue() ? -1 : arg0.intValue() == arg1.intValue() ? 0 : 1;
		}
		
	}
	
	private static class MyStringComparator implements Comparator<String>{
		@Override
		public int compare(String arg0, String arg1) {
			return  arg0.compareTo(arg1);
		}
		
	}
	
}
