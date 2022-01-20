package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.BillabilityType;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.EmployeeType;

@Repository
public interface BillabilityTypeRepository extends JpaRepository<BillabilityType, Long>{

}
