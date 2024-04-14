package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_delv_to;

public interface Cust_bussiness_partner_delv_toRepository extends JpaRepository<Cust_bussiness_partner_delv_to, Long>{
	
	@Query( "select c from Cust_bussiness_partner_delv_to c where c.cp_Id = :custid and c.modified_type = 'INSERTED'")
	Cust_bussiness_partner_delv_to getCustTransporters(@Param("custid") String custid);
	
	@Query( "select c from Cust_bussiness_partner_delv_to c where c.cp_Id = :custid and c.modified_type = 'INSERTED'")
	List<Cust_bussiness_partner_delv_to> getCustomerTransporters(@Param("custid") String custid);
	
	@Query( "select c from Cust_bussiness_partner_delv_to c where c.cp_Id =:cbp_id and c.b_unit_name = :b_unit_name and c.modified_type = 'INSERTED'")
	Cust_bussiness_partner_delv_to getCustDelvFromAdd(@Param("cbp_id") String cbp_id,@Param("b_unit_name") String b_unit_name);
	
	@Modifying
    @Query("UPDATE Cust_bussiness_partner_delv_to d SET d.transport_own ='Yes',d.transporters =:transid WHERE d.cp_Id = :custid and d.modified_type = 'INSERTED'")
    int updateCustTransporters(@Param("custid") String custid,@Param("transid") String transid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_delv_to w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_delv_toupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
	
	
	@Query( "select c from Cust_bussiness_partner_delv_to c where c.cp_Id = :code and c.modified_type = 'INSERTED' ORDER BY c.sl_no")
	List<Cust_bussiness_partner_delv_to> getCustDelvFromList(@Param("code") String code);
 
}
