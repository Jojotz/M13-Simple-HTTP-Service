package simpleSpringHTTPService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_EMPLOYEES")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="first_name")
	private String firstName;
	
	@Column (name="last_name")
	private String lastName;
		
	@Column (name="job_role")
	private String jobRole;
	
	@Column (name="salary")
	private double salary;
	
	public EmployeeEntity () {
		
	}
	
	public EmployeeEntity(Long id, String firstName, String lastName, String jobRole) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		CONTRACT contract = toContract(jobRole);
		this.jobRole = contract.getJob();
		this.salary = contract.getSalary();
				
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobRole() {
		return jobRole;
	}
		
	public void setJobRole(String jobRole) {
		CONTRACT contract = toContract(jobRole);
		this.jobRole = contract.getJob();
		this.salary = contract.getSalary();
	}
	
	public double getSalary() {
		return salary;
	}
			
	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", jobRole="
				+ jobRole + ", salary= " + salary + "]";
	}
	
	private CONTRACT toContract (String jobRole) {
		try {
			return CONTRACT.valueOf(jobRole.substring(0, 1).toUpperCase());
		} catch (Exception e) {
			System.out.println("Error: not a valid job role!");
			return CONTRACT.X;
		}
	}
	
	//4 Possible jobs: Employee, Coach, Manager, Boss
	private enum CONTRACT {
		
		E("Employee", 1200.0), C("Coach", 1400.0), M("Manager", 1600.0), B("Boss", 2000.0), X("Error", 0.0);
	
		private String job;
		private double salary;
		
		private CONTRACT (String job, double salary) {
			this.job = job;
			this.salary = salary;
		}
		
		public String getJob() {
			return job;
		}
		
		public double getSalary() {
			return salary;
		}			
	}	
}
