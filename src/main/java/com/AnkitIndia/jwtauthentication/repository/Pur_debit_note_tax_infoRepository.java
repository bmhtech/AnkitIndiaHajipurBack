package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_tax_info;

public interface Pur_debit_note_tax_infoRepository extends JpaRepository<Pur_debit_note_tax_info, Long>{
	
	@Query( "select w from Pur_debit_note_tax_info w where w.debitnoteid = :debitnoteid and  w.modified_type = 'INSERTED'" )
	Pur_debit_note_tax_info getPur_debit_note_tax_info(@Param("debitnoteid") String debitnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_debit_note_tax_info w SET w.modified_type ='UPDATED' WHERE w.debitnoteid = :debitnoteid")
    int updatePur_debit_note_tax_info(@Param("debitnoteid") String debitnoteid);

}
