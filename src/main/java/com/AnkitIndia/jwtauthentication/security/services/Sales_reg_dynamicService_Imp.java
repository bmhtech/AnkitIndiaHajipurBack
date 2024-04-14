package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.*;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Sales_dynamic_sort;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.repository.Sales_dynamic_sortRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_inv_dynamic_sortingRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_reg_dynamicRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_dynamic_sortDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.sales_reg_dynamicDTO;

@Service
public class Sales_reg_dynamicService_Imp implements Sales_reg_dynamicService{
	
	@Autowired
	Sales_reg_dynamicRepository sales_reg_dynamicRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Sales_dynamic_sortRepository sales_dynamic_sortRepository;

	@Transactional
	public Sales_reg_dynamic save(Sales_reg_dynamic sales_reg_dynamic) {
		LocalDateTime ldt = LocalDateTime.now();
		sales_reg_dynamic.setSales_report(sales_reg_dynamic.getSales_report());
		sales_reg_dynamic.setReportfields(sales_reg_dynamic.getReportfields());
		sales_reg_dynamic.setReportname(sales_reg_dynamic.getReportname());
		sales_reg_dynamic.setReporttype(sales_reg_dynamic.getReporttype());
		sales_reg_dynamic.setReportlist(sales_reg_dynamic.getReportlist());
		
		sales_reg_dynamic.setTable_name(reorganizeTableName(sales_reg_dynamic.getSales_report()));
	//	sales_reg_dynamic.setTable_name(reorganizeTableName2(sales_reg_dynamic.getTable_name()));
		sales_reg_dynamic.setStatic_report(sales_reg_dynamic.getStatic_report());
		sales_reg_dynamic.setDisplay_col(sales_reg_dynamic.getDisplay_col());
		
		sales_reg_dynamic.setModified_type("INSERTED");
		sales_reg_dynamic.setInserted_by(userRepository.getUserDetails(sales_reg_dynamic.getUsername()).getName());
		//sales_reg_dynamic.setInserted_by("bb");
		sales_reg_dynamic.setInserted_on(ldt);
		sales_reg_dynamic.setUpdated_by("NA");
		sales_reg_dynamic.setUpdated_on(ldt);
		sales_reg_dynamic.setDeleted_by("NA");
		sales_reg_dynamic.setDeleted_on(ldt);
		//System.out.println("check ::"+sales_reg_dynamic.getSales_report().length());
		return sales_reg_dynamicRepository.save(sales_reg_dynamic);
	}
	


	//Sales report master Sorting
	@Transactional
	public Sales_reg_dynamic update(Sales_reg_dynamic sales_reg_dynamic,long id)
	{
		Optional<Sales_reg_dynamic> sales_report = sales_reg_dynamicRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		String static_report=reorganizeBackend(sales_reg_dynamic.getBackend());
	    sales_reg_dynamic.setSales_report(static_report);
		sales_reg_dynamic.setReportname(sales_report.get().getReportname());
		sales_reg_dynamic.setTable_name(sales_report.get().getTable_name());
		sales_reg_dynamic.setReporttype(sales_reg_dynamic.getReporttype());
		sales_reg_dynamic.setReportlist(sales_reg_dynamic.getReportlist());
		sales_reg_dynamic.setCompany_id(sales_report.get().getCompany_id());
		sales_reg_dynamic.setStatic_report(reorganizeStaticReport(static_report));
		sales_reg_dynamic.setModified_type("UPDATED");
		sales_reg_dynamic.setInserted_by(sales_report.get().getInserted_by());
		sales_reg_dynamic.setInserted_on(sales_report.get().getInserted_on());
		sales_reg_dynamic.setUpdated_by(userRepository.getUserDetails(sales_reg_dynamic.getUsername()).getName());
		sales_reg_dynamic.setUpdated_on(ldt);
		sales_reg_dynamic.setDeleted_by("NA");
		sales_reg_dynamic.setDeleted_on(sales_report.get().getDeleted_on());
		if(sales_report.isPresent())
		{
			sales_reg_dynamic.setId(id);
		}
		return sales_reg_dynamicRepository.save(sales_reg_dynamic);
		
	}
	
	//Sales report master New Sorting Update
		@Transactional
		public Sales_reg_dynamic update2(Sales_reg_dynamic sales_reg_dynamic,long id)
		{
			Optional<Sales_reg_dynamic> sales_report = sales_reg_dynamicRepository.findById(id);
			LocalDateTime ldt = LocalDateTime.now();
			//System.out.println("///"+sales_reg_dynamic.getStatic_report());
			//String static_report=reorganozedSalesSorting(sales_reg_dynamic.getStatic_report());
			
		    sales_reg_dynamic.setSales_report(sales_reg_dynamic.getStatic_report());//changes new table model
			sales_reg_dynamic.setReportname(sales_report.get().getReportname());
			sales_reg_dynamic.setTable_name(sales_report.get().getTable_name());
			sales_reg_dynamic.setCompany_id(sales_report.get().getCompany_id());
			sales_reg_dynamic.setReporttype(sales_report.get().getReporttype());//no changes 
			sales_reg_dynamic.setReportlist(sales_report.get().getReportlist());
			
			sales_reg_dynamic.setStatic_report(reOrganizeStaticReport(sales_reg_dynamic.getStatic_report(),sales_report.get().getReportlist(),sales_report.get().getReporttype()));
			
			sales_reg_dynamic.setModified_type("UPDATED");
			sales_reg_dynamic.setInserted_by(sales_report.get().getInserted_by());
			sales_reg_dynamic.setInserted_on(sales_report.get().getInserted_on());
			sales_reg_dynamic.setUpdated_by(userRepository.getUserDetails(sales_reg_dynamic.getUsername()).getName());
			sales_reg_dynamic.setUpdated_on(ldt);
			sales_reg_dynamic.setDeleted_by("NA");
			sales_reg_dynamic.setDeleted_on(sales_report.get().getDeleted_on());
			if(sales_report.isPresent())
			{
				sales_reg_dynamic.setId(id);
			}
			return sales_reg_dynamicRepository.save(sales_reg_dynamic);
			
		}	

	public List<sales_reg_dynamicDTO> getSalesRegDynamicList()
	{
		List<Sales_reg_dynamic> registerlist=new ArrayList<Sales_reg_dynamic>();
		registerlist.addAll(sales_reg_dynamicRepository.findAll());
		List<Sales_reg_dynamic> allData = registerlist
				  .stream()
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Sales_reg_dynamic::getId))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<sales_reg_dynamicDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<sales_reg_dynamicDTO> regis_list = new ModelMapper().map(allData,listType);
		
		return regis_list;
	}
	
	public Sales_reg_dynamic findOne(long id)
	{
		Optional<Sales_reg_dynamic> op=this.sales_reg_dynamicRepository.findById(id);
		return op.get();
	}

	public List<sales_reg_dynamicDTO> getSalesReportNameList()
	{
		List<Sales_reg_dynamic> registerlist=new ArrayList<Sales_reg_dynamic>();// taking table vlues into Sales_reg_dynamic model
		registerlist.addAll(sales_reg_dynamicRepository.getSalesReportNameList());//adding all database table value into array list 
		
		List<Sales_reg_dynamic> allData = registerlist //mapping list to new register list 
				  .stream()
				  .sorted(Comparator.comparing(Sales_reg_dynamic::getReportname))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<sales_reg_dynamicDTO>>() {}.getType(); //declaring  a new type list 
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<sales_reg_dynamicDTO> regis_list = new ModelMapper().map(allData,listType);  // comparing type list data to array list data with dto
		
		return regis_list;
	}
	
	
	public List<Sales_reg_dynamic> getRows(String reportname,String fromdate,String todate){
		List dynamicview=new ArrayList();
		String reportlist=sales_reg_dynamicRepository.getReportNameList(reportname);
		
		System.out.println("reportlist/ "+reportlist +" / " + fromdate + " / " + todate + " / " + reportname);
		String reporttype[]=reportlist.split(",");
		if(reporttype[1].compareToIgnoreCase("salesreport")==0)
		{
			if(reporttype[0].compareToIgnoreCase("salesinvoice")==0)
			{
				//dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesInvoiceProcedure(reportname,fromdate,todate));
				dynamicview.addAll(sales_reg_dynamicRepository.getSalesInvoiceReport(fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("salesenquiry")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesEnquiryProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("salesquotation")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesQuotationProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("salesorder")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesOrderProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("deliverychallan")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesDeliverychallanProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("returnapprovalnote")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesReturnapprovalnoteProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("salesreturnnote")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesReturnnoteProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("salescreditnote")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesCreditnoteProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("salesgatepass")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsSalesgatepassProcedure(reportname,fromdate,todate));
			}
		}
		else if(reporttype[1].compareToIgnoreCase("purchasereport")==0)
		{
			if(reporttype[0].compareToIgnoreCase("indentorder")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurIndentorderProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("purchaseenquiry")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurEnquiryProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("purchasequotation")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurQuotationProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("purchaseorder")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurOrderProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("qualitycheck")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurQualitycheckProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("peripheralqualitycheck")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurPeripheralqualitycheckProcedure(reportname,fromdate,todate));
			}		
			else if(reporttype[0].compareToIgnoreCase("purchasegoodsreceipt")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurGoodsreceiptProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("l1selection")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurL1selectionProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("purchasebill")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurchasebillProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("purchasereturnappovalnote")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurReturnappovalnoteProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("purchasereturnnote")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurReturnnoteProcedure(reportname,fromdate,todate));
			}
			else if(reporttype[0].compareToIgnoreCase("debitnote")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsPurDebitnoteProcedure(reportname,fromdate,todate));
			}
		}
		if(reporttype[1].compareToIgnoreCase("weigmentreport")==0)
		{
			if(reporttype[0].compareToIgnoreCase("weightment")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsweighment(reportname,fromdate,todate));
			}
			if(reporttype[0].compareToIgnoreCase("unloadadvice")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsUnloadadvice(reportname,fromdate,todate));
			}
			if(reporttype[0].compareToIgnoreCase("loadingadvice")==0)
			{
				dynamicview.addAll(sales_reg_dynamicRepository.getRowsLoadingadvice(reportname,fromdate,todate));
			}
		}
		
		//dynamicview.addAll(sales_reg_dynamicRepository.getRows(reportname));
		return dynamicview;
	}
	
	public List<Sales_reg_dynamic> getColumn(String reportname){
		List dynamicview=new ArrayList();
	String  values=sales_reg_dynamicRepository.getColumn(reportname).toString();//backend = slaes report 
	//System.out.println(reportname+" values :: "+values);
	String firstsplit=values.replace("[", "");
	String secondsplit=firstsplit.replace("]", "");
	String splitvalues [] =secondsplit.split(",");
	for(int i=0;i<splitvalues.length;i++)
	{
		//System.out.println(" hey  "+ splitvalues[i] +" / " + sales_dynamic_sortRepository.getColName(splitvalues[i]));//search with backend 
		dynamicview.add(sales_dynamic_sortRepository.getColName(splitvalues[i]));
	}
	
		//dynamicview.addAll(sales_reg_dynamicRepository.getColumn(reportname));
		
		return dynamicview;
	}
	
	
	public List<Sales_dynamic_sortDTO> salesDynamicSort(long reportname){
		List dynList=new ArrayList(); 
		//dynList.addAll(sales_reg_dynamicRepository.salesDynamicSortList(reportname));
	
		dynList.add((sales_reg_dynamicRepository.salesDynamicSortList(reportname)).getSales_report());
		
		//salesreprot,reporttype,reportlist
		//System.out.println(" tuhin here  : "+dynList.size()+"//"+dynList.toString());
		String fsttime=dynList.toString().replace("[", "");
		String sttime=fsttime.replace("]", "");
	
		
		//System.out.println("check:"+sttime);
		//System.out.println(" tuhin here  : "+reporttypelist.size()+ reporttypelist.toString() + " / " + report_list.toString() );
		
		String [] breakup=sttime.split(",");
		ArrayList<Sales_dynamic_sort> dList=new ArrayList<Sales_dynamic_sort>();
		for(int i=0;i<breakup.length;i++)
		{
			//System.out.println("check loop:"+breakup.length+"//"+breakup[i]);
			dList.addAll(salessortde(breakup[i]));
		}



		List<Sales_dynamic_sort> allData = dList //mapping list to new register list 
				  .stream()
				 // .sorted(Comparator.comparing(Sales_dynamic_sort::getDynamic))
				  .collect(Collectors.toList());
		
		
		Type listType = new TypeToken<List<Sales_dynamic_sortDTO>>() {}.getType(); 
		List<Sales_dynamic_sortDTO> regis_list = new ModelMapper().map(allData,listType);
		//System.out.println("hello here "+regis_list.toString());
		return regis_list;
	}
	
	public List<Sales_dynamic_sort> salessortde(String as){
		ArrayList<Sales_dynamic_sort> dert=new ArrayList<Sales_dynamic_sort>();
			dert.addAll(sales_reg_dynamicRepository.salesDynamicMultiList(as));
			
			return dert;
	}
	
	
	public String  reorganizeBackend(String backend){
		
		String replacespecialcharacter=backend.replaceAll("\"", "");
		String finaloutput=replacespecialcharacter.substring(0, replacespecialcharacter.length()-1);
		String splitvalues[]=finaloutput.split(",");
		
		LinkedHashSet<String> uniquevalues = new LinkedHashSet<String>();
		for(int i=0;i<splitvalues.length;i++) 
		{
			uniquevalues.add(splitvalues[i]);
		}
		
		String semi_output="";
		 Iterator<String> i = uniquevalues.iterator();
		 
	        // Holds true till there is single element remaining
	        while (i.hasNext())
	        {
	        	semi_output +=i.next()+",";
	        	
	        }
	       String finaloutput_almost=semi_output.substring(0, semi_output.length()-1);
	
		return finaloutput_almost;
	}
	// datacolumn where  staticreport,reportlist,reporttype
	public String reOrganizeStaticReport(String staticdata,String reportlist,String reporttype)
	{
		String [] data=staticdata.split(",");
		ArrayList<String> staticData = new ArrayList<String>();
		for(int h=0;h<data.length;h++)
		{

			staticData.addAll(sales_dynamic_sortRepository.salesreportsorting_staticfield(data[h],reporttype,reportlist));
		}
		
		String firstrepalce=staticData.toString().replace("[", "");
		String[] secondrepalce=firstrepalce.replace("]", "").split(",");
		String output="";
		for(int i=0;i<secondrepalce.length;i++)
		{
			if(i==0) 
			{
				output +=secondrepalce[i]+",";
			}
			else 
			{
				output +=secondrepalce[i].substring(1, secondrepalce[i].length())+",";
			}
		}
			String finaloutput=output.substring(0, output.length()-1);
		return finaloutput;	
	}
	
	public String reorganizeStaticReport(String staticReport) {
		
		String [] data=staticReport.split(",");
		ArrayList<String> static_inputs = new ArrayList<String>();
		for(int h=0;h<data.length;h++)
		{
			static_inputs.addAll(sales_dynamic_sortRepository.salesDynamicsortListByName(data[h]));
		}
		
		String firstrepalce=static_inputs.toString().replace("[", "");
		String[] secondrepalce=firstrepalce.replace("]", "").split(",");
		String output="";
		for(int i=0;i<secondrepalce.length;i++)
		{
			if(i==0) 
			{
				output +=secondrepalce[i]+",";
			}
			else 
			{
				output +=secondrepalce[i].substring(1, secondrepalce[i].length())+",";
			}
			
		}
		String finaloutput=output.substring(0, output.length()-1);
		
		return finaloutput;
	}
	
	public String reorganozedSalesSorting(String staticreport)
	{
		String [] data=staticreport.split(",");
		System.out.println("//"+data.length);
		ArrayList<String> staticReport = new ArrayList<String>();
		for(int h=0;h<data.length;h++)
		{
			staticReport.addAll(sales_dynamic_sortRepository.salesBackendsortListByName(data[h]));
		}
		String firstside=staticReport.toString().replace("[", "");
		String[] secondside=firstside.replace("]", "").split(",");
		String output="";
		for(int i=0;i<secondside.length;i++)
		{
			if(i==0) 
			{
				output +=secondside[i]+",";
			}
			else 
			{
				output +=secondside[i].substring(1, secondside[i].length())+",";
			}
		}
		String finalResult=output.substring(0, output.length()-1);
		System.out.println("check result//"+finalResult);
		return finalResult;
	}
	
	public String reorganizeTableName(String tableName) {
		
		String [] splitoutput=tableName.split(",");
		
		ArrayList<String> columnnamesplits =new ArrayList<String>();
		
		for(int j=0;j<splitoutput.length;j++) 
		{
			String inside [] =splitoutput[j].split("\\.");
			
			columnnamesplits.add(inside[0]);
		}
		HashSet<String> unique = new HashSet<String>();
		for(int v=0;v<columnnamesplits.size();v++)
		{
			unique.add(columnnamesplits.get(v));
		}
		
		
		
		
		String semi_output="";
		 Iterator<String> i = unique.iterator();
		 
	        // Holds true till there is single element remaining
	        while (i.hasNext())
	        {
	        	semi_output +=i.next()+",";
	        	
	        }
	       String ultimatevalue= semi_output.substring(0, semi_output.length()-1);
		
		
		return ultimatevalue;
	}
	
public String reorganizeTableName2(String tableName) {
		
		String [] splitoutput=tableName.split(",");
		
		HashSet<String> unique = new HashSet<String>();
		for(int j=0;j<splitoutput.length;j++) 
		{
			unique.add(splitoutput[j]);
			
		}
		String semi_output="";
		 Iterator<String> i = unique.iterator();
		 
	        // Holds true till there is single element remaining
	        while (i.hasNext())
	        {
	        	semi_output +=i.next()+",";
	        	
	        }
	       String ultimatevalue= semi_output.substring(0, semi_output.length()-1);
		
		
		return ultimatevalue;
	}


public List<Sales_reg_dynamic> getSalesRegDuplicateCheck()
{
	List<Sales_reg_dynamic> registerlist=new ArrayList<Sales_reg_dynamic>();
	registerlist.addAll(sales_reg_dynamicRepository.getSalesRegDuplicateCheck());
	
	return registerlist;
}

//Sales report master delete
	/*@Transactional
	public Sales_reg_dynamic deleteSalesRegDynamic(Sales_reg_dynamic sales_reg_dynamic,long id)
	{
		System.out.println("chk id::"+id);
		Optional<Sales_reg_dynamic> op = sales_reg_dynamicRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Sales_reg_dynamic salesReg=op.get();

		salesReg.setInserted_by(op.get().getInserted_by());
		salesReg.setInserted_on(op.get().getInserted_on());
		salesReg.setUpdated_by(op.get().getUpdated_by());
		salesReg.setUpdated_on(op.get().getUpdated_on());
		salesReg.setDeleted_by(userRepository.getUserDetails(salesReg.getUsername()).getName());
		//salesSort.setDeleted_by("bb");
		salesReg.setDeleted_on(ldt);
		salesReg.setModified_type("DELETED");
		
		if(op.isPresent())
		{
			salesReg.setId(id);
		}
		return sales_reg_dynamicRepository.save(salesReg);
	}*/

	@Transactional
	public StatusDTO deleteSalesRegDynamic(long id)
	{
		System.out.println("chk id::"+id);
		StatusDTO result = new StatusDTO();
		sales_reg_dynamicRepository.deleteSalesRegDynamic(id);
		result.setStatus("Yes");
		return result;
	}
public List<sales_reg_dynamicDTO> reportTypeDropdownList(String reporttype)
{
	List<Sales_reg_dynamic> registerlist=new ArrayList<Sales_reg_dynamic>();// taking table vlues into Sales_reg_dynamic model
	registerlist.addAll(sales_reg_dynamicRepository.reportTypeDropdownList(reporttype));//adding all database table value into array list 
	//System.out.println("get//"+registerlist.size());
	List<Sales_reg_dynamic> allData = registerlist //mapping list to new register list 
			  .stream()
			  .sorted(Comparator.comparing(Sales_reg_dynamic::getReportname))
			  .collect(Collectors.toList());
	// Create Conversion Type
	Type listType = new TypeToken<List<sales_reg_dynamicDTO>>() {}.getType(); //declaring  a new type list 
	
	// Convert List of Entity objects to a List of DTOs objects 
	List<sales_reg_dynamicDTO> regis_list = new ModelMapper().map(allData,listType);  // comparing type list data to array list data with dto
	
	return regis_list;
}

}
