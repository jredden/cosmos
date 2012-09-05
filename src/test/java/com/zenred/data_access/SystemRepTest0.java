package com.zenred.data_access;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.data_access.MarshallSystems;
import cosmos.hibernate.SystemRep;

public class SystemRepTest0 {
	
	private MarshallSystems marshallSystems;

	@Before
	public void setUp() throws Exception {
		marshallSystems = new MarshallSystems();
	}

	@Test
	public void testFindSystemRep() {
		String systemRep = marshallSystems.getSystemRep("100003", "100003");
		System.out.println(systemRep);
		assertNotNull(systemRep);
	}
	@Test
	public void testFindSystemRepNo() {
		String systemRep = marshallSystems.getSystemRep("10000", "10000");
		System.out.println(systemRep);
		assertNull(systemRep);
	}

}
