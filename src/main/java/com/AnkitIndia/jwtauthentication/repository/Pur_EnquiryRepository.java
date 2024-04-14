package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;

public interface Pur_EnquiryRepository extends JpaRepository<Pur_Enquiry, Long> {

	@Query("select COUNT(id) from Pur_Enquiry")
	String countId();
	
	@Query("select COUNT(id) from Pur_Enquiry where enquiry_date =:orderdate and enquiry_type =:type ")
	String countEnquiryOrder(@Param("orderdate") String orderdate,@Param("type") String type);

	@Query( "select e from Pur_Enquiry e where e.modified_type = 'INSERTED' ORDER BY e.enquiry_id DESC")
	List<Pur_Enquiry> findAllPurEnquirys();
	
	@Query( "select enquiry_no from Pur_Enquiry where service_type= :p and modified_type = 'INSERTED'")
	List<Pur_Enquiry> enquiry_no(@Param("p") String p);
	
	@Query( "select e from Pur_Enquiry e where e.modified_type = 'INSERTED' and e.quotation_status !='1' and e.fullfillment_type = :ccc")
	List<Pur_Enquiry> getPurEnquiryDDCList(@Param("ccc") String ccc);
	
	@Query( "select e from Pur_Enquiry e where e.modified_type = 'INSERTED' and e.enquiry_id =:enqid ")
	Pur_Enquiry getPurEnquiryDetails(@Param("enqid") String enqid);
	
	//@Query("select p from Pur_Enquiry_Service_Details p where p.enquiry_id = :Id and p.quotation_status !='1' and p.modified_type != 'DELETED' ")
	//@Query("select p from Pur_Enquiry e JOIN e.pur_Enquiry_Service_Details p WHERE p.enquiry_id = :Id and p.quotation_status !='1' and p.modified_type != 'DELETED' ")
	//@Query(value ="SELECT * FROM Pur_Enquiry e JOIN Pur_Enquiry_Service_Details p ON e.enquiry_id=p.enquiry_id WHERE p.enquiry_id =:Id AND p.quotation_status !='1' AND p.modified_type != 'DELETED' ",nativeQuery = true)
	//List<Pur_Enquiry> getPurEnquiryCNQUPList(String Id);
	
	
	//@Query("select p from Pur_Enquiry_Service_Details p where p.quotation_status !='1' and p.modified_type != 'DELETED' ")
	//@Query(value ="SELECT * FROM Pur_Enquiry e JOIN Pur_Enquiry_Service_Details p ON e.enquiry_id=p.enquiry_id WHERE p.quotation_status !='1' AND p.modified_type != 'DELETED' ",nativeQuery = true)
	//List<Pur_Enquiry> getPurEnqItemDtlsList();
	
	
	@Query( "select e from Pur_Enquiry e where e.modified_type = 'INSERTED' and e.quotation_status !='1' and e.enquiry_id =:enqid and e.fullfillment_type = :reftype and e.service_type =:itemtype and e.enquiry_status='Open' ")
	List<Pur_Enquiry> getPurEnquiryDDCSuppList(@Param("enqid") String enqid,@Param("reftype") String reftype,@Param("itemtype") String itemtype);
	
	@Query( "select e from Pur_Enquiry e where e.modified_type = 'INSERTED' and e.quotation_status !='1' and e.fullfillment_type = :reftype and e.enquiry_status='Open' and e.enquiry_type='Informal' ")
	List<Pur_Enquiry> getPurEnquiryInformal(@Param("reftype") String reftype);
	
}
