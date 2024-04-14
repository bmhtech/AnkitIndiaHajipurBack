package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_vehicle_dtls;


public interface Trans_bussiness_partner_vehicle_dtlsRepository extends JpaRepository<Trans_bussiness_partner_vehicle_dtls, Long> {

	@Query( "select t from Trans_bussiness_partner_vehicle_dtls t where t.modified_type = 'INSERTED' and t.bp_Id =:tid ")
	List<Trans_bussiness_partner_vehicle_dtls> getVehicleDetails(@Param("tid") String tid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Trans_bussiness_partner_vehicle_dtls w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int trans_bussiness_partner_vehicle_dtlsupdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query( "select t from Trans_bussiness_partner_vehicle_dtls t where t.vehicle_name = :code and t.modified_type = 'INSERTED'")
	List<Trans_bussiness_partner_vehicle_dtls> getTransListByVlno(@Param("code") String code);
	
	@Query( "select t from Trans_bussiness_partner_vehicle_dtls t where t.modified_type = 'INSERTED' and t.bp_Id =:bp_id")
	List<Trans_bussiness_partner_vehicle_dtls> getTransBPVD(@Param("bp_id") String bp_id);
}
