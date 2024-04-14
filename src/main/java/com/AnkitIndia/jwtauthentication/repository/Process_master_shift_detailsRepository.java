package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Process_master_shift_details;

public interface Process_master_shift_detailsRepository extends JpaRepository<Process_master_shift_details, Long>{

	@Query( "select p from Process_master_shift_details p where p.modified_type ='INSERTED' and p.process_id =:process_id ORDER BY p.shiftno")
	List<Process_master_shift_details> getProcess_master_shift_details(@Param("process_id") String process_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Process_master_shift_details p SET p.modified_type ='UPDATED' WHERE p.process_id =:process_id")
    int updateProcess_master_shift_details(@Param("process_id") String process_id);
	
}
