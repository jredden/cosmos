package com.zenred.data_access;

import java.util.List;

import com.zenred.cosmos.SystemRepIF;

import cosmos.hibernate.SystemRep;

public class MarshallSystems extends AbstractDataAccess {

	public MarshallSystems() {
		init();
	}

	public List<SystemRep> getSystems() {
		SystemRepDAO _system_rep_dao = (SystemRepDAO) ctx
				.getBean("systemRepDAO");
		List<SystemRep> _system_rep_list = _system_rep_dao.findAll();
		return _system_rep_list;
	}

	public String getSystemRep(String udim, String vdim) {
		SystemRepDAO systemRepDao = (SystemRepDAO) ctx.getBean("systemRepDAO");
		String systemRepId = systemRepDao.findSystemRep(udim, vdim);
		return systemRepId;
	}
	
	public void writeSystemRep(SystemRepIF systemRepIF){
		SystemRepDAO systemRepDao = (SystemRepDAO) ctx.getBean("systemRepDAO");
		systemRepDao.writeSystemRep(systemRepIF);
	}
	
	
}
