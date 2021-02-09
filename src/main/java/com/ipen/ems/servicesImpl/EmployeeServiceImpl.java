package com.ipen.ems.servicesImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ipen.ems.entities.Employee;
import com.ipen.ems.repositories.EmployeeRepository;
import com.ipen.ems.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployee() {
		return  employeeRepository.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee =null;
		if(optional.isPresent()) {
			employee=optional.get();
		}
		else {
			throw new RuntimeException("Employee is not present with id: "+id);
		}
		return employee;
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee emp = employeeRepository.getOne(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		//emp.setId(employee.getId());
		employeeRepository.save(emp);
		
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		
	}

	//this method is for pagination only
	@Override
	public Page<Employee> showPaginated(int pageNo, int pageSize) {
		Pageable pageable =  (Pageable) PageRequest.of(pageNo-1, pageSize);
		return employeeRepository.findAll(pageable);
	}

	//this method is for pagination and sorting
	@Override
	public Page<Employee> showPaginatedAndSorted(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		Pageable pageable =  (Pageable) PageRequest.of(pageNo-1, pageSize,sort);
		return employeeRepository.findAll(pageable);
		//return employeeRepository.findAll(sort); //this will only do the sorting without pagination
		
	}

}
