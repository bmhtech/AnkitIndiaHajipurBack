package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.model.Item_master_qc_details;
import com.AnkitIndia.jwtauthentication.model.Item_master_stock_details;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_masterstatusDTO;

public interface Item_masterRepository extends JpaRepository<Item_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(item_id , 3 , length(item_id))) FROM Item_master where item_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(item_code , 8, length(item_code))) from Item_master where item_code like %:code% and company_id =:company ")
	Optional<String> getItemSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("SELECT i FROM Item_master i where i.id =:id")
    Item_master getItemDetails(@Param("id") Long id);
	
	@Query("SELECT i FROM Item_master i where i.item_id =:item_id and i.modified_type = 'INSERTED'")
    Item_master getItemDetailsThruItemId(@Param("item_id") String item_id);
	
	@Query("SELECT DISTINCT i.item_category FROM Item_master i where i.item_category =:catagory_id and i.modified_type = 'INSERTED'")
    Optional<String> getItemDtlsThruCategory(@Param("catagory_id") String catagory_id);
	
	@Query("SELECT DISTINCT i.item_group FROM Item_master i where i.item_group =:group_id and i.modified_type = 'INSERTED'")
    Optional<String> getItemDtlsThruGroup(@Param("group_id") String group_id);
	
	@Query("SELECT i FROM Item_master i where i.modified_type = 'INSERTED' and i.company_id =:comp ")
    public Page<Item_master> findSortedItems(@Param("pageable") Pageable pageable,@Param("comp") String comp);
	
	@Query("SELECT i FROM Item_master i where i.modified_type = 'INSERTED' and i.company_id =:company ORDER BY i.item_id DESC")
    public List<Item_master> findAllItems(@Param("company") String company);
    
    @Query("SELECT i FROM Item_master i WHERE CONCAT(i.item_code, i.hsn_code, i.item_name, i.item_type, i.item_group_name, i.mstock_unit_name, i.item_category_name) LIKE %:keyword% and i.modified_type = 'INSERTED' and i.company_id =:company ORDER BY i.item_id DESC")
    public List<Item_master> findItems(@Param("company") String company,String keyword);
    
    @Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_unit like %:business_unit% and i.item_type =:item_type")
	List<Item_master> getItemOpening(@Param("business_unit") String business_unit,@Param("item_type") String item_type);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status ")
	List<Item_master> getItemNameList(@Param("status") boolean status);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status ")
	List<Item_master> getAllItems(@Param("status") boolean status);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type =:type and i.company_id =:company")
	List<Item_master> getItemCodeByPacking(@Param("status") boolean status,@Param("type") String type,@Param("company") String company);
	//Delete
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type =:type ")
	List<Item_master> getItemCodeByPacking(@Param("status") boolean status,@Param("type") String type);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type !=:type ")
	List<Item_master> getItemCodeWithoutPacking(@Param("status") boolean status,@Param("type") String type);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type =:type ")
	List<Item_master> getItemRawMaterials(@Param("status") boolean status,@Param("type") String type);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.purchase_item =:status and i.item_type =:itype ")
	List<Item_master> getItemThruType(@Param("status") boolean status,@Param("itype") String itype);
	
	

	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =1 and i.item_type =:itype ")
	List<Item_master> getItemThruType3(@Param("itype") String itype);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type !=:type and i.sales_item =:salestatus")
	List<Item_master> getItemCodeWithoutPackSales(@Param("status") boolean status,@Param("type") String type,@Param("salestatus") boolean salestatus);
	
	@Query(value="select * from item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type !=:type and i.sales_item =:salestatus", nativeQuery=true)
	List<Map<String,Object>> getItemCodeWithoutPackSaleNew(@Param("status") boolean status,@Param("type") String type,@Param("salestatus") boolean salestatus);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type !=:type and i.sales_item =:salestatus and i.item_unit like %:bunit%")
	List<Item_master> getItemThruSalesThruBU(@Param("status") boolean status,@Param("type") String type,@Param("salestatus") boolean salestatus,@Param("bunit") String bunit);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =1 and i.purchase_item =1 and  i.item_unit like %:bunit% and i.item_name like %:itemtype% ")
	List<Item_master> getweatreceivingitemlist(@Param("bunit") String bunit,@Param("itemtype") String itemtype);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =1 and  i.item_unit like %:bunit% and i.item_type =:itemtype ")
	List<Item_master> getLabItemlist(@Param("bunit") String bunit,@Param("itemtype") String itemtype);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type !=:type and i.sales_item =:salestatus and i.item_unit like %:bunit% and i.item_type!='PACKING MATERIAL SALE'")
	List<Item_master> getItemThruSalesThruBU_withoutPMS(@Param("status") boolean status,@Param("type") String type,@Param("salestatus") boolean salestatus,@Param("bunit") String bunit);
	
	@Query(value="SELECT i.item_id,i.item_name FROM item_master i WHERE i.modified_type = 'INSERTED' AND i.item_active ='1' AND i.item_type = 'FINISHED PRODUCTS' AND i.sales_item ='1' AND i.company_id=:company AND i.item_unit LIKE %:bunit% AND item_name LIKE '%TAX%' ORDER BY i.item_name ASC", nativeQuery=true)
	List<Map<String,Object>> getItemThruSalesThruBU_inv_typeGST(@Param("bunit") String bunit,@Param("company") String company);
	
	@Query(value="SELECT i.item_id,i.item_name FROM item_master i WHERE i.modified_type = 'INSERTED' AND i.item_active ='1' AND i.sales_item ='1' AND i.company_id=:company AND i.item_unit LIKE %:bunit% AND item_name NOT LIKE '%TAX%' AND i.item_type!='PACKING MATERIAL SALE' AND i.item_type!='PACKING ITEMS' ORDER BY i.item_name ASC", nativeQuery=true)
	List<Map<String,Object>> getItemThruSalesThruBU_inv_typeReg(@Param("bunit") String bunit,@Param("company") String company);

	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type !=:type and i.sales_item =:salestatus and i.item_unit like %:bunit% and i.item_type='PACKING MATERIAL SALE'")
	List<Item_master> getItemThruSalesThruBU_PMS(@Param("status") boolean status,@Param("type") String type,@Param("salestatus") boolean salestatus,@Param("bunit") String bunit);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type ='JOB WORK' and i.sales_item =:status and i.item_unit like %:bunit% ")
	List<Item_master> getItemThroughJobWork(@Param("status") boolean status,@Param("bunit") String bunit);
	
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.processed_item =:status ")
	List<Item_master> getItemThruProcessed(@Param("status") boolean status);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.processed_item =:status and item_unit like %:bunit%")
	List<Item_master> getItemThruProcessed(@Param("status") boolean status,@Param("bunit") String bunit);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.purchase_item =:pstatus ")
	List<Item_master> getItemCodeWithoutPackPurchase(@Param("status") boolean status,@Param("pstatus") boolean pstatus);
	
	
	
	@Query(value= "select * from item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.purchase_item =:pstatus",nativeQuery=true)
	List<Map<String, Object>> getItemCodeWithoutPackPurchasenew(@Param("status") boolean status,@Param("pstatus") boolean pstatus);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_group =:group")
	List<Item_master> getItemListByGroup(@Param("group") String group);
	
	@Query( "select i from Item_master i where i.modified_type = 'INSERTED' and i.item_group =:group and i.item_id !=:item_id")
	List<Item_master> getItemListByGroup(@Param("group") String group,@Param("item_id") String item_id);
	
	@Query( "select i from Item_master i where i.item_code = :code and i.modified_type = 'INSERTED'")
	Item_master itemNameByCode(@Param("code") String code);
	
	@Query( "select i from Item_master i where i.item_id = :itemId and i.modified_type = 'INSERTED'")
	Item_master getItemNameCodeStock(@Param("itemId") String itemId);
	
	@Query( "select i from Item_master i where i.item_type = :type and i.modified_type = 'INSERTED'")
	List<Item_master> getItemCodeByType(@Param("type") String type);
	
	@Query( "select i from Item_master i where i.item_id = :code and i.modified_type = 'INSERTED'")
	Item_master itemNameById(@Param("code") String code);
	
	@Query( "select i from Item_master i where i.item_id = :code and i.company_id=:company and i.modified_type = 'INSERTED'")
	Item_master itemNameByIdcom(@Param("code") String code,@Param("company") String company);
	
	@Query( "select i.main_group from Item_group_master i where i.modified_type = 'INSERTED' and i.item_group_id =:mgcode ")
	String getMainGroup(@Param("mgcode") String mgcode);
	
	/*@Query( "select i from Item_master_pack_mat_tag i where i.item_id = :code and i.item_code= :code1 and i.modified_type = 'INSERTED'")
	Item_master itempackUom(@Param("code") String code,@Param("code1") String code1);
	
	
	
	
	
	
	*/
	@Query("select b.item_name from Item_master b where b.modified_type = 'INSERTED' and b.item_name =:iname")
	String chkItemNameStatus(@Param("iname") String iname);
	
	@Query("select i from Item_master i where i.modified_type = 'INSERTED' and i.item_code =:icode")
	Optional<Item_master> chkItemCodeStatus(@Param("icode") String icode);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master i SET i.stk_status =:mstatus WHERE i.item_id =:item_id")
    int updateItemStockStatus(@Param("item_id") String item_id,@Param("mstatus") boolean mstatus);

	//@Query(value = "{call unload_advice_updation(:pur_orderid)}", nativeQuery = true)
	//List<Status_table> getUnload_advice_updation(@Param("pur_orderid") String pur_orderid);
	
	@Query(value = "{call checkItemMasterUsage(:code)}", nativeQuery = true)
	String checkItemMasterUsage(@Param("code") String code);
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Item_master p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Item_master p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int exportuomtallyReverse(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Item_master_stock_details i SET i.modified_type = 'UPDATED'  WHERE i.item_id =:item_id" )
	int item_master_stock_detailsrest(@Param("item_id") String item_id);
	
	
	
	@Query( "select i from Item_master_stock_details i where i.item_id = :code and i.company_id=:company and i.modified_type = 'INSERTED'")
	List<Item_master_stock_details> getItemstockDetails(@Param("code") String code,@Param("company") String company);
	
	

	@Query("SELECT i FROM Item_master i where i.item_name =:item_name and i.modified_type = 'INSERTED'")
    Item_master getItemDetailsbyname(@Param("item_name") String item_name);
	
	@Query(value = "{call getsearchItemdata(:#{#tablename},:#{#item_id},:#{#item_group},:#{#item_category},:#{#item_type},:#{#itemname1},:#{#itemgroup},:#{#itemcategory},:#{#itemtype1},:#{#finyear})}", nativeQuery = true)
	List<Item_master> getsearchItemdata(@Param("tablename") String tablename,@Param("item_id") String item_id,
			@Param("item_group") String item_group,@Param("item_category") String item_category, 
			@Param("item_type") String item_type,@Param("itemname1") String itemname1,
			@Param("itemgroup") String itemgroup,@Param("itemcategory") String itemcategory,
			@Param("itemtype1") String itemtype1,@Param("finyear") String finyear);
	
	@Query( "select i from Item_master i where i.item_type = :type  and i.modified_type='INSERTED'")
	List<Item_master> getItemCodeByTypeNew(@Param("type") String type);
	
	@Query(value ="select d.capacity from item_master_pack_mat_tag d where d.modified_type ='INSERTED' and d.item_id=:item_code ORDER BY id DESC LIMIT 1",nativeQuery=true)
	String getItemCapacity(@Param("item_code") String item_code);
	
	
	
	@Query( "select i from Item_master i where  i.modified_type='INSERTED' and item_unit like %:businessunit% and i.item_active=1 and i.chakki_prod=1 and i.processed_item=1 and i.input_prod=1 ")
	List<Item_master> itemnameproductioninputchakki(@Param("businessunit") String businessunit);
	
	@Query( "select i from Item_master i where i.modified_type='INSERTED' and item_unit like %:businessunit% and i.item_active=1 and i.roller_prod=1 and i.processed_item=1 and i.input_prod=1")
	List<Item_master> itemnameproductioninputroller(@Param("businessunit") String businessunit);
	
	
//	@Query( "select i from Item_master i where i.modified_type='INSERTED' and item_unit like %:businessunit% and i.item_active=1 and i.purchase_item=1  and i.processed_item=1 and i.input_prod=0")
	@Query(value="select Distinct d.item_name,d.item_code from `pur_order` p,`pur_order_item_details` d where p.modified_type='INSERTED' and p.businessunit=:businessunit AND p.pur_orderid=D.pur_orderid AND p.ser_item_subtype_name='RAW MATERIALS' and d.item_code=(select item_id from item_master  where modified_type='INSERTED'  and item_active=1 and purchase_item=1  and processed_item=1 and input_prod=0 AND item_id = d.item_code)", nativeQuery = true)
	List<Object[]> itemnameproductioninputboth(@Param("businessunit") String businessunit);
	
	
	@Query( "select i from Item_master i where i.modified_type='INSERTED' and item_unit like %:businessunit% and i.item_active=1 and i.chakki_prod=1 and i.processed_item=1 and i.input_prod=0")
	List<Item_master> itemnameproductionoutputchakki(@Param("businessunit") String businessunit);
	
	
	@Query( "select i from Item_master i where i.modified_type='INSERTED' and item_unit like %:businessunit% and i.item_active=1 and i.roller_prod=1 and i.processed_item=1 and i.input_prod=0")
	List<Item_master> itemnameproductionoutputroller(@Param("businessunit") String businessunit);
	
	
	@Query( "select i from Item_master i where i.modified_type='INSERTED' and item_unit like %:businessunit% and i.item_active=1 and  i.purchase_item=1 and i.processed_item=1 and i.input_prod=1")
	List<Item_master> itemnameproductionoutputboth(@Param("businessunit") String businessunit);
	
	
	@Query( "select i from Item_master i where i.modified_type='INSERTED' and i.item_group=:itemgroup")
	List<Item_master> itemNameByGroupProduction(@Param("itemgroup") String itemgroup);

	@Query(value="select * from item_master i where i.modified_type = 'INSERTED'and i.item_active =:status", nativeQuery=true)
	List<Map<String,Object>> getItemNameNewList(@Param("status") boolean status);
	
	@Query(value="select item_id, item_code, item_name,mstock_unit_name from item_master  where modified_type = 'INSERTED'and item_unit like %:bunit% and item_active =1 and item_type =:itemtype", nativeQuery=true)
	List<Map<String,Object>> getFinishedItemlist(@Param("bunit") String bunit,@Param("itemtype") String itemtype);
	
	@Query(value="select item_id, item_code, item_name,mstock_unit_name from item_master  where modified_type = 'INSERTED' and item_id =:code", nativeQuery=true)
	List<Map<String,Object>> getPackingUomList(@Param("code") String code);
	
	@Query(value="select item_id, item_code, item_name,mstock_unit_name from item_master where modified_type = 'INSERTED' and item_active =1 and item_type !=:type and sales_item =1 and company_id =:company and item_unit like %:bunit%", nativeQuery=true)
	List<Map<String,Object>> getItemThruSalesThruBUFastApi(@Param("bunit") String bunit,@Param("company") String company,@Param("type") String type);
	
	@Query(value="select * from item_master where modified_type = 'INSERTED'and item_active =:status and company_id =:company and item_type IN (:type) ", nativeQuery=true)
	List<Map<String,Object>> searchItemData(@Param("type") List<String> type,@Param("company") String company,@Param("status") boolean status);
	
	@Query(value="select i.item_id_old as item_id,item_name from item_master_alternative_dtls i where i.modified_type = 'INSERTED' and i.item_id=:itemcode AND  i.item_id_old!=i.item_id", nativeQuery=true)
	List<Map<String,Object>> getAlternativeItemList(@Param("itemcode") String itemcode);
	
	@Query(value="SELECT i.item_id,i.item_name FROM item_master i WHERE i.modified_type = 'INSERTED' and item_id=:itemcode", nativeQuery=true)
	Map<String,Object> getAlternativeItemListitem(@Param("itemcode") String itemcode);
	
	//@Query(value="SELECT j.jw_item_array as service_id ,(SELECT service_name FROM item_service_master WHERE service_id=j.jw_item_array) AS service_name,(SELECT sac_code FROM item_service_master WHERE service_id=j.jw_item_array) as sac_code FROM  item_group_jobwork_sales_acc j ,item_master i WHERE j.modified_type ='INSERTED' AND j.`modified_type`=i.`modified_type`  AND i.`group_main_id`=j.`item_group_id` AND i.`item_id`=:item_id",nativeQuery=true)
	//List<Map<String,Object>> getJobworklist(@Param("item_id") String item_id);
	
	@Query(value="SELECT service.service_id,service1.service_name,service1.sac_code FROM (SELECT\r\n" + 
			"  TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(B.jw_item_array, ',', NS.n), ',', -1)) AS service_id\r\n" + 
			"FROM (\r\n" + 
			"  SELECT 1 AS n UNION ALL\r\n" + 
			"  SELECT 2 UNION ALL\r\n" + 
			"  SELECT 3 UNION ALL\r\n" + 
			"  SELECT 4 UNION ALL\r\n" + 
			"  SELECT 5 UNION ALL\r\n" + 
			"  SELECT 6 UNION ALL\r\n" + 
			"  SELECT 7 UNION ALL\r\n" + 
			"  SELECT 8 UNION ALL\r\n" + 
			"  SELECT 9 UNION ALL\r\n" + 
			"  SELECT 10\r\n" + 
			") NS\r\n" + 
			"INNER JOIN item_group_jobwork_sales_acc B ON NS.n <= CHAR_LENGTH(B.jw_item_array) - CHAR_LENGTH(REPLACE(B.jw_item_array, ',', '')) + 1\r\n" + 
			"WHERE B.item_group_id IN (SELECT item_group FROM item_master WHERE `item_id`=:item_id) AND B.modified_type='INSERTED') AS service LEFT JOIN item_service_master AS service1 ON service.service_id=service1.service_id",nativeQuery=true)
	List<Map<String,Object>> getJobworklist(@Param("item_id") String item_id);
	
}
