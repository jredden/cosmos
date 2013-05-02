package com.zenred.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zenred.data_access.MarshallSystems;
import com.zenred.service.MarshalSystemDetails;
import com.zenred.visualization.SystemPlusSomeDetails;

import cosmos.hibernate.SystemRep;

public class SpringappController3 extends MultiActionController {

	static private Logger logger = LoggerFactory
			.getLogger(SpringappController3.class);
	static private String STARTU = "startu";
	static private String STARTV = "startv";
	static private int EXTENT_SIZE = 10;

	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.info("In com.zenred.servlet.SpringappController3");

		String startu = request.getParameter(STARTU);
		String startv = request.getParameter(STARTV);
		Map<String, Object> map = null;
		if (startu != null && startv != null) {
			Integer istartu = new Integer(startu);
			Integer istartv = new Integer(startv);
			Integer iendu = istartu + EXTENT_SIZE;
			Integer iendv = istartv + EXTENT_SIZE;
			systemExtents(startu.toString(), startv.toString(),
					iendu.toString(), iendv.toString());
		} else {
			map = allSystems();
		}

		return new ModelAndView("systems3", map);
	}

	private Map<String, Object> allSystems() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "hello ");
		map.put("content", new Date(System.currentTimeMillis()).toString());
		List<SystemRep> systemsRepList = new MarshallSystems().getSystems();
		List<SystemPlusSomeDetails> _systems_list = new MarshalSystemDetails()
				.addClustersAndStars(systemsRepList);
		map.put("systems_list", _systems_list);
		return map;

	}

	private Map<String, Object> systemExtents(String startu, String startv,
			String endu, String endv) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "hello ");
		map.put("content", new Date(System.currentTimeMillis()).toString());
		List<SystemRep> systemsRepList = new MarshallSystems()
				.getSystemSegments(startu, startv, endu, endv);
		List<SystemPlusSomeDetails> _systems_list = new MarshalSystemDetails()
				.addClustersAndStars(systemsRepList);
		map.put("systems_list", _systems_list);

		return map;
	}

}
