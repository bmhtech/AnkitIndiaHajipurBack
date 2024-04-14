package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;

public interface Pur_return_noteRepository extends JpaRepository< Pur_return_note, Long>{
	
	@Query("select COUNT(id) from Pur_return_note")
	String countId();
	
	@Query("select COUNT(id) from Pur_return_note where purreturnnotedate =:prdate")
	String countPRNOrder(@Param("prdate") String prdate);
	
	@Query("SELECT p FROM Pur_return_note p where p.modified_type = 'INSERTED' and p.company_id =:company and p.fin_year =:finyear ORDER BY p.purreturnnoteid DESC")
    public List<Pur_return_note> findAllRtnNotes(@Param("company") String company,@Param("finyear") String finyear);
    
    @Query( "select p from Pur_return_note p where p.modified_type = 'INSERTED' AND p.purreturnnoteid = :purreturnnoteid")
	Pur_return_note getPurRtnNoteDtls(@Param("purreturnnoteid") String purreturnnoteid);
    
    @Query("SELECT p FROM Pur_return_note p where p.modified_type = 'INSERTED' and p.businessunit =:bunit and p.purreturnnotedate <=:invdate and p.company_id =:company and p.fin_year =:finyear ")
    public List<Pur_return_note> getPurReturnNotes(@Param("bunit") String bunit,@Param("invdate") String invdate,@Param("company") String company,@Param("finyear") String finyear);
    
   /* 
	
	
		
	*/
	
	
}
