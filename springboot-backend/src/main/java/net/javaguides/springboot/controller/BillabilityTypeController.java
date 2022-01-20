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
import net.javaguides.springboot.model.BillabilityType;
import net.javaguides.springboot.repository.BillabilityTypeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class BillabilityTypeController {

	@Autowired
	private BillabilityTypeRepository billabilityTypeRepository;
	
	// get all employees
	@GetMapping("/billabilityTypes")
	public List<BillabilityType> getAllBillabilityTypes(){
		return billabilityTypeRepository.findAll();
	}		
	
	// create employee rest api
	@PostMapping("/billabilityTypes")
	public BillabilityType createBillability(@RequestBody BillabilityType billabilityType) {
		return billabilityTypeRepository.save(billabilityType);
	}
	
	// get billability by id rest api
	@GetMapping("/billabilityTypes/{id}")
	public ResponseEntity<BillabilityType> getBillabilityTypeById(@PathVariable Long id) {
		BillabilityType billabilityType = billabilityTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Billability Type not exist with id :" + id));
		return ResponseEntity.ok(billabilityType);
	}
	
	// update billability rest api
	@PutMapping("/billabilityTypes/{id}")
	public ResponseEntity<BillabilityType> updateBillabilityType(@PathVariable Long id, @RequestBody BillabilityType billabilityTypeDetails){
		BillabilityType billabilityType = billabilityTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Billability type does not exist with id :" + id));
		
		billabilityType.setType(billabilityTypeDetails.getType());
				
		BillabilityType updatedBillabilityType = billabilityTypeRepository.save(billabilityType);
		return ResponseEntity.ok(updatedBillabilityType);
	}
	
	// delete employee type rest api
	@DeleteMapping("/billabilityTypes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBillabilityType(@PathVariable Long id){
		BillabilityType billabilityType = billabilityTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Billability type does not exist with id :" + id));
		
		billabilityTypeRepository.delete(billabilityType);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
