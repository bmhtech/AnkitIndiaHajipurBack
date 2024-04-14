package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Employee_master;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeMasterRepository extends JpaRepository<Employee_master, Long>{
	
	@Query("select max(substring(emp_id , 4, length(emp_id))) from Employee_master where emp_id like %:prefix%")
	Optional<String> countId(@Param("prefix") String prefix);
	
	@Query( "select e from Employee_master e where e.modified_type != 'DELETED' ")
	List<Employee_master> getEmployeeName();
	
	@Query( "select e from Employee_master e where e.modified_type != 'DELETED' and e.emp_id =:empid")
	Employee_master getEmployee(@Param("empid") String empid);
	
	@Query( "select e from Employee_master e where e.id =:id")
	Employee_master getEmployeeDtls(@Param("id") long id);
	
	@Query("select max(substring(emp_code , 8, length(emp_code))) from Employee_master where emp_code like %:code% and company_id =:company ")
	String getEmployeeSequenceId(@Param("code") String code,@Param("company") String company);

	@Query( "select e.emp_id,UPPER(e.emp_name) from Employee_master e where e.modified_type = 'INSERTED' and  e.company_id =:company_id ")
	List<Object[]> getemployees(@Param("company_id") String company_id);
	
	
	@Query( "select e from Employee_master e where e.modified_type = 'INSERTED' and e.emp_username =:username")
	Employee_master getEmployeebyuser(@Param("username") String username);
	
	@Query(value="SELECT * FROM employee_master e WHERE e.modified_type = 'INSERTED' AND e.user_role_id='RL00001' AND e.company_id =:company", nativeQuery = true)
	List<Map<String, Object>> employeeAdminNamesList(@Param("company") String company);
}
