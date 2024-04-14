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

import com.AnkitIndia.Exporttotally.Tallyrequest_ProductionReg;
import com.AnkitIndia.Exporttotally.Tallyrequest_ProductionSpecial;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Prodsummary;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_spl;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_spl_input_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_spl_output_details;
import com.AnkitIndia.jwtauthentication.repository.Bom_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_special_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transaction_splRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transaction_spl_input_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transaction_spl_output_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_spl_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_spl_output_detailsDTO;

@Service
public class Production_transaction_splService_Imp implements Production_transaction_splService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Production_transaction_splRepository production_transaction_splRepository;
	
	@Autowired
	Production_transaction_spl_input_detailsRepository production_transaction_spl_input_detailsRepository;
	
	@Autowired
	Production_transaction_spl_output_detailsRepository production_transaction_spl_output_detailsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	@Autowired
	Bom_masterRepository bom_masterRepository;
	
	@Autowired
	Production_planning_special_dtlsRepository production_planning_special_dtlsRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	public SequenceIdDTO getPTSSequenceId(String businessunit,String sfloor,String prefix,String company) {
		Long slno=0L;String fprefix="",code="",buprefix="",sfprefix="";
		code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(businessunit).getBusinessunit_name();
		sfprefix=shop_floor_masterRepository.getShopFloorDtls(sfloor).getShop_floor_name();
		
		fprefix=prefix+"/"+code+"/"+buprefix.substring(0,3)+"/"+sfprefix.substring(0,3).toUpperCase()+"/";;
		
		System.err.println("Code: > "+fprefix);
		String gen_sno=production_transaction_splRepository.getPTSequenceId(businessunit,sfloor,fprefix,company);
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
	public Production_transaction_spl save(Production_transaction_spl pTransaction)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(production_transaction_splRepository.countId() != null ) {
			slno=Long.parseLong(production_transaction_splRepository.countId());
		}
		String prefix="PT";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		
		/*End checking transaction no for unique */
		
		if(pTransaction.getBusiness_unit().compareTo("0") !=0 && pTransaction.getBusiness_unit().compareTo("") !=0 && pTransaction.getBusiness_unit() !=null) {
			pTransaction.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pTransaction.getBusiness_unit()).getBusinessunit_name());
		}else {pTransaction.setBusiness_unitname("None");}
		
		if(pTransaction.getShop_floor().compareTo("0") !=0 && pTransaction.getShop_floor().compareTo("") !=0 && pTransaction.getShop_floor() !=null) {
			pTransaction.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(pTransaction.getShop_floor()).getShop_floor_name());
		}else {pTransaction.setShop_floorname("None");}
		
		if(pTransaction.getProcess().compareTo("0") !=0 && pTransaction.getProcess().compareTo("") !=0 && pTransaction.getProcess() !=null) {
			pTransaction.setProd_processname(process_masterRepository.getProcessDetails(pTransaction.getProcess()).getProcess_desc());
			//pTransaction.setProd_processname(production_planning_special_dtlsRepository.getProdPlanSplProcessDtls(bunit,sfloor,process,company));
		}else {pTransaction.setProd_processname("None");}
		
		
		if(pTransaction.getProd_desc().compareTo("0") !=0 && pTransaction.getProd_desc().compareTo("") !=0 && pTransaction.getProd_desc() !=null) {
			pTransaction.setProd_description(bom_masterRepository.getBomDetails(pTransaction.getProd_desc(),pTransaction.getCompany_id()).getProd_desc());
		}else {pTransaction.setProd_description("None");}
		
		pTransaction.setProd_trans_id(gen_sno);
		pTransaction.setModified_type("INSERTED");
		pTransaction.setInserted_by(userRepository.getUserDetails(pTransaction.getUsername()).getName());
		pTransaction.setInserted_on(ldt);
		pTransaction.setUpdated_by("NA");
		pTransaction.setUpdated_on(ldt);
		pTransaction.setDeleted_by("NA");
		pTransaction.setDeleted_on(ldt);
		
		
		Set<Production_transaction_spl_input_details> bInput=new HashSet<Production_transaction_spl_input_details>();
		bInput.addAll(pTransaction.getProduction_transaction_spl_input_details());
		for(Production_transaction_spl_input_details bInput_details:bInput) 
		{
			
			bInput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bInput_details.setProd_trans_id(gen_sno);
			bInput_details.setCompany_id(pTransaction.getCompany_id());
			bInput_details.setFin_year(pTransaction.getFin_year());
			bInput_details.setModified_type("INSERTED");
			bInput_details.setInserted_by(pTransaction.getInserted_by());
			bInput_details.setInserted_on(pTransaction.getInserted_on());
			bInput_details.setUpdated_by(pTransaction.getUpdated_by());
			bInput_details.setUpdated_on(pTransaction.getUpdated_on());
			bInput_details.setDeleted_by("NA");
			bInput_details.setDeleted_on(ldt);

		}
		pTransaction.setProduction_transaction_spl_input_details(bInput);
		
		Set<Production_transaction_spl_output_details> bOutput=new HashSet<Production_transaction_spl_output_details>();
		bOutput.addAll(pTransaction.getProduction_transaction_spl_output_details());
		for(Production_transaction_spl_output_details bOutput_details:bOutput) 
		{
			bOutput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bOutput_details.setProd_trans_id(gen_sno);
			bOutput_details.setCompany_id(pTransaction.getCompany_id());
			bOutput_details.setFin_year(pTransaction.getFin_year());
			bOutput_details.setModified_type("INSERTED");
			bOutput_details.setInserted_by(pTransaction.getInserted_by());
			bOutput_details.setInserted_on(pTransaction.getInserted_on());
			bOutput_details.setUpdated_by(pTransaction.getUpdated_by());
			bOutput_details.setUpdated_on(pTransaction.getUpdated_on());
			bOutput_details.setDeleted_by("NA");
			bOutput_details.setDeleted_on(ldt);
			
		}
		pTransaction.setProduction_transaction_spl_output_details(bOutput);
		
		return production_transaction_splRepository.save(pTransaction);
	}
	
	public List<Production_transaction_spl> findAllProTrans(){
		List<Production_transaction_spl> proList=new ArrayList<Production_transaction_spl>();
		proList.addAll(production_transaction_splRepository.findAllProTrans());
		
		return proList;
	}
	
	public Production_transaction_spl findOne(long id)
	{
		Optional<Production_transaction_spl> op=this.production_transaction_splRepository.findById(id);
		return op.get();
	}
	
	public List<Production_transaction_spl_input_detailsDTO> getProdTranInputDetails(String prodid)
	{
		List<Production_transaction_spl_input_details> modelList=new ArrayList<Production_transaction_spl_input_details>();
		modelList.addAll(production_transaction_spl_input_detailsRepository.getProdTranInputDetails(prodid));
		Type listType=new TypeToken<List<Production_transaction_spl_input_detailsDTO>>() {}.getType();
		
		List<Production_transaction_spl_input_detailsDTO> inputDtls=new ModelMapper().map(modelList,listType);
		
		return inputDtls;
	}
	
	public List<Production_transaction_spl_output_detailsDTO> getProdTranOutputDetails(String prodid)
	{
		List<Production_transaction_spl_output_details> modelList=new ArrayList<Production_transaction_spl_output_details>();
		modelList.addAll(production_transaction_spl_output_detailsRepository.getProdTranOutputDetails(prodid));
		Type listType=new TypeToken<List<Production_transaction_spl_output_detailsDTO>>() {}.getType();
		
		List<Production_transaction_spl_output_detailsDTO> outDtls=new ModelMapper().map(modelList,listType);
		
		return outDtls;
	}
	
	@Transactional
	public Production_transaction_spl update(Production_transaction_spl pTransaction,long id) {
		Optional<Production_transaction_spl> op = production_transaction_splRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(pTransaction.getBusiness_unit().compareTo("0") !=0 && pTransaction.getBusiness_unit().compareTo("") !=0 && pTransaction.getBusiness_unit() !=null) {
			pTransaction.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pTransaction.getBusiness_unit()).getBusinessunit_name());
		}else {pTransaction.setBusiness_unitname("None");}
		
		if(pTransaction.getShop_floor().compareTo("0") !=0 && pTransaction.getShop_floor().compareTo("") !=0 && pTransaction.getShop_floor() !=null) {
			pTransaction.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(pTransaction.getShop_floor()).getShop_floor_name());
		}else {pTransaction.setShop_floorname("None");}
		
		if(pTransaction.getProcess().compareTo("0") !=0 && pTransaction.getProcess().compareTo("") !=0 && pTransaction.getProcess() !=null) {
			pTransaction.setProd_processname(process_masterRepository.getProcessDetails(pTransaction.getProcess()).getProcess_desc());
			//pTransaction.setProd_processname(production_planning_special_dtlsRepository.getProdPlanSplProcessDtls(bunit,sfloor,process,company));
		}else {pTransaction.setProd_processname("None");}
		
		if(pTransaction.getProd_desc().compareTo("0") !=0 && pTransaction.getProd_desc().compareTo("") !=0 && pTransaction.getProd_desc() !=null) {
			pTransaction.setProd_description(bom_masterRepository.getBomDetails(pTransaction.getProd_desc(),pTransaction.getCompany_id()).getProd_desc());
		}else {pTransaction.setProd_description("None");}
		
		pTransaction.setProd_trans_id(op.get().getProd_trans_id());
		pTransaction.setModified_type("INSERTED");
		pTransaction.setInserted_by(op.get().getInserted_by());
		pTransaction.setInserted_on(op.get().getInserted_on());
		pTransaction.setUpdated_by(userRepository.getUserDetails(pTransaction.getUsername()).getName());
		pTransaction.setUpdated_on(ldt);
		pTransaction.setDeleted_by("NA");
		pTransaction.setDeleted_on(ldt);
		
		//Update
		production_transaction_spl_input_detailsRepository.updateProduction_transaction_spl_input_details(op.get().getProd_trans_id());
		
		Set<Production_transaction_spl_input_details> bInput=new HashSet<Production_transaction_spl_input_details>();
		bInput.addAll(pTransaction.getProduction_transaction_spl_input_details());
		for(Production_transaction_spl_input_details bInput_details:bInput) 
		{
			bInput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bInput_details.setProd_trans_id(op.get().getProd_trans_id());
			bInput_details.setCompany_id(pTransaction.getCompany_id());
			bInput_details.setFin_year(pTransaction.getFin_year());
			bInput_details.setModified_type("INSERTED");
			bInput_details.setInserted_by(pTransaction.getInserted_by());
			bInput_details.setInserted_on(pTransaction.getInserted_on());
			bInput_details.setUpdated_by(pTransaction.getUpdated_by());
			bInput_details.setUpdated_on(pTransaction.getUpdated_on());
			bInput_details.setDeleted_by("NA");
			bInput_details.setDeleted_on(ldt);
			
		}
		pTransaction.setProduction_transaction_spl_input_details(bInput);
		
		//Update
		production_transaction_spl_output_detailsRepository.updateProduction_transaction_spl_output_details(op.get().getProd_trans_id());
		
		Set<Production_transaction_spl_output_details> bOutput=new HashSet<Production_transaction_spl_output_details>();
		bOutput.addAll(pTransaction.getProduction_transaction_spl_output_details());
		for(Production_transaction_spl_output_details bOutput_details:bOutput) 
		{
			bOutput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bOutput_details.setProd_trans_id(op.get().getProd_trans_id());
			bOutput_details.setCompany_id(pTransaction.getCompany_id());
			bOutput_details.setFin_year(pTransaction.getFin_year());
			bOutput_details.setModified_type("INSERTED");
			bOutput_details.setInserted_by(pTransaction.getInserted_by());
			bOutput_details.setInserted_on(pTransaction.getInserted_on());
			bOutput_details.setUpdated_by(pTransaction.getUpdated_by());
			bOutput_details.setUpdated_on(pTransaction.getUpdated_on());
			bOutput_details.setDeleted_by("NA");
			bOutput_details.setDeleted_on(ldt);
			
		}
		pTransaction.setProduction_transaction_spl_output_details(bOutput);
		
		if(op.isPresent())
		{
			pTransaction.setId(id);
		}
		return production_transaction_splRepository.save(pTransaction);
	}
	
    public List<Map<String, Object>> getspecialProdInputReport(String business_unit,String shop_floor,String fromdate,String todate)
    {
    	List<Map<String, Object>> inputReport=production_transaction_spl_input_detailsRepository.getspecialProdInputReport(shop_floor,fromdate,todate);
    			
    	return inputReport;
    }
	 
	public List<Map<String, Object>> getspecialProdOutputReport(String business_unit,String shop_floor,String fromdate,String todate)
	{
		List<Map<String, Object>> outputReport=production_transaction_spl_output_detailsRepository.getspecialProdOutputReport(shop_floor,fromdate,todate);
				
		return outputReport;
	}
	
	@Transactional
	public StatusDTO Prodtransaction_spl_Posting(String prodid,long id) 
	{
		StatusDTO res=new StatusDTO();
		
		
		List<Production_transaction_spl_input_details> input_produ=production_transaction_spl_input_detailsRepository.getProdTranInputDetails(prodid);
		
		List<Production_transaction_spl_output_details> output_produ=production_transaction_spl_output_detailsRepository.getProdTranOutputDetails(prodid);
		
		Optional<Production_transaction_spl> op = production_transaction_splRepository.findById(id);
		 
		String output;
		Tallyrequest_ProductionSpecial tally=new Tallyrequest_ProductionSpecial();
	System.out.println("hello");
		
        String item_name[]=new String[input_produ.size()];
        String item_amount[]=new String[input_produ.size()];
        String item_rate[]=new String[input_produ.size()];
        String item_passeditemqty[]=new String[input_produ.size()];
        String item_uom[]=new String[input_produ.size()];
        String packing_uom[]=new String[input_produ.size()];
        String packing_qty[]=new String[input_produ.size()];
        String price_based_on[]=new String[input_produ.size()];
        
        for(int i=0;i<input_produ.size();i++) 
        {
        	item_name[i]=item_masterRepository.itemNameById(input_produ.get(i).getItem()).getItem_name();
        	item_amount[i]=String.valueOf(2500*(input_produ.get(i).getProduction_qty()));
        	item_rate[i]=String.valueOf(2500);
        	price_based_on[i]=input_produ.get(i).getUom_basedon();
        	item_passeditemqty[i]=String.valueOf(input_produ.get(i).getProduction_qty());
        	packing_qty[i]=String.valueOf((int)(input_produ.get(i).getPacking_qty()));
        	packing_uom[i]=input_produ.get(i).getPacking_uom(); 
        	item_uom[i]=customUomMasterRepository.getUomByIGroup(input_produ.get(i).getProduction_uom()).getDescription()  ;//CustomUomMasterRepository
        	
        }
        
        String output_item_name[]=new String[output_produ.size()];
        String  output_item_amount[]=new String[output_produ.size()];
        String  output_item_rate[]=new String[output_produ.size()];
        String  output_item_passeditemqty[]=new String[output_produ.size()];
        String  output_item_uom[]=new String[output_produ.size()];
        String  output_packing_uom[]=new String[output_produ.size()];
        String  output_packing_qty[]=new String[output_produ.size()];
        String  output_price_based_on[]=new String[output_produ.size()];
        
        for(int i=0;i<output_produ.size();i++) 
        {
        	output_item_name[i]=item_masterRepository.itemNameById(output_produ.get(i).getItem()).getItem_name();
        	
        	 output_item_amount[i]=String.valueOf(2500*(output_produ.get(i).getProduction_qty()));
        	 output_item_rate[i]=String.valueOf(2500);
        	 output_price_based_on[i]=output_produ.get(i).getUom_basedon();
        	 output_item_passeditemqty[i]=String.valueOf(output_produ.get(i).getProduction_qty());
        	 output_packing_qty[i]=String.valueOf((int)(output_produ.get(i).getPacking_qty()));
        	 output_packing_uom[i]=output_produ.get(i).getPacking_uom(); 
        	 output_item_uom[i]=customUomMasterRepository.getUomByIGroup(output_produ.get(i).getProduction_uom()).getDescription();
        	 
        }
        
        
        
		
		
		try  
		{
			String date=op.get().getProd_trans_date().replace("-", ""); 
			output=tally.SendToTally(date,item_name,item_rate,item_uom,
					item_passeditemqty,packing_uom,packing_qty,output_item_name,output_item_rate,output_item_uom,
					output_item_passeditemqty,output_packing_uom,output_packing_qty);
			
			System.out.println(" output :: "+output);
			String [] splitoutput = output.split("\\|\\|");
			System.out.println("export status ::"+splitoutput[1]);
			System.out.println(splitoutput[0] + " / " + splitoutput[1]+"/" +id);
			
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
			
			
			production_transaction_splRepository.exportuomtally(id,splitoutput[0],exportstatus);
			System.out.println();
			
		}
		catch(Exception e)
		{
		
			System.out.println(e);
		}
		return res;
	}
	
	
	@Transactional
	public StatusDTO prodtransaction_spl_Posting_Undo(long id,String username)
	{
		StatusDTO res=new StatusDTO();
		try  
		{
			LocalDateTime ldt = LocalDateTime.now();
			production_transaction_splRepository.prodSpclPostingUndo(id,username,ldt);
			res.setStatus("Done");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return res;
	}

}
