package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.Exporttotally.Tallyrequest_Openingbalanceofmaster;
import com.AnkitIndia.Exporttotally.Tallyrequest_SalesInvoice;
import com.AnkitIndia.Exporttotally.Tallyrequest_TransporterMaster;
import com.AnkitIndia.FileUpload.FileUploadUtil;
import com.AnkitIndia.Utility.FileSupplierMasterUtil;
import com.AnkitIndia.Utility.FileUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address_dtls;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_delv_to;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_delv_from;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_doc;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_address_dtls;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_broker;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_doc;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_vehicle_dtls;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_tds;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_delv_toRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Post_office_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_delv_fromRepository;
import com.AnkitIndia.jwtauthentication.repository.Tds_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_accontRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_address_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_statutoryRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_tdsRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partner_vehicle_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Transporter_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_address_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_delv_fromDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_vehicle_dtlsDTO;

@Service
public class Trans_bussiness_partnerService_Imp implements Trans_bussiness_partnerService{
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	Trans_bussiness_partner_vehicle_dtlsRepository trans_bussiness_partner_vehicle_dtlsRepository;
	
	@Autowired
	Transporter_groupRepository transporter_groupRepository;
	
	@Autowired
	Cust_bussiness_partner_delv_toRepository cust_Partner_delv_toRepository; 
	
	@Autowired
	Supp_bussiness_partner_delv_fromRepository supp_bussiness_partner_delv_fromRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Trans_bussiness_partner_brokerRepository trans_bussiness_partner_brokerRepository;
	
	@Autowired
	Trans_bussiness_partner_address_dtlsRepository trans_bussiness_partner_address_dtlsRepository;
	
	@Autowired
	Trans_bussiness_partner_docRepository trans_bussiness_partner_docRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
		
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Trans_bussiness_partner_accontRepository trans_bussiness_partner_accontRepository;

	@Autowired
	Trans_bussiness_partner_addressRepository trans_bussiness_partner_addressRepository;
	 
	@Autowired
	Trans_bussiness_partner_statutoryRepository trans_bussiness_partner_statutoryRepository;
	
	@Autowired
	City_masterRepository city_masterRepository;
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	Post_office_masterRepository post_office_masterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	LedgermasterRepository 	ledgermasterRepository;
	
	
	@Autowired
	Tds_masterRepository tds_masterRepository;
	
	@Autowired
	Trans_bussiness_partner_tdsRepository trans_bussiness_partner_tdsRepository;

	public SequenceIdDTO getTransSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=trans_bussiness_partnerRepository.getTransSequenceId(fprefix,company);
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
	
	@Transactional
	public Trans_bussiness_partner save(Trans_bussiness_partner trans_bussiness_partner,MultipartFile files[]) throws IOException {
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="TBP";
		if(trans_bussiness_partnerRepository.countId(prefix).isPresent() ) {
			slno=Long.parseLong(trans_bussiness_partnerRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		trans_bussiness_partner.setBp_Id(gen_sno);
		
		if(Utility.isNullOrEmpty(trans_bussiness_partner.getGroup_type())) {
			trans_bussiness_partner.setGroup_type_name(transporter_groupRepository.getTransParentGroup(trans_bussiness_partner.getGroup_type()).getBp_grp_name());
		}else {trans_bussiness_partner.setGroup_type_name("None");}
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=trans_bussiness_partner.getBp_code().substring(0,7);
		String gen_snonew=trans_bussiness_partnerRepository.getTransSequenceId(tprefix,trans_bussiness_partner.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix,nslno);
		trans_bussiness_partner.setBp_code(gen_tslno);
		/*End checking transaction no for unique */
		
		trans_bussiness_partner.setModified_type("INSERTED");
		trans_bussiness_partner.setInserted_by(userRepository.getUserDetails(trans_bussiness_partner.getUsername()).getName());
		trans_bussiness_partner.setInserted_on(ldt);
		trans_bussiness_partner.setUpdated_by("NA");
		trans_bussiness_partner.setUpdated_on(ldt);
		trans_bussiness_partner.setDeleted_by("NA");
		trans_bussiness_partner.setDeleted_on(ldt);
		
		Set<Trans_bussiness_partner_address> bpAddressSet=new HashSet<Trans_bussiness_partner_address>();
		bpAddressSet.add(trans_bussiness_partner.getTrans_bussiness_partner_address());
		for(Trans_bussiness_partner_address bpadd:bpAddressSet) 
		{
			if(Utility.isNullOrEmpty(bpadd.getState_code())) {
				bpadd.setState(state_masterRepository.getState(bpadd.getState_code()).getState_name());
			}else {bpadd.setState("None");}
			
			if(Utility.isNullOrEmpty(bpadd.getDist_code())) {
				bpadd.setDistrict(district_masterRepository.getDistrictDtls(bpadd.getDist_code()).getDist_name());
			}else {bpadd.setDistrict("None");}
			
			/*if(Utility.isNullOrEmpty(bpadd.getCity_code())) {
				bpadd.setCity(city_masterRepository.getCityDtls(bpadd.getCity_code()).getCity_name());
			}else {bpadd.setCity("None");}*/
			
		/*	if(Utility.isNullOrEmpty(bpadd.getPostid())) {
				bpadd.setPost_office(post_office_masterRepository.getPincodeThruPO(bpadd.getPostid()).get().getPost_office());
			}else {bpadd.setPost_office("None");}*/
			bpadd.setPost_office(bpadd.getPost_office());
			bpadd.setCity(bpadd.getCity_code());
			bpadd.setBp_Id(gen_sno);
			bpadd.setBp_code(gen_tslno);
			bpadd.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpadd.setFin_year(trans_bussiness_partner.getFin_year());
			bpadd.setModified_type("INSERTED");
			bpadd.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpadd.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpadd.setUpdated_by("NA");
			bpadd.setUpdated_on(ldt);
			bpadd.setDeleted_by("NA");
			bpadd.setDeleted_on(ldt);
		}
		if(!bpAddressSet.isEmpty())	{
			trans_bussiness_partner.setTrans_bussiness_partner_address(bpAddressSet.iterator().next());
		}
		
		Set<Trans_bussiness_partner_address_dtls> bpAddrDtlsSet=new HashSet<Trans_bussiness_partner_address_dtls>();
		bpAddrDtlsSet.addAll(trans_bussiness_partner.getTrans_bussiness_partner_address_dtls());
		for(Trans_bussiness_partner_address_dtls bpAddrDtls:bpAddrDtlsSet) 
		{
			bpAddrDtls.setBp_Id(gen_sno);
			bpAddrDtls.setBp_code(gen_tslno);
			bpAddrDtls.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpAddrDtls.setFin_year(trans_bussiness_partner.getFin_year());
			bpAddrDtls.setModified_type("INSERTED");
			bpAddrDtls.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpAddrDtls.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpAddrDtls.setUpdated_by("NA");
			bpAddrDtls.setUpdated_on(ldt);
			bpAddrDtls.setDeleted_by("NA");
			bpAddrDtls.setDeleted_on(ldt);
		}
		trans_bussiness_partner.setTrans_bussiness_partner_address_dtls(bpAddrDtlsSet);

		Set<Trans_bussiness_partner_accont> bpAccountSet=new HashSet<Trans_bussiness_partner_accont>();
		bpAccountSet.add(trans_bussiness_partner.getTrans_bussiness_partner_accont());
		for(Trans_bussiness_partner_accont bpAccount:bpAccountSet) 
		{
			bpAccount.setBp_Id(gen_sno);
			bpAccount.setBp_code(gen_tslno);
			bpAccount.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpAccount.setFin_year(trans_bussiness_partner.getFin_year());
			bpAccount.setModified_type("INSERTED");
			bpAccount.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpAccount.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpAccount.setUpdated_by("NA");
			bpAccount.setUpdated_on(ldt);
			bpAccount.setDeleted_by("NA");
			bpAccount.setDeleted_on(ldt);
		}
		if(!bpAccountSet.isEmpty())	{
			trans_bussiness_partner.setTrans_bussiness_partner_accont(bpAccountSet.iterator().next());
		}

		Set<Trans_bussiness_partner_statutory> bpStatSet=new HashSet<Trans_bussiness_partner_statutory>();
		bpStatSet.add(trans_bussiness_partner.getTrans_bussiness_partner_statutory());
		for(Trans_bussiness_partner_statutory bpStat:bpStatSet) 
		{
			bpStat.setBp_Id(gen_sno);
			bpStat.setBp_code(gen_tslno);
			bpStat.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpStat.setFin_year(trans_bussiness_partner.getFin_year());
			bpStat.setModified_type("INSERTED");
			bpStat.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpStat.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpStat.setUpdated_by("NA");
			bpStat.setUpdated_on(ldt);
			bpStat.setDeleted_by("NA");
			bpStat.setDeleted_on(ldt);
		}
		if(!bpStatSet.isEmpty()) {
			trans_bussiness_partner.setTrans_bussiness_partner_statutory(bpStatSet.iterator().next());
		}
		
		
		Set<Trans_bussiness_partner_tds> tds=new HashSet<Trans_bussiness_partner_tds>();
		tds.add(trans_bussiness_partner.getTrans_bussiness_partner_tds());
		for(Trans_bussiness_partner_tds tdsdetails:tds) 
		{
			tdsdetails.setBp_Id(gen_sno);
			tdsdetails.setBp_code(gen_tslno);
			
			if(Utility.isNullOrEmpty(tdsdetails.getTds_acc())) 
			{
				tdsdetails.setTds_accname(ledgermasterRepository.getLedgers(tdsdetails.getTds_acc()).getLedgername());
			}
			else
			{
				tdsdetails.setTds_accname("None");
			}
			if(Utility.isNullOrEmpty(tdsdetails.getTds_id())) 
			{
				tdsdetails.setTds_type(tds_masterRepository.getTDSRate(tdsdetails.getTds_id()).getTds_type());
			}
			else
			{
				tdsdetails.setTds_type("None");
			}
			
			
			
			tdsdetails.setCompany_id(trans_bussiness_partner.getCompany_id());
			tdsdetails.setFin_year(trans_bussiness_partner.getFin_year());
			tdsdetails.setModified_type("INSERTED");
			tdsdetails.setInserted_by(trans_bussiness_partner.getInserted_by());
			tdsdetails.setInserted_on(trans_bussiness_partner.getInserted_on());
			tdsdetails.setUpdated_by("NA");
			tdsdetails.setUpdated_on(ldt);
			tdsdetails.setDeleted_by("NA");
			tdsdetails.setDeleted_on(ldt);
		}
		if(!tds.isEmpty()) {
			trans_bussiness_partner.setTrans_bussiness_partner_tds(tds.iterator().next());
		}
	/*	06-05-2022
		Set<Trans_bussiness_partner_broker> bpBrokerSet=new HashSet<Trans_bussiness_partner_broker>();
		bpBrokerSet.addAll(trans_bussiness_partner.getTrans_bussiness_partner_broker());
		for(Trans_bussiness_partner_broker bpBroker:bpBrokerSet) 
		{
			bpBroker.setBp_Id(gen_sno);
			bpBroker.setBp_code(gen_tslno);
			//System.out.println("vencode name::"+bpBroker.getVen_code_name());
			if(bpBroker.getVen_code_name().compareTo("")!=0) {
				bpBroker.setVen_name(broker_masterRepository.brokerNameByCode(bpBroker.getVen_code_name()).getName());
			}
			bpBroker.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpBroker.setFin_year(trans_bussiness_partner.getFin_year());
			bpBroker.setModified_type("INSERTED");
			bpBroker.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpBroker.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpBroker.setUpdated_by("NA");
			bpBroker.setUpdated_on(ldt);
			bpBroker.setDeleted_by("NA");
			bpBroker.setDeleted_on(ldt);
		}
		trans_bussiness_partner.setTrans_bussiness_partner_broker(bpBrokerSet);
		*/
		Set<Trans_bussiness_partner_vehicle_dtls> bpVehicleSet=new HashSet<Trans_bussiness_partner_vehicle_dtls>();
		bpVehicleSet.addAll(trans_bussiness_partner.getTrans_bussiness_partner_vehicle_dtls());
		for(Trans_bussiness_partner_vehicle_dtls bpvehicle:bpVehicleSet) 
		{
			bpvehicle.setBp_Id(gen_sno);
			bpvehicle.setBp_code(gen_tslno);
			bpvehicle.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpvehicle.setFin_year(trans_bussiness_partner.getFin_year());
			bpvehicle.setModified_type("INSERTED");
			bpvehicle.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpvehicle.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpvehicle.setUpdated_by("NA");
			bpvehicle.setUpdated_on(ldt);
			bpvehicle.setDeleted_by("NA");
			bpvehicle.setDeleted_on(ldt);
		}
		trans_bussiness_partner.setTrans_bussiness_partner_vehicle_dtls(bpVehicleSet);
		
		/*Set<Trans_bussiness_partner_doc> bpdocSet=new HashSet<Trans_bussiness_partner_doc>();
		bpdocSet.addAll(trans_bussiness_partner.getTrans_bussiness_partner_doc());
		for(Trans_bussiness_partner_doc bpdoc:bpdocSet) 
		{
			bpdoc.setBp_Id(gen_sno);
			bpdoc.setBp_code(gen_tslno);
			bpdoc.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpdoc.setFin_year(trans_bussiness_partner.getFin_year());
			bpdoc.setModified_type("INSERTED");
			bpdoc.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpdoc.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpdoc.setUpdated_by("NA");
			bpdoc.setUpdated_on(ldt);
			bpdoc.setDeleted_by("NA");
			bpdoc.setDeleted_on(ldt);
		}
		trans_bussiness_partner.setTrans_bussiness_partner_doc(bpdocSet);*/
		
		Set<Trans_bussiness_partner_doc> bpdocSet=new HashSet<Trans_bussiness_partner_doc>();
		bpdocSet.addAll(trans_bussiness_partner.getTrans_bussiness_partner_doc());
		int i=0;
		for(Trans_bussiness_partner_doc bpdoc:bpdocSet) 
		{
			bpdoc.setBp_Id(gen_sno);
			bpdoc.setBp_code(gen_tslno);
			
			
			System.out.println(gen_sno+" hello files : "+files.length);
			//here start
			
			if(files.length > 0) {
				try {
					System.out.println("files[i] :: "+i+" / "+files[i]);
						//fileUpload(files[i],gen_sno+"_");
					
					//bpdoc.setDoc_pdf(FileUploadUtil.fileUpload(files[i],gen_sno+"_",FileSupplierMasterUtil.folderPathTransporter));
					bpdoc.setDoc_pdf(fileUpload(files[i],gen_sno+"_"));		
					
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
					}
				
			}
			System.out.println("3 :: ");
			
			bpdoc.setCompany_id(trans_bussiness_partner.getCompany_id());
			bpdoc.setFin_year(trans_bussiness_partner.getFin_year());
			bpdoc.setModified_type("INSERTED");
			bpdoc.setInserted_by(trans_bussiness_partner.getInserted_by());
			bpdoc.setInserted_on(trans_bussiness_partner.getInserted_on());
			bpdoc.setUpdated_by("NA");
			bpdoc.setUpdated_on(ldt);
			bpdoc.setDeleted_by("NA");
			bpdoc.setDeleted_on(ldt);
		}
		trans_bussiness_partner.setTrans_bussiness_partner_doc(bpdocSet);
		
		
		return trans_bussiness_partnerRepository.save(trans_bussiness_partner);
    }
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileUtil.folderPathTransporter+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPathTransporter+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		 
		            return files_name;
	}
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPathTransporter);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	@Transactional
	public Trans_bussiness_partner update(Trans_bussiness_partner iMaster,MultipartFile files[])
	{
		Optional<Trans_bussiness_partner> op = trans_bussiness_partnerRepository.findById(iMaster.getId());
		LocalDateTime ldt = LocalDateTime.now();
		
		
		if(Utility.isNullOrEmpty(iMaster.getGroup_type())) {
			iMaster.setGroup_type_name(transporter_groupRepository.getTransParentGroup(iMaster.getGroup_type()).getBp_grp_name());
		}else {iMaster.setGroup_type_name("None");}
		
		iMaster.setBp_Id(op.get().getBp_Id());
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iMaster.setId(iMaster.getId());
		}

		trans_bussiness_partner_addressRepository.trans_bussiness_partner_addressupdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Trans_bussiness_partner_address> bpAddressSet=new HashSet<Trans_bussiness_partner_address>();
		bpAddressSet.add(iMaster.getTrans_bussiness_partner_address());
		for(Trans_bussiness_partner_address bpadd:bpAddressSet) 
		{
			if(Utility.isNullOrEmpty(bpadd.getState_code())) {
				bpadd.setState(state_masterRepository.getState(bpadd.getState_code()).getState_name());
			}else {bpadd.setState("None");}
			
			if(Utility.isNullOrEmpty(bpadd.getDist_code())) {
				bpadd.setDistrict(district_masterRepository.getDistrictDtls(bpadd.getDist_code()).getDist_name());
			}else {bpadd.setDistrict("None");}
			
			/*if(Utility.isNullOrEmpty(bpadd.getCity_code())) {
				bpadd.setCity(city_masterRepository.getCityDtls(bpadd.getCity_code()).getCity_name());
			}else {bpadd.setCity("None");}*/
			
			/*if(Utility.isNullOrEmpty(bpadd.getPostid())) {
				bpadd.setPost_office(post_office_masterRepository.getPincodeThruPO(bpadd.getPostid()).get().getPost_office());
			}else {bpadd.setPost_office("None");}*/
			//System.out.println("check data:"+bpadd.getPost_office());
			bpadd.setPost_office(bpadd.getPost_office());
			bpadd.setCity(bpadd.getCity_code());
			bpadd.setBp_Id(iMaster.getBp_Id());
			bpadd.setBp_code(iMaster.getBp_code());
			bpadd.setCompany_id(iMaster.getCompany_id());
			bpadd.setFin_year(iMaster.getFin_year());
			bpadd.setModified_type("INSERTED");
			bpadd.setInserted_by(iMaster.getInserted_by());
			bpadd.setInserted_on(iMaster.getInserted_on());
			bpadd.setUpdated_by(iMaster.getUpdated_by());
			bpadd.setUpdated_on(iMaster.getUpdated_on());
			bpadd.setDeleted_by("NA");
			bpadd.setDeleted_on(ldt);
		}
		if(!bpAddressSet.isEmpty())
		{
			iMaster.setTrans_bussiness_partner_address(bpAddressSet.iterator().next());
		}
		
		trans_bussiness_partner_address_dtlsRepository.trans_bussiness_partner_address_dtlsupdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Trans_bussiness_partner_address_dtls> bpAddrDtlsSet=new HashSet<Trans_bussiness_partner_address_dtls>();
		bpAddrDtlsSet.addAll(iMaster.getTrans_bussiness_partner_address_dtls());
		for(Trans_bussiness_partner_address_dtls bpAddrDtls:bpAddrDtlsSet) 
		{
			bpAddrDtls.setBp_Id(iMaster.getBp_Id());
			bpAddrDtls.setBp_code(iMaster.getBp_code());
			bpAddrDtls.setCompany_id(iMaster.getCompany_id());
			bpAddrDtls.setFin_year(iMaster.getFin_year());
			bpAddrDtls.setModified_type("INSERTED");
			bpAddrDtls.setInserted_by(iMaster.getInserted_by());
			bpAddrDtls.setInserted_on(iMaster.getInserted_on());
			bpAddrDtls.setUpdated_by(iMaster.getUpdated_by());
			bpAddrDtls.setUpdated_on(iMaster.getUpdated_on());
			bpAddrDtls.setDeleted_by("NA");
			bpAddrDtls.setDeleted_on(ldt);
		}
		
		iMaster.setTrans_bussiness_partner_address_dtls(bpAddrDtlsSet);
		
		trans_bussiness_partner_accontRepository.trans_bussiness_partner_accontupdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Trans_bussiness_partner_accont> bpAccountSet=new HashSet<Trans_bussiness_partner_accont>();
		bpAccountSet.add(iMaster.getTrans_bussiness_partner_accont());
		for(Trans_bussiness_partner_accont bpAccount:bpAccountSet) 
		{
			bpAccount.setBp_Id(iMaster.getBp_Id());
			bpAccount.setBp_code(iMaster.getBp_code());
			bpAccount.setCompany_id(iMaster.getCompany_id());
			bpAccount.setFin_year(iMaster.getFin_year());
			bpAccount.setModified_type("INSERTED");
			bpAccount.setInserted_by(iMaster.getInserted_by());
			bpAccount.setInserted_on(iMaster.getInserted_on());
			bpAccount.setUpdated_by(iMaster.getUpdated_by());
			bpAccount.setUpdated_on(iMaster.getUpdated_on());
			bpAccount.setDeleted_by("NA");
			bpAccount.setDeleted_on(ldt);
		}
		if(!bpAccountSet.isEmpty())
		{
			iMaster.setTrans_bussiness_partner_accont(bpAccountSet.iterator().next());
		}
		
		trans_bussiness_partner_statutoryRepository.trans_bussiness_partner_statutoryupdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Trans_bussiness_partner_statutory> bpStatSet=new HashSet<Trans_bussiness_partner_statutory>();
		bpStatSet.add(iMaster.getTrans_bussiness_partner_statutory());
		for(Trans_bussiness_partner_statutory bpStat:bpStatSet) 
		{
			bpStat.setBp_Id(iMaster.getBp_Id());
			bpStat.setBp_code(iMaster.getBp_code());
			bpStat.setCompany_id(iMaster.getCompany_id());
			bpStat.setFin_year(iMaster.getFin_year());
			bpStat.setModified_type("INSERTED");
			bpStat.setInserted_by(iMaster.getInserted_by());
			bpStat.setInserted_on(iMaster.getInserted_on());
			bpStat.setUpdated_by(iMaster.getUpdated_by());
			bpStat.setUpdated_on(iMaster.getUpdated_on());
			bpStat.setDeleted_by("NA");
			bpStat.setDeleted_on(ldt);
		}
		if(!bpStatSet.isEmpty())
		{
			iMaster.setTrans_bussiness_partner_statutory(bpStatSet.iterator().next());
		}
		
		trans_bussiness_partner_tdsRepository.updatetds(iMaster.getBp_Id(),"UPDATED");
		Set<Trans_bussiness_partner_tds> tds=new HashSet<Trans_bussiness_partner_tds>();
		tds.add(iMaster.getTrans_bussiness_partner_tds());
		for(Trans_bussiness_partner_tds tdsdetails:tds) 
		{
			tdsdetails.setBp_Id(iMaster.getBp_Id());
			tdsdetails.setBp_code(iMaster.getBp_code());
			
			if(Utility.isNullOrEmpty(tdsdetails.getTds_acc())) 
			{
				tdsdetails.setTds_accname(ledgermasterRepository.getLedgers(tdsdetails.getTds_acc()).getLedgername());
			}
			else
			{
				tdsdetails.setTds_accname("None");
			}
			if(Utility.isNullOrEmpty(tdsdetails.getTds_id())) 
			{
				tdsdetails.setTds_type(tds_masterRepository.getTDSRate(tdsdetails.getTds_id()).getTds_type());
			}
			else
			{
				tdsdetails.setTds_type("None");
			}
			
			
			
			tdsdetails.setCompany_id(iMaster.getCompany_id());
			tdsdetails.setFin_year(iMaster.getFin_year());
			tdsdetails.setModified_type("INSERTED");
			tdsdetails.setInserted_by(iMaster.getInserted_by());
			tdsdetails.setInserted_on(iMaster.getInserted_on());
			tdsdetails.setUpdated_by("NA");
			tdsdetails.setUpdated_on(ldt);
			tdsdetails.setDeleted_by("NA");
			tdsdetails.setDeleted_on(ldt);
		}
		if(!tds.isEmpty()) {
			iMaster.setTrans_bussiness_partner_tds(tds.iterator().next());
		}
		
		
		trans_bussiness_partner_brokerRepository.trans_bussiness_partner_brokerupdate(iMaster.getBp_Id(),"UPDATED");
	/*06-05-2022	
		Set<Trans_bussiness_partner_broker> bpBrokerSet=new HashSet<Trans_bussiness_partner_broker>();
		bpBrokerSet.addAll(iMaster.getTrans_bussiness_partner_broker());
		for(Trans_bussiness_partner_broker bpBroker:bpBrokerSet) 
		{
			bpBroker.setBp_Id(iMaster.getBp_Id());
			bpBroker.setBp_code(iMaster.getBp_code());
			if(bpBroker.getVen_code_name().compareTo("")!=0) {
				bpBroker.setVen_name(broker_masterRepository.brokerNameByCode(bpBroker.getVen_code_name()).getName());
			}
			bpBroker.setCompany_id(iMaster.getCompany_id());
			bpBroker.setFin_year(iMaster.getFin_year());
			bpBroker.setModified_type("INSERTED");
			bpBroker.setInserted_by(iMaster.getInserted_by());
			bpBroker.setInserted_on(iMaster.getInserted_on());
			bpBroker.setUpdated_by(iMaster.getUpdated_by());
			bpBroker.setUpdated_on(iMaster.getUpdated_on());
			bpBroker.setDeleted_by("NA");
			bpBroker.setDeleted_on(ldt);
		}
		
		iMaster.setTrans_bussiness_partner_broker(bpBrokerSet);
		*/
		trans_bussiness_partner_vehicle_dtlsRepository.trans_bussiness_partner_vehicle_dtlsupdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Trans_bussiness_partner_vehicle_dtls> bpVehicleSet=new HashSet<Trans_bussiness_partner_vehicle_dtls>();
		bpVehicleSet.addAll(iMaster.getTrans_bussiness_partner_vehicle_dtls());
		for(Trans_bussiness_partner_vehicle_dtls bpvehicle:bpVehicleSet) 
		{
			bpvehicle.setBp_Id(iMaster.getBp_Id());
			bpvehicle.setBp_code(iMaster.getBp_code());
			bpvehicle.setCompany_id(iMaster.getCompany_id());
			bpvehicle.setFin_year(iMaster.getFin_year());
			bpvehicle.setModified_type("INSERTED");
			bpvehicle.setInserted_by(iMaster.getInserted_by());
			bpvehicle.setInserted_on(iMaster.getInserted_on());
			bpvehicle.setUpdated_by(iMaster.getUpdated_by());
			bpvehicle.setUpdated_on(iMaster.getUpdated_on());
			bpvehicle.setDeleted_by("NA");
			bpvehicle.setDeleted_on(ldt);
		}
		
		iMaster.setTrans_bussiness_partner_vehicle_dtls(bpVehicleSet);
		
		trans_bussiness_partner_docRepository.trans_bussiness_partner_docupdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Trans_bussiness_partner_doc> bpdocSet=new HashSet<Trans_bussiness_partner_doc>();
		bpdocSet.addAll(iMaster.getTrans_bussiness_partner_doc());
		int i=0;
		
			
			
			
		for(Trans_bussiness_partner_doc bpdoc:bpdocSet) 
		{
			System.out.println(" hello files : "+files.length);
			//here start
			
			if(files.length > 0) {
				try {
					System.out.println("files[i] :: "+i+" / "+files[i]);
						//fileUpload(files[i],gen_sno+"_");
					
					bpdoc.setDoc_pdf(fileUpload(files[i],op.get().getBp_Id()+"_"));
					
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
					}
			}
			System.out.println("3 :: ");
			
			bpdoc.setBp_Id(iMaster.getBp_Id());
			bpdoc.setBp_code(iMaster.getBp_code());
			bpdoc.setCompany_id(iMaster.getCompany_id());
			bpdoc.setFin_year(iMaster.getFin_year());
			bpdoc.setModified_type("INSERTED");
			bpdoc.setInserted_by(iMaster.getInserted_by());
			bpdoc.setInserted_on(iMaster.getInserted_on());
			bpdoc.setUpdated_by(iMaster.getUpdated_by());
			bpdoc.setUpdated_on(iMaster.getUpdated_on());
			bpdoc.setDeleted_by("NA");
			bpdoc.setDeleted_on(ldt);
		}
		
		iMaster.setTrans_bussiness_partner_doc(bpdocSet);
		
		return trans_bussiness_partnerRepository.save(iMaster);
	}
	
	@Transactional
	public Trans_bussiness_partner deleteTransBussinessPartner (Trans_bussiness_partner tbpartner,Long id)
	{
		Optional<Trans_bussiness_partner> op = trans_bussiness_partnerRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Trans_bussiness_partner trans_bussiness_partner=op.get();
		
		trans_bussiness_partner.setInserted_by(op.get().getInserted_by());
		trans_bussiness_partner.setInserted_on(op.get().getInserted_on());
		trans_bussiness_partner.setUpdated_by("NA");
		trans_bussiness_partner.setUpdated_on(ldt);
		trans_bussiness_partner.setDeleted_by(userRepository.getUserDetails(tbpartner.getUsername()).getName());
		trans_bussiness_partner.setDeleted_on(ldt);
		
		trans_bussiness_partner.setModified_type("DELETED");
		
		trans_bussiness_partner_docRepository.trans_bussiness_partner_docupdate(op.get().getBp_Id(),"DELETED");
		trans_bussiness_partner_vehicle_dtlsRepository.trans_bussiness_partner_vehicle_dtlsupdate(op.get().getBp_Id(),"DELETED");
		trans_bussiness_partner_brokerRepository.trans_bussiness_partner_brokerupdate(op.get().getBp_Id(),"DELETED");
		trans_bussiness_partner_statutoryRepository.trans_bussiness_partner_statutoryupdate(op.get().getBp_Id(),"DELETED");
		trans_bussiness_partner_accontRepository.trans_bussiness_partner_accontupdate(op.get().getBp_Id(),"DELETED");
		trans_bussiness_partner_address_dtlsRepository.trans_bussiness_partner_address_dtlsupdate(op.get().getBp_Id(),"DELETED");
		trans_bussiness_partner_addressRepository.trans_bussiness_partner_addressupdate(op.get().getBp_Id(),"DELETED");
		trans_bussiness_partner_tdsRepository.updatetds(op.get().getBp_Id(),"DELETED");
		
		if(op.isPresent())
		{
			trans_bussiness_partner.setId(id);
		}
		return trans_bussiness_partnerRepository.save(trans_bussiness_partner);
	}
	
	public List<Trans_bussiness_partner> findAll(){
		List<Trans_bussiness_partner> transList=new ArrayList<Trans_bussiness_partner>();
		transList.addAll(trans_bussiness_partnerRepository.findAll());
				
		List<Trans_bussiness_partner> allData = transList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Trans_bussiness_partner::getBp_code).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	public List<Trans_bussiness_partner> transporterownlist(String translist)
	{
		List<Trans_bussiness_partner> transList12=new ArrayList<Trans_bussiness_partner>();
		System.out.println("here "+translist);
		if(translist.contains(",")) 
		{
			String[] splittrans = translist.split(",");
			for(int i=0;i<splittrans.length;i++) 
			{
				transList12.add(trans_bussiness_partnerRepository.bpNameById(splittrans[i]));
			}
			
			
		}
		else 
		{
			transList12.add(trans_bussiness_partnerRepository.bpNameById(translist));
		}
		
		return transList12;
	}
	
	public List<Trans_bussiness_partner> findTransporters(String searchtext){
		List<Trans_bussiness_partner> transList=new ArrayList<Trans_bussiness_partner>();
		transList.addAll(trans_bussiness_partnerRepository.findAll());

		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Trans_bussiness_partner> allData = transList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Trans_bussiness_partner::getBp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Trans_bussiness_partner> allData = transList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getBp_name()+c.getBp_code()+c.getBp_type()+c.getTrans_currency()+c.getConstitution()+c.getSsi_app()+c.getGroup_type_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Trans_bussiness_partner::getBp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public Trans_bussiness_partner findOne(long id) {
		Optional<Trans_bussiness_partner> op=this.trans_bussiness_partnerRepository.findById(id);
		return op.get();
	}
		
	public List<Trans_bussiness_partnerDTO> getTransporterMNCList(){
				System.out.println("getTransporterMNCList");
			List<Trans_bussiness_partner> modelList=trans_bussiness_partnerRepository.getTransporterMNCList(true);
			
			List<Trans_bussiness_partner> allData = modelList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Trans_bussiness_partner::getBp_name))
					  .collect(Collectors.toList());
			
			Type listType=new TypeToken<List<Trans_bussiness_partnerDTO>>() {}.getType();
			
			List<Trans_bussiness_partnerDTO> itemNameList=new ModelMapper().map(allData, listType);
			
			return itemNameList;
		}
	
	public List<Map<String, Object>> getTransporterMNCListFast(){

 		List<Map<String, Object>> val=trans_bussiness_partnerRepository.getTransporterMNCListFast(true);
 		
		return val;
	}
	
	public List<Map<String, Object>> getTransporterListFastbp_Id(){
		return trans_bussiness_partnerRepository.getTransporterListFastbp_Id(true);
	}
	
	public List<Trans_bussiness_partnerDTO> getTransporterThruCustomer(String custid) {
		 
		Set<Trans_bussiness_partner> tPartnerSet=new HashSet<Trans_bussiness_partner>();
		List<Trans_bussiness_partner> tBussiness_partners=new ArrayList<Trans_bussiness_partner>();
		LinkedHashSet<String> allTrans=new LinkedHashSet<String>();
		
		String[] arrOfStr=null;String tPartner="";
		List<Cust_bussiness_partner_delv_to> cDelvToList=cust_Partner_delv_toRepository.getCustomerTransporters(custid);
	
		for(Cust_bussiness_partner_delv_to cDelv_to:cDelvToList) {
			if(cDelv_to.getTransport_own().compareToIgnoreCase("YES")==0) {
				tPartner +=cDelv_to.getTransporters()+",";
			}
		}
	
		if(Utility.isNullOrEmpty(tPartner)) {
		tPartner=tPartner.substring(0,tPartner.length()-1);
		arrOfStr=tPartner.split(",");
			for(int i=0;i<arrOfStr.length;i++)
			{
				allTrans.add(arrOfStr[i]);
			}
			ArrayList<String> trans=new ArrayList<String>(allTrans);
			for(int j=0;j<trans.size();j++)
			{
				System.err.println(trans.get(j));
				tPartnerSet.addAll(trans_bussiness_partnerRepository.getTransporterThruCustomer(trans.get(j)));
			}
		}
		tBussiness_partners.addAll(tPartnerSet);
		
		Type listType=new TypeToken<List<Trans_bussiness_partnerDTO>>() {}.getType();
		List<Trans_bussiness_partnerDTO> transList=new ModelMapper().map(tBussiness_partners, listType);
		
		return transList;
	  }
	 
	
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppliertransport(String suppid) 
	{
		//System.out.println("suppid/"+suppid);
		List<Supp_bussiness_partner_delv_from> tPartner=supp_bussiness_partner_delv_fromRepository.getSuppTransportersown(suppid);
		
		Type listType=new TypeToken<List<Supp_bussiness_partner_delv_fromDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_delv_fromDTO> transList=new ModelMapper().map(tPartner, listType);
		
		return transList;
		
		
	}
	
	public List<VehicleMaster> getsalevehiclelist(String transportid)
	{
		List<Trans_bussiness_partner_vehicle_dtls> vechilelist = trans_bussiness_partner_vehicle_dtlsRepository.getVehicleDetails(transportid);
		
		List<VehicleMaster> vechile=new ArrayList<VehicleMaster>();
		
		for(int i=0;i<vechilelist.size();i++) 
		{
			//System.out.println("vechilelist size::"+vechilelist.get(i).getVehicle_name());
			VehicleMaster newvechile= new VehicleMaster();
			newvechile.setVehicle_id(vechilelist.get(i).getVehicle_name());
			newvechile.setVehicle_no(vehicleMasterRepository.getVehicleDetails(vechilelist.get(i).getVehicle_name()).getVehicle_no());
			
			vechile.add(newvechile);
		}
		
		return vechile;
	}
	
	//public String getSuppliertransport1(String suppid) 
	//{
	//	String tPartner=supp_bussiness_partner_delv_fromRepository.getSuppTransportersown(suppid).getTransport_own();
		
	//	return tPartner;
	//}
	
	public List<Trans_bussiness_partnerDTO> getTransporterThruSupplier(String suppid) {
			
			String tPartner=supp_bussiness_partner_delv_fromRepository.getSuppTransporters(suppid).getTransporters();
			String[] arrOfStr=tPartner.split(",");
			
			List<Trans_bussiness_partner> modelList=new ArrayList<Trans_bussiness_partner>();
			
			for(int i=0;i<arrOfStr.length;i++)
			{
				modelList.addAll(trans_bussiness_partnerRepository.getTransporterThruSupplier(arrOfStr[i]));
			}
			
			modelList.forEach((e)->{
				e.setBp_name(e.getBp_name().toUpperCase());
			});
	 		
			List<Trans_bussiness_partner> allData = modelList
					  .parallelStream()
					  .filter(c ->c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Trans_bussiness_partner::getBp_name))
					  .collect(Collectors.toList());
			
			Type listType=new TypeToken<List<Trans_bussiness_partnerDTO>>() {}.getType();
			
			List<Trans_bussiness_partnerDTO> transList=new ModelMapper().map(allData, listType);
			
			return transList;
		  }
	  
	public List<Trans_bussiness_partnerDTO> getTransporterThruGroup(String tgid) {
			
			List<Trans_bussiness_partner> modelList=trans_bussiness_partnerRepository.getTransporterThruGroup(tgid);
			
			for(int i=0;i<modelList.size();i++) {
				modelList.get(i).setGroup_type(transporter_groupRepository.getTransParentGroup(modelList.get(i).getGroup_type()).getBp_grp_name());
			}
			
			Type listType=new TypeToken<List<Trans_bussiness_partnerDTO>>() {}.getType();
			
			List<Trans_bussiness_partnerDTO> transByGrp=new ModelMapper().map(modelList, listType);
			
			return transByGrp;
	  }
	  
	public Trans_bussiness_partnerDTO getBPNameByCode(String bpCode){
	    	
		  Trans_bussiness_partner modelList=trans_bussiness_partnerRepository.bpNameById(bpCode);
			// Create Conversion Type
			Type listType = new TypeToken<Trans_bussiness_partnerDTO>() {}.getType();
			
			// Convert List of Entity objects to a List of DTOs objects 
			Trans_bussiness_partnerDTO bpNameList= new ModelMapper().map(modelList,listType);
			
			return bpNameList;
		}
	  
	public List<Trans_bussiness_partner_brokerDTO> getTransporterBrokerList(String code){

		List<Trans_bussiness_partner_broker> modelList=trans_bussiness_partner_brokerRepository.getTransporterBrokerList(code);
			
		Type listType=new TypeToken<List<Trans_bussiness_partner_brokerDTO>>() {}.getType();
		
		List<Trans_bussiness_partner_brokerDTO> transBrokerList=new ModelMapper().map(modelList,listType);
		
		return transBrokerList;
	}
	
	public List<Trans_bussiness_partner_brokerDTO> getTransporterBrokers(String vcode){

		List<Trans_bussiness_partner_broker> modelList=trans_bussiness_partner_brokerRepository.getTransporterBrokers(vcode);
		
		Type listType=new TypeToken<List<Trans_bussiness_partner_brokerDTO>>() {}.getType();
		
		List<Trans_bussiness_partner_brokerDTO> transBrokers=new ModelMapper().map(modelList,listType);
		
		return transBrokers;
	}
  
	public List<Trans_bussiness_partner_vehicle_dtlsDTO> getTransListByVlno(String vno)
		{
			List<Trans_bussiness_partner_vehicle_dtls> modelList=trans_bussiness_partner_vehicle_dtlsRepository.getTransListByVlno(vno);
				
			Type listType=new TypeToken<List<Trans_bussiness_partner_vehicle_dtlsDTO>>() {}.getType();
			
			List<Trans_bussiness_partner_vehicle_dtlsDTO> trnsCode=new ModelMapper().map(modelList,listType);
			
			return trnsCode;
		}
		
	public Trans_bussiness_partner_accontDTO getTransAccount(String bp_Id)
	 {
		 Trans_bussiness_partner_accont modelList=trans_bussiness_partner_accontRepository.getTransAccount(bp_Id);
		 Type listType = new TypeToken<Trans_bussiness_partner_accontDTO>() {}.getType();

		 Trans_bussiness_partner_accontDTO itemNameList= new ModelMapper().map(modelList,listType);
			
		return itemNameList;
	 }
	 
	public Trans_bussiness_partner_addressDTO getTransBPAddr(String bp_id)
	 {
		 Trans_bussiness_partner_address modelList=trans_bussiness_partner_addressRepository.getTransBPAddr(bp_id);

			Type listType = new TypeToken<Trans_bussiness_partner_addressDTO>() {}.getType();

			Trans_bussiness_partner_addressDTO suppAddress= new ModelMapper().map(modelList,listType);
			return suppAddress;
	 }
	 
	public List<Trans_bussiness_partner_address_dtlsDTO> getTransBPAddrDtls(String bp_id)
	 {
		 	List<Trans_bussiness_partner_address_dtls> modelList=trans_bussiness_partner_address_dtlsRepository.getTransBPAddrDtls(bp_id);
			
			Type listType=new TypeToken<List<Trans_bussiness_partner_address_dtlsDTO>>() {}.getType();
			
			List<Trans_bussiness_partner_address_dtlsDTO> trnsCode=new ModelMapper().map(modelList,listType);
			
			return trnsCode; 
	 }
	
	public Trans_bussiness_partner_tds getTranstds(String bp_id) 
	{
		return trans_bussiness_partner_tdsRepository.getTranstds(bp_id);
	}
	 
	public Trans_bussiness_partner_statutoryDTO getTransBPStatu(String bp_id)
	 {
		 Trans_bussiness_partner_statutory modelList=trans_bussiness_partner_statutoryRepository.getTransBPStatu(bp_id);

			Type listType = new TypeToken<Trans_bussiness_partner_statutoryDTO>() {}.getType();

			Trans_bussiness_partner_statutoryDTO suppAddress= new ModelMapper().map(modelList,listType);
			return suppAddress;
	 }
	 
	public List<Trans_bussiness_partner_docDTO> getTransBPDocs(String bp_id)
	 {
		 List<Trans_bussiness_partner_doc> modelList=trans_bussiness_partner_docRepository.getTransBPDocs(bp_id);
			
			Type listType=new TypeToken<List<Trans_bussiness_partner_docDTO>>() {}.getType();
			
			List<Trans_bussiness_partner_docDTO> trnsCode=new ModelMapper().map(modelList,listType);
			
			return trnsCode; 
	 }
	 
	public List<Trans_bussiness_partner_vehicle_dtlsDTO> getTransBPVD(String bp_id)
		{
			List<Trans_bussiness_partner_vehicle_dtls> modelList=trans_bussiness_partner_vehicle_dtlsRepository.getTransBPVD(bp_id);
			
				
			Type listType=new TypeToken<List<Trans_bussiness_partner_vehicle_dtlsDTO>>() {}.getType();
			
			List<Trans_bussiness_partner_vehicle_dtlsDTO> trnsCode=new ModelMapper().map(modelList,listType);
			
			return trnsCode;
		}
	 
	public MessageStatusDTO chkTransNameStatus(String tname){
				String result=trans_bussiness_partnerRepository.chkTransNameStatus(tname);
				
				if(result == null) {result="NOTEXIST";}
				else {result="EXIST";}
				
				Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
				MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
				grpStatus.setGroup_name(result);
				
				return grpStatus;
			}
	
	public StatusDTO chkTransBpCodeStatus(String code){
		String result="";
		
		if(trans_bussiness_partnerRepository.chkTransBpCodeStatus(code).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
	
	public StatusDTO checkTransporterMasterUsage(String code)
 	{
 		String result=trans_bussiness_partnerRepository.checkTransporterMasterUsage(code);
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
 	}
 	
	public List<Map<String, Object>> getTransporterBussinessPartnerFast(){

 		List<Map<String, Object>> val=trans_bussiness_partnerRepository.getTransporterBussinessPartnerFast(true);
 		
		return val;
	}
	
	@Transactional
	public Trans_bussiness_partner accountPostingTransporter(Long id)
	{
		String suppliername="",alliasname="",address="",statename="",panno="",registered="",type="",pincode="",ifsccode="",accountno="",gstn="",openingbal="";
		
		Optional<Trans_bussiness_partner> op=this.trans_bussiness_partnerRepository.findById(id);
		suppliername=op.get().getBp_name();
		type=op.get().getGroup_type_name();
		
		//Trans_bussiness_partner_address //address,statename,pincode
		//Trans_bussiness_partner_address_dtls//mobileno
		//Trans_bussiness_partner_statutory //,panno,registered,gstn
		
		Trans_bussiness_partner_address trans_address=trans_bussiness_partner_addressRepository.getTransBPAddr(op.get().getBp_Id());
		
		if(Utility.isNullOrEmpty(trans_address.getAdd1())) 
		{
			address = trans_address.getAdd1();
		}
		if(Utility.isNullOrEmpty(trans_address.getAdd2())) 
		{
			address = trans_address.getAdd2();
		}
		if(Utility.isNullOrEmpty(trans_address.getAdd3())) 
		{
			address = trans_address.getAdd3();
		}
		
		if(Utility.isNullOrEmpty(trans_address.getState())) 
		{
			statename = trans_address.getState();
		}
		
		if(Utility.isNullOrEmpty(trans_address.getState())) 
		{
			statename = trans_address.getState();
		}
		
		if(Utility.isNullOrEmpty(String.valueOf(trans_address.getPincode()))) 
		{
			System.out.println("pincode "+trans_address.getPincode());
			pincode=String.valueOf(trans_address.getPincode());
			if(pincode.compareToIgnoreCase("null")==0 || pincode.compareToIgnoreCase("")==0)
			{
				pincode="";
			}
		}
		else 
		{
			pincode="";
		}
		
		List<Trans_bussiness_partner_address_dtls> addressDetails= trans_bussiness_partner_address_dtlsRepository.getTransBPAddrDtls(op.get().getBp_Id());
		
		// NEW for contact Person
		/*String email="",phone="",fax="",contactperson="";
		
		if(Utility.isNullOrEmpty(String.valueOf(addressDetails.get(0).getEmail()))) 
		{
			email=String.valueOf(addressDetails.get(0).getEmail());
		}
		if(Utility.isNullOrEmpty(String.valueOf(addressDetails.get(0).getPhone()))) 
		{
			phone=String.valueOf(addressDetails.get(0).getPhone());
		}
		if(Utility.isNullOrEmpty(String.valueOf(addressDetails.get(0).getFax()))) 
		{
			fax=String.valueOf(addressDetails.get(0).getFax());
		}
		if(Utility.isNullOrEmpty(String.valueOf(addressDetails.get(0).getContact_person()))) 
		{
			contactperson=String.valueOf(addressDetails.get(0).getContact_person());
		}*/
		
		//OLD without contact person
		/*String mobileno[]=new String[addressDetails.size()];
		for(int i=0;i<addressDetails.size();i++) 
         {
		  
		  if(Utility.isNullOrEmpty(String.valueOf(addressDetails.get(i).getMobile()))) 
			{
			  	mobileno[i]=String.valueOf(addressDetails.get(i).getMobile());
				if(mobileno[i].compareToIgnoreCase("null")==0 || mobileno[i].compareToIgnoreCase("")==0)
				{
					mobileno[i]="";
				}
			}
			else 
			{
				mobileno[i]="";
			}
         }*/
		
		Trans_bussiness_partner_statutory trans_statutory=trans_bussiness_partner_statutoryRepository.getTransBPStatu(op.get().getBp_Id());
		String registerstatus="";
		
		if(Utility.isNullOrEmpty(trans_statutory.getPan_no())) 
		{
			panno = trans_statutory.getPan_no();
		}
		if(Utility.isNullOrEmpty(trans_statutory.getGst_no())) 
		{
			gstn=trans_statutory.getGst_no();
			
			if(gstn.compareToIgnoreCase("null")==0 || gstn.compareToIgnoreCase("")==0) 
			{
				registerstatus="UnRegistered";
			}
			else {
				gstn = trans_statutory.getGst_no();
				registerstatus="Registered";
			}
		}
		else 
		{
			registerstatus="UnRegistered";
		}
		
		String output;
		Tallyrequest_TransporterMaster tally=new Tallyrequest_TransporterMaster();
		try  
		{
			if(suppliername.contains("&"))
			{
				suppliername=suppliername.replaceAll("&","&amp;");
			}
			if(address.contains("&"))
			{
				address=address.replaceAll("&","&amp;");
			}
			
			Tallyrequest_Openingbalanceofmaster opening =new Tallyrequest_Openingbalanceofmaster();
			try  
			{
				openingbal=opening.SendToTally(suppliername);
				output = tally.SendToTally(suppliername,alliasname,address,statename,pincode,panno,registered,type,
						String.valueOf(addressDetails.get(0).getMobile()),ifsccode,accountno,openingbal,gstn);
		        
				//String [] splitoutput = output.split("\\|\\|");
			
				System.out.println("output::"+output);
				String [] splitoutput = output.split("\\|\\|");
				System.out.println("splitoutput[0] "+splitoutput[0] +" /splitoutput[1] / " + splitoutput[1]+"/"+id);
				
				/*int export=0;
				if(Integer.parseInt(splitoutput[1])==1) 
				{
					export=1;
				}
				if(Integer.parseInt(splitoutput[2])==1) 
				{
					export=1;
				}
				*/
				System.out.println("ID "+id +" /splitoutput[0] / " + splitoutput[0]+"///"+Integer.parseInt(splitoutput[1]));
				
				trans_bussiness_partnerRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
			}
			catch(Exception a)
			{
			
				System.out.println(a);
			}
			
		}
		catch(Exception e)
		{
		
			System.out.println(e);
		}
	
		Optional<Trans_bussiness_partner> op1=this.trans_bussiness_partnerRepository.findById(id);//static details 
		//System.out.println(op.get());
		
		return op1.get();
	}
	  
}
