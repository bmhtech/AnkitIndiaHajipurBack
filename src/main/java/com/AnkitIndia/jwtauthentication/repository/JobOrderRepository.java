package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.AnkitIndia.jwtauthentication.model.JobOrder;

public interface JobOrderRepository extends JpaRepository<JobOrder, Long> {
	
	@Query("Select count(id) from JobOrder")
	String countjoborder();

}
