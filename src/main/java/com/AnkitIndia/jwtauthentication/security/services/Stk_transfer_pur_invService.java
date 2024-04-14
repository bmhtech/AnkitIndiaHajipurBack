package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_musterroll_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_tax_info;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_pur_invDTO;

public interface Stk_transfer_pur_invService {

	public SequenceIdDTO getSTPISequenceId(String tdate,String bunit);
	
	public Stk_transfer_pur_inv save(Stk_transfer_pur_inv sPur_inv);
	
	public List<Stk_transfer_pur_invDTO> getStkTranPurInvs(String company,String finyear);
	
	public Stk_transfer_pur_inv findOne(long id);
	
	public List<Stk_transfer_pur_inv_item_dtls> stkPurInvItemRetriveList(String stk_trans_pur_inv_id);
	
	public List<Stk_transfer_pur_inv_musterroll_dtls> stkPurInvMusterRollRetriveList(String stk_trans_pur_inv_id);
	
	public List<Stk_transfer_pur_inv_docs> stkPurInvDocsRetriveList(String stk_trans_pur_inv_id);
	
	public Stk_transfer_pur_inv_tax_info stkPurInvTaxInfoRetriveList(String stk_trans_pur_inv_id);
	
	public Stk_transfer_pur_inv_bu_dtls stkPurInvBuRetriveList(String stk_trans_pur_inv_id);
	
	public Stk_transfer_pur_inv deleteStocktransferPurInv(Stk_transfer_pur_inv grn,long id);
	
	public List<Stk_transfer_pur_inv_item_dtls> getStkTransPurInvItemDtls(String stk_trans_pur_inv_id);
	
	
}
