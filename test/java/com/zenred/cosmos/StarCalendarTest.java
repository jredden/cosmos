package com.zenred.cosmos;

import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StarCalendarTest extends TestCase {
	public StarCalendarTest(String test_name) {
		super(test_name);
	}

	public static Test suite() {
		return new TestSuite(StarCalendarTest.class);
	}

	public void testStarCalendar() {
		assertTrue(true);
	}
}
