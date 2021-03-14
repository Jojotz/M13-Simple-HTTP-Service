package simpleSpringHTTPService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import simpleSpringHTTPService.model.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity, Long> {
	
	//@Query(value="select * from tbl_employees e where e.job_role like %:jobRole%", nativeQuery = true)  @Param("jobRole") 
	List<EmployeeEntity> findEmployeesByjobRole(String jobRole);
	
}
