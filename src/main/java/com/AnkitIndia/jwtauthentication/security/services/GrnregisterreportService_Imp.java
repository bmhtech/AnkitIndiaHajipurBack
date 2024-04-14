package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Dailypowerreport;
import com.AnkitIndia.jwtauthentication.model.Grnregisterreport;
import com.AnkitIndia.jwtauthentication.model.Grnregisterreport_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DailypowerreportRepository;
import com.AnkitIndia.jwtauthentication.repository.GrnregisterreportRepository;
import com.AnkitIndia.jwtauthentication.repository.Grnregisterreport_DtlsReository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.GrnregisterreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;

@Service
public class GrnregisterreportService_Imp implements GrnregisterreportService{
	
	@Autowired
	GrnregisterreportRepository grnregisterreportRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Grnregisterreport_DtlsReository grnregisterreport_DtlsReository;
	
	public List<Grnregisterreport> getGRNRegisterReportlist(String currDate,String finyear)
	{
		List<Grnregisterreport> grnlist = new ArrayList<Grnregisterreport>();
		grnlist.addAll(grnregisterreportRepository.getgrnregisterlist(currDate,finyear));
		
		return grnlist;
	}
	
	@Transactional
	public Grnregisterreport save(Grnregisterreport grnregisterreport) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(grnregisterreportRepository.countId() != null ) {
			slno=Long.parseLong(grnregisterreportRepository.countId());
		}
		String prefix="GRR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		grnregisterreport.setGrnregisterid(gen_sno);
		grnregisterreport.setCompany_id(grnregisterreport.getCompany_id());
		grnregisterreport.setFin_year(grnregisterreport.getFin_year());
		grnregisterreport.setModified_type("INSERTED");
		grnregisterreport.setInserted_on(ldt);
		grnregisterreport.setInserted_by(userRepository.getUserDetails(grnregisterreport.getUsername()).getName());
		grnregisterreport.setUpdated_by(grnregisterreport.getUpdated_by());
		grnregisterreport.setUpdated_on(ldt);
		grnregisterreport.setDeleted_by("NA");
		grnregisterreport.setDeleted_on(ldt);
		
		Set<Grnregisterreport_Dtls> grnregisterreport_Dtls=new HashSet<Grnregisterreport_Dtls>();
		grnregisterreport_Dtls.addAll(grnregisterreport.getGrnregisterreport_Dtls());
		for(Grnregisterreport_Dtls genDetails:grnregisterreport_Dtls) 
		{
			genDetails.setGrnregisterid(gen_sno);
			
			genDetails.setGrndate(grnregisterreport.getGrndate());
			
			genDetails.setCompany_id(grnregisterreport.getCompany_id());
			genDetails.setFin_year(grnregisterreport.getFin_year());
			genDetails.setModified_type("INSERTED");
			genDetails.setInserted_by(grnregisterreport.getInserted_by());
			genDetails.setInserted_on(ldt);
			genDetails.setUpdated_by("NA");
			genDetails.setUpdated_on(ldt);
			genDetails.setDeleted_by("NA");
			genDetails.setDeleted_on(ldt);
		}
		grnregisterreport.setGrnregisterreport_Dtls(grnregisterreport_Dtls);
		
		return grnregisterreportRepository.save(grnregisterreport);	
	}
	
	@Transactional
	public Grnregisterreport update(Grnregisterreport grnregisterreport,long id) 
	{

		Optional<Grnregisterreport> op =grnregisterreportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		grnregisterreport.setGrnregisterid(op.get().getGrnregisterid());
		grnregisterreport.setCompany_id(grnregisterreport.getCompany_id());
		grnregisterreport.setFin_year(grnregisterreport.getFin_year());
		grnregisterreport.setModified_type("INSERTED");
		grnregisterreport.setInserted_on(ldt);
		grnregisterreport.setInserted_by(userRepository.getUserDetails(grnregisterreport.getUsername()).getName());
		grnregisterreport.setUpdated_by(grnregisterreport.getUpdated_by());
		grnregisterreport.setUpdated_on(ldt);
		grnregisterreport.setDeleted_by("NA");
		grnregisterreport.setDeleted_on(ldt);
		
		grnregisterreport_DtlsReository.revertGrnregisterreport_Dtls(op.get().getGrnregisterid());
		
		Set<Grnregisterreport_Dtls> grnregisterreport_Dtls=new HashSet<Grnregisterreport_Dtls>();
		grnregisterreport_Dtls.addAll(grnregisterreport.getGrnregisterreport_Dtls());
		for(Grnregisterreport_Dtls grnDetails:grnregisterreport_Dtls) 
		{
			
			
			grnDetails.setGrnregisterid(op.get().getGrnregisterid());
			grnDetails.setGrndate(grnregisterreport.getGrndate());
			grnDetails.setCompany_id(grnregisterreport.getCompany_id());
			grnDetails.setFin_year(grnregisterreport.getFin_year());
			grnDetails.setModified_type("INSERTED");
			grnDetails.setInserted_by(grnregisterreport.getInserted_by());
			grnDetails.setInserted_on(ldt);
			grnDetails.setUpdated_by("NA");
			grnDetails.setUpdated_on(ldt);
			grnDetails.setDeleted_by("NA");
			grnDetails.setDeleted_on(ldt);
		}
		grnregisterreport.setGrnregisterreport_Dtls(grnregisterreport_Dtls);
		
		return grnregisterreportRepository.save(grnregisterreport);
	
	}
	
	@Transactional
	public Grnregisterreport delete(Grnregisterreport grnregisterreport,long id) 
	{
		Optional<Grnregisterreport> op = grnregisterreportRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Grnregisterreport grnRegister=op.get();
		
		grnRegister.setModified_type("DELETED");
		grnRegister.setInserted_by(op.get().getInserted_by());
		grnRegister.setInserted_on(op.get().getInserted_on());
		grnRegister.setUpdated_by(op.get().getUpdated_by());
		grnRegister.setUpdated_on(op.get().getUpdated_on());
		grnRegister.setDeleted_by(userRepository.getUserDetails(grnRegister.getUsername()).getName());
		grnRegister.setDeleted_on(ldt);
		
		grnregisterreport_DtlsReository.deleteGrnregisterreport_Dtls(op.get().getGrnregisterid());
		
		if(op.isPresent())
		{
			grnRegister.setId(id);
		}
		
			return grnregisterreportRepository.save(grnRegister);
	}
	
	public List<Grnregisterreport> searchGRNRegisterReport(String fromdate, String todate,String finyear)
	{
		List<Grnregisterreport> searchdaily =new ArrayList<Grnregisterreport>();
		String tablename="grnregisterreport",order_date="grndate";
		searchdaily.addAll(grnregisterreportRepository.getsearchGrnregisterreport(tablename,order_date,fromdate,todate,finyear));
		
		List<Grnregisterreport> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Grnregisterreport::getGrndate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Grnregisterreport findOne(long id) 
	{
		Optional<Grnregisterreport> op=grnregisterreportRepository.findById(id);
		return op.get();
	}
	
	public List<Grnregisterreport_Dtls> retriveGrnRegisterDetails(String grnregisterid)
	{
		List<Grnregisterreport_Dtls> details= new ArrayList<Grnregisterreport_Dtls>();
		details.addAll(grnregisterreport_DtlsReository.getdetails(grnregisterid));
		
		return details;
	}
	
	public List<GrnregisterreportDTO> grnRegisterAllDataList(String currDate,String finyear)
	{
		List<Object[]> details= new ArrayList<Object[]>();
		details.addAll(grnregisterreport_DtlsReository.getStaticData(currDate,finyear));
	    System.out.println("size::"+details.size());
		List<GrnregisterreportDTO> addData= new ArrayList<GrnregisterreportDTO>();
		 for(final Object o : details)
		    {
		        Object[] obj = (Object[]) o;
		        System.out.println("size1::"+obj[1].toString());
			Grnregisterreport sta=grnregisterreportRepository.grndetails(obj[1].toString());
			addData.add(new GrnregisterreportDTO(
					obj[1].toString(),
					sta.getGrndate(),
					sta.getGrnno(),
					sta.getBillno(),
					sta.getAdviceno(),
					sta.getSuppliername(),
					sta.getVehicleno(),
					sta.getStoreserialno(),
					obj[2].toString(),
					obj[3].toString(),
					obj[4].toString(),
					obj[5].toString(),
					obj[6].toString()
					
					));
		}
		
		
		return addData;
	}
	
	public List<GrnregisterreportDTO> searchGRNRegisterReportPriview(String fromdate, String todate,String finyear)
	{
		List<Object[]> details= new ArrayList<Object[]>();
		String tablename="grnregisterreport_Dtls",order_date="grndate";
		//System.out.println("date:"+fromdate+"//"+todate+"//"+finyear);
		details.addAll(grnregisterreportRepository.getsearchGrnregisterreportPriview(tablename,order_date,fromdate,todate,finyear));
		// System.out.println("size::"+details.size());
		List<GrnregisterreportDTO> addData= new ArrayList<GrnregisterreportDTO>();
		 for(final Object o : details)
		    {
		        Object[] obj = (Object[]) o;
		       // System.out.println("12"+obj[12].toString());
			Grnregisterreport sta=grnregisterreportRepository.grndetails(obj[12].toString());
			addData.add(new GrnregisterreportDTO(
					obj[1].toString(),
					sta.getGrndate(),
					sta.getGrnno(),
					sta.getBillno(),
					sta.getAdviceno(),
					sta.getSuppliername(),
					sta.getVehicleno(),
					sta.getStoreserialno(),
					obj[16].toString(),
					obj[13].toString(),
					obj[14].toString(),
					obj[17].toString(),
					obj[15].toString()
					));
		}
		return addData;
		
		
	}
	
}
