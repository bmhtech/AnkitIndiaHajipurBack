package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_BPDetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_TerminationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_app_chgsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_OrderDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Order_Termination_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Order_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;

public interface Pur_OrderService {
	
	public SequenceIdDTO getPOSequenceId(String prefix,String orderdate,boolean orderfor,String potype,String posubtype);

	public List<Map<String,Object>> getJwPo();
	
	public String getpssd_item_qty(String unloadadvice,String weighmentdata,String purchaseid);
	
	public double getpssd_item_qtyrectify(String unadviceid,String pricebasedon,String subtypestatus);
	
	public String getpssd_packing_qty(String unloadadvice,String weighmentdata,String purchaseid);
	
	public double getpssd_packing_qtyrectify(String unadviceid,String pricebasedon,String subtypestatus);
	
	public String getpssd_item_qty_multi_popup(String unloadadvice);
	
	public String getpssd_packing_qtymultiplepopup(String unloadadvice);
	
	public Pur_Order retrivePurchaseGoodReceiptPopup(Long Id);
	//public Pur_Order save(Pur_Order pOrder);
	
	public Pur_Order save(Pur_Order pOrder,MultipartFile files[]) throws IOException;
	
	public List<Map<String,Object>> transporterNameChgsPurList (String purid);
	
	public Pur_Order update(Pur_Order iMaster,Long id);
	
	public List<Pur_Order> findAllOrder();
	
	public Pur_Order_Trans_Chgs_dyn  getpurchaseordercharges(String transporter_Id,String referance_id);
	
	public Page<Pur_Order_Pagination_DTO> getPurOrderPagination(int page,int size);
	
	public List<Map<String, Object>> getpoitemdetailsreport(String poid);
	
	public List<Map<String, Object>> getExecutionpo(String poid);
	
	
	public List<Pur_Order_Pagination_DTO> searchPurchaseOrder(String orderno,String fromdate, String todate,String supplier_name1,String finyear);
	
	public List<Map<String,Object>> searchPurchaseOrderFast(String orderno,String fromdate, String todate,String supplier_name1,String finyear);
	
	public List<Map<String,Object>> getdocumentno();
	
	public List<Map<String,Object>> purchaseorderlist (String curdate);
	
	public List<Pur_Order> getPurOrd(String pur_order_id);
	
	public Pur_Order findOne(long id);
	
	 public List<Pur_Order_Item_DetailsDTO> getPurOrdItemDtlsList();
	 
	 public List<Pur_Order_brokerDTO> getPurOrdBrokerList(String pur_order_no);
	 
	 public Pur_Order_Trans_InfoDTO getPurOrdTrans(String pur_order_no);
	 
	 public List<Pur_OrderDTO> getPurOrdDDCList(String pur_order_no);
	 
	 public List<Map<String,Object>> getPurOrdTransChgsDynList(String pur_order_no);
	 
	 public List<Map<String,Object>> getPurOrdStoreDynList(String pur_order_no);
	 
	 public List<Pur_Order_Item_DetailsDTO> getPurOrdCNQUPList(String ccc);
	 
	//public List<Pur_Order_Item_DetailsDTO> getpurorderstorepurchase(String orderid);
	 
	 public List<Map<String, Object>> getpurorderstorepurchase(String orderid);
	 
	 //public List<Pur_Order_Item_DetailsDTO> getpurorderpacking(String orderid);
	 
	 public List<Map<String, Object>>getpurorderpacking(String orderid);
	 
	 public List<Pur_OrderDTO> getPurOrdAllList();
	 
	 public Pur_OrderDTO getPurOrdDetails(String orderid);
	 
	 public List<Wm_unload_advice_item_dtlsDTO> getPurOrdcheckingwAdvice(String pur_no,String unadvice_id);
	 
	 public Pur_OrderDTO wnUnAdvicebalancedtotalquantity(String orderid);
	 
	 public List<Pur_Order_Item_DetailsDTO> purOrdItemRetriveList(String code);
	 
	 public List<Pur_Order_app_chgsDTO> getPurOrdAppChgs(String orderid);
	 
	 public List<Pur_Order_Termination_dynDTO> getPurOrdTermList(String orderid);
	 
	 public Pur_Order_BPDetailsDTO purOrdBPDRetriveList(String code);
	 
	 public Pur_Order_TerminationDTO purOrdTerminateRetriveList(String code);
	 
	 public List<Pur_Order_DocDTO> purOrdDocRetriveList(String code);
	 
	 public Pur_Order_Terms_ConDTO purOrdTransConRetriveList(String code);
	 
	 public List<Pur_OrderDTO> getPurOrdThruSuppList(String suppid);
	 
	 public List<Pur_OrderDTO> getPurOrdAdvThruSupp(String suppid,String businessunit);
	 
	 public List<Pur_OrderDTO> getGrnThroughPurOrd(String businessunit,String pur_type);
	 
	 public List<Pur_OrderDTO> getGrnThroughPurOrdstore(String businessunit,String pur_type);
	 
	 public List<Pur_OrderDTO> getGrnThroughPurOrdpacking(String businessunit,String pur_type);
	 
	 
	 public List<Pur_OrderDTO> getPurOrdRtnApp(String bunit,String supplier,String tdate,String company,String finyear);
	 
	 public List<Pur_OrderDTO> getPurOrdThruSuppAdvReq(String suppid);
	 
	 public Pur_Order_Item_DetailsDTO getPurOrdItemDtls(String orderid,String itemcode);
	 
	 public Pur_Order_Item_DetailsDTO getPurOrdItemDtlsnew(String orderid,String itemcode,String packing);
	 
	 public Pur_Order_Item_DetailsDTO getPurOrdItemDtlsnewpoitemnumber(String orderid);
	 
	 public Pur_Order_Item_DetailsDTO getPurOrderItemWeightViewList(String orderid,String itemcode);
	 
	 
	 public Pur_Order_Item_DetailsDTO getPurchaseOrdernetWeightnew(String orderid,String itemcode,String packing_item);
	 
	 public List<Pur_OrderDTO> getPurOrdreceipt_criteria(String orderid);
	 
	 public StatusDTO checkPurchaseOrderUsage(String code);
	 
	 public Pur_Order deletePurchaseOrder(Pur_Order pur_Order,long id);
	 
	 public List<Pur_OrderDTO> getPurchaseOrderList(String currDate,String finyear);
	 
	 public List<Map<String, Object>> getPurchaseOrderListFastApi(String currDate,String finyear);
	 
	 public List<Map<String, Object>> getPurchaseordermiscreport(String business_unit,String catagory,String fromdate,String todate,String supplier_name,String ven_code_name,String statustype);
	 
	 public List<Map<String, Object>> getLastPOThruSupItemDtls(String supplier_name);
	 
	 public List<Map<String, Object>> getLastPOThruSupPurDtls(String supplier_name);
	 
	 public List<Map<String, Object>> getPendingSoudaReport(String fromdate,String todate,String catagory);
	 
	 public List<Map<String, Object>> purchaseOrderSupplierNamesList(String company,String fromdate,String todate,String business_unit);
		
	 public List<Map<String, Object>> purchaseOrderBrokerNamesList(String company,String fromdate,String todate,String business_unit);
	 
	 public List<Map<String, Object>> getPurOrderReport(String fromdate,String todate);
	 
	 public Map<String, Object> getPurOrderTransChgsData(String referance_id,String grnid);
	 
	 public List<Map<String, Object>> getPurOrderDetailsThroughOrderId(String orderid);
	 
	 public List<Map<String, Object>> getTermsConditionsDtlsList();
  
	 public List<Map<String,Object>> getPurOrdTermsCondDynList(String code);
  
	 public List<Map<String, Object>> purOrdTramsCondition(String purid);
	 
	 public List<Map<String, Object>> purOrdItemwtHSNRetriveList(String code);
	 
	 //public List<Map<String, Object>> getPurOrdListOnlyStorePurchase(String supplier,String businessunit);
	
	 public List<Pur_OrderDTO> getPurOrdListOnlyStorePurchase(String supplier,String businessunit);
	 
	 public List<Pur_OrderDTO> getPurOrdListOnlyPacking(String supplier,String businessunit);
	 
	 public Map<String,Object> getStoreChargesPo(String grnid,String referance_type);
	 
	 
}
