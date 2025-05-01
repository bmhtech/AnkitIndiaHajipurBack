package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Stock_transfer_doc;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_TransferDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_resource_costDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_terminationsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_terminations_dynDTO;

public interface Stock_Transfer_Service {
	
	public SequenceIdDTO getSTOSequenceId(String sdate,String ordpoint);
	
	public Stock_Transfer save(Stock_Transfer stock_Transfer);
	
	public Stock_Transfer update(Stock_Transfer sTransfer,Long id);
	
	public Stock_Transfer findOne(long id);
	
	public List<Stock_Transfer> findAll();
	
	public List<Stock_TransferDTO> getStkTrans();
	
	public List<Map<String, Object>> getStkTranswtoutVch();
	
	public Stock_TransferDTO getStockTransDtls(String order_id);
	
	public List<Stock_TransferDTO> getStockTransThruInter();
	
	public List<Stock_TransferDTO> getStockTransOrds(String tdate,String bunit);
	
	public Stock_Transfer_Trans_InfoDTO getStkTransTranInfo(String order_id);
	
	public Stock_Transfer_SummaryDTO getStkTransSumm(String order_id);
	
	public List<Stock_Transfer_Summary_dynDTO> getStkTraSumDyn(String order_id);
	
	public List<Stock_Transfer_Item_DtlsDTO> getStockTransItemDlts(String order_id);
	
	public List<Map<String, Object>> getStockTransItemDltsArmy(String order_id);
	
	public List<Stock_transfer_resource_costDTO> getStockTransReCost(String order_id);
	
	public Stock_transfer_terminationsDTO getStkTransTerms(String order_id);
	
	public List<Stock_transfer_terminations_dynDTO> getStockTransTermDtls(String order_id);

	public List<Stock_transfer_doc> getStockTransDoc(String order_id);
	
	public Stock_Transfer deleteStocktransferOrder(Stock_Transfer stkOrder,Long id);
	
	public Stock_Transfer getReceivingBuLoadingAdvice(String ordid);
	
	public Stock_Transfer getOrderNumberForChallan(String ref_id,String ref_type);
	
	public Stock_Transfer getStockOrdByUnloadCode(String reference_id,String reference_status);
	
	public Map<String, Object> getstockOrderdetails(String stockTransOrder_id);
	
	public Map<String, Object> getStkOrderVehicleNo(String stockTransOrder_id);
	
	public  List<Map<String, Object>> getStkTransOrderItemDlts(String stockTransOrder_id);
	
}