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
import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin_details;
import com.AnkitIndia.jwtauthentication.model.Visitor_master;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Visitor_masterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
@Repository
public class Visitor_masterService_Imp implements Visitor_masterService{

	@Autowired
	Visitor_masterRepository visitor_masterRepository;

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Visitor_master save(Visitor_master visitor_master,MultipartFile files) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(visitor_masterRepository.countId() != null ) {
			slno=Long.parseLong(visitor_masterRepository.countId());
		}
		
		String prefix="VIM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		visitor_master.setVisitor_id(gen_sno);
		visitor_master.setCompany_id(visitor_master.getCompany_id());
		visitor_master.setFin_year(visitor_master.getFin_year());
		visitor_master.setModified_type("INSERTED");
		visitor_master.setInserted_on(ldt);
		visitor_master.setUpdated_by("NA");
		visitor_master.setUpdated_by(visitor_master.getUpdated_by());
		visitor_master.setUpdated_on(ldt);
		visitor_master.setDeleted_by("NA");
		visitor_master.setDeleted_on(ldt);
		
		if(files == null) 
		{
			
		}
		else
		{
			try 
			{
				visitor_master.setDoc_img(fileUpload(files,gen_sno+"_"));		
			
			}
			catch (IOException e)
			{
				System.out.println(e);
			}
		}
		
		return visitor_masterRepository.save(visitor_master);
	}
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileUtil.folderPathvisitormaster+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPathvisitormaster+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		 
		            return files_name;
	}
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPathvisitormaster);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	
	public List<Visitor_master> findAll()
	{
		List<Visitor_master> checklist=new ArrayList<Visitor_master>();
		checklist.addAll(visitor_masterRepository.findcheckList());
		return checklist;
	}
	
	public Visitor_master findOne(long id)
	{
		Optional<Visitor_master> op=visitor_masterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Visitor_master update(Visitor_master visitor_master,MultipartFile files) throws IOException
	{
		Optional<Visitor_master> op = visitor_masterRepository.findById(visitor_master.getId());
		LocalDateTime ldt = LocalDateTime.now();
		
		
		visitor_master.setVisitor_id(op.get().getVisitor_id());
		visitor_master.setCompany_id(visitor_master.getCompany_id());
		visitor_master.setFin_year(visitor_master.getFin_year());
		visitor_master.setModified_type("INSERTED");
		visitor_master.setInserted_on(ldt);
		visitor_master.setInserted_by(op.get().getInserted_by());
		visitor_master.setUpdated_by("NA");
		visitor_master.setUpdated_on(ldt);
		visitor_master.setDeleted_by("NA");
		visitor_master.setDeleted_on(ldt);
		
		if(files == null) 
		{
			visitor_master.setDoc_img(op.get().getDoc_img());
		}
		else
		{
			try 
			{
				visitor_master.setDoc_img(fileUpload(files,op.get().getVisitor_id()+"_"));		
			
			}
			catch (IOException e)
			{
				System.out.println(e);
			}
		}
		
		return visitor_masterRepository.save(visitor_master);
	}
	
	public StatusDTO checkVisitorMasterDeletion(String visitor_id)
	{
		StatusDTO status=new StatusDTO();
		
			if(visitor_masterRepository.checkVisitors(visitor_id))
			{
				status.setStatus("Yes");
			}
			else
			{
				status.setStatus("No");
			}
		return status;
	}
	  
	@Transactional
	public Visitor_master deleteVisitorMaster(Visitor_master visitor_master,long id)
	{
		Optional<Visitor_master> op = visitor_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Visitor_master visitor=op.get();
		
		visitor.setInserted_by(op.get().getInserted_by());
		visitor.setInserted_on(op.get().getInserted_on());
		visitor.setUpdated_by(op.get().getUpdated_by());
		visitor.setUpdated_on(op.get().getUpdated_on());
		visitor.setDeleted_by("NA");
		visitor.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			visitor.setId(id);
		}
		visitor.setModified_type("DELETED");
		return visitor_masterRepository.save(visitor);
		
	}
	
}
