package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_bp_details;

public interface Pur_debit_note_bp_detailsRepository extends JpaRepository<Pur_debit_note_bp_details, Long>{

	
	@Query( "select w from Pur_debit_note_bp_details w where w.debitnoteid = :debitnoteid and  w.modified_type = 'INSERTED'" )
	Pur_debit_note_bp_details getPur_debit_note_bp_details(@Param("debitnoteid") String debitnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_debit_note_bp_details w SET w.modified_type ='UPDATED' WHERE w.debitnoteid = :debitnoteid")
    int updatePur_debit_note_bp_details(@Param("debitnoteid") String debitnoteid);
}
