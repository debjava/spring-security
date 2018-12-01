package com.ddlab.rnd.spring.oauth2.service;

import org.springframework.stereotype.Component;

import com.ddlab.rnd.spring.oauth2.entity.Employee;

@Component("empServiceImpl")
public class EmpServiceImpl implements EmpService {

	@Override
	public Employee getEmployeeById(String id) {
		Employee emp = new Employee();
		emp.setEmpId(id);
		emp.setFirstName("Debadatta");
		emp.setLastName("Mishra");
		return emp;
	}

}
