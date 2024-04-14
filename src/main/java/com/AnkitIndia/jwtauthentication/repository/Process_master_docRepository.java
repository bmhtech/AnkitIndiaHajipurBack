package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Process_master_doc;

public interface Process_master_docRepository extends JpaRepository<Process_master_doc, Long>{
	
	@Query( "select p from Process_master_doc p where p.modified_type ='INSERTED' and p.process_id =:process_id ORDER BY p.id")
	List<Process_master_doc> getProcess_master_doc(@Param("process_id") String process_id);
	
	
	@Query( "select p from Process_master_doc p where p.modified_type ='INSERTED' and p.doc_pdf =:doc_pdf ORDER BY p.id")
	List<Process_master_doc> getdocumentListwithfile(@Param("doc_pdf") String doc_pdf);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Process_master_doc p SET p.modified_type ='UPDATED' WHERE p.process_id =:process_id")
    int updateProcess_master_doc(@Param("process_id") String process_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Process_master_doc p SET p.modified_type ='DELETED' WHERE p.id =:id")
	// @Query("DELETE  from Process_master_doc p  WHERE p.doc_pdf =:doc_pdf")
    int updatepdfdelete(@Param("id") long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Process_master_doc p SET p.modified_type ='DELETED' WHERE p.process_id =:process_id")
    int delete(@Param("process_id") String process_id);

}
