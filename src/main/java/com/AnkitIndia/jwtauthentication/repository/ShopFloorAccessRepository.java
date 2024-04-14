package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.ShopFloorAccess;

public interface ShopFloorAccessRepository extends JpaRepository<ShopFloorAccess, Long>{

	@Query("select COUNT(id) from ShopFloorAccess")
	String countId();
	
	@Query("select p from ShopFloorAccess p where p.modified_type ='INSERTED' and p.fin_year=:finyear ORDER BY p.accessid DESC")
	List<ShopFloorAccess> getAccesslist(@Param("finyear") String finyear);
}
