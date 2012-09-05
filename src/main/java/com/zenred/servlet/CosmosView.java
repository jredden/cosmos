package com.zenred.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class CosmosView implements View {

	public String getContentType() {
		return null;
	}

	public void render(Map map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.getWriter().println(map.get("systems_list").toString());
	}

}
