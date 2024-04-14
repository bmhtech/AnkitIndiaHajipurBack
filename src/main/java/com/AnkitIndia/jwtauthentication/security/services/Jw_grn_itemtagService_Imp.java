package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Jw_grn_itemtag;
import com.AnkitIndia.jwtauthentication.model.Jw_grn_partytag_details;
import com.AnkitIndia.jwtauthentication.model.Jw_grn_partywitem_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nonservice_desc_details;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;
import com.AnkitIndia.jwtauthentication.model.Seives_master;
import com.AnkitIndia.jwtauthentication.repository.Jw_grn_itemtagRepository;
import com.AnkitIndia.jwtauthentication.repository.Jw_grn_partytag_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Jw_grn_partywitem_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.ResponseDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Jw_grn_itemtagService_Imp implements Jw_grn_itemtagService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Jw_grn_itemtagRepository jw_grn_itemtagRepository;
	
	@Autowired
	Jw_grn_partytag_detailsRepository jw_grn_partytag_detailsRepository;
	
	@Autowired
	Jw_grn_partywitem_detailsRepository jw_grn_partywitem_detailsRepository;
	
	
	public List<Map<String,Object>> getJwGRN()
	{
		return jw_grn_itemtagRepository.getJwGRN();
	}
	
	public List<Map<String,Object>> getJwGRNunique()
	{
		return jw_grn_itemtagRepository.getJwGRNunique();
	}
	
	public List<Map<String,Object>> getJobItemList()
	{
		return jw_grn_itemtagRepository.getJobItemList(true);
	}
	
	public List<Map<String,Object>> getJobItemTagMaster()
	{
		return jw_grn_itemtagRepository.getJobItemTagMaster();
	}
	
	@Transactional
	public Jw_grn_itemtag save(Jw_grn_itemtag jig) {
		    LocalDateTime ldt = LocalDateTime.now();
		    int slno=0;
		    String sno=jw_grn_itemtagRepository.countJobItemTag();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueId6("JIG",slno);
			jig.setJw_grn_tag(gen_sno);
			jig.setModified_type("INSERTED");
			jig.setInserted_by(userRepository.getUserDetails(jig.getUsername()).getName());
			jig.setInserted_on(ldt);
			jig.setUpdated_by("NA");
			jig.setUpdated_on(ldt);
			jig.setDeleted_by("NA");
			jig.setDeleted_on(ldt);
			
			//jw_grn_itemtagRepository.updatePurGRNPartyTag(jig.getGrn_id(),jig.getGrn_no());
			
			Set<Jw_grn_partytag_details> jw_grn_partytag_details=new HashSet<Jw_grn_partytag_details>();
			jw_grn_partytag_details.addAll(jig.getJw_grn_partytag_details());
			for(Jw_grn_partytag_details partydtls:jw_grn_partytag_details) 
			{
				partydtls.setJw_grn_tag(gen_sno);
				partydtls.setCompany_id(jig.getCompany_id());
				partydtls.setFin_year(jig.getFin_year());
				partydtls.setModified_type("INSERTED");
				partydtls.setInserted_by(jig.getInserted_by());
				partydtls.setInserted_on(ldt);
				partydtls.setUpdated_by("NA");
				partydtls.setUpdated_on(ldt);
				partydtls.setDeleted_by("NA");
				partydtls.setDeleted_on(ldt);
				partydtls.setUsername(jig.getUsername());
				
				Set<Jw_grn_partywitem_details> jw_grn_partywitem_details=new HashSet<Jw_grn_partywitem_details>();
				jw_grn_partywitem_details.addAll(partydtls.getJw_grn_partywitem_details());
				for(Jw_grn_partywitem_details itemdtls:jw_grn_partywitem_details) 
				{
					itemdtls.setJw_grn_tag(gen_sno);
					itemdtls.setCompany_id(jig.getCompany_id());
					itemdtls.setFin_year(jig.getFin_year());
					itemdtls.setModified_type("INSERTED");
					itemdtls.setInserted_by(jig.getInserted_by());
					itemdtls.setInserted_on(ldt);
					itemdtls.setUpdated_by("NA");
					itemdtls.setUpdated_on(ldt);
					itemdtls.setDeleted_by("NA");
					itemdtls.setDeleted_on(ldt);
					itemdtls.setUsername(jig.getUsername());
				}
				partydtls.setJw_grn_partywitem_details(jw_grn_partywitem_details);
			}
			jig.setJw_grn_partytag_details(jw_grn_partytag_details);
			
		return jw_grn_itemtagRepository.save(jig);
	}
	
	public Jw_grn_itemtag findOne(long id)
	{
		Optional<Jw_grn_itemtag> op=this.jw_grn_itemtagRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String, Object>> getJwGrnPartytagDetails(String jw_grn_tag)
	{
		return jw_grn_partytag_detailsRepository.getJwGrnPartytagDetails(jw_grn_tag);
	}
	
	public List<Map<String, Object>> getJwGrnPartywitemDetails(String jw_grn_tag,String party)
	{
		return jw_grn_partywitem_detailsRepository.getJwGrnPartywitemDetails(jw_grn_tag,party);
	}
	
	public StatusDTO checkdeleteJobItemTagMaster(String jw_grn_tag) 
	{
		StatusDTO res = new StatusDTO();
		
		List<Jw_grn_partywitem_details> details= new ArrayList<Jw_grn_partywitem_details>();
		details.addAll(jw_grn_partywitem_detailsRepository.getJobItemTagMaster(jw_grn_tag));
		
		boolean respon_output=false;
		for(int i=0;i<details.size();i++) 
		{

			if(details.get(i).getQty()>Double.parseDouble(jw_grn_partywitem_detailsRepository.checkrestwt(details.get(i).getJob_item(),details.get(i).getParty()))) 

			{
				respon_output=true;
			}
		}
		if(respon_output) 
		{
			res.setStatus("No");
		}
		else
		{
			res.setStatus("Yes");
		}
		return res;
	}
	
	public ResponseDTO checkjw_itemallocation(String party,String job_item,double qty,String jw_grn_tag) 
	{
		ResponseDTO res = new ResponseDTO();
		boolean respon_output=false;
		
		double previousqty=0.000,maximumqty=0.000,differnceqty=0.000,minimumqty=0.00;
		
		previousqty=jw_grn_partywitem_detailsRepository.getpreviousQty(job_item,party,jw_grn_tag);
		maximumqty=Double.parseDouble(jw_grn_partywitem_detailsRepository.checkrestwt(job_item,party));
		differnceqty=previousqty-maximumqty;
		
		if(differnceqty>0) 
		{
			minimumqty=differnceqty;//640-540=100//max qty=540
		}
		//if(qty >maximumqty) //640 >540
		//{
		//	respon_output=true;//change nhi hoga
		//}
		//else 
		//{
			if(qty <minimumqty) //80<100
			{
				respon_output=true;//change nhi hoga
				res.setCancel_message(""+minimumqty);			}
		//}
		//System.out.println(qty +" " +Double.parseDouble(jw_grn_partywitem_detailsRepository.checkrestwt(job_item,party)));
		if(respon_output) 
		{
			res.setStatus("No");//change nhi hoga
		}
		else
		{
			res.setStatus("Yes");//change hoga
		}
		return res;
		
	}
	
	@Transactional
	public Jw_grn_itemtag delete(Jw_grn_itemtag jw_grn_itemtag,long id) 
	{
		Optional<Jw_grn_itemtag> op = jw_grn_itemtagRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		String mstatus="DELETED";
		Jw_grn_itemtag grntag=op.get();
		
		grntag.setModified_type("DELETED");
		grntag.setInserted_by(op.get().getInserted_by());
		grntag.setInserted_on(op.get().getInserted_on());
		grntag.setUpdated_by(op.get().getUpdated_by());
		grntag.setUpdated_on(op.get().getUpdated_on());
		grntag.setDeleted_by(userRepository.getUserDetails(grntag.getUsername()).getName());
		grntag.setDeleted_on(ldt);
		

		jw_grn_partytag_detailsRepository.updateJwGrnPartytagDetails(op.get().getJw_grn_tag(),mstatus);
		jw_grn_partywitem_detailsRepository.updateJwGrnPartywitemDetails(op.get().getJw_grn_tag(),mstatus);
		
		
		if(op.isPresent())
		{
			grntag.setId(id);
		}
			return jw_grn_itemtagRepository.save(grntag);
	}
	
	@Transactional
	public Jw_grn_itemtag update(Jw_grn_itemtag jw_grn_itemtag,long id)  
	{
		Optional<Jw_grn_itemtag> op =jw_grn_itemtagRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		jw_grn_itemtag.setJw_grn_tag(op.get().getJw_grn_tag());
		jw_grn_itemtag.setCompany_id(jw_grn_itemtag.getCompany_id());
		jw_grn_itemtag.setFin_year(jw_grn_itemtag.getFin_year());
		jw_grn_itemtag.setModified_type("INSERTED");
		jw_grn_itemtag.setInserted_on(op.get().getInserted_on());
		jw_grn_itemtag.setInserted_by(op.get().getInserted_by());
		jw_grn_itemtag.setUpdated_by(userRepository.getUserDetails(jw_grn_itemtag.getUsername()).getName());
		jw_grn_itemtag.setUpdated_on(ldt);
		jw_grn_itemtag.setDeleted_by("NA");
		jw_grn_itemtag.setDeleted_on(ldt);
		
		String mstatus="UPDATED";
		//Update Party
		jw_grn_partytag_detailsRepository.updateJwGrnPartytagDetails(op.get().getJw_grn_tag(),mstatus);
		
		Set<Jw_grn_partytag_details> jw_grn_partytag_details=new HashSet<Jw_grn_partytag_details>();
		jw_grn_partytag_details.addAll(jw_grn_itemtag.getJw_grn_partytag_details());
		for(Jw_grn_partytag_details partydetails:jw_grn_partytag_details) 
		{
			partydetails.setJw_grn_tag(op.get().getJw_grn_tag());
			partydetails.setCompany_id(jw_grn_itemtag.getCompany_id());
			partydetails.setFin_year(jw_grn_itemtag.getFin_year());
			partydetails.setModified_type("INSERTED");
			partydetails.setInserted_by(userRepository.getUserDetails(jw_grn_itemtag.getUsername()).getName());
			partydetails.setInserted_on(ldt);
			partydetails.setUpdated_by(jw_grn_itemtag.getUpdated_by());
			partydetails.setUpdated_on(jw_grn_itemtag.getUpdated_on());
			partydetails.setDeleted_by("NA");
			partydetails.setDeleted_on(ldt);
			partydetails.setUsername(jw_grn_itemtag.getUsername());
			
			//Update Item
			jw_grn_partywitem_detailsRepository.updateJwGrnPartywitemDetails(op.get().getJw_grn_tag(),mstatus);
			
			Set<Jw_grn_partywitem_details> jw_grn_partywitem_details=new HashSet<Jw_grn_partywitem_details>();
			jw_grn_partywitem_details.addAll(partydetails.getJw_grn_partywitem_details());
			for(Jw_grn_partywitem_details itemdetails:jw_grn_partywitem_details) 
			{
				itemdetails.setJw_grn_tag(op.get().getJw_grn_tag());
				itemdetails.setCompany_id(jw_grn_itemtag.getCompany_id());
				itemdetails.setFin_year(jw_grn_itemtag.getFin_year());
				itemdetails.setModified_type("INSERTED");
				itemdetails.setInserted_by(userRepository.getUserDetails(jw_grn_itemtag.getUsername()).getName());
				itemdetails.setInserted_on(ldt);
				itemdetails.setUpdated_by(jw_grn_itemtag.getUpdated_by());
				itemdetails.setUpdated_on(jw_grn_itemtag.getUpdated_on());
				itemdetails.setDeleted_by("NA");
				itemdetails.setDeleted_on(ldt);
				itemdetails.setUsername(jw_grn_itemtag.getUsername());
			}
			partydetails.setJw_grn_partywitem_details(jw_grn_partywitem_details);
		}
		jw_grn_itemtag.setJw_grn_partytag_details(jw_grn_partytag_details);
		
		
		return jw_grn_itemtagRepository.save(jw_grn_itemtag);
	
	}
	
}
