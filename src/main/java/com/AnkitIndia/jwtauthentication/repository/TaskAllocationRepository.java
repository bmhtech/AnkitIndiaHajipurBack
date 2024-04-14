package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.TaskAllocation;

public interface TaskAllocationRepository extends JpaRepository<TaskAllocation, Long>{

	@Query("select COUNT(id) from TaskAllocation")
	String countId();
	
	@Query(value="SELECT * FROM task_allocation WHERE modified_type='INSERTED' ORDER BY id",nativeQuery=true)
	List<Map<String,Object>> getAllocationlist();
	
	@Query(value="SELECT * FROM task_allocation WHERE modified_type='INSERTED' AND valid_upto>=:currdate AND alloted_to_array LIKE %:empcode% ORDER BY id",nativeQuery=true)
	List<Map<String, Object>> getAllocationUsernameWiselist(@Param("empcode") String empcode,@Param("currdate") String currdate);
	
	@Query( "select i from TaskAllocation i where i.allocation_id=:taskid and i.modified_type = 'INSERTED'")
	TaskAllocation getTaskName(@Param("taskid") String taskid);
	
	@Query(value="SELECT * FROM task_allocation WHERE modified_type='INSERTED' AND allocation_id=:taskid",nativeQuery=true)
	Map<String, Object> getTaskNameDetails(@Param("taskid") String taskid);
}
