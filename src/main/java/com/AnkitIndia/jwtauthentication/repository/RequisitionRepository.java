package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Requisition;


public interface RequisitionRepository extends JpaRepository<Requisition, Long>{

	
	
	@Query("Select count(id) from Requisition")
	String countrequisition();
	
	
	@Query(value="Select r.id,r.requisitionno,r.business_unitname,r.requestedbyname,r.shop_floorname,r.requesteddate,(case when r.approvedbyname ='NA' then '' else r.approvedbyname end), CASE  WHEN r.approvedbyname ='NA' THEN false ELSE true END,r.reject from requisition r where r.modified_type ='INSERTED'",nativeQuery=true)
	List<Object[]> getdetails();
	
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE from Requisition w set  w.approvedby = :approvedby , w.approvedbyname = :approvedbyname,w.approvedon=:ldt WHERE w.id = :id and  w.modified_type ='INSERTED' ")
    int changeapprove(@Param("id") long id,@Param("approvedby") String approvedby,@Param("approvedbyname") String approvedbyname,@Param("ldt") LocalDateTime ldt);
	
	
	@Query("select r from Requisition r where modified_type ='INSERTED' and r.approvedbyname !='NA' and r.shop_floor=:shopfloor")
	List<Requisition> getRequisitionnumberapprove(@Param("shopfloor") String shopfloor);
	
	@Query("select r from Requisition r where modified_type ='INSERTED' and r.requisitionno =:requisitionno")
	Requisition getRequisition(@Param("requisitionno") String requisitionno);
	
}
