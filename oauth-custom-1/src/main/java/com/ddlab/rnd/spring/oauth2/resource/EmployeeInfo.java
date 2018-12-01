package com.ddlab.rnd.spring.oauth2.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.ddlab.rnd.spring.oauth2.entity.Employee;
import com.ddlab.rnd.spring.oauth2.service.EmpService;

@Path("/emp")
public class EmployeeInfo {
	
	@Autowired
	private EmpService empService;
	
	@Path("/info/{id}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Employee getEmpInfo( @PathParam("id") String empId ) {
		return empService.getEmployeeById(empId);
	}

}
