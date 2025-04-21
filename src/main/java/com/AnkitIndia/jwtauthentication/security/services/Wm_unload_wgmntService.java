package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Weighment_doc;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmntDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmnt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmnt_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Terminatekata;

public interface Wm_unload_wgmntService {
	
	public SequenceIdDTO getWeighmentSequenceId(String prefix,String orderdate,String weight);
	
	public SequenceIdDTO getWeighmentNumber(String vehicle,String orderdate);
	
	public SequenceIdDTO getWeighmentSequenceIdnew(String prefix,String orderdate,String weight,String unadviceid);
	
	public SequenceIdDTO getWeighmentSequenceIdnewloading(String prefix,String orderdate,String weight,String adviceid);
	
	//public Wm_unload_wgmnt save (Wm_unload_wgmnt wgmnt);
	public Wm_unload_wgmnt save(Wm_unload_wgmnt wgmnt,MultipartFile files[]) throws IOException;
	
	public Wm_unload_wgmnt otherSave(Wm_unload_wgmnt wgmnt,MultipartFile files[]) throws IOException;
	
	public List<Wm_unload_wgmnt> findAll();
	
	public Page<Wm_unload_wgmnt_Pagination_DTO> getUnloadWeightments_pagination(int page,int size);
	
	public List<Wm_unload_wgmnt_Pagination_DTO> searchWeighment(String orderno,String fromdate, String todate,String customer_name1,String supplier_name1,String finyear);
	
	public List<Map<String,Object>> searchWeighmentFast(String orderno,String fromdate, String todate,String customer_name1,String supplier_name1,String finyear);
	
	public List<Map<String,Object>> searchOtherWeighmentFast(String orderno,String fromdate, String todate,String noparty,String finyear);
	
	public Wm_unload_wgmnt findOne(long id);
	
	public Wm_unload_wgmntDTO getUnloadWeightment(String vcode);
	
	public Wm_unload_wgmntDTO getUnloadWeightmentWt(String wgment_id);
	
	public Wm_unload_wgmntDTO getUnloadWeightmentWtmultipopup(String unload_id);
	
	public List<Wm_unload_wgmnt_dtlsDTO> unloadWMDtlsRetriveList(String code);
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighment();
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighmentnew();
	
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighmenOutAuth();
	
	public Wm_unload_wgmnt getWeighmentId(String wgment_id);
	
	public List<Weighment_doc> getGetDocuments(String unadv_id);
	
	public List<Wm_unload_wgmntDTO> getWeighmentDataList(String currDate,String finyear);
	
	public List<Map<String,Object>> getWeighmentDataFastList(String currDate);
	
	public List<Map<String,Object>> getOtherWeighmentDataList(String currDate,String finyear);
	
	public List<Map<String,Object>> getNopartyList();
	
	public Map<String,Object> getFirstData();
	
	public Map<String,Object> getOtherWgFirstData(String vehicleid);
	
	public Map<String,Object> getOtherWgFirstDataWtWgtFor(String vehicleid);
	
	public List<Wm_unload_wgmnt> getdailygatewheatOUTwardreport(String fromdate,String todate);
	
	public List<Map<String, Object>> getOtherKataReport(String fromdate,String todate);
	
	public List<Map<String, Object>> getOtherKataWithPartyReport(String fromdate,String todate,String party);
	
	public List<Map<String, Object>> getdailyjobworkwgtreport(String loadfromdate,String load2date,String party);
	
	public StatusDTO checkLooseItem(String adviceid);
	
	public Terminatekata terminatekata(Terminatekata terminatekata);
	
	public List<Map<String,Object>> getOtherWgnmtList();

	public List<Map<String,Object>> getUnloadWeightmentWtmultipopupmultipleItem(String wgment_id);
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighmentLocation(String location);
	
	public List<Vehicle_weighment_load_unload> getVehicleLocationwiseWeighmentList(String location);
	
	public SequenceIdDTO getSecondkataSrlnoCamera(String bridge_location) throws IOException;
}
