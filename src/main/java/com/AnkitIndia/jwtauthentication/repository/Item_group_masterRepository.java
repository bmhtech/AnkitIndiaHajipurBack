package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_group_jobwork_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_jobwork_sales_return_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_stk_trans_sale;

public interface Item_group_masterRepository extends JpaRepository<Item_group_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(item_group_id , 3 , length(item_group_id))) FROM Item_group_master where item_group_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(item_group_code , 8, length(item_group_code))) from Item_group_master where item_group_code like %:code% and company_id =:company ")
	String getIgrpSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query( "select g from Item_group_master g where g.id =:id ")
	Item_group_master getGroupDtls(@Param("id") Long id);
	
	@Query( "select g from Item_group_master g where g.modified_type = 'INSERTED' and g.item_group_id =:code ")
	Item_group_master getGroupDtls_converter(@Param("code") String code);
	
	@Query( "select g from Item_group_master g where g.modified_type = 'INSERTED' and g.company_id =:company ORDER BY g.item_group_id DESC ")
	List<Item_group_master> findAllItemGroups(@Param("company") String company);
	
	@Query( "select g from Item_group_master g where g.modified_type = 'INSERTED' and g.group_active =:status and g.company_id =:company ORDER BY g.item_group_id")
	List<Item_group_master> getItemGroupList(@Param("status") boolean status,@Param("company") String company);
	
	@Query(value="select item_group_id,group_name from item_group_master where modified_type = 'INSERTED' and group_active =:status and company_id =:company ORDER BY item_group_id",nativeQuery=true)
	List<Map<String,Object>> itemGroupFastList(@Param("status") boolean status,@Param("company") String company);
	
	@Query( "select g from Item_group_master g where g.modified_type = 'INSERTED' and g.item_group_id =:code ")
	Item_group_master getItemGroupUom(@Param("code") String code);
	
	@Query( "select g from Item_group_master g where g.item_group_id =:code ")
	Item_group_master getItemGroups(@Param("code") String code);
	
	@Query( "select DISTINCT g.parent_group from Item_group_master g where g.item_group_id !=:code and g.parent_group =:code and g.modified_type = 'INSERTED'")
	Optional<String> chkItemGroup(@Param("code") String code);
	
	@Query( "select g.group_name from Item_group_master g where g.modified_type = 'INSERTED' and g.group_name =:code ")
	String getItemGroupName(@Param("code") String code); 
	
	@Query( "select g.parent_group from Item_group_master g where g.modified_type = 'INSERTED' and g.item_group_id =:pgroup ")
	String getParentGroupId(@Param("pgroup") String pgroup);
	
	@Query( "select g from Item_group_master g where g.modified_type = 'INSERTED' and g.group_name =:code ")
	Item_group_master chkItemGroupStatus(@Param("code") String code);
	
	@Query( "select g.group_name from Item_group_master g where g.modified_type = 'INSERTED' and g.item_group_id =:code ")
	String getGroupName(@Param("code") String code);
	
	@Query( "select g.group_name from Item_group_master g where g.modified_type = 'INSERTED' and g.group_name =:code ")
	String chkItemNameStatus(@Param("code") String code);

	@Query("select i from Item_group_master i where i.modified_type = 'INSERTED' and i.item_group_code =:code")
	Optional<Item_group_master> chkItemGroupCodeStatus(@Param("code") String code);
	
	/*@Query(value= "SELECT d.jw_item_array AS jw_item_array FROM item_group_jobwork_sales_acc d WHERE d.modified_type='INSERTED' AND d.item_group_id=:group", nativeQuery=true)
	String getGroupItemLedgerForJob(@Param("group") String group);
	
	@Query(value="select s1.jw_item_array AS jw_item_array FROM item_group_jobwork_sales_acc s1 WHERE s1.jw_item_array IN (:broker) AND s1.modified_type='INSERTED' ", nativeQuery=true)
	List<Map<String,Object>> getGroupItemLedgerForJob1(@Param("broker") List<String> broker);
	*/
}
