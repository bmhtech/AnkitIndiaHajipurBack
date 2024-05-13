package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls;
import com.AnkitIndia.jwtauthentication.responseDTO.DailygatewheatinwardreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Status_tableDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_app_chgsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_driver_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_party_wmDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_terms_conDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_trans_infoDTO;


public interface Wm_unload_adviceService {
	
	public SequenceIdDTO getUASequenceId(String bunit,String orderdate);

	public SequenceIdDTO getUASequenceIdnew(String bunit,String orderdate);
	
	//public Wm_unload_advice save (Wm_unload_advice wmAdvice);
	public Wm_unload_advice save(Wm_unload_advice wmAdvice,MultipartFile files[]) throws IOException;
	
	public List<Wm_unload_advice> findAll();
	
	public Page<Wm_unload_advice_Pagination_DTO> getUnloadAdvicePagination(int page,int size);
	
	public List<Wm_unload_advice_Pagination_DTO> searchUnloadAdvice(String orderno,String fromdate, String todate,String bus_partner1,String finyear);
	
	public Wm_unload_advice update(Wm_unload_advice wUnload_advice,Long id);
	
	public Wm_unload_advice findOne(long id);
	
	public Wm_unload_adviceDTO getUnloadAdvice(String vcode);
	
	public Wm_unload_adviceDTO getUnloadDetails(String unadviceid);
	
	public Map<String, Object> getUnloadDetailsFastApi(String unadviceid);

	public Wm_unload_adviceDTO getUnloadDetailsNew(String unadviceid);
	
	public Wm_unload_adviceDTO getUnload_multi_popup(String unadviceid);
	
	public Wm_unload_adviceDTO getUnloadAdvThruVehi(String vehicleid);
	
	public List<Wm_unload_adviceDTO> getUnloadCodeList(String bpartner);
	
	public List<Wm_unload_adviceDTO> getUnloadCodeList();
	
	public List<Wm_unload_adviceDTO> getUnloadVehiThruPurchase();
	
	public List<Wm_unload_adviceDTO> getUnloadVehiThruSR();
	
	public List<Wm_unload_adviceDTO> getUnloadVehiThruStkTransfer();
	
	public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdv();
	
	public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdvWt2();
	
	public List<Map<String, Object>> getUnloadAdvRefOpenAdvWt2New();
	
	public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdvQc();
	
	public List<Wm_unload_adviceDTO> getUnloadAdvRefPO();
	
	public List<Wm_unload_adviceDTO> getUnloadAdvRefPOwt2(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate);
	
	public List<Map<String, Object>> getUnloadAdvRefPOwt2FastAPI(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate);
	//getunloadstore
	public List<Wm_unload_adviceDTO> getunloadstore(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate);
	
	public List<Wm_unload_adviceDTO> getUnloadAdvRefPOwt2Argnew(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate);
	
	public List<Wm_unload_adviceDTO> getUnloadAdvRefPOQc();
	
	public List<Map<String,Object>> getUnloadAdvRefPOQcNew();
	
	public List<Wm_unload_adviceDTO> getUnloadAdvVehiThruBU(String bunit,String orderdate);
	
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemList(String code);
	
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemListrevise(String unadvice_id);
	
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemDtlsThruVehi(String vehicleid);
	
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadAdvPOItemDtls(String adviceid,String porderid);
	
	public List<Wm_unload_adviceDTO> getUnloadAdviceThruVehicle(String vcode,String weighment);
	
	public List<Map<String, Object>> getUnloadAdviceThruVehiclefast(String vcode,String weighment);
	
	public List<Wm_unload_advice_item_dtlsDTO> wmUnAdviceItemRetriveList(String code);
	
	public Wm_unload_advice_party_wmDTO wmUnAdvicePartyWmRetriveList(String code);
	
	public Wm_unload_advice_party_wmDTO wmUnAdvicePartyWmRetriveListmultipopup(String code);
	
	public Wm_unload_advice_party_wmDTO getUnloadAdvPartyWmThruVehi(String vehicleid);
	
	public Wm_unload_advice_driver_dtlsDTO wmUnAdviceDriverDtlsRetriveList(String code);
	
	public List<Wm_unload_advice_broker_dtlsDTO> wmUnAdviceBrokerRetriveList(String code);
	 
	public Wm_unload_advice_terms_conDTO wmUnAdviceTransConRetriveList(String code);
	 
	public Wm_unload_advice_trans_infoDTO wmUnAdviceTransInfoRetriveList(String code);
	
	public Wm_unload_advice_trans_infoDTO getUnloadAdvTransInfoThruVehi(String vehicleid);
	
	public Wm_unload_advice_bp_dtlsDTO wmUnAdviceBpDtlsRetriveList(String code);
	
	public List<Wm_unload_advice_docDTO> wmUnAdviceDocRetriveList(String code);
	
	public List<Wm_unload_advice_app_chgsDTO> wmUnAdviceAppChgsRetriveList(String code);
	
	public List<VehicleMasterDTO> getVehiclenoall();
	
	public List<Map<String, Object>> getVehiclenoallNew();
	
	public List<Map<String, Object>> wmUnAdviceBrokerRetriveFastList(String unloadid);
	
	public Map<String, Object> wmUnAdviceBpDtlsRetriveFastList(String unloadid);
	
	public Map<String, Object> wmUnAdviceDriverDtlsRetriveFastList(String unloadid);
	
	public Map<String, Object> wmUnAdvicePartyWmRetriveFastList(String unloadid);
	
	public List<Map<String, Object>> getUnloadItemFastList(String unloadid);
	
	public Map<String, Object> wmUnAdviceTransConRetriveFastList(String unloadid);
	
	public Map<String, Object> wmUnAdviceTransInfoRetriveFastList(String unloadid);
	
	public List<Map<String, Object>> wmUnAdviceAppChgsRetriveListFast(String unloadid);
	
	public List<Map<String, Object>> wmUnAdviceDocRetriveListFast(String unloadid);
	
	 public Status_tableDTO checkweightunloadadvice(String pur_orderid,String item_code,Double mat_wt);
	/*public List<Unload_Adv_Supp_DateDTO> getUnloadAdvSuppDate(String vno);*/
	 
	// public Wm_unload_adviceDTO getUnload_advice_updation(String orderid);
	 public List<Wm_unload_adviceDTO> getUnload_advice_updation(String orderid,String subtype);
	// public List<Wm_unload_advice_docDTO> getUnload_advice_updation(String orderid);
	 
	 public Wm_unload_adviceDTO getUnloadadvanceList(String unadviceid);
	 
	 public Wm_unload_advice deleteUnloadAdvice(Wm_unload_advice unloadid,Long id);
	 
	 public Wm_unload_advice getUnloadAdviceData(String unadviceid);
	 
	 public Wm_unload_advice retrivePurchaseGRNUnloadAdvicePopup(Long Id);
	 
	 public List<Wm_unload_advice> retrivePurchaseGRNMultipleUnloadAdvicePopup(Long Id);
	 
	 public List<Wm_unload_advice_item_dtls> retriveGRNItemFormultiple(Long Id);
	 
	 public List<Wm_unload_adviceDTO> getUnloaDataList(String currDate,String finyear);
	 
	 
	 public List<Map<String,Object>> getUnloaDataListfastapi(String currDate,String finyear);
	 
	 public List<Map<String,Object>> getSearchUnloadAdvice(String orderno,String fromdate,String todate,String bus_partner1,String finyear);
	
	 // public List<Map<String,Object>> getUnloaDataListNew(String currDate,String finyear);
	 
	 public List<DailygatewheatinwardreportDTO> getdailygatewheatinwardreportold2(String date,String todate);
	 
	 public List<Map<String, Object>> getdailygatewheatinwardreportnew(String date,String todate);
	 
	 public List<Map<String, Object>> getdailygatewheatinwardreportnew2(String date,String todate,String order);
	 
	 public List<Map<String, Object>> getdailygatewheatinwardreportnew2WithParty(String date,String todate,String order,String party);
	 
	public Wm_unload_advice getunloadfromreturnid(String salereturnid);
	
	public List<Map<String, Object>> getUnloadAdviceReport(String fromdate,String todate,String suppliername,String finyear);
	
	public List<Map<String, Object>> getUnloadListThroughPurOrderId(String purorder);

	public List<Map<String, Object>> getUnloadDetailsThroughUnloadId(String unloadid);
	
	public List<Map<String, Object>> retriveUnloadAdviceJobwork(String unloadid);
	
	public List<Map<String, Object>> getSalesReturnNoteJobwork(String advdate,String bunit,String party);
	
	public List<Map<String, Object>> unloadadvicejobworkRetriveList(String unadviceid);
	
	public List<Map<String,Object>> getUnloadAdvRefPOwt2ArgnewMultiItemGRN(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate);
	
}
