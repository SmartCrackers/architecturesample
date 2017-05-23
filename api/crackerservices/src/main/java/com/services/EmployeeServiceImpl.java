package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.models.Employee;

import com.repositories.EmployeeRepository;

/**
 * @author RITESH SINGH
 *
 */
@Service("employeeService")
@ComponentScan("com.repositories")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier("employeeRepository")
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		
		if(employee != null){
			return employeeRepository.save(employee);
		}else
			return null;
	}

	@Override
	public Long count() {
		
		return employeeRepository.count();
	}

	@Override
	public List<Employee> getEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmployees(Sort sort) {
		
		return employeeRepository.findAll(sort);
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		
		return employeeRepository.findOne(employeeId);
	}
}
