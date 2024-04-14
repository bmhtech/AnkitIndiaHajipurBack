package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Shop_floor_master;

public interface Shop_floor_masterRepository extends JpaRepository<Shop_floor_master, Long>{
	
	@Query("select max(substring(shop_floor_code , 7, length(shop_floor_code))) from Shop_floor_master where shop_floor_code like %:code% and company_id =:company ")
	String getSFSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select COUNT(id) from Shop_floor_master")
	String countId();
	
	@Query("select s from Shop_floor_master s where s.modified_type != 'DELETED' ORDER BY s.shop_floor_id DESC")
	List<Shop_floor_master> findAllShopFloors();
	
	@Query("select s from Shop_floor_master s where s.modified_type = 'INSERTED' and s.shop_floor_id=:sfid ")
	Shop_floor_master getShopFloorDtls(@Param("sfid") String sfid);
	
	@Query("select s from Shop_floor_master s where s.modified_type != 'DELETED' and s.business_unit =:bunit")
	List<Shop_floor_master> getShopFloorThruBU(@Param("bunit") String bunit);
	
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Process_master pl where pl.modified_type != 'DELETED' and pl.shop_floor=:sfid")
	Boolean checkShopFloorUsage(@Param("sfid") String sfid);
	
	
}
