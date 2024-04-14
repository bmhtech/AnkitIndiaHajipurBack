package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Vehicle_master_doc_details;

public interface Vehicle_master_doc_detailsRepository extends JpaRepository<Vehicle_master_doc_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_master_doc_details v SET v.modified_type =:mstatus WHERE v.vehicle_id = :vehicle_id ")
    int updateVehicle_master_doc_details(@Param("vehicle_id") String vehicle_id,@Param("mstatus") String mstatus);

}
