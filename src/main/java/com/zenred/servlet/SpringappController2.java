package com.zenred.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zenred.data_access.MarshallSystems;
import com.zenred.service.MarshalSystemDetails;
import com.zenred.visualization.SystemPlusSomeDetails;

import cosmos.hibernate.SystemRep;
@Deprecated
public class SpringappController2 extends MultiActionController {
	
	private static final Logger logger = Logger.getLogger(SpringappController2.class);

	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// org.apache.log4j.BasicConfigurator.configure();
		logger.info("In com.zenred.servlet.SpringappController2");
		 
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "hello ");
		map.put("content", new Date(System.currentTimeMillis()).toString());
		List<SystemRep> systemsRepList = new MarshallSystems().getSystems();
		List<SystemPlusSomeDetails> _systems_list = new MarshalSystemDetails().addClustersAndStars(systemsRepList);
		map.put("systems_list", _systems_list);		
		//response.getWriter().println("hello: " + System.currentTimeMillis());
		//return new ModelAndView("hello");
		// View view = (View)getApplicationContext().getBean("cosmosView");
		
		return new ModelAndView("systems", map);
	}

}
