package com.ddlab.rnd.spring.oauth2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddlab.rnd.spring.oauth2.models.User;
import com.ddlab.rnd.spring.oauth2.services.DataService;

/**
 * @author Nagesh.Chauhan
 *
 */
@Controller
@RequestMapping("/api/users")
public class RestController {

	@Autowired
	DataService dataService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<User> list() {
		return dataService.getUserList();

	}
}
