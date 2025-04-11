package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.responseDTO.ChallanpertransportreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Delivery_challan_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_DocsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Delivery_Trans_InfoDTO;

public interface Delivery_challanService {
	
	public SalesSequenceIdDTO getDCSequenceId(String fin_year,String inv_type);
	
	public Delivery_challan save(Delivery_challan dChallan);
	
	public SalesSequenceIdDTO getDCSequenceIdforDefence(String fin_year,String inv_type, String cust_id);
	
	public Page<Delivery_challan_Pagination_DTO> getDeliveryChallans_pagination(int page,int size);
	
	public List<Delivery_challan_Pagination_DTO> searchDeliveryChallan(String orderno,String fromdate, String todate,String party1,String finyear);
	
	public List<Map<String,Object>> searchDeliveryChallanFast(String orderno,String fromdate, String todate,String party1,String finyear);
	
	public Delivery_challan update(Delivery_challan dChallan,Long iMaster);
	
	public Delivery_challan updateDlvChallantransport(Delivery_challan dChallan,Long id);
	
	public StatusDTO updateTransporterDetailsthruPopup(String delv_id,double distance);
	
	public List<Delivery_challan> findAll();
	
	public Delivery_challanDTO getDeliveryChallanDtls(String delivery_cid);
	
	public Map<String,Object> getDeliveryChallanDtlsFast(String delivery_cid);
	
	public List<Delivery_challanDTO> deliveryChallanList(String invtype,String party,String invdate,String comp,String parentmodel);
	
	
	public List<Map<String, Object>> getDelvChallansnew(String invtype,String party,String invdate,String comp,String parentmodel);
	
	public List<Map<String, Object>> getDelvChallansnewjobwork(String invtype,String party,String invdate,String comp,String parentmodel);
	
	
	public List<Delivery_challanDTO> getMultipleDelvChallans(String invtype,String party,String invdate,String comp,String parentmodel);
	
	public List<Delivery_challanDTO> getMultipleDelvChallansUpdate(Long id);
	
	public List<Delivery_challanDTO> getDelvChallansApp(String party,String invdate,String comp,String parentmodel);
	
	public List<Map<String,Object>> getDelvChallansReturnApp(String party,String invdate,String comp,String parentmodel);
	
	public List<Delivery_challanDTO> getMultipleDelvChallansApp(String party,String invdate,String comp,String parentmodel);
	
	public List<Delivery_challan_Item_DtlsDTO> getDlvCItmDtls(String delivery_cid);
	
	public List<Map<String,Object>>getDlvChallanItemjobworkRetriveList(String delivery_cid);
	
	public List<Map<String,Object>>getRestDlvChallanItemDtls(String delivery_cid);
	//public List<Delivery_challan_Item_DtlsDTO> getDlvCItmDtlshsn(String delivery_cid);
	
	public List<Delivery_challan_Party_DtlsDTO> getDlvCPtyDtls(String delivery_cid);
	
	public List<Delivery_challan_Broker_DtlsDTO> getDlvChallanBrokerDtls(String delivery_cid);
	
	public Delivery_challan_Shipment_DtlsDTO getDlvChallanShipmentDtls(String delivery_cid);
	
	public Map<String,Object>getDlvChallanShipmentDtlsFast(String delivery_cid);
	
	public Delivery_challan getdeliverychallernpartyterm(String salesretunnote_id);
	
	public Delivery_challan_weight_dtlDTO getDlvChallanWeightDtls(String delivery_cid);
	
	public Delivery_challan_Trans_InfoDTO getDlvChallanTransInfo(String delivery_cid);
	
	public List<Invoice_Delivery_Trans_InfoDTO> getDlvChlnTransInfo(String delivery_cid);
	
	public List<Invoice_Delivery_Trans_InfoDTO> getMultipleDlvChlnTransInfo(String delivery_cid);
	
	public List<Delivery_challan_DocsDTO> getDlvCDoc(String delivery_cid);
	
	public StatusDTO checkcashchallan(String orderdate,double totalamount,long id,String party,String referance_id);
	
	public Delivery_challan findOne(long id);
	
	public Sales_Order retriveDeliveryChallanOrderNo(String delivery_cid);
	
	public StatusDTO checkDeliveryChallanUsage(String delivery_cid);
	
	public Delivery_challan deleteDeliveryChallan(Delivery_challan delvid,Long id);
	
	public List<Delivery_challan>getDeliverychallanlist (String salesorderid);
	
	
	public List<Delivery_challan_Item_Dtls>getdeliverchallanitemlist (String deliverycharges);
	
	public List<Map<String,Object>> getdeliverchallanjobitemlist (String deliverycharges);
	
	public List<Delivery_challan_Item_Dtls>getdeliverchallanitemlistUpdate (Long id);
	
	public List<Delivery_challan> getdiliverychallanreturnapprovepopup(Long id);
	
	public List<Delivery_challanDTO> getDeliveryChallanDataList(String currDate,String finyear);
	
	public List<Map<String,Object>> getDeliveryChallanFastList(String currDate,String finyear);
	
	public List<ChallanpertransportreportDTO> getChallanPerTransportReport(String fromdate,String todate,String transborneby);
	
	public List<Map<String, Object>> getchallanReport(String fromdate,String todate);
	
	public List<Map<String, Object>> getSalesTransportReport(String bunit,String fromdate,String todate,String inv_type,String trans_typ,String transporter_code);
	
	public List<Map<String,Object>> getDeliveryChallanReportThrouhgLA(String loadingid);
	
	public List<Map<String,Object>> getDelivery_challan_Chgs_dyn(String delivery_cid);
	
	public List<Map<String,Object>> getDelivery_challan_Chgs_dynDtls(String delivery_cid);
	
	public List<Map<String,Object>> getDelivery_challan_Chgs_dynpopup(String loadingid);
	
	public List<Map<String,Object>> deliverychallanjobworkRetriveList(String delv_id);
	
	public Map<String,Object> getSalesOrdIfMultiTransInfo(String delv_id);
	
	public Map<String,Object> getDlvChallanWeightDtlsMulti(String delv_id);
	
	public Map<String,Object> getLoadingAdviceTransDtls(String delv_id);
	
	public List<Map<String, Object>> getSalesFreightReport(String fromdate,String todate,String invoicetype);
	
	public List<Map<String, Object>> searchpendingDelvChallan(String fromdate,String todate);
	
	public Map<String,Object> getGrnDetails(String grnid);
	
	public Map<String,Object> getGrndetailsforWeighment(String grnid,String company);
	
}
