package com.zenred.servlet;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TurnUVToNextController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String page = request.getParameter("PAGE");
		String systemId = request.getParameter("systemId");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("systemId", systemId);

		if(null == page){
			return new ModelAndView("Systems_detail_cluster2", map);
		}
		else{
			return new ModelAndView(page, map);
		}
	}

}
