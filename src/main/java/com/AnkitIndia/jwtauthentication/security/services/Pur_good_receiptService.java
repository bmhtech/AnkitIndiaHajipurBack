package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_item_details;
import com.AnkitIndia.jwtauthentication.model.Pur_good_reciept_trans_info;
import com.AnkitIndia.jwtauthentication.model.Pur_goods_receipt_other_information;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_BPDetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receiptDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_Business_Partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_resource_costDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_reciept_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_goods_receipt_other_informationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_OrderDTO;

public interface Pur_good_receiptService {
	
	public SequenceIdDTO getGRNSequenceId(String bunit,boolean itype,String ptype,String psubtype,String orderdate);
	

	
	public Pur_good_receipt save(Pur_good_receipt pur_good_receipt);
	
	public List<Pur_good_receipt> findAll();
	
	public Page<Pur_good_receipt_Pagination_DTO> getPurGRNPagination(int page,int size);
	
	public List<Map<String, Object>> getgrnpurbillReport(String fromdate,String todate);
	
	public Pur_good_receipt_item_details gettaxcodefromgrn(String itemcode,String grnid);
	
	
	public Pur_good_receipt_item_details gettaxcodefromgrnnew(String itemcode,String grnid,String packingcode);
	
	public List<Pur_good_receipt_item_details> gettaxcodefromgrnnewMulti(String itemcode,String grnid,String packingcode);
	
	public Pur_good_receipt_item_details gettaxcodefromgrnnewForStore(String itemcode,String grnid,String packingcode,String classified);
	
	public List<Pur_good_receipt_Pagination_DTO> searchGRN(String orderno,String fromdate, String todate,String bus_partner1,String pur_type1,String finyear);
	
	public List<Map<String,Object>> searchGRNFast(String orderno,String fromdate, String todate,String bus_partner1,String pur_type1,String finyear);
	
	public List<Pur_good_receiptDTO> getPurGoodRcptList();
	
	public List<Pur_good_receiptDTO> getPurGoodRcptThruSupp(String suppid,boolean itype,String ptype,String psubtype);
	
	public List<Pur_good_receiptDTO> getPurGRptRtnApp(String bunit,String supplier,String tdate,String company,String finyear);
	
	public Pur_good_receipt findOne(Long Id);
	
	
	
	
	public Pur_good_receipt update(Pur_good_receipt pur_good_receipt,Long Id);
	
	public Pur_good_receiptDTO getPurGoodRcptDtls(String grnid);
	
	
	public Pur_good_receiptDTO getPurGoodRcptDtlsopengrn(String grnid);

	public Pur_good_receipt_Business_Partner_detailsDTO getPurGoodRcptBPDtls(String grnid);
	
	public List<Pur_good_receipt_item_detailsDTO> getPurGoodRcptItemDtlsList(String grnid);
	
	public List<Map<String, Object>> grnItemDtlsRetriveListFast(String grnid);
	
	public List<Map<String, Object>> getPurGoodRcptItemDtlsListfastapi(String grnid);
	
	public Map<String, Object> grnOtherInfoRetriveListFast(String grnid);
	
	public List<Map<String, Object>> grnBrokerRetriveListFast(String grnid);
	
	public Map<String, Object> grnTransInfoRetriveListFast(String grnid);
	
	public Map<String, Object> grnBPDtlsRetriveListFast(String grnid);
	
	public List<Map<String, Object>> grnResourceCostRetriveListFast(String grnid);
	
	public List<Map<String, Object>> grnDocRetriveListFast(String grnid);
	
	public Map<String, Object> grndriverdetailsFast(String grnid);
	
	public List<Pur_good_receipt_resource_costDTO> grnResourceCostRetriveList(String code);
	 
	public Pur_good_reciept_trans_infoDTO grnTransInfoRetriveList(String code);
	
	
	public Pur_good_receipt_driver_dtls grndriverdetails(String code);
	
	public Pur_goods_receipt_other_informationDTO grnOtherInfoRetriveList(String code);
	
	public List<Pur_good_receipt_docDTO> getPurGoodRcptDocList(String grnid);
	
	public List<Pur_good_receipt_brokerDTO> getPurGoodRcptBrokerList(String grnid); 
	
	public StatusDTO checkGRNUsage(String grnid);
	
	public Pur_good_receipt deleteGRN(Pur_good_receipt grnid,Long id);
	
	public List<Pur_good_receiptDTO> getGRNList(String currDate,String finyear);
	
	public List<Map<String, Object>> getGRNListData(String currDate,String finyear);
	
	public List<Map<String, Object>> getPurchaseTransportReport(String bunit,String fromdate,String todate,String pur_inv_type,String trans_typ);
	
	public List<Map<String, Object>> getGRNThroughUnloadId(String unadvice);
	
	public List<Map<String, Object>> getGrnDetailsThroughGrnId(String grnid);
	
	
	public StatusDTO purchasechecktotaltranslimit(double totalamount,String supplier_name,String finyear);
	
	public StatusDTO purchasechecktotaltranslimitupdate(double totalamount,String supplier_name,String finyear,long id);
	
	public List<Map<String, Object>> getJobWorkAllocationReport(String fromdate,String todate);
	
	public List<Map<String, Object>> searchpendingGRNReport(String fromdate,String todate);
	
}
