package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.TaskProgress;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long>{

	@Query("select COUNT(id) from TaskProgress")
	String countId();
	
	@Query(value="SELECT * FROM task_progress WHERE modified_type='INSERTED' ORDER BY id",nativeQuery=true)
	List<Map<String,Object>> getProgresslist();
	
	@Query(value="SELECT * FROM task_progress WHERE modified_type='INSERTED' AND date>=:fromdate AND date<=:todate ORDER BY id",nativeQuery=true)
	List<Map<String,Object>> getTaskProgressReport(@Param("fromdate") String frpmdate,@Param("todate") String todate);
	
}
