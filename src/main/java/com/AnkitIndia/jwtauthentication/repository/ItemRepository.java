package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master;

public interface ItemRepository extends PagingAndSortingRepository<Item_master, Long>{

	@Query("SELECT i FROM Item_master i WHERE i.company_id =:comp")
	Slice<Item_master> PaginationByJPQL(@Param("comp") String comp, Pageable pageable);
	
	@Query("SELECT i FROM Item_master i WHERE i.company_id =:comp")
	Page<Item_master> getTotalpages(@Param("comp") String comp, Pageable pageable);
	
}
