package com.AnkitIndia.jwtauthentication.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AnkitIndia.jwtauthentication.model.Exitclausemaster;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_bank_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_party_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_terms_con;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_time_service;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_time_service;
import com.AnkitIndia.jwtauthentication.model.Nongoodstypemaster;
import com.AnkitIndia.jwtauthentication.model.Nonservice_desc_details;
import com.AnkitIndia.jwtauthentication.model.Nonservicesrn_desc_details;
import com.AnkitIndia.jwtauthentication.model.Servicemaster;
import com.AnkitIndia.jwtauthentication.model.Termasservice;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.security.services.ExitclausemasterService;
import com.AnkitIndia.jwtauthentication.security.services.JobOrderService;
import com.AnkitIndia.jwtauthentication.security.services.NongoodsserviceService;
import com.AnkitIndia.jwtauthentication.security.services.NongoodssrnService;
import com.AnkitIndia.jwtauthentication.security.services.NongoodstypemasterService;
import com.AnkitIndia.jwtauthentication.security.services.ServicemasterService;
import com.AnkitIndia.jwtauthentication.security.services.TermasserviceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class ServiceController {
	
	@Autowired
	ServicemasterService servicemasterService;
	
	@Autowired
	JobOrderService jobOrderService;
	
	@Autowired
	NongoodsserviceService nongoodsserviceService;
	
	@Autowired
	NongoodstypemasterService nongoodstypemasterService;
	
	@Autowired
	ExitclausemasterService exitclausemasterService;
	
	@Autowired
	TermasserviceService termasserviceService;
	
	@Autowired
	NongoodssrnService nongoodssrnService;
	
	/*************************************Service Master Starts***************************************/
	 
	 @GetMapping("/getSSequenceId/{fin_year}")
	public SequenceIdDTO getSSequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		 return servicemasterService.getSSequenceId(fin_year);
	}
	 
	 @PostMapping("/createServiceMaster")
	public Servicemaster createServiceMaster(@Valid @RequestBody Servicemaster servicemaster)
	{
		 return servicemasterService.save(servicemaster);				
	}
	 
	 @GetMapping("/getServiceMasterList/{fin_year}")
	public List<Map<String, Object>> getServiceMasterList(@PathVariable(value = "fin_year") String fin_year)
	{
		return servicemasterService.getServiceMasterList(fin_year);				
	}
	
	 @GetMapping("/retriveServiceMaster/{id}")
	public Servicemaster retriveServiceMaster(@PathVariable(value = "id") Long id) {
		 return servicemasterService.findOne(id);
	}

	@GetMapping("/serviceMasterRetriveList/{s_no}")
	public List<Map<String, Object>> serviceMasterRetriveList(@PathVariable(value = "s_no") String s_no) {
		return servicemasterService.serviceRetriveList(s_no);
	}
	
	@PutMapping("/updateServiceMaster/{id}")
	public ResponseEntity<Servicemaster> updateServiceMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Servicemaster servicemaster) {
		Servicemaster op = servicemasterService.update(servicemaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/DeleteService/{id}")
	public ResponseEntity<Servicemaster> DeleteService(@PathVariable(value="id") long id,
													@Valid @RequestBody Servicemaster servicemaster)
	{
		Servicemaster deleteSM=servicemasterService.delete(servicemaster,id);
		return ResponseEntity.ok().body(deleteSM);
	}
	
	@GetMapping("/checkServiceMasterUsage/{service_no}")
	public ResponseEntity<StatusDTO> checkServiceMasterUsage(@PathVariable(value = "service_no") String service_no)
	{
		StatusDTO val=servicemasterService.checkServiceMasterUsage(service_no);
		return ResponseEntity.ok().body(val);
	}
	
		
	 /*************************************Service Master Ends***************************************/
	
	
		/*************************************Job Order Starts***************************************/
	 
	 @GetMapping("/getOSequenceId/{fin_year}")
	public SequenceIdDTO getOSequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		 return jobOrderService.getOSequenceId(fin_year);
	}

	 /******************* Job Order Ends ******************/
	
	/******************* Non Goods Service Starts ******************/
	
	@GetMapping("/getNonGoodsServicelist/{finyear}")
	public List<Map<String, Object>> getNonGoodsServicelist(@PathVariable(value = "finyear") String finyear)
	{
		return nongoodsserviceService.getNonGoodsServicelist(finyear);
	}
	
	@GetMapping("/getServiceNo/{check}/{fyear}")
	public SequenceIdDTO getServiceNo(@PathVariable(value = "check") boolean check,@PathVariable(value = "fyear") String fyear)
	{
		return nongoodsserviceService.getServiceNo(check,fyear);
	}
	
	@GetMapping("/getCustomerSupplierList/{bunit}/{check}")
	public List<Map<String, Object>> getCustomerSupplierList(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "check") boolean check)
	{
		return nongoodsserviceService.getCustomerSupplierList(bunit,check);
	}
	
	@GetMapping("/getServiceList/{servicetype}")
	public List<Map<String, Object>> getServiceList(@PathVariable(value = "servicetype") String servicetype)
	{
		return nongoodsserviceService.getServiceList(servicetype);
	}
	
	@GetMapping("/getServiceMasterDetails/{service}")
	public List<Map<String, Object>> getServiceMasterDetails(@PathVariable(value = "service") String service)
	{
		return nongoodsserviceService.getServiceMasterDetails(service);
	}
	
	@GetMapping("/getDescCode/{service}")
	public Map<String, Object> getDescCode(@PathVariable(value = "service") String service)
	{
		return nongoodsserviceService.getDescCode(service);
	}
	
	@GetMapping("/getTermAsService")
	public List<Map<String,Object>> getTermAsService()
	{
		return termasserviceService.getTermAsService();
	}
	
	@PostMapping("/createnongoodservice")
	public Nongoodsservice createnongoodservice(@Valid @RequestBody Nongoodsservice ngs)
	{
		return nongoodsserviceService.save(ngs);
	}
	
	@GetMapping("/retriveNongoodsService/{id}")
	public ResponseEntity<Nongoodsservice> retriveNongoodsService(@PathVariable(value="id") Long id )
	{
		Nongoodsservice pqc=nongoodsserviceService.findOne(id);
		if(pqc == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(pqc);
		}
	}
	
	@GetMapping("/retriveNongoodsServiceItem/{nongoodsid}")
	public List<Nongoodsservice_item_details> retriveNongoodsServiceItem(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceItem(nongoodsid);
	}
	
	@GetMapping("/getItemDetailsSerList/{nongoodsid}/{serviceno}")
	public List<Nonservice_desc_details> getItemDetailsSerList(@PathVariable(value="nongoodsid") String nongoodsid,@PathVariable(value="serviceno") String serviceno)
	{
		return nongoodsserviceService.getItemDetailsSerList(nongoodsid,serviceno);
	}
	
	@GetMapping("/retriveNongoodsServiceTermsCon/{nongoodsid}")
	public Nongoodsservice_terms_con retriveNongoodsServiceTermsCon(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceTermsCon(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceParty/{nongoodsid}")
	public List<Nongoodsservice_party_dtls> retriveNongoodsServiceParty(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceParty(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceBankDtls/{nongoodsid}")
	public Nongoodsservice_bank_dtls retriveNongoodsServiceBankDtls(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceBankDtls(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceSummary/{nongoodsid}")
	public Nongoodsservice_summary retriveNongoodsServiceSummary(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceSummary(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceSummaryDyn/{nongoodsid}")
	public List<Nongoodsservice_summary_dyn> retriveNongoodsServiceSummaryDyn(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceSummaryDyn(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceTimeService/{nongoodsid}")
	public List<Nongoodsservice_time_service> retriveNongoodsServiceTimeService(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceTimeService(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceDocs/{nongoodsid}")
	public List<Nongoodsservice_docs> retriveNongoodsServiceDocs(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceDocs(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceExitClause/{nongoodsid}")
	public Nongoodsservice_exit_clause retriveNongoodsServiceExitClause(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceExitClause(nongoodsid);
	}
	
	@GetMapping("/retriveNongoodsServiceExitClauseDyn/{nongoodsid}")
	public List<Nongoodsservice_exit_clause_dyn> retriveNongoodsServiceExitClauseDyn(@PathVariable(value="nongoodsid") String nongoodsid)
	{
		return nongoodsserviceService.retriveNongoodsServiceExitClauseDyn(nongoodsid);
	}
	
	
	
	@PutMapping("/updateNongoodservice/{id}")
	public ResponseEntity<Nongoodsservice> updatepurGRS(@Valid @RequestBody Nongoodsservice ngs,@PathVariable(value="id") Long id)
	{
		Nongoodsservice updateNGS=nongoodsserviceService.update(ngs, id);
		return ResponseEntity.ok().body(updateNGS);
	}
	
	@PutMapping("/deleteNonGoodsService/{id}")
	public ResponseEntity<Nongoodsservice> deleteNonGoodsService(@PathVariable(value="id") long id,@Valid @RequestBody Nongoodsservice ngs)
	{
		Nongoodsservice op=nongoodsserviceService.delete(ngs,id);
		return ResponseEntity.ok().body(op);
	}
	
	/******************* Non Goods Service Ends  **********************/
	
	/******************* Non Goods Type Master Starts  **********************/
	
	@GetMapping("/getServiceTypeList")
		public List<Map<String, Object>> getServiceTypeList()
		{
			return nongoodstypemasterService.getServiceTypeList();
		}
	 
	@PostMapping("/createNonGoodsTypeMaster")
	public Nongoodstypemaster createNonGoodsTypeMaster(@Valid @RequestBody Nongoodstypemaster nongoodstypemaster)
	{
		return nongoodstypemasterService.save(nongoodstypemaster);				
	}
	
	@GetMapping("/getNonGoodsTypeList/{fin_year}")
	public List<Map<String, Object>> getNonGoodsTypeList(@PathVariable(value = "fin_year") String fin_year)
	{
		return nongoodstypemasterService.getNonGoodsTypeMasterList(fin_year);				
	}
	
	@GetMapping("/retriveNonGoodsTypeMaster/{id}")
	public Nongoodstypemaster retriveNonGoodsTypeMaster(@PathVariable(value = "id") Long id)
	{
		return nongoodstypemasterService.findOne(id);				
	}
	
	@PutMapping("/updateNonGoodsTypeMaster/{id}")
	public ResponseEntity<Nongoodstypemaster> updateNonGoodsTypeMaster(@Valid @RequestBody Nongoodstypemaster nongoodstypemaster,
																	   @PathVariable(value="id") Long id)
	{
		Nongoodstypemaster updateNGTM=nongoodstypemasterService.update(nongoodstypemaster, id);
		return ResponseEntity.ok().body(updateNGTM);
	}
	
	@PutMapping("/deleteNonGoodsTypeMaster/{id}")
	public ResponseEntity<Nongoodstypemaster> deleteNonGoodsTypeMaster(@PathVariable(value="id") long id,
													@Valid @RequestBody Nongoodstypemaster nongoodstypemaster)
	{
		Nongoodstypemaster deleteNGTM=nongoodstypemasterService.delete(nongoodstypemaster,id);
		return ResponseEntity.ok().body(deleteNGTM);
	}

	/******************* Non Goods Type Master Ends  **********************/

	/******************* Exit Clause Master Starts  **********************/
	
	@GetMapping("/getESequenceId/{fin_year}")
	public SequenceIdDTO getESequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		return exitclausemasterService.getESequenceId(fin_year);
	}
	
	@PostMapping("/createExitClauseMaster")
	public Exitclausemaster createExitClauseMaster(@Valid @RequestBody Exitclausemaster exitclausemaster)
	{
		return exitclausemasterService.save(exitclausemaster);				
	}
	
	@GetMapping("/getExitClauseMasterList/{fin_year}")
	public List<Map<String, Object>> getExitClauseMasterList(@PathVariable(value = "fin_year") String fin_year)
	{
		return exitclausemasterService.getExitClauseMasterList(fin_year);				
	}
	
	@GetMapping("/retriveExitClauseMaster/{id}")
	public Exitclausemaster retriveExitClauseMaster(@PathVariable(value = "id") Long id)
	{
		return exitclausemasterService.findOne(id);				
	}
	
	@PutMapping("/updateExitClauseMaster/{id}")
	public ResponseEntity<Exitclausemaster> updateExitClauseMaster(@Valid @RequestBody Exitclausemaster exitclausemaster,
																	   @PathVariable(value="id") Long id)
	{
		Exitclausemaster updateECM=exitclausemasterService.update(exitclausemaster, id);
		return ResponseEntity.ok().body(updateECM);
	}
	
	@PutMapping("/deleteExitClauseMaster/{id}")
	public ResponseEntity<Exitclausemaster> deleteExitClauseMaster(@PathVariable(value="id") long id,
													@Valid @RequestBody Exitclausemaster exitclausemaster)
	{
		Exitclausemaster deleteECM=exitclausemasterService.delete(exitclausemaster,id);
		return ResponseEntity.ok().body(deleteECM);
	}

	/******************* Exit Clause Master Ends  **********************/
	
	/******************* Term as Service Starts  **********************/
	
	@GetMapping("/getTSequenceId/{fin_year}")
	public SequenceIdDTO getTSequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		return termasserviceService.getTSequenceId(fin_year);
	}
	
	@PostMapping("/createTermasService")
	public Termasservice createTermasService(@Valid @RequestBody Termasservice termasservice)
	{
		return termasserviceService.save(termasservice);				
	}
	
	@GetMapping("/getTermasServiceList/{fin_year}")
	public List<Map<String, Object>> getTermasServiceList(@PathVariable(value = "fin_year") String fin_year)
	{
		return termasserviceService.getTermasServiceList(fin_year);				
	}
	
	@GetMapping("/retriveTermasService/{id}")
	public Termasservice retriveTermasService(@PathVariable(value = "id") Long id)
	{
		return termasserviceService.findOne(id);				
	}
	
	@PutMapping("/updateTermasService/{id}")
	public ResponseEntity<Termasservice> updateTermasService(@Valid @RequestBody Termasservice termasservice,
																	   @PathVariable(value="id") Long id)
	{
		Termasservice updateTaS=termasserviceService.update(termasservice, id);
		return ResponseEntity.ok().body(updateTaS);
	}
	
	@PutMapping("/deleteTermasService/{id}")
	public ResponseEntity<Termasservice> deleteTermasService(@PathVariable(value="id") long id,
													@Valid @RequestBody Termasservice termasservice)
	{
		Termasservice deleteTaS=termasserviceService.delete(termasservice,id);
		return ResponseEntity.ok().body(deleteTaS);
	}

	/******************* Term as Service Ends  **********************/
	/******************* Non Goods Service SRN Starts ******************/
	
	@GetMapping("/getSRNNo/{fyear}")
	public SequenceIdDTO getSRNNo(@PathVariable(value = "fyear") String fyear)
	{
		return nongoodssrnService.getSRNNo(fyear);
	}
	
	@GetMapping("/getSRNlist/{finyear}")
	public List<Map<String, Object>> getSRNlist(@PathVariable(value = "finyear") String finyear)
	{
		return nongoodssrnService.getSRNlist(finyear);
	}
	
	@GetMapping("/getNonServiceOrderAllList/{ordertype}/{bunit}/{party}/{srndate}")
	public List<Map<String, Object>> getNonServiceOrderAllList(@PathVariable(value = "ordertype") String ordertype,
			@PathVariable(value = "bunit") String bunit,
			@PathVariable(value = "party") String party,
			@PathVariable(value = "srndate") String srndate)
	{
		return nongoodssrnService.getNonServiceOrderAllList(ordertype,bunit,party,srndate);
	}
	
	@GetMapping("/getServiceItemList/{serviceid}")
	public List<Nongoodsservice_item_details> getServiceItemList(@PathVariable(value = "serviceid") String serviceid)
	{
		return nongoodssrnService.getServiceItemList(serviceid);
	}
	
	@GetMapping("/getDescAccordingBillPeriodList/{nongoodsserviceid}/{serviceno}")
	public List<Map<String, Object>> getDescAccordingBillPeriodList(@PathVariable(value = "nongoodsserviceid") String nongoodsserviceid,@PathVariable(value = "serviceno") String serviceno)
	{
		return nongoodssrnService.getDescAccordingBillPeriodList(nongoodsserviceid,serviceno);
	}
	
	@PostMapping("/createnongoodsrn")
	public Nongoodssrn nongoodssrnService(@Valid @RequestBody Nongoodssrn ngs)
	{
		return nongoodssrnService.save(ngs);
	}
	
	@PutMapping("/updateNongoodsrn/{id}")
	public ResponseEntity<Nongoodssrn> updateNongoodsrn(@Valid @RequestBody Nongoodssrn srn,@PathVariable(value="id") Long id)
	{
		Nongoodssrn updatesrn=nongoodssrnService.update(srn, id);
		return ResponseEntity.ok().body(updatesrn);
	}
	
	
	@GetMapping("/retriveNongoodsSrn/{id}")
	public ResponseEntity<Nongoodssrn> retriveNongoodsSrn(@PathVariable(value="id") Long id )
	{
		Nongoodssrn pqc=nongoodssrnService.findOne(id);
		if(pqc == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(pqc);
		}
	}
	
	@GetMapping("/retriveNongoodsSrnItem/{srnid}")
	public List<Nongoodssrn_item_details> retriveNongoodsSrnItem(@PathVariable(value="srnid") String srnid)
	{
		return nongoodssrnService.retriveNongoodsSrnItem(srnid);
	}
	
	@GetMapping("/getSrnItemDetailsSerList/{srnid}/{services}")
	public List<Nonservicesrn_desc_details> getSrnItemDetailsSerList(@PathVariable(value="srnid") String srnid,@PathVariable(value="services") String services)
	{
		return nongoodssrnService.getSrnItemDetailsSerList(srnid,services);
	}
	
	@GetMapping("/retriveNongoodsSrnTime/{srnid}")
	public List<Nongoodssrn_time_service> retriveNongoodsSrnTime(@PathVariable(value="srnid") String srnid)
	{
		return nongoodssrnService.retriveNongoodsSrnTime(srnid);
	}
	
	@GetMapping("/retriveNongoodsSrnDocs/{srnid}")
	public List<Nongoodssrn_docs> retriveNongoodsSrnDocs(@PathVariable(value="srnid") String srnid)
	{
		return nongoodssrnService.retriveNongoodsSrnDocs(srnid);
	}
	
	
	@PutMapping("/deleteNongoodsrn/{id}")
	public ResponseEntity<Nongoodssrn> deleteNongoodsrn(@PathVariable(value="id") long id,@Valid @RequestBody Nongoodssrn nongoodssrn)
	{
		Nongoodssrn deletesrn=nongoodssrnService.delete(nongoodssrn,id);
		return ResponseEntity.ok().body(deletesrn);
	}
	/******************* Non Goods Service SRN Starts ******************/
}
