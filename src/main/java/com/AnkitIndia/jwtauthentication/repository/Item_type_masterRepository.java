package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_type_master;

public interface Item_type_masterRepository extends JpaRepository<Item_type_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(item_id , 5 , length(item_id))) FROM Item_type_master where item_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(item_code , 8, length(item_code))) from Item_type_master where item_code like %:code% and company_id =:company ")
	String getItypeSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("SELECT i FROM Item_type_master i where i.modified_type != 'DELETED' and i.company_id =:company ORDER BY i.item_id DESC")
	List<Item_type_master> getItemtypes(@Param("company") String company);

	@Query( "select i from Item_type_master i where i.modified_type != 'DELETED' and i.company_id =:company")
	List<Item_type_master> getItemTypeList(@Param("company") String company);
	
	@Query( "select i from Item_type_master i where i.item_id = :code")
	Item_type_master itemTypeNameByCode(@Param("code") String code);
	
	@Query( "select i from Item_type_master i where i.modified_type != 'DELETED' ")
	List<Item_type_master> getItemTypesList(@Param("pageable") Pageable pageable);
	
	@Query( "select i from Item_type_master i where i.modified_type = 'INSERTED' and i.item_id =:itypeid ")
	Item_type_master getItemType(@Param("itypeid") String itypeid);
	
	@Query("select i from Item_type_master i where i.modified_type != 'DELETED' and i.item_code =:code")
	Optional<Item_type_master> chkItemTypeCodeStatus(@Param("code") String code);
	
	
	@Query( "select i from Item_type_master i")//where i.modified_type != 'DELETED' and i.item_code =:item_code  
	List<Item_type_master> getpotypename(@Param("item_code") String potype);
	
	@Query(value="SELECT i.item_id AS item_id,i.item_code AS item_code,i.item_name AS item_name FROM item_type_master i WHERE i.modified_type='INSERTED' AND i.company_id=:company ORDER BY i.id",nativeQuery=true)
	List<Map<String, Object>> itemTypeListFastAPI(@Param("company") String company);
	
	
}
