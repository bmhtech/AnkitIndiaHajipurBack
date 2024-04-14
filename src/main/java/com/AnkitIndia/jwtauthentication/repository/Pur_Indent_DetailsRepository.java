package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Indent_Details;

public interface Pur_Indent_DetailsRepository extends JpaRepository<Pur_Indent_Details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Indent_Details w SET w.modified_type ='UPDATED' WHERE w.indent_id = :indent_id")
    int pur_Indent_Detailsupdate(@Param("indent_id") String indent_id);
	
	@Query("select p from Pur_Indent_Details p where p.indent_id = :Id and p.enquiry_status != '1' and p.modified_type = 'INSERTED' ORDER BY p.srl_no")
	List<Pur_Indent_Details> getPurIndentCNQUPList(@Param("Id") String Id);
	
	@Query("select p from Pur_Indent_Details p where p.modified_type = 'INSERTED' and p.indent_id = :indent_no ORDER BY p.srl_no")
	List<Pur_Indent_Details> getPurIndentDetailsList(@Param("indent_no") String indent_no);


}
