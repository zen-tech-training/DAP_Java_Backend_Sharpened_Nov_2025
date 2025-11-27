package com.zensar.services;

import java.util.List;

import com.zensar.dto.Employee;

public interface EmployeeService {
	public Employee createEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public List<Employee> getEmployeesByExperience(double experience);
}
