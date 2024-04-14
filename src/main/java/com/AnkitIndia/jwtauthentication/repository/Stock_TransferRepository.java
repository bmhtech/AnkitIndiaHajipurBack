package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Stock_transfer_terminations_dyn;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_resource_costDTO;

public interface Stock_TransferRepository extends JpaRepository<Stock_Transfer, Long>{
	
	@Query("select COUNT(id) from Stock_Transfer")
	String countId();
	
	@Query("select COUNT(id) from Stock_Transfer where order_date=:sdate and order_point =:ordpoint")
	String getSTOSequenceId(@Param("sdate") String sdate,@Param("ordpoint") String ordpoint);
	
	@Query("select c from Stock_Transfer c where c.modified_type = 'INSERTED' ORDER BY c.order_id DESC")
	List<Stock_Transfer> findAllStkTransOrds();
	
	@Query("select c from Stock_Transfer c where c.modified_type = 'INSERTED' ")
	List<Stock_Transfer> getStkTrans();
	
	@Query("select c from Stock_Transfer c where c.modified_type = 'INSERTED' and c.weightment_req='No' and c.stk_grn_status=0")
	List<Stock_Transfer> getStkTransNew();
	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_id = :order_id")
	Stock_Transfer getStockTransDtls(@Param("order_id") String order_id);
	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_point ='Inter' ")
	List<Stock_Transfer> getStockTransThruInter();
	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_point ='Inter' and s.order_date <=:tdate and s.business_unit =:bunit")
	List<Stock_Transfer> getStockTransOrdsInter(@Param("tdate") String tdate,@Param("bunit") String bunit);
	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_point ='Inter' and s.weightment_req='Yes' and s.order_date <=:tdate and s.business_unit =:bunit and s.loadunload_status = 0")
	List<Stock_Transfer> getStockTransOrdsInterNew(@Param("tdate") String tdate,@Param("bunit") String bunit);
	
	
	//@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_point ='Intra' and s.shipment_mode ='By Vehicle' and s.order_date <=:tdate and s.business_unit =:bunit")	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_point ='Intra' and s.shipment_mode ='By Vehicle' and s.weightment_req='Yes' and s.order_date <=:tdate and s.business_unit =:bunit")
	List<Stock_Transfer> getStockTransOrdsIntra(@Param("tdate") String tdate,@Param("bunit") String bunit);
	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_point ='Intra' and s.shipment_mode ='By Vehicle' and s.weightment_req='Yes' and s.order_date <=:tdate and s.business_unit =:bunit and s.loadunload_status = 0")
	List<Stock_Transfer> getStockTransOrdsIntraNew(@Param("tdate") String tdate,@Param("bunit") String bunit);
	
	@Query("select s from Stock_Transfer s where s.id =:id")
	Stock_Transfer getStockTransferDetails(@Param("id") Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer_Item_Dtls w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int stockTransferItemDetailsUpdate(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer_Trans_Info w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int stockTransferTransInfoUpdate(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer_Summary w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int stockTransferSummaryUpdate(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer_Summary_dyn w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int stockTransferSummaryDynUpdate(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_resource_cost w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int stockTransferResourceCostUpdate(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_doc w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int stockTransferDocUpdate(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_terminations w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int stockTransferTerminationsUpdate(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_terminations_dyn w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id and w.modified_type = 'INSERTED'")
    int StockTransferTerminationsDynUpdate(@Param("order_id") String order_id);
	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_id = :stk_challan_id")
	Stock_Transfer getStkOrder(@Param("stk_challan_id") String stk_challan_id);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer w SET w.loadunload_status =1 WHERE w.order_id = :refid and w.modified_type = 'INSERTED'")
    int updateLoadingAdvice(@Param("refid") String refid);
	
	@Query("select s from Stock_Transfer s where s.modified_type = 'INSERTED' and s.order_id = :order_id")
	Stock_Transfer getReceivingBuLoadingAdvice(@Param("order_id") String order_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer w SET w.stk_grn_status =1 WHERE w.order_id = :refid and w.modified_type = 'INSERTED'")
    int updateStockChallanStatus(@Param("refid") String refid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer w SET w.stk_grn_status =0 WHERE w.order_id = :refid and w.modified_type ='INSERTED' and w.stk_grn_status =1")
    int revertStockChallanStatus(@Param("refid") String refid);
	
	
}