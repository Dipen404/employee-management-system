package com.ipen.ems.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ipen.ems.entities.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployee();
	void addEmployee(Employee employee);
	Employee getEmployeeById(Long id);
	void updateEmployee(Employee employee);
	void deleteEmployee(Long id);
	Page<Employee> showPaginated(int pageNo, int pageSize);//this service method does the pagination only.
	Page<Employee> showPaginatedAndSorted(int pageNo, int pageSize, String sortField, String sortDirection); //this does pagination and sorting
	

}
