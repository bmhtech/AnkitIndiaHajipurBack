package com.AnkitIndia.jwtauthentication.controller;


import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.persistence.Tuple;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.AnkitIndia.jwtauthentication.model.Binreport;
import com.AnkitIndia.jwtauthentication.model.Binreportdetails;
import com.AnkitIndia.jwtauthentication.model.Dailypowerreport;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls_One;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;
import com.AnkitIndia.jwtauthentication.model.Dailyweigher;
import com.AnkitIndia.jwtauthentication.model.Dieselusedimport;
import com.AnkitIndia.jwtauthentication.model.Gateinoutregister;
import com.AnkitIndia.jwtauthentication.model.Gatepassregister;
import com.AnkitIndia.jwtauthentication.model.Granulation;
import com.AnkitIndia.jwtauthentication.model.Granulation_Dtls;
import com.AnkitIndia.jwtauthentication.model.Grnregisterreport;
import com.AnkitIndia.jwtauthentication.model.Grnregisterreport_Dtls;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport;
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport_Dtls;
import com.AnkitIndia.jwtauthentication.model.Misclabreportfg;
import com.AnkitIndia.jwtauthentication.model.Misclabreportfg_Dtls;
import com.AnkitIndia.jwtauthentication.model.Otherparameterreport;
import com.AnkitIndia.jwtauthentication.model.Otherparameterreport_Dtls;
import com.AnkitIndia.jwtauthentication.model.Powercutreport;
import com.AnkitIndia.jwtauthentication.model.Sales_transport;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;
import com.AnkitIndia.jwtauthentication.model.Solar_power_generation;
import com.AnkitIndia.jwtauthentication.model.Solar_power_generation_with_powercut;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.model.Weigherreding;
import com.AnkitIndia.jwtauthentication.model.Wheat_issue_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatacceptance;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreport;
import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreportdetails;
import com.AnkitIndia.jwtauthentication.model.Wheatstock_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.repository.DailystockfinishgoodRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.BinreportdetailspopupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.ChallanpertransportreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.DailygatewheatinwardreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.GrnregisterreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Powercutreport_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.WheatstackcardreportDTO;
import com.AnkitIndia.jwtauthentication.security.services.BinreportService;
import com.AnkitIndia.jwtauthentication.security.services.Cust_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.DailypowerreportService;
import com.AnkitIndia.jwtauthentication.security.services.DailyproductionService;
import com.AnkitIndia.jwtauthentication.security.services.DailystockfinishgoodService;
import com.AnkitIndia.jwtauthentication.security.services.DailyweigherService;
import com.AnkitIndia.jwtauthentication.security.services.Delivery_challanService;
import com.AnkitIndia.jwtauthentication.security.services.DieselusedimportService;
import com.AnkitIndia.jwtauthentication.security.services.GateinoutregisterService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassregisterService;
import com.AnkitIndia.jwtauthentication.security.services.GranulationService;
import com.AnkitIndia.jwtauthentication.security.services.GrnregisterreportService;
import com.AnkitIndia.jwtauthentication.security.services.MillbreakdownreportService;
import com.AnkitIndia.jwtauthentication.security.services.MisclabreportfgService;
import com.AnkitIndia.jwtauthentication.security.services.OtherparameterreportService;
import com.AnkitIndia.jwtauthentication.security.services.PowercutreportService;
import com.AnkitIndia.jwtauthentication.security.services.Production_planningService;
import com.AnkitIndia.jwtauthentication.security.services.Production_transaction_splService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_BillService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_OrderService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_good_receiptService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_InvoiceService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_OrderService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_credit_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_transportService;
import com.AnkitIndia.jwtauthentication.security.services.Solar_power_generationService;
import com.AnkitIndia.jwtauthentication.security.services.Solar_power_generation_with_powercutService;
import com.AnkitIndia.jwtauthentication.security.services.Supp_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.WeigherredingService;
import com.AnkitIndia.jwtauthentication.security.services.WheatacceptanceService;
import com.AnkitIndia.jwtauthentication.security.services.WheatreceivingService;
import com.AnkitIndia.jwtauthentication.security.services.WheatstackcardreportService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_loading_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_wgmntService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
public class ReportController {
	
	@Autowired
	PowercutreportService powercutreportService;
	
	@Autowired
	DailystockfinishgoodService dailystockfinishgoodService;
	
	@Autowired
	DailypowerreportService dailypowerreportService;
	
	@Autowired
	DieselusedimportService dieselusedimportService;
	
	@Autowired
	GrnregisterreportService grnregisterreportService;
	
	@Autowired
	WheatreceivingService wheatreceivingService;
	
	@Autowired
	WheatstackcardreportService wheatstackcardreportService;
	
	@Autowired
	GateinoutregisterService gateinoutregisterService;
	
	@Autowired
	GatepassregisterService gatepassregisterService;
	
	@Autowired
	DailyproductionService dailyproductionService;
	
	@Autowired
	MisclabreportfgService misclabreportfgService;
	
	@Autowired
	GranulationService granulationService;
	
	@Autowired
	OtherparameterreportService otherparameterreportService;
	
	@Autowired
	MillbreakdownreportService millbreakdownreportService;
	
	@Autowired
	WeigherredingService weigherredingService;
	
	@Autowired
	Delivery_challanService delivery_challanService;
	
	@Autowired
	WheatacceptanceService wheatacceptanceService;
	
	@Autowired
	Wm_unload_adviceService wm_unload_adviceService;
	
	@Autowired
	Sales_OrderService sales_orderService;
	
	@Autowired
	Sales_InvoiceService sales_invoiceService;
	
	@Autowired
	Pur_OrderService pur_OrderService;
	
	@Autowired
	Pur_BillService pur_BillService;
	
	@Autowired
	Production_planningService production_planningService;
	
	@Autowired
	Sales_transportService sales_transportService;
	
	@Autowired
	Pur_good_receiptService pur_good_receiptService;
	
	@Autowired
	Sales_OrderService sales_OrderService;
	
	@Autowired
	Wm_loading_adviceService wm_loading_adviceService;
	
	@Autowired
	Sales_credit_noteService sales_credit_noteService;
	
	@Autowired
	Supp_bussiness_partnerService supp_bussiness_partnerService;
	
	@Autowired
	Cust_bussiness_partnerService cust_bussiness_partnerService;
	
	/*************************************************************power cut Starts here***************************************/
	
	@GetMapping("/getpowercutlist/{page}/{size}")
	public Page<Powercutreport_Pagination_DTO> getpowercutlist(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return powercutreportService.getpowercutlist(page,size);
	}
	
	  
       /* @GetMapping(value = "/searchpowercut")
		public List<Powercutreport_Pagination_DTO> searchpowercut(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
		 {
			return powercutreportService.searchpowercut(fromdate,todate,finyear);
		 }*/
		
	   @GetMapping(value = "/searchpowercut")
		public List<Powercutreport> searchpowercut(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
		 {
			return powercutreportService.searchpowercut(fromdate,todate,finyear);
		 }
		
		@PostMapping("/createpowercut")
		public Powercutreport createpowercut(@Valid @RequestBody Powercutreport powercutreport) {
			return powercutreportService.save(powercutreport);
		}
		
		@GetMapping("/getpowercutDatalist/{currDate}/{finyear}")
		public List<Powercutreport> getLoadingAdviceDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
		{
			return powercutreportService.getpowercutDatalist(currDate,finyear);
		}
		
		@GetMapping("/retrivePowerCut/{id}")
		public ResponseEntity<Powercutreport> retrivePowerCut(@PathVariable(value="id") Long id)
		{
			Powercutreport op=powercutreportService.findOne(id);
			if(id==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(op);
			}
		}
		
		@PutMapping("/updatepowercut/{id}")
		public ResponseEntity<Powercutreport> updatepowercut(@PathVariable(value = "id") Long id,@Valid @RequestBody Powercutreport powercutreport) {
			Powercutreport op = powercutreportService.update(powercutreport, id);
			return ResponseEntity.ok().body(op);
		}
		
		@PutMapping("/deletePowerCutReport/{id}")
		public ResponseEntity<Powercutreport> deletePowerCutReport(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Powercutreport powercutreport) {
			Powercutreport op = powercutreportService.deletePowerCutReport(powercutreport, id);
			return ResponseEntity.ok().body(op);
		}
		
		 
		/*************************************************************power cut end here***************************************/
		
		
		/*************************************************************daily laodingitemwise starts here***************************************/
		@Autowired
		DailystockfinishgoodRepository dailystockfinishgoodRepository;
		
		  @GetMapping("/getdailyloadingitemwise/{fromdate}/{todate}/{finyear}")
	        public List<Map<String, Object>> getdailyloadingitemwise(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "finyear") String finyear) 
			{
	        	
	        	List<Tuple> loadinglist=new ArrayList<Tuple>();
	        	
	        	loadinglist.addAll(dailystockfinishgoodRepository.getdailyloading(fromdate,todate));
	        	
	        	List<Map<String, Object>> finallist=new ArrayList<Map<String, Object>>();
	        	loadinglist.forEach(tuple -> {
	        		Map<String, Object> values = new LinkedHashMap<>();
	                tuple.getElements().forEach(
	                    element -> {
	                    	
	                        values.put(element.getAlias(), tuple.get(element));
	                       
	                    }
	                   
	                );
	                finallist.add(values);
	            });
	        
	        	return  finallist;
			}
		
		
		
		/*************************************************************daily laodingitemwise  end here***************************************/
		
		
		
		/*************************************************************daily finished good starts here***************************************/ 
		
		@GetMapping("/getdailystockfinishedgoodslist/{currDate}/{finyear}")
		
		public List<Dailystockfinishgood> getdailystockfinishedgoodslist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
		{
			  return dailystockfinishgoodService.getdailystockfinishedgoodslist(currDate,finyear);
		}
		
		
		@PostMapping("/createdailystockfinishedgoods")
		public Dailystockfinishgood createdailystockfinishedgoods(@Valid @RequestBody Dailystockfinishgood dailystockfinishgood) {
			return dailystockfinishgoodService.save(dailystockfinishgood);
		}
		
		
		@PutMapping("/updatedailystockfinishedgoods/{id}")
		public ResponseEntity<Dailystockfinishgood> updatedailystockfinishedgoods(@PathVariable(value = "id") Long id,@Valid @RequestBody Dailystockfinishgood dailystockfinishgood) {
			Dailystockfinishgood op = dailystockfinishgoodService.update(dailystockfinishgood, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		
		@PutMapping("/deletedailystockfinishedgoods/{id}")
		public ResponseEntity<Dailystockfinishgood> deletedailystockfinishedgoods(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Dailystockfinishgood dailystockfinishgood) {
			Dailystockfinishgood op = dailystockfinishgoodService.delete(dailystockfinishgood, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		 @GetMapping(value = "/searchdailystockfinishedgoods")
		public List<Dailystockfinishgood> searchdailystockfinishedgoods(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
		 {
			return dailystockfinishgoodService.searchdailystockfinishedgoods(fromdate,todate,finyear);
		 }
		
		 
		 
		 @GetMapping("/retrivedailystockfinishedgoods/{id}")
			public ResponseEntity<Dailystockfinishgood> retrivedailystockfinishedgoods(@PathVariable(value="id") Long id)
			{
			 Dailystockfinishgood op=dailystockfinishgoodService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		 
		    @GetMapping("/getdailystockfinishedgoodsdtlslist/{dailystockid}")
			public List<Dailystockfinishgood_Dtls> getdailystockfinishedgoodsdtlslist(@PathVariable(value = "dailystockid") String dailystockid)
			{
				return dailystockfinishgoodService.getdailystockfinishedgoodsdtlslist(dailystockid);
			}
			
		
			
		    @GetMapping("/getdailystockfinishedgoodopeningstock/{itemId}/{date}/{finyear}")
			public Dailystockfinishgood_Dtls getdailystockfinishedgoodopeningstock(@PathVariable(value = "itemId") String itemId,@PathVariable(value = "date") String date,@PathVariable(value = "finyear") String finyear)
			{
				return dailystockfinishgoodService.getdailystockfinishedgoodopeningstock(itemId,date,finyear);
			}
		    
		    @GetMapping("/getcheckdate/{date}/{finyear}")
			public Status_table getcheckdate(@PathVariable(value = "date") String date,@PathVariable(value = "finyear") String finyear)
			{
		    	System.out.println(date + " /" + finyear);
				return dailystockfinishgoodService.getcheckdate(date,finyear);
			}
		    
		    @GetMapping("/getItemThruSalesThruBUanddDate/{BuUnit}/{company_id}/{date}/{finyear}")
			public List<Dailystockfinishgood_Dtls> getItemThruSalesThruBUanddDate(@PathVariable(value = "BuUnit") String bunit,@PathVariable(value = "company_id") String compid,
					@PathVariable(value = "date") String date,@PathVariable(value = "finyear") String finyear)
			{
		    	//System.out.println(date + " /" + finyear);
				return dailystockfinishgoodService.getItemThruSalesThruBUanddDate(bunit,compid,date,finyear);
			}
			/*************************************************************daily finished good ends here***************************************/
			
			/*************************************************************daily power report Starts here***************************************/
			
			@GetMapping("/getdailypowerreportlist/{currDate}/{finyear}")
			
			public List<Dailypowerreport> getdailypowerreportlist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
			{
				  return dailypowerreportService.getdailypowerreportlist(currDate,finyear);
			}
			
			
			@PostMapping("/createdailypowerreport")
			public Dailypowerreport createdailypowerreport(@Valid @RequestBody Dailypowerreport dailypowerreport) {
				return dailypowerreportService.save(dailypowerreport);
			}
			
			
			@PutMapping("/updatedailypowerreport/{id}")
			public ResponseEntity<Dailypowerreport> updatedailypowerreport(@PathVariable(value = "id") Long id,@Valid @RequestBody Dailypowerreport dailypowerreport) {
				Dailypowerreport op = dailypowerreportService.update(dailypowerreport, id);
				return ResponseEntity.ok().body(op);
			}
			
			
			
			@PutMapping("/deletedailypowerreport/{id}")
			public ResponseEntity<Dailypowerreport> deletedailypowerreport(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Dailypowerreport dailypowerreport) {
				Dailypowerreport op = dailypowerreportService.delete(dailypowerreport, id);
				return ResponseEntity.ok().body(op);
			}
			
			
			 @GetMapping(value = "/searchdailypowerreport")
			public List<Dailypowerreport> searchdailypowerreport(@RequestParam(defaultValue = "") String fromdate,
					@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
			 {
				return dailypowerreportService.searchdailypowerreport(fromdate,todate,finyear);
			 }
			
			 
			 
			 @GetMapping("/retrivedailypowerreport/{id}")
				public ResponseEntity<Dailypowerreport> retrivedailypowerreport(@PathVariable(value="id") Long id)
				{
				 Dailypowerreport op=dailypowerreportService.findOne(id);
					if(id==null)
					{
						return ResponseEntity.notFound().build();
					}
					else
					{
						return ResponseEntity.ok().body(op);
					}
				}
			 
			 
				/*************************************************************daily power report end here***************************************/
				/*************************************************************Diesel Used Import report Starts here***************************************/
				
				@GetMapping("/getDieselusedimportlist/{currDate}/{finyear}")
				
				public List<Dieselusedimport> getDieselusedimportlist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
				{
					  return dieselusedimportService.getDieselusedimportlist(currDate,finyear);
				}
				
				
				@PostMapping("/createdieselusedimport")
				public Dieselusedimport createdieselusedimport(@Valid @RequestBody Dieselusedimport dieselusedimport) {
					return dieselusedimportService.save(dieselusedimport);
				}
				
				
				@PutMapping("/updatedieselusedimport/{id}")
				public ResponseEntity<Dieselusedimport> updatedailypowerreport(@PathVariable(value = "id") Long id,@Valid @RequestBody Dieselusedimport dieselusedimport) {
					Dieselusedimport op = dieselusedimportService.update(dieselusedimport, id);
					return ResponseEntity.ok().body(op);
				}
				
				
				
				@PutMapping("/deletedieselusedimport/{id}")
				public ResponseEntity<Dieselusedimport> deletedieselusedimport(@PathVariable(value = "id") Long id,
						@Valid @RequestBody Dieselusedimport dieselusedimport) {
					Dieselusedimport op = dieselusedimportService.delete(dieselusedimport, id);
					return ResponseEntity.ok().body(op);
				}
				
				
				 @GetMapping(value = "/searchDieselusedimportReport")
				public List<Dieselusedimport> searchDieselusedimportReport(@RequestParam(defaultValue = "") String fromdate,
						@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
				 {
					return dieselusedimportService.searchDieselusedimportReport(fromdate,todate,finyear);
				 }
				
				 
				 
				 @GetMapping("/retrivedieselusedimport/{id}")
					public ResponseEntity<Dieselusedimport> retrivedieselusedimport(@PathVariable(value="id") Long id)
					{
					 Dieselusedimport op=dieselusedimportService.findOne(id);
						if(id==null)
						{
							return ResponseEntity.notFound().build();
						}
						else
						{
							return ResponseEntity.ok().body(op);
						}
					}
				 
				 

  /*************************************************************Diesel Used Import report end here***************************************/
				 
 /*************************************************************GRN Register Report Start***************************************/
	 @GetMapping("/getGRNRegisterReportlist/{currDate}/{finyear}")
		
	public List<Grnregisterreport> getGRNRegisterReportlist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
	{
		  return grnregisterreportService.getGRNRegisterReportlist(currDate,finyear);
	}
	
	
	@PostMapping("/creategrnregisterreport")
	public Grnregisterreport creategrnregisterreport(@Valid @RequestBody Grnregisterreport grnregisterreport) {
		return grnregisterreportService.save(grnregisterreport);
	}
	
	
	@PutMapping("/updategrnregisterreport/{id}")
	public ResponseEntity<Grnregisterreport> updategrnregisterreport(@PathVariable(value = "id") Long id,@Valid @RequestBody Grnregisterreport grnregisterreport) {
		Grnregisterreport op = grnregisterreportService.update(grnregisterreport, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	
	@PutMapping("/deletegrnReport/{id}")
	public ResponseEntity<Grnregisterreport> deletegrnReport(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Grnregisterreport grnregisterreport) {
		Grnregisterreport op = grnregisterreportService.delete(grnregisterreport, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@GetMapping(value = "/searchGRNRegisterReport")
	public List<Grnregisterreport> searchGRNRegisterReport(@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
	 {
		return grnregisterreportService.searchGRNRegisterReport(fromdate,todate,finyear);
	 }
		
	@GetMapping(value = "/searchGRNRegisterReportPriview")
	public List<GrnregisterreportDTO> searchGRNRegisterReportPriview(@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
	 {
		//System.out.println("date:"+fromdate+"//"+todate+"//"+finyear);
		return grnregisterreportService.searchGRNRegisterReportPriview(fromdate,todate,finyear);
	 }
	
	 @GetMapping("/retriveGRNRegister/{id}")
		public ResponseEntity<Grnregisterreport> retriveGRNRegister(@PathVariable(value="id") Long id)
		{
		 Grnregisterreport op=grnregisterreportService.findOne(id);
			if(id==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(op);
			}
		}
			
	 @GetMapping("/retriveGrnRegisterDetails/{grnregisterid}")
		public List<Grnregisterreport_Dtls> retriveGrnRegisterDetails(@PathVariable(value = "grnregisterid") String grnregisterid)
		{
			return grnregisterreportService.retriveGrnRegisterDetails(grnregisterid);
		}
		
	@GetMapping("/grnRegisterAllDataList/{currDate}/{finyear}")
	public List<GrnregisterreportDTO> grnRegisterAllDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return grnregisterreportService.grnRegisterAllDataList(currDate,finyear);
	}
 /*************************************************************GRN Register Report Ends***************************************/
 
 /*************************************************************Daily gate wheat inward report starts here***************************************/
	
	@Autowired
	Wm_unload_wgmntService wm_unload_wgmntService;
		
	 @GetMapping("/getdailygatewheatinwardreportold2/{date}/{todate}")
		public List<DailygatewheatinwardreportDTO> getdailygatewheatinwardreportold2(@PathVariable(value="date") String date,@PathVariable(value="todate") String todate) {
		 
		    System.out.println("From Date: " + date+" Todate: "+todate);
			return wm_unload_adviceService.getdailygatewheatinwardreportold2(date,todate);
		}
	
	 @GetMapping("/getdailygatewheatinwardreportnew/{date}/{todate}")
		public List<Map<String,Object>> getdailygatewheatinwardreportnew(@PathVariable(value="date") String date,@PathVariable(value="todate") String todate) {
		 
		    //System.out.println("From Date: " + date+" Todate: "+todate);
			return wm_unload_adviceService.getdailygatewheatinwardreportnew(date,todate);
		}
	 
	 @GetMapping("/getdailygatewheatinwardreportnew2/{date}/{todate}/{order}")
		public List<Map<String,Object>> getdailygatewheatinwardreportnew2(@PathVariable(value="date") String date,@PathVariable(value="todate") String todate,@PathVariable(value="order") String order) {
		 
		    //System.out.println("From Date: " + date+" Todate: "+todate);
			return wm_unload_adviceService.getdailygatewheatinwardreportnew2(date,todate,order);
		}
	 
	 @GetMapping("/getdailygatewheatinwardreportnew2WithParty/{date}/{todate}/{order}/{party}")
		public List<Map<String,Object>> getdailygatewheatinwardreportnew2WithParty(@PathVariable(value="date") String date,@PathVariable(value="todate") String todate,
				@PathVariable(value="order") String order,@PathVariable(value="party") String party) {
		 
		    //System.out.println("From Date: " + date+" Todate: "+todate+"//"+party);
			return wm_unload_adviceService.getdailygatewheatinwardreportnew2WithParty(date,todate,order,party);
		}
	 
	 @GetMapping("/getOtherKataReport/{date}/{todate}")
		public List<Map<String,Object>> getOtherKataReport(@PathVariable(value="date") String date,@PathVariable(value="todate") String todate) {
		 
		   // System.out.println("From Date: " + date+" Todate: "+todate);
			return wm_unload_wgmntService.getOtherKataReport(date,todate);
		}
	 
	
	 @GetMapping("/getOtherKataWithPartyReport/{date}/{todate}/{party}")
		public List<Map<String,Object>> getOtherKataWithPartyReport(@PathVariable(value="date") String date,@PathVariable(value="todate") String todate,@PathVariable(value="party") String party) {
		 
		   // System.out.println("From Date: " + date+" Todate: "+todate);
			return wm_unload_wgmntService.getOtherKataWithPartyReport(date,todate,party);
		}
	 
	 @GetMapping("/getdailyjobworkwgtreport/{loadfromdate}/{load2date}/{party}")
	public List<Map<String,Object>> getdailyjobworkwgtreport(@PathVariable(value="loadfromdate") String loadfromdate,
			@PathVariable(value="load2date") String load2date,@PathVariable(value="party") String party) 
	 {
		return wm_unload_wgmntService.getdailyjobworkwgtreport(loadfromdate,load2date,party);
	 }
	 
	 
	 @GetMapping(value = "/getdailygatewheatOUTwardreport")
		public List<Wm_unload_wgmnt> getdailygatewheatOUTwardreport(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate)
		 {
			return wm_unload_wgmntService.getdailygatewheatOUTwardreport(fromdate,todate);
		 }
				 
	@GetMapping(value = "/getChallanPerTransportReport")
	public List<ChallanpertransportreportDTO> getChallanPerTransportReport(@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String transborneby)
	 {
		return delivery_challanService.getChallanPerTransportReport(fromdate,todate,transborneby);
	 }
	
 /*************************************************************Daily gate wheat inward report ends here***************************************/
					
/*************************************************************Wheat Receiving Report starts here***************************************/
					
		@GetMapping("/getWheatReceivinglist/{currDate}/{finyear}")
		public List<Wheatreceiving> getWheatReceivinglist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
		{
			  return wheatreceivingService.getWheatReceivinglist(currDate,finyear);
		}
		
		
		@PostMapping("/createwheatreceiving")
		public Wheatreceiving createwheatreceiving(@Valid @RequestBody Wheatreceiving wheatreceiving) {
			return wheatreceivingService.save(wheatreceiving);
		}
		
		@PutMapping("/updatewheatreceiving/{id}")
		public ResponseEntity<Wheatreceiving> updatewheatreceiving(@PathVariable(value = "id") Long id,@Valid @RequestBody Wheatreceiving wheatreceiving) {
			Wheatreceiving op = wheatreceivingService.update(wheatreceiving, id);
			return ResponseEntity.ok().body(op);
		}
		
		@PutMapping("/deletewheatreceiving/{id}")
		public ResponseEntity<Wheatreceiving> deletewheatreceiving(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Wheatreceiving wheatreceiving) {
			Wheatreceiving op = wheatreceivingService.delete(wheatreceiving, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		 @GetMapping(value = "/searchWheatreceiving")
		public List<Wheatreceiving> searchWheatreceiving(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
		 {
			return wheatreceivingService.searchWheatreceiving(fromdate,todate,finyear);
		 }
		
		 
		 
		 @GetMapping("/retriveWheatReceiving/{id}")
			public ResponseEntity<Wheatreceiving> retriveWheatReceiving(@PathVariable(value="id") Long id)
			{
			 Wheatreceiving op=wheatreceivingService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		 
		 @GetMapping("/retriveWheatDetails/{wheatreceiveid}")
			public List<Wheatreceiving_Dtls> retriveWheatDetails(@PathVariable(value = "wheatreceiveid") String wheatreceiveid)
			{
				return wheatreceivingService.retriveWheatDetails(wheatreceiveid);
			}
			
		@GetMapping("/retriveWheatIssueDetails/{wheatreceiveid}")
		public List<Wheat_issue_Dtls> retriveWheatIssueDetails(@PathVariable(value = "wheatreceiveid") String wheatreceiveid)
		{
			return wheatreceivingService.retriveWheatIssueDetails(wheatreceiveid);
		}
			
		
		@GetMapping("/retrivewheatstock_Dtls/{wheatreceiveid}")
		public List<Wheatstock_Dtls> retrivewheatstock_Dtls(@PathVariable(value = "wheatreceiveid") String wheatreceiveid)
		{
			return wheatreceivingService.retrivewheatstock_Dtls(wheatreceiveid);
		}
			
		@GetMapping("/getopeningstockwheatrecieve/{itemId}/{date}/{finyear}")
		public Wheatstock_Dtls getopeningstockwheatrecieve(@PathVariable(value = "itemId") String itemId,@PathVariable(value = "date") String date,@PathVariable(value = "finyear") String finyear)
		{
			return wheatreceivingService.getopeningstockwheatrecieve(itemId,date,finyear);
		}
	    
	 
 /*************************************************************Wheat Receiving Report ends here***************************************/
					
/*************************************************************Wheat Stack Card Report starts here***************************************/
					
		@PostMapping("/createwheatstackcard")
		public Wheatstackcardreport createwheatstackcard(@Valid @RequestBody Wheatstackcardreport wheatstackcardreport) {
			return wheatstackcardreportService.save(wheatstackcardreport);
		}	
		
		@GetMapping("/getwheatstackcardlist/{finyear}")
		public List<WheatstackcardreportDTO> getwheatstackcardlist(@PathVariable(value = "finyear") String finyear) 
		{
			  return wheatstackcardreportService.getwheatstackcardlist(finyear);
		}
		
		
		@GetMapping("/retrievewheatstackcard/{id}")
		public Wheatstackcardreport retrievewheatstackcard(@PathVariable(value = "id") long id) 
		{
			  return wheatstackcardreportService.retrievewheatstackcard(id);
		}
		
		
		@GetMapping("/retrievewheatstackcard_details/{wheatstackid}")
		public List<Wheatstackcardreportdetails> retrievewheatstackcard_details(@PathVariable(value = "wheatstackid") String wheatstackid) 
		{
			  return wheatstackcardreportService.retrievewheatstackcard_details(wheatstackid);
		}
		
		@PutMapping("/updatewheatstackcard/{id}")
		public ResponseEntity<Wheatstackcardreport> updatewheatstackcard(@PathVariable(value = "id") Long id,@Valid @RequestBody Wheatstackcardreport wheatstackcardreport) {
			Wheatstackcardreport op = wheatstackcardreportService.update(wheatstackcardreport, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		@PutMapping("/deletewheatstackcard/{id}")
		public ResponseEntity<Wheatstackcardreport> deletewheatstackcard(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Wheatstackcardreport wheatstackcardreport) {
			Wheatstackcardreport op = wheatstackcardreportService.delete(wheatstackcardreport, id);
			return ResponseEntity.ok().body(op);
		}
		
					
/*************************************************************Wheat Stack Card Report ends here***************************************/
		/*************************************************************Gate In/Out Register starts here***************************************/
		
		@GetMapping("/getGateinoutList/{currDate}/{finyear}")
		public List<Gateinoutregister> getGateinoutList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
		{
			  return gateinoutregisterService.getGateinoutregister(currDate,finyear);
		}
		
		
		@PostMapping("/createGateinout")
		public Gateinoutregister createGateinout(@Valid @RequestBody Gateinoutregister gateinoutregister) {
			return gateinoutregisterService.save(gateinoutregister);
		}
		
		@PutMapping("/updateGateinout/{id}")
		public ResponseEntity<Gateinoutregister> updateGateinout(@PathVariable(value = "id") Long id,@Valid @RequestBody Gateinoutregister gateinoutregister) {
			Gateinoutregister op = gateinoutregisterService.update(gateinoutregister, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		
		@PutMapping("/deleteGateinout/{id}")
		public ResponseEntity<Gateinoutregister> deleteGateinout(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Gateinoutregister gateinoutregister) {
			Gateinoutregister op = gateinoutregisterService.delete(gateinoutregister, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		 @GetMapping(value = "/searchgateinoutRegister")
		public List<Gateinoutregister> searchgateinoutRegister(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
		 {
			return gateinoutregisterService.searchgateinoutRegister(fromdate,todate,finyear);
		 }
		
		 
		 
	 @GetMapping("/retriveGateinout/{id}")
		public ResponseEntity<Gateinoutregister> retriveGateinout(@PathVariable(value="id") Long id)
		{
		 Gateinoutregister op=gateinoutregisterService.findOne(id);
			if(id==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(op);
			}
		}
	
					
/*************************************************************Gate In/Out Register ends here***************************************/
	 
	 /*************************************************************GatePass Register starts here***************************************/
		
		@GetMapping("/getGatepassRegisterList/{currDate}/{finyear}")
		public List<Gatepassregister> getGatepassRegisterList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
		{
			  return gatepassregisterService.getGatepassRegisterList(currDate,finyear);
		}
		
		
		@PostMapping("/createGatepassRegister")
		public Gatepassregister createGatepassRegister(@Valid @RequestBody Gatepassregister gatepassregister) {
			return gatepassregisterService.save(gatepassregister);
		}
		
		@PutMapping("/updateGatepassRegister/{id}")
		public ResponseEntity<Gatepassregister> updateGatepassRegister(@PathVariable(value = "id") Long id,@Valid @RequestBody Gatepassregister gatepassregister) {
			Gatepassregister op = gatepassregisterService.update(gatepassregister, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		
		@PutMapping("/deleteGatePassRegister/{id}")
		public ResponseEntity<Gatepassregister> deleteGatePassRegister(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Gatepassregister gatepassregister) {
			Gatepassregister op = gatepassregisterService.delete(gatepassregister, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		 @GetMapping(value = "/searchgatePassRegister")
		public List<Gatepassregister> searchgatePassRegister(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
		 {
			return gatepassregisterService.searchgatePassRegister(fromdate,todate,finyear);
		 }
		
		 
		 
	 @GetMapping("/retriveGatePassRegister/{id}")
		public ResponseEntity<Gatepassregister> retriveGatePassRegister(@PathVariable(value="id") Long id)
		{
		 Gatepassregister op=gatepassregisterService.findOne(id);
			if(id==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(op);
			}
		}
	
					
/*************************************************************GatePass Register ends here***************************************/
/*************************************************************Daily Production Starts here***************************************/
	 
	 @GetMapping("/getDailyproductionList/{currDate}/{finyear}")
		public List<Dailyproduction> getDailyproductionList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
		{
			  return dailyproductionService.getDailyproductionList(currDate,finyear);
		}
	 
	 @PostMapping("/createdailyProduction")
		public Dailyproduction createdailyProduction(@Valid @RequestBody Dailyproduction dailyproduction) {
			return dailyproductionService.save(dailyproduction);
		}
		
		@PutMapping("/updatedailyProduction/{id}")
		public ResponseEntity<Dailyproduction> updatedailyProduction(@PathVariable(value = "id") Long id,@Valid @RequestBody Dailyproduction dailyproduction) {
			Dailyproduction op = dailyproductionService.update(dailyproduction, id);
			return ResponseEntity.ok().body(op);
		}
		
		@PutMapping("/deleteDailyProduction/{id}")
		public ResponseEntity<Dailyproduction> deleteDailyProduction(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Dailyproduction dailyproduction) {
			Dailyproduction op = dailyproductionService.delete(dailyproduction, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		 @GetMapping(value = "/searchDailyProduction")
		public List<Dailyproduction> searchDailyProduction(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
		 {
			return dailyproductionService.searchDailyProduction(fromdate,todate,finyear);
		 }
		
		 @GetMapping("/productionreportitembydata/{itemId}/{date}")
			public Dailyproduction_Dtls productionreportitembydata(@PathVariable(value = "itemId") String itemId,@PathVariable(value = "date") String date)
			{
				return dailyproductionService.productionreportitembydata(itemId,date);
			}
			
		 
		 
		 @GetMapping("/retriveDailyProduction/{id}")
			public ResponseEntity<Dailyproduction> retriveDailyProduction(@PathVariable(value="id") Long id)
			{
			 Dailyproduction op=dailyproductionService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		 
		 @GetMapping("/retriveProductionDetails/{dailyproductionid}")
			public List<Dailyproduction_Dtls> retriveProductionDetails(@PathVariable(value = "dailyproductionid") String dailyproductionid)
			{
				return dailyproductionService.retriveProductionDetails(dailyproductionid);
			}
			
		 @GetMapping("/retriveProductionDetails1/{dailyproductionid}")
			public List<Dailyproduction_Dtls_One> retriveProductionDetails1(@PathVariable(value = "dailyproductionid") String dailyproductionid)
			{
				return dailyproductionService.retriveProductionDetails1(dailyproductionid);
			}
				
			@GetMapping("/getItemCodeByTypeNew/{type}")
			public List<Item_masterDTO> getItemCodeByTypeNew(@PathVariable(value = "type") String type)
			{
				return dailyproductionService.getItemCodeByTypeNew(type);
			}
			
			@GetMapping("/getItemCapacity/{item_code}/{finyear}")
			public Item_master_pack_mat_tag getItemCapacity(@PathVariable(value = "item_code") String item_code,@PathVariable(value = "finyear") String finyear)
			{
				return dailyproductionService.getItemCapacity(item_code,finyear);
			}
/*************************************************************Daily Production Ends here***************************************/
/*************************************************************Lab Report Starts here***************************************/
			@GetMapping("/getLabReportlist/{currDate}/{finyear}")
			public List<Misclabreportfg> getLabReportlist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
			{
				  return misclabreportfgService.getLabReportlist(currDate,finyear);
			}
		 
		 @PostMapping("/createLabReport")
			public Misclabreportfg createLabReport(@Valid @RequestBody Misclabreportfg misclabreportfg) {
				return misclabreportfgService.save(misclabreportfg);
			}
			
			@PutMapping("/updateLabReport/{id}")
			public ResponseEntity<Misclabreportfg> updateLabReport(@PathVariable(value = "id") Long id,@Valid @RequestBody Misclabreportfg misclabreportfg) {
				Misclabreportfg op = misclabreportfgService.update(misclabreportfg, id);
				return ResponseEntity.ok().body(op);
			}
			
			@PutMapping("/deleteLabReport/{id}")
			public ResponseEntity<Misclabreportfg> deleteLabReport(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Misclabreportfg misclabreportfg) {
				Misclabreportfg op = misclabreportfgService.delete(misclabreportfg, id);
				return ResponseEntity.ok().body(op);
			}
			
			
			 @GetMapping(value = "/searcLabReport")
			public List<Misclabreportfg> searcLabReport(@RequestParam(defaultValue = "") String fromdate,
					@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
			 {
				return misclabreportfgService.searcLabReport(fromdate,todate,finyear);
			 }
			
			 
			 
			 @GetMapping("/retriveLabReport/{id}")
				public ResponseEntity<Misclabreportfg> retriveLabReport(@PathVariable(value="id") Long id)
				{
				 Misclabreportfg op=misclabreportfgService.findOne(id);
					if(id==null)
					{
						return ResponseEntity.notFound().build();
					}
					else
					{
						return ResponseEntity.ok().body(op);
					}
				}
			 
			 
			 @GetMapping("/retriveLabReportDetails/{misclabreportfgid}")
				public List<Misclabreportfg_Dtls> retriveLabReportDetails(@PathVariable(value = "misclabreportfgid") String misclabreportfgid)
				{
					return misclabreportfgService.retriveLabReportDetails(misclabreportfgid);
				}
				
			
/*************************************************************Lab Report Ends here***************************************/
/*************************************************************Granulation Report Starts here***************************************/
				@GetMapping("/getGranulationlist/{currDate}/{finyear}")
				public List<Granulation> getGranulationlist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
				{
					  return granulationService.getGranulationlist(currDate,finyear);
				}
				
				@GetMapping("/getSeiveslistByitemid/{itemid}")
				public List<Seives_Dtls> getSeiveslistByitemid(@PathVariable(value = "itemid") String itemid) 
				{
					System.out.println("itemid: "+itemid);
					  return granulationService.getSeiveslistByitemid(itemid);
				}
				
				@PostMapping("/createGranulation")
				public Granulation createGranulation(@Valid @RequestBody Granulation granulation) {
					return granulationService.save(granulation);
				}
				
				@PutMapping("/updateGranulation/{id}")
				public ResponseEntity<Granulation> updateGranulation(@PathVariable(value = "id") Long id,@Valid @RequestBody Granulation granulation) {
					Granulation op = granulationService.update(granulation, id);
					return ResponseEntity.ok().body(op);
				}
				
				@PutMapping("/deleteGranulation/{id}")
				public ResponseEntity<Granulation> deleteGranulation(@PathVariable(value = "id") Long id,
						@Valid @RequestBody Granulation granulation) {
					Granulation op = granulationService.delete(granulation, id);
					return ResponseEntity.ok().body(op);
				}
				
				
				 @GetMapping(value = "/searchGranulationReport")
				public List<Granulation> searchGranulationReport(@RequestParam(defaultValue = "") String fromdate,
						@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
				 {
					return granulationService.searchGranulationReport(fromdate,todate,finyear);
				 }
				
				 
				 
				 @GetMapping("/retriveGranulationReport/{id}")
					public ResponseEntity<Granulation> retriveGranulationReport(@PathVariable(value="id") Long id)
					{
					 Granulation op=granulationService.findOne(id);
						if(id==null)
						{
							return ResponseEntity.notFound().build();
						}
						else
						{
							return ResponseEntity.ok().body(op);
						}
					}
				 
				 
				 @GetMapping("/retriveGranulationDetails/{granulationreportid}")
					public List<Granulation_Dtls> retriveGranulationDetails(@PathVariable(value = "granulationreportid") String granulationreportid)
					{
						return granulationService.retriveGranulationDetails(granulationreportid);
					}
					
				
/*************************************************************Granulation Report Ends here***************************************/
/*************************************************************Lab Report Starts here***************************************/
					@GetMapping("/getOtherParameterlist/{currDate}/{finyear}")
					public List<Otherparameterreport> getOtherParameterlist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
					{
						  return otherparameterreportService.getOtherParameterlist(currDate,finyear);
					}
				 
					@PostMapping("/createOtherParameter")
					public Otherparameterreport createOtherParameter(@Valid @RequestBody Otherparameterreport otherparameterreport) {
						return otherparameterreportService.save(otherparameterreport);
					}
					
					@PutMapping("/updateOtherParameter/{id}")
					public ResponseEntity<Otherparameterreport> updateOtherParameter(@PathVariable(value = "id") Long id,@Valid @RequestBody Otherparameterreport otherparameterreport) {
						Otherparameterreport op = otherparameterreportService.update(otherparameterreport, id);
						return ResponseEntity.ok().body(op);
					}
					
					@PutMapping("/deleteOtherParameter/{id}")
					public ResponseEntity<Otherparameterreport> deleteOtherParameter(@PathVariable(value = "id") Long id,
							@Valid @RequestBody Otherparameterreport otherparameterreport) {
						Otherparameterreport op = otherparameterreportService.delete(otherparameterreport, id);
						return ResponseEntity.ok().body(op);
					}
					
					
					 @GetMapping(value = "/searcOtherparameter")
					public List<Otherparameterreport> searcOtherparameter(@RequestParam(defaultValue = "") String fromdate,
							@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
					 {
						return otherparameterreportService.searcOtherparameter(fromdate,todate,finyear);
					 }
					
					 
					 
					 @GetMapping("/retriveOtherParameter/{id}")
						public ResponseEntity<Otherparameterreport> retriveOtherParameter(@PathVariable(value="id") Long id)
						{
						 Otherparameterreport op=otherparameterreportService.findOne(id);
							if(id==null)
							{
								return ResponseEntity.notFound().build();
							}
							else
							{
								return ResponseEntity.ok().body(op);
							}
						}
					 
					 
					 @GetMapping("/retriveOtherParameterDetails/{otherparameterid}")
						public List<Otherparameterreport_Dtls> retriveOtherParameterDetails(@PathVariable(value = "otherparameterid") String otherparameterid)
						{
							return otherparameterreportService.retriveOtherParameterDetails(otherparameterid);
						}
						
					
/*************************************************************Lab Report Ends here***************************************/ 


/*************************************************************Bin Report start here***************************************/ 
				@Autowired 		
				BinreportService binreportService;	 
						 
				@GetMapping("/getbinreportlist/{businessunit_id}/{finyear}")
				public List<Binreportdetails> getbinreportlist(@PathVariable(value = "businessunit_id") String businessunit_id,@PathVariable(value = "finyear") String finyear)
				{
					return binreportService.getbinreportlist(businessunit_id,finyear);
				}
				
				@PostMapping("/createbinreport")
				public Binreport createbinreport(@Valid @RequestBody Binreport binreport) {
					return binreportService.save(binreport);
				}
				
				@PutMapping("/updatebinreport/{id}")
				public ResponseEntity<Binreport> updatebinreport(@PathVariable(value = "id") Long id,@Valid @RequestBody Binreport binreport) {
					Binreport op = binreportService.update(binreport, id);
					return ResponseEntity.ok().body(op);
				}
				
				@PutMapping("/deletebinreport/{id}")
				public ResponseEntity<Binreport> deletebinreport(@PathVariable(value = "id") Long id,
						@Valid @RequestBody Binreport binreport) {
					Binreport op = binreportService.delete(binreport, id);
					return ResponseEntity.ok().body(op);
				}
				
				@GetMapping("/retrivebillreport/{id}")
				public ResponseEntity<Binreport> retrivebillreport(@PathVariable(value="id") Long id)
				{
				 Binreport op=binreportService.findOne(id);
					if(id==null)
					{
						return ResponseEntity.notFound().build();
					}
					else
					{
						return ResponseEntity.ok().body(op);
					}
				}
							
			    @GetMapping("/retrivebillreportDetails/{binreportid}")
			    public List<Binreportdetails> retrivebillreportDetails(@PathVariable(value = "binreportid") String binreportid)
				{
					return binreportService.retrivebillreportDetails(binreportid);
				}
			    
				
			    @GetMapping("/retrivebillreportcolumnDetails/{binreportid}")
			    public List<BinreportdetailspopupDTO> retrivebillreportcolumnDetails(@PathVariable(value = "binreportid") String binreportid)
				{
					return binreportService.retrivebillreportcolumnDetails(binreportid);
				}
			    
			    @GetMapping("/getbillreportlist/{finyear}")
				public List<Binreport> getbillreportlist(@PathVariable(value = "finyear") String finyear)
				{
					return binreportService.getbillreportlist(finyear);
				}
			    
					
						
						
/*************************************************************Bin Report Ends here***************************************/ 

/*************************************************************Mill Breakdown Report Starts here***************************************/
						@GetMapping("/getMillBreakdownlist/{currDate}/{finyear}")
						public List<Millbreakdownreport> getMillBreakdownlist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
						{
							  return millbreakdownreportService.getMillBreakdownlist(currDate,finyear);
						}
					 
						@PostMapping("/createMillBreakdown")
						public Millbreakdownreport createMillBreakdown(@Valid @RequestBody Millbreakdownreport millbreakdownreport) {
							return millbreakdownreportService.save(millbreakdownreport);
						}
						
						@PutMapping("/updateMillBreakdown/{id}")
						public ResponseEntity<Millbreakdownreport> updateMillBreakdown(@PathVariable(value = "id") Long id,@Valid @RequestBody Millbreakdownreport millbreakdownreport) {
							Millbreakdownreport op = millbreakdownreportService.update(millbreakdownreport, id);
							return ResponseEntity.ok().body(op);
						}
						
						@PutMapping("/deleteMillBreakdown/{id}")
						public ResponseEntity<Millbreakdownreport> deleteMillBreakdown(@PathVariable(value = "id") Long id,
								@Valid @RequestBody Millbreakdownreport millbreakdownreport) {
							Millbreakdownreport op = millbreakdownreportService.delete(millbreakdownreport, id);
							return ResponseEntity.ok().body(op);
						}
						
						
						 @GetMapping(value = "/searcMillBreakdown")
						public List<Millbreakdownreport> searcMillBreakdown(@RequestParam(defaultValue = "") String fromdate,
								@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
						 {
							return millbreakdownreportService.searcMillBreakdown(fromdate,todate,finyear);
						 }
						
						 
						 
						 @GetMapping("/retriveMillBreakdown/{id}")
							public ResponseEntity<Millbreakdownreport> retriveMillBreakdown(@PathVariable(value="id") Long id)
							{
							 Millbreakdownreport op=millbreakdownreportService.findOne(id);
								if(id==null)
								{
									return ResponseEntity.notFound().build();
								}
								else
								{
									return ResponseEntity.ok().body(op);
								}
							}
						 
						 
						 @GetMapping("/retriveMillBreakdownDetails/{millbreakdownid}")
							public List<Millbreakdownreport_Dtls> retriveMillBreakdownDetails(@PathVariable(value = "millbreakdownid") String millbreakdownid)
							{
								return millbreakdownreportService.retriveMillBreakdownDetails(millbreakdownid);
							}
							
						
/*************************************************************Mill Breakdown Report Ends here***************************************/ 
/*************************************************************Mill Breakdown Report Starts here***************************************/
				@GetMapping("/getWeigherReadingList/{currDate}/{finyear}")
				public List<Weigherreding> getWeigherReadingList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
				{
					  return weigherredingService.getWeigherReadingList(currDate,finyear);
				}
			 
				@PostMapping("/createWeigherReading")
				public Weigherreding createWeigherReading(@Valid @RequestBody Weigherreding weigherreding) {
					return weigherredingService.save(weigherreding);
				}
				
				@PutMapping("/updateWeigherReading/{id}")
				public ResponseEntity<Weigherreding> updateWeigherReading(@PathVariable(value = "id") Long id,@Valid @RequestBody Weigherreding weigherreding) {
					Weigherreding op = weigherredingService.update(weigherreding, id);
					return ResponseEntity.ok().body(op);
				}
				
				@PutMapping("/deleteWeigherReading/{id}")
				public ResponseEntity<Weigherreding> deleteWeigherReading(@PathVariable(value = "id") Long id,
						@Valid @RequestBody Weigherreding weigherreding) {
					Weigherreding op = weigherredingService.delete(weigherreding, id);
					return ResponseEntity.ok().body(op);
				}
				
				
				 @GetMapping(value = "/searcWeigherReading")
				public List<Weigherreding> searcWeigherReading(@RequestParam(defaultValue = "") String fromdate,
						@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
				 {
					return weigherredingService.searcWeigherReading(fromdate,todate,finyear);
				 }
				
				 
				 
				 @GetMapping("/retriveWeigherReading/{id}")
					public ResponseEntity<Weigherreding> retriveWeigherReading(@PathVariable(value="id") Long id)
					{
					 Weigherreding op=weigherredingService.findOne(id);
						if(id==null)
						{
							return ResponseEntity.notFound().build();
						}
						else
						{
							return ResponseEntity.ok().body(op);
						}
					}
				 
							 
							
								
							
/*************************************************************Mill Breakdown Report Ends here***************************************/ 
/*************************************************************Wheat Acceptance Report Starts here***************************************/
			@GetMapping("/getWheatAcceptancelist/{currDate}/{finyear}")
			public List<Wheatacceptance> getWheatAcceptancelist(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear) 
			{
				  return wheatacceptanceService.getWheatAcceptancelist(currDate,finyear);
			}
			
			@PostMapping("/createWheatAcceptance")
			public Wheatacceptance createWheatAcceptance(@Valid @RequestBody Wheatacceptance wheatacceptance) {
				return wheatacceptanceService.save(wheatacceptance);
			}
			
			@PutMapping("/updateWheatAcceptance/{id}")
			public ResponseEntity<Wheatacceptance> updateWheatAcceptance(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Wheatacceptance wheatacceptance) {
				System.out.println("id"+id);
				Wheatacceptance op = wheatacceptanceService.update(wheatacceptance, id);
				return ResponseEntity.ok().body(op);
			}
			
			@PutMapping("/deleteWheatAcceptance/{id}")
			public ResponseEntity<Wheatacceptance> deleteWheatAcceptance(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Wheatacceptance wheatacceptance) {
				Wheatacceptance op = wheatacceptanceService.delete(wheatacceptance, id);
				return ResponseEntity.ok().body(op);
			}
					
			
			 @GetMapping(value = "/searchWheatAcceptance")
			public List<Wheatacceptance> searchWheatAcceptance(@RequestParam(defaultValue = "") String fromdate,
					@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String finyear)
			 {
				return wheatacceptanceService.searchWheatAcceptance(fromdate,todate,finyear);
			 }
			
			 
			 
		 @GetMapping("/retriveWheatAcceptance/{id}")
			public ResponseEntity<Wheatacceptance> retriveWheatAcceptance(@PathVariable(value="id") Long id)
			{
			 Wheatacceptance op=wheatacceptanceService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		  
		 @GetMapping("/getWheatAcceptancePrintList/{acceptanceid}")
			public List<Wheatacceptance> getWheatAcceptancePrintList(@PathVariable(value = "acceptanceid") String acceptanceid) 
			{
				  return wheatacceptanceService.getWheatAcceptancePrintList(acceptanceid);
			}
					
	/*************************************************************Wheat Acceptance Report Ends here***************************************/
			 
	 @GetMapping("/getUnloadAdviceReport/{fromdate}/{todate}/{suppliername}/{finyear}")
	    public List<Map<String, Object>> getUnloadAdviceReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "suppliername") String suppliername,@PathVariable(value = "finyear") String finyear) 
		{
			return wm_unload_adviceService.getUnloadAdviceReport(fromdate,todate,suppliername,finyear);
		}
	 
 /*************************************************************Special Reports Starts here***************************************/
	 
	 @GetMapping("/salesOrderFinishedItemlist/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> salesOrderFinishedItemlist(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return sales_orderService.salesOrderFinishedItemlist(company,fromdate,todate,business_unit);
		}
	 @GetMapping("/salesOrderBrokerlist/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> salesOrderBrokerlist(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return sales_orderService.salesOrderBrokerlist(company,fromdate,todate,business_unit);
		}
	 @GetMapping("/salesOrderPartylist/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> salesOrderPartylist(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return sales_orderService.salesOrderPartylist(company,fromdate,todate,business_unit);
		}
	 
	 @GetMapping("/getSalesOrderSummaryCatagorywiseList/{catagory}/{catagoryname}/{fromdate}/{todate}/{bunit}")
	    public List<Map<String, Object>> getSalesOrderSummaryCatagorywiseList(@PathVariable(value = "catagory") String catagory,
	    		@PathVariable(value = "catagoryname") String catagoryname,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "bunit") String bunit)
		{
		 //System.out.println("customer::"+customer+"//"+finyear);
			return sales_orderService.getSalesOrderSummaryCatagorywiseList(catagory,catagoryname,fromdate,todate,bunit);
		}
	 
	 @GetMapping("/getSalesOrderMiscList/{catagory}/{fromdate}/{todate}/{bunit}/{broker}/{customer}")
	    public List<Map<String, Object>> getSalesOrderMiscList(@PathVariable(value = "catagory") String catagory,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "bunit") String bunit,
	    		@PathVariable(value = "broker") String broker,
	    		@PathVariable(value = "customer") String customer) 
		{
			return sales_orderService.getSalesOrderMiscList(catagory,fromdate,todate,bunit,broker,customer);
		}
	 
	 @GetMapping("/getLoadingAdviceReportThrouhgSO/{soid}")
		public List<Map<String, Object>> getLoadingAdviceReportThrouhgSO(@PathVariable(value = "soid") String soid)
		{
			return wm_loading_adviceService.getLoadingAdviceReportThrouhgSO(soid);
		}
	 
	 @GetMapping("/getDeliveryChallanReportThrouhgLA/{loadingid}")
		public List<Map<String, Object>> getDeliveryChallanReportThrouhgLA(@PathVariable(value = "loadingid") String loadingid)
		{
			return delivery_challanService.getDeliveryChallanReportThrouhgLA(loadingid);
		}
	 
	 @GetMapping("/getInvoiceReportThroughChallan/{challanid}")
		 public List<Map<String,Object>> getInvoiceReportThroughChallan(@PathVariable(value="challanid") String challanid)
		 {
		 	return sales_invoiceService.getInvoiceReportThroughChallan(challanid);
		 }
	 
	 @GetMapping("/salesInvoiceFinishedItemlist/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> salesInvoiceFinishedItemlist(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return sales_invoiceService.salesInvoiceFinishedItemlist(company,fromdate,todate,business_unit);
		}
	 @GetMapping("/salesInvoiceBrokerlist/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> salesInvoiceBrokerlist(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return sales_invoiceService.salesInvoiceBrokerlist(company,fromdate,todate,business_unit);
		}
	 @GetMapping("/salesInvoicePartylist/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> salesInvoicePartylist(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return sales_invoiceService.salesInvoicePartylist(company,fromdate,todate,business_unit);
		}

	 @GetMapping("/getSalesInvoiceSummaryCatagorywiseList/{catagory}/{catagoryname}/{fromdate}/{todate}/{bunit}")
	    public List<Map<String, Object>> getSalesInvoiceSummaryCatagorywiseList(@PathVariable(value = "catagory") String catagory,
	    		@PathVariable(value = "catagoryname") String catagoryname,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "bunit") String bunit)
		{
		 //System.out.println("customer::"+customer+"//"+finyear);
			return sales_invoiceService.getSalesInvoiceSummaryCatagorywiseList(catagory,catagoryname,fromdate,todate,bunit);
		}
	 
	 @GetMapping("/getSalesInvoiceMiscList/{catagory}/{fromdate}/{todate}/{bunit}/{broker}/{customer}")
	    public List<Map<String, Object>> getSalesInvoiceMiscList(@PathVariable(value = "catagory") String catagory,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "bunit") String bunit,
			    @PathVariable(value = "broker") String broker,
				@PathVariable(value = "customer") String customer)
		{
			return sales_invoiceService.getSalesInvoiceMiscList(catagory,fromdate,todate,bunit,broker,customer);
		}
	 
	 @GetMapping("/purchaseOrderSupplierNamesList/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> purchaseOrderSupplierNamesList(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return pur_OrderService.purchaseOrderSupplierNamesList(company,fromdate,todate,business_unit);
		}
	 
	 @GetMapping("/purchaseOrderBrokerNamesList/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> purchaseOrderBrokerNamesList(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return pur_OrderService.purchaseOrderBrokerNamesList(company,fromdate,todate,business_unit);
		}
	  
	 @GetMapping("/getPurchaseordermiscreport/{business_unit}/{catagory}/{fromdate}/{todate}/{supplier_name}/{ven_code_name}/{statustype}")
	    public List<Map<String, Object>> getPurchaseordermiscreport(@PathVariable(value = "business_unit") String business_unit,
	    		@PathVariable(value = "catagory") String catagory,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "supplier_name") String supplier_name,
	    		@PathVariable(value = "ven_code_name") String ven_code_name,
	    		@PathVariable(value = "statustype") String statustype)
		{
		// System.out.println("catagory::"+catagory+"//"+fromdate+"//"+todate+"//"+supplier_name+"//"+ven_code_name+"//"+business_unit+"//"+statustype);
			return pur_OrderService.getPurchaseordermiscreport(business_unit,catagory,fromdate,todate,supplier_name,ven_code_name,statustype);
		}
	 
	 
	 @GetMapping("/getUnloadListThroughPurOrderId/{purorderid}")
	    public List<Map<String, Object>> getUnloadListThroughPurOrderId(@PathVariable(value = "purorderid") String purorderid)
		{
		 	//System.out.println("purorderid::"+purorderid);
			return wm_unload_adviceService.getUnloadListThroughPurOrderId(purorderid);
		}
	 
	 @GetMapping("/getGRNThroughUnloadId/{unadvice}")
	    public List<Map<String, Object>> getGRNThroughUnloadId(@PathVariable(value = "unadvice") String unadvice)
		{
		 	//System.out.println("unadvice::"+unadvice);
			return pur_good_receiptService.getGRNThroughUnloadId(unadvice);
		}
	 
	 @GetMapping("/getBillThroughGRNId/{grnid}")
	    public List<Map<String, Object>> getBillThroughGRNId(@PathVariable(value = "grnid") String grnid)
		{
		 	//System.out.println("grnid::"+grnid);
			return pur_BillService.getBillThroughGRNId(grnid);
		}
		
	 @GetMapping("/purchaseBillSupplierNamesList/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> purchaseBillSupplierNamesList(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return pur_BillService.purchaseBillSupplierNamesList(company,fromdate,todate,business_unit);
		}
	 
	 @GetMapping("/purchaseBillBrokerNamesList/{company}/{fromdate}/{todate}/{business_unit}")
	    public List<Map<String, Object>> purchaseBillBrokerNamesList(@PathVariable(value = "company") String company,@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,@PathVariable(value = "business_unit") String business_unit)
		{
		 //System.out.println("company::"+company+"//"+fromdate+"//"+todate+"//"+business_unit);
			return pur_BillService.purchaseBillBrokerNamesList(company,fromdate,todate,business_unit);
		}
	 
		@GetMapping("/getPurchaseBillmiscreport/{business_unit}/{catagory}/{fromdate}/{todate}/{supplier_name}/{ven_code_name}")
	    public List<Map<String, Object>> getPurchaseBillmiscreport(@PathVariable(value = "business_unit") String business_unit,
	    		@PathVariable(value = "catagory") String catagory,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "supplier_name") String supplier_name,
	    		@PathVariable(value = "ven_code_name") String ven_code_name) 
		{
		// System.out.println("catagory::"+catagory+"//"+fromdate+"//"+todate+"//"+supplier_name+"//"+ven_code_name+"//"+business_unit);
			return pur_BillService.getPurchaseBillmiscreport(business_unit,catagory,fromdate,todate,supplier_name,ven_code_name);
		}
		
		@GetMapping("/getGrnDetailsThroughGrnId/{grnid}")
	    public List<Map<String, Object>> getGrnDetailsThroughGrnId(@PathVariable(value = "grnid") String grnid)
		{
		 	//System.out.println("grnid::"+grnid);
			return pur_good_receiptService.getGrnDetailsThroughGrnId(grnid);
		}
		
		@GetMapping("/getUnloadDetailsThroughUnloadId/{unloadid}")
	    public List<Map<String, Object>> getUnloadDetailsThroughUnloadId(@PathVariable(value = "unloadid") String unloadid)
		{
		 	//System.out.println("unloadid::"+unloadid);
			return wm_unload_adviceService.getUnloadDetailsThroughUnloadId(unloadid);
		}
		
		@GetMapping("/getPurOrderDetailsThroughOrderId/{orderid}")
	    public List<Map<String, Object>> getPurOrderDetailsThroughOrderId(@PathVariable(value = "orderid") String orderid)
		{
		 	//System.out.println("orderid::"+orderid);
			return pur_OrderService.getPurOrderDetailsThroughOrderId(orderid);
		}
	 
 /*************************************************************Special Reports Ends here***************************************/
	 
		/************** Start Pending Souda **************/
		@GetMapping("/getPendingSoudaReport/{fromdate}/{todate}/{catagory}")
		public List<Map<String, Object>> getPendingSoudaReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "catagory") String catagory)
		{
			return pur_OrderService.getPendingSoudaReport(fromdate,todate,catagory);
		}
		
		/************** End Pending Souda **************/	
		
		/************** Start Sales Report **************/
		@GetMapping("/getSalesOrderReport/{fromdate}/{todate}")
		public List<Map<String, Object>> getSalesOrderReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
		{
			return sales_orderService.getSalesOrderReport(fromdate,todate);
		}
		
		@GetMapping("/getSalesOrderProcessWiseReport/{fromdate}/{todate}/{salesprocess}")
		public List<Map<String, Object>> getSalesOrderProcessWiseReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "salesprocess") String salesprocess)
		{
			return sales_orderService.getSalesOrderProcessWiseReport(fromdate,todate,salesprocess);
		}
		
		@GetMapping("/getSalesOrderReportOrderWise/{salesordernumber}")
		public List<Map<String, Object>> getSalesOrderReportOrderWise(@PathVariable(value = "salesordernumber") String salesordernumber)
		{
			return sales_orderService.getSalesOrderReportOrderWise(salesordernumber);
		}
		
		@GetMapping("/getSalesOrderReportOrderProcessWise/{salesordernumber}/{salesprocess}")
		public List<Map<String, Object>> getSalesOrderReportOrderProcessWise(@PathVariable(value = "salesordernumber") String salesordernumber,@PathVariable(value = "salesprocess") String salesprocess)
		{
			return sales_orderService.getSalesOrderReportOrderProcessWise(salesordernumber,salesprocess);
		}
		
		/************** End Pending Souda **************/
		
		
		/************** Start Purchase Bill Anuj Sir **************/
		
		@GetMapping("/getPurBillReport/{fromdate}/{todate}")
		public List<Map<String, Object>> getPurBillReport(@PathVariable(value = "fromdate") String fromdate,
														  @PathVariable(value = "todate") String todate)
		{
			return pur_BillService.getPurBillReport(fromdate,todate);
		}
		
		/************** End Purchase Bill Anuj Sir **************/
		
		/************** Start Purchase Order Anuj Sir **************/
		
		@GetMapping("/getPurOrderReport/{fromdate}/{todate}")
		public List<Map<String, Object>> getPurOrderReport(@PathVariable(value = "fromdate") String fromdate,
														  @PathVariable(value = "todate") String todate)
		{
			return pur_OrderService.getPurOrderReport(fromdate,todate);
		}
		
		/************** End Purchase Order Anuj Sir **************/
		
		/************** Start Job Work Report **************/
		
		@GetMapping("/getJobWorkSalesReport/{fromdate}/{todate}")
		public List<Map<String, Object>> getJobWorkSalesReport(@PathVariable(value = "fromdate") String fromdate,
														  @PathVariable(value = "todate") String todate)
		{
			return sales_invoiceService.getJobWorkSalesReport(fromdate,todate);
		}
		
		/************** End Job Work Report **************/
		
		
		/************** Start Production Report **************/
		@GetMapping("/getProdInputReport/{business_unit}/{shop_floor}/{fromdate}/{todate}")
		public List<Map<String, Object>> getProdInputReport(@PathVariable(value = "business_unit") String business_unit,@PathVariable(value = "shop_floor") String shop_floor,
				@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
		{
			return production_planningService.getProdInputReport(business_unit,shop_floor,fromdate,todate);
		}
		
		@GetMapping("/getProdOutputReport/{business_unit}/{shop_floor}/{fromdate}/{todate}")
		public List<Map<String, Object>> getProdOutputReport(@PathVariable(value = "business_unit") String business_unit,@PathVariable(value = "shop_floor") String shop_floor,
				@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
		{
			return production_planningService.getProdOutputReport(business_unit,shop_floor,fromdate,todate);
		}
		
		/************** End Production Report **************/
		
		/************** Start Special Production Report **************/
		
		@Autowired
		Production_transaction_splService production_transaction_splService;
		
		@GetMapping("/getspecialProdInputReport/{business_unit}/{shop_floor}/{fromdate}/{todate}")
		public List<Map<String, Object>> getspecialProdInputReport(@PathVariable(value = "business_unit") String business_unit,@PathVariable(value = "shop_floor") String shop_floor,
				@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
		{
			return production_transaction_splService.getspecialProdInputReport(business_unit,shop_floor,fromdate,todate);
		}
		
		@GetMapping("/getspecialProdOutputReport/{business_unit}/{shop_floor}/{fromdate}/{todate}")
		public List<Map<String, Object>> getspecialProdOutputReport(@PathVariable(value = "business_unit") String business_unit,@PathVariable(value = "shop_floor") String shop_floor,
				@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
		{
			return production_transaction_splService.getspecialProdOutputReport(business_unit,shop_floor,fromdate,todate);
		}
		
		/************** End Production Report **************/
		
		/************** Sales Transport Starts **************/
		
		@GetMapping("/getTransJVCode/{prefix}/{currDate}")
		public SequenceIdDTO getTransJVCode(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "currDate") String currDate)
		{
			return sales_transportService.getTransJVCode(prefix,currDate);
		}
		
		@GetMapping("/getSalesTransportReport/{business_unit}/{fromdate}/{todate}/{inv_type}/{trans_type}/{transporter_code}")
	    public List<Map<String, Object>> getSalesTransportReport(@PathVariable(value = "business_unit") String business_unit,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "inv_type") String inv_type,
	    		@PathVariable(value = "trans_type") String trans_type,
	    		@PathVariable(value = "transporter_code") String transporter_code) 
		{
		 System.out.println("business_unit::"+business_unit+"//"+fromdate+"//"+todate+"//"+inv_type+"//"+trans_type+"//"+transporter_code);
			return delivery_challanService.getSalesTransportReport(business_unit,fromdate,todate,inv_type,trans_type,transporter_code);
		}
		
		@GetMapping("/getPurchaseTransportReport/{business_unit}/{fromdate}/{todate}/{pur_inv_type}/{trans_type}")
	    public List<Map<String, Object>> getPurchaseTransportReport(@PathVariable(value = "business_unit") String business_unit,
	    		@PathVariable(value = "fromdate") String fromdate,
	    		@PathVariable(value = "todate") String todate,
	    		@PathVariable(value = "pur_inv_type") String pur_inv_type,
	    		@PathVariable(value = "trans_type") String trans_type) 
		{
		System.out.println("business_unit::"+business_unit+"//"+fromdate+"//"+todate+"//"+trans_type+"//"+pur_inv_type);
			return pur_good_receiptService.getPurchaseTransportReport(business_unit,fromdate,todate,pur_inv_type,trans_type);
		}
		
		@GetMapping("/getPurOrderTransChgsData/{referance_id}/{grn_id}")
	    public Map<String, Object> getPurOrderTransChgsData(@PathVariable(value = "referance_id") String referance_id,
	    												   @PathVariable(value = "grn_id") String grn_id) 
		{
		// System.out.println("business_unit::"+business_unit+"//"+fromdate+"//"+todate+"//"+inv_type+"//"+trans_type);
			return pur_OrderService.getPurOrderTransChgsData(referance_id,grn_id);
		}
		
		@GetMapping("/getSalesOrderData/{referance_id}/{delivery_cid}")
	    public Map<String, Object> getSalesOrderData(@PathVariable(value = "referance_id") String referance_id,
	    												   @PathVariable(value = "delivery_cid") String delivery_cid) 
		{
		// System.out.println("business_unit::"+business_unit+"//"+fromdate+"//"+todate+"//"+inv_type+"//"+trans_type);
			return sales_OrderService.getSalesOrderData(referance_id,delivery_cid);
		}
		
		@GetMapping("/getdeliverychallanData/{delivery_cid}")
	    public Map<String, Object> getdeliverychallanData(@PathVariable(value = "delivery_cid") String delivery_cid) 
		{
			return sales_OrderService.getdeliverychallanData(delivery_cid);
		}
	
		@GetMapping("/accountpostingsalestransport/{id}")
		public Sales_transport accountpostingsalestransport(@PathVariable(value = "id") Long id) {
			
				return sales_transportService.accountpostingsalestransport(id);
			
		}
		
		@GetMapping("/accountpostingsalestransportundo/{id}")
		public Sales_transport accountpostingsalestransportundo(@PathVariable(value = "id") Long id) {
			
				return sales_transportService.accountpostingsalestransportundo(id);
			
		}
		
		@GetMapping("/checkBulkSupply/{adviceid}")
	    public StatusDTO checkBulkSupply(@PathVariable(value = "adviceid") String adviceid)
		{
			return sales_transportService.checkBulkSupply(adviceid);
		}
		
		/************** Sales Transport Ends **************/
		
		/************** Start Sales Transport Report **************/
		
		@PostMapping("/createSalesTransport")
		public Sales_transport createSalesTransport(@Valid @RequestBody Sales_transport sales_transport) {
			return sales_transportService.save(sales_transport);
		}
		
		 @GetMapping("/retriveSalesTransport/{id}")
			public ResponseEntity<Sales_transport> retriveSalesTransport(@PathVariable(value="id") Long id)
			{
				Sales_transport op=sales_transportService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		@PutMapping("/updateSalesTransport/{id}")
		public ResponseEntity<Sales_transport> updateSalesTransport(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Sales_transport sales_transport) {
			//System.out.println("id"+id);
			Sales_transport op = sales_transportService.update(sales_transport, id);
			return ResponseEntity.ok().body(op);
		}
		
		/*@GetMapping("/getSalesTransactionReport/{fromdate}/{todate}")
		public List<Map<String, Object>> getSalesTransactionReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
		{
			return sales_transportService.getSalesTransactionReport(fromdate,todate);
		}*/
		@GetMapping(value = "/getSalesTransactionReport")
		public List<Map<String,Object>> getSalesTransactionReport(@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String chllanno,@RequestParam(defaultValue = "") String invtype,@RequestParam(defaultValue = "") String finyear,
				@RequestParam(defaultValue = "") String jvnum,@RequestParam(defaultValue = "") String date_search_type)
		 {
			//System.out.println("service:"+fromdate+"//"+todate+"//"+chllanno+"//"+invtype+"//"+finyear+"//"+jvnum);
			return sales_transportService.getSalesTransactionReport(fromdate,todate,chllanno,invtype,finyear,jvnum,date_search_type);
		 }
		
		@GetMapping("/getSalesTransactionReportCheckbalance/{fromdate}/{todate}/{inv_typenew}/{trans_code}")
		public List<Map<String, Object>> getSalesTransactionReportCheckbalance(@PathVariable(value = "fromdate") String fromdate,
																			   @PathVariable(value = "todate") String todate,
																			   @PathVariable(value = "inv_typenew") String inv_typenew,
																			   @PathVariable(value = "trans_code") String trans_code)
		{
			return sales_transportService.getSalesTransactionReportCheckbalance(fromdate,todate,inv_typenew,trans_code);
		}
		
		@GetMapping("/getSalesTransactionReportList/{currentdate}")
		public List<Map<String, Object>> getSalesTransactionReportList(@PathVariable(value = "currentdate") String currentdate)
		{
			return sales_transportService.getSalesTransactionReportList(currentdate);
		}
		
		@GetMapping("/getSalestransportappchgsList/{sales_tranport_id}")
		public List<Map<String, Object>> getSalestransportappchgsList(@PathVariable(value = "sales_tranport_id") String sales_tranport_id)
		{
			return sales_transportService.getSalestransportappchgsList(sales_tranport_id);
		}
		
		@GetMapping("/getSalesTransport/{id}")
		public Map<String, Object> getSalesTransport(@PathVariable(value = "id") String id)
		{
			return sales_transportService.getSalesTransport(id);
		}
		
		@GetMapping("/getSalesTransportChgs/{transportId}")
		public List<Map<String, Object>> getSalesTransportChgs(@PathVariable(value = "transportId") String transportId)
		{
			return sales_transportService.getSalesTransportChgs(transportId);
		}
		
		@GetMapping("/searchTransportStatement/{fromdate}/{todate}/{invoicetype}")
		public List<Map<String, Object>> searchTransportStatement(@PathVariable(value = "fromdate") String fromdate,
				@PathVariable(value = "todate") String todate,@PathVariable(value = "invoicetype") String invoicetype)
		{
			return sales_transportService.searchTransportStatement(fromdate,todate,invoicetype);
		}
		
		/************** End Sales Transport Report **************/
		
		
		@GetMapping("/getpoitemdetailsreport/{poid}")
		public List<Map<String, Object>> getpoitemdetailsreport(@PathVariable(value = "poid") String poid)
		{
			return pur_OrderService.getpoitemdetailsreport(poid);
		}
		
		
		@GetMapping("/getExecutionpo/{poid}")
		public List<Map<String, Object>> getExecutionpo(@PathVariable(value = "poid") String poid)
		{
			return pur_OrderService.getExecutionpo(poid);
		}
		
		/******************************************* Trnascationa report ******************************************************/
		
		@GetMapping("/gettransactionalReport/{fromdate}/{todate}/{catagory}")
		public String gettransactionalReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "catagory") String catagory)
		{
			
			String json="";
			try {
		 		ObjectMapper objectMapper = new ObjectMapper();

		 		 json = objectMapper.writeValueAsString(pur_BillService.gettransactionalReport(fromdate,todate,catagory));
		 		System.out.println("json: "+json);
		 		}
		 		catch(Exception e) {}
			//System.out.println();
			return json;
			//System.out.println(pur_BillService.gettransactionalReport(fromdate,todate,catagory));
			//return pur_BillService.gettransactionalReport(fromdate,todate,catagory);
		}
		
		
		@GetMapping("/getstocktrackingReport/{fromdate}/{todate}/{catagory}/{startdate}")
		public String getstocktrackingReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "catagory") String catagory,@PathVariable(value = "startdate") String startdate)
		{
			
			String json="";
			try {
		 		ObjectMapper objectMapper = new ObjectMapper();

		 		 json = objectMapper.writeValueAsString(pur_BillService.getstocktrackingReport(fromdate,todate,catagory,startdate));
		 		System.out.println("json: "+json);
		 		}
		 		catch(Exception e) {}
			//System.out.println();
			return json;
			//System.out.println(pur_BillService.gettransactionalReport(fromdate,todate,catagory));
			//return pur_BillService.gettransactionalReport(fromdate,todate,catagory);
		}
		
		
		
		
		@GetMapping("/getstocktrackingReport2/{fromdate}/{todate}/{catagory}/{startdate}")
		public List<Map<String, Object>> getstocktrackingReport2(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "catagory") String catagory,@PathVariable(value = "startdate") String startdate)
		{
			return pur_BillService.getstocktrackingReport2(fromdate,todate,catagory,startdate);
		}
		
		
		
		@GetMapping("/getalltransactionfromitem/{itemcode}/{packingcode}/{fromdate}/{todate}/{catagory}")
		public List<Map<String, Object>> getalltransactionfromitem(@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "packingcode") String packingcode,@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "catagory") String catagory)
		{
			return pur_BillService.getalltransactionfromitem(itemcode,packingcode,fromdate,todate,catagory);
		}
		
		/******************************************* Trnascationa report ends  ******************************************************/
	
		/*************************************************************Dailyweigher Starts here***************************************/
		
		@Autowired
		DailyweigherService dailyweigherService;
		
		@GetMapping("/getDailyweigherList")
		public List<Map<String,Object>> getDailyweigherList() 
		{
			  return dailyweigherService.getDailyweigherList();
		}
		
		@PostMapping("/createDailyweigher")
		public Dailyweigher createDailyweigher(@Valid @RequestBody Dailyweigher dailyweigher) {
			return dailyweigherService.save(dailyweigher);
		}
		
		@PutMapping("/updateDailyweigher/{id}")
		public ResponseEntity<Dailyweigher> updateDailyweigher(@PathVariable(value = "id") Long id,@Valid @RequestBody Dailyweigher dailyweigher) {
			Dailyweigher op = dailyweigherService.update(dailyweigher, id);
			return ResponseEntity.ok().body(op);
		}
		
		 @GetMapping("/retriveDailyweigher/{id}")
			public ResponseEntity<Dailyweigher> retriveDailyweigher(@PathVariable(value="id") Long id)
			{
			 Dailyweigher op=dailyweigherService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		 
		 @GetMapping("/retriveDailyweigherDetails/{dwg_id}")
			public List<Map<String,Object>> retriveDailyweigherDetails(@PathVariable(value = "dwg_id") String dwg_id)
			{
				return dailyweigherService.retriveDailyweigherDetails(dwg_id);
			}
			
		 @PutMapping("/deleteDailyweigher/{id}")
			public ResponseEntity<Dailyweigher> deleteDailyweigher(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Dailyweigher dailyweigher) {
			 Dailyweigher op = dailyweigherService.delete(dailyweigher, id);
				return ResponseEntity.ok().body(op);
			}
		 
		 @GetMapping("/getDailyWeigherReport/{wdate}/{machine}")
			public List<Map<String,Object>> getDailyWeigherReport(@PathVariable(value = "wdate") String wdate,@PathVariable(value = "machine") String machine)
			{
			 	String newmachine=machine.replace("%20"," ");
			 	System.out.println("CHECK : : "+newmachine);
			 	return dailyweigherService.getDailyWeigherReport(wdate,newmachine);
			}
		
	/*************************************************************Dailyweigher Ends here***************************************/
		 
	 /*************************************************************Solar Power Generation Starts here***************************************/
		@Autowired
		Solar_power_generationService solar_power_generationService;
		
		@GetMapping("/getSolarPowerGeneration/{company_id}")
		public List<Map<String,Object>> getSolarPowerGeneration(@PathVariable(value="company_id") String company_id) 
		{
			  return solar_power_generationService.getSolarPowerGeneration(company_id);
		}
		
		@PostMapping("/createSolarPowerGeneration")
		public Solar_power_generation createSolarPowerGeneration(@Valid @RequestBody Solar_power_generation solar_power_generation) {
			return solar_power_generationService.save(solar_power_generation);
		}
		
		@PutMapping("/updateSolarPowerGeneration/{id}")
		public ResponseEntity<Solar_power_generation> updateSolarPowerGeneration(@PathVariable(value = "id") Long id,@Valid @RequestBody Solar_power_generation solar_power_generation) {
			Solar_power_generation op = solar_power_generationService.update(solar_power_generation, id);
			return ResponseEntity.ok().body(op);
		}
		
		 @GetMapping("/retriveSolarPowerGeneration/{id}")
			public ResponseEntity<Solar_power_generation> retriveSolarPowerGeneration(@PathVariable(value="id") Long id)
			{
				Solar_power_generation op=solar_power_generationService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		 
		 @GetMapping("/getSolarPorGenReport/{bunit}/{fromdate}/{todate}")
			public List<Map<String,Object>> getSolarPorGenReport(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
			{
				return solar_power_generationService.getSolarPorGenReport(bunit,fromdate,todate);
			}
			
		 @PutMapping("/deleteSolarPowerGeneration/{id}")
			public ResponseEntity<Solar_power_generation> deleteSolarPowerGeneration(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Solar_power_generation solar_power_generation) {
				Solar_power_generation op = solar_power_generationService.delete(solar_power_generation, id);
				return ResponseEntity.ok().body(op);
			}
		
		@GetMapping("/getInverterSolarPowerGeneration/{bunit}/{fromdate}/{todate}")
		public List<Map<String,Object>> getInverterSolarPowerGeneration(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
		{
			return solar_power_generationService.getInverterSolarPowerGeneration(bunit,fromdate,todate);
		}
		
		@GetMapping("/getAllSolarData/{solar_date}")
		public List<Map<String,Object>> getAllSolarData(@PathVariable(value = "solar_date") String solar_date)
		{
			return solar_power_generationService.getAllSolarData(solar_date);
		}
		
		@GetMapping("/checkSolarPowerDate/{solardate}")
		public Status_table checkSolarPowerDate(@PathVariable(value = "solardate") String solardate)
		{
			return solar_power_generationService.checkSolarPowerDate(solardate);
		}
		
	/*************************************************************Solar Power Generation Ends here***************************************/
		
	/*************************************************************Solar Power Cut Starts here***************************************/
		
		@Autowired
		Solar_power_generation_with_powercutService solar_power_generation_with_powercutService;
		
		@GetMapping("/solarPowerGenerationWithPowerCutList/{compid}")
		public List<Map<String,Object>> solarPowerGenerationWithPowerCutList(@PathVariable(value = "compid") String compid) 
		{
			  return solar_power_generation_with_powercutService.solarPowerGenerationWithPowerCutList(compid);
		}
		
		@PostMapping("/createSolarPowerCut")
		public Solar_power_generation_with_powercut createSolarPowerCut(@Valid @RequestBody Solar_power_generation_with_powercut solar_power_generation_with_powercut) {
			return solar_power_generation_with_powercutService.save(solar_power_generation_with_powercut);
		}
		
		@PutMapping("/updateSolarPowerCut/{id}")
		public ResponseEntity<Solar_power_generation_with_powercut> updateSolarPowerCut(@PathVariable(value = "id") Long id,@Valid @RequestBody Solar_power_generation_with_powercut solar_power_generation_with_powercut) {
			Solar_power_generation_with_powercut op = solar_power_generation_with_powercutService.update(solar_power_generation_with_powercut, id);
			return ResponseEntity.ok().body(op);
		}
		
		 @GetMapping("/retriveSolarPowerCut/{id}")
			public ResponseEntity<Solar_power_generation_with_powercut> retriveSolarPowerCut(@PathVariable(value="id") Long id)
			{
				Solar_power_generation_with_powercut op=solar_power_generation_with_powercutService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		 
		 @GetMapping("/retriveSolarPowercutDetails/{powerid}")
			public List<Map<String,Object>> retriveSolarPowercutDetails(@PathVariable(value = "powerid") String powerid)
			{
				return solar_power_generation_with_powercutService.retriveSolarPowercutDetails(powerid);
			}
			
		 @PutMapping("/deleteSolarPowerCut/{id}")
			public ResponseEntity<Solar_power_generation_with_powercut> deleteSolarPowerCut(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Solar_power_generation_with_powercut solar_power_generation_with_powercut) {
				Solar_power_generation_with_powercut op = solar_power_generation_with_powercutService.delete(solar_power_generation_with_powercut, id);
				return ResponseEntity.ok().body(op);
			}
			
			@GetMapping("/getSolarPowerWithPowerCutList/{fromdate}/{todate}/{bunit}")
			public List<Map<String,Object>> getSolarPowerWithPowerCutList(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
			{
				System.out.println("bunit::"+bunit+"//"+fromdate+"//"+todate);
				return solar_power_generation_with_powercutService.getSolarPowerWithPowerCutList(bunit,fromdate,todate);
			}
		
			@GetMapping("/checkSolarPowerCut/{solardate}")
			public Status_table checkSolarPowerCut(@PathVariable(value = "solardate") String solardate)
			{
				return solar_power_generation_with_powercutService.checkSolarPowerCut(solardate);
			}
			
	/*************************************************************Solar Power Cut Ends here***************************************/
	
			@GetMapping("/getSalesCreditNoteReport/{fromdate}/{todate}/{invoicetype}")
			public List<Map<String, Object>> getSalesCreditNoteReport(@PathVariable(value = "fromdate") String fromdate,
															  @PathVariable(value = "todate") String todate,@PathVariable(value = "invoicetype") String invoicetype)
			{
				//System.out.println("fromdate"+fromdate+","+todate+","+invoicetype);
				return sales_credit_noteService.getSalesCreditNoteReport(fromdate,todate,invoicetype);
			}		
				
			
			@GetMapping("/getTrialdata/{fromdate}/{todate}")
			public List<Map<String,Object>> getTrialdata(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
			{
				//System.out.println("advice::"+advice);
				return sales_orderService.getTrialdata(fromdate,todate);
			}
			
	/************************************************************************************************************************************/
			
			@GetMapping("/getSalesInvoicetransitReport/{fromdate}/{todate}")
			public List<Map<String, Object>> getSalesInvoicetransitReport(@PathVariable(value = "fromdate") String fromdate,
															  @PathVariable(value = "todate") String todate)
			{
				return sales_invoiceService.getSalesInvoicetransitReport(fromdate,todate);
			}	
			
			@GetMapping("/getSupplierMasterReport")
			public List<Map<String, Object>> getSupplierMasterReport()
			{
				return supp_bussiness_partnerService.getSupplierMasterReport();
			}	
			
			@GetMapping("/getCustomerMasterReport")
			public List<Map<String, Object>> getCustomerMasterReport()
			{
				return cust_bussiness_partnerService.getCustomerMasterReport();
			}
			
			@GetMapping("/getSalesFreightReport/{fromdate}/{todate}/{invoicetype}")
			public List<Map<String, Object>> getSalesFreightReport(@PathVariable(value = "fromdate") String fromdate,
															  @PathVariable(value = "todate") String todate,@PathVariable(value = "invoicetype") String invoicetype)
			{
				return delivery_challanService.getSalesFreightReport(fromdate,todate,invoicetype);
			}
			
			@GetMapping("/getJobWorkAllocationReport/{fromdate}/{todate}")
			public List<Map<String, Object>> getJobWorkAllocationReport(@PathVariable(value = "fromdate") String fromdate,
															  @PathVariable(value = "todate") String todate)
			{
				return pur_good_receiptService.getJobWorkAllocationReport(fromdate,todate);
			}
			
			@GetMapping("/searchpendingUnAdviceReport/{fromdate}/{todate}")
			public List<Map<String, Object>> searchpendingUnAdviceReport(@PathVariable(value = "fromdate") String fromdate,
															  @PathVariable(value = "todate") String todate)
			{
				return wm_unload_adviceService.searchpendingUnAdviceReport(fromdate,todate);
			}
			
			@GetMapping("/searchpendingGRNReport/{fromdate}/{todate}")
			public List<Map<String, Object>> searchpendingGRNReport(@PathVariable(value = "fromdate") String fromdate,
															  @PathVariable(value = "todate") String todate)
			{
				return pur_good_receiptService.searchpendingGRNReport(fromdate,todate);
			}
			
			@GetMapping("/searchpendingDelvChallan/{fromdate}/{todate}")
			public List<Map<String, Object>> searchpendingDelvChallan(@PathVariable(value = "fromdate") String fromdate,
															  @PathVariable(value = "todate") String todate)
			{
				return delivery_challanService.searchpendingDelvChallan(fromdate,todate);
			}
			
			@GetMapping("/getWeighmentReportForAnujSir/{fromdate}/{todate}")
			public List<Map<String,Object>> getWeighmentReportForAnujSir(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
			{
				//System.out.println("advice::"+advice);
				return sales_orderService.getWeighmentReportForAnujSir(fromdate,todate);
			}
			
}
