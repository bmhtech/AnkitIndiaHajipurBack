package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Warehouse_stack_dtls;

public interface Warehouse_stack_dtlsRepository extends JpaRepository<Warehouse_stack_dtls, Long>
{
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Warehouse_stack_dtls u SET u.modified_type =:mstatus WHERE u.warehouse_id = :warehouse_id and u.modified_type ='INSERTED'")
	  int updateWarehouse_stack_dtls(@Param("warehouse_id") String warehouse_id, @Param("mstatus") String mstatus);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Warehouse_stack_dtls d SET d.modified_type ='DELETED' WHERE d.warehouse_id = :warehouse_id")
    int warehouse_stack_dtlsDelete(@Param("warehouse_id") String warehouse_id);

	@Query(value="select * from warehouse_stack_dtls where warehouse_id = :warehouse_id and modified_type = 'INSERTED' ORDER BY sl_no ASC", nativeQuery = true)
	List<Map<String, Object>> getStackDtlsList(@Param("warehouse_id") String warehouse_id);

	@Query(value= "select * from warehouse_stack_dtls w where w.modified_type = 'INSERTED' and w.warehouse_code =:wh_code", nativeQuery=true)
	List<Map<String, Object>> getStackNoByWarehouseList(@Param("wh_code") String wh_code);

	@Query(value= "select CASE WHEN (SELECT description FROM custom_uom_master WHERE customuom_id=w.packing_uom) IS NULL THEN '' ELSE (SELECT description FROM custom_uom_master WHERE customuom_id=w.packing_uom) END AS packing_uom from warehouse_stack_dtls w where w.modified_type = 'INSERTED' and w.stack_no =:rack", nativeQuery=true)
	Map<String, Object> getStackUom(@Param("rack") String rack);

	
}
