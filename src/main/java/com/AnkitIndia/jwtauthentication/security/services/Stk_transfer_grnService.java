package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grnDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_bu_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_docsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_other_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_resource_costDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_trans_infoDTO;

public interface Stk_transfer_grnService {

	public SequenceIdDTO getSTGRNSequenceId(String tdate,String bunit);
	
	public Stk_transfer_grn save(Stk_transfer_grn sGrn);
	
	public List<Stk_transfer_grn> getStkTranGRN(String company,String finyear);
	
	public List<Stk_transfer_grnDTO> getStkTranGrns(String bunit,String tdate,String company,String finyear);
	
	public Stk_transfer_grnDTO getStkTranGrnDtls(String stkgrnid);
	
	public Stk_transfer_grn findOne(long id);
	
	public List<Stk_transfer_grn_item_detailsDTO> getStkTranGrnItemDlts(String stk_grn_id);
	
	public Stk_transfer_grn_trans_infoDTO getStkTranGrnTranInfo(String stk_grn_id);
	
	public Stk_transfer_grn_bu_dtlsDTO getStkTranGrnBUDtls(String stk_grn_id);
	
	public Stk_transfer_grn_other_infoDTO getStkTranGrnOthDtls(String stk_grn_id);
	
	public List<Stk_transfer_grn_resource_costDTO> getStkTranGrnReCostDlts(String stk_grn_id);
	
	public List<Stk_transfer_grn_docsDTO> getStkTranGrnDocs(String stk_grn_id);
	
	public List<Vehicle_weighment_load_unload> getVehiclesFromVehicleLoadUnload();
	
	public Vehicle_weighment_load_unload getVehicleRefName(String vehicleid);
	
	public Stk_transfer_grn deleteStocktransferGRN(Stk_transfer_grn grn,long id);
	
	public StatusDTO checkStockGRNUsage(long id);
	
	public Stk_transfer_grnDTO stkPurInv(long id);
	
	public Map<String,Object> getStkTransferGrnRestQty(String orderid,String item,String packing);
	
	public List<Map<String,Object>> getStkTranGrnsFast(String comp,String fyear);
	
	public Map<String,Object> getSalesInvFromStkTransGrn(String stk_grn_id);
	
}
