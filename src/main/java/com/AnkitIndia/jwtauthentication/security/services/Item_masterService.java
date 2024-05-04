package com.AnkitIndia.jwtauthentication.security.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.AnkitIndia.jwtauthentication.model.Item_catagory_master;
import com.AnkitIndia.jwtauthentication.model.Item_maintain;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_master_stock_details;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_alternative_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_inv_data1DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_inv_data2DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_other_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_stat_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Paging_SortingDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_master_pack_mat_tagDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_master_qc_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_masterstatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;

public interface Item_masterService {
	
	public SequenceIdDTO getItemSequenceId(String prefix,String company);
	
	List<Item_maintain> getItemOpeningStock(String bunit,String itype,String tdate);
	
	List<Item_masterDTO> getItemOpening(String bunit,String itype,String tdate);
	
	public Item_master saveItem (Item_master item_master);
	
	public List<Item_master> getItems(String company);
	
	public List<Item_master> findAllItems();
	
	public List<Item_master> findItems(String company,String searchtext);
	
	public Item_master update(Item_master iMaster,long id);
	
	public MessageStatusDTO chkItemNameStatus(String iname);
	
	public StatusDTO chkItemCodeStatus(String icode);
	
	public List<Item_masterDTO> getAllItems();
	
	public List<Item_masterDTO> getItemNameList();
	
	public List<Map<String,Object>> getItemNameNewList();
	
	public List<Item_masterDTO> getItemCodeNamecom(String company);
	
	public List<Item_masterDTO> getItemRawMaterials();
	
	public List<Item_masterDTO> getItemThruType(String itype);
	
	public List<Item_masterDTO> getItemThruType3(String itype);
	
	
	public List<Item_masterDTO> getweatreceivingitemlist(String businessunit_id);
	
	public List<Item_masterDTO> getLabItemlist(String businessunit_id);
	
	public List<Item_masterDTO> getItemThruSales();
	
	public List<Map<String, Object>> getItemThruSalesNew();
	
	public List<Item_masterDTO> getItemThruSalesThruBU(String bunit,String company);
	
	public List<Map<String,Object>> getItemThruSalesThruBUFastApi(String bunit,String company);
	
	public List<Item_masterDTO> getItemThruSalesThruBU_inv_type(String bunit,String company,String inv_type);
	
	public List<Map<String,Object>> getItemThruSalesThruBU_inv_typeGST(String bunit,String company);
	
	public List<Map<String,Object>> getItemThruSalesThruBU_inv_typeReg(String bunit,String company);
	
	public List<Item_masterDTO> getItemThruProcessed();
	
	public List<Item_masterDTO> getItemThruProcessed(String bunit);
	
	public List<Item_masterDTO> getItemThruPurchase();
	
	public List<Map<String, Object>> getItemThruPurchasenew();
	
	public List<Map<String, Object>> getFinishedItemlist(String businessunit_id);
	
	public List<Item_masterDTO> getItemCodeByPacking(String company);
	
	public List<Map<String, Object>> getPackingUom(String item_id);
	
	//Delete
	public List<Item_masterDTO> getItemCodeByPacking();
	
	public List<Item_masterDTO> getItemCodeWithoutPacking();
	
	public List<Item_masterDTO> getItemListByGroup(String group,String item_id);
	
	public List<Item_masterDTO> getItemByItemGroup(String itemid);
	
	public Item_masterDTO getItemNameByCode(String code);
	
	public Item_masterDTO getItemNameById(String code,String company);
	
	public Item_master_pack_mat_tagDTO itempackUom(String code,String code1,String company);
	
	public Item_master findOne(Long id);
	
	public List<Item_master_qc_detailsDTO> getItemQCDetails(String code,String company);
	
	public Item_masterDTO getItemNameCodeStock(String itemID);
	
	public List<Item_masterDTO> getItemCodeByType(String type);
	
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMat(String code,String company);
	
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMat(String code);
	
	public Item_master_inv_data1DTO retriveItemMasterInvData1(String item_id,String company);
	
	public List<Item_master_alternative_dtlsDTO> retriveItemMasterAltDtls(String item_id,String company);
	
	public Item_master_inv_data2DTO retriveItemMasterInvData2(String item_id,String company);
	
	
	public Item_master_other_infoDTO retriveItemMasterOtherInfo(String item_id,String company);
	
	public List<Item_master_stat_infoDTO> retriveItemMasterStatInfo(String item_id,String company);
	
	public Item_master_pack_mat_tagDTO getItemCapacity(String code);
	
	public Page<Item_master> getItemPaging(int pageno,int pagesize,String sortBy,String comp);
	
	public List<Item_master> getItemPagingSorting(Integer pageno,Integer pagesize,String sortBy,String comp);
	
	public Paging_SortingDTO getItemPagingTotal(Integer pageno,Integer pagesize,String sortBy,String comp);
	
	public StatusDTO checkItemMasterUsage(String code);
	
	public Item_masterDTO deleteItemMaster(long id,Item_master iMaster);
	
	public Item_master accountitemposting(long id);
	
	public Item_master accountpostingUndoItemMaster(long id);
	
	public List<Item_master_stock_details> getItemstockDetails(String code,String company);
	
	public List<Map<String,Object>> searchItemData(String itemtype1,String company_name);
	
	public List<Item_masterDTO> itemnameproduction(String entrymode,String businessunit,String shopfloor);
	
	public List<Item_masterDTO> itemNameByGroupProduction(String businessunit,String shop_floor,String process);
	
	public List<Item_masterDTO> getItemThruTypeForUsedItem(String bunit,String itype);
	
	public List<Map<String,Object>> getAlternativeItemList(String itemcode);
	
	public Map<String, Object> getItemAlternateprice(String itemid,String alternateitemid);
	
	public List<Map<String,Object>> getJobworklist(String item_id);
	
	public List<Map<String,Object>> retriveClassifiedItem(String item_id,String company);
	
	public List<Map<String,Object>> retriveItemSizeAndWeight(String item_id,String company);
	
	public List<Map<String,Object>> getPackingMasterCode(String itemid,String packingid);
	
	public Map<String, Object> retrivePackingDtls(String packingMasterCode, String packingid);
	
	public List<Map<String,Object>> getItemMasterPackMatNew(String code);
	
	public Map<String,Object> getItemPackUomNew(String code,String code1,String company);
}
