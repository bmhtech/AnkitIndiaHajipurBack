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
import com.AnkitIndia.jwtauthentication.model.Channel_customer_master;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Party_Dtls;
import com.AnkitIndia.jwtauthentication.repository.Channel_customer_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Party_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Supplier_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_customer_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;


@Service
public class Channel_customer_masterService_Imp implements Channel_customer_masterService {
	
	@Autowired
	Channel_customer_masterRepository cMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Cust_groupRepository cust_groupRepository;
	
	@Autowired
	Supplier_groupRepository supplier_groupRepository;
	
	@Autowired
	Pur_OrderRepository pur_OrderRepository;
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	@Autowired
	Sales_Order_Party_DtlsRepository sales_Order_Party_DtlsRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Transactional
	public Channel_customer_master save(Channel_customer_master cMaster)
	{
        LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="CCM";
		if(cMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(cMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		if(cMaster.getChanneltype().compareToIgnoreCase("Sale")==0)
		{
			cMaster.setGroup_type_name(cust_groupRepository.getCustParentGroup(cMaster.getGroup_type()).getGrp_name());
		}
		else
		{
			
			cMaster.setGroup_type_name(supplier_groupRepository.getSuppParentGroup(cMaster.getGroup_type()).getBp_grp_name());
		}
		cMaster.setChannel_custid(gen_sno);
		cMaster.setTerminate(false);
		cMaster.setModified_type("INSERTED");
		cMaster.setInserted_by(userRepository.getUserDetails(cMaster.getUsername()).getName());
		cMaster.setInserted_on(ldt);
		cMaster.setUpdated_by("NA");
		cMaster.setUpdated_on(ldt);
		cMaster.setDeleted_by("NA");
		cMaster.setDeleted_on(ldt);
		
		return cMasterRepository.save(cMaster);
	}
	
	public List<Channel_customer_master> findAll()
	{
		List<Channel_customer_master> ccList=new ArrayList<Channel_customer_master>();
		//ccList.addAll(cMasterRepository.findAll());
		ccList.addAll(cMasterRepository.getChannelCustDescNew());		
		List<Channel_customer_master> allData = ccList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Channel_customer_master::getChannel_custid).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Map<String, Object>> getChannelCustForSales()
	{
		return cMasterRepository.getChannelCustForSales();
	}
	
	public List<Map<String, Object>> getChannelCustFastApi()
	{
		return cMasterRepository.getChannelCustFastApi();
	}
	
	public Channel_customer_master findOne(Long id) {
		 Optional<Channel_customer_master> op=this.cMasterRepository.findById(id);
		 return op.get();
	}

	@Transactional
	public StatusDTO terminatechannel(long id)
	{
		cMasterRepository.updateChannelTerminate(id);
		StatusDTO res=new StatusDTO();
		res.setStatus("Yes");
		return res;
		
	}
	@Transactional
	public Channel_customer_master update(Channel_customer_master pMaster,long id)
	{
		
		Optional<Channel_customer_master> op = cMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		if(pMaster.getChanneltype().compareToIgnoreCase("Sale")==0)
		{
		/*	pMaster.setGroup_type_name(cust_groupRepository.getCustParentGroup(pMaster.getGroup_type()).getGrp_name());
			//get all sale order id in a list
			//update prvious sale order channel to updated
			//insert new row in sales order party details
			List<Sales_Order> saleslist = new ArrayList<>();
			saleslist.addAll(sales_OrderRepository.getsalesorderthroughchannel(op.get().getChannel_custid()));
			
			String customerchannelid [] = pMaster.getCustid().split(",");
			
			if(saleslist.size()>1) 
			{
				Set<Sales_Order_Party_Dtls> bpSet = new HashSet<Sales_Order_Party_Dtls>();
				for(int i=0;i<saleslist.size();i++) 
				{
					
					
					sales_Order_Party_DtlsRepository.updatepreviouschannelpartynames(saleslist.get(i).getOrder_id());
					
			         for(int v=0;v<customerchannelid.length;v++)
			         {
			        	 	
			        	        Sales_Order_Party_Dtls partydetails= new Sales_Order_Party_Dtls();
			        		
			        	        partydetails.setOrder_id(saleslist.get(i).getOrder_id());
			        	        partydetails.setOrder_no(saleslist.get(i).getOrder_no());
			        	        partydetails.setP_name(cust_bussiness_partnerRepository.getCustomer(customerchannelid[v]).getCp_name());
			        	      
			        	        partydetails.setSl_no(v+1);
			        	        partydetails.setTcs_rate(0.00);
			        	        partydetails.setCity_approved(false);
			        	        partydetails.setCp_city("");
			        	        partydetails.setSend_via("");
			        	        partydetails.setCompany_id(saleslist.get(i).getCompany_id());
			        	        partydetails.setFin_year(saleslist.get(i).getFin_year());
			        	        partydetails.setModified_type("INSERTED");
			        	        partydetails.setInserted_by(saleslist.get(i).getInserted_by());
			        	        partydetails.setInserted_on(saleslist.get(i).getInserted_on());
			        	        partydetails.setUpdated_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
			        	        partydetails.setUpdated_on(ldt);
			        	        partydetails.setDeleted_by("NA");
			        	        partydetails.setDeleted_on(ldt);
			        	        
			        	        bpSet.add(partydetails);
			         }
			         
					
				}
				pMaster.setSales_Order_Party_Dtls(bpSet);
			}
			
			
			
			*/
			
			pMaster.setGroup_type_name(cust_groupRepository.getCustParentGroup(pMaster.getGroup_type()).getGrp_name());
			
			sales_OrderRepository.changeChannelCustomerList(pMaster.getCustid(),op.get().getChannel_custid());
		}
		else
		{
			pMaster.setGroup_type_name(supplier_groupRepository.getSuppParentGroup(pMaster.getGroup_type()).getBp_grp_name());
		
			pur_OrderRepository.changeChannelSupplierList( pMaster.getCustid(),op.get().getChannel_custid());
		}
		pMaster.setTerminate(false);
		pMaster.setChannel_custid(op.get().getChannel_custid());
		pMaster.setModified_type("UPDATED");
		pMaster.setInserted_by(op.get().getInserted_by());
		pMaster.setInserted_on(op.get().getInserted_on());
		pMaster.setUpdated_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setUpdated_on(ldt);
		pMaster.setDeleted_by("NA");
		pMaster.setDeleted_on(ldt);
		
		
		cMasterRepository.updateChannelTerminate(id);
		
		if(op.isPresent())
		{
			pMaster.setId(id);
		}
		return cMasterRepository.save(pMaster);
	}
	
	@Transactional
	public Channel_customer_master deleteChannel_customer(Channel_customer_master cMaster,long id)
	{
		Optional<Channel_customer_master> op = cMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Channel_customer_master ccMaster=op.get();

		ccMaster.setInserted_by(op.get().getInserted_by());
		ccMaster.setInserted_on(op.get().getInserted_on());
		ccMaster.setUpdated_by(op.get().getUpdated_by());
		ccMaster.setUpdated_on(op.get().getUpdated_on());
		ccMaster.setDeleted_by(userRepository.getUserDetails(cMaster.getUsername()).getName());
		ccMaster.setDeleted_on(ldt);
		ccMaster.setModified_type("DELETED");
		
		if(op.isPresent())
		{
			ccMaster.setId(id);
		}
		return cMasterRepository.save(ccMaster);
	}
	
	
	public List<Channel_customer_masterDTO> getChannelCustDesc(){
		List<Channel_customer_master> ccList=new ArrayList<Channel_customer_master>();
		/*ccList.addAll(cMasterRepository.findAll());
				
		List<Channel_customer_master> allData = ccList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Channel_customer_master::getChannel_desc))
			  .collect(Collectors.toList());
		*/
		ccList.addAll(cMasterRepository.getChannelCustDescNew());
		List<Channel_customer_master> allData = ccList
				  .stream()
				  .sorted(Comparator.comparing(Channel_customer_master::getChannel_desc))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<Channel_customer_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Channel_customer_masterDTO> desc= new ModelMapper().map(allData,listType);
		
		return desc;
		
	}
	
	public List<Channel_customer_master> findChannel_customer(String searchtext)
	{
		List<Channel_customer_master> ccList=new ArrayList<Channel_customer_master>();
		ccList.addAll(cMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Channel_customer_master> allData = ccList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Channel_customer_master::getChannel_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Channel_customer_master> allData = ccList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getChannel_custid()+c.getChannel_desc()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Channel_customer_master::getChannel_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO checkChennelCustomerMasterUsage(String code)
 	{
		StatusDTO result = new StatusDTO();
		
		boolean purchase=false;
		
		if(cMasterRepository.checkChennelCustomerMasterUsage(code))//need to change Query 
		{
			purchase=true;
		}
		if(purchase == true)
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		return result;
	 }

}
