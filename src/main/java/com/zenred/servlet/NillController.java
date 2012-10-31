package com.zenred.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class NillController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String page = request.getParameter("PAGE");
		if(null == page){
			return new ModelAndView("fish");
		}
		else{
			return new ModelAndView(page);
		}
	}

}
