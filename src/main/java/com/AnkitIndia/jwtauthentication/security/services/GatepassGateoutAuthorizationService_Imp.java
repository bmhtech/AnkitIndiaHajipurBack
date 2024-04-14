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
import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization_details;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin_details;
import com.AnkitIndia.jwtauthentication.repository.GatepassGateoutAuthorizationRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;

@Service
@Repository
public class GatepassGateoutAuthorizationService_Imp implements GatepassGateoutAuthorizationService{

	@Autowired
	GatepassGateoutAuthorizationRepository gatepassGateoutAuthorizationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Transactional
	public GatepassGateoutAuthorization save(GatepassGateoutAuthorization gatepassGateoutAuthorization)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(gatepassGateoutAuthorizationRepository.countId() != null ) {
			slno=Long.parseLong(gatepassGateoutAuthorizationRepository.countId());
		}
		String prefix="GPA";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		//System.out.println("username::"+gpChecklist.getUsername());
		
		gatepassGateoutAuthorization.setGp_go_auth_id(gen_sno);
		gatepassGateoutAuthorization.setCompany_id(gatepassGateoutAuthorization.getCompany_id());
		gatepassGateoutAuthorization.setFin_year(gatepassGateoutAuthorization.getFin_year());
		gatepassGateoutAuthorization.setModified_type("INSERTED");
		gatepassGateoutAuthorization.setInserted_on(ldt);
		gatepassGateoutAuthorization.setInserted_by(userRepository.getUserDetails(gatepassGateoutAuthorization.getUsername()).getName());
		gatepassGateoutAuthorization.setUpdated_by(gatepassGateoutAuthorization.getUpdated_by());
		gatepassGateoutAuthorization.setUpdated_on(ldt);
		gatepassGateoutAuthorization.setDeleted_by("NA");
		gatepassGateoutAuthorization.setDeleted_on(ldt);
		gatepassGateoutAuthorization.setGate_outstatus("NO");
		gatepassGateoutAuthorization.setVechile_no(vehicleMasterRepository.getVehicleDetails(gatepassGateoutAuthorization.getVechileid()).getVehicle_no());
		
		gatepassGateoutAuthorizationRepository.updateVehicleLoadunload(gatepassGateoutAuthorization.getVechileid(),gen_sno);
		
		Set<GatepassGateoutAuthorization_details> getAuthDetails=new HashSet<GatepassGateoutAuthorization_details>();
		getAuthDetails.addAll(gatepassGateoutAuthorization.getGatepassGateoutAuthorization_details());
		for(GatepassGateoutAuthorization_details gpAuDetails:getAuthDetails) 
		{
			
			
			gpAuDetails.setGp_go_auth_id(gen_sno);
			
			gpAuDetails.setCompany_id(gatepassGateoutAuthorization.getCompany_id());
			gpAuDetails.setFin_year(gatepassGateoutAuthorization.getFin_year());
			gpAuDetails.setModified_type("INSERTED");
			gpAuDetails.setInserted_by(userRepository.getUserDetails(gatepassGateoutAuthorization.getUsername()).getName());
			gpAuDetails.setInserted_on(ldt);
			gpAuDetails.setUpdated_by("NA");
			gpAuDetails.setUpdated_on(ldt);
			gpAuDetails.setDeleted_by("NA");
			gpAuDetails.setDeleted_on(ldt);
		}
		gatepassGateoutAuthorization.setGatepassGateoutAuthorization_details(getAuthDetails);
		
		return gatepassGateoutAuthorizationRepository.save(gatepassGateoutAuthorization);
	}
	
	
	@Transactional
	public GatepassGateoutAuthorization update(GatepassGateoutAuthorization gatepassGateoutAuthorization,Long id)
	{
		Optional<GatepassGateoutAuthorization> op = gatepassGateoutAuthorizationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		gatepassGateoutAuthorization.setGp_go_auth_id(op.get().getGp_go_auth_id());
		gatepassGateoutAuthorization.setCompany_id(gatepassGateoutAuthorization.getCompany_id());
		gatepassGateoutAuthorization.setFin_year(gatepassGateoutAuthorization.getFin_year());
		gatepassGateoutAuthorization.setModified_type("INSERTED");
		gatepassGateoutAuthorization.setInserted_on(ldt);
		gatepassGateoutAuthorization.setInserted_by(op.get().getInserted_by());
		gatepassGateoutAuthorization.setUpdated_by(userRepository.getUserDetails(gatepassGateoutAuthorization.getUsername()).getName());
		gatepassGateoutAuthorization.setUpdated_on(ldt);
		gatepassGateoutAuthorization.setDeleted_by("NA");
		gatepassGateoutAuthorization.setDeleted_on(ldt);
		gatepassGateoutAuthorization.setGate_outstatus("NO");
		gatepassGateoutAuthorization.setVechile_no(vehicleMasterRepository.getVehicleDetails(op.get().getVechileid()).getVehicle_no());
		
		gatepassGateoutAuthorizationRepository.updatedetails(op.get().getGp_go_auth_id());
		
		Set<GatepassGateoutAuthorization_details> getAuthDetails=new HashSet<GatepassGateoutAuthorization_details>();
		getAuthDetails.addAll(gatepassGateoutAuthorization.getGatepassGateoutAuthorization_details());
		for(GatepassGateoutAuthorization_details gpAuDetails:getAuthDetails) 
		{
			
			
			gpAuDetails.setGp_go_auth_id(op.get().getGp_go_auth_id());
			
			gpAuDetails.setCompany_id(gatepassGateoutAuthorization.getCompany_id());
			gpAuDetails.setFin_year(gatepassGateoutAuthorization.getFin_year());
			gpAuDetails.setModified_type("INSERTED");
			gpAuDetails.setInserted_by(op.get().getInserted_by());
			gpAuDetails.setInserted_on(ldt);
			gpAuDetails.setUpdated_by(userRepository.getUserDetails(gatepassGateoutAuthorization.getUsername()).getName());
			gpAuDetails.setUpdated_on(ldt);
			gpAuDetails.setDeleted_by("NA");
			gpAuDetails.setDeleted_on(ldt);
		}
		gatepassGateoutAuthorization.setGatepassGateoutAuthorization_details(getAuthDetails);
		
		return gatepassGateoutAuthorizationRepository.save(gatepassGateoutAuthorization);
	}
	public GatepassGateoutAuthorization findOne(long id)
	{
		Optional<GatepassGateoutAuthorization> op=gatepassGateoutAuthorizationRepository.findById(id);
		return op.get();
	}
	
	public List<GatepassGateoutAuthorization_details> getGatepassgetoutaretrivedetails(String gp_gi_id)
	{
        List<GatepassGateoutAuthorization_details> gatepassoutadetails= new ArrayList<GatepassGateoutAuthorization_details>();
		
        gatepassoutadetails.addAll(gatepassGateoutAuthorizationRepository.getgatepassauthdetails(gp_gi_id));
		
		return gatepassoutadetails;
		
	}
	
	public List<GatepassGateoutAuthorization> getGatepassgetouta_List()
	{
		List<GatepassGateoutAuthorization> gatepassouta= new ArrayList<GatepassGateoutAuthorization>();
		
		gatepassouta.addAll(gatepassGateoutAuthorizationRepository.getGatepassgetouta_List());
		
		return gatepassouta;
	}
	
}
