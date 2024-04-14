package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Invoice_type;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_typeRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Invoice_typeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class Invoice_typeService_Imp implements Invoice_typeService{

	@Autowired
	Invoice_typeRepository  invoice_typeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Invoice_type save(Invoice_type invoice_type)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="INV";
		if(invoice_typeRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(invoice_typeRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		invoice_type.setInvtype_id(gen_sno);
		invoice_type.setModified_type("INSERTED");
		invoice_type.setInserted_by(userRepository.getUserDetails(invoice_type.getUsername()).getName());
		invoice_type.setInserted_on(ldt);
		invoice_type.setUpdated_by("NA");
		invoice_type.setUpdated_on(ldt);
		invoice_type.setDeleted_by("NA");
		invoice_type.setDeleted_on(ldt);
		
		return invoice_typeRepository.save(invoice_type);
	}
	
	@Transactional
	public Invoice_type update(Invoice_type invoice_type,long id)
	{
		Optional<Invoice_type> op = invoice_typeRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		invoice_type.setInvtype_id(op.get().getInvtype_id());
		invoice_type.setModified_type("UPDATED");
		invoice_type.setInserted_by(op.get().getInserted_by());
		invoice_type.setInserted_on(op.get().getInserted_on());
		invoice_type.setUpdated_by(userRepository.getUserDetails(invoice_type.getUsername()).getName());
		invoice_type.setUpdated_on(ldt);
		invoice_type.setDeleted_by("NA");
		invoice_type.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			invoice_type.setId(id);
		}
		return invoice_typeRepository.save(invoice_type);
	}
	
	@Transactional
	public Invoice_type deleteInvoiceType(Invoice_type invoice_type,long id)
	{
		Optional<Invoice_type> op = invoice_typeRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Invoice_type invoiceMaster=op.get();

		invoiceMaster.setInserted_by(op.get().getInserted_by());
		invoiceMaster.setInserted_on(op.get().getInserted_on());
		invoiceMaster.setUpdated_by(op.get().getUpdated_by());
		invoiceMaster.setUpdated_on(op.get().getUpdated_on());
		invoiceMaster.setDeleted_by(userRepository.getUserDetails(invoice_type.getUsername()).getName());
		invoiceMaster.setDeleted_on(ldt);
		invoiceMaster.setModified_type("DELETED");
		
		if(op.isPresent())
		{
			invoiceMaster.setId(id);
		}
		return invoice_typeRepository.save(invoiceMaster);
	}
	
	public List<Invoice_type> findAll()
	{
		List<Invoice_type> areaList=new ArrayList<Invoice_type>();
		areaList.addAll(invoice_typeRepository.findAll());
				
		List<Invoice_type> allData = areaList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Invoice_type::getInvtype_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Invoice_type findOne(long id)
	{
		Optional<Invoice_type> op=this.invoice_typeRepository.findById(id);
		return op.get();
	}
	
	public List<Invoice_typeDTO> getSalesInvTypes()
	{
		List<Invoice_type> inv1List=new ArrayList<Invoice_type>();
		inv1List.addAll(invoice_typeRepository.findAll());
				
		List<Invoice_type> allData = inv1List
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Invoice_type::getInvtype_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Invoice_typeDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Invoice_typeDTO> invList= new ModelMapper().map(allData,listType);
		
		return invList;
	}
	
	public List<Map<String,Object>> getSalesInvTypesFast()
	{
		List<Map<String,Object>> invList=invoice_typeRepository.getSalesInvTypesFast();
		return invList;
	}
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	public SequenceIdDTO getInvtypeSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=invoice_typeRepository.getInvtypeSequenceId(fprefix,company);
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
	
	public List<Invoice_type> findInvoice_type(String searchtext)
	{
		List<Invoice_type> invList=new ArrayList<Invoice_type>();
		invList.addAll(invoice_typeRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Invoice_type> allData = invList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Invoice_type::getInvtype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Invoice_type> allData = invList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getInvtype_name()+c.getInvtype_code()+c.getInvtype_prefix()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Invoice_type::getInvtype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
}
