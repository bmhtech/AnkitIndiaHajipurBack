package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.Utility.FileUtil;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin_details;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.repository.GatepassGetinRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;

@Service
@Repository
public class GatepassGetinService_Imp implements GatepassGetinService{

	@Autowired
	GatepassGetinRepository gatepassGetinRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	
	@Transactional
	public GatepassGetin save(GatepassGetin gatepassGetin,MultipartFile files) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(gatepassGetinRepository.countId() != null ) {
			slno=Long.parseLong(gatepassGetinRepository.countId());
		}
		
		String prefix="GPI";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		gatepassGetin.setGp_gi_id(gen_sno);
		gatepassGetin.setCompany_id(gatepassGetin.getCompany_id());
		gatepassGetin.setFin_year(gatepassGetin.getFin_year());
		gatepassGetin.setModified_type("INSERTED");
		gatepassGetin.setInserted_on(ldt);
		gatepassGetin.setInserted_by(userRepository.getUserDetails(gatepassGetin.getUsername()).getName());
		gatepassGetin.setUpdated_by(gatepassGetin.getUpdated_by());
		gatepassGetin.setUpdated_on(ldt);
		gatepassGetin.setDeleted_by("NA");
		gatepassGetin.setDeleted_on(ldt);
		gatepassGetin.setVechile_no(vehicleMasterRepository.getVehicleDetails(gatepassGetin.getVechileid()).getVehicle_no());
		
		gatepassGetinRepository.updateVehicleLoadunload(gatepassGetin.getVechileid(),gen_sno);
		
		if(files == null) 
		{
			
		}
		else
		{
			try 
			{
				gatepassGetin.setDoc_pdf(fileUpload(files,gen_sno+"_"));		
			
			}
			catch (IOException e)
			{
				System.out.println(e);
			}
		}
		
		Set<GatepassGetin_details> getInDetails=new HashSet<GatepassGetin_details>();
		getInDetails.addAll(gatepassGetin.getGatepassGetin_details());
		for(GatepassGetin_details gpDetails:getInDetails) 
		{
			
			
			gpDetails.setGp_gi_id(gen_sno);
			
			gpDetails.setCompany_id(gatepassGetin.getCompany_id());
			gpDetails.setFin_year(gatepassGetin.getFin_year());
			gpDetails.setModified_type("INSERTED");
			gpDetails.setInserted_by(gatepassGetin.getInserted_by());
			gpDetails.setInserted_on(ldt);
			gpDetails.setUpdated_by("NA");
			gpDetails.setUpdated_on(ldt);
			gpDetails.setDeleted_by("NA");
			gpDetails.setDeleted_on(ldt);
		}
		gatepassGetin.setGatepassGetin_details(getInDetails);
		
		return gatepassGetinRepository.save(gatepassGetin);
	}
	
	@Transactional
	public GatepassGetin update(GatepassGetin gatepassGetin,MultipartFile files) throws IOException
	{
		Optional<GatepassGetin> op = gatepassGetinRepository.findById(gatepassGetin.getId());
		LocalDateTime ldt = LocalDateTime.now();
		
		
		gatepassGetin.setGp_gi_id(op.get().getGp_gi_id());
		gatepassGetin.setCompany_id(gatepassGetin.getCompany_id());
		gatepassGetin.setFin_year(gatepassGetin.getFin_year());
		gatepassGetin.setModified_type("INSERTED");
		gatepassGetin.setInserted_on(ldt);
		gatepassGetin.setInserted_by(op.get().getInserted_by());
		gatepassGetin.setUpdated_by(userRepository.getUserDetails(gatepassGetin.getUsername()).getName());
		gatepassGetin.setUpdated_on(ldt);
		gatepassGetin.setDeleted_by("NA");
		gatepassGetin.setDeleted_on(ldt);
		gatepassGetin.setVechile_no(op.get().getVechile_no());
		
		//gatepassGetinRepository.updateVehicleLoadunload(gatepassGetin.getVechileid(),op.get().getGp_gi_id());
		
		if(files == null) 
		{
			gatepassGetin.setDoc_pdf(op.get().getDoc_pdf());
		}
		else
		{
			try 
			{
				gatepassGetin.setDoc_pdf(fileUpload(files,op.get().getGp_gi_id()+"_"));		
			
			}
			catch (IOException e)
			{
				System.out.println(e);
			}
		}
		
		gatepassGetinRepository.updategetInDetails(op.get().getGp_gi_id()); 
		
		Set<GatepassGetin_details> getInDetails=new HashSet<GatepassGetin_details>();
		getInDetails.addAll(gatepassGetin.getGatepassGetin_details());
		for(GatepassGetin_details gpDetails:getInDetails) 
		{
			
			
			gpDetails.setGp_gi_id(op.get().getGp_gi_id());
			
			gpDetails.setCompany_id(gatepassGetin.getCompany_id());
			gpDetails.setFin_year(gatepassGetin.getFin_year());
			gpDetails.setModified_type("INSERTED");
			gpDetails.setInserted_by(op.get().getInserted_by());
			gpDetails.setInserted_on(ldt);
			gpDetails.setUpdated_by(userRepository.getUserDetails(gatepassGetin.getUsername()).getName());
			gpDetails.setUpdated_on(ldt);
			gpDetails.setDeleted_by("NA");
			gpDetails.setDeleted_on(ldt);
		}
		gatepassGetin.setGatepassGetin_details(getInDetails);
		
		return gatepassGetinRepository.save(gatepassGetin);
	}
	
	
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPathgetpassgetin);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileUtil.folderPathgetpassgetin+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPathgetpassgetin+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		 
		            return files_name;
	}
	
	public List<GatepassGetin> getGatepassgetin_List()
	{
		List<GatepassGetin> gatepassin= new ArrayList<GatepassGetin>();
		
		gatepassin.addAll(gatepassGetinRepository.getGatepassgetin_List());
		
		return gatepassin;
	}
	
	public GatepassGetin findOne(long id)
	{
		Optional<GatepassGetin> op=gatepassGetinRepository.findById(id);
		return op.get();
	}
	
	public List<GatepassGetin_details> getGatepassgetinretrivedetails(String gp_gi_id)
	{
        List<GatepassGetin_details> gatepassindetails= new ArrayList<GatepassGetin_details>();
		
        gatepassindetails.addAll(gatepassGetinRepository.getGatepassgetinretrivedetails(gp_gi_id));
		
		return gatepassindetails;
		
	}
	
}
