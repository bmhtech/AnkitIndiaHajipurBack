package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Exporttotally.Tallyrequest_Suppliermaster;
import com.AnkitIndia.Exporttotally.Tallyrequest_TransportJV;
import com.AnkitIndia.Utility.NumToWord;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Termination_dyn;
import com.AnkitIndia.jwtauthentication.model.Sales_transport;
import com.AnkitIndia.jwtauthentication.model.Salestransport_app_chgs;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Wheatacceptance;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Item_Dtls_for_jobworkRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_transportRepository;
import com.AnkitIndia.jwtauthentication.repository.Salestransport_app_chgsRepository;
import com.AnkitIndia.jwtauthentication.repository.Tds_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Sales_transportService_Imp implements Sales_transportService{
	
	@Autowired
	Sales_transportRepository sales_transportRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Salestransport_app_chgsRepository salestransport_app_chgsRepository;
	
	@Autowired
	Tds_masterRepository tds_masterRepository;
	
	@Autowired
	Delivery_challan_Item_Dtls_for_jobworkRepository delivery_challan_Item_Dtls_for_jobworkRepository;
	
	@Autowired
	Delivery_challan_Item_DtlsRepository delivery_challan_Item_DtlsRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	public SequenceIdDTO getTransJVCode(String prefix,String currDate)
	{
		int slno=0;
		
		String sno=sales_transportRepository.countTransJVCode(currDate);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = currDate.split("-");
		prefix=prefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		System.out.println("CHECK : "+genCode);
		
		return genCode;
	}
	
	@Transactional
	public Sales_transport save(Sales_transport sales_transport) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(sales_transportRepository.countId() != null ) {
			slno=Long.parseLong(sales_transportRepository.countId());
		}
		String prefix="TSR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		sales_transport.setSales_tranport_id(gen_sno);
		
		
		//starts here 
		int slno1=0;
		
        String sno1=sales_transportRepository.countTransJVCode(sales_transport.getJvdate());
		
		if(sno1 != null ) {
			slno1=Integer.parseInt(sno1);
		}
		//String date[] = sales_transport.getCurrentdate().split("-");
		String date[] = sales_transport.getJvdate().split("-");
		
		String prefix1="TRANS";
		prefix1=prefix1+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		
		
		String gen_sno1=UniqueIDTransaction.uniqueId4(prefix1,slno1);
		
		//ends here
		
		sales_transport.setTrans_jv_no(gen_sno1);

		sales_transport.setCompany_id(sales_transport.getCompany_id());
		sales_transport.setFin_year(sales_transport.getFin_year());
		sales_transport.setModified_type("INSERTED");
		sales_transport.setInserted_on(ldt);
		sales_transport.setInserted_by(userRepository.getUserDetails(sales_transport.getUsername()).getName());
		sales_transport.setUpdated_by(sales_transport.getUpdated_by());
		sales_transport.setUpdated_on(ldt);
		sales_transport.setDeleted_by("NA");
		sales_transport.setDeleted_on(ldt);
		
		Set<Salestransport_app_chgs> salesappchgsSet = new HashSet<Salestransport_app_chgs>();
		salesappchgsSet.addAll(sales_transport.getSalestransport_app_chgs());
		for(Salestransport_app_chgs sChgs : salesappchgsSet)
		{
			sChgs.setSales_tranport_id(gen_sno);
			sChgs.setCompany_id(sales_transport.getCompany_id());
			sChgs.setFin_year(sales_transport.getFin_year());
			sChgs.setModified_type("INSERTED");
			sChgs.setInserted_by(sales_transport.getInserted_by());
			sChgs.setInserted_on(sales_transport.getInserted_on());
			sChgs.setUpdated_by("NA");
			sChgs.setUpdated_on(ldt);
			sChgs.setDeleted_by("NA");
			sChgs.setDeleted_on(ldt);
		
		}
		sales_transport.setSalestransport_app_chgs(salesappchgsSet);
		
		
		return sales_transportRepository.save(sales_transport);	
	}

	public Sales_transport findOne(long id) 
	{
		Optional<Sales_transport> op=sales_transportRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Sales_transport update(Sales_transport sales_transport,long id) 
	{
		Optional<Sales_transport> op = sales_transportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		
		sales_transport.setSales_tranport_id(op.get().getSales_tranport_id());
		sales_transport.setTrans_jv_no(op.get().getTrans_jv_no());	
		sales_transport.setExport(op.get().getExport());
		sales_transport.setResponse(op.get().getResponse());	
		sales_transport.setCompany_id(sales_transport.getCompany_id());
		sales_transport.setFin_year(sales_transport.getFin_year());
		sales_transport.setModified_type("INSERTED");
		sales_transport.setInserted_on(op.get().getInserted_on());
		sales_transport.setInserted_by(op.get().getInserted_by());
		sales_transport.setUpdated_by(userRepository.getUserDetails(sales_transport.getUsername()).getName());
		sales_transport.setUpdated_on(ldt);
		sales_transport.setDeleted_by("NA");
		sales_transport.setDeleted_on(ldt);
		
		salestransport_app_chgsRepository.salestransport_app_chgsupdate(op.get().getSales_tranport_id());
		Set<Salestransport_app_chgs> salesappchgsSet = new HashSet<Salestransport_app_chgs>();
		salesappchgsSet.addAll(sales_transport.getSalestransport_app_chgs());
		for(Salestransport_app_chgs sChgs : salesappchgsSet)
		{
			sChgs.setSales_tranport_id(op.get().getSales_tranport_id());
			sChgs.setCompany_id(sales_transport.getCompany_id());
			sChgs.setFin_year(sales_transport.getFin_year());
			sChgs.setModified_type("INSERTED");
			sChgs.setInserted_on(op.get().getInserted_on());
			sChgs.setInserted_by(op.get().getInserted_by());
			sChgs.setUpdated_by(userRepository.getUserDetails(sales_transport.getUsername()).getName());
			sChgs.setUpdated_on(ldt);
			sChgs.setDeleted_by("NA");
			sChgs.setDeleted_on(ldt);
		}
		sales_transport.setSalestransport_app_chgs(salesappchgsSet);
		
		return sales_transportRepository.save(sales_transport);
	}
	
	
	/*public List<Map<String, Object>> getSalesTransactionReport(String fromdate,String todate)
	{
		List<Map<String, Object>> outputReport=sales_transportRepository.getSalesTransactionReport(fromdate,todate);
		 
	     return outputReport;
	}*/
	
	public List<Map<String, Object>> getSalesTransactionReport(String fromdate,String todate,String challanno,String salestype,String finyear,String jvnum,String date_search_type)
	{
		List<Map<String,Object>> serchdata =new ArrayList<Map<String,Object>>();
		String tablename="sales_transport",invtype="challanno",challanno1="challanno",jvno="trans_jv_no",currentdate="";
		//String buname="";
		//System.out.println("sales_transport"+tablename+"//"+invtype+"//"+challanno1+"//"+salestype+"//"+challanno+"//"+fromdate+"//"+todate+"//"+finyear+"//"+jvno+"//"+jvnum);
		
		if(date_search_type.compareToIgnoreCase("Current Date")==0)
		{
			 currentdate="currentdate";
		}
		if(date_search_type.compareToIgnoreCase("Challan Date")==0)
		{
			currentdate="challandate";
		}
		if(date_search_type.compareToIgnoreCase("JV Date")==0)
		{
			currentdate="jvdate";
		}
		System.out.println("sales_transport"+tablename+"//"+invtype+"//"+challanno1+"//"+currentdate+"//"+salestype+"//"+challanno+"//"+fromdate+"//"+todate+"//"+finyear+"//"+jvno+"//"+jvnum);
		serchdata.addAll(sales_transportRepository.getSearchTransport(tablename,invtype,challanno1,currentdate,salestype,challanno,fromdate,todate,finyear,jvno,jvnum));
	    return serchdata;
	}
	
	
	
	public List<Map<String, Object>> getSalesTransactionReportCheckbalance(String fromdate,String todate,String inv_typenew,String trans_code)
	{
		List<Map<String, Object>> outputReport=new ArrayList<Map<String,Object>>();
		
		if(trans_code.compareToIgnoreCase("No")==0)				
		{
			outputReport.addAll(sales_transportRepository.getSalesTransactionReportCheckbalance(fromdate,todate,inv_typenew));
		}
		else 
		{
			outputReport.addAll(sales_transportRepository.getSalesTransactionReportCheckbalancewithTrans(fromdate,todate,inv_typenew,trans_code));
		}
		 
	     return outputReport;
	}
	
	public List<Map<String, Object>> getSalesTransactionReportList(String currentdate)
	{
		List<Map<String, Object>> outputReport=sales_transportRepository.getSalesTransactionReportList(currentdate);
		 
	     return outputReport;
	}
	
	public List<Map<String, Object>> getSalestransportappchgsList(String sales_tranport_id)
	{
		List<Map<String, Object>> outputReport=salestransport_app_chgsRepository.getSalestransportappchgsList(sales_tranport_id);
		 
	     return outputReport;
	}
	
	public Map<String, Object> getSalesTransport(String id)
	{
		return sales_transportRepository.getSalesTransport(id);
	}
	
	public List<Map<String, Object>> getSalesTransportChgs(String transportId)
	{
		return salestransport_app_chgsRepository.getSalesTransportChgs(transportId);
	}
	
	
	@Transactional
	public Sales_transport accountpostingsalestransport(long id) 
	{
		
		Optional<Sales_transport> op=sales_transportRepository.findById(id);
		
		String output;
		String date="",voucherno="",tds="",roundoff="",transportername="",transportationcharges="";
		double amount=0.00,roundoffamount=0.00,tdsamount=0.00,transportationamount=0.00;
		boolean tdsstatus=false,roundoffstatus=false,rounddrcr=false;
		
		Tallyrequest_TransportJV tally=new Tallyrequest_TransportJV();
		try  
		{
			
			//transportationcharges="TRANSPORTATION CHRG - FIN MISC (P/L)";
			//transportationcharges="TRANSPORTATION CHARGES-ARMY";
			
			//String challanno=sales_transportRepository.getSalesTransport(Long.toString(id)).get("challanno").toString(); // Pull out string from Map<String,Object>
			transportationcharges=sales_transportRepository.getSalesTransportLedger(Long.toString(id));
			System.out.println("transportationcharges:: "+transportationcharges);
			
			transportationamount=op.get().getBalance_amt();
			
			//System.out.println("transportationamount"+transportationamount);
			//date=op.get().getLoadingdate();
			LocalDateTime ldt = LocalDateTime.now();
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String  date1=ldt.format(dtf);
			
			
			
			date=date1.substring(0,10);
			//String date2[] = date1.split(" ");
			//date=date2[0];
			System.out.println("date:::::"+date);
			voucherno=op.get().getTrans_jv_no();
			if(op.get().getTds_rate()==0)
			{
				tds="";
			}
			else {
			  tds= tds_masterRepository.gettdsname(op.get().getTds_rate()).getTds_accname();
			}
			tdsamount=op.get().getTds_amt();
			//System.out.println("tdsamount"+tdsamount);
			roundoffamount=op.get().getRound_off();
			transportername=op.get().getTransname();
			amount=op.get().getPay_amt();
			//System.out.println(" round off "+op.get().getRound_off());
			if(String.valueOf(op.get().getRound_off()).contains("-"))
			{
				rounddrcr=true;
			}
			//System.out.println("rounddrcr :: "+ rounddrcr);
           
			if(tdsamount>0) 
			{
				tdsstatus=true;
			}
			if(roundoffamount!=0) 
			{
				roundoffstatus=true;
			}
			if(transportername.contains("&"))
			{
				transportername=transportername.replaceAll("&","&amp;");
			}
			
			date= op.get().getJvdate().replace("-", ""); 
			//date=date.replace("-", "");
			
			String remarks = op.get().getRemarks();
			if(Utility.isNullOrEmpty(remarks)) 
			{
				
			}
			else 
			{
				remarks="";
			}
			String referanceno = op.get().getReferance_no();
			if(Utility.isNullOrEmpty(referanceno)) 
			{
				
			}
			else 
			{
				referanceno="";
			}
			
			//System.out.println("date::"+date+" :voucherno: "+voucherno+" :tds: "+tds+" :tdsamount: "+tdsamount+" :roundoff: "+roundoff+" :roundoffamount: "+roundoffamount+" :transportername: "+transportername+" :amount: "+amount+" :tdsstatus: "+tdsstatus+" :roundoffstatus: "+roundoffstatus+" :transportationcharges: "+transportationcharges+" :transportationamount: "+transportationamount+" :rounddrcr: "+rounddrcr);
			output=tally.SendToTally(date,voucherno,tds,tdsamount,roundoff,roundoffamount,transportername,amount,tdsstatus,roundoffstatus,transportationcharges,transportationamount,rounddrcr,remarks,referanceno);
			
			
			String [] splitoutput = output.split("\\|\\|");
			
			
			
			System.out.println(id + " / "+splitoutput[0] + " / " +Integer.parseInt(splitoutput[1]));
			sales_transportRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
		 }
		 catch(Exception e)
		 {
		
			System.out.println(e);
		 }
		
		Optional<Sales_transport> op1=sales_transportRepository.findById(id);
		System.out.println(op1.get());
		
		return op1.get();
	}
	
	@Transactional
	public Sales_transport accountpostingsalestransportundo(long id) 
	{
		sales_transportRepository.exportuomtallyundo(id);
		
		Optional<Sales_transport> op1=sales_transportRepository.findById(id);
		System.out.println(op1.get());
		
		return op1.get();
	}
	
	public List<Map<String, Object>> getDuplicateRefTransport()
	{
		return sales_transportRepository.getDuplicateRefTransport();
	}
	
	public List<Map<String, Object>> searchTransportStatement(String fromdate,String todate,String invoicetype)
	{
		List<Map<String, Object>> transportStatement=sales_transportRepository.searchTransportStatement(fromdate,todate,invoicetype);
		 
	     return transportStatement;
	}

	public StatusDTO checkBulkSupply(String adviceid) 
	{
		StatusDTO res =new StatusDTO();
		Wm_loading_advice op = wm_loading_adviceRepository.loadingAdviceVehicleList(adviceid);
		
		if(op.isLooseitem())
		{
			 res.setStatus("YES");
		}
		else {
			 res.setStatus("NO");
		}
		/*int checkstatus=1;
		
		if(invid.compareToIgnoreCase("INV00003")==0)
		{
			checkstatus=delivery_challan_Item_Dtls_for_jobworkRepository.searchJobItem(delivery_cid);
			System.out.println("Enter INV00003 :: "+checkstatus);
		}
		else
		{
			checkstatus=delivery_challan_Item_DtlsRepository.searchBulkItem(delivery_cid);
			System.out.println("Enter else :: "+checkstatus);
		}
		System.out.println("checkstatus :: "+checkstatus);
		if(checkstatus == 1) 
		 {
			res.setStatus("NO");
		 }
		 else 
		 {
			 res.setStatus("YES");
		 }*/
		return res;
	}
	
	@Transactional
	public Sales_transport delete(Sales_transport sales_transport,long id,String reason)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Sales_transport> op = sales_transportRepository.findById(id);
		
		Sales_transport st=op.get();
		st.setDel_remarks(reason);		
		st.setModified_type("DELETED");
		st.setInserted_by(op.get().getInserted_by());
		st.setInserted_on(op.get().getInserted_on());
		st.setUpdated_by(op.get().getUpdated_by());
		st.setUpdated_on(op.get().getUpdated_on());
		st.setDeleted_by(userRepository.getUserDetails(st.getUsername()).getName());
		st.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			st.setId(id);
		}
		
		salestransport_app_chgsRepository.deleteSTACDetails(op.get().getSales_tranport_id(),
								ldt,userRepository.getUserDetails(st.getUsername()).getName());
		
		return sales_transportRepository.save(st);
	}
}
