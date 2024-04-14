package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization_details;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout_details;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.GatepassGateoutAuthorizationRepository;
import com.AnkitIndia.jwtauthentication.repository.GatepassGateoutRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;

@Service
@Repository
public class GatepassGateoutService_Imp implements GatepassGateoutService{

	@Autowired
	GatepassGateoutRepository gatepassGateoutRepository;
	

	@Autowired
	GatepassGateoutAuthorizationRepository gatepassGateoutAuthorizationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	public List<GatepassGateoutAuthorization> getVehicleListgatepassout()
	{
GatepassGateoutAuthorization newveh = new GatepassGateoutAuthorization();
		
		newveh.setVechileid("0");
		newveh.setVechile_no("Choose an option");
		
           List<GatepassGateoutAuthorization> modelList=new ArrayList<GatepassGateoutAuthorization>();
           modelList.add(newveh);
		   modelList.addAll(gatepassGateoutRepository.getVehicleListgatepassout());
		
		return modelList;
	}
	public GatepassGateoutAuthorization getVehicleListgatepassauth(String vehicle_id)
	{
		 GatepassGateoutAuthorization auth=gatepassGateoutAuthorizationRepository.getgatepassauthstaticdetails(vehicle_id);
 		  
		return auth;
	}
	
	public List<GatepassGateoutAuthorization_details> getgatepassauthdetails(String authid)
	{
           List<GatepassGateoutAuthorization_details> modelList=new ArrayList<GatepassGateoutAuthorization_details>();
		   modelList.addAll(gatepassGateoutAuthorizationRepository.getgatepassauthdetails(authid));
		
		return modelList;
	}
	
	
	
		
		@Transactional
		public GatepassGateout save(GatepassGateout gatepassGateout)
		{
			LocalDateTime ldt = LocalDateTime.now();
			
			long slno=0;
			if(gatepassGateoutRepository.countId() != null ) {
				slno=Long.parseLong(gatepassGateoutRepository.countId());
			}
			String prefix="GPO";
			String gen_sno=UniqueID.uniqueid(prefix,slno);
			//System.out.println("username::"+gpChecklist.getUsername());
			
			gatepassGateout.setGp_go_id(gen_sno);
			gatepassGateout.setCompany_id(gatepassGateout.getCompany_id());
			gatepassGateout.setFin_year(gatepassGateout.getFin_year());
			gatepassGateout.setModified_type("INSERTED");
			gatepassGateout.setInserted_on(ldt);
			gatepassGateout.setInserted_by(userRepository.getUserDetails(gatepassGateout.getUsername()).getName());
			gatepassGateout.setUpdated_by(gatepassGateout.getUpdated_by());
			gatepassGateout.setUpdated_on(ldt);
			gatepassGateout.setDeleted_by("NA");
			gatepassGateout.setDeleted_on(ldt);
			gatepassGateout.setVechile_no(vehicleMasterRepository.getVehicleDetails(gatepassGateout.getVechileid()).getVehicle_no());
			gatepassGateout.setConfirmed_by_name(employeeMasterRepository.getEmployee(gatepassGateout.getConfirmed_by()).getEmp_name());
			
			gatepassGateoutRepository.updateGatepassAuthset(gatepassGateout.getReference_id());
			
			gatepassGateoutRepository.updateVehicleLoadunload(gatepassGateout.getVechileid(),gen_sno);
			

			
			
			Set<GatepassGateout_details> getOutDetails=new HashSet<GatepassGateout_details>();
			getOutDetails.addAll(gatepassGateout.getGatepassGateout_details());
			for(GatepassGateout_details gpODetails:getOutDetails) 
			{
				
				
				gpODetails.setGp_go_id(gen_sno);
				
				gpODetails.setCompany_id(gatepassGateout.getCompany_id());
				gpODetails.setFin_year(gatepassGateout.getFin_year());
				gpODetails.setModified_type("INSERTED");
				gpODetails.setInserted_by(userRepository.getUserDetails(gatepassGateout.getUsername()).getName());
				gpODetails.setInserted_on(ldt);
				gpODetails.setUpdated_by("NA");
				gpODetails.setUpdated_on(ldt);
				gpODetails.setDeleted_by("NA");
				gpODetails.setDeleted_on(ldt);
			}
			gatepassGateout.setGatepassGateout_details(getOutDetails);;
			
			return gatepassGateoutRepository.save(gatepassGateout);
		}
		
	public List<GatepassGateout> findAll()
	{
		List<GatepassGateout> getoutlist=new ArrayList<GatepassGateout>();
		getoutlist.addAll(gatepassGateoutRepository.findGetoutList());
		
		return getoutlist;
	}
	
	public GatepassGateout findOne(long id)
	{
		Optional<GatepassGateout> op=this.gatepassGateoutRepository.findById(id);
		return op.get();
	}
	
	
	public List<GatepassGateout_details> retriveGatepassGateOutDetails(String gp_go_id)
	{
		List<GatepassGateout_details> modelList= new ArrayList<>();
		modelList.addAll(gatepassGateoutRepository.retriveGatepassGateOutDetails(gp_go_id));
		return modelList;
	}
		
/*	@Transactional
	public GatepassGateout update(GatepassGateout gatepassGateout,long id)
	{
		Optional<GatepassGateout> op =gatepassGateoutRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		gatepassGateoutRepository.updateGateOut(id);
		gatepassGateoutRepository.revertGatepassAuthset(op.get().getReference_id());
		gatepassGateoutRepository.revertVehicleLoadunload(gatepassGateout.getVechileid(),op.get().getGp_go_id());
		
		
		gatepassGateout.setGp_go_id(op.get().getGp_go_id());
		gatepassGateout.setCompany_id(gatepassGateout.getCompany_id());
		gatepassGateout.setFin_year(gatepassGateout.getFin_year());
		gatepassGateout.setModified_type("INSERTED");
		gatepassGateout.setInserted_on(ldt);
		gatepassGateout.setInserted_by(userRepository.getUserDetails(gatepassGateout.getUsername()).getName());
		gatepassGateout.setUpdated_by(gatepassGateout.getUpdated_by());
		gatepassGateout.setUpdated_on(ldt);
		gatepassGateout.setDeleted_by("NA");
		gatepassGateout.setDeleted_on(ldt);
		gatepassGateout.setVechile_no(vehicleMasterRepository.getVehicleDetails(gatepassGateout.getVechileid()).getVehicle_no());
		gatepassGateout.setConfirmed_by_name(employeeMasterRepository.getEmployee(gatepassGateout.getConfirmed_by()).getEmp_name());
		
		gatepassGateoutRepository.updateGatepassAuthset(gatepassGateout.getReference_id());
		
		gatepassGateoutRepository.updateVehicleLoadunload(gatepassGateout.getVechileid(),op.get().getGp_go_id());
		
		Set<GatepassGateout_details> getOutDetails=new HashSet<GatepassGateout_details>();
		getOutDetails.addAll(gatepassGateout.getGatepassGateout_details());
		for(GatepassGateout_details gpODetails:getOutDetails) 
		{
			
			
			gpODetails.setGp_go_id(op.get().getGp_go_id());
			
			gpODetails.setCompany_id(gatepassGateout.getCompany_id());
			gpODetails.setFin_year(gatepassGateout.getFin_year());
			gpODetails.setModified_type("INSERTED");
			gpODetails.setInserted_by(userRepository.getUserDetails(gatepassGateout.getUsername()).getName());
			gpODetails.setInserted_on(ldt);
			gpODetails.setUpdated_by("NA");
			gpODetails.setUpdated_on(ldt);
			gpODetails.setDeleted_by("NA");
			gpODetails.setDeleted_on(ldt);
		}
		gatepassGateout.setGatepassGateout_details(getOutDetails);;
		
		
		if(op.isPresent())
		{
			gatepassGateout.setId(id);
		}
		return gatepassGateoutRepository.save(gatepassGateout);
	}*/
}
