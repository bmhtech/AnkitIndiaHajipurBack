package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Production_planning;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shift_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shift_details_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls;
import com.AnkitIndia.jwtauthentication.repository.Bom_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planningRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_periodic_date_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_shift_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_shop_floor_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_shop_floor_dtls_staticRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Production_planning_shop_floor_dtls_staticService_Imp implements Production_planning_shop_floor_dtls_staticService{

	@Autowired
	Production_planningRepository production_planningRepository;
	
	@Autowired
	Production_planning_shop_floor_dtls_staticRepository production_planning_shop_floor_dtls_staticRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	@Autowired
	Bom_masterRepository bom_masterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Production_planning_shift_detailsRepository production_planning_shift_detailsRepository;
	
	@Autowired
	Production_planning_periodic_date_detailsRepository pDate_detailsRepository;
	
	@Autowired
	Production_planning_shop_floor_dtlsRepository production_planning_shop_floor_dtlsRepository;
	
	@Transactional
	public Production_planning_shop_floor_dtls_static save(Production_planning_shop_floor_dtls_static pDtls) throws JsonParseException, JsonMappingException, IOException
	{
		//Update
		production_planning_shop_floor_dtlsRepository.updateProduction_planning_shop_floor_dtls_static(pDtls.getProd_plan_id());
		
		LocalDateTime ldt = LocalDateTime.now();
		Production_planning op = production_planningRepository.findByProdPlanId(pDtls.getProd_plan_id());
		
		String buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(op.getBusiness_unit()).getBusinessunit_name().substring(0,3);
		
		if(pDtls.getProcess().compareTo("0") !=0 && pDtls.getProcess().compareTo("") !=0 && pDtls.getProcess() !=null) {
			pDtls.setProcess_name(process_masterRepository.getProcessDetails(pDtls.getProcess()).getProcess_desc());
		}else {pDtls.setProcess_name("None");}
		
		if(pDtls.getProduction().compareTo("0") !=0 && pDtls.getProduction().compareTo("") !=0 && pDtls.getProduction() !=null) {
			pDtls.setProduction_name(bom_masterRepository.getBomDetails(pDtls.getProduction()).getProd_desc());
		}else {pDtls.setProduction_name("None");}
		
		pDtls.setProd_plan_code(op.getProd_plan_code());
		pDtls.setProd_plan_id(op.getProd_plan_id());
		pDtls.setBusiness_unit(op.getBusiness_unit());
		pDtls.setCompany_id(op.getCompany_id());
		pDtls.setFin_year(op.getFin_year());
		pDtls.setModified_type("INSERTED");
		pDtls.setInserted_by(op.getInserted_by());
		pDtls.setInserted_on(op.getInserted_on());
		pDtls.setUpdated_by(op.getUpdated_by());
		pDtls.setUpdated_on(op.getUpdated_on());
		pDtls.setDeleted_by("NA");
		pDtls.setDeleted_on(ldt);
		
		int x=pDtls.getSl_no();
		pDtls.setPpd_id(pDtls.getProd_plan_id()+Long.toString(x));
		
			//Update
			pDate_detailsRepository.updateProduction_planning_periodic_date_details_static(op.getProd_plan_id());
		
			//For regular process popup date details ********
			long n=0;
			
			Set<Production_planning_periodic_date_details_static> pDate_details2=new HashSet<Production_planning_periodic_date_details_static>();
			
			if(pDtls.getProcess_date().compareTo("")==0) {
				pDtls.setProcess_date("{\"periodic_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\",\"todate\":\"\",\"shift1\":\"\"}]}");
			}
			
			String inptData=pDtls.getProcess_date().replace("{\"periodic_date_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			//Production_planning_periodic_date_details is class.
			//inptData must be mapped with the class's entity
			@SuppressWarnings("unchecked")
			Production_planning_periodic_date_details_static[] pDate_details= new ObjectMapper().readValue(inptData, Production_planning_periodic_date_details_static[].class);
			// List<Production_planning_periodic_date_details> listObjects = Arrays.asList(pDate_details);
			
			Set<Production_planning_periodic_date_details_static> popupDateSet = new HashSet<>(Arrays.asList(pDate_details));
			
			/*for(Production_planning_periodic_date_details ppDateDetails:popupDateSet) 
			{
				System.err.println("Got Date 1st:> "+ppDateDetails.getFromdate()+" To: "+ppDateDetails.getTodate());
				if(ppDateDetails.getFromdate().compareTo("")!=0 && ppDateDetails.getTodate().compareTo("")!=0)
				{
					pDate_details2.add(ppDateDetails);
				}
			}*/
			
			pDate_details2.addAll(popupDateSet);
			for(Production_planning_periodic_date_details_static ppDatedtls:pDate_details2) 
			{
				ppDatedtls.setProd_plan_code(pDtls.getProd_plan_code());
				ppDatedtls.setProd_plan_id(pDtls.getProd_plan_id());
				ppDatedtls.setPpd_id(pDtls.getPpd_id());
				
				ppDatedtls.setCompany_id(pDtls.getCompany_id());
				ppDatedtls.setFin_year(pDtls.getFin_year());
				ppDatedtls.setModified_type("INSERTED");
				ppDatedtls.setInserted_by(pDtls.getInserted_by());
				ppDatedtls.setInserted_on(pDtls.getInserted_on());
				ppDatedtls.setUpdated_by(pDtls.getUpdated_by());
				ppDatedtls.setUpdated_on(pDtls.getUpdated_on());
				ppDatedtls.setDeleted_by("NA");
				ppDatedtls.setDeleted_on(ldt);
				
				n++;
				ppDatedtls.setPpds_id("S"+pDtls.getProd_plan_id()+Long.toString(n));
				
				if(ppDatedtls.getFromdate().compareTo("")==0) {ppDatedtls.setFromdate("00-00-0000");}
				if(ppDatedtls.getTodate().compareTo("")==0) {ppDatedtls.setTodate("00-00-0000");}
				String date[] = ppDatedtls.getFromdate().split("-");
				String date2[] = ppDatedtls.getTodate().split("-");
				
				String prefix=buprefix+"-"+date[0]+date[1]+date[2].substring(2,4)+"-"+date2[0]+date2[1]+date2[2].substring(2,4)+"-";
				String gen_sno="";
				//For regular process popup date shift details ********
				
				//Update
				production_planning_shift_detailsRepository.updateProduction_planning_shift_details_static(op.getProd_plan_id());
				
				String shifts="",newshifts="",pshifts="";
				
				String processfreq=process_masterRepository.getProcessDetails(pDtls.getProcess()).getProcess_freq();
				String shiftinfo[]=null;
				/*if(processfreq.compareTo("Periodic")==0)
				{*/
					pshifts=ppDatedtls.getShift1();
					if(pshifts.compareTo("")==0) {pshifts="0,0";}
					shiftinfo=pshifts.split(",");
					for (int m=0;m<shiftinfo.length;m++) 
					{
						String pshiftinfo[]=null;
						pshiftinfo=shiftinfo[m].split("-");
						newshifts +=pshiftinfo[0]+",";
					}
					shifts=newshifts.substring(0, newshifts.length()-1);
					
					ppDatedtls.setShift2(shifts);
				/*}
				else {
					System.err.println("In case of daily ................");
					shifts=ppDatedtls.getShift1();
					ppDatedtls.setShift2("0");
				}*/
				
				String noshifts[]=null;
				if(shifts.compareTo("")==0) {shifts="0,0";}
				noshifts=shifts.split(",");
				
				int shslno=0;String genshftno="",sftgen_sno="";
				String shsno=production_planning_shift_detailsRepository.countShifts(op.getBusiness_unit(),ppDatedtls.getFromdate(),ppDatedtls.getTodate());
				if(shsno != null ) {
					shslno=Integer.parseInt(shsno);
				}
				
				System.err.println("Shift Length: "+shiftinfo.length+" Shift: "+noshifts.length);
				String shift_enddate="0",shift_endtime="0",shift_startdate="0",shift_starttime="0",shiftprefix="",shiftcode="";
				
				Set<Production_planning_shift_details_static> pShift_details=new HashSet<Production_planning_shift_details_static>();
				for (int j=0;j<noshifts.length;j++) 
				{ 
					//if(processfreq.compareTo("Periodic")==0 && shiftinfo[j].length()>1) {
					if(shiftinfo[j].length()>1) {	
						shift_startdate=shiftinfo[j].substring(shiftinfo[j].length()-33, 12);
						shift_starttime=shiftinfo[j].substring(shiftinfo[j].length()-22, 18);
						shift_enddate=shiftinfo[j].substring(shiftinfo[j].length()-16, 29);
						shift_endtime=shiftinfo[j].substring(shiftinfo[j].length()-5, shiftinfo[j].length());
					}
					
					//sftgen_sno=UniqueIDTransaction.uniqueId2(prefix,Integer.parseInt(noshifts[j])-1)+"-";
					//genshftno=UniqueIDTransaction.uniqueId4(sftgen_sno,shslno);
					
					sftgen_sno=UniqueIDTransaction.uniqueId2(buprefix+"-",Integer.parseInt(noshifts[j])-1);
					shiftprefix=sftgen_sno+"-"+shift_startdate+" "+shift_starttime+"-"+shift_enddate+" "+shift_endtime+"-";
					shiftcode=UniqueIDTransaction.uniqueId4(shiftprefix,shslno);
					
					System.err.println("Shft ID: "+shiftcode);
					
					//Production_planning_shift_details_static shfts=new Production_planning_shift_details_static(ppDatedtls.getProd_plan_id(),ppDatedtls.getProd_plan_code(),ppDatedtls.getPpd_id(),ppDatedtls.getPpds_id(),shiftcode,op.getBusiness_unit(),pDtls.getShop_floor(),pDtls.getProcess(),pDtls.getProduction(),ppDatedtls.getFromdate(),ppDatedtls.getTodate(),Integer.parseInt(noshifts[j]),shift_startdate,shift_starttime,shift_enddate,shift_endtime,ppDatedtls.getCompany_id(),ppDatedtls.getFin_year(),ppDatedtls.getModified_type(),ppDatedtls.getInserted_on(),ppDatedtls.getInserted_by(),ppDatedtls.getUpdated_on(),ppDatedtls.getUpdated_by(),ppDatedtls.getDeleted_on(),ppDatedtls.getDeleted_by(),ppDatedtls);
					Production_planning_shift_details_static shfts=new Production_planning_shift_details_static();
					
					shfts.setProd_plan_id(ppDatedtls.getProd_plan_id()); 
					shfts.setProd_plan_code(ppDatedtls.getProd_plan_code());
					shfts.setPpd_id(ppDatedtls.getPpd_id());
					shfts.setPpds_id(ppDatedtls.getPpds_id());
					shfts.setPp_shiftid(shiftcode);
					shfts.setBusiness_unit(op.getBusiness_unit());
					shfts.setShop_floor(pDtls.getShop_floor());
					shfts.setProcess(pDtls.getProcess());
					shfts.setProduction(pDtls.getProduction());
					shfts.setFromdate(ppDatedtls.getFromdate());
					shfts.setTodate(ppDatedtls.getTodate());
					shfts.setShift(Integer.parseInt(noshifts[j]));
					shfts.setShift_startdate(shift_startdate);
					shfts.setShift_starttime(shift_starttime);
					shfts.setShift_enddate(shift_enddate);
					shfts.setShift_endtime(shift_endtime);
					shfts.setCompany_id(ppDatedtls.getCompany_id());
					shfts.setFin_year(ppDatedtls.getFin_year());
					
					shfts.setModified_type(ppDatedtls.getModified_type());
					shfts.setInserted_on(ppDatedtls.getInserted_on());
					shfts.setInserted_by(ppDatedtls.getInserted_by());
					shfts.setUpdated_on(ppDatedtls.getUpdated_on());
					shfts.setUpdated_by(ppDatedtls.getUpdated_by());
					shfts.setDeleted_on(ppDatedtls.getDeleted_on());
					shfts.setDeleted_by(ppDatedtls.getDeleted_by());
					
					pShift_details.add(shfts);
					
					shslno++;
				}
				ppDatedtls.setProduction_planning_shift_details(pShift_details);
			}
			pDtls.setPeriodic_date_details(pDate_details2);
			
		return production_planning_shop_floor_dtls_staticRepository.save(pDtls);
	}
	
	
	@Transactional
	public Production_planning_shop_floor_dtls_static update(Production_planning_shop_floor_dtls_static pDtls,String prodid,int slno) throws JsonParseException, JsonMappingException, IOException 
	{
		
		//Update
		production_planning_shop_floor_dtlsRepository.updateProduction_planning_shop_floor_dtls_static(pDtls.getProd_plan_id(),slno);
		
		LocalDateTime ldt = LocalDateTime.now();
		Production_planning op = production_planningRepository.findByProdPlanId(pDtls.getProd_plan_id());
		
		String buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(op.getBusiness_unit()).getBusinessunit_name().substring(0,3);
		
		//Production_planning_shop_floor_dtls reguDtls=production_planning_shop_floor_dtlsRepository.getProduction_planning_regular_dtls(prodid,slno);
		
		if(pDtls.getProcess().compareTo("0") !=0 && pDtls.getProcess().compareTo("") !=0 && pDtls.getProcess() !=null) {
			pDtls.setProcess_name(process_masterRepository.getProcessDetails(pDtls.getProcess()).getProcess_desc());
		}else {pDtls.setProcess_name("None");}
		
		if(pDtls.getProduction().compareTo("0") !=0 && pDtls.getProduction().compareTo("") !=0 && pDtls.getProduction() !=null) {
			pDtls.setProduction_name(bom_masterRepository.getBomDetails(pDtls.getProduction()).getProd_desc());
		}else {pDtls.setProduction_name("None");}
		
		pDtls.setProd_plan_code(op.getProd_plan_code());
		pDtls.setProd_plan_id(op.getProd_plan_id());
		pDtls.setBusiness_unit(op.getBusiness_unit());
		pDtls.setCompany_id(op.getCompany_id());
		pDtls.setFin_year(op.getFin_year());
		pDtls.setModified_type("INSERTED");
		pDtls.setInserted_by(op.getInserted_by());
		pDtls.setInserted_on(op.getInserted_on());
		pDtls.setUpdated_by(op.getUpdated_by());
		pDtls.setUpdated_on(op.getUpdated_on());
		pDtls.setDeleted_by("NA");
		pDtls.setDeleted_on(ldt);
		
		int x=pDtls.getSl_no();
		pDtls.setPpd_id(pDtls.getProd_plan_id()+Long.toString(x));
		
		
			//Update
			pDate_detailsRepository.updateProduction_planning_periodic_date_details_static(pDtls.getProd_plan_id(),pDtls.getPpd_id());
		
			//For regular process popup date details ********
			long n=0;
			
			Set<Production_planning_periodic_date_details_static> pDate_details2=new HashSet<Production_planning_periodic_date_details_static>();
			
			if(pDtls.getProcess_date().compareTo("")==0) {
				pDtls.setProcess_date("{\"periodic_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\",\"todate\":\"\",\"shift1\":\"\"}]}");
			}
			
			String inptData=pDtls.getProcess_date().replace("{\"periodic_date_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			//Production_planning_periodic_date_details is class.
			//inptData must be mapped with the class's entity
			@SuppressWarnings("unchecked")
			Production_planning_periodic_date_details_static[] pDate_details= new ObjectMapper().readValue(inptData, Production_planning_periodic_date_details_static[].class);
			// List<Production_planning_periodic_date_details> listObjects = Arrays.asList(pDate_details);
			
			Set<Production_planning_periodic_date_details_static> popupDateSet = new HashSet<>(Arrays.asList(pDate_details));
			
			/*for(Production_planning_periodic_date_details ppDateDetails:popupDateSet) 
			{
				System.err.println("Got Date 1st:> "+ppDateDetails.getFromdate()+" To: "+ppDateDetails.getTodate());
				if(ppDateDetails.getFromdate().compareTo("")!=0 && ppDateDetails.getTodate().compareTo("")!=0)
				{
					pDate_details2.add(ppDateDetails);
				}
			}*/
			
			pDate_details2.addAll(popupDateSet);
			for(Production_planning_periodic_date_details_static ppDatedtls:pDate_details2) 
			{
				ppDatedtls.setProd_plan_code(pDtls.getProd_plan_code());
				ppDatedtls.setProd_plan_id(pDtls.getProd_plan_id());
				ppDatedtls.setPpd_id(pDtls.getPpd_id());
				
				ppDatedtls.setCompany_id(pDtls.getCompany_id());
				ppDatedtls.setFin_year(pDtls.getFin_year());
				ppDatedtls.setModified_type("INSERTED");
				ppDatedtls.setInserted_by(pDtls.getInserted_by());
				ppDatedtls.setInserted_on(pDtls.getInserted_on());
				ppDatedtls.setUpdated_by(pDtls.getUpdated_by());
				ppDatedtls.setUpdated_on(pDtls.getUpdated_on());
				ppDatedtls.setDeleted_by("NA");
				ppDatedtls.setDeleted_on(ldt);
				
				n++;
				ppDatedtls.setPpds_id("S"+pDtls.getProd_plan_id()+Long.toString(n));
				//For regular process popup date shift details ********
				
				if(ppDatedtls.getFromdate().compareTo("")==0) {ppDatedtls.setFromdate("00-00-0000");}
				if(ppDatedtls.getTodate().compareTo("")==0) {ppDatedtls.setTodate("00-00-0000");}
				String date[] = ppDatedtls.getFromdate().split("-");
				String date2[] = ppDatedtls.getTodate().split("-");
				
				String prefix=buprefix+"-"+date[0]+date[1]+date[2].substring(2,4)+"-"+date2[0]+date2[1]+date2[2].substring(2,4)+"-";
				String gen_sno="";
				
				//Update
				production_planning_shift_detailsRepository.updateProduction_planning_shift_details_static(pDtls.getProd_plan_id(),pDtls.getPpd_id());
				
				String shifts="",newshifts="",pshifts="";
				
				String processfreq=process_masterRepository.getProcessDetails(pDtls.getProcess()).getProcess_freq();
				String shiftinfo[]=null;
				/*if(processfreq.compareTo("Periodic")==0)
				{*/
					pshifts=ppDatedtls.getShift1();
					if(pshifts.compareTo("")==0) {pshifts="0,0";}
					shiftinfo=pshifts.split(",");
					for (int m=0;m<shiftinfo.length;m++) 
					{
						String pshiftinfo[]=null;
						pshiftinfo=shiftinfo[m].split("-");
						newshifts +=pshiftinfo[0]+",";
					}
					shifts=newshifts.substring(0, newshifts.length()-1);
					
					ppDatedtls.setShift2(shifts);
				/*}
				else {
					System.err.println("In case of daily ................");
					shifts=ppDatedtls.getShift1();
					ppDatedtls.setShift2("0");
				}*/
				
				String noshifts[]=null;
				if(shifts.compareTo("")==0) {shifts="0,0";}
				noshifts=shifts.split(",");
				
				int shslno=0;String genshftno="",sftgen_sno="";
				String shsno=production_planning_shift_detailsRepository.countShifts(op.getBusiness_unit(),ppDatedtls.getFromdate(),ppDatedtls.getTodate());
				if(shsno != null ) {
					shslno=Integer.parseInt(shsno);
				}
				
				System.err.println("Shift Length: "+shiftinfo.length+" Shift: "+noshifts.length);
				String shift_startdate="0",shift_starttime="0",shift_enddate="0",shift_endtime="0",shiftprefix="",shiftcode="";
				
				Set<Production_planning_shift_details_static> pShift_details=new HashSet<Production_planning_shift_details_static>();
				for (int j=0;j<noshifts.length;j++)
				{ 
					//if(processfreq.compareTo("Periodic")==0 && shiftinfo[j].length()>1) {
					if(shiftinfo[j].length()>1) {	
						shift_startdate=shiftinfo[j].substring(shiftinfo[j].length()-33, 12);
						shift_starttime=shiftinfo[j].substring(shiftinfo[j].length()-22, 18);
						shift_enddate=shiftinfo[j].substring(shiftinfo[j].length()-16, 29);
						shift_endtime=shiftinfo[j].substring(shiftinfo[j].length()-5, shiftinfo[j].length());
					}
					
					//sftgen_sno=UniqueIDTransaction.uniqueId2(prefix,Integer.parseInt(noshifts[j])-1)+"-";
					//genshftno=UniqueIDTransaction.uniqueId4(sftgen_sno,shslno);
					
					sftgen_sno=UniqueIDTransaction.uniqueId2(buprefix+"-",Integer.parseInt(noshifts[j])-1);
					shiftprefix=sftgen_sno+"-"+shift_startdate+" "+shift_starttime+"-"+shift_enddate+" "+shift_endtime+"-";
					shiftcode=UniqueIDTransaction.uniqueId4(shiftprefix,shslno);
					
					//Production_planning_shift_details_static shfts=new Production_planning_shift_details_static(ppDatedtls.getProd_plan_id(),ppDatedtls.getProd_plan_code(),ppDatedtls.getPpd_id(),ppDatedtls.getPpds_id(),shiftcode,op.getBusiness_unit(),pDtls.getShop_floor(),pDtls.getProcess(),pDtls.getProduction(),ppDatedtls.getFromdate(),ppDatedtls.getTodate(),Integer.parseInt(noshifts[j]),shift_startdate,shift_starttime,shift_enddate,shift_endtime,ppDatedtls.getCompany_id(),ppDatedtls.getFin_year(),ppDatedtls.getModified_type(),ppDatedtls.getInserted_on(),ppDatedtls.getInserted_by(),ppDatedtls.getUpdated_on(),ppDatedtls.getUpdated_by(),ppDatedtls.getDeleted_on(),ppDatedtls.getDeleted_by(),ppDatedtls);
					
                     Production_planning_shift_details_static shfts=new Production_planning_shift_details_static();
					
					shfts.setProd_plan_id(ppDatedtls.getProd_plan_id()); 
					shfts.setProd_plan_code(ppDatedtls.getProd_plan_code());
					shfts.setPpd_id(ppDatedtls.getPpd_id());
					shfts.setPpds_id(ppDatedtls.getPpds_id());
					shfts.setPp_shiftid(shiftcode);
					shfts.setBusiness_unit(op.getBusiness_unit());
					shfts.setShop_floor(pDtls.getShop_floor());
					shfts.setProcess(pDtls.getProcess());
					shfts.setProduction(pDtls.getProduction());
					shfts.setFromdate(ppDatedtls.getFromdate());
					shfts.setTodate(ppDatedtls.getTodate());
					shfts.setShift(Integer.parseInt(noshifts[j]));
					shfts.setShift_startdate(shift_startdate);
					shfts.setShift_starttime(shift_starttime);
					shfts.setShift_enddate(shift_enddate);
					shfts.setShift_endtime(shift_endtime);
					shfts.setCompany_id(ppDatedtls.getCompany_id());
					shfts.setFin_year(ppDatedtls.getFin_year());
					shfts.setModified_type(ppDatedtls.getModified_type());
					shfts.setInserted_on(ppDatedtls.getInserted_on());
					shfts.setInserted_by(ppDatedtls.getInserted_by());
					shfts.setUpdated_on(ppDatedtls.getUpdated_on());
					shfts.setUpdated_by(ppDatedtls.getUpdated_by());
					shfts.setDeleted_on(ppDatedtls.getDeleted_on());
					shfts.setDeleted_by(ppDatedtls.getDeleted_by());
					
					pShift_details.add(shfts);
					
					shslno++;
				}
				ppDatedtls.setProduction_planning_shift_details(pShift_details);
				
			}
			pDtls.setPeriodic_date_details(pDate_details2);
		
		return production_planning_shop_floor_dtls_staticRepository.save(pDtls);
	}
	
}
