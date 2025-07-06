package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_OrderDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Item_Dtls_for_jobworkDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_broker_dtlsDTO;
//import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_doc_attchDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_driver_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_itm_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_trans_infoDTO;


public interface Wm_loading_adviceService {
	
	public SequenceIdDTO getLASequenceId(String prefix,String orderdate,String type);
	
	public SequenceIdDTO getLASequenceIdWarehouse(String prefix,String orderdate);
	
	//public Wm_loading_advice save(Wm_loading_advice wm_loading_advice);
	public Wm_loading_advice save(Wm_loading_advice wm_loading_advice,MultipartFile files[]) throws IOException;
	
	public Wm_loading_advice update(Wm_loading_advice wm_loading_advice,Long id);
	
	public Wm_loading_advice deleteLoadingAdvice(Wm_loading_advice wm_loading_advice,Long id);
	
	public List<Wm_loading_adviceDTO> findAll();
	
	public Page<Wm_loading_advice_Pagination_DTO> getLoadingAdvices_pagination(int page,int size);
	

	
	public List<Wm_loading_advice_Pagination_DTO> searchLoadingAdvice(String orderno,String fromdate, String todate,String bus_partner1,String finyear);
	
	public List<Map<String,Object>> searchLoadingAdviceFast(String orderno,String fromdate, String todate,String bus_partner1,String finyear);
	
	public Wm_loading_advice findOne(long id);
	
	public List<Wm_loading_advice_itm_dtlsDTO> loadingItemRetriveList(String code);
	
	public List<Wm_loading_advice_itm_dtlsDTO> loadingItemRetriveListprint(String code);
	
	
	public Wm_loading_advice_driver_dtlsDTO loadingDriverRetriveList(String code);
	 
	public Wm_loading_advice_trans_infoDTO loadingTransInfoRetriveList(String code,String company);
	 
	public Wm_loading_advice_bp_dtlsDTO loadingBPDtlsRetriveList(String code);
	
	public List<Wm_loading_advice_doc_attchDTO> loadingDocRetriveList(String code);
	
	public List<Wm_loading_advice_Party_DtlsDTO> getLoadingAdvPartyDtls(String advice_id);
	
	public List<Wm_loading_advice_broker_dtlsDTO> getLoadingAdvBrokerDtls(String order_id);
	
	public List<Wm_loading_adviceDTO> getLoadingAdviceList(String company);
	
	public Wm_loading_adviceDTO getLoadingDetails(String advice_id);
	
	public Wm_loading_adviceDTO getLoadingAdvThruVehicle(String vehicleid,String adv_type);
	
	public List<Wm_loading_adviceDTO> getLoadingAdviceStkTrans();
	
	public List<Wm_loading_adviceDTO> getLoadingAdvicesThruWeigh(String cdate);
	
	public List<Wm_loading_adviceDTO> getLoadingAdvicesThruWeigh(String cdate,String party,String inv_type);
	
	public List<Wm_loading_adviceDTO> getLoadingAdvPurRtn(String prdate,String supplier,String company,String finyear);
	
	public List<Wm_loading_adviceDTO> getLoadingAdviceThruBUnit(String bunit);
	
	public Wm_loading_adviceDTO loadingAdviceVehicleList(String code);
	
	public Wm_loading_advice_Shipment_DtlsDTO getLoadingAdvShipDtls(String advice_id);
	
	public Wm_loading_advice_trans_infoDTO getLoadingAdvTransinfo(String advice_id);
	
	public List<Wm_loading_advice_itm_dtlsDTO> getLoadingAdvItemDtlsThruVehicle(String vehicle_id,String adv_type);
	
	public Wm_loading_advice_driver_dtlsDTO getLoadingAdvDriverDtlsThruVehicle(String vehicle_id,String adv_type);
	
	public Wm_loading_advice_trans_infoDTO getLoadingAdvTransInfoThruVehicle(String vehicle_id,String adv_type);
	
	//public List<Wm_loading_adviceDTO> getLoadingVehiThruSales();
	
	public List<VehicleMaster> getLoadingVehiThruSales();
	
	public List<Wm_loading_adviceDTO> getLoadingVehiThruStkTransfer();
	
	public List<Wm_loading_adviceDTO> getLoadingVehiThruPR();
	
	public List<Wm_loading_adviceDTO> getLoadngAdviceThruVehicle(String vcode,String weighment);
	
	public List<Map<String, Object>> getLoadngAdviceThruVehiclefast(String vcode,String weighment);
	
	public List<Wm_loading_adviceDTO> getLoadingVehiThruBU(String dbunit,String cdate);
	
	public List<Wm_loading_adviceDTO> getLoadAdvThruWeighmentUpdate(Long id);
	
	public List<Wm_loading_advice_itm_dtls> loadingItemRetriveListUpdate(Long id);
	
	public StatusDTO checkVehicleNoWeighment(String veh_id,String action);
	
	public List<Wm_loading_adviceDTO> getLoadingAdviceDataList(String currDate,String finyear);
	
	public List<Map<String,Object>>  getLoadingAdviceDataListfast(String currDate,String finyear);
	
	public List<Map<String, Object>> getLoadingAdviceReport(String fromdate,String todate,String finyear);
	
	public List<Map<String, Object>> getLoadingAdviceReportThrouhgSO(String soid);
	
	public Map<String, Object> getLoadItemTotalWt(String loadid);
	
	public List<Wm_loading_advice_Item_Dtls_for_jobworkDTO> loadingItemjobworkRetriveList(String adviceid);
	
	public List<Map<String, Object>> loadadvicejobworkRetriveList(String adviceid);
	
	public StatusDTO checkAdviceinCash(String advicedate,String party,String ref_type,double total_amt);
	
	public StatusDTO checkAdviceinCashUpdate(String advicedate,String party,String ref_type,double total_amt,String advice_id);
	
	public StatusDTO custPayment(String advicedate,String party,String ref_type);
	
	public Map<String, Object> loadAdviceDetails(String advice);
	
	public Map<String, Object> getLoadingDtlsByWeighmentId(String wid);
	
}
