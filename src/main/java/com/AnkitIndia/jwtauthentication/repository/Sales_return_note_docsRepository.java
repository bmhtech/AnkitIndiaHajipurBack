package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_return_note_docs;

public interface Sales_return_note_docsRepository extends JpaRepository<Sales_return_note_docs, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_docs w SET w.modified_type =:mstatus WHERE w.salesreturnnoteid = :salesreturnnoteid")
    int updateSales_return_note_docs(@Param("salesreturnnoteid") String salesreturnnoteid,@Param("mstatus") String mstatus);

	
	@Query( "select c from Sales_return_note_docs c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid = :salesreturnnoteid")
	List<Sales_return_note_docs> getSalesReturnNoteD(@Param("salesreturnnoteid") String salesreturnnoteid);
}
