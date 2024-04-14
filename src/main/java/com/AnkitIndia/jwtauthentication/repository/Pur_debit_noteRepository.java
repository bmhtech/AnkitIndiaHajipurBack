package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_debit_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note;

public interface Pur_debit_noteRepository extends JpaRepository<Pur_debit_note, Long>{
	
	@Query("select COUNT(id) from Pur_debit_note")
	String countId();
	
	@Query("select COUNT(id) from Pur_debit_note where debitnotedate =:dndate and debitnotetype =:dntype")
	String countDebitNote(@Param("dndate") String dndate,@Param("dntype") String dntype);
	
	/*
	
	
	
	
	
	
*/	
	@Query("SELECT p FROM Pur_debit_note p where p.modified_type = 'INSERTED' and p.company_id =:company and p.fin_year =:finyear ORDER BY p.debitnoteid DESC")
    public List<Pur_debit_note> findAllDNotes(@Param("company") String company,@Param("finyear") String finyear);
    

}
