package com.zensar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.dto.Employee;
import com.zensar.entity.one_to_one.EmployeeEntity;
import com.zensar.repo.EmployeeRepo;
import com.zensar.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public Employee createEmployee(Employee employee) {
		EmployeeEntity employeeEntity = employeeRepo.save(EmployeeUtil.convertEmployeeIntoEmployeeEntity(employee));
		return EmployeeUtil.convertEmployeeEntityIntoEmployee(employeeEntity);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return EmployeeUtil.convertEmployeeEntityListIntoEmployeeList(employeeRepo.findAll());
	}

	@Override
	public List<Employee> getEmployeesByExperience(double experience) {
		List<EmployeeEntity> employeeEntityList = employeeRepo.findByEmployeeByExperience(experience);
		return EmployeeUtil.convertEmployeeEntityListIntoEmployeeList(employeeEntityList);
	}

}
