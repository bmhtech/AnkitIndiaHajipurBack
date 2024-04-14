package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;

public interface Supp_bussiness_partner_addressRepository extends JpaRepository<Supp_bussiness_partner_address, Long> {
	
	@Query( "select a from Supp_bussiness_partner_address a where a.bp_Id = :bp_id and a.modified_type ='INSERTED'")
	Supp_bussiness_partner_address getSupplierAddr(@Param("bp_id") String bp_id);
	
	@Query(value="select s.bp_Id,s.state,s.state_code,s.add1 from supp_bussiness_partner_address s where s.modified_type = 'INSERTED' and s.bp_Id =:bp_id",nativeQuery=true)
	Map<String, Object> getSupplierAddrFast(@Param("bp_id") String bp_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_address w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_addressUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);

}
