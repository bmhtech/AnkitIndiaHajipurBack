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

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;
import com.AnkitIndia.jwtauthentication.model.Ratechart;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_rate_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.RatechartRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class RatechartService_Imp implements RatechartService
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	RatechartRepository ratechartRepository;
	
	@Autowired
	Item_rate_dtlsRepository item_rate_dtlsRepository;
	
	@Autowired
	Sales_Order_Item_DtlsRepository sales_Order_Item_DtlsRepository;
	
	public SequenceIdDTO getRSequenceId(String fin_year) 
	{
		int slno=0;
		String sno=ratechartRepository.countrate();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="SRC-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Ratechart save(Ratechart ratechart) {
		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=ratechartRepository.countrate();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueId6("SRC",slno);
			ratechart.setRate_id(gen_sno);
			
			ratechart.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ratechart.getB_unit()).getBusinessunit_name());
			ratechart.setModified_type("INSERTED");
			ratechart.setInserted_by(userRepository.getUserDetails(ratechart.getUsername()).getName());
			ratechart.setInserted_on(ldt);
			ratechart.setUpdated_by("NA");
			ratechart.setUpdated_on(ldt);
			ratechart.setDeleted_by("NA");
			ratechart.setDeleted_on(ldt);
		
			Set<Item_rate_dtls> item_rate_dtls=new HashSet<Item_rate_dtls>();
			item_rate_dtls.addAll(ratechart.getItem_rate_dtls());
			for(Item_rate_dtls iDetails:item_rate_dtls) 
			{
				
				iDetails.setRate_id(gen_sno);
				
				iDetails.setRate_code(ratechart.getRate_code());
				
				iDetails.setDate(ratechart.getDate());
				
				iDetails.setValid_date(ratechart.getValid_date());
				
				iDetails.setItem_name(item_masterRepository.itemNameById(iDetails.getItem_code()).getItem_name());
				iDetails.setPacking_name(item_masterRepository.itemNameById(iDetails.getPacking()).getItem_name());
				
				iDetails.setCompany_id(ratechart.getCompany_id());
				iDetails.setFin_year(ratechart.getFin_year());
				iDetails.setModified_type("INSERTED");
				iDetails.setInserted_by(ratechart.getInserted_by());
				iDetails.setInserted_on(ldt);
				iDetails.setUpdated_by("NA");
				iDetails.setUpdated_on(ldt);
				iDetails.setDeleted_by("NA");
				iDetails.setDeleted_on(ldt);
				iDetails.setUsername(ratechart.getUsername());
			}
			ratechart.setItem_rate_dtls(item_rate_dtls);
			
		return ratechartRepository.save(ratechart);
		
	}
	
	public List<Map<String, Object>> getRateChartList(String fin_year){
		List<Map<String, Object>> RateChartList = new ArrayList<Map<String, Object>>();
		RateChartList.addAll(ratechartRepository.getRateChartList(fin_year));
		return RateChartList;
	}
	
	public List<Map<String, Object>> RateChartList()
	{
		LocalDateTime date = LocalDateTime.now();
		String currentdatetime = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		
		List<Map<String, Object>> RateChartList = new ArrayList<Map<String, Object>>();
		RateChartList.addAll(ratechartRepository.RateChartList(currentdatetime));
		return RateChartList;
	}
	
	public Ratechart findOne(long id)
	{
		Optional<Ratechart> op=this.ratechartRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String, Object>> rateRetriveList(String r_code)
	{
		List<Map<String, Object>> rateList=new ArrayList<Map<String, Object>>();
		
		rateList.addAll(item_rate_dtlsRepository.getRatedtlsList(r_code));
		
		return rateList;
	}
	
	@Transactional
	public Ratechart update(Ratechart ratechart,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Ratechart> op = ratechartRepository.findById(id);
		
		ratechart.setRate_id(op.get().getRate_id());		
		ratechart.setRate_code(op.get().getRate_code());
		ratechart.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ratechart.getB_unit()).getBusinessunit_name());
		
		ratechart.setModified_type("INSERTED");
		ratechart.setInserted_by(op.get().getInserted_by());
		ratechart.setInserted_on(op.get().getInserted_on());
		ratechart.setUpdated_by(userRepository.getUserDetails(ratechart.getUsername()).getName());
		ratechart.setUpdated_on(ldt);
		ratechart.setDeleted_by(op.get().getDeleted_by());
		ratechart.setDeleted_on(op.get().getDeleted_on());
		
		//Dynamic
		item_rate_dtlsRepository.updateItem_ratedtls(ratechart.getRate_code(),"UPDATED");
		Set<Item_rate_dtls> rateset = new HashSet<Item_rate_dtls>();
		rateset.addAll(ratechart.getItem_rate_dtls());
		for(Item_rate_dtls ratedtls : rateset)
		{
			ratedtls.setRate_id(ratechart.getRate_id());
			ratedtls.setRate_code(ratechart.getRate_code());
			
			ratedtls.setValid_date(ratechart.getValid_date());
			
			ratedtls.setDate(ratechart.getDate());
			
			ratedtls.setItem_name(item_masterRepository.itemNameById(ratedtls.getItem_code()).getItem_name());
			ratedtls.setPacking_name(item_masterRepository.itemNameById(ratedtls.getPacking()).getItem_name());
			
			ratedtls.setCompany_id(ratechart.getCompany_id());
			ratedtls.setFin_year(ratechart.getFin_year());
			ratedtls.setUsername(ratechart.getUsername());
			
			ratedtls.setModified_type("INSERTED");
			ratedtls.setInserted_by(ratechart.getInserted_by());
			ratedtls.setInserted_on(ratechart.getInserted_on());
			ratedtls.setUpdated_by(ratechart.getUpdated_by());
			ratedtls.setUpdated_on(ratechart.getUpdated_on());
			ratedtls.setDeleted_by(ratechart.getDeleted_by());
			ratedtls.setDeleted_on(ratechart.getDeleted_on());
		}
	
		ratechart.setItem_rate_dtls(rateset);
		
		if(op.isPresent())
		{
			ratechart.setId(id);
		}
		return ratechartRepository.save(ratechart);
	}
	
	@Transactional
	public Ratechart delete(Ratechart ratechart, long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Ratechart> op = ratechartRepository.findById(id);
		
		Ratechart rc=op.get();
		
		rc.setModified_type("DELETED");
		rc.setInserted_by(op.get().getInserted_by());
		rc.setInserted_on(op.get().getInserted_on());
		rc.setUpdated_by(op.get().getUpdated_by());
		rc.setUpdated_on(op.get().getUpdated_on());
		rc.setDeleted_by(userRepository.getUserDetails(ratechart.getUsername()).getName());
		rc.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
		  rc.setId(id);
		}
		
		item_rate_dtlsRepository.item_rate_dtlsUpdate(op.get().getRate_code());
		
		return ratechartRepository.save(rc);
	}
	
	
	public StatusDTO getRateChartDateVerify(String date) 
	{
		StatusDTO res=new StatusDTO();
		if(ratechartRepository.checkDate(date)>0) 
		{
			res.setStatus("YES");;
		}
		else 
		{
			res.setStatus("NO");
		}
		
		return res;
	}
	
	public List<Item_rate_dtls> getRateChartItemthdt(String date)
	{
		List<Item_rate_dtls> itemdtlsList=new ArrayList<Item_rate_dtls>();
		
		String res= ratechartRepository.checkRateChartExist(date);
		System.out.println("res "+ res);
		if(res.compareToIgnoreCase("NA") ==0 ) 
		{
			Item_rate_dtls it = new Item_rate_dtls();
			
			it.setItem_code("No Data Found");
			it.setItem_name("");
			it.setPacking("");
			it.setItem_uom("");
			it.setPacking_uom("");
			it.setPrice_based_on("");
			it.setRate("");
			it.setTolerance("5");
			itemdtlsList.add(it);
			System.out.println("res "+itemdtlsList.size() +" // " + itemdtlsList.get(0).getItem_code());
			
		}		
		else 
		{
			//item_rate_dtlsRepository.getItemList(res);
			System.out.println("else");
			itemdtlsList.addAll(ratechartRepository.getItemList(res));
		}
				
		
		
		return itemdtlsList;
	}
	
	public List<Map<String,Object>> getRateChartItemList(String curr_date, String b_unit, String inv_type)
	{
		
		List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>();
		
		LocalDateTime date = LocalDateTime.now();
		curr_date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		
		System.out.println("CHECK : :  "+curr_date);
		
		if(inv_type.compareToIgnoreCase("INV00001") ==0)
		{
			list.addAll(ratechartRepository.getRateChartItemListReg(curr_date,b_unit));
		}
		else if(inv_type.compareToIgnoreCase("INV00002") ==0)
		{
			list.addAll(ratechartRepository.getRateChartItemListGst(curr_date,b_unit));
		}
		else if(inv_type.compareToIgnoreCase("INV00004") ==0)
		{
			list.addAll(ratechartRepository.getRateChartItemListPms(curr_date,b_unit));
		}
		
		return list;
	}
	
	public Map<String,Object> getRateItemQty(String order_date, String itemid, String packingid, long id, String pricebasedon)
	{
		List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>();
		
		LocalDateTime date = LocalDateTime.now();
		order_date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		
		if(id>0)
		{
			if(sales_Order_Item_DtlsRepository.itemRateExist(id,itemid,packingid)>0)
			{
				list.add(ratechartRepository.getRateItemQtySaleOrder(order_date,itemid,packingid,id,pricebasedon));
			}
			else
			{
				list.add(ratechartRepository.getRateItemQtySave(order_date,itemid,packingid,pricebasedon));
			}
		}
		else
		{
			list.add(ratechartRepository.getRateItemQtySave(order_date,itemid,packingid,pricebasedon));
		}
		return list.get(0);
	}
}
