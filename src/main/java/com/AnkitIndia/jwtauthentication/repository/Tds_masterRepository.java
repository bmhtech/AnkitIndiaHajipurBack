package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Tds_master;

public interface Tds_masterRepository extends JpaRepository<Tds_master,Long>{
	
	@Query("SELECT MAX(SUBSTRING(tds_id , 5 , length(tds_id))) FROM Tds_master where tds_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select t from Tds_master t where t.modified_type != 'DELETED' ")
	List<Tds_master> getTdsMNCList();
	
	@Query( "select t from Tds_master t where t.modified_type != 'DELETED' and t.tds_id =:tdsid ")
	Tds_master getTDSRate(@Param("tdsid") String tdsid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkTdsMasterUsage(@Param("code") String code);
	
	/*
	 * @Query("select max(substring(item_code , 8, length(item_code))) from Tds_master where item_code like %:code% and company_id =:company "
	 * ) String getItemSequenceId(@Param("code") String code,@Param("company")
	 * String company);
	 */
	
	@Query( "select t from Tds_master t where t.modified_type != 'DELETED' and t.tds_rate=:tds_rate ")
	Tds_master gettdsname(@Param("tds_rate") double tds_rate);
	
}
