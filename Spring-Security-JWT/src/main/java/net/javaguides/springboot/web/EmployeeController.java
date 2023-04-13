package net.javaguides.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@CrossOrigin("*")
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/home")
	public String sayHello() {
		return "hello";
	}
	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		List<Employee> employees=empService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employeeDetails";
	}
	
	@GetMapping("/add-employee")
	public String showEmployeeForm() {
		return "addEmployee";
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable Integer id) {
		return empService.getEmployee(id);
	}
	
	@ModelAttribute("employee")
	public Employee employee() {
	    return new Employee();
	}
	@PostMapping("/employees")
	public String addEmployee(@ModelAttribute("employee") Employee employee) {
		empService.addEmployee(employee);
		return "redirect:/employees";
	}
	@GetMapping("/edit-employee/{id}")
	public String updateEmployee(@PathVariable  Integer id,Model model) {
	    Employee employee=new Employee();
	    employee=empService.getEmployee(id);
		model.addAttribute("employee",employee);
		return "update_employee";
	}
	
	@GetMapping("/delete-employee/{id}")
	public String deleteEmployee(@PathVariable  Integer id) {
		empService.deleteEmployee(id);
		return "redirect:/employees";
	}
	
	
}

