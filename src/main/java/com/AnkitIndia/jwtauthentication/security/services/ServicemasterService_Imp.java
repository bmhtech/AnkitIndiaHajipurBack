package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Service_masterdtls;
import com.AnkitIndia.jwtauthentication.model.Servicemaster;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Service_masterdtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.ServicemasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class ServicemasterService_Imp implements ServicemasterService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	ServicemasterRepository servicemasterRepository;
	
	@Autowired
	Service_masterdtlsRepository service_masterdtlsRepository;
	
	public SequenceIdDTO getSSequenceId(String fin_year) 
	{
		int slno=0;
		String sno=servicemasterRepository.countservicemaster();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="JSM-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Servicemaster save(Servicemaster servicemaster) {
		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=servicemasterRepository.countservicemaster();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=servicemaster.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			String prefix="JSM-"+final_fyear+"-";
			String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			servicemaster.setService_no(gen_sno);
			
			servicemaster.setService_acc_code(servicemaster.getService_acc_code());
			servicemaster.setService_group(servicemaster.getService_group());
			//servicemaster.setService_groupname(servicemaster.getService_groupname());
			servicemaster.setService_type(servicemaster.getService_type());
			servicemaster.setService_subtype(servicemaster.getService_subtype());
			//servicemaster.setService_subtypename(servicemaster.getService_subtypename());
			servicemaster.setService_ac(servicemaster.getService_ac());
			//servicemaster.setService_acname(servicemaster.getService_acname());
			servicemaster.setDescription(servicemaster.getDescription());
			servicemaster.setTax_name(servicemaster.getTax_name());
			servicemaster.setTax_id(servicemaster.getTax_id());
			servicemaster.setTax_rate(servicemaster.getTax_rate());
			servicemaster.setService_item_type(servicemaster.getService_item_type());
			servicemaster.setRemarks(servicemaster.getRemarks());
			servicemaster.setModified_type("INSERTED");
			servicemaster.setInserted_by(userRepository.getUserDetails(servicemaster.getUsername()).getName());
			servicemaster.setInserted_on(ldt);
			servicemaster.setUpdated_by("NA");
			servicemaster.setUpdated_on(ldt);
			servicemaster.setDeleted_by("NA");
			servicemaster.setDeleted_on(ldt);
		
			Set<Service_masterdtls> service_masterdtls=new HashSet<Service_masterdtls>();
			service_masterdtls.addAll(servicemaster.getService_masterdtls());
			for(Service_masterdtls sDetails:service_masterdtls) 
			{
				sDetails.setService_no(gen_sno);
				sDetails.setService_name(sDetails.getService_name());
				sDetails.setRemarks_a(sDetails.getRemarks_a());
				sDetails.setCompany_id(servicemaster.getCompany_id());
				sDetails.setFin_year(servicemaster.getFin_year());
				sDetails.setModified_type("INSERTED");
				sDetails.setInserted_by(servicemaster.getInserted_by());
				sDetails.setInserted_on(ldt);
				sDetails.setUpdated_by("NA");
				sDetails.setUpdated_on(ldt);
				sDetails.setDeleted_by("NA");
				sDetails.setDeleted_on(ldt);
				sDetails.setUsername(servicemaster.getUsername());
			}
			servicemaster.setService_masterdtls(service_masterdtls);
			
		return servicemasterRepository.save(servicemaster);
		
	}
	
	public List<Map<String, Object>> getServiceMasterList(String fin_year){
		List<Map<String, Object>> ServiceMasterList = new ArrayList<Map<String, Object>>();
		ServiceMasterList.addAll(servicemasterRepository.getServiceMasterList(fin_year));
		return ServiceMasterList;
	}
	
	public Servicemaster findOne(long id)
	{
		Optional<Servicemaster> op=this.servicemasterRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String, Object>> serviceRetriveList(String s_no)
	{
		List<Map<String, Object>> serviceList=new ArrayList<Map<String, Object>>();
		
		serviceList.addAll(servicemasterRepository.getServiceMasterdtlsList(s_no));
		
		return serviceList;
	}
	
	
	@Transactional
	public Servicemaster update(Servicemaster servicemaster,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Servicemaster> op = servicemasterRepository.findById(id);
		
		servicemaster.setService_no(op.get().getService_no());
		servicemaster.setService_acc_code(servicemaster.getService_acc_code());
		servicemaster.setService_group(servicemaster.getService_group());
		servicemaster.setService_groupname(servicemaster.getService_groupname());
		servicemaster.setService_type(servicemaster.getService_type());
		servicemaster.setService_subtype(servicemaster.getService_subtype());
		servicemaster.setService_subtypename(servicemaster.getService_subtypename());
		servicemaster.setService_ac(servicemaster.getService_ac());
		servicemaster.setService_acname(servicemaster.getService_acname());
		servicemaster.setDescription(servicemaster.getDescription());
		servicemaster.setTax_name(servicemaster.getTax_name());
		servicemaster.setTax_id(servicemaster.getTax_id());
		servicemaster.setTax_rate(servicemaster.getTax_rate());
		servicemaster.setService_item_type(servicemaster.getService_item_type());
		servicemaster.setRemarks(servicemaster.getRemarks());
		servicemaster.setModified_type("INSERTED");
		servicemaster.setInserted_by(op.get().getInserted_by());
		servicemaster.setInserted_on(op.get().getInserted_on());
		servicemaster.setUpdated_by(userRepository.getUserDetails(servicemaster.getUsername()).getName());
		servicemaster.setUpdated_on(ldt);
		servicemaster.setDeleted_by(op.get().getDeleted_by());
		servicemaster.setDeleted_on(op.get().getDeleted_on());
		
		//Dynamic
		service_masterdtlsRepository.updateService_masterdtls(servicemaster.getService_no(),"UPDATED");
		Set<Service_masterdtls> serviceset = new HashSet<Service_masterdtls>();
		serviceset.addAll(servicemaster.getService_masterdtls());
		for(Service_masterdtls servicedts : serviceset)
		{
			servicedts.setService_no(servicemaster.getService_no());
			
			servicedts.setService_name(servicedts.getService_name());
			servicedts.setRemarks_a(servicedts.getRemarks_a());
			servicedts.setCompany_id(servicemaster.getCompany_id());
			servicedts.setFin_year(servicemaster.getFin_year());
			servicedts.setUsername(servicemaster.getUsername());
			
			servicedts.setModified_type("INSERTED");
			servicedts.setInserted_by(servicemaster.getInserted_by());
			servicedts.setInserted_on(servicemaster.getInserted_on());
			servicedts.setUpdated_by(servicemaster.getUpdated_by());
			servicedts.setUpdated_on(servicemaster.getUpdated_on());
			servicedts.setDeleted_by(servicemaster.getDeleted_by());
			servicedts.setDeleted_on(servicemaster.getDeleted_on());
		}
	
		servicemaster.setService_masterdtls(serviceset);
		
		if(op.isPresent())
		{
			servicemaster.setId(id);
		}
		return servicemasterRepository.save(servicemaster);
	}
	
	@Transactional
	public Servicemaster delete(Servicemaster servicemaster, long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Servicemaster> op = servicemasterRepository.findById(id);
		
		Servicemaster sm=op.get();
		
		sm.setModified_type("DELETED");
		sm.setInserted_by(op.get().getInserted_by());
		sm.setInserted_on(op.get().getInserted_on());
		sm.setUpdated_by(op.get().getUpdated_by());
		sm.setUpdated_on(op.get().getUpdated_on());
		sm.setDeleted_by(userRepository.getUserDetails(servicemaster.getUsername()).getName());
		sm.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
		  sm.setId(id);
		}
		
		service_masterdtlsRepository.service_masterdtlsUpdate(op.get().getService_no());
		
		return servicemasterRepository.save(sm);
	}
	
	public StatusDTO checkServiceMasterUsage(String service_no){
		String result=servicemasterRepository.checkServiceMasterUsage(service_no);
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
	
}
