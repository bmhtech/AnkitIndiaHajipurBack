package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Terminations;

public interface Stock_Indent_TerminationsRepository extends JpaRepository<Stock_Indent_Terminations, Long >{
	
	@Query( "select a from Stock_Indent_Terminations a where a.indent_id = :indent_no and a.modified_type ='INSERTED' ")
	Stock_Indent_Terminations getStkIndTer(@Param("indent_no") String indent_no);

}
