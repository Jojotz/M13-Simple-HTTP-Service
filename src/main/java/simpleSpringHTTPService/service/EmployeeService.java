package simpleSpringHTTPService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simpleSpringHTTPService.exceptions.RecordNotFoundException;
import simpleSpringHTTPService.model.EmployeeEntity;
import simpleSpringHTTPService.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public List<EmployeeEntity> getAllEmployees() {
		
		System.out.println ("getAllEmployees");
		List<EmployeeEntity> result = (List<EmployeeEntity>) repository.findAll();
		
		if (result.size() > 0) {
			
			return result;
			
		} else {
			
			return new ArrayList<EmployeeEntity>();
			
		}
		
	}
	
	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
		
		System.out.println ("getEmployeeById");
		Optional<EmployeeEntity> employee = repository.findById(id);
		
		if (employee.isPresent()) {
			
			return employee.get();
			
		} else {
			
			throw new RecordNotFoundException ("No employee record exists for given id: " + id);
			
		}
	}
	
	
	
	public EmployeeEntity createOrUpdateEmployee (EmployeeEntity entity) {
		
		System.out.println ("createOrUpdateEmployee");
		
		//Create new employee
		if (entity.getId() == null) {
			
			entity = repository.save(entity);
			
			return entity;
		
		//Update existing employee
		} else {
			Optional<EmployeeEntity> employee= repository.findById(entity.getId());
			
			if (employee.isPresent()) {
				
				EmployeeEntity newEntity = employee.get();
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());
				newEntity.setJobRole(entity.getJobRole());
				
				newEntity = repository.save(newEntity);
				
				return newEntity;
							
			} else {
				
				entity = repository.save(entity);
				
				return entity;
			}
		}
	}
	
	public void deleteEmployeeById (Long id)  throws RecordNotFoundException {
		
		System.out.println ("deleteEmployeeById");
		Optional<EmployeeEntity> employee= repository.findById(id);
		if (employee.isPresent()) {
			repository.deleteById(id);
		} else {
			
			throw new RecordNotFoundException ("No employee record exists for given id: " + id);
		}
	}
	
	//Get employees by Job Role
	public List<EmployeeEntity> findEmployeesByjobRole (String jobRole) {
		
		return repository.findEmployeesByjobRole(jobRole);
	}
}
