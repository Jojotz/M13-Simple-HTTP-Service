package simpleSpringHTTPService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simpleSpringHTTPService.model.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity, Long> {

	
}
