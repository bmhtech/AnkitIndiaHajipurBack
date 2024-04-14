package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;

public interface Process_masterRepository extends JpaRepository<Process_master, Long>{
	
	@Query("select max(substring(process_no , 7, length(process_no))) from Process_master where process_no like %:code% and company_id =:company ")
	String getPMSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select COUNT(id) from Process_master")
	String countId();
	
	@Query("select p from Process_master p where p.modified_type ='INSERTED' ORDER BY p.process_id DESC")
	List<Process_master> findAllProcesses();
	
	@Query("select p from Process_master p where p.modified_type ='INSERTED' and p.business_unit =:bunit and p.shop_floor =:sfloor and p.company_id =:company ")
	List<Process_master> getProcessThruBUShopFloor(@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("company") String company);
	
	@Query("select p from Process_master p where p.modified_type ='INSERTED' and p.business_unit =:bunit and p.shop_floor =:sfloor and p.process_type ='Regular' and p.company_id =:company ")
	List<Process_master> getProcessThruBUShopFloorRegular(@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("company") String company);
	
	@Query("select p from Process_master p where p.modified_type ='INSERTED' and p.business_unit =:bunit and p.shop_floor =:sfloor and p.process_type ='Special' and p.company_id =:company ")
	List<Process_master> getProcessThruBUShopFloorSpl(@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("company") String company);
	
	@Query("select p from Process_master p where p.modified_type ='INSERTED' and p.business_unit =:bunit and p.shop_floor =:sfloor and p.process_id =:pdesc and p.company_id =:company ")
	Process_master getProcessThruBUSFProDesc(@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("pdesc") String pdesc,@Param("company") String company);
	
	@Query("select p from Process_master p where p.modified_type ='INSERTED' and p.process_id =:process_id")
	Process_master getProcessDetails(@Param("process_id") String process_id);
	
	@Query("select a.process_id from Process_master a where a.modified_type !='DELETED' and a.id =:id")
	String getProcesid(@Param("id") Long id);
	
	@Query("select a from Process_master_doc a where a.modified_type !='DELETED' and a.process_id =:process_id")
	List<Process_master_doc> getProcessdocuments(@Param("process_id") String process_id);
	
	@Query("select a.itemgroup_array from Process_master a where a.modified_type ='INSERTED' and a.business_unit =:businessunit and a.shop_floor =:shopfloor and a.process_id =:plist")
	String getGroupByProcessName(@Param("businessunit") String businessunit,@Param("shopfloor") String shopfloor,@Param("plist") String plist);
	
	@Query(value="SELECT shop_floor AS shop_floor_id,shop_floorname AS shop_floor_name FROM process_master WHERE  modified_type='INSERTED' and process_type='Special' AND business_unit=:businessunit",nativeQuery=true)
	List<Map<String,Object>> getShopFloorspecialThruBU(@Param("businessunit") String businessunit);
	
	
	@Query(value="SELECT shop_floor AS shop_floor_id,shop_floorname AS shop_floor_name FROM process_master WHERE  modified_type='INSERTED' and process_type!='Special' AND business_unit=:businessunit",nativeQuery=true)
	List<Map<String,Object>> getShopFloorThruBUregular(@Param("businessunit") String businessunit);
	
	
	
}
