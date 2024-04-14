package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Warehouse_master;
import com.AnkitIndia.jwtauthentication.model.Warehouse_stack_dtls;
import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Warehouse_stack_dtlsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.WarehouseMasterDTO;

@Service
public class WarehouseMasterService_Imp implements WarehouseMasterService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository  companyMasterRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	City_masterRepository city_masterRepository;
	
	@Autowired
	BinMasterRepository binMasterRepository;
	
	@Autowired
	Warehouse_stack_dtlsRepository warehouse_stack_dtlsRepository;
	
	@Transactional
	public Warehouse_master save(Warehouse_master warehouse) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="WHM";
		if(warehouseMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(warehouseMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);

		if(Utility.isNullOrEmpty(warehouse.getBusinessunit_code())) {
			warehouse.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(warehouse.getBusinessunit_code()).getBusinessunit_name());
		}else {warehouse.setBusinessunit_name("None");}
		
		if(Utility.isNullOrEmpty(warehouse.getState_code())) {
			warehouse.setState_name(state_masterRepository.getState(warehouse.getState_code()).getState_name());
		}else {warehouse.setState_name("None");}
		
		if(Utility.isNullOrEmpty(warehouse.getDist_code())) {
			warehouse.setDist_name(district_masterRepository.getDistrictDtls(warehouse.getDist_code()).getDist_name());
		}else {warehouse.setDist_name("None");}
		
		/*if(Utility.isNullOrEmpty(warehouse.getCity_code())) {
			warehouse.setCity_name(city_masterRepository.getCityDtls(warehouse.getCity_code()).getCity_name());
		}else {warehouse.setCity_name("None");}*/
		
		warehouse.setWarehouse_id(gen_sno);
		warehouse.setModified_type("INSERTED");
		warehouse.setInserted_by(userRepository.getUserDetails(warehouse.getUsername()).getName());
		warehouse.setInserted_on(ldt);
		warehouse.setUpdated_by("NA");
		warehouse.setUpdated_on(ldt);
		warehouse.setDeleted_by("NA");
		warehouse.setDeleted_on(ldt);
		
		//Dynamic
		Set<Warehouse_stack_dtls> warehouse_stack_dtls=new HashSet<Warehouse_stack_dtls>();
		warehouse_stack_dtls.addAll(warehouse.getWarehouse_stack_dtls());
		for(Warehouse_stack_dtls wDetails:warehouse_stack_dtls) 
		{
			wDetails.setWarehouse_id(gen_sno);
			wDetails.setWarehouse_code(warehouse.getWarehouse_code());		
			wDetails.setCompany_id(warehouse.getCompany_id());
			wDetails.setFin_year(warehouse.getFin_year());
			wDetails.setModified_type("INSERTED");
			wDetails.setInserted_by(warehouse.getInserted_by());
			wDetails.setInserted_on(ldt);
			wDetails.setUpdated_by("NA");
			wDetails.setUpdated_on(ldt);
			wDetails.setDeleted_by("NA");
			wDetails.setDeleted_on(ldt);
			wDetails.setUsername(warehouse.getUsername());
		}
		warehouse.setWarehouse_stack_dtls(warehouse_stack_dtls);
		
		return warehouseMasterRepository.save(warehouse);
	}
	
	public List<Warehouse_master> findAll()
	{
		
		List<Warehouse_master> warehouseList=new ArrayList<Warehouse_master>();
		warehouseList.addAll(warehouseMasterRepository.findAll());
				
		List<Warehouse_master> allData = warehouseList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Warehouse_master::getWarehouse_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Warehouse_master> getWareHouseNCList()
	{
		List<Warehouse_master> warehouseList=new ArrayList<Warehouse_master>();
		warehouseList.addAll(warehouseMasterRepository.findAll());
				
		List<Warehouse_master> allData = warehouseList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Warehouse_master::getWarehouse_name))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Warehouse_master findOne(long id)
	{
		Optional<Warehouse_master> op=this.warehouseMasterRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String, Object>> warehouseStackRetriveList(String warehouse_id)
	{
		List<Map<String, Object>> warehouseList=new ArrayList<Map<String, Object>>();
		
		warehouseList.addAll(warehouse_stack_dtlsRepository.getStackDtlsList(warehouse_id));
		
		return warehouseList;
	}
	
	public List<WarehouseMasterDTO> getwarehouse_code() {
		List<Warehouse_master> wareList=new ArrayList<Warehouse_master>();
		wareList.addAll(warehouseMasterRepository.findAll());
				
		List<Warehouse_master> allData = wareList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Warehouse_master::getWarehouse_name))
			  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<WarehouseMasterDTO>>() {}.getType();
		List<WarehouseMasterDTO> warehouseList = new ModelMapper().map(allData,listType);
		
		return warehouseList;
	}
	
	@Transactional
	public Warehouse_master update(Warehouse_master warehouse,long id)
	{
		Optional<Warehouse_master> op =warehouseMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();

		if(Utility.isNullOrEmpty(warehouse.getBusinessunit_code())) {
			warehouse.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(warehouse.getBusinessunit_code()).getBusinessunit_name());
		}else {warehouse.setBusinessunit_name("None");}
		
		if(Utility.isNullOrEmpty(warehouse.getState_code())) {
			warehouse.setState_name(state_masterRepository.getState(warehouse.getState_code()).getState_name());
		}else {warehouse.setState_name("None");}
		
		if(Utility.isNullOrEmpty(warehouse.getDist_code())) {
			warehouse.setDist_name(district_masterRepository.getDistrictDtls(warehouse.getDist_code()).getDist_name());
		}else {warehouse.setDist_name("None");}
		
		/*if(Utility.isNullOrEmpty(warehouse.getCity_code())) {
			warehouse.setCity_name(city_masterRepository.getCityDtls(warehouse.getCity_code()).getCity_name());
		}else {warehouse.setCity_name("None");}*/
		
		warehouse.setWarehouse_id(op.get().getWarehouse_id());
	
		warehouse.setModified_type("INSERTED");
		warehouse.setInserted_by(op.get().getInserted_by());
		warehouse.setInserted_on(op.get().getInserted_on());
		warehouse.setUpdated_by(userRepository.getUserDetails(warehouse.getUsername()).getName());
		warehouse.setUpdated_on(ldt);
		warehouse.setDeleted_by(op.get().getDeleted_by());
		warehouse.setDeleted_on(op.get().getDeleted_on());
		
		
		//Dynamic
		warehouse_stack_dtlsRepository.updateWarehouse_stack_dtls(warehouse.getWarehouse_id(),"UPDATED");
		Set<Warehouse_stack_dtls> stackset = new HashSet<Warehouse_stack_dtls>();
		stackset.addAll(warehouse.getWarehouse_stack_dtls());
		for(Warehouse_stack_dtls stackdtls : stackset)
		{
			stackdtls.setWarehouse_id(warehouse.getWarehouse_id());
			stackdtls.setWarehouse_code(warehouse.getWarehouse_code());
			
			stackdtls.setCompany_id(warehouse.getCompany_id());
			stackdtls.setFin_year(warehouse.getFin_year());
			stackdtls.setUsername(warehouse.getUsername());
			
			stackdtls.setModified_type("INSERTED");
			stackdtls.setInserted_by(warehouse.getInserted_by());
			stackdtls.setInserted_on(warehouse.getInserted_on());
			stackdtls.setUpdated_by(warehouse.getUpdated_by());
			stackdtls.setUpdated_on(warehouse.getUpdated_on());
			stackdtls.setDeleted_by(warehouse.getDeleted_by());
			stackdtls.setDeleted_on(warehouse.getDeleted_on());
		}
	
		warehouse.setWarehouse_stack_dtls(stackset);
		
		
		if(op.isPresent())
		{
			warehouse.setId(id);
		}
		return warehouseMasterRepository.save(warehouse);
	}
	
	@Transactional
	public Warehouse_master deleteWarehouse(Warehouse_master warehouse,long id)
	{
		Optional<Warehouse_master> op = warehouseMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Warehouse_master wMaster=op.get();
		
		wMaster.setDist_code(op.get().getDist_code());
		wMaster.setInserted_by(op.get().getInserted_by());
		wMaster.setInserted_on(op.get().getInserted_on());
		wMaster.setUpdated_by(op.get().getUpdated_by());
		wMaster.setUpdated_on(op.get().getUpdated_on());
		wMaster.setDeleted_by(userRepository.getUserDetails(warehouse.getUsername()).getName());
		wMaster.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			wMaster.setId(id);
		}
		
		//Dynamic
		warehouse_stack_dtlsRepository.warehouse_stack_dtlsDelete(op.get().getWarehouse_id());
		
		if(binMasterRepository.getBinDtlsThruWH(op.get().getWarehouse_code()).isPresent()) 
		{
			return warehouse;
		}
		else 
		{
			wMaster.setModified_type("DELETED");
			return warehouseMasterRepository.save(wMaster);
		}

	}
	
	public List<WarehouseMasterDTO> getWarehouseNameCode() {
		List<Warehouse_master> wareList=new ArrayList<Warehouse_master>();
		wareList.addAll(warehouseMasterRepository.findAll());
				
		List<Warehouse_master> allData = wareList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.isWarehouse_active()==true)
			  .sorted(Comparator.comparing(Warehouse_master::getWarehouse_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<WarehouseMasterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<WarehouseMasterDTO> warehouseList = new ModelMapper().map(allData,listType);
		
		return warehouseList;	
	}
	
	public WarehouseMasterDTO getWHNListbyCode(String whCode)
	{
		Warehouse_master modelList=warehouseMasterRepository.getWHNListbyCode(whCode);
		Type listType=new TypeToken<WarehouseMasterDTO>() {}.getType();
		
		WarehouseMasterDTO whNameList=new ModelMapper().map(modelList, listType);
		
		return whNameList;
	}
	
	public List<WarehouseMasterDTO> getWHListbyBUnit(String bunit) {
		
		List<Warehouse_master> wareList=new ArrayList<Warehouse_master>();
		wareList.addAll(warehouseMasterRepository.findAll());
				
		List<Warehouse_master> allData = wareList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getBusinessunit_code().equals(bunit) && c.isWarehouse_active()==true)
			  .sorted(Comparator.comparing(Warehouse_master::getWarehouse_name))
			  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<WarehouseMasterDTO>>() {}.getType();
		List<WarehouseMasterDTO> warehouseList = new ModelMapper().map(allData,listType);
		
		return warehouseList;	
	}
	
	public List<Map<String, Object>> getWHListbyBUnitFastApi(String bunit)
	{
		List<Map<String, Object>> warehouseNewList = new ArrayList<Map<String, Object>>();
		 
		warehouseNewList.addAll(warehouseMasterRepository.getWarehouseFastList(bunit));
		return warehouseNewList;
	}
	
	public List<WarehouseMasterDTO> getAllWarehouse()
	{
		List<Warehouse_master> warehouseList= new ArrayList<Warehouse_master>();
		Warehouse_master def=new Warehouse_master();
		def.setWarehouse_code("0");
		def.setWarehouse_name("Choose an Option");
		warehouseList.add(def);
		warehouseList.addAll(warehouseMasterRepository.warehouseNewList());
		
		Type listType = new TypeToken<List<WarehouseMasterDTO>>() {}.getType();
		List<WarehouseMasterDTO> warehouse= new ModelMapper().map(warehouseList,listType);
		return warehouse;
	}
	
	public List<Map<String, Object>> getStackNoByWarehouse(String wh_code)
	{
		List<Map<String, Object>> stackNoByWarehouseNewList = new ArrayList<Map<String, Object>>();
		 
		stackNoByWarehouseNewList.addAll(warehouse_stack_dtlsRepository.getStackNoByWarehouseList(wh_code));
		return stackNoByWarehouseNewList;
	}
	
	public Map<String, Object> getStackUom(String rack)
	{	 
		return warehouse_stack_dtlsRepository.getStackUom(rack);
	}
	
	public SequenceIdDTO getWareHouseSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=warehouseMasterRepository.getWareHouseSequenceId(fprefix,company);
		System.err.println("No: > "+gen_sno);
		
		if(gen_sno != null ) {
			slno=Long.parseLong(gen_sno);
		}
		
		String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
		
		genCode.setSequenceid(gen_slno);
		
		return genCode;
	}

	public List<Warehouse_master> findAllWarehouse(String searchtext)
	{
		List<Warehouse_master> warehouseList=new ArrayList<Warehouse_master>();
		warehouseList.addAll(warehouseMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) 
		{
			List<Warehouse_master> allData = warehouseList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Warehouse_master::getWarehouse_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Warehouse_master> allData = warehouseList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getWarehouse_name()+c.getWarehouse_address()+c.getWarehouse_code()+c.getState_name()+c.getDist_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Warehouse_master::getWarehouse_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
}
