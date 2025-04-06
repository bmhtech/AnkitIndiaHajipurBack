package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stack_maintain;

public interface Stack_maintainRepository extends JpaRepository<Stack_maintain, Long> {

	@Query("select COUNT(id) from Stack_maintain")
	String countId();
	
	@Query(value= "select * from stack_maintain w where w.modified_type = 'INSERTED' and w.stack_date =:currdate ORDER BY id ASC", nativeQuery=true)
	List<Map<String, Object>> stackMaintainList(@Param("currdate") String currdate);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Stack_maintain_details p SET p.modified_type =:stat WHERE p.stack_id = :stack_id AND p.modified_type='INSERTED'" )
    int updateStackMaintainDetails(@Param("stack_id") String stack_id,@Param("stat") String stat);
	
	@Query(value= "select * from stack_maintain_details w where w.modified_type = 'INSERTED' and w.stack_id =:stack_id ORDER BY id ASC", nativeQuery=true)
	List<Map<String, Object>> stackItemRetriveList(@Param("stack_id") String stack_id);
	
}