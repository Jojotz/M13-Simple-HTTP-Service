package simpleSpringHTTPService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import simpleSpringHTTPService.exceptions.RecordNotFoundException;
import simpleSpringHTTPService.model.EmployeeEntity;
import simpleSpringHTTPService.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	//Gets all employees
	@RequestMapping
	public String getAllEmployees (Model model) {
		
		System.out.println("getAllEmployees");		
		List<EmployeeEntity> list = service.getAllEmployees();		
		model.addAttribute ("employees", list);		
		return "list-employees";
	}
	
	//Gets an employee by Id
	@GetMapping ("/{id}")
	public String findEmployeeById (Model model, Long id) throws RecordNotFoundException {
		System.out.println("findEmployeeById" + id);
		model.addAttribute("employees", service.getEmployeeById(id));
				
		return "list-employees";
	}
	
	//Updates an employee by Id
	@RequestMapping (path= {"/edit", "/edit/{id}"})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		
		System.out.println("editEmployeeById" + id);
		if (id.isPresent()) {
			EmployeeEntity entity = service.getEmployeeById(id.get());
			model.addAttribute ("employee", entity);
		} else {
			model.addAttribute("employee", new EmployeeEntity());
		}
		
		return "add-edit-employee";
	}
		
	//Deletes an employee by Id
	@RequestMapping(path= "/delete/{id}")
	public String deleteEmployeeById (Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		
		System.out.println("deleteEmployeeById" + id);
		
		service.deleteEmployeeById(id);
		
		return "redirect:/";
		
	}
	
	//Creates or updates an employee
	@RequestMapping(path= "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee (EmployeeEntity employee) {
		
		System.out.println("createOrUpdateEmployee");
		
		service.createOrUpdateEmployee(employee);
		
		return "redirect:/";
	}
	
	//Finds all employees by job role
	@GetMapping ("/findEmployeesByjobRole")
	public String findEmployeesByjobRole (Model model, String jobRole) {
		
		if (jobRole !=null) {
			
			model.addAttribute("employees", service.findEmployeesByjobRole(jobRole));
		
		} else {
			
			model.addAttribute("employees", service.getAllEmployees());
		}
		
		return "list-employees";
		
	}
	
}
