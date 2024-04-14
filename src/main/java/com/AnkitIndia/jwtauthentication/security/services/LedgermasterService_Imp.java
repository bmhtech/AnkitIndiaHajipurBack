package com.AnkitIndia.jwtauthentication.security.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.repository.GroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.LedgermasterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;


@Service
public class LedgermasterService_Imp implements LedgermasterService{
	
	@Autowired
	LedgermasterRepository 	ledgermasterRepository;
	
	@Autowired
	SubgroupmasterRepository subgroupmasterRepository;
	
	@Autowired
	GroupmasterRepository groupmasterRepository;
	
	public List<LedgermasterDTO> getLedgerBySGrp(String sgrpCode)
	{
		List<Ledgermaster> modelList=ledgermasterRepository.getLedgerList(sgrpCode);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<LedgermasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<LedgermasterDTO> ledgerNameList = new ModelMapper().map(modelList,listType);
		
		return ledgerNameList;
	}
	
	public List<LedgermasterDTO> getLedger()
	{
		List<Ledgermaster> modelList=ledgermasterRepository.getLedger();
		modelList.forEach((e->{
			e.setLedgername(e.getLedgername().toUpperCase());
		}));
		
		List<Ledgermaster> cPartners = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Ledgermaster::getLedgername))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<LedgermasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<LedgermasterDTO> ledgerList = new ModelMapper().map(cPartners,listType);
		
		return ledgerList;
	}
	
	public List<Map<String,Object>> getLedgerNew()
	{
		List<Map<String, Object>> ledgerList = new ArrayList<Map<String, Object>>();
		
		ledgerList.addAll(ledgermasterRepository.getLedgerNew());
		
		return ledgerList;
	}
	
	public List<LedgermasterDTO> getLedgerWithBACH() {
		
		List<Ledgermaster> modelList=ledgermasterRepository.getLedgerWithBACH();
		// Create Conversion Type
		Type listType = new TypeToken<List<LedgermasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<LedgermasterDTO> ledgerList = new ModelMapper().map(modelList,listType);
		
		return ledgerList;
	}
	
	public List<LedgermasterDTO> getDutiesTaxesLedger()
	{
		List<Ledgermaster> modelList=ledgermasterRepository.getDutiesTaxesLedger();
		
		Type listType = new TypeToken<List<LedgermasterDTO>>() {}.getType();
		List<LedgermasterDTO> ledgerList = new ModelMapper().map(modelList,listType);
		
		return ledgerList;
	}
	
	public List<LedgermasterDTO> getControlLedgers()
	{
		List<Ledgermaster> modelList=ledgermasterRepository.getControlLedgers();
		modelList.forEach((e->{
			e.setLedgername(e.getLedgername().toUpperCase());
		}));
		List<Ledgermaster> allData = modelList
				  .stream()
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Ledgermaster::getLedgername))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<LedgermasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<LedgermasterDTO> ledgerList = new ModelMapper().map(allData,listType);
		return ledgerList;
	}
	
	public List<LedgermasterDTO> getBankLedger()
	{
		List<Ledgermaster> modelList=ledgermasterRepository.getBankLedger();
		
		// Create Conversion Type
		Type listType = new TypeToken<List<LedgermasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<LedgermasterDTO> ledgerList = new ModelMapper().map(modelList,listType);
		
		return ledgerList;
	}
	
	public LedgermasterDTO getLedgerName(String ledgerid) {
		Ledgermaster ledgermaster=ledgermasterRepository.getLedgerDetails(ledgerid);
		
		Type listType = new TypeToken<LedgermasterDTO>() {}.getType();
		LedgermasterDTO LedgerDto = new ModelMapper().map(ledgermaster,listType);
		
		return LedgerDto;
	}
	
	public List<Ledgermaster> getAccountPostingFor()
	{
		List<Ledgermaster> ledgerlist=ledgermasterRepository.getLedgerList();
		ledgerlist.forEach(element->{
			String subgrpName=subgroupmasterRepository.getSubGrpMasterNByC(element.getSubgroupcode());
			element.setSub_group_name(subgrpName);
			element.setGroup_name(groupmasterRepository.getGrpMasterNByCList(element.getMainsubgroupcode()));
		});
		return ledgerlist;
	}
}
