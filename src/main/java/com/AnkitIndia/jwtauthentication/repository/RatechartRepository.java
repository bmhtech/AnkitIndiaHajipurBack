package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;
import com.AnkitIndia.jwtauthentication.model.Ratechart;

public interface RatechartRepository extends JpaRepository<Ratechart, Long>
{
	@Query("Select count(id) from Ratechart")
	String countrate();
	
	@Query(value="select id, rate_code, business_unitname, b_unit from ratechart where modified_type='INSERTED' and fin_year=:finyear", nativeQuery=true)
	List<Map<String, Object>> getRateChartList(@Param("finyear") String finyear);
		
	//@Query(value="select * from ratechart where modified_type='INSERTED'", nativeQuery=true)
	@Query(value="SELECT r.id,r.b_unit,r.business_unitname,r.company_id,r.date,r.rate_code,r.rate_id,r.valid_date, (CASE WHEN (r.date<=:sys_date AND r.valid_date>=:sys_date) THEN 1 ELSE 0 END) AS access FROM ratechart r WHERE r.modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> RateChartList(@Param("sys_date") String sys_date);
	
	@Query(value="select count(id) from ratechart where modified_type='INSERTED' and date<=:date AND valid_date>=:date", nativeQuery=true)
	int checkDate(@Param("date") String date);
	
	@Query(value="select count(id) from ratechart where modified_type='INSERTED' and date=:date", nativeQuery=true)
	List<Map<String, Object>> getItemdtlsthroughDate(@Param("date") String date);
	
	@Query(value="select case when count(id)>0 then(select rate_code from ratechart where modified_type='INSERTED' and date<:date order by date desc limit 1 ) else 'NA' end as response from ratechart where modified_type='INSERTED' AND date<:date ", nativeQuery = true)
	String checkRateChartExist(@Param("date") String date);
	
	@Query("select b from Item_rate_dtls b where b.rate_code=:res and b.modified_type='INSERTED' order by b.sl_no ")
	List<Item_rate_dtls> getItemList(@Param("res") String res);
	
	@Query(value="SELECT i.* FROM ratechart r, item_rate_dtls ir, item_master i WHERE r.modified_type='INSERTED' AND ir.modified_type='INSERTED' AND r.modified_type=ir.modified_type AND r.rate_id=ir.rate_id AND r.date<=:curr_date AND r.valid_date>=:curr_date AND ir.sales_status='Open' AND ir.item_code=i.item_id AND i.modified_type = 'INSERTED' AND i.item_active ='1' AND i.sales_item ='1' AND i.item_unit LIKE %:b_unit% AND i.item_name NOT LIKE '%TAX%' AND i.item_type!='PACKING MATERIAL SALE' AND i.item_type!='PACKING ITEMS' GROUP BY i.item_id", nativeQuery=true)
	List<Map<String, Object>> getRateChartItemListReg(@Param("curr_date") String curr_date,
												   @Param("b_unit") String b_unit);
	
	@Query(value="SELECT i.* FROM ratechart r, item_rate_dtls ir, item_master i WHERE r.modified_type='INSERTED' AND ir.modified_type='INSERTED' AND r.modified_type=ir.modified_type AND r.rate_id=ir.rate_id AND r.date<=:curr_date AND r.valid_date>=:curr_date AND ir.sales_status='Open' AND ir.item_code=i.item_id AND i.modified_type = 'INSERTED' AND i.item_active ='1' AND i.item_type = 'FINISHED PRODUCTS' AND i.sales_item ='1' AND i.item_unit LIKE %:b_unit% AND i.item_name LIKE '%TAX%' GROUP BY i.item_id", nativeQuery=true)
	List<Map<String, Object>> getRateChartItemListGst(@Param("curr_date") String curr_date,
												   	  @Param("b_unit") String b_unit);
	
	@Query(value="SELECT i.* FROM ratechart r, item_rate_dtls ir, item_master i WHERE r.modified_type='INSERTED' AND ir.modified_type='INSERTED' AND r.modified_type=ir.modified_type AND r.rate_id=ir.rate_id AND r.date<=:curr_date AND r.valid_date>=:curr_date AND ir.sales_status='Open' AND ir.item_code=i.item_id AND i.modified_type = 'INSERTED' AND i.item_active ='1' AND i.sales_item ='1' AND i.item_unit LIKE %:b_unit% AND i.item_type='PACKING MATERIAL SALE' GROUP BY i.item_id", nativeQuery=true)
	List<Map<String, Object>> getRateChartItemListPms(@Param("curr_date") String curr_date,
												   	  @Param("b_unit") String b_unit);
	
	//@Query(value="SELECT i.* FROM ratechart r, item_rate_dtls i WHERE r.modified_type='INSERTED' AND i.modified_type='INSERTED' AND r.modified_type=i.modified_type AND r.rate_id=i.rate_id AND r.date<=:order_date AND r.valid_date>=:order_date AND i.sales_status='Open' AND i.item_code=:itemid AND i.packing=:packingid", nativeQuery=true)
	@Query(value="SELECT ((i.qty) - (CASE WHEN (SELECT COUNT(s.id)>0 FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation') THEN (SELECT SUM(d.squantity) FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation') ELSE 0 END)) AS packingqty, ((i.qty)-(CASE WHEN (SELECT COUNT(s.id)>0 FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation') THEN (SELECT SUM(d.quantity) FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation') ELSE 0 END)) AS qty, i.price_based_on FROM ratechart r, item_rate_dtls i WHERE r.modified_type='INSERTED' AND i.modified_type='INSERTED' AND r.modified_type=i.modified_type AND r.rate_id=i.rate_id AND r.date<=:order_date AND r.valid_date>=:order_date AND i.sales_status='Open' AND i.item_code=:itemid AND i.packing=:packingid AND i.price_based_on=:pricebasedon", nativeQuery=true)
	Map<String, Object> getRateItemQtySave(@Param("order_date") String order_date,
										   @Param("itemid") String itemid,
										   @Param("packingid") String packingid,
										   @Param("pricebasedon") String pricebasedon);
	
	//@Query(value="SELECT CASE WHEN si.price_based_on='Packing' THEN SUM(i.qty+si.squantity) ELSE SUM(i.qty+si.quantity) END AS qty, i.price_based_on FROM ratechart r, item_rate_dtls i, sales_order s, sales_order_item_dtls si WHERE r.modified_type='INSERTED' AND i.modified_type='INSERTED' AND r.modified_type=i.modified_type AND r.rate_id=i.rate_id AND r.date<=:order_date AND r.valid_date>=:order_date AND i.sales_status='Open' AND i.item_code=:itemid AND i.packing=:packingid AND s.id=:id AND s.order_id=si.order_id AND si.item_code=i.item_code AND si.packing=i.packing", nativeQuery=true)
	@Query(value="SELECT ((i.qty) - (CASE WHEN (SELECT COUNT(s.id)>0 FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation' AND s.id NOT IN (:id)) THEN (SELECT SUM(d.squantity) FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation' AND s.id NOT IN (:id) ) ELSE 0 END)) AS packingqty, ((i.qty)-(CASE WHEN (SELECT COUNT(s.id)>0 FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation' AND s.id NOT IN (:id)) THEN (SELECT SUM(d.quantity) FROM sales_order s,sales_order_item_dtls d WHERE s.ratedateentry<=r.valid_date AND s.ratedateentry>=r.date AND s.modified_type='INSERTED' AND s.modified_type =d.modified_type AND s.order_id=d.order_id AND d.item_code=i.item_code AND d.packing=i.packing AND s.ref_type!='Sales Quotation' AND s.id NOT IN (:id)) ELSE 0 END)) AS qty, i.price_based_on FROM ratechart r, item_rate_dtls i WHERE r.modified_type='INSERTED' AND i.modified_type='INSERTED' AND r.modified_type=i.modified_type AND r.rate_id=i.rate_id AND r.date<=:order_date AND r.valid_date>=:order_date AND i.sales_status='Open' AND i.item_code=:itemid AND i.packing=:packingid AND i.price_based_on=:pricebasedon", nativeQuery=true)
	Map<String, Object> getRateItemQtySaleOrder(@Param("order_date") String order_date,
										   @Param("itemid") String itemid,
										   @Param("packingid") String packingid,
										   @Param("id") long id,
										   @Param("pricebasedon") String pricebasedon);
}
