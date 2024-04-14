package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Indent_Doc;

public interface Pur_Indent_DocRepository extends JpaRepository<Pur_Indent_Doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Indent_Doc w SET w.modified_type ='UPDATED' WHERE w.indent_id = :indent_id")
    int pur_Indent_Docupdate(@Param("indent_id") String indent_id);
	
	@Query("select p from Pur_Indent_Doc p where p.indent_id = :indent_no and p.modified_type = 'INSERTED' ORDER BY p.id")
	List<Pur_Indent_Doc> indentOrderDocRetriveList(@Param("indent_no") String indent_no);

}
