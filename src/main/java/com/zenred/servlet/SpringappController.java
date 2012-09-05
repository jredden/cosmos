package com.zenred.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
// import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zenred.data_access.MarshallSystems;

import cosmos.hibernate.SystemRep;

public class SpringappController extends MultiActionController {
	
	private static final Logger logger = Logger.getLogger(SpringappController.class);

	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// org.apache.log4j.BasicConfigurator.configure();
		logger.info("In com.zenred.servlet.SpringappController");
		 
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "hello ");
		map.put("content", new Date(System.currentTimeMillis()).toString());
		List<SystemRep> _systems_list = new MarshallSystems().getSystems();
		map.put("systems_list", _systems_list);		
		//response.getWriter().println("hello: " + System.currentTimeMillis());
		//return new ModelAndView("hello");
		// View view = (View)getApplicationContext().getBean("cosmosView");
		
		return new ModelAndView("solar", map);
	}

}
