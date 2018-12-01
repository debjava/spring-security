package com.ddlab.rnd.spring.resources;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddlab.rnd.spring.entity.Employee;

@RequestMapping(path="/greet")
@RestController()
public class GreetingController {

    @RequestMapping(value = "/hi",method = RequestMethod.GET,name="hi")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello";
    }
    
    @RequestMapping(value="/emp/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Employee getById( @PathVariable (value="id") int id ) {
    	Employee emp = new Employee();
    	emp.setId(id);
    	emp.setFirstName("Deb");
    	
    	return emp;
    }
}