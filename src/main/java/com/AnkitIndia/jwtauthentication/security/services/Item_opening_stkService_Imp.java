package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_opening_stk;
import com.AnkitIndia.jwtauthentication.model.Item_opening_stk_dtls;
import com.AnkitIndia.jwtauthentication.model.Item_opening_stk_pack_dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_opening_stkRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_opening_stk_dtlsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_opening_stk_dtlsDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Item_opening_stkService_Imp implements Item_opening_stkService {

	@Autowired
	Item_opening_stkRepository item_opening_stkRepository;
	
	@Autowired
	Item_opening_stk_dtlsRepository item_opening_stk_dtlsRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Transactional
	public Item_opening_stk save(Item_opening_stk iOpening_stk) throws JsonParseException, JsonMappingException, IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="IOS";
		if(item_opening_stkRepository.getItemOpenStkSeqId(prefix).isPresent()) {
			slno=Long.parseLong(item_opening_stkRepository.getItemOpenStkSeqId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		iOpening_stk.setTranse_id(gen_sno);
		
		Set<Item_opening_stk_dtls> iStk_dtls=new HashSet<Item_opening_stk_dtls>();
		iStk_dtls.addAll(iOpening_stk.getItem_opening_stk_dtls());
		
		List<Item_opening_stk_dtls> allItem = iStk_dtls
				  .stream()
				  .filter(c -> c.getOpen_item_gr_qty() > 0)
				  .collect(Collectors.toList());
				
		Set<Item_opening_stk_dtls> iStk_dtlsFinal=new HashSet<Item_opening_stk_dtls>();
		iStk_dtlsFinal.addAll(allItem);
		
			for(Item_opening_stk_dtls iOpenStkDtls:iStk_dtlsFinal) 
			{
				iOpenStkDtls.setTranse_id(gen_sno);
				iOpenStkDtls.setBusiness_unit(iOpening_stk.getBusiness_unit());
				iOpenStkDtls.setItem_type(iOpening_stk.getItem_type());
				iOpenStkDtls.setTdate(iOpening_stk.getTdate());
				iOpenStkDtls.setCurr_item_gr_qty(iOpenStkDtls.getOpen_item_gr_qty());
				iOpenStkDtls.setFin_year(iOpening_stk.getFin_year());
				
				System.err.println("Packing: >> "+iOpenStkDtls.getPack_dtls());
				
				if(iOpening_stk.getItem_type().trim().compareTo("Packing Items")!=0) {
					/********************************************* Packing Details*************************************************/
					Set<Item_opening_stk_pack_dtls> iPack_dtls=new HashSet<Item_opening_stk_pack_dtls>();
					
					/*if(iOpenStkDtls.getPack_dtls() == null || iOpenStkDtls.getPack_dtls().trim().isEmpty() || iOpenStkDtls.getPack_dtls().trim().compareTo("0") ==0) {
						iOpenStkDtls.setPack_dtls("{\"item_opening_stk_pack_dtls\":[{\"packing_id\":\"0\",\"item_id\":\"0\",\"open_packing_qty\":0,\"packing_uom\":\"0\",\"open_item_qty\":0,\"item_uom\":\"0\",\"tolerance\":0,\"capacity\":\"0\"}]}");
					}*/
					System.err.println("Pack 1: "+iOpenStkDtls.getPack_dtls());
					String inptData=iOpenStkDtls.getPack_dtls().replace("{\"item_opening_stk_pack_dtls\":", "");
					System.err.println("Pack 2: "+inptData);
					inptData=inptData.substring(0, inptData.length()-1);
					
					//Item_opening_stk_pack_dtls[] iStk_pack_dtls= new ObjectMapper().readValue(inptData, Item_opening_stk_pack_dtls[].class);
					
					//inptData must be mapped with the class's entity
					@SuppressWarnings("unchecked")
					Item_opening_stk_pack_dtls[] iStk_pack_dtls;
					try {
						iStk_pack_dtls = new ObjectMapper().readValue(inptData, Item_opening_stk_pack_dtls[].class);
						
						Set<Item_opening_stk_pack_dtls> iStkPackDtlsSet = new HashSet<>(Arrays.asList(iStk_pack_dtls));
						iPack_dtls.addAll(iStkPackDtlsSet);
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					for(Item_opening_stk_pack_dtls iPackDtls:iPack_dtls) 
					{
						iPackDtls.setTranse_id(gen_sno);
						iPackDtls.setBusiness_unit(iOpening_stk.getBusiness_unit());
						iPackDtls.setItem_type(iOpening_stk.getItem_type());
						iPackDtls.setTdate(iOpening_stk.getTdate());
						iPackDtls.setCurr_packing_qty(iPackDtls.getOpen_packing_qty());
						iPackDtls.setCurr_item_qty(iPackDtls.getOpen_item_qty());
						iPackDtls.setFin_year(iOpening_stk.getFin_year());
					}
					iOpenStkDtls.setItem_opening_stk_pack_dtls(iPack_dtls);
					/********************************************* Packing Details*************************************************/
				}
				//Item Stack Status Update
				int s=item_masterRepository.updateItemStockStatus(iOpenStkDtls.getItem_id(),true);
			}
			iOpening_stk.setItem_opening_stk_dtls(iStk_dtlsFinal);
				
		return item_opening_stkRepository.save(iOpening_stk);
	}
	
	public List<Item_opening_stk_dtlsDTO> getItemOpeningStk(){
		List<Item_opening_stk_dtls> itemList=new ArrayList<Item_opening_stk_dtls>();
		itemList.addAll(item_opening_stk_dtlsRepository.findAll());

		Type listType = new TypeToken<List<Item_opening_stk_dtlsDTO>>() {}.getType();
		List<Item_opening_stk_dtlsDTO> items = new ModelMapper().map(itemList,listType);
		
		items.forEach((stk) -> {
			if(Utility.isNullOrEmpty(stk.getBusiness_unit())) {
				stk.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(stk.getBusiness_unit()).getBusinessunit_name());
			}else {stk.setBusiness_unitname("None");}
			
			if(Utility.isNullOrEmpty(stk.getItem_id())) {
				stk.setItem_name(item_masterRepository.getItemDetailsThruItemId(stk.getItem_id()).getItem_name());
			}else {stk.setItem_name("None");}
			
			if(Utility.isNullOrEmpty(stk.getItem_uom())) {
				stk.setItem_uomname(customUomMasterRepository.getCustomUomById(stk.getItem_uom()).getDescription());
			}else {stk.setItem_uomname("None");}
		});
		
		return items;
	}
	
}
