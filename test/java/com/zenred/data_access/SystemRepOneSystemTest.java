package com.zenred.data_access;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmos.hibernate.SystemRep;

public class SystemRepOneSystemTest {
	
	private MarshallSystems marshallSystems;
	List<SystemRep> systemsRepList = null;

	@Before
	public void setUp() throws Exception {
		marshallSystems = new MarshallSystems();
		systemsRepList = marshallSystems.getSystems();
	}

	@Test
	public void testFindSystemRep() {
		SystemRep systemRep = systemsRepList.get(0);
		SystemRep systemRepRenew = marshallSystems.getOneSystemRep(systemRep.getUcoordinate().toString(), systemRep.getVcoordinate().toString());
		System.out.println(systemRepRenew);
		assertNotNull(systemRep);
	}


}
