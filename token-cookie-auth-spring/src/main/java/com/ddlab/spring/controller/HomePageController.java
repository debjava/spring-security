package com.ddlab.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    
    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public ModelAndView getHomePage() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String viewName = "home";
    	if( auth.getName().equals("admin"))
    		viewName = "admin";
    	ModelAndView model = new ModelAndView();
    	model.setViewName(viewName);
        return model;
    }
}
