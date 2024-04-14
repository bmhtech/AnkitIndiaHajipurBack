package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
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
import org.springframework.stereotype.Service;

import com.AnkitIndia.Exporttotally.Tallyrequest_ItemMaster;
import com.AnkitIndia.Exporttotally.Tallyrequest_ProductionReg;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.model.Item_master_stat_info;
import com.AnkitIndia.jwtauthentication.model.Prod_summary_dtls;
import com.AnkitIndia.jwtauthentication.model.Prodsummary;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_sales_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.ProdsummaryRepository;
import com.AnkitIndia.jwtauthentication.repository.ProdsummarydtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class ProdsummaryService_Imp implements ProdsummaryService
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	ProdsummaryRepository prodsummaryRepository;
	
	@Autowired
	ProdsummarydtlsRepository prodsummarydtlsRepository;
	
	@Autowired
	Item_group_master_sales_accRepository item_group_master_sales_accRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	public SequenceIdDTO  getProductionsummurynumber(String finyear) 
	{
	    int slno=0;
	    String sno=prodsummaryRepository.countProd_summary();
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		
		String fin_yearspit[]=finyear.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="PS-"+final_fyear+"-";
		
		String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			
        Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		
		return genCode;
	}
	
	
	@Transactional
	public Prodsummary save(Prodsummary prodsummary)
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=prodsummaryRepository.countProd_summary();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=prodsummary.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			
			String prefix="PS-"+final_fyear+"-";
			
			String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			prodsummary.setProd_sum_id(gen_sno);
			
			prodsummary.setModified_type("INSERTED");
			prodsummary.setInserted_by(userRepository.getUserDetails(prodsummary.getUsername()).getName());
			prodsummary.setInserted_on(ldt);
			prodsummary.setUpdated_by("NA");
			prodsummary.setUpdated_on(ldt);
			prodsummary.setDeleted_by("NA");
			prodsummary.setDeleted_on(ldt);
		
			Set<Prod_summary_dtls> prod_summary_dtls=new HashSet<Prod_summary_dtls>();
			prod_summary_dtls.addAll(prodsummary.getProd_summary_dtls());
			for(Prod_summary_dtls psDetails:prod_summary_dtls) 
			{
				
				psDetails.setProd_sum_id(gen_sno);
				
				//psDetails.setItem_name(item_masterRepository.itemNameById(psDetails.getItem()).getItem_name());
				
				psDetails.setCompany_id(prodsummary.getCompany_id());
				psDetails.setFin_year(prodsummary.getFin_year());
				psDetails.setModified_type("INSERTED");
				psDetails.setInserted_by(prodsummary.getInserted_by());
				psDetails.setInserted_on(ldt);
				psDetails.setUpdated_by("NA");
				psDetails.setUpdated_on(ldt);
				psDetails.setDeleted_by("NA");
				psDetails.setDeleted_on(ldt);
				psDetails.setUsername(prodsummary.getUsername());
			}
			prodsummary.setProd_summary_dtls(prod_summary_dtls);
			
		return prodsummaryRepository.save(prodsummary);
		
	}
	
	public List<Map<String,Object>> Productionsummaryitemdetails(String proddate)
	{
		 List<Map<String,Object>> itemdetails= new ArrayList<>();
		 
		 itemdetails.addAll(prodsummaryRepository.productionregoutput(proddate));
		 //itemdetails.addAll(prodsummaryRepository.productionspecialoutput(proddate));
		 return itemdetails;
		
	}
	
	public List<Map<String,Object>> getProductionSummurylist()
	{
		return prodsummaryRepository.getProductionSummurylist();
	}
	
	@Transactional
	public Prodsummary update(Prodsummary prodsummary,long id) 
	{
		Optional<Prodsummary> op =prodsummaryRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		prodsummary.setProd_sum_id(op.get().getProd_sum_id());
		prodsummary.setCompany_id(prodsummary.getCompany_id());
		prodsummary.setFin_year(prodsummary.getFin_year());
		prodsummary.setModified_type("INSERTED");
		prodsummary.setInserted_on(ldt);
		prodsummary.setInserted_by(userRepository.getUserDetails(prodsummary.getUsername()).getName());
		prodsummary.setUpdated_by(prodsummary.getUpdated_by());
		prodsummary.setUpdated_on(ldt);
		prodsummary.setDeleted_by("NA");
		prodsummary.setDeleted_on(ldt);
	
		prodsummarydtlsRepository.retriveProdSumDtls(op.get().getProd_sum_id());
		
		Set<Prod_summary_dtls> prod_summary_dtls=new HashSet<Prod_summary_dtls>();
		prod_summary_dtls.addAll(prodsummary.getProd_summary_dtls());
		for(Prod_summary_dtls sDetails:prod_summary_dtls) 
		{
			sDetails.setProd_sum_id(op.get().getProd_sum_id());
			sDetails.setCompany_id(prodsummary.getCompany_id());
			sDetails.setFin_year(prodsummary.getFin_year());
			sDetails.setModified_type("INSERTED");
			sDetails.setInserted_by(prodsummary.getInserted_by());
			sDetails.setInserted_on(ldt);
			sDetails.setUpdated_by(userRepository.getUserDetails(prodsummary.getUsername()).getName());
			sDetails.setUpdated_on(ldt);
			sDetails.setDeleted_by("NA");
			sDetails.setDeleted_on(ldt);
			sDetails.setUsername(prodsummary.getUsername());
		}
		prodsummary.setProd_summary_dtls(prod_summary_dtls);
		
		return prodsummaryRepository.save(prodsummary);
	}
	
	@Transactional
	public Prodsummary delete(Prodsummary prodsummary,long id) 
	{
		Optional<Prodsummary> op = prodsummaryRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Prodsummary summary=op.get();
		
		summary.setModified_type("DELETED");
		summary.setInserted_by(op.get().getInserted_by());
		summary.setInserted_on(op.get().getInserted_on());
		summary.setUpdated_by(op.get().getUpdated_by());
		summary.setUpdated_on(op.get().getUpdated_on());
		summary.setDeleted_by(userRepository.getUserDetails(summary.getUsername()).getName());
		summary.setDeleted_on(ldt);
		
		prodsummarydtlsRepository.deleteProdSumDtls(op.get().getProd_sum_id());
		
		if(op.isPresent())
		{
			summary.setId(id);
		}
	  return prodsummaryRepository.save(summary);
	}
	
	public Prodsummary findOne(long id) 
	{
		Optional<Prodsummary> op=prodsummaryRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String,Object>> getProdSumDtls(String prod_sum_id)
	{
		return prodsummarydtlsRepository.getProdSumDtls(prod_sum_id);
	}
	
	public StatusDTO Productionsummarydatecheck(String date)
	{
		StatusDTO res =new StatusDTO();
		res.setStatus(prodsummaryRepository.Productionsummarydatecheck(date));
		return res;
	}
	
	/*@Transactional
	public Prodsummary productionposting(long id) 
	{
		Prodsummary summaryData=prodsummaryRepository.getSummaryDetails(id);
		
		String output;
		Tallyrequest_ProductionPosting tally=new Tallyrequest_ProductionPosting();
		try  
		{
			
			String date=summaryData.getDate();
			Double totalrate=summaryData.getTotalrate();
			Double totalamount=summaryData.getTotalamount();
			Double totalbags=summaryData.getTotalbags();
			Double totalqty=summaryData.getTotalqty();
			
			
			output=tally.SendToTally(date,totalrate,totalamount,totalbags,totalqty);
			
			System.out.println(" output :: "+output);
			String [] splitoutput = output.split("\\|\\|");
			System.out.println("export status ::"+splitoutput[1]);
			System.out.println(splitoutput[0] + " / " + splitoutput[1]+"/" +id);
			
			int exportstatus=0;
			if(Integer.parseInt(splitoutput[1])==1) 
			{
				exportstatus=1;
			}
			if(Integer.parseInt(splitoutput[2])==1) 
			{
				exportstatus=1;
			}
			prodsummaryRepository.exportuomtally(id,splitoutput[0],exportstatus);
			System.out.println();
		
		}
		catch(Exception e)
		{
		
			System.out.println(e);
		}
		
		Prodsummary summaryData1=prodsummaryRepository.getSummaryDetails(id);
		
		return summaryData1;
	}*/
	
	@Transactional
	public StatusDTO ProdSummaryPosting(long id)
	{
		StatusDTO res=new StatusDTO();
		
		Prodsummary summaryData=prodsummaryRepository.getSummaryDetails(id);
	
				
		List<Prod_summary_dtls>details=prodsummarydtlsRepository.retriveProdSumDtlslist(summaryData.getProd_sum_id());
				
		String item_name_ledger[]=new String[details.size()];
        String item_name[]=new String[details.size()];
        String item_amount[]=new String[details.size()];
        String item_rate[]=new String[details.size()];
        String item_passeditemqty[]=new String[details.size()];
        String item_uom[]=new String[details.size()];
        String packing_uom[]=new String[details.size()];
        String packing_qty[]=new String[details.size()];
        String price_based_on[]=new String[details.size()];
        
        double item_total=summaryData.getTotalamount(),totalqty=summaryData.getTotalqty(),totalbags=summaryData.getTotalbags();
        for(int i=0;i<details.size();i++) 
        {
        	 String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(details.get(i).getItem()).getItem_group();
     		 Item_group_master_sales_acc itemgroup= item_group_master_sales_accRepository.getItem_group_master_sales_acc(subgroup_items_code);
         	 item_name_ledger[i]=ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername();
             
         	item_name[i]=details.get(i).getItem_name();
        	item_amount[i]=String.valueOf(details.get(i).getAmount());
        	item_rate[i]=String.valueOf(details.get(i).getRate());
        	price_based_on[i]=details.get(i).getUom_basedon();
        	item_passeditemqty[i]=String.valueOf(details.get(i).getProduction_qty());
        	packing_qty[i]=String.valueOf((int)(details.get(i).getPacking_qty()));
        	
        	packing_uom[i]=details.get(i).getPacking_uom(); 
        	item_uom[i]=details.get(i).getProduction_uom();
        	
        }
       
		String output;
		Tallyrequest_ProductionReg tally=new Tallyrequest_ProductionReg();
		try  
		{
			String date=summaryData.getDate().replace("-", ""); 
			output=tally.SendToTally(date,item_name_ledger,item_name,item_rate,item_uom,
					item_passeditemqty,packing_uom,packing_qty,item_total,totalqty,totalbags);
			
			System.out.println(" output :: "+output);
			String [] splitoutput = output.split("\\|\\|");
			System.out.println("export status ::"+splitoutput[1]);
			System.out.println(splitoutput[0] + " / " + splitoutput[1]+"/" +id);
			//System.out.println("TALLY :: "+splitoutput[0] + " / " + splitoutput[1]+"/" +id);
			
			int exportstatus=0;
			if(Integer.parseInt(splitoutput[1])==1) 
			{
				exportstatus=1;
				res.setStatus("Done");
			}
			else
			{
				res.setStatus("Not Done");
			}
			/*if(Integer.parseInt(splitoutput[2])==1) 
			{
				exportstatus=1;
				res.setStatus("Done");
			}
			else
			{
				res.setStatus("Not Done");
			}*/
			
			prodsummaryRepository.exportuomtally(id,splitoutput[0],exportstatus);
			System.out.println();
			
		}
		catch(Exception e)
		{
		
			System.out.println(e);
		}
		return res;
	}
	
	@Transactional
	public StatusDTO prodSummaryPostingUndo(long id,String username)
	{
		StatusDTO res=new StatusDTO();
		try  
		{
			LocalDateTime ldt = LocalDateTime.now();
			prodsummaryRepository.prodSummaryPostingUndo(id,username,ldt);
			res.setStatus("Done");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return res;
	}
	
}
