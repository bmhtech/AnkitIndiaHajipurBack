package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_broker;

public interface Cust_bussiness_partner_brokerRepository extends JpaRepository<Cust_bussiness_partner_broker, Long>{
	
	@Query("select b from Cust_bussiness_partner_broker b where b.cp_Id =:cp_id and b.ven_code_name =:bcode and b.modified_type = 'INSERTED'")
	Cust_bussiness_partner_broker getCustomerBrokerDtls(@Param("cp_id") String cp_id,@Param("bcode") String bcode);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_broker w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_brokerupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
	
	@Query("select b from Cust_bussiness_partner_broker b where b.ven_code_name =:bcode and b.modified_type = 'INSERTED'")
	List<Cust_bussiness_partner_broker> custBrokerByCode(@Param("bcode") String bcode);
	
	@Query( "select c from Cust_bussiness_partner_broker c where c.cp_Id = :code and c.modified_type = 'INSERTED' ORDER BY c.sl_no")
	List<Cust_bussiness_partner_broker> custBrokerRetriveList(@Param("code") String code);
	
	@Query( "select c from Cust_bussiness_partner_broker c where c.cp_Id = :code and c.modified_type = 'INSERTED' ORDER BY c.sl_no")
	Cust_bussiness_partner_broker custBrokerRetriveListNew(@Param("code") String code);
	

}
