package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.hibernate.annotations.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;

public interface Pur_IndentRepository extends JpaRepository<Pur_Indent, Long> {
	
	@Query("select COUNT(id) from Pur_Indent")
	String countId();
	
	@Query("select COUNT(id) from Pur_Indent where indent_date =:orderdate and indent_type =:type ")
	String countIndentOrder(@Param("orderdate") String orderdate,@Param("type") String type);
	
	@Query("select p.indent_no from Pur_Indent p where p.indent_id =:indent_no ")
	String findIndentOrder(@Param("indent_no") String indent_no);
	
	@Query("select indent_no from Pur_Indent")
	List<Pur_Indent> getPurIndentCList();
	
	@Query("select indent_no,fin_year from Pur_Indent")
	List<Pur_Indent> getPurIndentCYList();
	
	@Query("SELECT p FROM Pur_Indent p where p.modified_type = 'INSERTED' ORDER BY p.indent_id DESC ")
	List<Pur_Indent> findPurIndents();
	
	@Query("select p from Pur_Indent p where p.modified_type = 'INSERTED' and p.enquiry_status != '1' and p.fullfillment_type = :ccc and p.ser_item_type =:itemtype and p.close='No' ")
	List<Pur_Indent> getPurIndentDDCList(@Param("ccc") String ccc,@Param("itemtype") String itemtype);
	
/*	
	
	
	
	*/
	
	@Query("select p from Pur_Indent p where p.modified_type = 'INSERTED' and p.indent_id = :indentno ")
	Pur_Indent getPurIndentDtls(@Param("indentno") String indentno);
	
}
