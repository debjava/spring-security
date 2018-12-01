package org.springsource.oauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CVController {

	private CVService cvService;
	
	
	
	@RequestMapping(value="/cv")
	public ModelAndView getCV() {
		String cv = cvService.getCVContent();
		
		
//		String cvURL = "http://localhost:8080/AuthServer/oauth/tokenManagment";
//		String cv = restTemplate.getForObject(URI.create(cvURL), String.class);
		
		
//		String cv = "OKKKKKKKKKKKKK";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("cv", cv);
		ModelAndView modelAndView = new ModelAndView("show", params);
		return modelAndView;
		
	}

	
	public CVService getCvService() {
		return cvService;
	}

	@Autowired
	public void setCvService(CVService cvService) {
		this.cvService = cvService;
	}
	
}
