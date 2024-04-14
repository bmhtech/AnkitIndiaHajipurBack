package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_docs;

public interface Pur_debit_note_docsRepository extends JpaRepository<Pur_debit_note_docs, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_debit_note_docs w SET w.modified_type ='UPDATED' WHERE w.debitnoteid = :debitnoteid")
    int updatePur_debit_note_docs(@Param("debitnoteid") String debitnoteid);
	
	@Query( "select p from Pur_debit_note_docs p where p.debitnoteid = :debitnoteid and p.modified_type ='INSERTED'" )
	List<Pur_debit_note_docs> getPur_debit_note_docs(@Param("debitnoteid") String debitnoteid);

}
