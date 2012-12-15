package com.zenred.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zenred.data_access.MarshallSystems;

import cosmos.hibernate.SystemRep;
@Deprecated
public class SystemsFormController2 extends MultiActionController {
	
	private static final Logger logger = Logger.getLogger(SystemsFormController2.class);
	
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws ServletException, IOException {
		List<SystemRep> _systems_list = new MarshallSystems().getSystems();
		Map<String, List> map = new HashMap<String, List>();
		map.put("systems_list", _systems_list);
		return new ModelAndView("systems-index", map);

	}

}
