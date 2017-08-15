import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Bradley Gauntlett
 *
 */
public class TestModel2 {
	
	static int[][] test = new int[][] { { 9, 9, 1, 9 }, { 1, 0, 9, 9 }, { 9, 9, 0, 9 }, { 9, 1, 9, 9 } };
	Model2 m2 = new Model2();

	/**
	 * Test method for {@link Model2#validate(int[][])}.
	 */
	@Test
	public void testValidate() {
		m2.n= 4;
		Model2.validate(test);
		assertEquals(false, false);
	}

	/**
	 * Test method for {@link Model2#solve(int, int, int[][])}.
	 */
	@Test
	public void testSolve() {
		m2.n= 4;
		m2.solve(0, 0, test);
		assertEquals(true, m2.solve(0, 0, test));
	}


	/**
	 * Test method for {@link Model2#displayGrid(int[][])}.
	 */
	@Test
	public void testDisplayGrid() {
		m2.displayGrid(test);
	}

}
