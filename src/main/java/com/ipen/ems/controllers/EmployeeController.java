package com.ipen.ems.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipen.ems.entities.Employee;
import com.ipen.ems.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//Display list of employee with paginated data
	/*@GetMapping("/")
	public String viewHomePage(Model model) {
		//model.addAttribute("employeeList", employeeService.getAllEmployee());
		//return "index";
		return getAllPaginated(1, model);
	}*/
	//this method displays pagination and sorting both
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return getAllPaginatedAndSorted(1,"firstName","asc", model);
	}
	@GetMapping("/addemployee")
	public String showAddEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "add_employee";
		
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.addEmployee(employee);
		return "redirect:/";
	}
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable (value="id") Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
		
	}
	@PostMapping("/updateEmployee")
	public String saveEmployeeUpdated(@ModelAttribute("employee") Employee employee) {
		employeeService.updateEmployee(employee);
		return "redirect:/";
	}
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value="id") Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/";
	}
	
	//this does only the pagination works
	/*@GetMapping("/page/{pageNo}")
	public String getAllPaginated(@PathVariable (value ="pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.showPaginated(pageNo, pageSize);
		List<Employee> listEmployee = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("employeeList", listEmployee);
		return "index";
	}*/
	//this does the pagination and sorting
	@GetMapping("/page/{pageNo}")
	public String getAllPaginatedAndSorted(@PathVariable (value ="pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection,
			Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.showPaginatedAndSorted(pageNo, pageSize, sortField, sortDirection);
		List<Employee> listEmployee = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("employeeList", listEmployee);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("revSortDirection", sortDirection.equals("asc")?"desc":"asc");
		return "index";
	}

}
