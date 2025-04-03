package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Warehouse_master;

public interface WarehouseMasterRepository extends JpaRepository<Warehouse_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(warehouse_id , 4 , length(warehouse_id))) FROM Warehouse_master where warehouse_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select w from Warehouse_master w where w.warehouse_active = :sts and w.modified_type != 'DELETED' ")
	List<Warehouse_master> getWarehouseNc(@Param("sts") Boolean sts);
	
	@Query( "select w from Warehouse_master w where w.warehouse_code = :code and w.modified_type != 'DELETED'")
	Warehouse_master getWHNListbyCode(@Param("code") String code);
	
	@Query( "select w from Warehouse_master w where w.warehouse_id = :wid and w.modified_type != 'DELETED'")
	Warehouse_master getWarehouseDetails(@Param("wid") String wid);
	
	@Query( "select w from Warehouse_master w where w.businessunit_code =:bunit and w.modified_type != 'DELETED'")
	List<Warehouse_master> getWHListbyBUnit(@Param("bunit") String bunit);
	
	@Query(value= "select * from warehouse_master w where w.modified_type = 'INSERTED' and w.businessunit_code =:bunit and w.warehouse_active = '1' ORDER BY w.warehouse_name ASC", nativeQuery=true)
	List<Map<String, Object>> getWarehouseFastList(@Param("bunit") String bunit);
	
	@Query("select max(substring(warehouse_code , 8, length(warehouse_code))) from Warehouse_master where warehouse_code like %:code% and company_id =:company ")
	String getWareHouseSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select w from Warehouse_master w where w.modified_type = 'INSERTED' ")
	List<Warehouse_master> warehouseNewList();
	
}
