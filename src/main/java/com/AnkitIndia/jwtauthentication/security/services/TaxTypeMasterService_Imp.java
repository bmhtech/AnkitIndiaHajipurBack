package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Gst_input_output_ledger_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Tax_type_master;

import com.AnkitIndia.jwtauthentication.repository.TaxTypeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TaxTypeMasterDTO;


@Service
public class TaxTypeMasterService_Imp implements TaxTypeMasterService{
	@Autowired
	TaxTypeMasterRepository taxTypeMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SequenceIdDTO getTaxTypeSequenceId() {
		
		long slno=0;String prefix="TTM";
		
		if(taxTypeMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(taxTypeMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueIDTransaction.uniqueId5(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Tax_type_master save(Tax_type_master taxtype) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		//taxtype.setTaxtype_code(gen_sno);
		taxtype.setModified_type("INSERTED");
		taxtype.setInserted_by(userRepository.getUserDetails(taxtype.getUsername()).getName());
		taxtype.setInserted_on(ldt);
		taxtype.setUpdated_by("NA");
		taxtype.setUpdated_on(ldt);
		taxtype.setDeleted_by("NA");
		taxtype.setDeleted_on(ldt);
		
		
		
		
		Set<Gst_input_output_ledger_dtls> taxTypeDyn = new HashSet<Gst_input_output_ledger_dtls>();
		taxTypeDyn.addAll(taxtype.getGst_input_output_ledger_dtls());
		for(Gst_input_output_ledger_dtls taxgst:taxTypeDyn) 
		{
			taxgst.setTaxtype_code(taxtype.getTaxtype_code());
			
			taxgst.setCompany_id(taxtype.getCompany_id());
			taxgst.setFin_year(taxtype.getFin_year());
			taxgst.setModified_type("INSERTED");
			taxgst.setInserted_by(taxtype.getInserted_by());
			taxgst.setInserted_on(taxtype.getInserted_on());
			taxgst.setUpdated_by("NA");
			taxgst.setUpdated_on(ldt);
			taxgst.setDeleted_by("NA");
			taxgst.setDeleted_on(ldt);
			
			//if(!taxTypeDyn.isEmpty())	{
			//	taxtype.setGst_Input_Output_Ledger_Dtls(taxTypeDyn.iterator().next());
			//}	
			taxtype.setGst_input_output_ledger_dtls(taxTypeDyn);
		}
		return taxTypeMasterRepository.save(taxtype);
	}
	
	public List<Tax_type_master> findAll()
	{
		List<Tax_type_master> taxtypeList=new ArrayList<Tax_type_master>();
		taxtypeList.addAll(taxTypeMasterRepository.findAll());
		
		List<Tax_type_master> allData = taxtypeList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Tax_type_master::getTaxtype_code).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Tax_type_master> getTaxTypeNCList()
	{
		List<Tax_type_master> taxtypeList=new ArrayList<Tax_type_master>();
		taxtypeList.addAll(taxTypeMasterRepository.getTaxTypeNCList());
		
		List<Tax_type_master> allData = taxtypeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Tax_type_master::getTaxtype_name))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Tax_type_master findOne(long id)
	{
		Optional<Tax_type_master> op=this.taxTypeMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Tax_type_master update(Tax_type_master taxtype,long id)
	{
		Optional<Tax_type_master> op =taxTypeMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		taxtype.setTaxtype_code(op.get().getTaxtype_code());
		taxtype.setModified_type("UPDATED");
		taxtype.setInserted_by(op.get().getInserted_by());
		taxtype.setInserted_on(op.get().getInserted_on());
		taxtype.setUpdated_by(userRepository.getUserDetails(taxtype.getUsername()).getName());
		taxtype.setUpdated_on(ldt);
		taxtype.setDeleted_by("NA");
		taxtype.setDeleted_on(ldt);
		
		taxTypeMasterRepository.gst_input_output_ledger_dtlsupdate(op.get().getTaxtype_code());
		Set<Gst_input_output_ledger_dtls> taxtypeDyn = new HashSet<Gst_input_output_ledger_dtls>();
		taxtypeDyn.addAll(taxtype.getGst_input_output_ledger_dtls());
		for(Gst_input_output_ledger_dtls taxtypeDts : taxtypeDyn)
		{
			taxtypeDts.setTaxtype_code(op.get().getTaxtype_code());
			
			taxtypeDts.setCompany_id(taxtype.getCompany_id());
			taxtypeDts.setFin_year(taxtype.getFin_year());
			taxtypeDts.setModified_type("INSERTED");
			taxtypeDts.setInserted_by(taxtype.getInserted_by());
			taxtypeDts.setInserted_on(taxtype.getInserted_on());
			taxtypeDts.setUpdated_by(taxtype.getUpdated_by());
			taxtypeDts.setUpdated_on(taxtype.getUpdated_on());
			taxtypeDts.setDeleted_by("NA");
			taxtypeDts.setDeleted_on(ldt);
		
		}
		taxtype.setGst_input_output_ledger_dtls(taxtypeDyn);
		
		if(op.isPresent())
		{
			taxtype.setId(id);
		}
		return taxTypeMasterRepository.save(taxtype);
	}
	
	@Transactional
	public Tax_type_master deleteTax_type(Tax_type_master taxtype,long id)
	{
		Optional<Tax_type_master> op = taxTypeMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Tax_type_master taxtypeMaster=op.get();

		taxtypeMaster.setInserted_by(op.get().getInserted_by());
		taxtypeMaster.setInserted_on(op.get().getInserted_on());
		taxtypeMaster.setUpdated_by(op.get().getUpdated_by());
		taxtypeMaster.setUpdated_on(op.get().getUpdated_on());
		taxtypeMaster.setDeleted_by(userRepository.getUserDetails(taxtype.getUsername()).getName());
		taxtypeMaster.setDeleted_on(ldt);
		taxtypeMaster.setModified_type("DELETED");
		
		if(op.isPresent())
		{
			taxtypeMaster.setId(id);
		}
		
		return taxTypeMasterRepository.save(taxtypeMaster);
	}
	
	
	public List<TaxTypeMasterDTO> getTaxTypeNameCode() {
		
		List<Tax_type_master> taxtype1List=new ArrayList<Tax_type_master>();
		taxtype1List.addAll(taxTypeMasterRepository.findAll());
				
		List<Tax_type_master> allData = taxtype1List
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Tax_type_master::getTaxtype_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<TaxTypeMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<TaxTypeMasterDTO> taxtypeList= new ModelMapper().map(allData,listType);
		
		return taxtypeList;
	}
	
	public List<Tax_type_master> findTax_type(String searchtext)
	{
		List<Tax_type_master> vehicleList=new ArrayList<Tax_type_master>();
		vehicleList.addAll(taxTypeMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Tax_type_master> allData = vehicleList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Tax_type_master::getTaxtype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Tax_type_master> allData = vehicleList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getTaxtype_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Tax_type_master::getTaxtype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public Gst_input_output_ledger_dtls getgstdetailsoftaxtype(String taxtypecode)
	{
		Gst_input_output_ledger_dtls modelList=taxTypeMasterRepository.getgstdetailsoftaxtype(taxtypecode);

		return modelList;
	}
	
	public List<Gst_input_output_ledger_dtls> retriveTaxTypeGst(String taxtypecode)
	{
		List<Gst_input_output_ledger_dtls> modelList=taxTypeMasterRepository.retriveTaxTypeGst(taxtypecode);

		return modelList;
	}
}
