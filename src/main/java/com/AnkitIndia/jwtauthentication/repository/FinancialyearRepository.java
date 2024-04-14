package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.AnkitIndia.jwtauthentication.model.Financialyear;

public interface FinancialyearRepository  extends JpaRepository<Financialyear, Long> {
	
	@Query("select f from Financialyear f")
	List<Financialyear> getfinyearlist();
	

}
