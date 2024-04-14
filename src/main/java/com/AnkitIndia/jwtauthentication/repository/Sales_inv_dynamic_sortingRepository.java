package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.AnkitIndia.jwtauthentication.model.Sales_inv_dynamic_sorting;

@Transactional
public interface Sales_inv_dynamic_sortingRepository extends JpaRepository<Sales_inv_dynamic_sorting, Long>{

	@Query("select s from Sales_inv_dynamic_sorting s where s.modified_type != 'DELETED'")
	List<Sales_inv_dynamic_sorting> getSalesSortDynList();
	
}
