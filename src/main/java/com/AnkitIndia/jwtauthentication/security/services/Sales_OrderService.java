package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Trans_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Sales_transaction;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_OrderDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.ResponsemsgDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_DocsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Trans_InfoDTO;

public interface Sales_OrderService {
	
	public SalesSequenceIdDTO getSalesOrdSequenceId(String prefix,String orderdate,String type);
	
	public SalesSequenceIdDTO getSalesOrdSequenceIdWarehouse(String prefix,String orderdate);

	public Sales_Order save(Sales_Order sales_Order);
	
	public Sales_Order createEffectiveSalesOrder(Sales_Order sales_Order);
	
	public Sales_Order updateEffectiveSalesOrder(Sales_Order iMaster,Long id);
	
	public Sales_Order update(Sales_Order iMaster,Long id);
	
	public Sales_Order updateEffectiveSalesOrderWithLoading(Sales_Order iMaster,Long id);
	
	public Sales_Order updateSalesOrderWithLoading(Sales_Order iMaster,Long id);
	
	public StatusDTO updateSalesOrderWithLoadingItemDetails(String orderid);
	
	public Sales_Order deleteSalesOrder(Sales_Order sOrder,Long id);
	
	public StatusDTO SalesOrderTerminate(long id,String username,String quotationid);
	
	public Sales_Order deleteEffectiveSalesOrder(Sales_Order sOrder,Long id);
	
	public List<Sales_OrderDTO> findAll();
	
	public List<Sales_OrderDTO> getliewterminationsalelist();
	
	public List<Sales_OrderDTO> getSalesOrders(String party,String invdate,String comp);
	
	public List<Map<String,Object>> getSalesOrderReturn(String party,String invdate,String comp);
	
	public List<Sales_OrderDTO> getDeliverySalesOrderUpdate(Long id);
	
	public Sales_Order_Trans_Chgs_dyn  getsaleordercharges(String transporter_Id,String referance_id);
	
	public List<Sales_OrderDTO> findSalesOrders(String bunit,String party,String invdate);
	
	public List<Map<String,Object>> findSalesOrdersModified(String bunit,String party,String invdate);
	
	public List<Map<String,Object>> findSalesOrdersRefraction(String bunit,String party,String invdate);
	
	
	public List<Sales_OrderDTO> salesOrderList();
	
	public Sales_OrderDTO getSalesOrderDetails(String orderid);
	
	public List<Sales_OrderDTO> salesOrderByParty(String custid);
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtls(String order_id);
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsNew(String order_id);
	
	public List<Sales_Order_Item_Dtls> getSalesOrdItemDtlsRefraction(String order_id);
	
	public List<Map<String,Object>> getSalesOrderList(String salesprocess, String fin_year);
	
	public  List<Map<String,Object>> getSalesOrdItemDtlsJobwork(String order_id);
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsThruLoad(String order_id);
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrderItemDtls(String order_id);
	
	public List<Sales_Order_Broker_DtlsDTO> getSalesOrdBrokerDtls(String order_id);
	
	public List<Sales_Order_Summary_dynDTO> getSalesOrdSummDyna(String order_id);
	
	public List<Map<String,Object>> getSalesOrdJobItemDtls(String orderid);
	
	public List<Map<String,Object>> getSalesOrdTServiceItem(String orderid);
	
	public List<Sales_Order_Party_DtlsDTO> getSalesOrdPartyDtls(String order_id);
	
	public List<Sales_Order_DocsDTO> getSalesOrdDocs(String order_id);
	
	public Sales_Order_Shipment_DtlsDTO getSalesOrdShipDtls(String order_id);
	
	public Sales_Order_Trans_InfoDTO getSalesOrdTransInfo(String order_id);
	
	public Sales_Order_SummaryDTO getSalesOrdSumm(String order_id);
	
	public Sales_Order_Terms_ConDTO getSalesOrdTermsCon(String order_id);
	
	public Sales_Order findOne(long id);
	
	public Sales_transaction getSalesStockDetails(String orderid,String bunit,String itemid,String packingid);
	
	public Sales_transaction getSalesStockDetailsThruLoad(String orderid,String bunit,String itemid,String packingid,String loadingid);
	
	public List<Sales_Order> getreturnapprovalsalesorder(Long id);
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsUpdate(Long order_id);
	
	public StatusDTO getLoadingRestWeight(String orderid,String itemid,Double packing_qty);
	
	public StatusDTO getLoadingRestWeightJobwork(String orderid,String itemid,Double packing_qty,String packing,String party);
	
	public StatusDTO getLoadingRestWeightJobworkrectify(String pur_cust_refdocno,Double item_qty);
	
	public StatusDTO checkjobworkrestwt(String advice_id);
	
	public StatusDTO getLoadingRestWeightJobWorkAllocation(String itemid,String party,Double item_qty);
	
	public StatusDTO getLoadingRestWeightupdate(String orderid,String itemid,Double packing_qty,String advice_id,String packing,String alter_item_code,String alter_packing);
	
	public StatusDTO getLoadingRestWeightJobworkupdate(String orderid,String itemid,Double packing_qty,String advice_id,String packing,String party);
	
	public StatusDTO getLoadingRestWeightupdatelooseitem(String orderid,String itemid,Double packing_qty,String advice_id,String packing);
	
	public List<Sales_Order_Pagination_DTO> findsaleorder(String order_id);
	
	//public List<Sales_Order_Pagination_DTO> searchsaleorder(String orderno,String fromdate, String todate,String customername,String finyear);
	public List<Sales_OrderDTO> searchsaleorder(String orderno,String fromdate, String todate,String customername,String finyear);
	//public List<Map<String,Object>> searchsaleorder(String orderno,String fromdate, String todate,String customername,String finyear);
	
	public List<Sales_OrderDTO> getSaleOrderList(String currDate,String finyear);
	
	public List<Map<String,Object>> getSaleOrderFastList(String currDate,String finyear);
	
	public StatusDTO getnumtowordsaleorder(String orderid);
	
	public ResponsemsgDTO checkcustomeramount(String orderid,String finyear);
	
	public Sales_Order getSalesOrderDetailsthdeliverchallan(String deliveryid);
	
	public List<Map<String, Object>> getsaleorderjobworkprice(String deliveryid);
	
	public Sales_Order updatesalesorder(Long id);
	
	public Item_rate_dtls getRateChartItemSO(String itemid,String packingcode,String company,String business_unit,String order_date,String pricebasedon);
	
	public List<Item_rate_dtls> getRateChartItemSOforItem(String itemid,String packingcode,String company,String business_unit,String order_date);
	
	public List<Map<String, Object>> getSalesOrderSummaryCatagorywiseList(String catagory,String catagoryname,String fromdate,String todate,String bunit);

	public List<Map<String, Object>> getSalesOrderMiscList(String catagory,String fromdate,String todate,String bunit,String broker,String customer);
	
	public List<Map<String, Object>> salesOrderFinishedItemlist(String company,String fromdate,String todate,String business_unit);
	
	public List<Map<String, Object>> salesOrderBrokerlist(String company,String fromdate,String todate,String business_unit);
		
	public List<Map<String, Object>> salesOrderPartylist(String company,String fromdate,String todate,String business_unit);
	
	public List<Map<String,Object>> getSalesOrdTransChgsDynList(String ordid);
	
	public Map<String,Object> getSalesOrderData(String referance_id, String delivery_cid);
	
	
	public Map<String,Object> getdeliverychallanData(String delivery_cid);
	
	public List<Map<String,Object>> transporterNameChgsList(String orderid);
	
	public List<Map<String,Object>> getSalesOrdItemDtlsnew(String orderid);
	
	public List<Map<String, Object>> getSalesOrderReport(String fromdate, String todate);
	
	public List<Map<String, Object>> getSalesOrderReportOrderWise(String orderno);
	
	public List<Map<String, Object>> findJobSalesOrders(String bunit,String party,String advdate);
	
	public List<Map<String, Object>> getTrialdata(String fromdate,String todate);
	
	public List<Map<String, Object>> getSOjwRestQty(String order_id,String item_id,String item_code);
	
	public List<Map<String, Object>> getWeighmentReportForAnujSir(String fromdate,String todate);
	
	public List<Map<String, Object>> getDelvChallanByOrder(String salesid,String fyear);
	
	public List<Map<String, Object>> getSaleOrderItemThroughGrn(String salesid,String grnid);
	
	public Map<String, Object> getGrnWeighment(String grnid);
	
}
