package com.AnkitIndia.jwtauthentication.security.services;

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

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_group_jobwork_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_jobwork_sales_return_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_pur_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_pur_retacc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_retacc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_stk_trans_pur;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_stk_trans_sale;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_jobwork_sales_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_jobwork_sales_return_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_pur_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_pur_retaccRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_sales_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_sales_retaccRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_stk_trans_purRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_stk_trans_saleRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_jobwork_sales_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_jobwork_sales_return_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_pur_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_pur_retaccDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_sales_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_sales_retaccDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_stk_trans_purDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_stk_trans_saleDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Item_group_masterService_Imp implements Item_group_masterService{

	@Autowired
	Item_group_masterRepository item_group_masterRepository;
	
	@Autowired
	Item_group_master_pur_accRepository item_group_master_pur_accRepository;
	
	@Autowired
	Item_group_master_sales_accRepository item_group_master_sales_accRepository;
	
	@Autowired
	Item_group_master_sales_retaccRepository item_group_master_sales_retaccRepository;
	
	@Autowired
	Item_group_master_pur_retaccRepository item_group_master_pur_retaccRepository;
	
	@Autowired
	Item_group_master_stk_trans_saleRepository item_group_master_stk_trans_saleRepository;
	
	@Autowired
	Item_group_jobwork_sales_accRepository item_group_jobwork_sales_accRepository;
	
	@Autowired
	Item_group_jobwork_sales_return_accRepository item_group_jobwork_sales_return_accRepository;
	
	@Autowired
	Item_group_master_stk_trans_purRepository item_group_master_stk_trans_purRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;

	public SequenceIdDTO getIgrpSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=item_group_masterRepository.getIgrpSequenceId(fprefix,company);
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
	public Item_group_master saveItemGroup(Item_group_master group_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0; String prefix="IG";
		if(item_group_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(item_group_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(group_master.getParent_group().compareTo("0")==0) {
			group_master.setParent_group(gen_sno);
			group_master.setMain_group(gen_sno);
		}
		else
		{
			String xxx=item_group_masterRepository.getParentGroupId(group_master.getParent_group());
			group_master.setMain_group(xxx);
		}
		
		/*Start checking transaction no for unique */
		long nslno=0;
		String gen_snonew=item_group_masterRepository.getIgrpSequenceId(group_master.getItem_group_code().substring(0,7),group_master.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(group_master.getItem_group_code().substring(0,7),nslno);
		group_master.setItem_group_code(gen_tslno);
		/*End checking transaction no for unique */
		
		group_master.setItem_group_id(gen_sno);
		group_master.setModified_type("INSERTED");
		group_master.setInserted_by(userRepository.getUserDetails(group_master.getUsername()).getName());
		group_master.setInserted_on(ldt);
		group_master.setUpdated_by("NA");
		group_master.setUpdated_on(ldt);
		group_master.setDeleted_by("NA");
		group_master.setDeleted_on(ldt);
		System.out.println(" group  type " + group_master.getGroup_type());
		if(group_master.getGroup_type().compareToIgnoreCase("Normal")==0)
		{
			//Static
			Set<Item_group_master_sales_acc> saleAccSet = new HashSet<Item_group_master_sales_acc>();
			saleAccSet.add(group_master.getItem_group_master_sales_acc());
			for(Item_group_master_sales_acc iSales_acc : saleAccSet)
			{
				iSales_acc.setItem_group_id(gen_sno);
				iSales_acc.setItem_group_code(group_master.getItem_group_code());
				iSales_acc.setCompany_id(group_master.getCompany_id());
				iSales_acc.setFin_year(group_master.getFin_year());
				iSales_acc.setModified_type("INSERTED");
				iSales_acc.setInserted_by(group_master.getInserted_by());
				iSales_acc.setInserted_on(ldt);
				iSales_acc.setUpdated_by("NA");
				iSales_acc.setUpdated_on(ldt);
				iSales_acc.setDeleted_by("NA");
				iSales_acc.setDeleted_on(ldt);
			}
			
			if(!saleAccSet.isEmpty())
			{
				group_master.setItem_group_master_sales_acc(saleAccSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_pur_acc> purAccSet = new HashSet<Item_group_master_pur_acc>();
			purAccSet.add(group_master.getItem_group_master_pur_acc ());
			for(Item_group_master_pur_acc iPur_acc : purAccSet)
			{
				iPur_acc.setItem_group_id(gen_sno);
				iPur_acc.setItem_group_code(group_master.getItem_group_code());
				iPur_acc.setCompany_id(group_master.getCompany_id());
				iPur_acc.setFin_year(group_master.getFin_year());
				iPur_acc.setModified_type("INSERTED");
				iPur_acc.setInserted_by(group_master.getInserted_by());
				iPur_acc.setInserted_on(ldt);
				iPur_acc.setUpdated_by("NA");
				iPur_acc.setUpdated_on(ldt);
				iPur_acc.setDeleted_by("NA");
				iPur_acc.setDeleted_on(ldt);
			}
			
			if(!purAccSet.isEmpty())
			{
				group_master.setItem_group_master_pur_acc(purAccSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_sales_retacc> saleAccRtnSet = new HashSet<Item_group_master_sales_retacc>();
			saleAccRtnSet.add(group_master.getItem_group_master_sales_retacc());
			for(Item_group_master_sales_retacc iSales_retacc : saleAccRtnSet)
			{
				iSales_retacc.setItem_group_id(gen_sno);
				iSales_retacc.setItem_group_code(group_master.getItem_group_code());
				iSales_retacc.setCompany_id(group_master.getCompany_id());
				iSales_retacc.setFin_year(group_master.getFin_year());
				iSales_retacc.setModified_type("INSERTED");
				iSales_retacc.setInserted_by(group_master.getInserted_by());
				iSales_retacc.setInserted_on(ldt);
				iSales_retacc.setUpdated_by("NA");
				iSales_retacc.setUpdated_on(ldt);
				iSales_retacc.setDeleted_by("NA");
				iSales_retacc.setDeleted_on(ldt);
			}
			
			if(!saleAccRtnSet.isEmpty())
			{
				group_master.setItem_group_master_sales_retacc(saleAccRtnSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_pur_retacc> purAccRtnSet = new HashSet<Item_group_master_pur_retacc>();
			purAccRtnSet.add(group_master.getItem_group_master_pur_retacc());
			for(Item_group_master_pur_retacc iPur_retacc : purAccRtnSet)
			{
				iPur_retacc.setItem_group_id(gen_sno);
				iPur_retacc.setItem_group_code(group_master.getItem_group_code());
				iPur_retacc.setCompany_id(group_master.getCompany_id());
				iPur_retacc.setFin_year(group_master.getFin_year());
				iPur_retacc.setModified_type("INSERTED");
				iPur_retacc.setInserted_by(group_master.getInserted_by());
				iPur_retacc.setInserted_on(ldt);
				iPur_retacc.setUpdated_by("NA");
				iPur_retacc.setUpdated_on(ldt);
				iPur_retacc.setDeleted_by("NA");
				iPur_retacc.setDeleted_on(ldt);
			}
			
			if(!purAccRtnSet.isEmpty())
			{
				group_master.setItem_group_master_pur_retacc(purAccRtnSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_stk_trans_sale> stkTrnsSaleSet = new HashSet<Item_group_master_stk_trans_sale>();
			stkTrnsSaleSet.add(group_master.getItem_group_master_stk_trans_sale());
			for(Item_group_master_stk_trans_sale iTrans_sale : stkTrnsSaleSet)
			{
				iTrans_sale.setItem_group_id(gen_sno);
				iTrans_sale.setItem_group_code(group_master.getItem_group_code());
				iTrans_sale.setCompany_id(group_master.getCompany_id());
				iTrans_sale.setFin_year(group_master.getFin_year());
				iTrans_sale.setModified_type("INSERTED");
				iTrans_sale.setInserted_by(group_master.getInserted_by());
				iTrans_sale.setInserted_on(ldt);
				iTrans_sale.setUpdated_by("NA");
				iTrans_sale.setUpdated_on(ldt);
				iTrans_sale.setDeleted_by("NA");
				iTrans_sale.setDeleted_on(ldt);
			}
			
			if(!stkTrnsSaleSet.isEmpty())
			{
				group_master.setItem_group_master_stk_trans_sale(stkTrnsSaleSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_stk_trans_pur> stkTrnsPurSet = new HashSet<Item_group_master_stk_trans_pur>();
			stkTrnsPurSet.add(group_master.getItem_group_master_stk_trans_pur());
			for(Item_group_master_stk_trans_pur iTrans_pur : stkTrnsPurSet)
			{
				iTrans_pur.setItem_group_id(gen_sno);
				iTrans_pur.setItem_group_code(group_master.getItem_group_code());
				iTrans_pur.setCompany_id(group_master.getCompany_id());
				iTrans_pur.setFin_year(group_master.getFin_year());
				iTrans_pur.setModified_type("INSERTED");
				iTrans_pur.setInserted_by(group_master.getInserted_by());
				iTrans_pur.setInserted_on(ldt);
				iTrans_pur.setUpdated_by("NA");
				iTrans_pur.setUpdated_on(ldt);
				iTrans_pur.setDeleted_by("NA");
				iTrans_pur.setDeleted_on(ldt);
			}
			
			if(!stkTrnsPurSet.isEmpty())
			{
				group_master.setItem_group_master_stk_trans_pur(stkTrnsPurSet.iterator().next());
			}
			
			//Static
			Set<Item_group_jobwork_sales_acc> jobwork1 = new HashSet<Item_group_jobwork_sales_acc>();
			jobwork1.add(group_master.getItem_group_jobwork_sales_acc());
			for(Item_group_jobwork_sales_acc jobwork : jobwork1)
			{
				jobwork.setItem_group_id(gen_sno);
				jobwork.setItem_group_code(group_master.getItem_group_code());
				jobwork.setCompany_id(group_master.getCompany_id());
				jobwork.setFin_year(group_master.getFin_year());
				jobwork.setModified_type("INSERTED");
				jobwork.setInserted_by(group_master.getInserted_by());
				jobwork.setInserted_on(ldt);
				jobwork.setUpdated_by("NA");
				jobwork.setUpdated_on(ldt);
				jobwork.setDeleted_by("NA");
				jobwork.setDeleted_on(ldt);
			}
			
			if(!jobwork1.isEmpty())
			{
				group_master.setItem_group_jobwork_sales_acc(jobwork1.iterator().next());
			}
			
			//Static
			Set<Item_group_jobwork_sales_return_acc> jobreturn1 = new HashSet<Item_group_jobwork_sales_return_acc>();
			jobreturn1.add(group_master.getItem_group_jobwork_sales_return_acc());
			for(Item_group_jobwork_sales_return_acc jobreturn : jobreturn1)
			{
				jobreturn.setItem_group_id(gen_sno);
				jobreturn.setItem_group_code(group_master.getItem_group_code());
				jobreturn.setCompany_id(group_master.getCompany_id());
				jobreturn.setFin_year(group_master.getFin_year());
				jobreturn.setModified_type("INSERTED");
				jobreturn.setInserted_by(group_master.getInserted_by());
				jobreturn.setInserted_on(ldt);
				jobreturn.setUpdated_by("NA");
				jobreturn.setUpdated_on(ldt);
				jobreturn.setDeleted_by("NA");
				jobreturn.setDeleted_on(ldt);
			}
			
			if(!jobreturn1.isEmpty())
			{
				group_master.setItem_group_jobwork_sales_return_acc(jobreturn1.iterator().next());
			}
		}
		else if(group_master.getGroup_type().compareToIgnoreCase("Job Work")==0)
		{
			Set<Item_group_master_sales_acc> saleAccSet = new HashSet<Item_group_master_sales_acc>();
			saleAccSet.add(group_master.getItem_group_master_sales_acc());
			for(Item_group_master_sales_acc iSales_acc : saleAccSet)
			{
				iSales_acc.setItem_group_id(gen_sno);
				iSales_acc.setItem_group_code(group_master.getItem_group_code());
				iSales_acc.setCompany_id(group_master.getCompany_id());
				iSales_acc.setFin_year(group_master.getFin_year());
				iSales_acc.setModified_type("INSERTED");
				iSales_acc.setInserted_by(group_master.getInserted_by());
				iSales_acc.setInserted_on(ldt);
				iSales_acc.setUpdated_by("NA");
				iSales_acc.setUpdated_on(ldt);
				iSales_acc.setDeleted_by("NA");
				iSales_acc.setDeleted_on(ldt);
			}
			
			if(!saleAccSet.isEmpty())
			{
				group_master.setItem_group_master_sales_acc(saleAccSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_pur_acc> purAccSet = new HashSet<Item_group_master_pur_acc>();
			purAccSet.add(group_master.getItem_group_master_pur_acc ());
			for(Item_group_master_pur_acc iPur_acc : purAccSet)
			{
				iPur_acc.setItem_group_id(gen_sno);
				iPur_acc.setItem_group_code(group_master.getItem_group_code());
				iPur_acc.setCompany_id(group_master.getCompany_id());
				iPur_acc.setFin_year(group_master.getFin_year());
				iPur_acc.setModified_type("INSERTED");
				iPur_acc.setInserted_by(group_master.getInserted_by());
				iPur_acc.setInserted_on(ldt);
				iPur_acc.setUpdated_by("NA");
				iPur_acc.setUpdated_on(ldt);
				iPur_acc.setDeleted_by("NA");
				iPur_acc.setDeleted_on(ldt);
			}
			
			if(!purAccSet.isEmpty())
			{
				group_master.setItem_group_master_pur_acc(purAccSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_sales_retacc> saleAccRtnSet = new HashSet<Item_group_master_sales_retacc>();
			saleAccRtnSet.add(group_master.getItem_group_master_sales_retacc());
			for(Item_group_master_sales_retacc iSales_retacc : saleAccRtnSet)
			{
				iSales_retacc.setItem_group_id(gen_sno);
				iSales_retacc.setItem_group_code(group_master.getItem_group_code());
				iSales_retacc.setCompany_id(group_master.getCompany_id());
				iSales_retacc.setFin_year(group_master.getFin_year());
				iSales_retacc.setModified_type("INSERTED");
				iSales_retacc.setInserted_by(group_master.getInserted_by());
				iSales_retacc.setInserted_on(ldt);
				iSales_retacc.setUpdated_by("NA");
				iSales_retacc.setUpdated_on(ldt);
				iSales_retacc.setDeleted_by("NA");
				iSales_retacc.setDeleted_on(ldt);
			}
			
			if(!saleAccRtnSet.isEmpty())
			{
				group_master.setItem_group_master_sales_retacc(saleAccRtnSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_pur_retacc> purAccRtnSet = new HashSet<Item_group_master_pur_retacc>();
			purAccRtnSet.add(group_master.getItem_group_master_pur_retacc());
			for(Item_group_master_pur_retacc iPur_retacc : purAccRtnSet)
			{
				iPur_retacc.setItem_group_id(gen_sno);
				iPur_retacc.setItem_group_code(group_master.getItem_group_code());
				iPur_retacc.setCompany_id(group_master.getCompany_id());
				iPur_retacc.setFin_year(group_master.getFin_year());
				iPur_retacc.setModified_type("INSERTED");
				iPur_retacc.setInserted_by(group_master.getInserted_by());
				iPur_retacc.setInserted_on(ldt);
				iPur_retacc.setUpdated_by("NA");
				iPur_retacc.setUpdated_on(ldt);
				iPur_retacc.setDeleted_by("NA");
				iPur_retacc.setDeleted_on(ldt);
			}
			
			if(!purAccRtnSet.isEmpty())
			{
				group_master.setItem_group_master_pur_retacc(purAccRtnSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_stk_trans_sale> stkTrnsSaleSet = new HashSet<Item_group_master_stk_trans_sale>();
			stkTrnsSaleSet.add(group_master.getItem_group_master_stk_trans_sale());
			for(Item_group_master_stk_trans_sale iTrans_sale : stkTrnsSaleSet)
			{
				iTrans_sale.setItem_group_id(gen_sno);
				iTrans_sale.setItem_group_code(group_master.getItem_group_code());
				iTrans_sale.setCompany_id(group_master.getCompany_id());
				iTrans_sale.setFin_year(group_master.getFin_year());
				iTrans_sale.setModified_type("INSERTED");
				iTrans_sale.setInserted_by(group_master.getInserted_by());
				iTrans_sale.setInserted_on(ldt);
				iTrans_sale.setUpdated_by("NA");
				iTrans_sale.setUpdated_on(ldt);
				iTrans_sale.setDeleted_by("NA");
				iTrans_sale.setDeleted_on(ldt);
			}
			
			if(!stkTrnsSaleSet.isEmpty())
			{
				group_master.setItem_group_master_stk_trans_sale(stkTrnsSaleSet.iterator().next());
			}
			
			//Static
			Set<Item_group_master_stk_trans_pur> stkTrnsPurSet = new HashSet<Item_group_master_stk_trans_pur>();
			stkTrnsPurSet.add(group_master.getItem_group_master_stk_trans_pur());
			for(Item_group_master_stk_trans_pur iTrans_pur : stkTrnsPurSet)
			{
				iTrans_pur.setItem_group_id(gen_sno);
				iTrans_pur.setItem_group_code(group_master.getItem_group_code());
				iTrans_pur.setCompany_id(group_master.getCompany_id());
				iTrans_pur.setFin_year(group_master.getFin_year());
				iTrans_pur.setModified_type("INSERTED");
				iTrans_pur.setInserted_by(group_master.getInserted_by());
				iTrans_pur.setInserted_on(ldt);
				iTrans_pur.setUpdated_by("NA");
				iTrans_pur.setUpdated_on(ldt);
				iTrans_pur.setDeleted_by("NA");
				iTrans_pur.setDeleted_on(ldt);
			}
			
			if(!stkTrnsPurSet.isEmpty())
			{
				group_master.setItem_group_master_stk_trans_pur(stkTrnsPurSet.iterator().next());
			}
			
			//Static
			Set<Item_group_jobwork_sales_acc> jobwork1 = new HashSet<Item_group_jobwork_sales_acc>();
			jobwork1.add(group_master.getItem_group_jobwork_sales_acc());
			for(Item_group_jobwork_sales_acc jobwork : jobwork1)
			{
				System.out.println("job sales acc: "+ jobwork.getJw_item_total().toString());
				jobwork.setItem_group_id(gen_sno);
				jobwork.setItem_group_code(group_master.getItem_group_code());
				jobwork.setCompany_id(group_master.getCompany_id());
				jobwork.setFin_year(group_master.getFin_year());
				jobwork.setModified_type("INSERTED");
				jobwork.setInserted_by(group_master.getInserted_by());
				jobwork.setInserted_on(ldt);
				jobwork.setUpdated_by("NA");
				jobwork.setUpdated_on(ldt);
				jobwork.setDeleted_by("NA");
				jobwork.setDeleted_on(ldt);
			}
			
			if(!jobwork1.isEmpty())
			{
				group_master.setItem_group_jobwork_sales_acc(jobwork1.iterator().next());
			}
			
			//Static
			Set<Item_group_jobwork_sales_return_acc> jobreturn1 = new HashSet<Item_group_jobwork_sales_return_acc>();
			jobreturn1.add(group_master.getItem_group_jobwork_sales_return_acc());
			for(Item_group_jobwork_sales_return_acc jobreturn : jobreturn1)
			{
				jobreturn.setItem_group_id(gen_sno);
				jobreturn.setItem_group_code(group_master.getItem_group_code());
				jobreturn.setCompany_id(group_master.getCompany_id());
				jobreturn.setFin_year(group_master.getFin_year());
				jobreturn.setModified_type("INSERTED");
				jobreturn.setInserted_by(group_master.getInserted_by());
				jobreturn.setInserted_on(ldt);
				jobreturn.setUpdated_by("NA");
				jobreturn.setUpdated_on(ldt);
				jobreturn.setDeleted_by("NA");
				jobreturn.setDeleted_on(ldt);
			}
			
			if(!jobreturn1.isEmpty())
			{
				group_master.setItem_group_jobwork_sales_return_acc(jobreturn1.iterator().next());
			}
			
			
		}
		else {}
		return item_group_masterRepository.save(group_master);
	}
	
	public List<Item_group_master> findAll(String company)
	{
		List<Item_group_master> igList=new ArrayList<Item_group_master>();
		igList.addAll(item_group_masterRepository.findAll());
		
		List<Item_group_master> allData = igList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company))
				  .sorted(Comparator.comparing(Item_group_master::getItem_group_id).reversed())
				  .collect(Collectors.toList());
		
		allData.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(item_group_masterRepository.getItemGroups(ig.getParent_group()).getGroup_name());
			}else {ig.setParent_group("None");}
		});
		return allData;
	}
	
	public List<Item_group_masterDTO> getItemGroup(String company) {
		
		List<Item_group_master> modelList=item_group_masterRepository.getItemGroupList(true,company);
		
		modelList.forEach((e)->{
			e.setGroup_name(e.getGroup_name().toUpperCase());
		});
 		
		List<Item_group_master> cPartners = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_group_master::getGroup_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_group_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_group_masterDTO> itemGroupList = new ModelMapper().map(cPartners,listType);
		
		return itemGroupList;
	}
	
	public List<Map<String,Object>> itemGroupFastList(String company) {
		
		List <Map<String,Object>> itemGroupList = new ArrayList<>();
		
		itemGroupList.addAll(item_group_masterRepository.itemGroupFastList(true,company));
		
		return itemGroupList;
	}
	
	public Item_group_masterDTO getItemGroupUom(String code) {
		
		Item_group_master modelList=item_group_masterRepository.getItemGroupUom(code);
		
		Type listType = new TypeToken<Item_group_masterDTO>() {}.getType();
		
		Item_group_masterDTO itemGroupUom = new ModelMapper().map(modelList,listType);
		
		return itemGroupUom;
	}
	
	public String chkItemGroupName(String code) {
		
		String result=item_group_masterRepository.getItemGroupName(code);
		if(result == null) {
			result="NOTEXIST";
		}
		else {
			result="EXIST";
		}
		return result;
	}
	
	public MessageStatusDTO chkItemGroupStatus(String code) {
		
		String result=item_group_masterRepository.chkItemNameStatus(code);
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}

	@Override
	public Item_group_master findOne(Long id) {
		// TODO Auto-generated method stub
		Optional<Item_group_master> op=this.item_group_masterRepository.findById(id);
		
		return op.get();
	}
	
	public Item_group_master_sales_accDTO getItemGroupSalesAcc(String igroupid) {
		Item_group_master_sales_acc modelList=item_group_master_sales_accRepository.getItem_group_master_sales_acc(igroupid);

		Type listType = new TypeToken<Item_group_master_sales_accDTO>() {}.getType();

		Item_group_master_sales_accDTO salesAcc= new ModelMapper().map(modelList,listType);
		
		return salesAcc;
	}
	
	public Item_group_master_pur_accDTO getItemGroupPurAcc(String igroupid) {
		Item_group_master_pur_acc modelList=item_group_master_pur_accRepository.getItem_group_master_pur_acc(igroupid);

		Type listType = new TypeToken<Item_group_master_pur_accDTO>() {}.getType();
	
		Item_group_master_pur_accDTO iPur_accDTO= new ModelMapper().map(modelList,listType);
		
		return iPur_accDTO;
	}
	
	public Item_group_master_sales_retaccDTO getItemGroupSalesRtnAcc(String igroupid) {
		Item_group_master_sales_retacc modelList=item_group_master_sales_retaccRepository.getItem_group_master_sales_retacc(igroupid);

		Type listType = new TypeToken<Item_group_master_sales_retaccDTO>() {}.getType();

		Item_group_master_sales_retaccDTO iSales_retaccDTO= new ModelMapper().map(modelList,listType);
		
		return iSales_retaccDTO;
	}	
	
	public Item_group_master_pur_retaccDTO getItemGroupPurRtnAcc(String igroupid) {
		Item_group_master_pur_retacc modelList=item_group_master_pur_retaccRepository.getItem_group_master_pur_retacc(igroupid);

		Type listType = new TypeToken<Item_group_master_pur_retaccDTO>() {}.getType();
	
		Item_group_master_pur_retaccDTO iPur_retaccDTO= new ModelMapper().map(modelList,listType);
		
		return iPur_retaccDTO;
	}
	
	public Item_group_master_stk_trans_purDTO getItemGroupStkTrnsPur(String igroupid) {
		Item_group_master_stk_trans_pur modelList=item_group_master_stk_trans_purRepository.getItem_group_master_stk_trans_pur(igroupid);

		Type listType = new TypeToken<Item_group_master_stk_trans_purDTO>() {}.getType();
	
		Item_group_master_stk_trans_purDTO iStk_trans_purDTO= new ModelMapper().map(modelList,listType);
		
		return iStk_trans_purDTO;
	}
	
	public Item_group_master_stk_trans_saleDTO getItemGroupStkTrnsSale(String igroupid) {
		Item_group_master_stk_trans_sale modelList=item_group_master_stk_trans_saleRepository.getItem_group_master_stk_trans_sale(igroupid);

		Type listType = new TypeToken<Item_group_master_stk_trans_saleDTO>() {}.getType();

		Item_group_master_stk_trans_saleDTO iStk_trans_saleDTO= new ModelMapper().map(modelList,listType);
		
		return iStk_trans_saleDTO;
	}
	
	public Item_group_jobwork_sales_accDTO getItemGroupJobworkSales(String igroupid) {
		Item_group_jobwork_sales_acc modelList=item_group_jobwork_sales_accRepository.getItemGroupJobworkSales(igroupid);
		Type listType = new TypeToken<Item_group_jobwork_sales_accDTO>() {}.getType();
		Item_group_jobwork_sales_accDTO iStk_job_saleDTO= new ModelMapper().map(modelList,listType);
		return iStk_job_saleDTO;
	}
	
	public Item_group_jobwork_sales_return_accDTO getItemGroupJobworkSalesReturn(String igroupid) {
		Item_group_jobwork_sales_return_acc modelList=item_group_jobwork_sales_return_accRepository.getItemGroupJobworkSalesReturn(igroupid);
		Type listType = new TypeToken<Item_group_jobwork_sales_return_accDTO>() {}.getType();
		Item_group_jobwork_sales_return_accDTO iStk_job_saleRtn= new ModelMapper().map(modelList,listType);
		return iStk_job_saleRtn;
	}
	
	
	@Transactional
	public Item_group_master update(Item_group_master iGroup_master,long id)
	{
		Optional<Item_group_master> op = item_group_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iGroup_master.setItem_group_id(op.get().getItem_group_id());
		iGroup_master.setModified_type("INSERTED");
		iGroup_master.setInserted_by(op.get().getInserted_by());
		iGroup_master.setInserted_on(op.get().getInserted_on());
		iGroup_master.setUpdated_by(userRepository.getUserDetails(iGroup_master.getUsername()).getName());
		iGroup_master.setUpdated_on(ldt);
		iGroup_master.setDeleted_by("NA");
		iGroup_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iGroup_master.setId(id);
		}
			
		System.out.println(iGroup_master.getGroup_type());
		if(iGroup_master.getGroup_type().compareToIgnoreCase("Normal")==0)
		{
			
			item_group_master_sales_accRepository.updateItem_group_master_sales_acc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_sales_acc> saleAccSet = new HashSet<Item_group_master_sales_acc>();
			saleAccSet.add(iGroup_master.getItem_group_master_sales_acc());
			for(Item_group_master_sales_acc iSales_acc : saleAccSet)
			{
				iSales_acc.setItem_group_id(op.get().getItem_group_id());
				iSales_acc.setItem_group_code(iGroup_master.getItem_group_code());
				iSales_acc.setCompany_id(iGroup_master.getCompany_id());
				iSales_acc.setFin_year(iGroup_master.getFin_year());
				iSales_acc.setModified_type("INSERTED");
				iSales_acc.setInserted_by(iGroup_master.getInserted_by());
				iSales_acc.setInserted_on(ldt);
				iSales_acc.setUpdated_by("NA");
				iSales_acc.setUpdated_on(ldt);
				iSales_acc.setDeleted_by("NA");
				iSales_acc.setDeleted_on(ldt);
			}
			
			if(!saleAccSet.isEmpty())
			{
				iGroup_master.setItem_group_master_sales_acc(saleAccSet.iterator().next());
			}
			
			item_group_master_pur_accRepository.updateItem_group_master_pur_acc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_pur_acc> purAccSet = new HashSet<Item_group_master_pur_acc>();
			purAccSet.add(iGroup_master.getItem_group_master_pur_acc ());
			for(Item_group_master_pur_acc iPur_acc : purAccSet)
			{
				iPur_acc.setItem_group_id(op.get().getItem_group_id());
				iPur_acc.setItem_group_code(iGroup_master.getItem_group_code());
				iPur_acc.setCompany_id(iGroup_master.getCompany_id());
				iPur_acc.setFin_year(iGroup_master.getFin_year());
				iPur_acc.setModified_type("INSERTED");
				iPur_acc.setInserted_by(iGroup_master.getInserted_by());
				iPur_acc.setInserted_on(ldt);
				iPur_acc.setUpdated_by("NA");
				iPur_acc.setUpdated_on(ldt);
				iPur_acc.setDeleted_by("NA");
				iPur_acc.setDeleted_on(ldt);
			}
			
			if(!purAccSet.isEmpty())
			{
				iGroup_master.setItem_group_master_pur_acc(purAccSet.iterator().next());
			}
			
			item_group_master_sales_retaccRepository.updateItem_group_master_sales_retacc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_sales_retacc> saleAccRtnSet = new HashSet<Item_group_master_sales_retacc>();
			saleAccRtnSet.add(iGroup_master.getItem_group_master_sales_retacc());
			for(Item_group_master_sales_retacc iSales_retacc : saleAccRtnSet)
			{
				iSales_retacc.setItem_group_id(op.get().getItem_group_id());
				iSales_retacc.setItem_group_code(iGroup_master.getItem_group_code());
				iSales_retacc.setCompany_id(iGroup_master.getCompany_id());
				iSales_retacc.setFin_year(iGroup_master.getFin_year());
				iSales_retacc.setModified_type("INSERTED");
				iSales_retacc.setInserted_by(iGroup_master.getInserted_by());
				iSales_retacc.setInserted_on(ldt);
				iSales_retacc.setUpdated_by("NA");
				iSales_retacc.setUpdated_on(ldt);
				iSales_retacc.setDeleted_by("NA");
				iSales_retacc.setDeleted_on(ldt);
			}
			
			if(!saleAccRtnSet.isEmpty())
			{
				iGroup_master.setItem_group_master_sales_retacc(saleAccRtnSet.iterator().next());
			}
			
			item_group_master_pur_retaccRepository.updateItem_group_master_pur_retacc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_pur_retacc> purAccRtnSet = new HashSet<Item_group_master_pur_retacc>();
			purAccRtnSet.add(iGroup_master.getItem_group_master_pur_retacc());
			for(Item_group_master_pur_retacc iPur_retacc : purAccRtnSet)
			{
				iPur_retacc.setItem_group_id(op.get().getItem_group_id());
				iPur_retacc.setItem_group_code(iGroup_master.getItem_group_code());
				iPur_retacc.setCompany_id(iGroup_master.getCompany_id());
				iPur_retacc.setFin_year(iGroup_master.getFin_year());
				iPur_retacc.setModified_type("INSERTED");
				iPur_retacc.setInserted_by(iGroup_master.getInserted_by());
				iPur_retacc.setInserted_on(ldt);
				iPur_retacc.setUpdated_by("NA");
				iPur_retacc.setUpdated_on(ldt);
				iPur_retacc.setDeleted_by("NA");
				iPur_retacc.setDeleted_on(ldt);
			}
			
			if(!purAccRtnSet.isEmpty())
			{
				iGroup_master.setItem_group_master_pur_retacc(purAccRtnSet.iterator().next());
			}
			
			item_group_master_stk_trans_saleRepository.updateItem_group_master_stk_trans_sale(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_stk_trans_sale> stkTrnsSaleSet = new HashSet<Item_group_master_stk_trans_sale>();
			stkTrnsSaleSet.add(iGroup_master.getItem_group_master_stk_trans_sale());
			for(Item_group_master_stk_trans_sale iTrans_sale : stkTrnsSaleSet)
			{
				iTrans_sale.setItem_group_id(op.get().getItem_group_id());
				iTrans_sale.setItem_group_code(iGroup_master.getItem_group_code());
				iTrans_sale.setCompany_id(iGroup_master.getCompany_id());
				iTrans_sale.setFin_year(iGroup_master.getFin_year());
				iTrans_sale.setModified_type("INSERTED");
				iTrans_sale.setInserted_by(iGroup_master.getInserted_by());
				iTrans_sale.setInserted_on(ldt);
				iTrans_sale.setUpdated_by("NA");
				iTrans_sale.setUpdated_on(ldt);
				iTrans_sale.setDeleted_by("NA");
				iTrans_sale.setDeleted_on(ldt);
			}
			
			if(!stkTrnsSaleSet.isEmpty())
			{
				iGroup_master.setItem_group_master_stk_trans_sale(stkTrnsSaleSet.iterator().next());
			}
			
			item_group_master_stk_trans_purRepository.updateItem_group_master_stk_trans_pur(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_stk_trans_pur> stkTrnsPurSet = new HashSet<Item_group_master_stk_trans_pur>();
			stkTrnsPurSet.add(iGroup_master.getItem_group_master_stk_trans_pur());
			for(Item_group_master_stk_trans_pur iTrans_pur : stkTrnsPurSet)
			{
				iTrans_pur.setItem_group_id(op.get().getItem_group_id());
				iTrans_pur.setItem_group_code(iGroup_master.getItem_group_code());
				iTrans_pur.setCompany_id(iGroup_master.getCompany_id());
				iTrans_pur.setFin_year(iGroup_master.getFin_year());
				iTrans_pur.setModified_type("INSERTED");
				iTrans_pur.setInserted_by(iGroup_master.getInserted_by());
				iTrans_pur.setInserted_on(ldt);
				iTrans_pur.setUpdated_by("NA");
				iTrans_pur.setUpdated_on(ldt);
				iTrans_pur.setDeleted_by("NA");
				iTrans_pur.setDeleted_on(ldt);
			}
			
			if(!stkTrnsPurSet.isEmpty())
			{
				iGroup_master.setItem_group_master_stk_trans_pur(stkTrnsPurSet.iterator().next());
			}
			
			item_group_jobwork_sales_accRepository.updateItem_group_master_jobwork(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_jobwork_sales_acc> jobwork1 = new HashSet<Item_group_jobwork_sales_acc>();
			jobwork1.add(iGroup_master.getItem_group_jobwork_sales_acc());
			for(Item_group_jobwork_sales_acc jobwork : jobwork1)
			{
				jobwork.setItem_group_id(op.get().getItem_group_id());
				jobwork.setItem_group_code(iGroup_master.getItem_group_code());
				//String[] strAr1=new String[] {"0"};
				//jobwork.setJw_item_total(strAr1);
				jobwork.setCompany_id(iGroup_master.getCompany_id());
				jobwork.setFin_year(iGroup_master.getFin_year());
				jobwork.setModified_type("INSERTED");
				jobwork.setInserted_by(iGroup_master.getInserted_by());
				jobwork.setInserted_on(ldt);
				jobwork.setUpdated_by("NA");
				jobwork.setUpdated_on(ldt);
				jobwork.setDeleted_by("NA");
				jobwork.setDeleted_on(ldt);
			}
			
			if(!jobwork1.isEmpty())
			{
				iGroup_master.setItem_group_jobwork_sales_acc(jobwork1.iterator().next());
			}
			
			item_group_jobwork_sales_return_accRepository.updateItem_group_master_jobwork_return(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_jobwork_sales_return_acc> jobreturn1 = new HashSet<Item_group_jobwork_sales_return_acc>();
			jobreturn1.add(iGroup_master.getItem_group_jobwork_sales_return_acc());
			for(Item_group_jobwork_sales_return_acc jobreturn : jobreturn1)
			{
				jobreturn.setItem_group_id(op.get().getItem_group_id());
				jobreturn.setItem_group_code(iGroup_master.getItem_group_code());
			//	String[] strAr1=new String[] {"0"};
			//	jobreturn.setJw_sr_item_total(strAr1);
				jobreturn.setCompany_id(iGroup_master.getCompany_id());
				jobreturn.setFin_year(iGroup_master.getFin_year());
				jobreturn.setModified_type("INSERTED");
				jobreturn.setInserted_by(iGroup_master.getInserted_by());
				jobreturn.setInserted_on(ldt);
				jobreturn.setUpdated_by("NA");
				jobreturn.setUpdated_on(ldt);
				jobreturn.setDeleted_by("NA");
				jobreturn.setDeleted_on(ldt);
			}
			
			if(!jobreturn1.isEmpty())
			{
				iGroup_master.setItem_group_jobwork_sales_return_acc(jobreturn1.iterator().next());
			}
			
		}
		else 
		{
			item_group_master_sales_accRepository.updateItem_group_master_sales_acc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_sales_acc> saleAccSet = new HashSet<Item_group_master_sales_acc>();
			saleAccSet.add(iGroup_master.getItem_group_master_sales_acc());
			for(Item_group_master_sales_acc iSales_acc : saleAccSet)
			{
				iSales_acc.setItem_group_id(op.get().getItem_group_id());
				iSales_acc.setItem_group_code(iGroup_master.getItem_group_code());
				iSales_acc.setCompany_id(iGroup_master.getCompany_id());
				iSales_acc.setFin_year(iGroup_master.getFin_year());
				iSales_acc.setModified_type("INSERTED");
				iSales_acc.setInserted_by(iGroup_master.getInserted_by());
				iSales_acc.setInserted_on(ldt);
				iSales_acc.setUpdated_by("NA");
				iSales_acc.setUpdated_on(ldt);
				iSales_acc.setDeleted_by("NA");
				iSales_acc.setDeleted_on(ldt);
			}
			
			if(!saleAccSet.isEmpty())
			{
				iGroup_master.setItem_group_master_sales_acc(saleAccSet.iterator().next());
			}
			
			item_group_master_pur_accRepository.updateItem_group_master_pur_acc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_pur_acc> purAccSet = new HashSet<Item_group_master_pur_acc>();
			purAccSet.add(iGroup_master.getItem_group_master_pur_acc ());
			for(Item_group_master_pur_acc iPur_acc : purAccSet)
			{
				iPur_acc.setItem_group_id(op.get().getItem_group_id());
				iPur_acc.setItem_group_code(iGroup_master.getItem_group_code());
				iPur_acc.setCompany_id(iGroup_master.getCompany_id());
				iPur_acc.setFin_year(iGroup_master.getFin_year());
				iPur_acc.setModified_type("INSERTED");
				iPur_acc.setInserted_by(iGroup_master.getInserted_by());
				iPur_acc.setInserted_on(ldt);
				iPur_acc.setUpdated_by("NA");
				iPur_acc.setUpdated_on(ldt);
				iPur_acc.setDeleted_by("NA");
				iPur_acc.setDeleted_on(ldt);
			}
			
			if(!purAccSet.isEmpty())
			{
				iGroup_master.setItem_group_master_pur_acc(purAccSet.iterator().next());
			}
			
			item_group_master_sales_retaccRepository.updateItem_group_master_sales_retacc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_sales_retacc> saleAccRtnSet = new HashSet<Item_group_master_sales_retacc>();
			saleAccRtnSet.add(iGroup_master.getItem_group_master_sales_retacc());
			for(Item_group_master_sales_retacc iSales_retacc : saleAccRtnSet)
			{
				iSales_retacc.setItem_group_id(op.get().getItem_group_id());
				iSales_retacc.setItem_group_code(iGroup_master.getItem_group_code());
				iSales_retacc.setCompany_id(iGroup_master.getCompany_id());
				iSales_retacc.setFin_year(iGroup_master.getFin_year());
				iSales_retacc.setModified_type("INSERTED");
				iSales_retacc.setInserted_by(iGroup_master.getInserted_by());
				iSales_retacc.setInserted_on(ldt);
				iSales_retacc.setUpdated_by("NA");
				iSales_retacc.setUpdated_on(ldt);
				iSales_retacc.setDeleted_by("NA");
				iSales_retacc.setDeleted_on(ldt);
			}
			
			if(!saleAccRtnSet.isEmpty())
			{
				iGroup_master.setItem_group_master_sales_retacc(saleAccRtnSet.iterator().next());
			}
			
			item_group_master_pur_retaccRepository.updateItem_group_master_pur_retacc(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_pur_retacc> purAccRtnSet = new HashSet<Item_group_master_pur_retacc>();
			purAccRtnSet.add(iGroup_master.getItem_group_master_pur_retacc());
			for(Item_group_master_pur_retacc iPur_retacc : purAccRtnSet)
			{
				iPur_retacc.setItem_group_id(op.get().getItem_group_id());
				iPur_retacc.setItem_group_code(iGroup_master.getItem_group_code());
				iPur_retacc.setCompany_id(iGroup_master.getCompany_id());
				iPur_retacc.setFin_year(iGroup_master.getFin_year());
				iPur_retacc.setModified_type("INSERTED");
				iPur_retacc.setInserted_by(iGroup_master.getInserted_by());
				iPur_retacc.setInserted_on(ldt);
				iPur_retacc.setUpdated_by("NA");
				iPur_retacc.setUpdated_on(ldt);
				iPur_retacc.setDeleted_by("NA");
				iPur_retacc.setDeleted_on(ldt);
			}
			
			if(!purAccRtnSet.isEmpty())
			{
				iGroup_master.setItem_group_master_pur_retacc(purAccRtnSet.iterator().next());
			}
			
			item_group_master_stk_trans_saleRepository.updateItem_group_master_stk_trans_sale(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_stk_trans_sale> stkTrnsSaleSet = new HashSet<Item_group_master_stk_trans_sale>();
			stkTrnsSaleSet.add(iGroup_master.getItem_group_master_stk_trans_sale());
			for(Item_group_master_stk_trans_sale iTrans_sale : stkTrnsSaleSet)
			{
				iTrans_sale.setItem_group_id(op.get().getItem_group_id());
				iTrans_sale.setItem_group_code(iGroup_master.getItem_group_code());
				iTrans_sale.setCompany_id(iGroup_master.getCompany_id());
				iTrans_sale.setFin_year(iGroup_master.getFin_year());
				iTrans_sale.setModified_type("INSERTED");
				iTrans_sale.setInserted_by(iGroup_master.getInserted_by());
				iTrans_sale.setInserted_on(ldt);
				iTrans_sale.setUpdated_by("NA");
				iTrans_sale.setUpdated_on(ldt);
				iTrans_sale.setDeleted_by("NA");
				iTrans_sale.setDeleted_on(ldt);
			}
			
			if(!stkTrnsSaleSet.isEmpty())
			{
				iGroup_master.setItem_group_master_stk_trans_sale(stkTrnsSaleSet.iterator().next());
			}
			
			item_group_master_stk_trans_purRepository.updateItem_group_master_stk_trans_pur(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_master_stk_trans_pur> stkTrnsPurSet = new HashSet<Item_group_master_stk_trans_pur>();
			stkTrnsPurSet.add(iGroup_master.getItem_group_master_stk_trans_pur());
			for(Item_group_master_stk_trans_pur iTrans_pur : stkTrnsPurSet)
			{
				iTrans_pur.setItem_group_id(op.get().getItem_group_id());
				iTrans_pur.setItem_group_code(iGroup_master.getItem_group_code());
				iTrans_pur.setCompany_id(iGroup_master.getCompany_id());
				iTrans_pur.setFin_year(iGroup_master.getFin_year());
				iTrans_pur.setModified_type("INSERTED");
				iTrans_pur.setInserted_by(iGroup_master.getInserted_by());
				iTrans_pur.setInserted_on(ldt);
				iTrans_pur.setUpdated_by("NA");
				iTrans_pur.setUpdated_on(ldt);
				iTrans_pur.setDeleted_by("NA");
				iTrans_pur.setDeleted_on(ldt);
			}
			
			if(!stkTrnsPurSet.isEmpty())
			{
				iGroup_master.setItem_group_master_stk_trans_pur(stkTrnsPurSet.iterator().next());
			}
			
			item_group_jobwork_sales_accRepository.updateItem_group_master_jobwork(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_jobwork_sales_acc> jobwork1 = new HashSet<Item_group_jobwork_sales_acc>();
			jobwork1.add(iGroup_master.getItem_group_jobwork_sales_acc());
			for(Item_group_jobwork_sales_acc jobwork : jobwork1)
			{
				jobwork.setItem_group_id(op.get().getItem_group_id());
				jobwork.setItem_group_code(iGroup_master.getItem_group_code());
				//String[] strAr1=new String[] {"0"};
				//jobwork.setJw_item_total(strAr1);
				jobwork.setCompany_id(iGroup_master.getCompany_id());
				jobwork.setFin_year(iGroup_master.getFin_year());
				jobwork.setModified_type("INSERTED");
				jobwork.setInserted_by(iGroup_master.getInserted_by());
				jobwork.setInserted_on(ldt);
				jobwork.setUpdated_by("NA");
				jobwork.setUpdated_on(ldt);
				jobwork.setDeleted_by("NA");
				jobwork.setDeleted_on(ldt);
			}
			
			if(!jobwork1.isEmpty())
			{
				iGroup_master.setItem_group_jobwork_sales_acc(jobwork1.iterator().next());
			}
			
			item_group_jobwork_sales_return_accRepository.updateItem_group_master_jobwork_return(op.get().getItem_group_id(),"UPDATED");
			//Static
			Set<Item_group_jobwork_sales_return_acc> jobreturn1 = new HashSet<Item_group_jobwork_sales_return_acc>();
			jobreturn1.add(iGroup_master.getItem_group_jobwork_sales_return_acc());
			for(Item_group_jobwork_sales_return_acc jobreturn : jobreturn1)
			{
				jobreturn.setItem_group_id(op.get().getItem_group_id());
				jobreturn.setItem_group_code(iGroup_master.getItem_group_code());
			//	String[] strAr1=new String[] {"0"};
			//	jobreturn.setJw_sr_item_total(strAr1);
				jobreturn.setCompany_id(iGroup_master.getCompany_id());
				jobreturn.setFin_year(iGroup_master.getFin_year());
				jobreturn.setModified_type("INSERTED");
				jobreturn.setInserted_by(iGroup_master.getInserted_by());
				jobreturn.setInserted_on(ldt);
				jobreturn.setUpdated_by("NA");
				jobreturn.setUpdated_on(ldt);
				jobreturn.setDeleted_by("NA");
				jobreturn.setDeleted_on(ldt);
			}
			
			if(!jobreturn1.isEmpty())
			{
				iGroup_master.setItem_group_jobwork_sales_return_acc(jobreturn1.iterator().next());
			}
			
			
			
			
			
		}
		
		
			
		return item_group_masterRepository.save(iGroup_master);
		
	}
	
	@Transactional
	public Item_group_master deleteItemGroup(Item_group_master iGroup_master,long id)
	{
		Optional<Item_group_master> op = item_group_masterRepository.findById(id);
		Item_group_master iGroup=item_group_masterRepository.getGroupDtls(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iGroup.setInserted_by(op.get().getInserted_by());
		iGroup.setInserted_on(op.get().getInserted_on());
		iGroup.setUpdated_by(op.get().getUpdated_by());
		iGroup.setUpdated_on(op.get().getUpdated_on());
		iGroup.setDeleted_by(userRepository.getUserDetails(iGroup_master.getUsername()).getName());
		iGroup.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iGroup.setId(id);
		}
		
		if(item_masterRepository.getItemDtlsThruGroup(iGroup.getItem_group_id()).isPresent() ||
				item_group_masterRepository.chkItemGroup(iGroup.getItem_group_id()).isPresent()) {
			return iGroup_master;
		}else {
			iGroup.setModified_type("DELETED");
			item_group_master_sales_accRepository.updateItem_group_master_sales_acc(op.get().getItem_group_id(),"DELETED");
			item_group_master_pur_accRepository.updateItem_group_master_pur_acc(op.get().getItem_group_id(),"DELETED");
			item_group_master_sales_retaccRepository.updateItem_group_master_sales_retacc(op.get().getItem_group_id(),"DELETED");
			item_group_master_pur_retaccRepository.updateItem_group_master_pur_retacc(op.get().getItem_group_id(),"DELETED");
			item_group_master_stk_trans_saleRepository.updateItem_group_master_stk_trans_sale(op.get().getItem_group_id(),"DELETED");
			item_group_master_stk_trans_purRepository.updateItem_group_master_stk_trans_pur(op.get().getItem_group_id(),"DELETED");
			item_group_jobwork_sales_accRepository.updateItem_group_master_jobwork(op.get().getItem_group_id(),"DELETED");
			item_group_jobwork_sales_return_accRepository.updateItem_group_master_jobwork_return(op.get().getItem_group_id(),"DELETED");
			
			return item_group_masterRepository.save(iGroup);
		}
	}
	
	public Item_group_master_sales_accDTO getGroupSalesAccThruItems(String items) {
		
		Type listType = new TypeToken<Item_group_master_sales_accDTO>() {}.getType();
		
		Item_group_master_sales_acc iSales_acc=null;
		System.out.println("Items: "+items);
		String[] arrOfItem=items.split(",");
		
		LinkedHashSet<String> allGrp=new LinkedHashSet<String>();
		LinkedHashSet<String> itemTotals=new LinkedHashSet<String>();
		LinkedHashSet<String> adjPlus=new LinkedHashSet<String>();
		LinkedHashSet<String> discounts=new LinkedHashSet<String>();
		LinkedHashSet<String> adjMinus=new LinkedHashSet<String>();
		
		for(int i=0;i<arrOfItem.length;i++)
		{
			allGrp.add(item_masterRepository.getItemDetailsThruItemId(arrOfItem[i]).getItem_group());
		}
		ArrayList<String> uniqueGrp=new ArrayList<String>(allGrp);
		
		String itemtotal="",discount="",adjplus="",adjminus="",fitemtotal="",fdiscount="",fadjplus="",fadjminus="";
		for(int j=0;j<uniqueGrp.size();j++)
		{
			System.err.println("Groups: "+uniqueGrp.get(j));
			iSales_acc=item_group_master_sales_accRepository.getItem_group_master_sales_acc(uniqueGrp.get(j));
			
			itemTotals.add(iSales_acc.getItem_total());adjPlus.add(iSales_acc.getAdjplus());
			discounts.add(iSales_acc.getDiscount());adjMinus.add(iSales_acc.getAdjminus());
			
			//itemtotal +=iSales_acc.getItem_total()+",";discount +=iSales_acc.getDiscount()+",";
			//adjplus +=iSales_acc.getAdjplus()+",";adjminus +=iSales_acc.getAdjminus()+",";
		}
		ArrayList<String> uItemTotals=new ArrayList<String>(itemTotals);
		ArrayList<String> uAdjPlus=new ArrayList<String>(adjPlus);
		ArrayList<String> uDiscounts=new ArrayList<String>(discounts);
		ArrayList<String> uAdjMinus=new ArrayList<String>(adjMinus);
		
		for(int k=0;k<uItemTotals.size();k++){fitemtotal +=uItemTotals.get(k) + ",";}
		for(int m=0;m<uAdjPlus.size();m++){fadjplus +=uAdjPlus.get(m) + ",";}
		for(int l=0;l<uDiscounts.size();l++){fdiscount +=uDiscounts.get(l) + ",";}
		for(int n=0;n<uAdjMinus.size();n++){fadjminus +=uAdjMinus.get(n) + ",";}
		
		Item_group_master_sales_accDTO salesAcc= new ModelMapper().map(iSales_acc,listType);

		salesAcc.setItem_total(fitemtotal.substring(0, fitemtotal.length()-1));
		salesAcc.setDiscount(fdiscount.substring(0, fdiscount.length()-1));
		salesAcc.setAdjplus(fadjplus.substring(0, fadjplus.length()-1));
		salesAcc.setAdjminus(fadjminus.substring(0, fadjminus.length()-1));
		
		return salesAcc;
	}
	
	public List<Item_group_master> findItemGroups(String searchtext,String company)
	{
		List<Item_group_master> igroupList=new ArrayList<Item_group_master>();
		igroupList.addAll(item_group_masterRepository.findAll());
		
		igroupList.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(item_group_masterRepository.getItemGroups(ig.getParent_group()).getGroup_name());
			}else {ig.setParent_group("None");}
		});
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Item_group_master> allData = igroupList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Item_group_master::getGroup_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			
			List<Item_group_master> allData = igroupList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED")  && c.getCompany_id().equals(company) && 
							  (c.getItem_group_id()+c.getItem_group_code()+c.getGroup_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Item_group_master::getGroup_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO chkItemGroupCodeStatus(String code){
		String result="";
		
		if(item_group_masterRepository.chkItemGroupCodeStatus(code).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
	
	/*public List<Map<String, Object>> getGroupItemLedgerForJob(String group)
 	{
 		List<Map<String,Object>> ledgerlist=new ArrayList <>();
 		String groupmultiple="";
 		ArrayList<String> cusmultiple=new ArrayList<String>();
 		String multigroup[]=group.split(",");
			
			for(int i=0;i<multigroup.length;i++)
			{
				groupmultiple=item_group_masterRepository.getGroupItemLedgerForJob(multigroup[i]);
				String anothermulti[]=groupmultiple.split(",");
				for(int j=0;j<anothermulti.length;j++)
				{
					cusmultiple.add(anothermulti[j]);
				}
			}
			
			ledgerlist.addAll(item_group_masterRepository.getGroupItemLedgerForJob1(cusmultiple));
 		
 		return ledgerlist;
 	}*/

}
