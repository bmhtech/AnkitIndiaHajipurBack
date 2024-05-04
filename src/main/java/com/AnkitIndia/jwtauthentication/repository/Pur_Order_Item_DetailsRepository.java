package com.AnkitIndia.jwtauthentication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_Item_Details;

public interface Pur_Order_Item_DetailsRepository extends JpaRepository<Pur_Order_Item_Details, Long>{
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :porderid and p.modified_type = 'INSERTED' ")
	List<Pur_Order_Item_Details> getPurOrdItemList(@Param("porderid") String porderid);
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :orderid and p.item_code =:itemcode and p.modified_type = 'INSERTED' ")
	Pur_Order_Item_Details getPurOrdItemDtls(@Param("orderid") String orderid,@Param("itemcode") String itemcode);
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :orderid and p.item_code =:itemcode and p.packing_item=:packing and p.modified_type = 'INSERTED' ")
	Pur_Order_Item_Details getPurOrdItemDtlsnew (@Param("orderid") String orderid,@Param("itemcode") String itemcode,@Param("packing") String packing);
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :orderid and p.modified_type = 'INSERTED' ")
	Pur_Order_Item_Details getPurOrdItemDtlsnewpoitemnumber (@Param("orderid") String orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Item_Details w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Item_Detailsupdate(@Param("pur_orderid") String pur_orderid);
	
	@Query("select p from Pur_Order_Item_Details p where p.modified_type = 'INSERTED' ORDER BY p.slno")
	List<Pur_Order_Item_Details> getPurOrdItemDtlsList();
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :ccc and p.modified_type = 'INSERTED' ORDER BY p.slno")
	List<Pur_Order_Item_Details> getPurOrdCNQUPList(@Param("ccc") String ccc);
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :code and p.modified_type = 'INSERTED' ORDER BY p.slno")
	List<Pur_Order_Item_Details> purOrdItemRetriveList(@Param("code") String code);
	
	@Query(value="SELECT p.*,i.hsn_code FROM pur_order_item_details p LEFT JOIN item_master i ON p.item_code=i.item_id WHERE p.pur_orderid = :code AND p.modified_type = 'INSERTED' AND i.modified_type='INSERTED' ORDER BY p.slno", nativeQuery=true)
	List<Map<String,Object>> purOrdItemwtHSNRetriveList(@Param("code") String code);
	
	//@Modifying(clearAutomatically = true)
   // @Formula("UPDATE Pur_Order_Item_Details p SET p.final_mat_wt+=:final_mat_wt WHERE p.id = :id" )
    //int updateTotalItemQuantity(@Param("final_mat_wt") Double final_mat_wt,@Param("id") int id);
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :code")
	List<Pur_Order_Item_Details> getPurOrdcheckingwAdvice(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Item_Details w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Item_DetailsUpdate(@Param("pur_orderid") String pur_orderid);
	
	
	@Query(value="SELECT p.pur_bill_no as pur_bill_no,w.pur_order_no as pur_order_no,w.pur_orderid as pur_orderid,w.advicenumber as advicenumber,w.partyname as partyname,w.vehicleno as vehicleno,w.grossweight as grossweight,w.netweight as netweight,w.digital_weight as digital_weight,w.digital_weight1 as digital_weight1,w.weighmentdate as weighmentdate,d.adv_item_name as adv_item_name,d.adv_packing_item_name as adv_packing_item_name,d.passed_packing_qty as passed_packing_qty,d.passed_item_qty as passed_item_qty,d.unit_rate as unit_rate,d.price_based_on as price_based_on,d.gross_amt as gross_amt,p.discount as discount,p.qc_deduction as qc_deduction,Dala_Charges_Driver,UP_Front_Brokerage,WB_Truck_Charges_Driver,Deduction_From_Supplier,FREIGHT_ADV,MOISTURE,DALA_CHARGES,UP_BROKERAGE,QUALITY_CLAIM,WB_CHARGES,OFFICE_EXP,HDPE_BAG,TDS_194Q,FREIGHT_ADVANCE , p.add_tax as add_tax,p.add1 as addplus,p.add2 as addminus,p.roundoff_amt as roundoff_amt,p.payable_party as payable_party FROM pur_bill p , wheat_inward_view w , purbill_weigment b,pur_bill_item_details d WHERE p.modified_type='INSERTED' AND p.adviceno=w.advicenumber AND p.pur_bill_no = b.pur_bill_no AND p.pur_bill_id=d.pur_bill_id AND p.modified_type=d.modified_type and w.pur_orderid =:porderid ",nativeQuery=true)
	List<Map<String, Object>> getpoitemdetailsreport(@Param("porderid") String porderid);
	

	//@Query(value="SELECT p.pur_bill_no as pur_bill_no,w.pur_order_no as pur_order_no,w.pur_orderid as pur_orderid,w.advicenumber as advicenumber,w.partyname as partyname,w.vehicleno as vehicleno,w.grossweight as grossweight,w.netweight as netweight,w.digital_weight as digital_weight,w.digital_weight1 as digital_weight1,w.weighmentdate as weighmentdate,d.adv_item_name as adv_item_name,d.adv_packing_item_name as adv_packing_item_name,d.passed_packing_qty as passed_packing_qty,d.passed_item_qty as passed_item_qty,d.unit_rate as unit_rate,d.price_based_on as price_based_on,d.gross_amt as gross_amt,p.discount as discount,p.qc_deduction as qc_deduction,Dala_Charges_Driver,UP_Front_Brokerage,WB_Truck_Charges_Driver,Deduction_From_Supplier,FREIGHT_ADV,MOISTURE,DALA_CHARGES,UP_BROKERAGE,QUALITY_CLAIM,WB_CHARGES,OFFICE_EXP,HDPE_BAG,TDS_194Q,FREIGHT_ADVANCE , p.add_tax as add_tax,p.add1 as addplus,p.add2 as addminus,p.roundoff_amt as roundoff_amt,p.payable_party as payable_party FROM pur_bill p , wheat_inward_view w , purbill_weigment b,pur_bill_item_details d WHERE p.modified_type='INSERTED' AND p.adviceno=w.advicenumber AND p.pur_bill_no = b.pur_bill_no AND p.pur_bill_id=d.pur_bill_id AND p.modified_type=d.modified_type",nativeQuery=true)
	//@Query(value="SELECT SUBSTRING(p.pur_bill_no,18,20) AS pur_bill_no,w.pur_orderid AS pur_orderid,SUBSTRING(w.advicenumber,16,18) AS advicenumber,w.vehicleno AS vehicleno,w.grossweight AS grossweight,w.netweight AS netweight,w.digital_weight AS digital_weight,w.digital_weight1 AS digital_weight1,w.weighmentdate AS weighmentdate,CASE WHEN (SELECT alt_name FROM item_master WHERE item_name=d.adv_item_name) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_name=d.adv_item_name) END AS adv_item_name,CASE WHEN (SELECT alt_name FROM item_master WHERE item_name=d.adv_packing_item_name) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_name=d.adv_packing_item_name) END AS adv_packing_item_name,d.passed_packing_qty AS passed_packing_qty,d.passed_item_qty AS passed_item_qty,d.unit_rate AS unit_rate,d.gross_amt AS gross_amt,p.discount AS discount,p.qc_deduction AS qc_deduction,Dala_Charges_Driver,UP_Front_Brokerage,WB_Truck_Charges_Driver,Deduction_From_Supplier,FREIGHT_ADV,MOISTURE,DALA_CHARGES,UP_BROKERAGE,QUALITY_CLAIM,WB_CHARGES,OFFICE_EXP,HDPE_BAG,TDS_194Q,FREIGHT_ADVANCE , p.add_tax AS add_tax,p.add1 AS addplus,p.add2 AS addminus,p.roundoff_amt AS roundoff_amt,p.payable_party AS payable_party FROM pur_bill p , wheat_inward_view w , purbill_weigment b,pur_bill_item_details d WHERE p.modified_type='INSERTED' AND p.adviceno=w.advicenumber AND p.pur_bill_no = b.pur_bill_no AND p.pur_bill_id=d.pur_bill_id AND p.modified_type=d.modified_type",nativeQuery=true)
	@Query(value="SELECT SUBSTRING(p.pur_bill_no,18,20) AS pur_bill_no,w.pur_orderid AS pur_orderid,SUBSTRING(w.advicenumber,16,18) AS advicenumber,w.vehicleno AS vehicleno,w.grossweight AS grossweight,w.netweight AS netweight,w.digital_weight AS digital_weight,w.digital_weight1 AS digital_weight1,w.weighmentdate AS weighmentdate,CASE WHEN (SELECT alt_name FROM item_master WHERE item_name=d.adv_item_name) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_name=d.adv_item_name) END AS adv_item_name,CASE WHEN (SELECT alt_name FROM item_master WHERE item_name=d.adv_packing_item_name) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_name=d.adv_packing_item_name) END AS adv_packing_item_name,d.passed_packing_qty AS passed_packing_qty,d.passed_item_qty AS passed_item_qty,d.unit_rate AS unit_rate,d.gross_amt AS gross_amt,p.discount AS discount,p.qc_deduction AS qc_deduction,IFNULL(z.Dala_Charges_Driver,0) as Dala_Charges_Driver ,IFNULL(z.UP_Front_Brokerage,0) as UP_Front_Brokerage ,IFNULL(z.WB_Truck_Charges_Driver,0) as WB_Truck_Charges_Driver,IFNULL(z.Deduction_From_Supplier,0) as Deduction_From_Supplier,IFNULL(z.FREIGHT_ADV,0) as FREIGHT_ADV,IFNULL(z.MOISTURE,0) as MOISTURE,IFNULL(z.DALA_CHARGES,0) as DALA_CHARGES,IFNULL(z.UP_BROKERAGE,0) as UP_BROKERAGE,IFNULL(z.QUALITY_CLAIM,0) as QUALITY_CLAIM,IFNULL(z.WB_CHARGES,0) as WB_CHARGES,IFNULL(z.OFFICE_EXP,0) as OFFICE_EXP,IFNULL(z.HDPE_BAG,0) as HDPE_BAG,IFNULL(z.TDS_194Q,0) as TDS_194Q,IFNULL(z.FREIGHT_ADVANCE,0) as FREIGHT_ADVANCE,p.add_tax AS add_tax,p.add1 AS addplus,p.add2 AS addminus,p.roundoff_amt AS roundoff_amt,p.payable_party AS payable_party FROM wheat_inward_view w LEFT JOIN pur_bill p ON w.advicenumber=p.adviceno LEFT JOIN purbill_weigment z ON p.pur_bill_id=z.pur_bill_id LEFT JOIN pur_bill_item_details d ON p.pur_bill_id=d.pur_bill_id AND p.`modified_type`= d.`modified_type`  WHERE p.`modified_type`='INSERTED' AND w.`pur_orderid` in(:list)",nativeQuery=true)
	List<Map<String, Object>> getpoitemdetailsreportnew(@Param("list") List<String> list);
	
	
	@Query(value="SELECT d.total_qty AS puritemqty,SUM(b.purbillitemqty) AS purbillitemqty,d.pur_orderid AS pur_orderid,(d.total_qty - SUM(b.purbillitemqty)) AS balanceqty,d.pur_order_no AS pur_order_no FROM pur_order d,purbill_wt_po b  WHERE d.pur_orderid=b.pur_orderid AND d.modified_type='INSERTED' AND d.pur_orderid=:porderid",nativeQuery=true)
	List<Map<String, Object>> getExecutionpo(@Param("porderid") String porderid);
	
	
	@Query(value="SELECT  d.item_code,d.item_name,d.classified_item_name,d.packing_item,d.packing_item_name,d.packing_item_code,d.packing_size,d.packing_weight,d.packing_type,d.packing_uom,IF(`d`.`price_based_on`='Packing',v.rest_wt, d.packing_qty) AS packing_qty,d.stock_uom,IF(`d`.`price_based_on`='Packing',d.stock_qty, v.rest_wt) AS stock_qty,d.price,d.mat_weight,d.con_factor,d.price_based_on,d.amount,d.discount,d.discount_basedon,d.discount_amount,d.net_amount,d.tax_code,d.tax_rate,d.tax_amount,d.total_amount,d.qc_norms,d.priority,d.delivery_date,d.purpose,d.to_be_used,d.remarks,v.rest_wt AS final_mat_wt,d.no_advice_cal,d.id FROM pur_order_item_details d,pur_order_store_item_view v WHERE d.`modified_type`='INSERTED' AND d.`pur_orderid`=v.pur_orderid AND d.`item_code`=v.item_code AND d.`packing_item`=v.packing_item AND d.`classified_item_name`=v.classified_item_name and d.pur_orderid=:porderid",nativeQuery=true)
	List<Map<String, Object>> getpurorderstorepurchase(@Param("porderid") String porderid);
	
	@Query(value="SELECT  d.item_code,d.item_name,d.classified_item_name,d.packing_item,d.packing_item_name,d.packing_item_code,d.packing_size,d.packing_weight,d.packing_type,d.packing_uom,IF(`d`.`price_based_on`='Packing',v.rest_wt, d.packing_qty) AS packing_qty,d.stock_uom,IF(`d`.`price_based_on`='Packing',d.stock_qty, v.rest_wt) AS stock_qty,d.price,d.mat_weight,d.con_factor,d.price_based_on,d.amount,d.discount,d.discount_basedon,d.discount_amount,d.net_amount,d.tax_code,d.tax_rate,d.tax_amount,d.total_amount,d.qc_norms,d.priority,d.delivery_date,d.purpose,d.to_be_used,d.remarks,v.rest_wt AS final_mat_wt,d.no_advice_cal,d.id FROM pur_order_item_details d,pur_order_packing_item_view v WHERE d.`modified_type`='INSERTED' AND d.`pur_orderid`=v.pur_orderid AND d.`item_code`=v.item_code AND d.`packing_item`=v.packing_item AND d.`packing_item_code`=v.packing_item_code AND d.`packing_size`=v.packing_size AND d.`packing_weight`=v.packing_weight AND d.`packing_type`=v.packing_type AND d.pur_orderid=:porderid",nativeQuery=true)
	List<Map<String, Object>> getpurorderpacking(@Param("porderid") String porderid);
	
	@Query(value="select * from pur_order_item_details p where p.pur_orderid = :orderid and p.item_code =:itemcode and p.modified_type = 'INSERTED' ", nativeQuery = true)
	List<Map<String,Object>> getPurOrdItemDtlsMultipleItemGRN (@Param("orderid") String orderid,@Param("itemcode") String itemcode);
	
	
}
