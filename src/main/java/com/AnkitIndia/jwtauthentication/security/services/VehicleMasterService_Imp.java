package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AnkitIndia.Master.Exception.FileStorageException;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDGenerator;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_vehicle_dtls;
import com.AnkitIndia.jwtauthentication.model.Uniqueid;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_master_doc_details;
import com.AnkitIndia.jwtauthentication.payload.Response;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_vehicle_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UniqueidRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleTypeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Vehicle_master_doc_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_wgmntRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleMasterDTO;

@Service
public class VehicleMasterService_Imp implements VehicleMasterService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Trans_bussiness_partner_vehicle_dtlsRepository tbpVehicle_dtlsRepository;
	
	@Autowired
	UniqueidRepository uniqueidRepository;
	
	@Autowired
	Wm_unload_wgmntRepository wm_unload_wgmntRepository;
	
	@Autowired
	VehicleTypeMasterRepository vehicleTypeMasterRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Vehicle_master_doc_detailsRepository vehicle_master_doc_detailsRepository;
	
	@Value("${docPdf.upload-dir}")
	private String docPdf;
	
	@Transactional
	public Response fileUpload(@RequestParam("file") MultipartFile file,String fileName,String path,String folder) throws IOException
	{
		System.out.println("get file: "+file.getSize());
		File filePath = new File(path+"/VehicleDocuments");
		boolean value = filePath.mkdir();
	    if(value) {
	      System.out.println("The new directory is created.");
	      File filePath2 = new File(filePath+"/"+folder);
	      boolean value2 = filePath2.mkdir();
	      if(value2) {
	    	  System.out.println("The new directory is created.");
	      }else {
		      System.out.println("The directory already exists.");
		  }
	      filePath=filePath2;
	    }
	    else {
	      System.out.println("The directory already exists.");
	      File filePath2 = new File(filePath+"/"+folder);
	      boolean value2 = filePath2.mkdir();
	      if(value2) {
	    	  System.out.println("The new directory is created.");
	      }else {
		      System.out.println("The directory already exists.");
		  }
	      filePath=filePath2;
	    }
	    System.err.println("File Path: "+filePath);
    	File convertFile = new File(filePath+"/" + fileName.trim());
    	
		convertFile.createNewFile();
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
		try (FileOutputStream fout = new FileOutputStream(convertFile))
		{
			fout.write(file.getBytes());
		}
		catch (Exception exe)
		{
			 throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", exe);
		}
		return new Response(file.getOriginalFilename(), fileDownloadUri,file.getContentType(), file.getSize());
	}
	
	@Transactional
	public VehicleMaster save(VehicleMaster vehicle,MultipartFile files[]) throws IOException 
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		String prefix="VCM",user="None";
		if(vehicleMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(vehicleMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(Utility.isNullOrEmpty(vehicle.getUsername())) {user=userRepository.getUserDetails(vehicle.getUsername()).getName();}
		
		if(Utility.isNullOrEmpty(vehicle.getVehtype_code())) {
			vehicle.setVehtype_name(vehicleTypeMasterRepository.getVehicleTypeNByCode(vehicle.getVehtype_code()).getVehtype_name());
		}else {vehicle.setVehtype_name("None");}
		
		if(Utility.isNullOrEmpty(vehicle.getTareweight_uom())) {
			vehicle.setTareweight_uomname(customUomMasterRepository.getUomByIGroup(vehicle.getTareweight_uom()).getDescription());
		}else {vehicle.setTareweight_uomname("None");}
		
		vehicle.setWeighment_status(false);
		
	/*	if(vehicle.getTransporter().compareToIgnoreCase("")==0)
		{
			vehicle.setTransporter("NA");
			System.out.println("blank");
		}
		else
		{
			vehicle.setTransporter(vehicle.getTransporter());
			System.out.println("not blank");
		}*/
		vehicle.setVehicle_id(gen_sno);
		vehicle.setModified_type("INSERTED");
		vehicle.setInserted_by(user);
		vehicle.setInserted_on(ldt);
		vehicle.setUpdated_by("NA");
		vehicle.setUpdated_on(ldt);
		vehicle.setDeleted_by("NA");
		vehicle.setDeleted_on(ldt);
		
		if(vehicle.getTransporter().compareToIgnoreCase("")!=0) 
		{
			Trans_bussiness_partner_vehicle_dtls tVehicle_dtls=new Trans_bussiness_partner_vehicle_dtls();
			tVehicle_dtls.setBp_Id(vehicle.getTransporter());
			
			tVehicle_dtls.setBp_code(trans_bussiness_partnerRepository.bpNameById(vehicle.getTransporter()).getBp_code());
			tVehicle_dtls.setVehicle_type(vehicle.getVehtype_code());
			tVehicle_dtls.setVehicle_name(gen_sno);
			tVehicle_dtls.setModified_type("INSERTED");
			tVehicle_dtls.setFin_year("0000-0000");
			tVehicle_dtls.setCompany_id("");
			tVehicle_dtls.setInserted_by(user);
			tVehicle_dtls.setInserted_on(ldt);
			tVehicle_dtls.setUpdated_by("NA");
			tVehicle_dtls.setUpdated_on(ldt);
			tVehicle_dtls.setDeleted_by("NA");
			tVehicle_dtls.setDeleted_on(ldt);
			
			tbpVehicle_dtlsRepository.save(tVehicle_dtls);
		}
		
		
		Set<Vehicle_master_doc_details> vehiSet = new HashSet<Vehicle_master_doc_details>();
		vehiSet.addAll(vehicle.getVehicle_master_doc_details());
		
		int i=0;
		String path=docPdf;
		for(Vehicle_master_doc_details vDetails : vehiSet)
		{
			System.err.println(files.length);
			if(files.length > 0) {
				try {
					//if((i+1) == vDetails.getSlno()) {}
					fileUpload(files[i],vehicle.getVehicle_id().trim()+"_"+(i+1)+".pdf",path,vehicle.getVehicle_no().trim());
				}catch (IOException e) {System.out.println(e);}
				i++;
			}
			
			vDetails.setVehicle_id(gen_sno);
			vDetails.setModified_type("INSERTED");
			vDetails.setCompany_id(vehicle.getCompany_id());
			vDetails.setFin_year(vehicle.getFin_year());
			vDetails.setUsername(vehicle.getUsername());
			vDetails.setInserted_by(user);
			vDetails.setInserted_on(ldt);
			vDetails.setUpdated_by("NA");
			vDetails.setUpdated_on(ldt);
			vDetails.setDeleted_by("NA");
			vDetails.setDeleted_on(ldt);
		}
		vehicle.setVehicle_master_doc_details(vehiSet);
		
		return vehicleMasterRepository.save(vehicle);
	}
	
	
	@Transactional
	public VehicleMaster popupsave(VehicleMaster vehicle)   
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		String prefix="VCM",user="None";
		if(vehicleMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(vehicleMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(Utility.isNullOrEmpty(vehicle.getUsername())) {user=userRepository.getUserDetails(vehicle.getUsername()).getName();}
		
		if(Utility.isNullOrEmpty(vehicle.getVehtype_code())) {
			vehicle.setVehtype_name(vehicleTypeMasterRepository.getVehicleTypeNByCode(vehicle.getVehtype_code()).getVehtype_name());
		}else {vehicle.setVehtype_name("None");}
		
		if(Utility.isNullOrEmpty(vehicle.getTareweight_uom())) {
			vehicle.setTareweight_uomname(customUomMasterRepository.getUomByIGroup(vehicle.getTareweight_uom()).getDescription());
		}else {vehicle.setTareweight_uomname("None");}
		
		vehicle.setWeighment_status(false);
		
		vehicle.setVehicle_id(gen_sno);
		vehicle.setModified_type("INSERTED");
		vehicle.setInserted_by(user);
		vehicle.setInserted_on(ldt);
		vehicle.setUpdated_by("NA");
		vehicle.setUpdated_on(ldt);
		vehicle.setDeleted_by("NA");
		vehicle.setDeleted_on(ldt);
		
		Trans_bussiness_partner_vehicle_dtls tVehicle_dtls=new Trans_bussiness_partner_vehicle_dtls();
		//changes on 27-04-2022 for no vehicle
		if(vehicle.getTransporter() == null ) 
		{
			
		}else 
		
		{
			tVehicle_dtls.setBp_Id(vehicle.getTransporter());
			tVehicle_dtls.setBp_code(trans_bussiness_partnerRepository.bpNameById(vehicle.getTransporter()).getBp_code());
			
		}
		//tVehicle_dtls.setBp_Id(vehicle.getTransporter());
		//tVehicle_dtls.setBp_code(trans_bussiness_partnerRepository.bpNameById(vehicle.getTransporter()).getBp_code());
		tVehicle_dtls.setVehicle_type(vehicle.getVehtype_code());
		tVehicle_dtls.setVehicle_name(gen_sno);
		tVehicle_dtls.setModified_type("INSERTED");
		tVehicle_dtls.setFin_year("0000-0000");
		tVehicle_dtls.setCompany_id("");
		tVehicle_dtls.setInserted_by(user);
		tVehicle_dtls.setInserted_on(ldt);
		tVehicle_dtls.setUpdated_by("NA");
		tVehicle_dtls.setUpdated_on(ldt);
		tVehicle_dtls.setDeleted_by("NA");
		tVehicle_dtls.setDeleted_on(ldt);
		
		tbpVehicle_dtlsRepository.save(tVehicle_dtls);
		
		//Set<Vehicle_master_doc_details> vehiSet = new HashSet<Vehicle_master_doc_details>();
		//vehiSet.addAll(vehicle.getVehicle_master_doc_details());
		
		
		//for(Vehicle_master_doc_details vDetails : vehiSet)
	//	{
			
			
		//	vDetails.setVehicle_id(gen_sno);
		//	vDetails.setModified_type("INSERTED");
		//	vDetails.setCompany_id(vehicle.getCompany_id());
		//	vDetails.setFin_year(vehicle.getFin_year());
		//	vDetails.setUsername(vehicle.getUsername());
		//	vDetails.setInserted_by(user);
		//	vDetails.setInserted_on(ldt);
		//	vDetails.setUpdated_by("NA");
		//	vDetails.setUpdated_on(ldt);
	//		vDetails.setDeleted_by("NA");
		//	vDetails.setDeleted_on(ldt);
		//}
		//vehicle.setVehicle_master_doc_details(vehiSet);
		
		return vehicleMasterRepository.save(vehicle);
	}
	
	
	
	public List<VehicleMaster> findAll()
	{
		List<VehicleMaster> vehicleList=new ArrayList<VehicleMaster>();
		vehicleList.addAll(vehicleMasterRepository.findAll());
				
		List<VehicleMaster> allData = vehicleList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(VehicleMaster::getVehicle_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<VehicleMaster> getVehicleNCList()
	{
		List<VehicleMaster> vehicleList=new ArrayList<VehicleMaster>();
		vehicleList.addAll(vehicleMasterRepository.findAll());
				
		List<VehicleMaster> allData = vehicleList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(VehicleMaster::getVehicle_no))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Vehicle_master_doc_details> getVehicleDocDtls(String vid){
		List<Vehicle_master_doc_details> vehiDtls=new ArrayList<Vehicle_master_doc_details>();
		vehiDtls.addAll(vehicle_master_doc_detailsRepository.findAll());
				
		List<Vehicle_master_doc_details> allData = vehiDtls
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.getVehicle_id().equals(vid))
			  .sorted(Comparator.comparing(Vehicle_master_doc_details::getSlno))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public VehicleMaster findOne(long id)
	{
		Optional<VehicleMaster> op=this.vehicleMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public VehicleMaster update(VehicleMaster vehicle,MultipartFile files[]) throws IOException
	{
		Optional<VehicleMaster> op =vehicleMasterRepository.findById(vehicle.getId());
		LocalDateTime ldt = LocalDateTime.now();
		String user="None";
		if(Utility.isNullOrEmpty(vehicle.getUsername())) {user=userRepository.getUserDetails(vehicle.getUsername()).getName();}
		
		if(Utility.isNullOrEmpty(vehicle.getVehtype_code())) {
			vehicle.setVehtype_name(vehicleTypeMasterRepository.getVehicleTypeNByCode(vehicle.getVehtype_code()).getVehtype_name());
		}else {vehicle.setVehtype_name("None");}
		
		if(Utility.isNullOrEmpty(vehicle.getTareweight_uom())) {
			vehicle.setTareweight_uomname(customUomMasterRepository.getUomByIGroup(vehicle.getTareweight_uom()).getDescription());
		}else {vehicle.setTareweight_uomname("None");}
		
		vehicle.setVehicle_id(op.get().getVehicle_id());
		vehicle.setModified_type("INSERTED");
		vehicle.setInserted_by(op.get().getInserted_by());
		vehicle.setInserted_on(op.get().getInserted_on());
		vehicle.setUpdated_by(user);
		vehicle.setUpdated_on(ldt);
		vehicle.setDeleted_by("NA");
		vehicle.setDeleted_on(ldt);
		
		Trans_bussiness_partner_vehicle_dtls tVehicle_dtls=new Trans_bussiness_partner_vehicle_dtls();
		tVehicle_dtls.setBp_Id(vehicle.getTransporter());
		tVehicle_dtls.setVehicle_type(vehicle.getVehtype_code());
		tVehicle_dtls.setVehicle_name(op.get().getVehicle_id());
		tVehicle_dtls.setModified_type("INSERTED");
		tVehicle_dtls.setInserted_by(user);
		tVehicle_dtls.setInserted_on(ldt);
		tVehicle_dtls.setUpdated_by("NA");
		tVehicle_dtls.setUpdated_on(ldt);
		tVehicle_dtls.setDeleted_by("NA");
		tVehicle_dtls.setDeleted_on(ldt);
		
		tbpVehicle_dtlsRepository.save(tVehicle_dtls);
		
		vehicle_master_doc_detailsRepository.updateVehicle_master_doc_details(op.get().getVehicle_id(),"UPDATED");
		
		Set<Vehicle_master_doc_details> vehiSet = new HashSet<Vehicle_master_doc_details>();
		vehiSet.addAll(vehicle.getVehicle_master_doc_details());
		
		int i=0;int j=0;
		String path=docPdf;
		for(Vehicle_master_doc_details vDetails : vehiSet)
		{
			if(files.length > 0) {
				System.err.println("Inserting files ...");
				try {
					//if((i+1) == vDetails.getSlno()) {}
					fileUpload(files[i],vehicle.getVehicle_id().trim()+"_"+(i+1)+".pdf",path,vehicle.getVehicle_no().trim());
				}catch (IOException e) {System.out.println(e);}
				i++;
			}
			
			vDetails.setVehicle_id(op.get().getVehicle_id());
			vDetails.setModified_type("INSERTED");
			vDetails.setCompany_id(vehicle.getCompany_id());
			vDetails.setFin_year(vehicle.getFin_year());
			vDetails.setUsername(vehicle.getUsername());
			vDetails.setInserted_by(user);
			vDetails.setInserted_on(ldt);
			vDetails.setUpdated_by("NA");
			vDetails.setUpdated_on(ldt);
			vDetails.setDeleted_by("NA");
			vDetails.setDeleted_on(ldt);
		}
		vehicle.setVehicle_master_doc_details(vehiSet);
		
		if(op.isPresent())
		{
			vehicle.setId(vehicle.getId());
		}
		return vehicleMasterRepository.save(vehicle);
	}
	
	@Transactional
	public VehicleMaster deleteVehicleMaster(VehicleMaster vehicle,long id)
	{
		Optional<VehicleMaster> op = vehicleMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		VehicleMaster VehicleMaster=op.get();

		VehicleMaster.setInserted_by(op.get().getInserted_by());
		VehicleMaster.setInserted_on(op.get().getInserted_on());
		VehicleMaster.setUpdated_by(op.get().getUpdated_by());
		VehicleMaster.setUpdated_on(op.get().getUpdated_on());
		VehicleMaster.setDeleted_by(userRepository.getUserDetails(vehicle.getUsername()).getName());
		VehicleMaster.setDeleted_on(ldt);
		
		VehicleMaster.setModified_type("DELETED");
		vehicle_master_doc_detailsRepository.updateVehicle_master_doc_details(op.get().getVehicle_id(),"DELETED");
		
		if(op.isPresent())
		{
			VehicleMaster.setId(id);
		}
		return vehicleMasterRepository.save(VehicleMaster);
	}
	
	public List<VehicleMasterDTO> getVehicleNameCode() {
		
		List<VehicleMaster> vehiList=new ArrayList<VehicleMaster>();
		vehiList.addAll(vehicleMasterRepository.findAll());
				
		List<VehicleMaster> allData = vehiList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(VehicleMaster::getVehicle_no))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<VehicleMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<VehicleMasterDTO> vehileList= new ModelMapper().map(allData,listType);
		
		return vehileList;
	}
	
	public List<VehicleMasterDTO> getVehicleThruWeighment() {
		
		List<VehicleMaster> vehiList=new ArrayList<VehicleMaster>();
		vehiList.addAll(vehicleMasterRepository.findAll());
				
		List<VehicleMaster> allData = vehiList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.isWeighment_status()==false)
			  .sorted(Comparator.comparing(VehicleMaster::getVehicle_no))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<VehicleMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<VehicleMasterDTO> vehileList= new ModelMapper().map(allData,listType);
		
		return vehileList;
	}
	
	public List<Map<String,Object>> getVehicleThruWeighmentfast()
	{
		 List<Map<String,Object>> vehileList=vehicleMasterRepository.findvechilethroughstatus();
		return vehileList;
	}
	
	public VehicleMasterDTO getVehicleDetails(String vid) {
		VehicleMaster modelList=vehicleMasterRepository.getVehicleDetails(vid);
		
		// Create Conversion Type
		Type listType = new TypeToken<VehicleMasterDTO>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		VehicleMasterDTO vehileList = new ModelMapper().map(modelList,listType);
		
		return vehileList;	
	}
	
	public List<VehicleMasterDTO> getVehicleThruTransporter(String tid) {
		
		List<Trans_bussiness_partner_vehicle_dtls> transVehicle=tbpVehicle_dtlsRepository.getVehicleDetails(tid);
		
		List<VehicleMaster> modelList=new ArrayList<VehicleMaster>();
		
		for(int i=0;i<transVehicle.size();i++) {
			modelList.addAll(vehicleMasterRepository.getVehicleList(transVehicle.get(i).getVehicle_name()));
		}
		Type listType = new TypeToken<List<VehicleMasterDTO>>() {}.getType();
		
		List<VehicleMasterDTO> vehileList = new ModelMapper().map(modelList,listType);
		
		return vehileList;	
	}
	
	public List<VehicleMasterDTO> getVehicleThruTransWOWt1(String tid) {
		
		List<Trans_bussiness_partner_vehicle_dtls> transVehicle=tbpVehicle_dtlsRepository.getVehicleDetails(tid);
		
		List<VehicleMaster> modelList=new ArrayList<VehicleMaster>();
		
		for(int i=0;i<transVehicle.size();i++) {
			modelList.addAll(vehicleMasterRepository.getVehicleWithoutWt1(transVehicle.get(i).getVehicle_name(),false));
		}
		Type listType = new TypeToken<List<VehicleMasterDTO>>() {}.getType();
		
		List<VehicleMasterDTO> vehileList = new ModelMapper().map(modelList,listType);
		
		return vehileList;	
	}
	
public List<VehicleMasterDTO> getVehicleThruTransWOWt2(String tid) {
		
		List<Trans_bussiness_partner_vehicle_dtls> transVehicle=tbpVehicle_dtlsRepository.getVehicleDetails(tid);
		
		List<VehicleMaster> modelList=new ArrayList<VehicleMaster>();
		
		for(int i=0;i<transVehicle.size();i++) {
			modelList.addAll(vehicleMasterRepository.getVehicleWithoutWt2(transVehicle.get(i).getVehicle_name()));
		}
		Type listType = new TypeToken<List<VehicleMasterDTO>>() {}.getType();
		
		List<VehicleMasterDTO> vehileList = new ModelMapper().map(modelList,listType);
		
		return vehileList;	
	}

	public List<VehicleMasterDTO> getVehicleNumberByVtype(String vtype){
		List<VehicleMaster> vehiList=new ArrayList<VehicleMaster>();
		vehiList.addAll(vehicleMasterRepository.findAll());
				
		List<VehicleMaster> allData = vehiList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.getVehtype_code().equals(vtype))
			  .sorted(Comparator.comparing(VehicleMaster::getVehicle_id))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<VehicleMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<VehicleMasterDTO> vehileList= new ModelMapper().map(allData,listType);
		
		return vehileList;
	}
	
	public List<VehicleMasterDTO> getVehicleTypeName(String vcode)
	{
		List<VehicleMaster> vehiList=new ArrayList<VehicleMaster>();
		
		vehiList.addAll(vehicleMasterRepository.getVehicleList(vcode));
		/*vehiList.addAll(vehicleMasterRepository.findAll());
				
		List<VehicleMaster> allData = vehiList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.getVehicle_id().equals(vcode))
			  .sorted(Comparator.comparing(VehicleMaster::getVehicle_id))
			  .collect(Collectors.toList());
		*/
		// Create Conversion Type
		Type listType = new TypeToken<List<VehicleMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<VehicleMasterDTO> vehileList= new ModelMapper().map(vehiList,listType);
		
		return vehileList;
	}
	
	public SequenceIdDTO getSequenceId(String prefix)
	{
		Long slno=0L;
		if(uniqueidRepository.getSequenceId() != null ) {
			slno=Long.parseLong(uniqueidRepository.getSequenceId());
		}
		Long k=(long) String.valueOf(slno).length();
		UniqueIDGenerator u = new UniqueIDGenerator(); 
		String x=u.getUniqueID();
		
		Uniqueid uniqueid=new Uniqueid();
		uniqueid.setSid((slno+1));
		uniqueid.setUid(x +(k+1));
		uniqueidRepository.save(uniqueid);
		
		String gen_sno=UniqueIDTransaction.uniqueid(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SequenceIdDTO getVehicleSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=vehicleMasterRepository.getVehicleSequenceId(fprefix,company);
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
	
	public List<VehicleMaster> findVehicle(String company,String searchtext)
	{
		List<VehicleMaster> vehiList=new ArrayList<VehicleMaster>();
		vehiList.addAll(vehicleMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<VehicleMaster> allData = vehiList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(VehicleMaster::getVehicle_no))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<VehicleMaster> allData = vehiList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company) && (c.getVehicle_no()+c.getVehicle_chassisno()+c.getVehicle_aliasno()+c.getVehtype_name()+c.getOnwer_name()+c.getOnwer_address()+c.getTransporter()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(VehicleMaster::getVehicle_no))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	/*public StatusDTO checkVehicleMasterUsage(String code)
 	{
 		String result=vehicleMasterRepository.checkVehicleMasterUsage(code);
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
 	}*/
	
	public MessageStatusDTO chkVehicleNoStatus(String sname){
		String result=vehicleMasterRepository.chkVehicleNoStatus(sname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	
	public List<Map<String,Object>> allVechileList()
	{
		 List<Map<String,Object>> vehileList=vehicleMasterRepository.allVechileList();
		return vehileList;
	}
	
	/*@Configuration
	public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer
	            .defaultContentType(MediaType.APPLICATION_JSON)
	            .favorPathExtension(true);
	}
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	    resolver.setContentNegotiationManager(manager);

	    // Define all possible view resolvers
	    List<ViewResolver> resolvers = new ArrayList<>();

	    resolvers.add(csvViewResolver());
	    resolvers.add(excelViewResolver());
	    resolvers.add(pdfViewResolver());

	    resolver.setViewResolvers(resolvers);
	    return resolver;
	}
	
	@Bean
	public ViewResolver excelViewResolver() {
	    return new ExcelViewResolver();
	}
	@Bean
	public ViewResolver csvViewResolver() {
	    return new CsvViewResolver();
	}
	@Bean
	public ViewResolver pdfViewResolver() {
	    return new PdfViewResolver();
	}
  }*/
}
