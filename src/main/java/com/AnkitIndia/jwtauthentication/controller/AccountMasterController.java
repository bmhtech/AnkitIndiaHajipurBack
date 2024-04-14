package com.AnkitIndia.jwtauthentication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AnkitIndia.jwtauthentication.model.Acc_group_master;
import com.AnkitIndia.jwtauthentication.model.Acc_narration_master;
import com.AnkitIndia.jwtauthentication.model.Acc_sub_group_master;
import com.AnkitIndia.jwtauthentication.model.Acc_cost_category_master;
import com.AnkitIndia.jwtauthentication.model.Acc_cost_centre_master;
import com.AnkitIndia.jwtauthentication.model.Acc_gen_ledger_master;
import com.AnkitIndia.jwtauthentication.security.services.Acc_group_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_narration_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_sub_group_masterService;
import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master;
import com.AnkitIndia.jwtauthentication.model.Acc_pay_mode_master;
import com.AnkitIndia.jwtauthentication.model.Acc_pay_term_master;
import com.AnkitIndia.jwtauthentication.model.Acc_taxcode_details_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_acceptance_norms_master_dtsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_term_master_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.security.services.Acc_acceptance_norms_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_pay_mode_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_pay_term_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_taxcode_details_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_cost_category_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_cost_centre_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_gen_ledger_masterService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class AccountMasterController {
	
/******************* Acc_acceptance_norms Master End*******************/
	
	@Autowired
	Acc_acceptance_norms_masterService acc_acceptance_norms_masterService;
	
	@PostMapping("/createAccAcceptanceNorms")
	public Acc_acceptance_norms_master save(@Valid @RequestBody Acc_acceptance_norms_master acc_acceptance_norms_master)
	{
		return acc_acceptance_norms_masterService.save(acc_acceptance_norms_master);
	}
	
	@GetMapping("/getAccAcceptanceNorms")
	
	public List<Acc_acceptance_norms_master> findAcc_acceptance_norms_masterAll()
	{
		return acc_acceptance_norms_masterService.findAll();
	}
	
	@GetMapping("/retriveAccAcceptanceNorms/{id}")
	public ResponseEntity<Acc_acceptance_norms_master> getAcc_acceptance_norms_master(@PathVariable(value="id") Long id)
	{
		Acc_acceptance_norms_master op = acc_acceptance_norms_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/accNormsRetriveList/{a_id}")
	public List<Acc_acceptance_norms_master_dtsDTO> accNormsRetriveList(@PathVariable(value = "a_id") String a_id)
	{
		return acc_acceptance_norms_masterService.accNormsRetriveList(a_id);
	}
	
	@PutMapping("/updateAccAcceptanceNorms/{id}")
	public ResponseEntity<Acc_acceptance_norms_master> updateAcc_acceptance_norms_master(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_acceptance_norms_master acc_acceptance_norms_master)
	{
		Acc_acceptance_norms_master op = acc_acceptance_norms_masterService.update(acc_acceptance_norms_master,id);
		return ResponseEntity.ok().body(op);
	}
	
	
	/******************* Acc_pay_mode_master Start *******************/
	@Autowired
	Acc_pay_mode_masterService acc_pay_mode_masterService;
	
	@PostMapping("/createAccPayMode")
	public Acc_pay_mode_master save(@Valid @RequestBody Acc_pay_mode_master paymode)
	{
		return acc_pay_mode_masterService.save(paymode);
	}
	
	@GetMapping("/getAccPayModes")
	public List<Acc_pay_mode_master> findpaymodeAll() 
	{
		return acc_pay_mode_masterService.findAll();
	}
	
	@GetMapping("/retriveAccPayModes/{id}")
	public ResponseEntity<Acc_pay_mode_master> retriveAccPayModes(@PathVariable(value="id") Long id)
	{
		Acc_pay_mode_master op = acc_pay_mode_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateAccPayMode/{id}")
	public ResponseEntity<Acc_pay_mode_master> updateAccPayMode(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_pay_mode_master paymode)
	{
		Acc_pay_mode_master op = acc_pay_mode_masterService.update(paymode,id);
		return ResponseEntity.ok().body(op);
	}
	  
	/******************* Acc_pay_mode_master End *******************/
	
	/******************* Acc_pay_term_master Start *******************/
	@Autowired
	Acc_pay_term_masterService acc_pay_term_masterService;
	
	@GetMapping("/getPayTermSequenceId")
	public ResponseEntity<SequenceIdDTO> getPayTermSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = acc_pay_term_masterService.getPayTermSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createAccPayTerm")
	public Acc_pay_term_master save(@Valid @RequestBody Acc_pay_term_master payterm)
	{
		return acc_pay_term_masterService.save(payterm);
	}
	
	@GetMapping("/getAccPayTerms")
	public List<Acc_pay_term_master> findPayTermAll() 
	{
		return acc_pay_term_masterService.findAll();
	}
	
	@GetMapping("/getAccPayTermsMaster")
	public List<Acc_pay_term_master> getAccPayTermsMaster() 
	{
		return acc_pay_term_masterService.getAccPayTermsMaster();
	}
	
	@GetMapping("/retriveAccPayTerms/{id}")
	public ResponseEntity<Acc_pay_term_master> retriveAccPayTerms(@PathVariable(value="id") Long id)
	{
		Acc_pay_term_master op = acc_pay_term_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/accPayTermsRetriveList/{p_id}")
	public List<Acc_pay_term_master_detailsDTO> accPayTermsRetriveList(@PathVariable(value = "p_id") String p_id)
	{
		return acc_pay_term_masterService.payTermRetriveList(p_id);
	}
	
	@PutMapping("/updateAccPayTerms/{id}")
	public ResponseEntity<Acc_pay_term_master> updateAccPayTerms(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_pay_term_master payterm)
	{
		Acc_pay_term_master op = acc_pay_term_masterService.update(payterm,id);
		return ResponseEntity.ok().body(op);
	}
	
	

	@PutMapping("/deleteAccPayTerm/{id}")
	public ResponseEntity<Acc_pay_term_master> deleteAccPayTerm(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Acc_pay_term_master acc_pay_term_master) {
		Acc_pay_term_master op = acc_pay_term_masterService.deleteAccPayTerm(acc_pay_term_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findAccPayTerm")
	public ResponseEntity<List<Acc_pay_term_master>> findAccPayTerm(@RequestParam(defaultValue = "") String searchtext) {
		List<Acc_pay_term_master> dList= acc_pay_term_masterService.findAccPayTerm(searchtext);
		if (dList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(dList);
		}
	}
	  
	/******************* Acc_pay_term_master End *******************/
	
	
	/******************* Acc_taxcode_details_master Start *******************/
	@Autowired
	Acc_taxcode_details_masterService acc_taxcode_details_masterService;
	
	@PostMapping("/createAccTaxCode")
	public Acc_taxcode_details_master save(@Valid @RequestBody Acc_taxcode_details_master taxcode)
	{
		return acc_taxcode_details_masterService.save(taxcode);
	}
	
	@GetMapping("/getAccTaxCodes")
	public List<Acc_taxcode_details_master> findTaxCodeAll() 
	{
		return acc_taxcode_details_masterService.findAll();
	}
	
	@GetMapping("/retriveAccTaxCode/{id}")
	public ResponseEntity<Acc_taxcode_details_master> getTaxCodeAll(@PathVariable(value="id") Long id)
	{
		Acc_taxcode_details_master op = acc_taxcode_details_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateAccTaxCode/{id}")
	public ResponseEntity<Acc_taxcode_details_master> updateTaxCodeAll(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_taxcode_details_master taxcode)
	{
		Acc_taxcode_details_master op = acc_taxcode_details_masterService.update(taxcode,id);
		return ResponseEntity.ok().body(op);
	}
	  
	/******************* Acc_taxcode_details_master End *******************/

	
    /******************* Acc Cost Category Master Start *******************/
	
	  @Autowired
	  Acc_cost_category_masterService acc_cost_category_masterService;
	
	
	  @PostMapping("/createAccCostCategory") 
	  public Acc_cost_category_master  save(@Valid @RequestBody Acc_cost_category_master acc_cost_category_master) 
	  {
		  return acc_cost_category_masterService.save(acc_cost_category_master); 
	  }
	  
	  @GetMapping("/getAccCostCategories") 
	  public List<Acc_cost_category_master> findAcc_cost_catagory_masterAll() 
	  { 
		  return acc_cost_category_masterService.findAll(); 
	  }
	  
	  @GetMapping("/retriveAccCostCategory/{id}") 
	  public ResponseEntity<Acc_cost_category_master> getAcc_cost_catagory_master(@PathVariable(value="id") Long id) 
	  {
		  Acc_cost_category_master op = acc_cost_category_masterService.findOne(id);
	      if(op==null)
	      { 
	    	  return ResponseEntity.notFound().build(); 
	      } 
	      else 
	      { 
	    	  return ResponseEntity.ok().body(op); 
	      }
	  }
	  
	  @PutMapping("/updateAccCostCategory/{id}") 
	  public ResponseEntity<Acc_cost_category_master> updateAcc_cost_catagory_master(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_cost_category_master acc_cost_category_master) 
	  {
		  Acc_cost_category_master op =acc_cost_category_masterService.update(acc_cost_category_master,id); 
		  return ResponseEntity.ok().body(op); 
	  }
	
	/******************* Acc Cost Category Master End*******************/
	  
	/******************* Acc Cost Centre Master Start *******************/
	@Autowired
	Acc_cost_centre_masterService acc_cost_centre_masterService;
	
	@PostMapping("/createAccCostCentre")
	public Acc_cost_centre_master save(@Valid @RequestBody Acc_cost_centre_master costcentre)
	{
		return acc_cost_centre_masterService.save(costcentre);
	}
	
	@GetMapping("/getAccCostCentres")
	public List<Acc_cost_centre_master> findcostcentreAll()
	{
		return acc_cost_centre_masterService.findAll();
	}
	
	@GetMapping("/retriveAccCostCentre/{id}")
	public ResponseEntity<Acc_cost_centre_master> getAcc_cost_centre_master(@PathVariable(value="id") Long id)
	{
		Acc_cost_centre_master op = acc_cost_centre_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateAccCostCentre/{id}")
	public ResponseEntity<Acc_cost_centre_master> updateAcc_cost_centre_master(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_cost_centre_master acc_cost_centre_master)
	{
		Acc_cost_centre_master op = acc_cost_centre_masterService.update(acc_cost_centre_master,id);
		return ResponseEntity.ok().body(op);
	}
	  
	/******************* Acc Cost Centre Master End *******************/
	
	/******************* Acc_gen_ledger_master Start *******************/
	@Autowired
	Acc_gen_ledger_masterService acc_gen_ledger_masterService;
	
	@PostMapping("/createAccGenLedger")
	public Acc_gen_ledger_master save(@Valid @RequestBody Acc_gen_ledger_master genledger)
	{
		return acc_gen_ledger_masterService.save(genledger);
	}
	
	@GetMapping("/getAccGenLedgers")
	public List<Acc_gen_ledger_master> findAll()
	{
		return acc_gen_ledger_masterService.findAll();
	}
	
	@GetMapping("/retriveAccGenLedger/{id}")
	public ResponseEntity<Acc_gen_ledger_master> getAcc_gen_ledger_master(@PathVariable(value="id") Long id)
	{
		Acc_gen_ledger_master op = acc_gen_ledger_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateAccGenLedger/{id}")
	public ResponseEntity<Acc_gen_ledger_master> updateAcc_gen_ledger_master(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_gen_ledger_master genledger)
	{
		Acc_gen_ledger_master op = acc_gen_ledger_masterService.update(genledger,id);
		return ResponseEntity.ok().body(op);
	}
	  
	/******************* Acc_gen_ledger_master End *******************/
	
	/*********** Account Group Master Start**************/
	
	@Autowired
	Acc_group_masterService accgroupservice;
	
	/* to save a Account Group*/
	@PostMapping("/createAccGroup")
	public Acc_group_master createAccGroup(@Valid @RequestBody Acc_group_master accgroup) 
	{
		return accgroupservice.save(accgroup);
	}
	
	/*get all Account Group*/
	@GetMapping("/getAccountGroups")
	public List<Acc_group_master> getAll()
	{
		return accgroupservice.findAll();                          
	}
	
	@GetMapping("/retriveAccountGroup/{id}")
	public ResponseEntity<Acc_group_master> getAcc_group_master(@PathVariable(value="id") Long id)
	{
		Acc_group_master op = accgroupservice.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateAccountGroup/{id}")
	public ResponseEntity<Acc_group_master> updateAcc_group_master(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_group_master group)
	{
		Acc_group_master op = accgroupservice.update(group,id);
		return ResponseEntity.ok().body(op);
	}
	
	/*
	@RequestMapping(value = "/accGroupName", method = RequestMethod.GET)
	public List<Acc_group_master> getaccGroupName() {
		return accgroupservice.findByName();
	}*/
	
	/*********** Account Group Master End**************/
	
	/******************* Acc_narration_master Start *******************/
	@Autowired
	Acc_narration_masterService acc_narration_masterService;
	
	@PostMapping("/createAccNarration")
	public Acc_narration_master save(@Valid @RequestBody Acc_narration_master narration)
	{
		return acc_narration_masterService.save(narration);
	}
	
	@GetMapping("/getAccNarrations")
	public List<Acc_narration_master> findnarrationAll() 
	{
		return acc_narration_masterService.findAll();
	}
	
	@GetMapping("/retriveAccNarration/{id}")
	public ResponseEntity<Acc_narration_master> getAcc_narration_master(@PathVariable(value="id") Long id)
	{
		Acc_narration_master op = acc_narration_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateAccNarration/{id}")
	public ResponseEntity<Acc_narration_master> updateAcc_narration_master(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_narration_master narration)
	{
		Acc_narration_master op = acc_narration_masterService.update(narration,id);
		return ResponseEntity.ok().body(op);
	}
	  
	/******************* Acc_narration_master End *******************/
	
	
	
	/******************* Acc_sub_group_master Start *******************/
	@Autowired
	Acc_sub_group_masterService acc_sub_group_masterService;
	
	@PostMapping("/createAccSubGroup")
	public Acc_sub_group_master save(@Valid @RequestBody Acc_sub_group_master subgroup)
	{
		return acc_sub_group_masterService.save(subgroup);
	}
	
	@GetMapping("/getAccAccSubGroups")
	public List<Acc_sub_group_master> findSubGroupAll() 
	{
		return acc_sub_group_masterService.findAll();
	}
	
	@GetMapping("/retriveAccSubGroup/{id}")
	public ResponseEntity<Acc_sub_group_master> getSubGroup(@PathVariable(value="id") Long id)
	{
		Acc_sub_group_master op = acc_sub_group_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateAccSubGroup/{id}")
	public ResponseEntity<Acc_sub_group_master> updateSubGroup(@PathVariable(value="id") Long id,@Valid @RequestBody Acc_sub_group_master subgroup)
	{
		Acc_sub_group_master op = acc_sub_group_masterService.update(subgroup,id);
		return ResponseEntity.ok().body(op);
	}
	  
	/******************* Acc_sub_group_master End *******************/
	
	
	
}
