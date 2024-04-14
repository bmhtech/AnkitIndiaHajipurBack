package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_note_docs;

public interface Pur_return_note_docsRepository extends JpaRepository<Pur_return_note_docs, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_note_docs w SET w.modified_type ='UPDATED' WHERE w.purreturnnoteid = :purreturnnoteid")
    int updatePur_return_note_docs(@Param("purreturnnoteid") String purreturnnoteid);
	
	@Query( "select p from Pur_return_note_docs p where p.modified_type = 'INSERTED' AND p.purreturnnoteid = :purreturnnoteid")
	List<Pur_return_note_docs> getPur_return_note_docs(@Param("purreturnnoteid") String purreturnnoteid);
}
