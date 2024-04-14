package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_musterroll_details;

public interface Pur_debit_note_musterroll_detailsRepository extends JpaRepository<Pur_debit_note_musterroll_details, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_debit_note_musterroll_details w SET w.modified_type ='UPDATED' WHERE w.debitnoteid = :debitnoteid")
    int updatePur_debit_note_musterroll_details(@Param("debitnoteid") String debitnoteid);
	
	@Query( "select p from Pur_debit_note_musterroll_details p where p.debitnoteid = :debitnoteid and p.modified_type ='INSERTED'" )
	List<Pur_debit_note_musterroll_details> getPur_debit_note_musterroll_details(@Param("debitnoteid") String debitnoteid);
}
