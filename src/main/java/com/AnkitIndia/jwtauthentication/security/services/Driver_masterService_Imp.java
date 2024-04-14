package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Driver_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_master;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.repository.Driver_masterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Driver_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.EmployeeMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.Master.Exception.*;
import com.AnkitIndia.Utility.FileDriverUtil;
import com.AnkitIndia.jwtauthentication.payload.Response;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Driver_masterService_Imp implements Driver_masterService {
	
	@Autowired
	Driver_masterRepository dmasterRepository;
	
	/*@Transactional
	public Driver_master save(MultipartFile[] files,Driver_master dMaster)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(dmasterRepository.countId() != null ) {
			slno=Long.parseLong(dmasterRepository.countId());
		}
		String prefix="DV";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		dMaster.setIdentity(gen_sno+"_"+dMaster.getPhone_no());
		
		dMaster.setDriver_id(gen_sno);
		dMaster.setFin_year("2019-2020");
		dMaster.setModified_type("INSERTED");
		dMaster.setInserted_by("xxxx");
		dMaster.setInserted_on(ldt);
		dMaster.setUpdated_by("NA");
		dMaster.setUpdated_on(ldt);
		dMaster.setDeleted_by("NA");
		dMaster.setDeleted_on(ldt);
		
		try {
			fileUpload(files[0],dMaster.getIdentity()+".jpg");	
		}
		catch (IOException e) {}
		
		return dmasterRepository.save(dMaster);
	}*/
	
	
	@Transactional
	public Driver_master save(Driver_master dMaster)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(dmasterRepository.countId() != null ) {
			slno=Long.parseLong(dmasterRepository.countId());
		}
		String prefix="DV";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		dMaster.setIdentity("NO");
		
		dMaster.setDriver_id(gen_sno);
		dMaster.setFin_year("2019-2020");
		dMaster.setModified_type("INSERTED");
		dMaster.setInserted_by("xxxx");
		dMaster.setInserted_on(ldt);
		dMaster.setUpdated_by("NA");
		dMaster.setUpdated_on(ldt);
		dMaster.setDeleted_by("NA");
		dMaster.setDeleted_on(ldt);
		
		return dmasterRepository.save(dMaster);
	}
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileDriverUtil.folderPath);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	
	@Transactional
	public  String fileuploadimg(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileDriverUtil.folderPath+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileDriverUtil.folderPath+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		      

		            return files_name;
	
	
	}
	
	
	@Transactional	
	public Driver_master savepopup(MultipartFile files[],Driver_master dMaster) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(dmasterRepository.countId() != null ) {
			slno=Long.parseLong(dmasterRepository.countId());
		}
		String prefix="DV";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		System.out.println(" hello "+files.length);
		if(files.length>0) 
		{
			dMaster.setDoc_img(fileuploadimg(files[0],gen_sno+"_"));
		}
		
		dMaster.setIdentity("NO");
		
		dMaster.setDriver_id(gen_sno);
		dMaster.setFin_year("2019-2020");
		dMaster.setModified_type("INSERTED");
		dMaster.setInserted_by("xxxx");
		dMaster.setInserted_on(ldt);
		dMaster.setUpdated_by("NA");
		dMaster.setUpdated_on(ldt);
		dMaster.setDeleted_by("NA");
		dMaster.setDeleted_on(ldt);
		
		return dmasterRepository.save(dMaster);
	}
	
	
	@Transactional	
	public Driver_master update(MultipartFile files[],Driver_master dMaster) throws IOException
	{
		
		Optional<Driver_master> op = dmasterRepository.findById(dMaster.getId());
		LocalDateTime ldt = LocalDateTime.now();
		
		
		
		System.out.println(" hello "+files.length);
		if(files.length>0) 
		{
			dMaster.setDoc_img(fileuploadimg(files[0],op.get().getDriver_id()+"_"));
		}
		
		dMaster.setIdentity("NO");
		
		dMaster.setDriver_id(op.get().getDriver_id());
		dMaster.setFin_year("2019-2020");
		dMaster.setModified_type("INSERTED");
		dMaster.setInserted_by("xxxx");
		dMaster.setInserted_on(ldt);
		dMaster.setUpdated_by("NA");
		dMaster.setUpdated_on(ldt);
		dMaster.setDeleted_by("NA");
		dMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			dMaster.setId(dMaster.getId());
		}
		return dmasterRepository.save(dMaster);
	}
	
	@Transactional
	public Driver_master deleteDriver(Driver_master dMaster,long id)
	{
		Optional<Driver_master> op = dmasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Driver_master drMaster=op.get();
		
		drMaster.setModified_type("DELETED");
		drMaster.setInserted_by(op.get().getInserted_by());
		drMaster.setInserted_on(op.get().getInserted_on());
		drMaster.setUpdated_by(op.get().getUpdated_by());
		drMaster.setUpdated_on(op.get().getUpdated_on());
		drMaster.setDeleted_by("xxxx");
		drMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			drMaster.setId(id);
		}
		return dmasterRepository.save(drMaster);
		
	}
	
	

	public Driver_master findOne(long id)
	{
		Optional<Driver_master> op=this.dmasterRepository.findById(id);
		return op.get();
	}
	
	
	
	@Transactional
	public Response fileUpload(@RequestParam("file") MultipartFile file,String fileName) throws IOException
	{
    	//File convertFile = new File("E:/work/" + fileName);
    	File convertFile = new File("/usr/Images/AnkitIndiaImages/" + fileName);
    	
		convertFile.createNewFile();
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            //.path( file.getOriginalFilename())
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
		//return "File has uploaded successfully";
		return new Response(file.getOriginalFilename(), fileDownloadUri,
                file.getContentType(), file.getSize());
	}
	
	public List<Driver_master> findAll()
	{
		return dmasterRepository.findAll();
	}
	
	public List<Driver_masterDTO> getDriverList() {
		
		List<Driver_master> modelList=dmasterRepository.getDriverList();
		
		Type listType = new TypeToken<List<Driver_masterDTO>>() {}.getType();
		
		List<Driver_masterDTO> driverList = new ModelMapper().map(modelList,listType);
		
		return driverList;
	}
	
	public List<Map<String,Object>> getDriverListnew() 
	{
		return dmasterRepository.getDriverListnew();
	}
	
	public List<Driver_masterDTO> getDriverByVehicle(String vid) {
		
		List<Driver_master> modelList=dmasterRepository.getDriverByVehicle(vid);
		
		Type listType = new TypeToken<List<Driver_masterDTO>>() {}.getType();
		
		List<Driver_masterDTO> driverVehi = new ModelMapper().map(modelList,listType);
		
		return driverVehi;
	}
	
	
	public Driver_masterDTO getDriverDetails(String did) {
		
		Driver_master modelList=dmasterRepository.getDriverDetails(did);
		
		Type listType = new TypeToken<Driver_masterDTO>() {}.getType();
		
		Driver_masterDTO driverDtls = new ModelMapper().map(modelList,listType);
		
		return driverDtls;
	}
	
	public List<Driver_masterDTO> chkDriverStatus(String doc) {
		
		List<Driver_master> modelList=dmasterRepository.chkDriverStatus(doc);
		
		Type listType = new TypeToken<List<Driver_masterDTO>>() {}.getType();
		
		/*if(modelList == null) {
			Driver_masterDTO def=new Driver_masterDTO("0","0",0L,"0","0","0","0","0");
			
			Driver_masterDTO dstatus = new ModelMapper().map(def,listType);
			return dstatus;
		}
		else {
			Driver_masterDTO dstatus = new ModelMapper().map(modelList,listType);
			return dstatus;
		}*/
		List<Driver_masterDTO> dstatus = new ModelMapper().map(modelList,listType);
		return dstatus;
	}

}
