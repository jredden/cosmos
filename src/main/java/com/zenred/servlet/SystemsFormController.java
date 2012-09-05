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
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
// import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zenred.data_access.MarshallSystems;
import cosmos.hibernate.SystemRep;

public class SystemsFormController extends SimpleFormController {
	
	private static final Logger logger = Logger.getLogger(SystemsFormController.class);
	
	public SystemsFormController(){
		setCommandClass(SystemRep.class);
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws ServletException, IOException {
		List<SystemRep> _systems_list = new MarshallSystems().getSystems();
		return new ModelAndView("cosmos-index", "systems_list", _systems_list );
	}

}
