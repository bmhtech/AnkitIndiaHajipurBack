package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_ChallanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_bu_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_trans_dtlsDTO;

public interface Stk_transfer_sales_invService {
	
	public SequenceIdDTO getSTSISequenceId(String idate,String bunit);
	
	public Stk_transfer_sales_inv save (Stk_transfer_sales_inv sTransfer_sales_inv);
	
	public List<Stk_transfer_sales_inv> getStkTranSaleInvs(String company,String finyear);
	
	public Stk_transfer_sales_inv findOne(long id);
	
	public List<Stk_transfer_sales_inv_item_dtlsDTO> getStkTransSalesInvItemDtls(String stk_sales_inv_id);

	public List<Stk_transfer_sales_inv_trans_dtlsDTO> getStkTransSalesInvTransDtls(String stk_sales_inv_id);
	
	public Stk_transfer_sales_inv_bu_dtlsDTO getStkTransSalesInvBUDtls(String stk_sales_inv_id);
	
	public List<Stk_transfer_sales_inv_docs> getStkTransSalesInvDocs(String stk_sales_inv_id);
	
	public Stk_transfer_sales_inv_shipment_dtlsDTO getStkTransSalesInvShipDtls(String stk_sales_inv_id);
	
	public Stk_transfer_sales_inv_tax_infoDTO getStkTransSalesInvTaxInfo(String stk_sales_inv_id);
	
	public Stk_Transfer_ChallanDTO stkSalesInv(long id);
	
	public Stk_transfer_sales_inv deleteStockTransferSalesInvoice(Stk_transfer_sales_inv sInv,Long id);
	
	public List<Stk_transfer_sales_inv_item_dtlsDTO> getMultipleStkTransSalesInvItemDtlsupdate(long id);
	
	public Stk_transfer_sales_inv getStkTransSalesInvByIdprint(long id);
	
	public Stk_transfer_sales_inv getstockrecivingbuunit(long id);
	
	public  List<Stocksaleitem_groupwise_hsnsumm> getStockSaleInvHsnSum(String stk_sales_inv_id);
	
	public  List<Stocksaleitem_groupwise_taxsumm> getStockSaleInvTaxSum(String stk_sales_inv_id);
	
	
}
