package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation_Service;

public interface Pur_QuotationRepository extends JpaRepository<Pur_Quotation, Long> {

	@Query("select COUNT(id) from Pur_Quotation")
	String countId();
	
	@Query("select COUNT(id) from Pur_Quotation where quotation_date =:orderdate and quotation_type =:type ")
	String countQuotOrder(@Param("orderdate") String orderdate,@Param("type") String type);
	
	@Query("select p from Pur_Quotation p where p.modified_type ='INSERTED' ORDER BY p.quotation_id DESC ")
	List<Pur_Quotation> findAllPurQuotations();
	
	@Query("select p from Pur_Quotation_Service p where p.modified_type ='INSERTED' ORDER BY p.slno")
	List<Pur_Quotation> getPurQtyItemDtlsList();
	
	@Query("select p from Pur_Quotation p where p.modified_type ='INSERTED' and p.quotation_id = :quotationno ")
	Pur_Quotation purQuotDetails(@Param("quotationno") String quotationno);
	
	@Query("select p from Pur_Quotation p where p.modified_type ='INSERTED' and p.order_status != '1' and p.fullfillment_type = :ccc and p.quotation_status ='Finalise' ")
	List<Pur_Quotation> getPurQtyDDCList(@Param("ccc") String ccc);
	
	@Query("select p from Pur_Quotation p where p.modified_type ='INSERTED' and p.order_status != '1' and p.quotation_status ='Pending' ")
	List<Pur_Quotation> getPurQuotPrevList();
	
	@Query("select p from Pur_Quotation p where p.modified_type ='INSERTED' and p.order_status != '1' and p.fullfillment_type = :reftype and p.supplier_name =:suppid and p.quotation_service =:itemtype and p.quotation_status ='Finalise' ")
	List<Pur_Quotation> getPurQuotThruSuppList(@Param("reftype") String reftype,@Param("suppid") String suppid,@Param("itemtype") String itemtype);
	
	@Query("select p from Pur_Quotation_Service p where p.quotation_id = :Id  and p.modified_type ='INSERTED' ORDER BY p.slno")
	List<Pur_Quotation> getPurQtyCNQUPList(@Param("Id") String Id);
	
	@Query( "select p from Pur_Quotation_Service p where p.quotation_id = :code and p.modified_type ='INSERTED' ORDER BY p.slno " )
	List<Pur_Quotation> purQServRetriveList(@Param("code") String code);
	
	@Query( "select p from Pur_Quotation_Doc p where p.quotation_id = :code and p.modified_type ='INSERTED' ORDER BY p.id " )
	List<Pur_Quotation> purQDocRetriveList(@Param("code") String code);
	
	@Query("select p from Pur_Quotation_Broker p where p.quotation_id = :quot_id  and p.modified_type ='INSERTED' ORDER BY p.sl_no ")
	List<Pur_Quotation> getPurQuotBrokerDtls(@Param("quot_id") String quot_id);
	
	//p.modified_type ='INSERTED' and p.enquiry_status != '1' and
	//@Query( "select c.company_name from Company_master c where c.company_code = :ccode" )
	//String getCompCodeToCompName(String ccode);
	
	//@Query( "select c.company_code from Company_master c where c.company_name = :cname" )
	//String getCompNameToCompCode(String cname);
}