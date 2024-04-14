package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_broker;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_broker;

public interface Supp_bussiness_partner_brokerRepository extends JpaRepository<Supp_bussiness_partner_broker, Long>{

	@Query("select b from Supp_bussiness_partner_broker b,Supp_bussiness_partner s where b.bp_Id=s.bp_Id and s.id = :Id and b.modified_type ='INSERTED'")
	List<Supp_bussiness_partner_broker> getBrokerList(@Param("Id") String Id);
	
	@Query("select b from Supp_bussiness_partner_broker b where b.bp_Id =:sbp_id and b.ven_code_name =:brokercode and b.modified_type = 'INSERTED'")
	Supp_bussiness_partner_broker getSupplierBrokerDtls(@Param("sbp_id") String sbp_id,@Param("brokercode") String brokercode);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_broker w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_brokerUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query("select b from Supp_bussiness_partner_broker b where b.ven_code_name =:code and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_broker> getSupplierBrokerDtls(@Param("code") String code);
	
	
	@Query("select b from Supp_bussiness_partner_broker b where b.ven_code_name =:code and b.bp_Id=:supp and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_broker> getnewSupplierBrokersDtls(@Param("code") String code,@Param("supp") String supp);
	
	
	@Query("select b from Supp_bussiness_partner_broker b where b.bp_Id =:code and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_broker> getSupplierBrokerList(@Param("code") String code);
	
	//@Query(value = "{call bro_supp_updation(:bp_Id)}",nativeQuery = true)
	//List<Status_table> sup_broker_count1(@Param("bp_Id") String bp_Id);
	
	@Query(value = "{call bro_supp_updation(:code)}",nativeQuery = true)
	List<Status_table> sup_broker(@Param("code") String code);

	@Query(value = "{call supp_broker_updation(:code)}",nativeQuery = true)
	List<Status_table> broker_supp(@Param("code") String code);
	
	@Query("select b from Supp_bussiness_partner_broker b where b.bp_Id =:code and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_broker> getSupplierBrokerDtlscode(@Param("code") String code);
}
