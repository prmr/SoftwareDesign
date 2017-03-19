package module04;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for Math.abs and Math.min. The methods
 * are overloaded to support different primitive types.
 * These tests are only for the integer version.
 * 
 * Note how each different class of input is 
 * tested in a different unit test.
 */
public class TestMath
{
	@Test
	public void testAbsZero()
	{
		assertEquals(0, Math.abs(0));
	}
	
	@Test
	public void testAbsPositive()
	{
		assertEquals(5, Math.abs(5));
	}
	
	@Test
	public void testAbsNegative()
	{
		assertEquals(5, Math.abs(-5));
	}
	
	@Test
	public void testAbsMaxInt()
	{
		assertEquals(Integer.MAX_VALUE, Math.abs(Integer.MAX_VALUE));
	}
	
	/*
	 * If the oracle seems wrong, see:
	 * https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#abs-int-
	 */
	@Test
	public void testAbsMinInt()
	{
		assertEquals(Integer.MIN_VALUE, Math.abs(Integer.MIN_VALUE));
	}

	@Test
	public void testMinFirst()
	{
		assertEquals(1, Math.min(1, 2));
		assertEquals(-2, Math.min(-2, -1));
		assertEquals(Integer.MIN_VALUE, Math.min(Integer.MIN_VALUE, 0));
	}
	
	@Test
	public void testMinSecond()
	{
		assertEquals(1, Math.min(2, 1));
		assertEquals(-2, Math.min(-1, -2));
		assertEquals(Integer.MIN_VALUE, Math.min(0, Integer.MIN_VALUE));
	}
}
