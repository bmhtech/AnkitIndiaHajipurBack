package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_BusinessUnit_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Weight_Dtl;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_driver_dtls;
import com.AnkitIndia.jwtauthentication.repository.Stock_Indent_TerminationsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Stk_Transfer_Challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_ChallanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Weight_DtlDTO;

public interface Stk_Transfer_ChallanService {
	
	public SequenceIdDTO getSTCSequenceId(String cdate,String bunit);
	
	public Stk_Transfer_Challan save(Stk_Transfer_Challan stockTransferChallan);
	
	public List<Stk_Transfer_Challan> findAll();
	
	public Stk_Transfer_Challan findOne(long id);
	
	public Wm_loading_adviceDTO getStockTransLoadingWeighmentId(long id);
	
	public Stk_Transfer_Challan update(Stk_Transfer_Challan stk_Transfer_Challan,Long id);
	
	public List<Stk_Transfer_ChallanDTO> getStkTransChallanThruBUnit(String bunit);

	public List<Stk_Transfer_ChallanDTO> getStkTransChallans(String bunit,String invdate,String comp,String finyear);
	
	public List<Stk_Transfer_ChallanDTO> getstocktransferchallaninunloading(String bunit,String invdate,String comp,String finyear);
	
	
	public Stk_Transfer_ChallanDTO getStkTransChallanDtls(String stk_challan_id);
	
	public List<Stk_Transfer_Challan_Item_DtlsDTO> getStkTransChallanItemDlts(String stk_challan_id);
	
	public Stk_Transfer_Challan_Shipment_DtlsDTO getStkTransChallanShipDtls(String stk_challan_id);

	public Stk_Transfer_Challan_Trans_InfoDTO getStkTransChallanTranInfo(String stk_challan_id);
	
	public List<Invoice_Stk_Transfer_Challan_Trans_InfoDTO> getStkTransChallanTranInfos(String stk_challan_id);
	
	public List<Stk_Transfer_Challan_Docs> getStkTransChallanDocs(String stk_challan_id);
	
	public List<Stk_Transfer_Challan_BusinessUnit_Dtls> getStkTransBUDtls(String stk_challan_id);
	
	public Stk_Transfer_Challan_Weight_DtlDTO getStkTransChallanWtDtls(String stk_challan_id);
	
	public Stk_Transfer_Challan deleteStocktransferChallan(Stk_Transfer_Challan challan,Long id);
	
	public Stk_Transfer_Challan getStkOrderDetails(String stk_challan_id);
	
	public List<Stk_Transfer_Challan_Item_Dtls> getMultipleStkTransChallanDtls(String stk_challan_id);
	
	public List<Stk_Transfer_Challan_Docs> getMultipleStkTransChallanDocs(String stk_challan_id);
	
	public List<Stk_Transfer_ChallanDTO> getmutiplegetStkTransChallans(long id);
	
	public Stk_Transfer_Challan getMultipleStkOrderDetails(String stk_challan_id);
	
	public Stk_Transfer_Challan_Trans_Info getStkChallanVehicleNo(String stk_challan_id);
	
	public Stk_Transfer_Challan getReceipt_criteria(String stk_challan_id,String unload);
	
	public Stk_Transfer_Challan_Item_Dtls getChallanItemDlts(String stk_challan_id,String itemid);
	
	public Wm_loading_advice_driver_dtls getStockVehicleAndDriver(String challan_id);
	
	public Stock_Transfer findOneChallan(long id);
	
	public Stk_Transfer_Challan getstockchallandetails(String stk_challan_id);
	
}
