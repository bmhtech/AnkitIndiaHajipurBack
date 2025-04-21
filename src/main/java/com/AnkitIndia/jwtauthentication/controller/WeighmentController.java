package com.AnkitIndia.jwtauthentication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.AnkitIndia.Utility.FileUtil;

import com.AnkitIndia.jwtauthentication.model.Driver_master;

import com.AnkitIndia.jwtauthentication.model.Tag_advice_with_po;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_wgmnt;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tag_advice_with_poDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tag_advice_with_po_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Item_Dtls_for_jobworkDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_driver_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_itm_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmntDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmnt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.security.services.Driver_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_good_receiptService;
import com.AnkitIndia.jwtauthentication.security.services.Tag_advice_with_poService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_loading_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_loading_wgmntService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_wgmntService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Terminatekata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class WeighmentController {
	
	/******************* Loading Advice Start*******************/
	
	@Autowired
	Wm_loading_adviceService wm_loading_adviceService;
	
	@Autowired
	Pur_good_receiptService pur_good_receiptService;
	
	
	@GetMapping("/getLoadingAdviceDataList/{currDate}/{finyear}")
	public List<Wm_loading_adviceDTO> getLoadingAdviceDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return wm_loading_adviceService.getLoadingAdviceDataList(currDate,finyear);
	}
	
	@GetMapping("/getLoadingAdviceDataListfast/{currDate}/{finyear}")
	public List<Map<String,Object>> getLoadingAdviceDataListfast(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return wm_loading_adviceService.getLoadingAdviceDataListfast(currDate,finyear);
	}
	@GetMapping("/getLASequenceId/{prefix}/{orderdate}/{type}")
	public SequenceIdDTO getLASequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "type") String type)
	{
		return wm_loading_adviceService.getLASequenceId(prefix,orderdate,type);
	}
	
	@GetMapping("/getLASequenceIdWarehouse/{prefix}/{orderdate}")
	public SequenceIdDTO getLASequenceIdWarehouse(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate)
	{
		return wm_loading_adviceService.getLASequenceIdWarehouse(prefix,orderdate);
	}
	
	/*@PostMapping("/createLoadingAdvice")
	public Wm_loading_advice save(@Valid @RequestBody Wm_loading_advice wm_loading_advice) 
	{
		return wm_loading_adviceService.save(wm_loading_advice);
	}
	*/
	
	@PostMapping("/createLoadingAdvice")
	public Wm_loading_advice createLoadingAdvice(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Wm_loading_advice wm_loading_advice=objectMapper.readValue(input, Wm_loading_advice.class);
		
		return wm_loading_adviceService.save(wm_loading_advice,files);
	}
	
	

	
	@GetMapping("/getLoadingAdvices")
	public List<Wm_loading_adviceDTO> findWm_loading_adviceAll()
	{
		//PageRequest pageRequest = PageRequest.of(0, 10);
		return wm_loading_adviceService.findAll();
	}
	
	
	
	 @GetMapping("/getLoadingAdvices_pagination/{page}/{size}")
	  public Page<Wm_loading_advice_Pagination_DTO> getLoadingAdvices_pagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return wm_loading_adviceService.getLoadingAdvices_pagination(page,size);
	}
	
	  @GetMapping(value = "/searchLoadingAdvice")
		public List<Wm_loading_advice_Pagination_DTO> searchLoadingAdvice(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String bus_partner1,@RequestParam(defaultValue = "") String finyear)
		 {
			return wm_loading_adviceService.searchLoadingAdvice(orderno,fromdate,todate,bus_partner1,finyear);
		 }
		
	 @GetMapping(value = "/searchLoadingAdviceFast")
		public List<Map<String,Object>> searchLoadingAdviceFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String bus_partner1,@RequestParam(defaultValue = "") String finyear)
		 {
			return wm_loading_adviceService.searchLoadingAdviceFast(orderno,fromdate,todate,bus_partner1,finyear);
		 }
		
	@GetMapping("/retriveLoadingAdvice/{id}")
	public ResponseEntity<Wm_loading_advice> retriveWm_loading_advice(@PathVariable(value="id") Long id)
	{
		Wm_loading_advice wla=wm_loading_adviceService.findOne(id);
		if(wla==null) {
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(wla);
		}
	}
	
	@PutMapping("/updateLoadingAdvice/{id}")
	public ResponseEntity<Wm_loading_advice> updateLoadingAdvice(@PathVariable(value="id") long id,@Valid @RequestBody Wm_loading_advice iMaster)
	{
		Wm_loading_advice op=wm_loading_adviceService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteLoadingAdvice/{id}")
	public ResponseEntity<Wm_loading_advice> deleteLoadingAdvice(@PathVariable(value="id") long id,@Valid @RequestBody Wm_loading_advice iMaster)
	{
		Wm_loading_advice op=wm_loading_adviceService.deleteLoadingAdvice(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getLoadingAdviceList")
	public ResponseEntity<List<Wm_loading_adviceDTO>> getLoadingAdviceList(@RequestParam("company") String company) 
	{
		List<Wm_loading_adviceDTO> val=wm_loading_adviceService.getLoadingAdviceList(company);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingDetails/{advice_id}")
	public ResponseEntity<Wm_loading_adviceDTO> getLoadingDetails(@PathVariable(value="advice_id") String advice_id) 
	{
		Wm_loading_adviceDTO val=wm_loading_adviceService.getLoadingDetails(advice_id);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingAdvThruVehicle/{vehicleid}/{adv_type}")
	public ResponseEntity<Wm_loading_adviceDTO> getLoadingAdvThruVehicle(@PathVariable(value="vehicleid") String vehicleid,@PathVariable(value="adv_type") String adv_type) 
	{
		Wm_loading_adviceDTO val=wm_loading_adviceService.getLoadingAdvThruVehicle(vehicleid,adv_type);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingAdviceStkTrans")
	public ResponseEntity<List<Wm_loading_adviceDTO>> getLoadingAdviceStkTrans() 
	{
		List<Wm_loading_adviceDTO> val=wm_loading_adviceService.getLoadingAdviceStkTrans();
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingAdvThruWeigh/{cdate}")
	public ResponseEntity<List<Wm_loading_adviceDTO>> getLoadingAdvicesThruWeigh(@PathVariable(value = "cdate") String cdate) 
	{
		List<Wm_loading_adviceDTO> val=wm_loading_adviceService.getLoadingAdvicesThruWeigh(cdate);
		if(val==null){
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadAdvThruWeighmentUpdate/{id}")
	public List<Wm_loading_adviceDTO> getLoadAdvThruWeighmentUpdate(@PathVariable(value= "id") Long id)
	{
		return wm_loading_adviceService.getLoadAdvThruWeighmentUpdate(id);
	}
	
	
	
	@GetMapping("/loadingItemRetriveListUpdate/{id}")
	public List<Wm_loading_advice_itm_dtls> loadingItemRetriveListUpdate(@PathVariable(value= "id") Long id)
	{
		return wm_loading_adviceService.loadingItemRetriveListUpdate(id);
	}
	
	@GetMapping("/getLoadingAdvThruWeigh")
	public ResponseEntity<List<Wm_loading_adviceDTO>> getLoadingAdvicesThruWeigh(@RequestParam(defaultValue = "") String cdate,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String inv_type) 
	{
		List<Wm_loading_adviceDTO> val=wm_loading_adviceService.getLoadingAdvicesThruWeigh(cdate,party,inv_type);
		if(val==null){
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingAdvPurRtn")
	public ResponseEntity<List<Wm_loading_adviceDTO>> getLoadingAdvPurRtn(@RequestParam(defaultValue = "") String prdate,@RequestParam(defaultValue = "") String supplier,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear) 
	{
		List<Wm_loading_adviceDTO> val=wm_loading_adviceService.getLoadingAdvPurRtn(prdate,supplier,company,finyear);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingVehiThruBU/{dbuint}/{cdate}")
	public ResponseEntity<List<Wm_loading_adviceDTO>> getLoadingVehiThruBU(@PathVariable(value = "dbuint") String dbuint,@PathVariable(value = "cdate") String cdate) 
	{
		List<Wm_loading_adviceDTO> val=wm_loading_adviceService.getLoadingVehiThruBU(dbuint,cdate);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/loadingAdviceVehicle/{code}")
	public Wm_loading_adviceDTO loadingAdviceVehicleList(@PathVariable(value = "code") String code)
	{
		return wm_loading_adviceService.loadingAdviceVehicleList(code);
	}
	
	@GetMapping("/getLoadingAdvBrokerDtls/{order_id}")
	public List<Wm_loading_advice_broker_dtlsDTO> getLoadingAdvBrokerDtls(@PathVariable(value = "order_id") String order_id)
	{
		return wm_loading_adviceService.getLoadingAdvBrokerDtls(order_id);
	}
	
	@GetMapping("/loadingItemjobworkRetriveList/{advice_id}")
	public List<Wm_loading_advice_Item_Dtls_for_jobworkDTO> loadingItemjobworkRetriveList(@PathVariable(value = "advice_id") String advice_id)
	{
		return wm_loading_adviceService.loadingItemjobworkRetriveList(advice_id);
	}
	
	@GetMapping("/getLoadingAdvPartyDtls/{advice_id}")
	public List<Wm_loading_advice_Party_DtlsDTO> getLoadingAdvPartyDtls(@PathVariable(value = "advice_id") String advice_id)
	{
		return wm_loading_adviceService.getLoadingAdvPartyDtls(advice_id);
	}
	
	@GetMapping("/getLoadingAdvShipDtls/{advice_id}")
	public Wm_loading_advice_Shipment_DtlsDTO getLoadingAdvShipDtls(@PathVariable(value = "advice_id") String advice_id)
	{
		return wm_loading_adviceService.getLoadingAdvShipDtls(advice_id);
	}
	
	@GetMapping("/getLoadingAdvTransinfo/{advice_id}")
	public Wm_loading_advice_trans_infoDTO getLoadingAdvTransinfo(@PathVariable(value = "advice_id") String advice_id)
	{
		return wm_loading_adviceService.getLoadingAdvTransinfo(advice_id);
	}
	
	@GetMapping("/getLoadingAdviceThruBUnit/{bunit}")
	public ResponseEntity<List<Wm_loading_adviceDTO>> getLoadingAdviceThruBUnit(@PathVariable(value = "bunit") String bunit) 
	{
		List<Wm_loading_adviceDTO> val=wm_loading_adviceService.getLoadingAdviceThruBUnit(bunit);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingAdvItemDtlsThruVehicle/{vehicle_id}/{adv_type}")
	public ResponseEntity<List<Wm_loading_advice_itm_dtlsDTO>> getLoadingAdvItemDtlsThruVehicle(@PathVariable(value = "vehicle_id") String vehicle_id,@PathVariable(value = "adv_type") String adv_type) 
	{
		List<Wm_loading_advice_itm_dtlsDTO> val=wm_loading_adviceService.getLoadingAdvItemDtlsThruVehicle(vehicle_id,adv_type);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingAdvDriverDtlsThruVehicle/{vehicle_id}/{adv_type}")
	public ResponseEntity<Wm_loading_advice_driver_dtlsDTO> getLoadingAdvDriverDtlsThruVehicle(@PathVariable(value = "vehicle_id") String vehicle_id,@PathVariable(value = "adv_type") String adv_type) 
	{
		Wm_loading_advice_driver_dtlsDTO val=wm_loading_adviceService.getLoadingAdvDriverDtlsThruVehicle(vehicle_id,adv_type);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getLoadingAdvTransInfoThruVehicle/{vehicle_id}/{adv_type}")
	public ResponseEntity<Wm_loading_advice_trans_infoDTO> getLoadingAdvTransInfoThruVehicle(@PathVariable(value = "vehicle_id") String vehicle_id,@PathVariable(value = "adv_type") String adv_type) 
	{
		Wm_loading_advice_trans_infoDTO val=wm_loading_adviceService.getLoadingAdvTransInfoThruVehicle(vehicle_id,adv_type);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	
	@GetMapping("/getLoadingAdviceReport/{fromdate}/{todate}/{finyear}")
    public List<Map<String, Object>> getLoadingAdviceReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "finyear") String finyear) 
	{
		return wm_loading_adviceService.getLoadingAdviceReport(fromdate,todate,finyear);
	}
	
	@GetMapping("/loadadvicejobworkRetriveList/{advid}")
    public List<Map<String, Object>> loadadvicejobworkRetriveList(@PathVariable(value = "advid") String advid) 
	{
		System.out.println("enter controller:"+advid);
		return wm_loading_adviceService.loadadvicejobworkRetriveList(advid);
	}
	
	@GetMapping("/checkAdviceinCash/{advicedate}/{party}/{ref_type}/{total_amt}")
	public StatusDTO checkAdviceinCash(@PathVariable(value = "advicedate") String advicedate,@PathVariable(value = "party") String party,
									   @PathVariable(value = "ref_type") String ref_type,@PathVariable(value = "total_amt") double total_amt)
	{
		return wm_loading_adviceService.checkAdviceinCash(advicedate,party,ref_type,total_amt);
	}
	
	@GetMapping("/checkAdviceinCashUpdate/{advicedate}/{party}/{ref_type}/{total_amt}/{advice_id}")
	public StatusDTO checkAdviceinCashUpdate(@PathVariable(value = "advicedate") String advicedate,@PathVariable(value = "party") String party,
									   @PathVariable(value = "ref_type") String ref_type,@PathVariable(value = "total_amt") double total_amt,@PathVariable(value = "advice_id") String advice_id)
	{
		return wm_loading_adviceService.checkAdviceinCashUpdate(advicedate,party,ref_type,total_amt,advice_id);
	}
	
	@GetMapping("/custPayment/{advicedate}/{party}/{ref_type}")
	public StatusDTO custPayment(@PathVariable(value = "advicedate") String advicedate,@PathVariable(value = "party") String party,
								 @PathVariable(value = "ref_type") String ref_type)
	{
		return wm_loading_adviceService.custPayment(advicedate,party,ref_type);
	}
	
	/******************* Loading Advice End*******************/
	
	/******************* Unload Advice ******************/
	
	@Autowired
	Wm_unload_adviceService wm_unload_adviceService;
	
	@GetMapping("/getUnloaDataList/{currDate}/{finyear}")
	public List<Wm_unload_adviceDTO> getUnloaDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return wm_unload_adviceService.getUnloaDataList(currDate,finyear);
	}
	
	@GetMapping("/getUnloaDataListfastapi/{currDate}/{finyear}")
	public List<Map<String,Object>> getUnloaDataListfastapi(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return wm_unload_adviceService.getUnloaDataListfastapi(currDate,finyear);
	}
	
	@GetMapping(value = "/searchUnloadAdvice")
	public List<Map<String,Object>> getSearchUnloadAdvice(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String bus_partner1,@RequestParam(defaultValue = "") String finyear)
	 {
		return wm_unload_adviceService.getSearchUnloadAdvice(orderno,fromdate,todate,bus_partner1,finyear);
	 }
	
	
	/*@GetMapping("/getUnloaDataListNew/{currDate}/{finyear}")
	public List<Map<String,Object>> getUnloaDataListNew(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return wm_unload_adviceService.getUnloaDataListNew(currDate,finyear);
	}*/
	
	@GetMapping("/getUASequenceId/{bunit}/{orderdate}")
	public SequenceIdDTO getUASequenceId(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "orderdate") String orderdate)
	{
		return wm_unload_adviceService.getUASequenceId(bunit,orderdate);
	}
	
	@GetMapping("/getUASequenceIdnew/{bunit}/{orderdate}")
	public SequenceIdDTO getUASequenceIdnew(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "orderdate") String orderdate)
	{
		return wm_unload_adviceService.getUASequenceIdnew(bunit,orderdate);
	}
	
/*	@PostMapping("/createUnloadAdvice")
	public ResponseEntity<Wm_unload_advice> save(@Valid @RequestBody Wm_unload_advice unload_advice)
	{
		wm_unload_adviceService.save(unload_advice);
		 
		return new ResponseEntity<Wm_unload_advice>(unload_advice, HttpStatus.OK);
	}*/
	
	@PostMapping("/createUnloadAdvice")
	public Wm_unload_advice createUnloadAdvice(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Wm_unload_advice unload_advice=objectMapper.readValue(input, Wm_unload_advice.class);
		//	public Wm_unload_wgmnt save(Wm_unload_wgmnt wgmnt,MultipartFile files[]) throws IOException;
		return wm_unload_adviceService.save(unload_advice,files);
	}
	
	
	@GetMapping("/getUnloadAdvice")
	public List<Wm_unload_advice> getUnloadAdviceAll()
	{
		return wm_unload_adviceService.findAll();
	}
	
	
	@GetMapping("/getUnloadAdvicePagination/{page}/{size}")
	public Page<Wm_unload_advice_Pagination_DTO> getUnloadAdvicePagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return wm_unload_adviceService.getUnloadAdvicePagination(page,size);
	}
	  
  /*@GetMapping(value = "/searchUnloadAdvice")
	public List<Wm_unload_advice_Pagination_DTO> searchUnloadAdvice(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String bus_partner1,@RequestParam(defaultValue = "") String finyear)
	 {
		return wm_unload_adviceService.searchUnloadAdvice(orderno,fromdate,todate,bus_partner1,finyear);
	 }*/
		
	
	  
	@PutMapping("/updateUnloadAdvice/{id}")
	public ResponseEntity<Wm_unload_advice> updateUnloadAdvice(@PathVariable(value="id") long id,@Valid @RequestBody Wm_unload_advice wUnload_advice)
	{
		Wm_unload_advice op=wm_unload_adviceService.update(wUnload_advice,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getunloadfromreturnid/{salereturnid}")
	public Wm_unload_advice getunloadfromreturnid(@PathVariable(value= "salereturnid") String salereturnid)
	{
		return wm_unload_adviceService.getunloadfromreturnid(salereturnid);
	}
	
	@GetMapping(value = "/retriveUnloadAdviceJobwork/{unadviceid}")
	 public List<Map<String, Object>> retriveUnloadAdviceJobwork(@PathVariable(value = "unadviceid") String unadviceid) 
	{
		  return wm_unload_adviceService.retriveUnloadAdviceJobwork(unadviceid);
	}
	
	@GetMapping(value = "/getSalesReturnNoteJobwork/{advdate}/{bunit}/{party}")
	 public List<Map<String, Object>> getSalesReturnNoteJobwork(@PathVariable(value = "advdate") String advdate,
			 	@PathVariable(value = "bunit") String bunit,@PathVariable(value = "party") String party) 
	{
		  return wm_unload_adviceService.getSalesReturnNoteJobwork(advdate,bunit,party);
	}
	
	@GetMapping("/unloadadvicejobworkRetriveList/{unadviceid}")
    public List<Map<String, Object>> unloadadvicejobworkRetriveList(@PathVariable(value = "unadviceid") String unadviceid) 
	{
		//System.out.println("enter controller:"+advid);
		return wm_unload_adviceService.unloadadvicejobworkRetriveList(unadviceid);
	}
	
/*	@Autowired
	Pur_OrderService pur_OrderService;
	@GetMapping("/gettotalqtyanduom/{pur_orderid}")
	public List<Pur_Order> gettotalqtyanduom(@PathVariable(value = "pur_orderid") String pur_orderid)
	{
		return pur_OrderService.findAll();
	}*/
	
	/*********** End Unload Advice ******************/
	
	/*********** Unload Weightment ******************/
	
	@Autowired
	Wm_unload_wgmntService wm_unload_wgmntService;
	
	@GetMapping("/getWeighmentDataList/{currDate}/{finyear}")
	public List<Wm_unload_wgmntDTO> getWeighmentDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return wm_unload_wgmntService.getWeighmentDataList(currDate,finyear);
	}
	
	@GetMapping("/getWeighmentDataFastList/{currDate}")
	public List<Map<String,Object>> getWeighmentDataFastList(@PathVariable(value = "currDate") String currDate)
	{
		return wm_unload_wgmntService.getWeighmentDataFastList(currDate);
	}
	
	@GetMapping("/getOtherWeighmentDataList/{currDate}/{finyear}")
	public List<Map<String,Object>> getOtherWeighmentDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return wm_unload_wgmntService.getOtherWeighmentDataList(currDate,finyear);
	}
	
	@GetMapping("/getNopartyList")
	public List<Map<String,Object>> getNopartyList()
	{
		return wm_unload_wgmntService.getNopartyList();
	}
	
	@GetMapping("/getFirstData")
	public Map<String,Object> getFirstData()
	{
		return wm_unload_wgmntService.getFirstData();
	}
	
	@GetMapping("/getOtherWgFirstData/{vehicleid}")
	public Map<String,Object> getOtherWgFirstData(@PathVariable(value = "vehicleid") String vehicleid)
	{
		return wm_unload_wgmntService.getOtherWgFirstData(vehicleid);
	}
	
	@GetMapping("/getOtherWgFirstDataWtWgtFor/{vehicleid}")
	public Map<String,Object> getOtherWgFirstDataWtWgtFor(@PathVariable(value = "vehicleid") String vehicleid)
	{
		return wm_unload_wgmntService.getOtherWgFirstDataWtWgtFor(vehicleid);
	}
	
	@GetMapping("/getWeighmentSequenceId/{prefix}/{orderdate}/{weight}")
	public SequenceIdDTO getWeighmentSequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "weight") String weight)
	{
		return wm_unload_wgmntService.getWeighmentSequenceId(prefix,orderdate,weight);
	}
	
	@GetMapping("/getWeighmentSequenceIdnew/{prefix}/{orderdate}/{weight}/{unadviceid}")
	public SequenceIdDTO getWeighmentSequenceIdnew(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,
			@PathVariable(value = "weight") String weight,@PathVariable(value = "unadviceid") String unadviceid)
	{
		return wm_unload_wgmntService.getWeighmentSequenceIdnew(prefix,orderdate,weight,unadviceid);
	}
	
	@GetMapping("/getWeighmentSequenceIdnewloading/{prefix}/{orderdate}/{weight}/{adviceid}")
	public SequenceIdDTO getWeighmentSequenceIdnewloading(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,
			@PathVariable(value = "weight") String weight,@PathVariable(value = "adviceid") String adviceid)
	{
		return wm_unload_wgmntService.getWeighmentSequenceIdnewloading(prefix,orderdate,weight,adviceid);
	}
	
	@GetMapping("/getWeighmentNumber/{vehicle}/{orderdate}")
	public SequenceIdDTO getWeighmentNumber(@PathVariable(value = "vehicle") String vehicle,@PathVariable(value = "orderdate") String orderdate)
	{
		System.out.println("vehicle:"+vehicle+"//"+orderdate);
		return wm_unload_wgmntService.getWeighmentNumber(vehicle,orderdate);
	}
	
	
	@PostMapping("/terminatekata")
	public Terminatekata terminatekata(@Valid @RequestBody Terminatekata terminatekata)
	{
		return wm_unload_wgmntService.terminatekata(terminatekata);
	}
	
	@GetMapping("/getOtherWgnmtList")
	public List<Map<String,Object>> getOtherWgnmtList()
	{
		return wm_unload_wgmntService.getOtherWgnmtList();
	}
	
	/*@PostMapping("/createUnloadWeightment")
	public ResponseEntity<Wm_unload_wgmnt> save(@Valid @RequestBody Wm_unload_wgmnt unload_wgmnt)
	{
		wm_unload_wgmntService.save(unload_wgmnt);
		 
		return new ResponseEntity<Wm_unload_wgmnt>(unload_wgmnt, HttpStatus.OK);
	}*/
	
	@PostMapping("/createUnloadWeightment")
	public Wm_unload_wgmnt createUnloadWeightment(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Wm_unload_wgmnt unload_wgmnt=objectMapper.readValue(input, Wm_unload_wgmnt.class);
		
		return wm_unload_wgmntService.save(unload_wgmnt,files);
	}
	
	@PostMapping("/createWeightment")
	public Wm_unload_wgmnt createWeightment(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Wm_unload_wgmnt unload_wgmnt=objectMapper.readValue(input, Wm_unload_wgmnt.class);
		
		return wm_unload_wgmntService.otherSave(unload_wgmnt,files);
	}
	
	
	@GetMapping("/getUnloadWeightments")
	public List<Wm_unload_wgmnt> getUnloadWeightmentAll()
	{
		return wm_unload_wgmntService.findAll();
	}
	
	
	@GetMapping("/getUnloadWeightments_pagination/{page}/{size}")
	public Page<Wm_unload_wgmnt_Pagination_DTO> getUnloadWeightments_pagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size)
	{
		return wm_unload_wgmntService.getUnloadWeightments_pagination(page,size);
	}
	
  @GetMapping(value = "/searchWeighment")
	public List<Wm_unload_wgmnt_Pagination_DTO> searchWeighment(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String customer_name1,@RequestParam(defaultValue = "") String supplier_name1,
			@RequestParam(defaultValue = "") String finyear)
	 {
		return wm_unload_wgmntService.searchWeighment(orderno,fromdate,todate,customer_name1,supplier_name1,finyear);
	 }
			
  @GetMapping(value = "/searchWeighmentFast")
	public List<Map<String,Object>> searchWeighmentFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String customer_name1,@RequestParam(defaultValue = "") String supplier_name1,
			@RequestParam(defaultValue = "") String finyear)
	 {
		return wm_unload_wgmntService.searchWeighmentFast(orderno,fromdate,todate,customer_name1,supplier_name1,finyear);
	 }
  
   @GetMapping(value = "/searchOtherWeighmentFast")
	public List<Map<String,Object>> searchOtherWeighmentFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String nopartyname1,
			@RequestParam(defaultValue = "") String finyear)
	 {
		return wm_unload_wgmntService.searchOtherWeighmentFast(orderno,fromdate,todate,nopartyname1,finyear);
	 }
   
   @GetMapping("/checkLooseItem/{adviceid}")
   public StatusDTO checkLooseItem(@PathVariable(value = "adviceid") String adviceid)
	{
		return wm_unload_wgmntService.checkLooseItem(adviceid);
	}
   
	/*********** End Unload Weightment ******************/
	
	/******************* Loading Weightment Start*******************/
	@Autowired
	Wm_loading_wgmntService wm_loading_wgmntService;
	
	@PostMapping("/createLoadingWtmnt")
	public Wm_loading_wgmnt save(@Valid @RequestBody Wm_loading_wgmnt wm_loading_wgmnt) 
	{
		return wm_loading_wgmntService.save(wm_loading_wgmnt);
	}
	
	
	@GetMapping("/getLoadingWtmnts")
	public List<Wm_loading_wgmnt> findWm_loading_wgmntAll()
	{
		return wm_loading_wgmntService.findAll();
	}
	
	@GetMapping("/retriveLoadingWtmnt/{id}")
	public ResponseEntity<Wm_loading_wgmnt> retriveWm_loading_wgmnt(@PathVariable(value="id") Long id)
	{
		Wm_loading_wgmnt wlw=wm_loading_wgmntService.findOne(id);
		if(wlw==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(wlw);
		}
	}
	
	/******************* Loading Weightment End*******************/
	
	/**************** Driver Master ****************/

	@Autowired
	Driver_masterService driver_masterService;
	
	/*@PostMapping("/createDriver")
	public Driver_master createDriver(@RequestParam("files") MultipartFile[] files,@RequestParam("InputData") String inptData) throws JsonParseException,JsonMappingException,IOException
	{
		Driver_master dMaster=new ObjectMapper().readValue(inptData, Driver_master.class);
		
		return driver_masterService.save(files,dMaster);
	}*/
	
	@PostMapping("/createDriver")
	public ResponseEntity<Driver_master> createDriver(@Valid @RequestBody Driver_master dMaster)
	{
		driver_masterService.save(dMaster);
		 
		return new ResponseEntity<Driver_master>(dMaster, HttpStatus.OK);
	}
	
	
	@PostMapping("/createDriverpopup")
	public Driver_master save(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input +" / " + files.length);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Driver_master dMaster=objectMapper.readValue(input, Driver_master.class);
		
		return driver_masterService.savepopup(files,dMaster);
	}
	
	
	
	@GetMapping("/retrieveDriver/{id}")
	public ResponseEntity<Driver_master> retrieveDriver(@PathVariable(value="id") Long id)
	{
		Driver_master wla = driver_masterService.findOne(id);
		if(wla==null) {
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(wla);
		}
	}
	//deleteDriver
	@PutMapping("/deleteDriver/{id}")
	public ResponseEntity<Driver_master> deleteDriver(@PathVariable(value="id") long id,@Valid @RequestBody Driver_master dMaster)
	{
		Driver_master op=driver_masterService.deleteDriver(dMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@PostMapping("/updateDriverpopup")
	public Driver_master update(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input +" / " + files.length);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Driver_master dMaster=objectMapper.readValue(input, Driver_master.class);
		
		return driver_masterService.update(files,dMaster);
	}
	
	
	
	@GetMapping("/getdriverimage/{doc_img}")
    public ResponseEntity<?> getProfileImage(@PathVariable(value = "doc_img")  String doc_img) {        
        try {
            
        	
        	//Path imagePath = Paths.get("D:/AnkitIndiaDriverDocuments/"+doc_img);
        	Path imagePath = Paths.get("usr/documents/driverdocs/"+doc_img);	//Online Aayog
            
            if (imagePath != null) {
           
                
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));
                
                return ResponseEntity
                        .ok()
                        .contentLength(imagePath.toFile().length())
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);                    
            }
            else 
            {
               
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	
	
	@GetMapping("/getDrivers")
	public List<Driver_master> getDrivers()
	{
		return driver_masterService.findAll();
	}
	
	@GetMapping( value = "/getDocImage/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable(value = "filename") String filename) throws IOException 
	{
		//String filePath = "E:\\work\\"+filename+".jpg";
		String filePath = "/usr/Images/AnkitIndiaImages/"+filename+".jpg";
		
	    FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally { 
        	if (fileInputStream != null) {try {fileInputStream.close();} catch (IOException e) {e.printStackTrace();}}
        }
        return bytesArray;
	}
	
	/**************** End Driver Master ****************/
	
	@Autowired
	Tag_advice_with_poService tag_advice_with_poService;
	
	@GetMapping("/getTagAdvPOSequenceId/{prefix}")
	public SequenceIdDTO getTagAdvPOSequenceId(@PathVariable(value = "prefix") String prefix)
	{
		return tag_advice_with_poService.getTagAdvPOSequenceId(prefix);
	}
	
	@GetMapping("/getTagAdviceWithPoList/{currDate}/{finyear}")
	public List<Map<String, Object>> getTagAdviceWithPoList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return tag_advice_with_poService.getTagAdviceWithPoList(currDate,finyear);
	}
	
	
	@PostMapping("/createTagAdvWithPO")
	public ResponseEntity<Tag_advice_with_po> save(@Valid @RequestBody Tag_advice_with_po tWith_po)
	{
		tag_advice_with_poService.save(tWith_po);
		 
		return new ResponseEntity<Tag_advice_with_po>(tWith_po, HttpStatus.OK);
	}
	
	@GetMapping("/getTagAdvWithPO")
	public List<Tag_advice_with_poDTO> getTagAdvWithPO(@RequestParam String company)
	{
		return tag_advice_with_poService.getTagAdvWithPO(company);
	}
	
	
	
	@PutMapping("/deleteTagadvice/{id}")
	public ResponseEntity<Tag_advice_with_po> deleteTagAdvWithPO(@PathVariable(value="id") long id,@Valid @RequestBody Tag_advice_with_po tag_advice_with_po)
	{
		//System.out.println("Avijit");
		Tag_advice_with_po op=tag_advice_with_poService.deleteTagAdvWithPO(tag_advice_with_po,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteUnloadAdvice/{id}")
	public ResponseEntity<Wm_unload_advice> deleteUnloadAdvice(@PathVariable(value="id") long id,@Valid @RequestBody Wm_unload_advice unloadid)
	{
		Wm_unload_advice op=wm_unload_adviceService.deleteUnloadAdvice(unloadid,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping(value = "/getUnloadAdviceData/{unloadid}")
	public Wm_unload_advice getUnloadAdviceData(@PathVariable(value = "unloadid") String unloadid) {
		return wm_unload_adviceService.getUnloadAdviceData(unloadid);
	}
	
	
	@GetMapping("/gettaggedadvice_pagination/{page}/{size}")
	  public Page<Tag_advice_with_po_Pagination_DTO> gettaggedadvice_pagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  
		  return tag_advice_with_poService.gettaggedadvice_pagination(page,size);
	}
	
	  @GetMapping(value = "/searchtaggedadvice")
		public List<Tag_advice_with_po_Pagination_DTO> searchtaggedadvice(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String pono,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String bus_partner1,@RequestParam(defaultValue = "") String finyear)
		 {
			return tag_advice_with_poService.searchtaggedadvice(orderno,pono,fromdate,todate,bus_partner1,finyear);
		 }
	
	@GetMapping(value = "/searchtaggedadviceFast")
	public List<Map<String,Object>> searchtaggedadviceFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String pono,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String bus_partner1,@RequestParam(defaultValue = "") String finyear)
	 {
		return tag_advice_with_poService.searchtaggedadviceFast(orderno,pono,fromdate,todate,bus_partner1,finyear);
	 }
		
	/*		Kata Image Capture Starts			*/
	@GetMapping("/getSecondkataSrlnoCamera/{bridge_location}")
	public SequenceIdDTO getSecondkataSrlnoCamera(@PathVariable(value = "bridge_location")  String bridge_location) throws IOException
	{
		return wm_unload_wgmntService.getSecondkataSrlnoCamera(bridge_location);
	}
	
	@GetMapping("/getKataImg/{bridge_location}/{doc_img}")
    public ResponseEntity<?> getInnerImg(@PathVariable(value = "bridge_location")  String bridge_location, @PathVariable(value = "doc_img")  String doc_img) {        
        try {
        	
        	Path imagePath = null;
        	System.out.println("bridge_location:: "+bridge_location);
        	if(bridge_location.compareToIgnoreCase("Weight Bridge 1") == 0) {
        		System.out.println("bridge_location IF:: "+bridge_location);
        		imagePath = Paths.get(FileUtil.folderPathKata1Img+doc_img);
        	}
        	if(bridge_location.compareToIgnoreCase("Weight Bridge 2") == 0) {
        		System.out.println("bridge_location IF:: "+bridge_location);
        		imagePath = Paths.get(FileUtil.folderPathKata2Img+doc_img);
        	}
        	
			if (imagePath != null) {
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));
                
                return ResponseEntity
                        .ok()
                        .contentLength(imagePath.toFile().length())
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);                    
            }
            else 
            {
       
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	/*		Kata Image Capture Ends			*/
	
}
