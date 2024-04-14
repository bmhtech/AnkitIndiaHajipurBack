package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.AnkitIndia.jwtauthentication.model.Purchase_register_details;

public interface Purchase_register_detailsRepository extends JpaRepository<Purchase_register_details, Long>{
	@Query("select b from Purchase_register_details b where b.purchase_report =:purchase_report ")
	
	List<Purchase_register_details>getPurchaseRegister(@Param("purchase_report") String purchase_report);

}
