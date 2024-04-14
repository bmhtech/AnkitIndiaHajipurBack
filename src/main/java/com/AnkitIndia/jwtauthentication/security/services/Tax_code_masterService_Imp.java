package com.AnkitIndia.jwtauthentication.security.services;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Tax_code_details;
import com.AnkitIndia.jwtauthentication.model.Tax_code_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_Service_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.TaxTypeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tax_code_masterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Tax_code_detailsDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class Tax_code_masterService_Imp implements Tax_code_masterService{
	
	@Autowired
	Tax_code_masterRepository tax_code_masterRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaxTypeMasterRepository taxTypeMasterRepository;
	
	@Autowired
	Item_Service_masterRepository item_Service_masterRepository;
	
	@Transactional
	public Tax_code_master save(Tax_code_master tax_code_master) {
		
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix= "TC";
		if(tax_code_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(tax_code_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix, slno);
		tax_code_master.setTax_id(gen_sno);
		tax_code_master.setTax_code(gen_sno);
		tax_code_master.setModified_type("INSERTED");
		tax_code_master.setInserted_by(userRepository.getUserDetails(tax_code_master.getUsername()).getName());
		tax_code_master.setInserted_on(ldt);
		tax_code_master.setUpdated_by("NA");
		tax_code_master.setUpdated_on(ldt);
		tax_code_master.setDeleted_by("NA");
		tax_code_master.setDeleted_on(ldt);
		
		//long dslno=0;String dprefix= "TC";
		//if(tax_code_detailsRepository.getMaxTaxCodes(dprefix) != null) {
		//	dslno=Long.parseLong(tax_code_detailsRepository.getMaxTaxCodes(dprefix));
		//}
		
		Set<Tax_code_details> aacNormsSet = new HashSet<Tax_code_details>();
		aacNormsSet.addAll(tax_code_master.getTax_code_details());
		for(Tax_code_details aanmdts:aacNormsSet)
		{
			aanmdts.setTax_code(gen_sno);
		//	aanmdts.setTax_id(UniqueID.uniqueid(dprefix, dslno));
			aanmdts.setTax_name(taxTypeMasterRepository.gettaxtypename(aanmdts.getTax_id()).getTaxtype_name());
			aanmdts.setCompany_id(tax_code_master.getCompany_id());
			aanmdts.setFin_year(tax_code_master.getFin_year());
			aanmdts.setModified_type(tax_code_master.getModified_type());
			aanmdts.setInserted_by(tax_code_master.getInserted_by());
			aanmdts.setInserted_on(tax_code_master.getInserted_on());
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
			
		//	dslno++;
		}
		tax_code_master.setTax_code_details(aacNormsSet);
		
		return tax_code_masterRepository.save(tax_code_master);
	}
	
	public List<Tax_code_master> findAll()
	{
		List<Tax_code_master> taxList=new ArrayList<Tax_code_master>();
		taxList.addAll(tax_code_masterRepository.findAll());
		
		List<Tax_code_master> allData = taxList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Tax_code_master::getTax_id).reversed())
				  .collect(Collectors.toList());
		
		return allData;
	}
	
	
	public Tax_code_master findOne(long id)
	{
		Optional<Tax_code_master> op=this.tax_code_masterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Tax_code_master update(Tax_code_master tax_code_master,long id)
	{
		Optional<Tax_code_master> op = tax_code_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		tax_code_master.setTax_id(op.get().getTax_id());
		tax_code_master.setTax_code(op.get().getTax_code());
		
		
		tax_code_master.setModified_type("INSERTED");
		tax_code_master.setInserted_by(op.get().getInserted_by());
		tax_code_master.setInserted_on(op.get().getInserted_on());
		tax_code_master.setUpdated_by(userRepository.getUserDetails(tax_code_master.getUsername()).getName());
		tax_code_master.setUpdated_on(ldt);
		tax_code_master.setDeleted_by("NA");
		tax_code_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			tax_code_master.setId(id);
		}
		
		//Update
		tax_code_detailsRepository.updateTax_code_details(op.get().getTax_code(),"UPDATED");
		
		//long dslno=0;String dprefix= "TC";
	//	if(tax_code_detailsRepository.getMaxTaxCodes(dprefix) != null) {
		//	dslno=Long.parseLong(tax_code_detailsRepository.getMaxTaxCodes(dprefix));
		//}
		
		
		//System.out.println(" dslno :: "+ dslno);
		Set<Tax_code_details> aacNormsSet = new HashSet<Tax_code_details>();
		aacNormsSet.addAll(tax_code_master.getTax_code_details());
		for(Tax_code_details aanmdts:aacNormsSet)
		{
			aanmdts.setTax_code(op.get().getTax_code());
			System.out.println(aanmdts.getTax_id());
			
			aanmdts.setTax_name(taxTypeMasterRepository.gettaxtypename(aanmdts.getTax_id()).getTaxtype_name());
			//tuhinlateruse 24-06-2022
			//aanmdts.setTax_id(UniqueID.uniqueid(dprefix, dslno));
			aanmdts.setSrno(aanmdts.getSrno());
			//if(Utility.isNullOrEmpty(aanmdts.getTax_id())) 
			//{
			//	aanmdts.setTax_id(aanmdts.getTax_id());
			//}
			//else 
			//{
				//System.out.println(Utility.isNullOrEmpty(aanmdts.getTax_id()) + UniqueID.uniqueid(dprefix, dslno) + " / " + dslno);
			//	aanmdts.setTax_id(UniqueID.uniqueid(dprefix, dslno));	
			//	dslno++;
			//}
			aanmdts.setCompany_id(tax_code_master.getCompany_id());
			aanmdts.setFin_year(tax_code_master.getFin_year());
			aanmdts.setModified_type(tax_code_master.getModified_type());
			aanmdts.setInserted_by(tax_code_master.getInserted_by());
			aanmdts.setInserted_on(tax_code_master.getInserted_on());
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
			
			
		}
		tax_code_master.setTax_code_details(aacNormsSet);
		
		return tax_code_masterRepository.save(tax_code_master);
	}
	
	
	@Transactional
	public Tax_code_master deleteTaxCode(Tax_code_master tax_code_master,long id)
	{
		Optional<Tax_code_master> op = tax_code_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Tax_code_master tMaster=op.get();
		
		tMaster.setTax_id(op.get().getTax_id());
		tMaster.setTax_code(op.get().getTax_code());
		tMaster.setInserted_by(op.get().getInserted_by());
		tMaster.setInserted_on(op.get().getInserted_on());
		tMaster.setUpdated_by(op.get().getUpdated_by());
		tMaster.setUpdated_on(op.get().getUpdated_on());
		tMaster.setDeleted_by(userRepository.getUserDetails(tax_code_master.getUsername()).getName());
		tMaster.setDeleted_on(ldt);
		tax_code_masterRepository.updatedynamicdetails(op.get().getTax_code());
		
		if(op.isPresent()) {
			tMaster.setId(id);
		}
		
		tMaster.setModified_type("DELETED");
		tax_code_detailsRepository.updateTax_code_details(op.get().getTax_code(),"DELETED");
		
		return tax_code_masterRepository.save(tMaster);
	}
	
	public List<Tax_code_masterDTO> getTaxNameCode(String company) {
		
		List<Tax_code_master> taxList=new ArrayList<Tax_code_master>();
		taxList.addAll(tax_code_masterRepository.findAll());
		
		List<Tax_code_master> allData = taxList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company))
				  .sorted(Comparator.comparing(Tax_code_master::getTax_description))
				  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<Tax_code_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Tax_code_masterDTO> taxcodeList = new ModelMapper().map(allData,listType);
		
		return taxcodeList;	
	}
	//Delete
	public List<Tax_code_masterDTO> getTaxNameCode() {
		List<Tax_code_master> modelList=tax_code_masterRepository.getTaxNameCode();
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Tax_code_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Tax_code_masterDTO> taxcodeList = new ModelMapper().map(modelList,listType);
		
		return taxcodeList;	
	}
	
	public List<Tax_code_master> getTaxCode() {
		
		List<Tax_code_master> taxList=new ArrayList<Tax_code_master>();
		taxList.addAll(tax_code_masterRepository.findAll());
		
		List<Tax_code_master> allData = taxList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Tax_code_master::getTax_description))
				  .collect(Collectors.toList());
		return allData;	
	}
	
	public Tax_code_masterDTO getTaxNameByTaxCode(String code)
	{
		Tax_code_master modelList=tax_code_masterRepository.getTaxNameByTaxCode(code);
		
		Type listType = new TypeToken<Tax_code_masterDTO>() {}.getType();
		
		Tax_code_masterDTO taxName = new ModelMapper().map(modelList,listType);
		
		return taxName;
		
	}
	
	public List<Tax_code_detailsDTO> getTaxNameRate()
	{
		List<Tax_code_master> modelList=new ArrayList<Tax_code_master>();
		modelList.addAll(tax_code_masterRepository.getTaxNameRate());
			
		Type listType=new TypeToken<List<Tax_code_detailsDTO>>() {}.getType();
		
		List<Tax_code_detailsDTO> taxList=new ModelMapper().map(modelList,listType);
		
		return taxList;
	}
	
	public Tax_code_details taxlistbycode(String code) 
	{
		Tax_code_details modellist =tax_code_masterRepository.getTaxNameByTaxCodesaleorder(code);
		return modellist;
	}
	
	public List<Tax_code_detailsDTO> taxCodeDtlsRetriveList(String t_id)
	{
		//List<Tax_code_master> modelList=new ArrayList<Tax_code_master>();
		
		List<Tax_code_details> modelList=new ArrayList<Tax_code_details>();
		modelList.addAll(tax_code_masterRepository.taxCodeDtlsRetriveList(t_id));
			
		List<Tax_code_details> allData = modelList
				  .parallelStream()
				  .sorted(Comparator.comparing(Tax_code_details::getTax_id))
				  .collect(Collectors.toList());
		
		
		
		Type listType=new TypeToken<List<Tax_code_detailsDTO>>() {}.getType();
		
		List<Tax_code_detailsDTO> taxcode=new ModelMapper().map(allData,listType);
		
		return taxcode;
	}
	
	public Tax_code_detailsDTO getTaxCodeDetails(String t_id)
	{
		Tax_code_details tDetails=tax_code_detailsRepository.getTaxCodeDetails(t_id);
			
		Type listType=new TypeToken<Tax_code_detailsDTO>() {}.getType();
		Tax_code_detailsDTO taxDtls=new ModelMapper().map(tDetails,listType);
		
		taxDtls.setTax_name_rate(taxDtls.getTax_name()+"@"+taxDtls.getTax_rate());
		
		return taxDtls;
	}
	
	public Tax_code_detailsDTO getTaxCodeDetailsname(String tax_name)
	{
		System.out.println("tax_name :: "+tax_name);
		Tax_code_details tDetails=tax_code_detailsRepository.getTaxCodeDetailstax_name(tax_name);
			
		Type listType=new TypeToken<Tax_code_detailsDTO>() {}.getType();
		Tax_code_detailsDTO taxDtls=new ModelMapper().map(tDetails,listType);
		
		taxDtls.setTax_name_rate(taxDtls.getTax_name()+"@"+taxDtls.getTax_rate());
		
		return taxDtls;
	}
	
	
	public SequenceIdDTO getTaxCodeSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=tax_code_masterRepository.getTaxCodeSequenceId(fprefix,company);
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
	
	public List<Tax_code_master> findTaxCode(String searchtext)
	{
		List<Tax_code_master> taxList=new ArrayList<Tax_code_master>();
		taxList.addAll(tax_code_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Tax_code_master> allData = taxList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Tax_code_master::getTax_description))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Tax_code_master> allData = taxList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getTax_code()  +c.getTax_description()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Tax_code_master::getTax_description))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	public Map<String,Object> getServiceItemTax(String name)
	{
		String taxcode=item_Service_masterRepository.getTaxCode(name);
		return tax_code_detailsRepository.getServiceItemTax(taxcode);
	}
	

}
