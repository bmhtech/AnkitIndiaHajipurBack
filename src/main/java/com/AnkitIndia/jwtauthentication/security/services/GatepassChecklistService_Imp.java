package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;
import com.AnkitIndia.jwtauthentication.repository.GatepassChecklistRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
@Repository
public class GatepassChecklistService_Imp implements GatepassChecklistService{

	@Autowired
	GatepassChecklistRepository gatepassChecklistRepository;

	@Autowired
	UserRepository userRepository;
	
	
	@Transactional
	public GatepassChecklist save(GatepassChecklist gpChecklist)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(gatepassChecklistRepository.countId() != null ) {
			slno=Long.parseLong(gatepassChecklistRepository.countId());
		}
		String prefix="GPC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		//System.out.println("username::"+gpChecklist.getUsername());
		
		gpChecklist.setChecklist_code(gen_sno);
		gpChecklist.setCompany_id(gpChecklist.getCompany_id());
		gpChecklist.setFin_year(gpChecklist.getFin_year());
		gpChecklist.setModified_type("INSERTED");
		gpChecklist.setInserted_on(ldt);
		gpChecklist.setInserted_by(userRepository.getUserDetails(gpChecklist.getUsername()).getName());
		gpChecklist.setUpdated_by(gpChecklist.getUpdated_by());
		gpChecklist.setUpdated_on(ldt);
		gpChecklist.setDeleted_by("NA");
		gpChecklist.setDeleted_on(ldt);
		
		return gatepassChecklistRepository.save(gpChecklist);
	}
	
	public List<GatepassChecklist> getGatepasschecklistin()
	{
		List<GatepassChecklist> checklist=new ArrayList<GatepassChecklist>();
		checklist.addAll(gatepassChecklistRepository.getGatepasschecklistin());
		return checklist;
	}
	
	public List<GatepassChecklist> getGatepasschecklistout()
	{
		List<GatepassChecklist> checklist=new ArrayList<GatepassChecklist>();
		checklist.addAll(gatepassChecklistRepository.getGatepasschecklistout());
		return checklist;
	}
	
	public List<GatepassChecklist> findAll()
	{
		List<GatepassChecklist> checklist=new ArrayList<GatepassChecklist>();
		checklist.addAll(gatepassChecklistRepository.findcheckList());
		return checklist;
	}
	
	public GatepassChecklist findOne(long id)
	{
		Optional<GatepassChecklist> op=this.gatepassChecklistRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public GatepassChecklist update(GatepassChecklist gatePassChecklist,long id)
	{
		Optional<GatepassChecklist> op =gatepassChecklistRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		gatepassChecklistRepository.updateCheckList(op.get().getChecklist_code());
		
		gatePassChecklist.setChecklist_code(op.get().getChecklist_code());
		gatePassChecklist.setCompany_id(gatePassChecklist.getCompany_id());
		gatePassChecklist.setFin_year(gatePassChecklist.getFin_year());
		gatePassChecklist.setModified_type("INSERTED");
		gatePassChecklist.setInserted_on(ldt);
		gatePassChecklist.setInserted_by(userRepository.getUserDetails(gatePassChecklist.getUsername()).getName());
		gatePassChecklist.setUpdated_by(gatePassChecklist.getUpdated_by());
		gatePassChecklist.setUpdated_on(ldt);
		gatePassChecklist.setDeleted_by("NA");
		gatePassChecklist.setDeleted_on(ldt);
		
		return gatepassChecklistRepository.save(gatePassChecklist);
	}
	
	 public StatusDTO checkGatePassCheckListUsage(String code)
	 	{
			StatusDTO status_del =new StatusDTO();
			boolean checkListIn=false;
			boolean checkListAuth=false;
			
	 		if(gatepassChecklistRepository.checkGatePassCheckListInUsage(code))
			{
	 			checkListIn=true;
			}
			
			if(gatepassChecklistRepository.checkGatePassCheckListAuthUsage(code))
			{
				checkListAuth=true;
			}
			
			if(checkListIn == true || checkListAuth == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
			
			return status_del;
	 	}
	 
	 @Transactional
		public GatepassChecklist deleteGatePassCheckList(GatepassChecklist gpassChecklist,long id)
		{
			Optional<GatepassChecklist> op = gatepassChecklistRepository.findById(id);
			LocalDateTime ldt = LocalDateTime.now();
			GatepassChecklist gatepassChecklist=op.get();
			
			gatepassChecklist.setInserted_by(op.get().getInserted_by());
			gatepassChecklist.setInserted_on(op.get().getInserted_on());
			gatepassChecklist.setUpdated_by(op.get().getUpdated_by());
			gatepassChecklist.setUpdated_on(op.get().getUpdated_on());
			gatepassChecklist.setDeleted_by(userRepository.getUserDetails(gpassChecklist.getUsername()).getName());
			gatepassChecklist.setDeleted_on(ldt);
			
			if(op.isPresent())
			{
				gatepassChecklist.setId(id);
			}
			gatepassChecklist.setModified_type("DELETED");
			return gatepassChecklistRepository.save(gatepassChecklist);
			
		}
	
}
