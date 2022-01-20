package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.EmployeeType;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.EmployeeTypeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeTypeController {

	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	// get all employees
	@GetMapping("/employeeTypes")
	public List<EmployeeType> getAllEmployeeTypes(){
		return employeeTypeRepository.findAll();
	}		
	
	// create employee rest api
	@PostMapping("/employeeTypes")
	public EmployeeType createEmployee(@RequestBody EmployeeType employeeType) {
		return employeeTypeRepository.save(employeeType);
	}
	
	// get employee by id rest api
	@GetMapping("/employeeTypes/{id}")
	public ResponseEntity<EmployeeType> getEmployeeTypeById(@PathVariable Long id) {
		EmployeeType employeeType = employeeTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Type not exist with id :" + id));
		return ResponseEntity.ok(employeeType);
	}
	
	// update employee rest api
	@PutMapping("/employeeTypes/{id}")
	public ResponseEntity<EmployeeType> updateEmployeeType(@PathVariable Long id, @RequestBody EmployeeType employeeTypeDetails){
		EmployeeType employeeType = employeeTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee type does not exist with id :" + id));
		
		employeeType.setType(employeeTypeDetails.getType());
				
		EmployeeType updatedEmployeeType = employeeTypeRepository.save(employeeType);
		return ResponseEntity.ok(updatedEmployeeType);
	}
	
	// delete employee type rest api
	@DeleteMapping("/employeeTypes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployeeType(@PathVariable Long id){
		EmployeeType employeeType = employeeTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee type does not exist with id :" + id));
		
		employeeTypeRepository.delete(employeeType);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
