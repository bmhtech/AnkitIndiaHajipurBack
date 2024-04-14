package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_delv_to;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_delv_from;

public interface Supp_bussiness_partner_delv_fromRepository extends JpaRepository<Supp_bussiness_partner_delv_from, Long>{

	@Transactional
	
	@Query( "select b from Supp_bussiness_partner_delv_from b where b.bp_Id =:sbp_id and b.contact_person = :CN and b.modified_type ='INSERTED'")
	Supp_bussiness_partner_delv_from getSupplierDelvFromAdd(@Param("sbp_id") String sbp_id,@Param("CN") String CN);
	
	@Query( "select b from Supp_bussiness_partner_delv_from b where b.bp_Id = :bp_id and b.modified_type ='INSERTED'")
	Supp_bussiness_partner_delv_from getSuppBUnitAddr(@Param("bp_id") String bp_id);
	
	@Query( "select s from Supp_bussiness_partner_delv_from s where s.modified_type = 'INSERTED' and s.bp_Id =:bp_id and s.bu_name =:bu_name and s.modified_type ='INSERTED'")
	Supp_bussiness_partner_delv_from getSuppDelvAddress(@Param("bp_id") String bp_id,@Param("bu_name") String bu_name);
	//CHANGES ON 12-04-2022
	//@Query( "select s from Supp_bussiness_partner_delv_from s where s.bp_Id = :suppid and s.modified_type ='INSERTED'")
	@Query( "select s from Supp_bussiness_partner_delv_from s where s.bp_Id = :suppid and s.transport_own= 'YES' and s.modified_type ='INSERTED'")
	Supp_bussiness_partner_delv_from getSuppTransporters(@Param("suppid") String suppid);
	
	@Query( "select s from Supp_bussiness_partner_delv_from s where s.bp_Id = :suppid  and s.modified_type ='INSERTED'")
	List<Supp_bussiness_partner_delv_from> getSuppTransportersown(@Param("suppid") String suppid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_delv_from w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_delv_fromUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query( "select b from Supp_bussiness_partner_delv_from b where b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_delv_from> getSuppBUnitName();
	
	@Query( "select b from Supp_bussiness_partner_delv_from b where b.bp_Id = :bp_id and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_delv_from> getSuppContactNameList(@Param("bp_id") String bp_id);
}
