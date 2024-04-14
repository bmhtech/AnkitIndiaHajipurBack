package com.AnkitIndia.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_opening_stk;

public interface Item_opening_stkRepository extends JpaRepository<Item_opening_stk, Long>{

	@Query("select max(substring(transe_id ,4, length(transe_id))) from Item_opening_stk where transe_id like %:code% ")
	Optional<String> getItemOpenStkSeqId(@Param("code") String code);
	
}
