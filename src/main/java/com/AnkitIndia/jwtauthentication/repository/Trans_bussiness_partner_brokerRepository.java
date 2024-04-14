package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_broker;

public interface Trans_bussiness_partner_brokerRepository extends JpaRepository<Trans_bussiness_partner_broker, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Trans_bussiness_partner_broker w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int trans_bussiness_partner_brokerupdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query( "select t from Trans_bussiness_partner_broker t where t.modified_type = 'INSERTED' and t.bp_Id =:code")
	List<Trans_bussiness_partner_broker> getTransporterBrokerList(@Param("code") String code);
	
	@Query( "select t from Trans_bussiness_partner_broker t where t.modified_type = 'INSERTED' and t.ven_code_name =:vcode")
	List<Trans_bussiness_partner_broker> getTransporterBrokers(@Param("vcode") String vcode);

}
