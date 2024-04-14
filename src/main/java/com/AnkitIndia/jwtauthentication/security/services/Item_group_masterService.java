package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.AnkitIndia.jwtauthentication.model.Item_group_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_jobwork_sales_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_jobwork_sales_return_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_pur_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_pur_retaccDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_sales_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_sales_retaccDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_stk_trans_purDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_stk_trans_saleDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Item_group_masterService {
	
	public SequenceIdDTO getIgrpSequenceId(String prefix,String company);

	public Item_group_master saveItemGroup(Item_group_master iGroup_master);
	
	public List<Item_group_master> findAll(String company);
	
	public List<Item_group_masterDTO> getItemGroup(String company);
	
	public List<Map<String,Object>> itemGroupFastList(String company);
	
	public Item_group_masterDTO getItemGroupUom(String code);
	
	public String  chkItemGroupName(String code);
	
	public MessageStatusDTO  chkItemGroupStatus(String code);
	
	public Item_group_master findOne(Long id);
	
	public Item_group_master_sales_accDTO getItemGroupSalesAcc(String igroupid);
	
	public Item_group_master_pur_accDTO getItemGroupPurAcc(String igroupid);
	
	public Item_group_master_sales_retaccDTO getItemGroupSalesRtnAcc(String igroupid);
	
	public Item_group_master_pur_retaccDTO getItemGroupPurRtnAcc(String igroupid);
	
	public Item_group_master_stk_trans_purDTO getItemGroupStkTrnsPur(String igroupid);
	
	public Item_group_master_stk_trans_saleDTO getItemGroupStkTrnsSale(String igroupid);
	
	public Item_group_jobwork_sales_accDTO getItemGroupJobworkSales(String igroupid);
	
	public Item_group_jobwork_sales_return_accDTO getItemGroupJobworkSalesReturn(String igroupid);
	
	public Item_group_master update(Item_group_master iGroup_master,long id);
	
	public Item_group_master deleteItemGroup(Item_group_master iGroup_master,long id);
	
	public Item_group_master_sales_accDTO getGroupSalesAccThruItems(String items);
	
	public List<Item_group_master> findItemGroups(String searchtext,String company);
	
	public StatusDTO chkItemGroupCodeStatus(String code);
	
	//public List<Map<String,Object>> getGroupItemLedgerForJob(String group);
	
}
