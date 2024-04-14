package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.Utility.FileUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Bom_input_details;
import com.AnkitIndia.jwtauthentication.model.Item_type_master;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Process_master_shift_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shift_details;

import com.AnkitIndia.jwtauthentication.repository.Bom_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_master_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_master_shift_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TimeCalculationDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_input_detailsDTO;

import java.io.File;
import java.io.FileOutputStream;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.AnkitIndia.jwtauthentication.responseDTO.Invoice_typeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_ListDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_shiftDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_shift_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_shift_infoDTO;

@Service
public class Process_masterService_Imp implements Process_masterService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	Process_master_docRepository process_master_docRepository;
	
	@Autowired
	Process_master_shift_detailsRepository process_master_shift_detailsRepository;
	
	@Autowired
	Bom_masterRepository bom_masterRepository;
	
	public SequenceIdDTO getPMSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=process_masterRepository.getPMSequenceId(fprefix,company);
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
	
	public TimeCalculationDTO getShiftTime(String sstime,int shifthrs){
		
		System.err.println("Got val: "+sstime +" , "+shifthrs);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(sstime, formatter);
		
		//Plus some hours, minutes, and seconds to the original DateTime.
		System.err.println("Plus 720 hour is      = " + dateTime.plusHours(shifthrs));
		
		Type listType = new TypeToken<TimeCalculationDTO>() {}.getType();
		
		TimeCalculationDTO st = new ModelMapper().map(dateTime.plusHours(shifthrs),listType);
		
		st.setShift_start_time(dateTime);
		st.setShift_end_time(dateTime.plusHours(shifthrs));
		
		return st;
	}
	
	public List<Process_master_shift_infoDTO> getProcessShiftThruDate(String pid,String sdate)
	{
		String datetime="",newdatetime="";int shifthrs=0;
		List<Process_master_shift_details> modelList=new ArrayList<Process_master_shift_details>();
		modelList.addAll(process_master_shift_detailsRepository.getProcess_master_shift_details(pid));
		
		Process_master processDtls=process_masterRepository.getProcessDetails(pid);
		
		datetime=processDtls.getShift_start_time();
		shifthrs=Integer.parseInt(processDtls.getTot_shift_hrs());
		newdatetime=Utility.convertDateYYYYMMDD(sdate)+datetime.substring(10,(datetime.length()));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(newdatetime, formatter);
		
		Type listType=new TypeToken<List<Process_master_shift_infoDTO>>() {}.getType();
		List<Process_master_shift_infoDTO> shiftDtls=new ModelMapper().map(modelList,listType);
		
		int k=1;
		for(int j=0;j<shiftDtls.size();j++) 
		{ 
			String stdatetime=formatter.format(dateTime),enddatetime=formatter.format(dateTime.plusHours(shifthrs));
			//System.err.println("Date: "+Utility.convertDateDDMMYYYY(stdatetime.substring(0,stdatetime.length()-6))+" Time: "+stdatetime.substring(stdatetime.length()-5,stdatetime.length()));
			shiftDtls.get(j).setShiftinfo(String.valueOf(k)+"-"+Utility.convertDateDDMMYYYY(stdatetime.substring(0,stdatetime.length()-6))+" "+stdatetime.substring(stdatetime.length()-5,stdatetime.length())+"-"+Utility.convertDateDDMMYYYY(enddatetime.substring(0,enddatetime.length()-6))+" "+enddatetime.substring(enddatetime.length()-5,enddatetime.length()));
			
			dateTime=dateTime.plusHours(shifthrs);
			k++;
		}
		
		return shiftDtls;
	}
	
	/*@Transactional
	public Process_master save(Process_master pMaster)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(process_masterRepository.countId() != null ) {
			slno=Long.parseLong(process_masterRepository.countId());
		}
		String prefix="PM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		Start checking transaction no for unique 
		
		End checking transaction no for unique 
		
		if(pMaster.getBusiness_unit().compareTo("0") !=0 && pMaster.getBusiness_unit().compareTo("") !=0 && pMaster.getBusiness_unit() !=null) {
			pMaster.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pMaster.getBusiness_unit()).getBusinessunit_name());
		}else {pMaster.setBusiness_unitname("None");}
		
		if(pMaster.getShop_floor().compareTo("0") !=0 && pMaster.getShop_floor().compareTo("") !=0 && pMaster.getShop_floor() !=null) {
			pMaster.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(pMaster.getShop_floor()).getShop_floor_name());
		}else {pMaster.setShop_floorname("None");}
		
		pMaster.setProcess_id(gen_sno);
		pMaster.setModified_type("INSERTED");
		pMaster.setInserted_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setInserted_on(ldt);
		pMaster.setUpdated_by("NA");
		pMaster.setUpdated_on(ldt);
		pMaster.setDeleted_by("NA");
		pMaster.setDeleted_on(ldt);
		
		//For Document Details start ........................................
		Set<Process_master_doc> pDoc=new HashSet<Process_master_doc>();
		pDoc.addAll(pMaster.getProcess_master_doc());
		
		for(Process_master_doc pDocs:pDoc) 
		{
			pDocs.setProcess_no(pMaster.getProcess_no());
			pDocs.setProcess_id(gen_sno);
			pDocs.setCompany_id(pMaster.getCompany_id());
			pDocs.setFin_year(pMaster.getFin_year());
			pDocs.setModified_type("INSERTED");
			pDocs.setInserted_by(pMaster.getInserted_by());
			pDocs.setInserted_on(pMaster.getInserted_on());
			pDocs.setUpdated_by(pMaster.getUpdated_by());
			pDocs.setUpdated_on(pMaster.getUpdated_on());
			pDocs.setDeleted_by("NA");
			pDocs.setDeleted_on(ldt);
		}
		pMaster.setProcess_master_doc(pDoc);
		//For Document Details end ........................................
		
		if(pMaster.getProcess_type().compareTo("Special")!=0 && pMaster.getProcess_mntnce().compareTo("Yes")==0)
		{
			//For Shift Details start .........................................
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(pMaster.getShift_start_time(), formatter);
			
			Set<Process_master_shift_details> pShift_details=new HashSet<Process_master_shift_details>();
			
			for (int j=1;j<=Integer.parseInt(pMaster.getShift_no());j++) 
			{ 
				Process_master_shift_details shfts=new Process_master_shift_details(pMaster.getProcess_id(), pMaster.getProcess_no(), j, formatter.format(dateTime), formatter.format(dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()))), pMaster.getTot_shift_hrs(), pMaster.getCompany_id(), pMaster.getFin_year(), "INSERTED", pMaster.getInserted_on(), pMaster.getInserted_by(), pMaster.getUpdated_on(), pMaster.getUpdated_by(), pMaster.getDeleted_on(), pMaster.getDeleted_by(), pMaster);
				pShift_details.add(shfts);
				
				dateTime=dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()));
			}
			
			int x=0;
			pShift_details.addAll(pMaster.getProcess_master_shift_details());
			for(Process_master_shift_details pShifts:pShift_details) 
			{
				x++;
				pShifts.setProcess_id(gen_sno);
				pShifts.setProcess_no(pMaster.getProcess_no());
				
				pShifts.setShiftno(x);
				pShifts.setShift_start_time(formatter.format(dateTime));
				pShifts.setShift_end_time(formatter.format(dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()))));
				pShifts.setTot_shift_hrs(pMaster.getTot_shift_hrs());
				
				pShifts.setCompany_id(pMaster.getCompany_id());
				pShifts.setFin_year(pMaster.getFin_year());
				pShifts.setModified_type("INSERTED");
				pShifts.setInserted_by(pMaster.getInserted_by());
				pShifts.setInserted_on(pMaster.getInserted_on());
				pShifts.setUpdated_by(pMaster.getUpdated_by());
				pShifts.setUpdated_on(pMaster.getUpdated_on());
				pShifts.setDeleted_by("NA");
				pShifts.setDeleted_on(ldt);
				
				dateTime=dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()));
			}
			
			pMaster.setProcess_master_shift_details(pShift_details);
			//For Shift Details end ........................................
		}
		
		return process_masterRepository.save(pMaster);
	}*/
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPath);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileUtil.folderPath+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPath+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		      

		            return files_name;
	
	
	}
	
@Transactional
	
	public  Process_master save(Process_master pMaster,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(process_masterRepository.countId() != null ) {
			slno=Long.parseLong(process_masterRepository.countId());
		}
		String prefix="PM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		
		/*End checking transaction no for unique */
		System.out.println("1 :: ");
		if(pMaster.getBusiness_unit().compareTo("0") !=0 && pMaster.getBusiness_unit().compareTo("") !=0 && pMaster.getBusiness_unit() !=null) {
			pMaster.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pMaster.getBusiness_unit()).getBusinessunit_name());
		}else {pMaster.setBusiness_unitname("None");}
		
		if(pMaster.getShop_floor().compareTo("0") !=0 && pMaster.getShop_floor().compareTo("") !=0 && pMaster.getShop_floor() !=null) {
			pMaster.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(pMaster.getShop_floor()).getShop_floor_name());
		}else {pMaster.setShop_floorname("None");}
		
		System.out.println(pMaster.getItem_group());
		pMaster.setProcess_id(gen_sno);
		pMaster.setModified_type("INSERTED");
		pMaster.setInserted_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setInserted_on(ldt);
		pMaster.setUpdated_by("NA");
		pMaster.setUpdated_on(ldt);
		pMaster.setDeleted_by("NA");
		pMaster.setDeleted_on(ldt);
		System.out.println("2:: ");
		//For Document Details start ........................................
		Set<Process_master_doc> pDoc=new HashSet<Process_master_doc>();
		pDoc.addAll(pMaster.getProcess_master_doc());
		int i=0;
		
		for(Process_master_doc pDocs:pDoc) 
		{
			System.out.println(" hello files : "+files.length);
			//here start
			
			if(files.length > 0) {
				try {
					System.out.println("files[i] :: "+i+" / "+files[i]);
						//fileUpload(files[i],gen_sno+"_");
					
						pDocs.setDoc_pdf(fileUpload(files[i],gen_sno+"_"));
						
					
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
					}
				
			}
			System.out.println("3 :: ");
			//here ends
			
			pDocs.setProcess_no(pMaster.getProcess_no());
			pDocs.setProcess_id(gen_sno);
			pDocs.setCompany_id(pMaster.getCompany_id());
			pDocs.setFin_year(pMaster.getFin_year());
			pDocs.setModified_type("INSERTED");
			pDocs.setInserted_by(pMaster.getInserted_by());
			pDocs.setInserted_on(pMaster.getInserted_on());
			pDocs.setUpdated_by(pMaster.getUpdated_by());
			pDocs.setUpdated_on(pMaster.getUpdated_on());
			pDocs.setDeleted_by("NA");
			pDocs.setDeleted_on(ldt);
		}
		pMaster.setProcess_master_doc(pDoc);
		
		
		//For Document Details end ........................................
		
		if(pMaster.getProcess_type().compareTo("Special")!=0 && pMaster.getProcess_mntnce().compareTo("Yes")==0)
		{
			//For Shift Details start .........................................
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(pMaster.getShift_start_time(), formatter);
			
			Set<Process_master_shift_details> pShift_details=new HashSet<Process_master_shift_details>();
			
			for (int j=1;j<=Integer.parseInt(pMaster.getShift_no());j++) 
			{ 
				Process_master_shift_details shfts=new Process_master_shift_details(pMaster.getProcess_id(), pMaster.getProcess_no(), j, formatter.format(dateTime), formatter.format(dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()))), pMaster.getTot_shift_hrs(), pMaster.getCompany_id(), pMaster.getFin_year(), "INSERTED", pMaster.getInserted_on(), pMaster.getInserted_by(), pMaster.getUpdated_on(), pMaster.getUpdated_by(), pMaster.getDeleted_on(), pMaster.getDeleted_by(), pMaster);
				pShift_details.add(shfts);
				
				dateTime=dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()));
			}
			
			
			
			pMaster.setProcess_master_shift_details(pShift_details);
			//For Shift Details end ........................................
		}
		
		return process_masterRepository.save(pMaster);
	}
	
	
	public List<Process_master> findAllProcess()
	{
		List<Process_master> processList=new ArrayList<Process_master>();
		processList.addAll(process_masterRepository.findAllProcesses());
		
		return processList;
	}
	public List<Process_master_ListDTO> findAllProcessList()
	{
		
		List<Process_master> processList=new ArrayList<Process_master>();
		processList.addAll(process_masterRepository.findAllProcesses());
		
		
		List<Process_master_ListDTO> process_master_List=new ArrayList<Process_master_ListDTO>();
		processList.forEach(element->
		{
			Process_master_ListDTO newlist=new Process_master_ListDTO();
			newlist.setId(element.getId());
			newlist.setBusiness_unitname(element.getBusiness_unitname());
			newlist.setProcess_id(element.getProcess_id());
			newlist.setProcess_no(element.getProcess_no());
			newlist.setProcess_type(element.getProcess_type());
			newlist.setShop_floorname(element.getShop_floorname());
			newlist.setProcess_desc(element.getProcess_desc());
			newlist.setStatus(bom_masterRepository.checkprocessexist(element.getProcess_id()));
			process_master_List.add(newlist);
		});
		
		
		return process_master_List;
	}
	
	
	
	
	public Process_master findOne(long id)
	{
		Optional<Process_master> op=this.process_masterRepository.findById(id);
		return op.get();
	}
	public Process_master_doc findOneprocessdoc(long id) 
	{
		Optional<Process_master_doc> op=this.process_master_docRepository.findById(id);
		return op.get();
	}
	@Transactional
	public void deleteProcessDocument(Process_master_doc process_master_doc) 
	{
		System.out.println("process_master_doc.getId()"+process_master_doc.getId());
		process_master_docRepository.updatepdfdelete(process_master_doc.getId());
	}
	
	
	public List<Process_master_docDTO> getProcessDocs(String pid)
	{
		List<Process_master_doc> modelList=new ArrayList<Process_master_doc>();
		modelList.addAll(process_master_docRepository.getProcess_master_doc(pid));
		Type listType=new TypeToken<List<Process_master_docDTO>>() {}.getType();
		
		List<Process_master_docDTO> docDtls=new ModelMapper().map(modelList,listType);
		
		return docDtls;
	}
	
	
	
	public List<Process_master_docDTO> getdocumentListwithfile(String doc_pdf)
	{
		
		String filename="C:/AnkitIndiaDocuments/"+doc_pdf;
		System.out.println("filename " +filename);
		List<Process_master_doc> modelList=new ArrayList<Process_master_doc>();
		modelList.addAll(process_master_docRepository.getdocumentListwithfile(filename));
		Type listType=new TypeToken<List<Process_master_docDTO>>() {}.getType();
		
		List<Process_master_docDTO> docDtls=new ModelMapper().map(modelList,listType);
		
		return docDtls;
	}
	
	public List<Process_master_shiftDTO> getProcessShift(String pid)
	{
		List<Process_master_shift_details> modelList=new ArrayList<Process_master_shift_details>();
		modelList.addAll(process_master_shift_detailsRepository.getProcess_master_shift_details(pid));
		Type listType=new TypeToken<List<Process_master_shiftDTO>>() {}.getType();
		
		List<Process_master_shiftDTO> shiftDtls=new ModelMapper().map(modelList,listType);
		
		return shiftDtls;
	}

	public List<Process_masterDTO> getProcess()
	{
		 List<Process_master> modelList=  process_masterRepository.findAllProcesses();
		 
		 Type listType=new TypeToken<List<Process_masterDTO>>() {}.getType();
		 
		 List<Process_masterDTO> shopF=new ModelMapper().map(modelList, listType);
		 
		 return shopF;
	}
	
	public List<Process_masterDTO> getProcessThruBUShopFloor(String bunit,String sfloor,String company)
	{
		 List<Process_master> modelList=  process_masterRepository.getProcessThruBUShopFloor(bunit,sfloor,company);
		 
		 Type listType=new TypeToken<List<Process_masterDTO>>() {}.getType();
		 
		 List<Process_masterDTO> shopF=new ModelMapper().map(modelList, listType);
		 
		 return shopF;
	}
	
	public List<Process_masterDTO> getProcessThruBUShopFloorRegular(String bunit,String sfloor,String company)
	{
		 List<Process_master> modelList=  process_masterRepository.getProcessThruBUShopFloorRegular(bunit,sfloor,company);
		 
		 Type listType=new TypeToken<List<Process_masterDTO>>() {}.getType();
		 
		 List<Process_masterDTO> shopF=new ModelMapper().map(modelList, listType);
		 
		 return shopF;
	}
	
	public List<Process_masterDTO> getProcessThruBUShopFloorSpl(String bunit,String sfloor,String company)
	{
		 List<Process_master> modelList=  process_masterRepository.getProcessThruBUShopFloorSpl(bunit,sfloor,company);
		 
		 Type listType=new TypeToken<List<Process_masterDTO>>() {}.getType();
		 
		 List<Process_masterDTO> shopF=new ModelMapper().map(modelList, listType);
		 
		 return shopF;
	}
	
	
	public Process_masterDTO getProcessThruBUSFProDesc(String bunit,String sfloor,String pdesc,String company)
	{
		 Process_master processModel=  process_masterRepository.getProcessThruBUSFProDesc(bunit,sfloor,pdesc,company);
		 
		 Type listType=new TypeToken<Process_masterDTO>() {}.getType();
		 
		 Process_masterDTO proDtls=new ModelMapper().map(processModel, listType);
		 
		 return proDtls;
	}
	
	@Transactional
	public Process_master update(Process_master pMaster,MultipartFile files[])
	{
		Optional<Process_master> op = process_masterRepository.findById(pMaster.getId());
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(pMaster.getBusiness_unit().compareTo("0") !=0 && pMaster.getBusiness_unit().compareTo("") !=0 && pMaster.getBusiness_unit() !=null) {
			pMaster.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pMaster.getBusiness_unit()).getBusinessunit_name());
		}else {pMaster.setBusiness_unitname("None");}
		
		if(pMaster.getShop_floor().compareTo("0") !=0 && pMaster.getShop_floor().compareTo("") !=0 && pMaster.getShop_floor() !=null) {
			pMaster.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(pMaster.getShop_floor()).getShop_floor_name());
		}else {pMaster.setShop_floorname("None");}
		System.out.println("itemgroup "+ pMaster.getItem_group().toString());
	
		pMaster.setModified_type("INSERTED");
		pMaster.setInserted_by(op.get().getInserted_by());
		pMaster.setInserted_on(op.get().getInserted_on());
		pMaster.setUpdated_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setUpdated_on(ldt);
		pMaster.setDeleted_by("NA");
		pMaster.setDeleted_on(ldt);
		
		//Update
		//changed on 01-04-2022
	//	process_master_docRepository.updateProcess_master_doc(op.get().getProcess_id());
		
		Set<Process_master_doc> pDoc=new HashSet<Process_master_doc>();
		pDoc.addAll(pMaster.getProcess_master_doc());
		int i=0;
		for(Process_master_doc pDocs:pDoc) 
		{
			
			System.out.println(" hello files : "+files.length);
			//here start
			
			if(files.length > 0) {
				try {
					System.out.println("files[i] :: "+i+" / "+files[i]);
						//fileUpload(files[i],gen_sno+"_");
					
						pDocs.setDoc_pdf(fileUpload(files[i],op.get().getProcess_id()+"_"));
						
					
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
					}
				
			}
			System.out.println("3 :: ");
			
			
			
			pDocs.setProcess_no(op.get().getProcess_no());
			pDocs.setProcess_id(op.get().getProcess_id());
			pDocs.setCompany_id(pMaster.getCompany_id());
			pDocs.setFin_year(pMaster.getFin_year());
			pDocs.setModified_type("INSERTED");
			pDocs.setInserted_by(pMaster.getInserted_by());
			pDocs.setInserted_on(pMaster.getInserted_on());
			pDocs.setUpdated_by(pMaster.getUpdated_by());
			pDocs.setUpdated_on(pMaster.getUpdated_on());
			pDocs.setDeleted_by("NA");
			pDocs.setDeleted_on(ldt);
		}
		pMaster.setProcess_master_doc(pDoc);
		
		if(pMaster.getProcess_type().compareTo("Special")!=0 && pMaster.getProcess_mntnce().compareTo("Yes")==0)
		{
			//For Shift Details start ........................................
			//Update
			process_master_shift_detailsRepository.updateProcess_master_shift_details(op.get().getProcess_id());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(pMaster.getShift_start_time(), formatter);
			
			Set<Process_master_shift_details> pShift_details=new HashSet<Process_master_shift_details>();
			
			for (int j=1;j<=Integer.parseInt(pMaster.getShift_no());j++) 
			{ 
				Process_master_shift_details shfts=new Process_master_shift_details(pMaster.getProcess_id(), pMaster.getProcess_no(), j, formatter.format(dateTime), formatter.format(dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()))), pMaster.getTot_shift_hrs(), pMaster.getCompany_id(), pMaster.getFin_year(), "INSERTED", pMaster.getInserted_on(), pMaster.getInserted_by(), pMaster.getUpdated_on(), pMaster.getUpdated_by(), pMaster.getDeleted_on(), pMaster.getDeleted_by(), pMaster);
				pShift_details.add(shfts);
				
				dateTime=dateTime.plusHours(Integer.parseInt(pMaster.getTot_shift_hrs()));
			}
			
			
			
			pMaster.setProcess_master_shift_details(pShift_details);
			//For Shift Details end ........................................
		}
		
		if(op.isPresent())
		{
			pMaster.setId(pMaster.getId());
		}
		return process_masterRepository.save(pMaster);
	}

	
	@Transactional
	 public Process_master deleteProcessMaster(Process_master pMaster,long id)
	{
		Optional<Process_master> op = process_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		System.out.println(" itemgroup  "+op.get().getItem_group() );
		
		pMaster.setItem_group(op.get().getItem_group());
		pMaster.setModified_type("DELETED");
		pMaster.setInserted_by(op.get().getInserted_by());
		pMaster.setInserted_on(op.get().getInserted_on());
		pMaster.setUpdated_by(op.get().getUpdated_by());
		pMaster.setUpdated_on(op.get().getUpdated_on());
		pMaster.setDeleted_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setDeleted_on(ldt);
		
		
		if(op.isPresent()) {
			pMaster.setId(id);
		}
		
		
		process_master_docRepository.delete(op.get().getProcess_id());
		return process_masterRepository.save(pMaster);
	}
}
