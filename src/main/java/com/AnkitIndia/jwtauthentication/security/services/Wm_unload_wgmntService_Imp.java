package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.Utility.FileUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_other_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Weighment_doc;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt_dtls;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.OtherItemMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.OtherPartyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_itm_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_item_dtls_for_jobworkRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_wgmntRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmntDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmnt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmnt_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Terminatekata;

@Service
@Repository
public class Wm_unload_wgmntService_Imp implements Wm_unload_wgmntService {
	
	@Autowired
	Wm_unload_wgmntRepository wm_unload_wgmntRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	Pur_return_approval_noteRepository pur_return_approval_noteRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Wm_unload_advice_item_dtlsRepository wm_unload_advice_item_dtlsRepository;
	
	@Autowired
	Wm_loading_advice_itm_dtlsRepository wm_loading_advice_itm_dtlsRepository;
	
	@Autowired
	OtherItemMasterRepository otherItemMasterRepository;
	
	@Autowired
	OtherPartyMasterRepository otherPartyMasterRepository;
	
	@Autowired
	Wm_unload_advice_item_dtls_for_jobworkRepository wm_unload_advice_item_dtls_for_jobworkRepository;
	
	public SequenceIdDTO getWeighmentSequenceId(String prefix,String orderdate,String weight)
	{
		int slno=0;String prefix1="",prefix2="";
		//String dt=Utility.convertDate(orderdate);
		if(prefix.compareTo("Purchase Order")==0)
		{
			prefix2="PO";
		}
		if(prefix.compareTo("Stock Transfer Unloading")==0) {prefix2="STU";}
		if(prefix.compareTo("Sale")==0) {prefix2="SO";}
		if(prefix.compareTo("Stock Transfer Loading")==0) {prefix2="STL";}
		if(prefix.compareTo("Sales Return")==0) {prefix2="SR";}
		if(prefix.compareTo("Purchase Return")==0) {prefix2="PR";}
		
		if(weight.compareTo("weight2")==0) {
			prefix1="WE-"+prefix2+"-2ND";
		}
		else {
			prefix1="WE-"+prefix2+"-1ST";weight="";
		}
		String sno=wm_unload_wgmntRepository.countWeighmentOrder(orderdate,weight);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		
		String date[] = orderdate.split("-");
		prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//System.err.println(prefix1);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix1,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SequenceIdDTO getWeighmentNumber(String vehicle,String orderdate)
	{
		int slno=0;String prefix1="",prefix2="";
		String weight="";
		//String dt=Utility.convertDate(orderdate);
		
		VehicleMaster vehiclewgmnt=vehicleMasterRepository.getVehicleDetails(vehicle);
		
		if(vehiclewgmnt.getWeighment_vehicle() ==1) 
		{
			prefix1="WE-MIS-2ND";
			weight="weight2";
		}
		else
		{
			prefix1="WE-MIS-1ST";
			weight="";
		}
		
		String sno=wm_unload_wgmntRepository.countWeighmentOrder(orderdate,weight);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		
		String date[] = orderdate.split("-");
		prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//System.err.println(prefix1);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix1,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SequenceIdDTO getWeighmentSequenceIdnew(String prefix,String orderdate,String weight,String unadviceid) 
	{
		int slno=0;String prefix1="",prefix2="";
		boolean pwitem=false;
		if(prefix.compareTo("Purchase Order")==0)
		{
			//unadviceid
			//if(wm_unload_adviceRepository.getUnloadId(unadviceid).getItem_subtype().compareToIgnoreCase("ITMT00008")==0)
			if(wm_unload_adviceRepository.getUnloadIdfast(unadviceid).compareToIgnoreCase("ITMT00008")==0)
			{
				prefix2="PW";
				pwitem=true;
			}
			else 
			{
				prefix2="PO";
				pwitem=false;
			}
			
		}
		if(prefix.compareTo("Stock Transfer Unloading")==0) {prefix2="STU";}
		if(prefix.compareTo("Sale")==0) {prefix2="SO";}
		if(prefix.compareTo("Stock Transfer Loading")==0) {prefix2="STL";}
		if(prefix.compareTo("Sales Return")==0) {prefix2="SR";}
		if(prefix.compareTo("Purchase Return")==0) {prefix2="PR";}
		
		if(weight.compareTo("weight2")==0) {
			prefix1="WE-"+prefix2+"-2ND";
		}
		else {
			prefix1="WE-"+prefix2+"-1ST";weight="";
		}
		String sno="";
		if(pwitem == true) 
		{
			 sno=wm_unload_wgmntRepository.countWeighmentOrdernew(orderdate,weight,"PW");
		}
		else 
		{
			 sno=wm_unload_wgmntRepository.countWeighmentOrder(orderdate,weight);
		}
		
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		
		String date[] = orderdate.split("-");
		prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix1,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
		
	}
	
	public SequenceIdDTO getWeighmentSequenceIdnewloading(String prefix,String orderdate,String weight,String adviceid) 
	{
		int slno=0;String prefix1="",prefix2="";
		boolean pwitem=false;
		
		if(prefix.compareTo("Purchase Order")==0)
		{
			prefix2="PO";
		}
		
		if(prefix.compareTo("Stock Transfer Unloading")==0) {prefix2="STU";}
		if(prefix.compareTo("Sale")==0)
		{
			
			
			if(wm_loading_adviceRepository.getloadIdfast(adviceid,"SW")==0)
			{
				
				prefix2="SO";
				pwitem=false;
			}
			else 
			{
				prefix2="SW";
				pwitem=true;
			}
		}
		if(prefix.compareTo("Stock Transfer Loading")==0) {prefix2="STL";}
		if(prefix.compareTo("Sales Return")==0) {prefix2="SR";}
		if(prefix.compareTo("Purchase Return")==0) {prefix2="PR";}
		
		if(weight.compareTo("weight2")==0) {
			prefix1="WE-"+prefix2+"-2ND";
		}
		else {
			prefix1="WE-"+prefix2+"-1ST";weight="";
		}
		String sno="";
		if(pwitem == true) 
		{
			 sno=wm_unload_wgmntRepository.countWeighmentOrdernew(orderdate,weight,"SW");
		}
		else 
		{
			 sno=wm_unload_wgmntRepository.countWeighmentOrder(orderdate,weight);
		}
		
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		
		String date[] = orderdate.split("-");
		prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix1,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
		
	}
	
	@Transactional
	public Wm_unload_wgmnt save(Wm_unload_wgmnt wgmnt,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		boolean pwitem=false ;
		long slno=0;String prefix="WULW";
		if(wm_unload_wgmntRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(wm_unload_wgmntRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		wgmnt.setWgment_id(gen_sno);
	
		long nslno=0;String prefix1="",prefix2="";
		
		wgmnt.setWgmentdate(wgmnt.getWgment_date());
		wgmnt.setWgmentno(wgmnt.getWgment_no());
		
		System.out.println(" Check 1st : : "+wgmnt.getAdvice());
		
		List<Wm_unload_wgmnt_dtls> weightlist = new ArrayList<Wm_unload_wgmnt_dtls>();
		weightlist.addAll(wgmnt.getWm_unload_wgmnt_dtls());
		
		if(wgmnt.getWgment_for().compareTo("Purchase Order")==0)
		{
			if(wm_unload_adviceRepository.getUnloadId(weightlist.get(0).getAdvice()).getItem_subtype().compareToIgnoreCase("ITMT00008")==0)
			{
				prefix2="PW";
				pwitem=true;
			}
			else 
			{
				prefix2="PO";
				pwitem=false;
			}
			if(wgmnt.getWeight2().compareTo("weight2")==0)
			{
				
				prefix1="WE-"+prefix2;
			} 
			else
			{
				prefix1="WE-"+prefix2+"-1ST";wgmnt.setWeight2("");
			}
			String tsno="";
			if(pwitem == true) 
			{
				tsno=wm_unload_wgmntRepository.countWeighmentOrdernew(wgmnt.getWgment_date(),wgmnt.getWeight2(),"PW");
			}
			else 
			{
				tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
			}
			
			//String tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = wgmnt.getWgment_date().split("-");
			prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(prefix1,nslno);
			wgmnt.setWgment_no(gen_tslno);
		}
		
		//if(wgmnt.getWgment_for().compareTo("Sale")==0) {prefix2="SO";}
		else if(wgmnt.getWgment_for().compareTo("Sale")==0)
		{
			
			
			if(wm_loading_adviceRepository.getloadIdfast(weightlist.get(0).getAdvice(),"SW")==0)
			{
				
				prefix2="SO";
				pwitem=false;
			}
			else 
			{
				prefix2="SW";
				pwitem=true;
			}
			if(wgmnt.getWeight2().compareTo("weight2")==0)
			{
				
				prefix1="WE-"+prefix2;
			} 
			else
			{
				prefix1="WE-"+prefix2+"-1ST";wgmnt.setWeight2("");
			}
			String tsno="";
			if(pwitem == true) 
			{
				tsno=wm_unload_wgmntRepository.countWeighmentOrdernew(wgmnt.getWgment_date(),wgmnt.getWeight2(),"SW");
			}
			else 
			{
				tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
			}
			
			//String tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = wgmnt.getWgment_date().split("-");
			prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(prefix1,nslno);
			wgmnt.setWgment_no(gen_tslno);
		}
		
		else 
		{
			
			if(wgmnt.getWgment_for().compareTo("Stock Transfer Unloading")==0) {prefix2="STU";}
			if(wgmnt.getWgment_for().compareTo("Stock Transfer Loading")==0) {prefix2="STL";}
			if(wgmnt.getWgment_for().compareTo("Sales Return")==0) {prefix2="SR";}
			if(wgmnt.getWgment_for().compareTo("Purchase Return")==0) {prefix2="PR";}
			
			if(wgmnt.getWeight2().compareTo("weight2")==0)
			{
				
				prefix1="WE-"+prefix2;
			} 
			else
			{
				prefix1="WE-"+prefix2+"-1ST";wgmnt.setWeight2("");
			}
			String tsno="";
			if(pwitem == true) 
			{
				tsno=wm_unload_wgmntRepository.countWeighmentOrdernew(wgmnt.getWgment_date(),wgmnt.getWeight2(),"PW");
			}
			else 
			{
				tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
			}
			
			//String tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = wgmnt.getWgment_date().split("-");
			prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(prefix1,nslno);
			wgmnt.setWgment_no(gen_tslno);
		}
	
		/*if(wgmnt.getWgment_for().compareTo("Stock Transfer Loading")==0) {prefix2="STL";}
		if(wgmnt.getWgment_for().compareTo("Sales Return")==0) {prefix2="SR";}
		if(wgmnt.getWgment_for().compareTo("Purchase Return")==0) {prefix2="PR";}
		
		if(wgmnt.getWeight2().compareTo("weight2")==0)
		{
			
			prefix1="WE-"+prefix2;
		} 
		else
		{
			prefix1="WE-"+prefix2+"-1ST";wgmnt.setWeight2("");
		}
		String tsno="";
		if(pwitem == true) 
		{
			tsno=wm_unload_wgmntRepository.countWeighmentOrdernew(wgmnt.getWgment_date(),wgmnt.getWeight2(),"PW");
		}
		else 
		{
			tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
		}
		
		//String tsno=wm_unload_wgmntRepository.countWeighmentOrder(wgmnt.getWgment_date(),wgmnt.getWeight2());
				
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = wgmnt.getWgment_date().split("-");
		prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(prefix1,nslno);
		wgmnt.setWgment_no(gen_tslno);
		*/
		//System.err.println("Last:>>>"+wgmnt.getWgment_no());
		/*End checking transaction no for unique */
		
		
		// Weightment No Alternate Starts
		
		String snov=wm_unload_wgmntRepository.countVechicle();
		long slnov=0;
		if(snov != null )
		{
			slnov=Integer.parseInt(snov);
		}
		String datev[] = wgmnt.getWgment_date().split("-");
		prefix1="WE"+"-"+datev[0]+datev[1]+datev[2];
		String gen_tslnov=UniqueIDTransaction.uniqueId4(prefix1,slnov);
		wgmnt.setWgment_no_alt(gen_tslnov);
		System.out.println(" Check ALTWGT 1st NO : : "+wgmnt.getWgment_no_alt());
		
		// Weightment No Alternate Ends
				
		if(wgmnt.getWeight2().compareToIgnoreCase("weight2")==0) 
		{
		}  
		
		
		else 
		{
			if(wgmnt.getVehicle_ref_name().compareTo("Load Advice")==0) 
			{
				wgmnt.setGw_time("");
				wgmnt.setGw_date(null);
			}
			else 
			{
				wgmnt.setTw_time("");
				wgmnt.setTw_date(null);
			}
		}
		
			Set<Wm_unload_wgmnt_dtls> weightSet = new HashSet<Wm_unload_wgmnt_dtls>();
			weightSet.addAll(wgmnt.getWm_unload_wgmnt_dtls());
			for(Wm_unload_wgmnt_dtls wgmnt_dtls : weightSet)
			{
				//System.out.println("hello outer :: "+gen_sno +" / " +wgmnt.getWgment_for());
				if(wgmnt.getWgment_for().compareTo("Purchase Order")==0 || wgmnt.getWgment_for().compareTo("Stock Transfer Unloading")==0 || wgmnt.getWgment_for().compareTo("Sales Return")==0) 
				{
					
					Wm_unload_advice advDtls=wm_unload_adviceRepository.getUnloadDetails(wgmnt_dtls.getAdvice());
					
					if(advDtls.getUnload_status() ==0 && advDtls.getWeighment_status() ==0) 
					{
						wm_unload_adviceRepository.updateStatus(wgmnt_dtls.getAdvice(),gen_sno);//Unload_status 1 and  Weighment_status 1 
						vehicleMasterRepository.updateStatus(wgmnt.getVehicle_id(),true);
						wm_unload_adviceRepository.updateLoadUnloadStatus(wgmnt_dtls.getAdvice(),gen_sno);
					}
					else if(advDtls.getUnload_status() ==1 && advDtls.getWeighment_status() ==1)
					{
						
						wm_unload_adviceRepository.updateUnloadStatus(wgmnt_dtls.getAdvice(),gen_sno);//Unload_status 1 and  Weighment_status 2 
						vehicleMasterRepository.updateStatus(wgmnt.getVehicle_id(),false);
						vehicleMasterRepository.updateVehicleWeighmentLocation(wgmnt.getVehicle_id(),"NA");
						wm_unload_adviceRepository.updateAfterLoadUnloadStatus(wgmnt_dtls.getAdvice(),gen_sno);
					}
					else
					{
						//System.out.println("Not Match !!!");  
					}
					
					if(wgmnt.getWgment_for().compareTo("Sales Return")==0) 
					{
						//System.out.println("advDtls.getReferance_id():: "+advDtls.getReferance_id());
						if(advDtls.getUnload_status() ==0 && advDtls.getWeighment_status() ==0 && advDtls.getReferance_id().substring(0, 2).compareTo("RA")==0) 
						{
							return_approval_noteRepository.updateStatus(advDtls.getReferance_id(),gen_sno);
						}
						else if(advDtls.getUnload_status() ==1 && advDtls.getWeighment_status() ==1 && advDtls.getReferance_id().substring(0, 2).compareTo("RA")==0)
						{
							return_approval_noteRepository.updateAppStatus(advDtls.getReferance_id(),gen_sno);
						}
						else
						{
							//System.out.println("Not Match !!!");  
						}
					}
				}
				else if(wgmnt.getWgment_for().compareTo("Sale")==0 || wgmnt.getWgment_for().compareTo("Stock Transfer Loading")==0 || wgmnt.getWgment_for().compareTo("Purchase Return")==0)
				{
					//System.out.println(" 1234 :: "+wgmnt_dtls.getAdvice());
					Wm_loading_advice loadingDtls=wm_loading_adviceRepository.getLoadingDetails(wgmnt_dtls.getAdvice());
					
					if(loadingDtls.getLoading_status() ==0 && loadingDtls.getWeighment_status() ==0) 
					{
						//System.out.println("HELLO HERE1 ");
						wm_loading_adviceRepository.updateStatus(wgmnt_dtls.getAdvice(),gen_sno);
						//System.err.println("Vehicle: "+wgmnt.getVehicle_id());
						vehicleMasterRepository.updateStatus(wgmnt.getVehicle_id(),true);
						wm_loading_adviceRepository.updateLoadUnloadStatus(wgmnt_dtls.getAdvice(),gen_sno);
					}
					else if(loadingDtls.getLoading_status() ==1 && loadingDtls.getWeighment_status() ==1)
					{
						//System.out.println("HELLO HERE2 ");
						wm_loading_adviceRepository.updateLoadingStatus(wgmnt_dtls.getAdvice(),gen_sno);
						vehicleMasterRepository.updateStatus(wgmnt.getVehicle_id(),false);
						vehicleMasterRepository.updateVehicleWeighmentLocation(wgmnt.getVehicle_id(),"NA");
						wm_loading_adviceRepository.updateAfterLoadUnloadStatus(wgmnt_dtls.getAdvice(),gen_sno);
					}
					else {
						System.out.println("Not Match !!!");
					}
					
					if(wgmnt.getWgment_for().compareTo("Purchase Return")==0) {
						if(loadingDtls.getLoading_status() ==0 && loadingDtls.getWeighment_status() ==0 && loadingDtls.getReferance_id().substring(0, 2).compareTo("PR")==0) {
							pur_return_approval_noteRepository.updateStatus(loadingDtls.getReferance_id(),gen_sno);
						}
						else if(loadingDtls.getLoading_status() ==1 && loadingDtls.getWeighment_status() ==1 && loadingDtls.getReferance_id().substring(0, 2).compareTo("PR")==0) {
							pur_return_approval_noteRepository.updateAppStatus(loadingDtls.getReferance_id(),gen_sno);
						}
						else {
							System.out.println("Not Match !!!");  
						}
					}
				}
				else {
					System.out.println("Not Match !!!");
				}
				
			}
	
			if(wgmnt.getWeight1().compareTo("weight1") == 0 && wgmnt.getWeight2().compareTo("weight2") == 0)
			{
				wgmnt.setWe_status(true);
				//update query for unlaod 
				
				
				
				}
			else{
				wgmnt.setWe_status(false);
				}	 
			
			if(Utility.isNullOrEmpty(wgmnt.getVehicle_id())) 
			{
				
				wgmnt.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wgmnt.getVehicle_id()).getVehicle_no());
			}
			else 
			{
				wgmnt.setVehicle_no("None");
		    }
			
			
			
			wgmnt.setModified_type("INSERTED");
			wgmnt.setInserted_by(userRepository.getUserDetails(wgmnt.getUsername()).getName());
			wgmnt.setInserted_on(ldt);
			wgmnt.setUpdated_by("NA");
			wgmnt.setUpdated_on(ldt);
			wgmnt.setDeleted_by("NA");
			wgmnt.setDeleted_on(ldt);
			
			double mat_weight=0.00;
			String advice_id="";
			
			//Dynamic
			Set<Wm_unload_wgmnt_dtls> wgSet = new HashSet<Wm_unload_wgmnt_dtls>();
			wgSet.addAll(wgmnt.getWm_unload_wgmnt_dtls());
			for(Wm_unload_wgmnt_dtls wgmnt_dtls : wgSet)
			{
				
				if(wgmnt.getWgment_for().compareTo("Purchase Order")==0 || wgmnt.getWgment_for().compareTo("Stock Transfer Unloading")==0) 
				{
					wgmnt_dtls.setCustomer("CBP00000");
					//System.out.println(" unload id  " + wgmnt_dtls.getAdvice());
					//previously done changed by tuhin on 28-02-2024 as in po flow no data are falls under Wm_unload_advice_item_dtls_for_jobwork ,
					//bcz Wm_unload_advice_item_dtls_for_jobwork table is done for sale return so changes are being made
					/*if(wm_unload_adviceRepository.getUnloadId(wgmnt_dtls.getAdvice()).getItem_subtype().compareToIgnoreCase("ITMT00009")==0)
					{
						mat_weight+=wm_unload_advice_item_dtls_for_jobworkRepository.getTotalJobItem(wgmnt_dtls.getAdvice());
						
					}
					else
					{
						mat_weight+=wm_unload_advice_item_dtlsRepository.gettotalmat_weight(wgmnt_dtls.getAdvice());
					}
					*/
					
					mat_weight+=wm_unload_advice_item_dtlsRepository.gettotalmat_weight(wgmnt_dtls.getAdvice());
					
					
					wgmnt.setTotalweight(mat_weight);
					wgmnt.setPartyname(wgmnt_dtls.getSupplier());
					//System.out.println("PURCHASE :: "+mat_weight);
				}
				if(wgmnt.getWgment_for().compareTo("Sales Return")==0) 
				{
					wgmnt_dtls.setSupplier("SBP00000");
					
					if(wm_unload_adviceRepository.getUnloadId(wgmnt_dtls.getAdvice()).getItem_subtype().compareToIgnoreCase("ITMT00009")==0)
					{
						mat_weight+=wm_unload_advice_item_dtls_for_jobworkRepository.getTotalJobItem(wgmnt_dtls.getAdvice());
					}
					else
					{
						mat_weight+=wm_unload_advice_item_dtlsRepository.gettotalmat_weight(wgmnt_dtls.getAdvice());
					}
					
					wgmnt.setTotalweight(mat_weight);
					wgmnt.setPartyname(wgmnt_dtls.getCustomer());
					//System.out.println("PURCHASE :: "+mat_weight);
				}
				if(wgmnt.getWgment_for().compareTo("Sale")==0 || wgmnt.getWgment_for().compareTo("Stock Transfer Loading")==0 || wgmnt.getWgment_for().compareTo("Purchase Return")==0)
				{
					wgmnt_dtls.setSupplier("SBP00000");
					mat_weight+=wm_loading_advice_itm_dtlsRepository.gettotalmat_weightjobwork(wgmnt_dtls.getAdvice());
					wgmnt.setTotalweight(mat_weight);
					wgmnt.setPartyname(wgmnt_dtls.getCustomer());
				//	System.out.println("SALES :: "+mat_weight);
				}
				advice_id=wgmnt_dtls.getAdvice();
				/*if(wgmnt.getWgment_for().compareTo("Sale")==0) 
				{
					wm_loading_adviceRepository.updatemultipleadviceinsinglekata(wgmnt_dtls.getAdvice());
			    }*/ 
				
				wgmnt.setAdvice(wgmnt_dtls.getAdvice());
				wgmnt.setAdvice_no(wgmnt_dtls.getAdvice_no());
				
				
				wgmnt_dtls.setWgment_id(gen_sno);
				wgmnt_dtls.setWgment_no(wgmnt.getWgment_no());
				wgmnt_dtls.setCompany_id(wgmnt.getCompany_id());
				wgmnt_dtls.setFin_year(wgmnt.getFin_year());
				wgmnt_dtls.setModified_type("INSERTED");
				wgmnt_dtls.setInserted_by(wgmnt.getInserted_by());
				wgmnt_dtls.setInserted_on(wgmnt.getInserted_on());
				wgmnt_dtls.setUpdated_by("NA");
				wgmnt_dtls.setUpdated_on(ldt);
				wgmnt_dtls.setDeleted_by("NA");
				wgmnt_dtls.setDeleted_on(ldt);
			
			}
			wgmnt.setWm_unload_wgmnt_dtls(wgSet);
		
			// start here 23122023
			
			System.out.println(" Check ad: : "+wgmnt.getAdvice());
			
			if(wgmnt.getWeight2().compareTo("weight2")==0)
			{
				// Weightment No Alternate same Vehicle start
				System.out.println(" Check ad ALTWGT : : "+wgmnt.getAdvice());
				
				if(wgmnt.getWeight2().compareToIgnoreCase("weight2")==0) 
				{
					wgmnt.setWgment_no_alt(wm_unload_wgmntRepository.getAltWgtNo(wgmnt.getAdvice()));
				} // Weightment No Alternate same Vehicle
				
				System.out.println(" Check ALTWGT NO : : "+wgmnt.getWgment_no_alt());
				
				if(wgmnt.getWgment_for().compareTo("Sale")==0)
				{
					System.out.println(" Check : : "+wgmnt.getAdvice());
					Wm_loading_advice wm_loading_advice=wm_loading_adviceRepository.getLoadingDetails(wgmnt.getAdvice());
					//System.out.println(" Check : : "+wgmnt.getAdvice());
					//System.out.println(" YA YA  : : "+wm_loading_advice.isJobwork());
					if(wm_loading_advice.isJobwork()==true)
					{
						if(wm_loading_advice.isLooseitem()==true)					
						{
							Wm_loading_advice_Item_Dtls_for_jobwork dtls=wm_loading_adviceRepository.jwDtls(wgmnt.getAdvice());
							wm_loading_adviceRepository.wm_advJobItemupdate(wgmnt.getAdvice(),"UPDATED");
							
							Wm_loading_advice_Item_Dtls_for_jobwork jobwork=new Wm_loading_advice_Item_Dtls_for_jobwork();
							
							jobwork.setAdvice_id(wgmnt.getAdvice());
							
							//jobwork.setItem_qty(wgmnt.getNet_weight()); //Orginal Wgmnt Net Wgt
							
							jobwork.setItem_qty(wgmnt.getNet_weight_bulker());
							
							jobwork.setAdvice_no(dtls.getAdvice_no());						
							
							jobwork.setOrder_id(dtls.getOrder_id());
							jobwork.setItem_uom(dtls.getItem_uom());
							jobwork.setJob_hsn(dtls.getJob_hsn());
							jobwork.setJob_item(dtls.getJob_item());
							jobwork.setJob_item_name(dtls.getJob_item_name());
							jobwork.setJob_packing(dtls.getJob_packing());
							jobwork.setJob_packing_name(dtls.getJob_packing_name());
							jobwork.setPack_qty(dtls.getPack_qty());
							jobwork.setPack_uom(dtls.getPack_uom());
							jobwork.setPrice_based_on(dtls.getPrice_based_on());
							jobwork.setSlno(dtls.getSlno());
							jobwork.setTolerance(dtls.getTolerance());
							jobwork.setJob_tolerance_qty(dtls.getJob_tolerance_qty());
							
							jobwork.setCompany_id(dtls.getCompany_id());
							jobwork.setUsername(dtls.getUsername());
							jobwork.setFin_year(dtls.getFin_year());
							jobwork.setModified_type("INSERTED");
							jobwork.setInserted_by(dtls.getInserted_by());
							jobwork.setInserted_on(dtls.getInserted_on());
							jobwork.setUpdated_by("NA");
							jobwork.setUpdated_on(ldt);
							jobwork.setDeleted_by("NA");
							jobwork.setDeleted_on(ldt);
							
							wgmnt.setWm_loading_advice_Item_Dtls_for_jobwork(jobwork);
						}
					}
					else
					{
						if(wm_loading_advice.isLooseitem()==true)					
						{
							Wm_loading_advice_itm_dtls dtls=wm_loading_advice_itm_dtlsRepository.getLoadingItemDtls(wgmnt.getAdvice());
							wm_loading_advice_itm_dtlsRepository.wm_loading_advice_itm_dtlsupdate(wgmnt.getAdvice(),"UPDATED");
							System.out.println("Yes ");
							Wm_loading_advice_itm_dtls itemDtls=new Wm_loading_advice_itm_dtls();
							System.out.println("Yes 2 ");
							itemDtls.setAdvice_id(wgmnt.getAdvice());
							
							//itemDtls.setQuantity(wgmnt.getNet_weight());   //Orginal Wgmnt Net Wgt
							//itemDtls.setMat_wt(wgmnt.getNet_weight());	//Orginal Wgmnt Net Wgt
							
							itemDtls.setQuantity(wgmnt.getNet_weight_bulker());
							itemDtls.setMat_wt(wgmnt.getNet_weight_bulker());
							
							itemDtls.setAdvice_no(dtls.getAdvice_no());

							itemDtls.setAcc_norms(dtls.getAcc_norms());
							itemDtls.setBase_qty(dtls.getBase_qty());
							itemDtls.setDiscount_amt(dtls.getDiscount_amt());
							itemDtls.setDiscount_rate(dtls.getDiscount_rate());
							itemDtls.setDiscount_type(dtls.getDiscount_type());
							itemDtls.setHsn_code(dtls.getHsn_code());
							itemDtls.setItem_code(dtls.getItem_code());
							itemDtls.setItem_name(dtls.getItem_name());
							itemDtls.setPacking(dtls.getPacking());
							itemDtls.setPacking_name(dtls.getPacking_name());
							itemDtls.setPrice(dtls.getPrice());
							itemDtls.setPrice_based_on(dtls.getPrice_based_on());
							itemDtls.setS_quantity(dtls.getS_quantity());
							itemDtls.setS_uom(dtls.getS_uom());
							itemDtls.setSl_no(dtls.getSl_no());
							itemDtls.setStack_rack(dtls.getStack_rack());
							itemDtls.setStack_rack_name(dtls.getStack_rack_name());
							itemDtls.setTax_code(dtls.getTax_code());
							itemDtls.setTax_rate(dtls.getTax_rate());
							itemDtls.setTolerance(dtls.getTolerance());
							itemDtls.setUom(dtls.getUom());
							itemDtls.setWarehouse(dtls.getWarehouse());
							itemDtls.setWarehouse_name(dtls.getWarehouse_name());
							itemDtls.setAlter_item_code(dtls.getAlter_item_code());
							itemDtls.setAlter_item_name(dtls.getAlter_item_name());
							itemDtls.setAlter_packing(dtls.getAlter_packing());
							itemDtls.setAlter_packing_name(dtls.getAlter_packing_name());
							itemDtls.setPricecal(dtls.getPricecal());
							itemDtls.setAlt_s_quantity(dtls.getAlt_s_quantity());
							itemDtls.setTolerance_qty(dtls.getTolerance_qty());
							
							if(dtls.getPrice_based_on().compareToIgnoreCase("Packing")==0)
							{
								itemDtls.setAmount(dtls.getAmount());
								itemDtls.setTax_amt(dtls.getTax_amt());		
								itemDtls.setTotal_amt(dtls.getTotal_amt());
							}
							else
							{
								double amt=0.00,tax_amt=0.00,total_amt=0.00;
								
								//amt=dtls.getPrice()*wgmnt.getNet_weight();	//Orginal Wgmnt Net Wgt Amt Calculation
								amt=dtls.getPrice()*wgmnt.getNet_weight_bulker();
								tax_amt=(dtls.getTax_rate()*amt)/100;
								total_amt=amt+tax_amt;
								
								itemDtls.setAmount(amt);
								itemDtls.setTax_amt(tax_amt);		
								itemDtls.setTotal_amt(total_amt);						
							}
							
							itemDtls.setOrder_id(dtls.getOrder_id());
							itemDtls.setCompany_id(dtls.getCompany_id());
							itemDtls.setUsername(dtls.getUsername());
							itemDtls.setFin_year(dtls.getFin_year());
							itemDtls.setModified_type("INSERTED");
							itemDtls.setInserted_by(dtls.getInserted_by());
							itemDtls.setInserted_on(dtls.getInserted_on());
							itemDtls.setUpdated_by(dtls.getUpdated_by());
							itemDtls.setUpdated_on(dtls.getUpdated_on());
							itemDtls.setDeleted_by("NA");
							itemDtls.setDeleted_on(ldt);
							
							wgmnt.setWm_loading_advice_itm_dtls(itemDtls);
						}
					}
				}
			}
			
			// end here 23122023
			
			//For Document Details start ........................................
			Set<Weighment_doc> wDoc=new HashSet<Weighment_doc>();
			wDoc.addAll(wgmnt.getWeighment_doc());
			int i=0;
			
			for(Weighment_doc wDocs:wDoc) 
			{
				//System.out.println(" hello files : "+files.length);
				//here start
				
				if(files.length > 0) {
					try {
						//System.out.println("files[i] :: "+i+" / "+files[i]);
							//fileUpload(files[i],gen_sno+"_");
						
						wDocs.setDoc_pdf(fileUpload(files[i],gen_sno+"_"));
							
						
					i++;
					}
					catch (IOException e)
					{
						System.out.println(e);
						}
					
				}
				//System.out.println("3 :: ");
				//here ends
				
				wDocs.setWgment_no(wgmnt.getWgment_no());
				wDocs.setWgment_id(gen_sno);
				wDocs.setCompany_id(wgmnt.getCompany_id());
				wDocs.setFin_year(wgmnt.getFin_year());
				wDocs.setModified_type("INSERTED");
				wDocs.setInserted_by(wgmnt.getInserted_by());
				wDocs.setInserted_on(wgmnt.getInserted_on());
				wDocs.setUpdated_by(wgmnt.getUpdated_by());
				wDocs.setUpdated_on(wgmnt.getUpdated_on());
				wDocs.setDeleted_by("NA");
				wDocs.setDeleted_on(ldt);
			}
			wgmnt.setWeighment_doc(wDoc);
			
			
			//For Document Details end ........................................
			
		return wm_unload_wgmntRepository.save(wgmnt);
	}
	
	@Transactional
	public Wm_unload_wgmnt otherSave(Wm_unload_wgmnt wgmnt,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		boolean pwitem=false ;
		int stat=0;
		long slno=0;String prefix="WULW";
		if(wm_unload_wgmntRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(wm_unload_wgmntRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		wgmnt.setWgment_id(gen_sno);
	
		long nslno=0;String prefix1="",prefix2="";
		
		wgmnt.setWgmentdate(wgmnt.getWgment_date());
		wgmnt.setWgmentno(wgmnt.getWgment_no());
		wgmnt.setWeighment_type("Other");
		
		if(wgmnt.getNoitemid().compareTo("0") !=0 && wgmnt.getNoitemid().compareTo("") !=0 && wgmnt.getNoitemid() !=null) {
			wgmnt.setNoitemname(otherItemMasterRepository.otherItemName(wgmnt.getNoitemid()).getNoitemname());
		}else {wgmnt.setNoitemname("None");}
		
		if(wgmnt.getNopartyid().compareTo("0") !=0 && wgmnt.getNopartyid().compareTo("") !=0 && wgmnt.getNopartyid() !=null) {
			wgmnt.setNopartyname(otherPartyMasterRepository.otherPartyName(wgmnt.getNopartyid()).getNopartyname());
		}else {wgmnt.setNopartyname("None");}
		
		if(wgmnt.getWeight1().compareTo("weight1") == 0 && wgmnt.getWeight2().compareTo("weight2") == 0)
		{
			wgmnt.setWe_status(true);
			}
		else{
			wgmnt.setWe_status(false);
			}
			
			if(Utility.isNullOrEmpty(wgmnt.getVehicle_id())) 
			{
				
				wgmnt.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wgmnt.getVehicle_id()).getVehicle_no());
			}
			else 
			{
				wgmnt.setVehicle_no("None");
		    }
			
			wgmnt.setModified_type("INSERTED");
			wgmnt.setInserted_by(userRepository.getUserDetails(wgmnt.getUsername()).getName());
			wgmnt.setInserted_on(ldt);
			wgmnt.setUpdated_by("NA");
			wgmnt.setUpdated_on(ldt);
			wgmnt.setDeleted_by("NA");
			wgmnt.setDeleted_on(ldt);
			System.out.println("subs::"+wgmnt.getWgment_no().substring(7,10));
			
			
			Vehicle_other_weighment_load_unload  vehicle_other_weighment_load_unload = new Vehicle_other_weighment_load_unload();
			
			vehicle_other_weighment_load_unload.setWeighment_id(wgmnt.getWgment_id());
			vehicle_other_weighment_load_unload.setVehicle_id(wgmnt.getVehicle_id());
			vehicle_other_weighment_load_unload.setVehicle_no(wgmnt.getVehicle_no());
			vehicle_other_weighment_load_unload.setRef_name("Other Weighment");
			vehicle_other_weighment_load_unload.setCompany_id(wgmnt.getCompany_id());
			vehicle_other_weighment_load_unload.setFin_year(wgmnt.getFin_year());
			vehicle_other_weighment_load_unload.setUsername(wgmnt.getUsername());
			vehicle_other_weighment_load_unload.setModified_type("INSERTED");
			vehicle_other_weighment_load_unload.setInserted_by(userRepository.getUserDetails(wgmnt.getUsername()).getName());
			vehicle_other_weighment_load_unload.setInserted_on(ldt);
			vehicle_other_weighment_load_unload.setUpdated_by("NA");
			vehicle_other_weighment_load_unload.setUpdated_on(ldt);
			vehicle_other_weighment_load_unload.setDeleted_by("NA");
			vehicle_other_weighment_load_unload.setDeleted_on(ldt);
			vehicle_other_weighment_load_unload.setGatepass_status("NA");
			vehicle_other_weighment_load_unload.setWeight_bridge_location(wgmnt.getWeight_bridge_location());
			
			if(wgmnt.getWgment_no().substring(7,10).compareToIgnoreCase("1ST")==0)
			{
				stat=1;
				vehicleMasterRepository.updateVehicleForWeighment(wgmnt.getVehicle_id(),stat);
				vehicleMasterRepository.updateVehicleWeighmentLocation(wgmnt.getVehicle_id(),wgmnt.getWeight_bridge_location());
				vehicle_other_weighment_load_unload.setWeighment_status(1);
			}
			else
			{
				stat=0;
				vehicleMasterRepository.updateVehicleForWeighment(wgmnt.getVehicle_id(),stat);
				vehicleMasterRepository.updateVehicleOtherLoadUnload(wgmnt.getVehicle_id(),2);
				vehicleMasterRepository.updateVehicleWeighmentLocation(wgmnt.getVehicle_id(),"NA");
				vehicle_other_weighment_load_unload.setWeighment_status(2);
			}
				
			
			wgmnt.setVehicle_other_weighment_load_unload(vehicle_other_weighment_load_unload);
			
			//Dynamic
			Set<Wm_unload_wgmnt_dtls> wgSet = new HashSet<Wm_unload_wgmnt_dtls>();
			wgSet.addAll(wgmnt.getWm_unload_wgmnt_dtls());
			for(Wm_unload_wgmnt_dtls wgmnt_dtls : wgSet)
			{
				
				wgmnt_dtls.setWgment_id(gen_sno);
				wgmnt_dtls.setWgment_no(wgmnt.getWgment_no());
				wgmnt_dtls.setCompany_id(wgmnt.getCompany_id());
				wgmnt_dtls.setFin_year(wgmnt.getFin_year());
				wgmnt_dtls.setModified_type("INSERTED");
				wgmnt_dtls.setInserted_by(wgmnt.getInserted_by());
				wgmnt_dtls.setInserted_on(wgmnt.getInserted_on());
				wgmnt_dtls.setUpdated_by("NA");
				wgmnt_dtls.setUpdated_on(ldt);
				wgmnt_dtls.setDeleted_by("NA");
				wgmnt_dtls.setDeleted_on(ldt);
			
			}
			wgmnt.setWm_unload_wgmnt_dtls(wgSet);
		
			//For Document Details start ........................................
			Set<Weighment_doc> wDoc=new HashSet<Weighment_doc>();
			wDoc.addAll(wgmnt.getWeighment_doc());
			int i=0;
			
			for(Weighment_doc wDocs:wDoc) 
			{
				//System.out.println(" hello files : "+files.length);
				//here start
				
				if(files.length > 0) {
					try {
						//System.out.println("files[i] :: "+i+" / "+files[i]);
							//fileUpload(files[i],gen_sno+"_");
						
						wDocs.setDoc_pdf(fileUpload(files[i],gen_sno+"_"));
							
						
					i++;
					}
					catch (IOException e)
					{
						System.out.println(e);
						}
					
				}
				//System.out.println("3 :: ");
				//here ends
				
				wDocs.setWgment_no(wgmnt.getWgment_no());
				wDocs.setWgment_id(gen_sno);
				wDocs.setCompany_id(wgmnt.getCompany_id());
				wDocs.setFin_year(wgmnt.getFin_year());
				wDocs.setModified_type("INSERTED");
				wDocs.setInserted_by(wgmnt.getInserted_by());
				wDocs.setInserted_on(wgmnt.getInserted_on());
				wDocs.setUpdated_by(wgmnt.getUpdated_by());
				wDocs.setUpdated_on(wgmnt.getUpdated_on());
				wDocs.setDeleted_by("NA");
				wDocs.setDeleted_on(ldt);
			}
			wgmnt.setWeighment_doc(wDoc);
			
			
			//For Document Details end ........................................
			
		return wm_unload_wgmntRepository.save(wgmnt);
	}
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPathWeighment);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileUtil.folderPathWeighment+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPathWeighment+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		      

		            return files_name;
	
	
	}
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	public String getBusinesspartnername (String wgtype,String wgid) 
	{
		String name="";
		//System.out.println("wgtype ::  "+wgtype+"wgid//"+wgid);
		//if(wgtype.compareToIgnoreCase("Purchase Order") == 0 || wgtype.compareToIgnoreCase("Sales Return") == 0 || wgtype.compareToIgnoreCase("Stock Transfer Unloading") == 0) 
		if(wgtype.compareToIgnoreCase("Purchase Order") == 0 )
		{
			
			//String suppliername=wm_unload_wgmntRepository.getSupplierCode(wgid);
			String suppliername=wm_unload_wgmntRepository.getSupplierCodeNew(wgid);
			//System.out.println("suppliername ::  "+suppliername);
			 name = supp_bussiness_partnerRepository.getSupplierThruBUstring(suppliername);	
			 
		}
		
		
		//if(wgtype.compareToIgnoreCase("Sale") == 0 || wgtype.compareToIgnoreCase("Purchase Return") == 0 || wgtype.compareToIgnoreCase("Stock Transfer Loading") == 0) 
		
		if(wgtype.compareToIgnoreCase("Sale") == 0 )
		{
			//System.out.println("wgid ::  "+wgid);
			//List<String> customername=wm_unload_wgmntRepository.getCustomererCode(wgid);
			List<String> customername=wm_unload_wgmntRepository.getCustomererCodeNew(wgid);
			//System.out.println("customername ::  "+customername);
			Cust_bussiness_partner newname = cust_bussiness_partnerRepository.getCustomerThruBUstringnew(customername.get(0));
			
			 name = newname.getCp_name();	
			
		}
		if(wgtype.compareToIgnoreCase("Sales Return") == 0 )
		{
			//String customername=wm_unload_wgmntRepository.getCustomererCode(wgid);
			//List<String> customername=wm_unload_wgmntRepository.getCustomererCode(wgid);
			List<String> customername=wm_unload_wgmntRepository.getCustomererCodeNew(wgid);
			//System.out.println("customername ::  "+customername);
			Cust_bussiness_partner newname = cust_bussiness_partnerRepository.getCustomerThruBUstringnew(customername.get(0));
			
			 name = newname.getCp_name();	
			
		}
		
		return name;
	}
	
	public List<Wm_unload_wgmnt> findAll()
	{
		List<Wm_unload_wgmnt> weighList=new ArrayList<Wm_unload_wgmnt>();
		weighList.addAll(wm_unload_wgmntRepository.findAllWeighments(true));
		
	//wgment_id	
		
		
		//wgment_for Sale  Purchase Order
		weighList.forEach((e)->{
			//System.out.println("unload:"+e.getVehicle_ref_name());
			if(e.getVehicle_ref_name().compareToIgnoreCase("Unload Advice")==0)
			{
				
					e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
					//e.setTw_remarks("");
				
			}
			if(e.getVehicle_ref_name().compareToIgnoreCase("Load Advice")==0)
			{
				
				if(e.getWgment_for().compareToIgnoreCase("Stock Transfer Loading")==0)
				{
					e.setTw_remarks("None");
				}
				else
				{
					e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
					//e.setTw_remarks("");
				}
			}
			e.setVehicle_id(vehicleMasterRepository.getVehicleDetails(e.getVehicle_id()).getVehicle_no());
			
		});
		
		/*for(int i=0;i<weighList.size();i++) {
			weighList.get(i).setVehicle_id(vehicleMasterRepository.getVehicleDetails(weighList.get(i).getVehicle_id()).getVehicle_no());
			
		}*/
		return weighList;
	}
	public Page<Wm_unload_wgmnt_Pagination_DTO> getUnloadWeightments_pagination(int page,int size)
	{

		  
		PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
		//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("wgmentdate").descending().and(Sort.by("wgmentno")).descending());
	    Page<Wm_unload_wgmnt> pageResult = wm_unload_wgmntRepository.findAll(pageRequest);
	   
	    List<Wm_unload_wgmnt_Pagination_DTO> advList = pageResult
	    	      .stream()
	    	      .map((Wm_unload_wgmnt wm_unload_wgmnt) -> new Wm_unload_wgmnt_Pagination_DTO(wm_unload_wgmnt.getId(),
	    	    		  wm_unload_wgmnt.getWgment_id(),
	    	    		  wm_unload_wgmnt.getWgment_no(),
	    	    		  wm_unload_wgmnt.getWgment_date(),
	    	    		  wm_unload_wgmnt.getTw_remarks(),
	    	    		  wm_unload_wgmnt.getWgment_for(),
	    	    		  wm_unload_wgmnt.getVehicle_no(),
	    	    		  wm_unload_wgmnt.getGross_weight(),
	    	    		  wm_unload_wgmnt.getTare_weight(),
	    	    		  wm_unload_wgmnt.getNet_weight(),
	    	    		  wm_unload_wgmnt.getWgment_charge(),
	    	    		  wm_unload_wgmnt.isWe_status(),
	    	    		  wm_unload_wgmnt.getModified_type(),
	    	    		  wm_unload_wgmnt.getVehicle_ref_name(),
	    	    		  wm_unload_wgmnt.getVehicle_id()
	    	    		  ))
	    	      //.filter(c -> c.getModified_type().equals("INSERTED") && c.isWe_status()==true).collect(Collectors.toList());
	              .filter(c -> c.getModified_type().equals("INSERTED") ).collect(Collectors.toList());
	    
	  //wgment_for Sale  Purchase Order
	    advList.forEach((e)->{
	  			
	  			if(e.getVehicle_ref_name().compareToIgnoreCase("Unload Advice")==0)
	  			{
	  			    e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
	  				// e.setTw_remarks("");
	  			}
	  			if(e.getVehicle_ref_name().compareToIgnoreCase("Load Advice")==0)
	  			{
	  				if(e.getWgment_for().compareToIgnoreCase("Stock Transfer Loading")==0)
	  				{
	  					e.setTw_remarks("None");
	  				}
	  				else
	  				{
	  					e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
	  					//e.setTw_remarks("");
	  				}
	  			}
	  			e.setVehicle_id(vehicleMasterRepository.getVehicleDetails(e.getVehicle_id()).getVehicle_no());
	  			
	  		});
	  		
	    return new PageImpl<>(advList, pageRequest, pageResult.getTotalElements());
  
	}
	
	public List<Map<String,Object>> searchWeighmentFast(String orderno,String fromdate, String todate,String customer_name1,String supplier_name1,String finyear)
	{
		List<Map<String,Object>> wm_unload_wgmnt =new ArrayList<Map<String,Object>>();
		String tablename="wm_unload_wgmnt",party_name="partyname",order_no="wgment_no",order_date="wgment_date";
		
		if((customer_name1.compareToIgnoreCase("")==0 || customer_name1.compareToIgnoreCase("0")==0) && (supplier_name1.compareToIgnoreCase("")==0 || supplier_name1.compareToIgnoreCase("0")==0))
		{
			String nobpartner="";
			wm_unload_wgmnt.addAll(wm_unload_wgmntRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,nobpartner,finyear));
		}
		if(customer_name1.compareToIgnoreCase("")!=0 )
		{
			wm_unload_wgmnt.addAll(wm_unload_wgmntRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,customer_name1,finyear));
		}
		if(supplier_name1.compareToIgnoreCase("")!=0)
		{
			wm_unload_wgmnt.addAll(wm_unload_wgmntRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,supplier_name1,finyear));
		}
		return wm_unload_wgmnt;
	}
	
	public List<Map<String,Object>> searchOtherWeighmentFast(String orderno,String fromdate, String todate,String party,String finyear)
	{
		List<Map<String,Object>> otherweighment =new ArrayList<Map<String,Object>>();
		String tablename="wm_unload_wgmnt",party_name="nopartyname",order_no="wgment_no",order_date="wgment_date";
		
		otherweighment.addAll(wm_unload_wgmntRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,party,finyear));
		
		return otherweighment;
	}
	
	public List<Wm_unload_wgmnt_Pagination_DTO> searchWeighment(String orderno,String fromdate, String todate,String customer_name1,String supplier_name1,String finyear)
	{
		List<Wm_unload_wgmnt> wm_unload_wgmnt =new ArrayList<Wm_unload_wgmnt>();
		//System.out.println("finyear :: "+finyear);
		String tablename="wm_unload_wgmnt",party_name="partyname",order_no="wgment_no",order_date="wgment_date";
		//System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" bus_partner1 "+bus_partner1+" finyear "+finyear);
		//wm_unload_wgmnt.addAll(wm_unload_wgmntRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,bus_partner1,finyear));
		
		if((customer_name1.compareToIgnoreCase("")==0 || customer_name1.compareToIgnoreCase("0")==0) && (supplier_name1.compareToIgnoreCase("")==0 || supplier_name1.compareToIgnoreCase("0")==0))
		{
			String nobpartner="";
			wm_unload_wgmnt.addAll(wm_unload_wgmntRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,nobpartner,finyear));
		}
		if(customer_name1.compareToIgnoreCase("")!=0 )
		{
			wm_unload_wgmnt.addAll(wm_unload_wgmntRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,customer_name1,finyear));
		}
		if(supplier_name1.compareToIgnoreCase("")!=0)
		{
			wm_unload_wgmnt.addAll(wm_unload_wgmntRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,supplier_name1,finyear));
		}
		
		
		//System.out.println(wm_unload_wgmnt.size());
		List<Wm_unload_wgmnt_Pagination_DTO> unAdvList = new ArrayList<Wm_unload_wgmnt_Pagination_DTO>();
		for(int i=0;i<wm_unload_wgmnt.size();i++) 
		{
		
			Wm_unload_wgmnt_Pagination_DTO sale= new Wm_unload_wgmnt_Pagination_DTO(
				  wm_unload_wgmnt.get(i).getId(),
  	    		  wm_unload_wgmnt.get(i).getWgment_id(),
  	    		  wm_unload_wgmnt.get(i).getWgment_no(),
  	    		  wm_unload_wgmnt.get(i).getWgment_date(),
  	    		  wm_unload_wgmnt.get(i).getTw_remarks(),
  	    		  wm_unload_wgmnt.get(i).getWgment_for(),
  	    		  wm_unload_wgmnt.get(i).getVehicle_no(),
  	    		  wm_unload_wgmnt.get(i).getGross_weight(),
  	    		  wm_unload_wgmnt.get(i).getTare_weight(),
  	    		  wm_unload_wgmnt.get(i).getNet_weight(),
  	    		  wm_unload_wgmnt.get(i).getWgment_charge(),
  	    		  wm_unload_wgmnt.get(i).isWe_status(),
  	    		  wm_unload_wgmnt.get(i).getModified_type(),
  	    		  wm_unload_wgmnt.get(i).getVehicle_ref_name(),
  	    		  wm_unload_wgmnt.get(i).getVehicle_id());
			
			unAdvList.add(sale);
		}
		
		unAdvList.forEach((e)->{
  			
  			if(e.getVehicle_ref_name().compareToIgnoreCase("Unload Advice")==0)
  			{
  			    e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
  			}
  			if(e.getVehicle_ref_name().compareToIgnoreCase("Load Advice")==0)
  			{
  				if(e.getWgment_for().compareToIgnoreCase("Stock Transfer Loading")==0)
  				{
  					e.setTw_remarks("None");
  				}
  				else
  				{
  					e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
  				}
  			}
  			e.setVehicle_id(vehicleMasterRepository.getVehicleDetails(e.getVehicle_id()).getVehicle_no());
  			
  		});
		
		List<Wm_unload_wgmnt_Pagination_DTO> finalData = unAdvList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
		/*		  .sorted(Comparator.comparing(Wm_unload_wgmnt_Pagination_DTO::getWgment_date).
						  thenComparingInt(
                        d -> d.getWgment_date().length() + d.getWgment_no().length())
                .thenComparing(Wm_unload_wgmnt_Pagination_DTO::getWgment_no).reversed())
                */
				  .sorted(Comparator.comparing(Wm_unload_wgmnt_Pagination_DTO::getId))
				  .collect(Collectors.toList());
		
		return finalData;
		
	}
	public Wm_unload_wgmnt findOne(long id)
	{
		Optional<Wm_unload_wgmnt> op=this.wm_unload_wgmntRepository.findById(id);
		return op.get();
	}
	
	public Wm_unload_wgmntDTO getUnloadWeightment(String vcode){
		Wm_unload_wgmnt modelList=wm_unload_wgmntRepository.getUnloadWeightment(vcode);
			
		Type listType = new TypeToken<Wm_unload_wgmntDTO>() {}.getType();
		Wm_unload_wgmntDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		return uAdvice;
	}
	
	public Wm_unload_wgmntDTO getUnloadWeightmentWt(String wgment_id){
		Wm_unload_wgmnt modelList=wm_unload_wgmntRepository.getUnloadWeightmentWt(wgment_id);
			
		Type listType = new TypeToken<Wm_unload_wgmntDTO>() {}.getType();
		Wm_unload_wgmntDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		return uAdvice;
	}
	
	public Wm_unload_wgmntDTO getUnloadWeightmentWtmultipopup(String unload_id){
		Wm_unload_wgmnt modelList=wm_unload_wgmntRepository.getUnloadWeightmentWtmultipopup(unload_id);
		
		Type listType = new TypeToken<Wm_unload_wgmntDTO>() {}.getType();
		Wm_unload_wgmntDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		return uAdvice;
	}
	
	public List<Wm_unload_wgmnt_dtlsDTO> unloadWMDtlsRetriveList(String code)
	{
		List<Wm_unload_wgmnt> modelList=new ArrayList<Wm_unload_wgmnt>();
		
		modelList.addAll(wm_unload_wgmntRepository.unloadWMDtlsRetriveList(code));
			
		Type listType=new TypeToken<List<Wm_unload_wgmnt_dtlsDTO>>() {}.getType();
		List<Wm_unload_wgmnt_dtlsDTO> unloadWn=new ModelMapper().map(modelList,listType);
		
		return unloadWn;
	}
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighment()
	{   
		Vehicle_weighment_load_unload vehidefualt=new Vehicle_weighment_load_unload();
		vehidefualt.setVehicle_id("0");
		vehidefualt.setVehicle_no("Choose an Option");
		List<Vehicle_weighment_load_unload> modelList=new ArrayList<Vehicle_weighment_load_unload>();
		modelList.add(vehidefualt);
		modelList.addAll(wm_unload_wgmntRepository.findVehicleList());
		
		return modelList;
	
	}
	
	
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighmentnew()
	{
		List<Vehicle_weighment_load_unload> modelList=new ArrayList<Vehicle_weighment_load_unload>();
		
		modelList.addAll(wm_unload_wgmntRepository.findVehicleListnew());
		
		return modelList;
	}
	
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighmenOutAuth()
	{
		Vehicle_weighment_load_unload newvehivle= new Vehicle_weighment_load_unload();
		newvehivle.setVehicle_id("0");
		newvehivle.setVehicle_no("Choose an option");
		
		List<Vehicle_weighment_load_unload> modelList=new ArrayList<Vehicle_weighment_load_unload>();
		modelList.add(newvehivle);
		modelList.addAll(wm_unload_wgmntRepository.getVehicleListWeighmenOutAuth());
		
		return modelList;
	}
	
	public Wm_unload_wgmnt getWeighmentId(String wgment_id){
		Wm_unload_wgmnt modelList=wm_unload_wgmntRepository.getUnloadWeightmentWt(wgment_id);
		
		return modelList;
	}
	
	public List<Weighment_doc> getGetDocuments(String unadv_id)
	{
		List<Wm_unload_wgmnt_dtls> weimentid =new ArrayList<Wm_unload_wgmnt_dtls>();
		weimentid.addAll(wm_unload_wgmntRepository.getWeimentid(unadv_id));
				
		List<Weighment_doc> modelList=new ArrayList<Weighment_doc>();
		
		for(int i=0;i<weimentid.size();i++) 
		{
			modelList.addAll(wm_unload_wgmntRepository.getWm_doc(weimentid.get(i).getWgment_id()));
		}
		
		return modelList;
	}
	
	public List<Wm_unload_wgmntDTO> getWeighmentDataList(String currDate,String finyear)
	{
	 
	 	List<Wm_unload_wgmnt> modelList =new ArrayList<Wm_unload_wgmnt>();
	 	String tablename="wm_unload_wgmnt",party_name="partyname",order_no="wgment_no",order_date="wgment_date";
	 	String orderno="",nobpartner="";
	 	//modelList.addAll(wm_unload_wgmntRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,nobpartner,finyear));
	 	modelList.addAll(wm_unload_wgmntRepository.getWeighmentData(currDate,finyear));
		Type listType = new TypeToken<List<Wm_unload_wgmntDTO>>() {}.getType();
		List<Wm_unload_wgmntDTO> advList = new ModelMapper().map(modelList,listType);
		
		advList.forEach((e)->{
  			
  			if(e.getVehicle_ref_name().compareToIgnoreCase("Unload Advice")==0)
  			{
  			    e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
  			}
  			if(e.getVehicle_ref_name().compareToIgnoreCase("Load Advice")==0)
  			{
  				if(e.getWgment_for().compareToIgnoreCase("Stock Transfer Loading")==0)
  				{
  					e.setTw_remarks("None");
  				}
  				else
  				{
  					e.setTw_remarks(getBusinesspartnername(e.getWgment_for(),e.getWgment_id()));
  				}
  			}
  			e.setVehicle_id(vehicleMasterRepository.getVehicleDetails(e.getVehicle_id()).getVehicle_no());
  			
  		});
		return advList;
	}
	
	public List<Map<String,Object>> getWeighmentDataFastList(String currDate)
	{
		return wm_unload_wgmntRepository.getWeighmentDataFastList(currDate);
	}
	
	public List<Map<String,Object>> getOtherWeighmentDataList(String currDate,String finyear)
	{
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 modelList.addAll(wm_unload_wgmntRepository.getOtherWeighmentDataList(currDate,finyear));
		 return modelList;
	}
	
	public List<Map<String,Object>> getNopartyList()
	{
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 modelList.addAll(wm_unload_wgmntRepository.getNopartyList());
		 return modelList;
	}
	
	public Map<String,Object> getFirstData()
	{
		Map<String,Object> modelList=wm_unload_wgmntRepository.getFirstData();
		 return modelList;
	}
	
	public Map<String,Object> getOtherWgFirstData(String vehicleid)
	{
		Map<String,Object> modelList=wm_unload_wgmntRepository.getOtherWgFirstData(vehicleid);
		 return modelList;
	}
	
	public Map<String, Object> getOtherWgFirstDataWtWgtFor(String vehicleid) {
		Map<String, Object> modelList = wm_unload_wgmntRepository.getOtherWgFirstDataWtWgtFor(vehicleid);
		return modelList;
	}
	
	public List<Wm_unload_wgmnt> getdailygatewheatOUTwardreport(String fromdate,String todate)
	{
		String tablename="wm_unload_wgmnt",order_date="wgment_date";
		List<Wm_unload_wgmnt> weiment =new ArrayList<Wm_unload_wgmnt>();
		weiment.addAll(wm_unload_wgmntRepository.getsearchweighmentdatasale(tablename,order_date,fromdate,todate));
		
		weiment.forEach((e)->{
			List<Wm_loading_advice_itm_dtls> loadingdetails=wm_loading_advice_itm_dtlsRepository.loadingItemRetriveList(e.getAdvice());
			e.setUpdated_by(e.getAdvice_no());	
			e.setWgment_no(e.getWgment_no().substring(13, e.getWgment_no().length()));
			e.setPartyname(cust_bussiness_partnerRepository.getCustomerThruBUstringnew(e.getPartyname()).getCp_name());
			if(loadingdetails.size()>1) 
			{
				e.setAdvice_no(loadingdetails.get(0).getItem_name()+"....");
			}
			else 
			{
				e.setAdvice_no(loadingdetails.get(0).getItem_name());
			}
			
					
			
			List<String>multipleadvice=wm_loading_advice_itm_dtlsRepository.getadviceidthrughweighmentid(e.getWgment_id());
			double totalitem=0.00,totalpacking=0.00;
			for(int i=0;i<multipleadvice.size();i++) 
			{
				 totalitem+=wm_loading_advice_itm_dtlsRepository.getsumitemqty(multipleadvice.get(i));
				 totalpacking+=wm_loading_advice_itm_dtlsRepository.getsumpackingqty(multipleadvice.get(i));
			}
			
			e.setTw_unit(loadingdetails.get(0).getUom());//item uom
			e.setDeleted_by(String.valueOf(totalitem));//item qty  getsumitemqty
			
			e.setGw_unit(loadingdetails.get(0).getS_uom());//packing uom
			e.setInserted_by(String.valueOf(totalpacking));//packqty  getsumpackingqty
			
			
			e.setCompany_id(String.valueOf(e.getNet_weight()-totalitem));//differnce netwt - itemqty
		});
		return weiment;
	}
	
	public List<Map<String, Object>> getOtherKataReport(String date,String todate)
	 {
		 return wm_unload_wgmntRepository.getOtherKataReport(date,todate);
	 }
	
	public List<Map<String, Object>> getOtherKataWithPartyReport(String date,String todate,String party)
	 {
		 return wm_unload_wgmntRepository.getOtherKataWithPartyReport(date,todate,party);
	 }
	
	public List<Map<String, Object>> getdailyjobworkwgtreport(String loadfromdate,String load2date,String party)
	 {
		if(party.compareToIgnoreCase("0")==0)
		{
			return wm_unload_wgmntRepository.wheat_inward_view_sale_jobwork(loadfromdate,load2date);
		}
		else
		{
			return wm_unload_wgmntRepository.wheat_inward_view_sale_jobworkWithParty(loadfromdate,load2date,party);
		}
		 
	 }
	
	public StatusDTO checkLooseItem(String adviceid) 
	{
		StatusDTO res =new StatusDTO();
		int checkstatus=1;
		Wm_loading_advice wm_loading_advice=wm_loading_adviceRepository.getLoadingDetails(adviceid);
		//System.out.println(" Check : : "+wgmnt.getAdvice());
		//System.out.println(" YA YA  : : "+wm_loading_advice.isJobwork());
		if(wm_loading_advice.isLooseitem())					
		{
			res.setStatus("YES");
		}
		else 					
		{
			res.setStatus("NO");
		}
		return res;
	}
	
	@Transactional
	public Terminatekata terminatekata(Terminatekata terminatekata) 
	{
		System.out.println(terminatekata.getWeighment_id()+" / "+terminatekata.getUsername()+" / "+terminatekata.getTerminate_remarks()+" / "+terminatekata.getTer_oth_wgmnt_no()+" / "+terminatekata.getWgment_for());
		wm_unload_wgmntRepository.terminatekatamain(terminatekata.getWeighment_id(),terminatekata.getUsername(),terminatekata.getTer_oth_wgmnt_no(),terminatekata.getTerminate_remarks());
		if(terminatekata.getWgment_for().compareToIgnoreCase("Purchase Order")==0)		
		{
			wm_unload_adviceRepository.terminateunloading(terminatekata.getWeighment_id(),terminatekata.getUsername());
		}
		else
		{
			wm_loading_adviceRepository.terminateloading(terminatekata.getWeighment_id(),terminatekata.getUsername());
		}
		
		return terminatekata;
	}
	
	public List<Map<String,Object>> getOtherWgnmtList()
	{
		return wm_unload_wgmntRepository.getOtherWgnmtList();
	}
	
	public List<Map<String,Object>> getUnloadWeightmentWtmultipopupmultipleItem(String wgment_id){
		return wm_unload_wgmntRepository.getUnloadWeightmentWtmultipopupmultipleItem(wgment_id);
	}
	
	public List<Vehicle_weighment_load_unload> getVehicleListWeighmentLocation(String location)
	{
		Vehicle_weighment_load_unload vehidefualt = new Vehicle_weighment_load_unload();
		vehidefualt.setVehicle_id("0");
		vehidefualt.setVehicle_no("Choose an Option");
		List<Vehicle_weighment_load_unload> modelList = new ArrayList<Vehicle_weighment_load_unload>();
		modelList.add(vehidefualt);
		modelList.addAll(wm_unload_wgmntRepository.findVehicleLocationwiseList(location));

		return modelList;
	}
	
	public List<Vehicle_weighment_load_unload> getVehicleLocationwiseWeighmentList(String location){
		List<Vehicle_weighment_load_unload> modelList = new ArrayList<Vehicle_weighment_load_unload>();

		modelList.addAll(wm_unload_wgmntRepository.getVehicleLocationwiseWeighmentList(location));

		return modelList;
	}
	
}
