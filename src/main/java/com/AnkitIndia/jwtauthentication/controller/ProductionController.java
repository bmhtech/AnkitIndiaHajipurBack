package com.AnkitIndia.jwtauthentication.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Bom_master;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Prodsummary;
import com.AnkitIndia.jwtauthentication.model.Production_planning;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls_static;
import com.AnkitIndia.jwtauthentication.model.Production_transaction;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_input_popup_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_output_popup_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_spl;
import com.AnkitIndia.jwtauthentication.repository.Process_master_docRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_ListDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_shiftDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_shift_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planningDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_periodic_date_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shiftDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shift_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shift_timeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shop_floor_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_special_date_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_special_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_up;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TimeCalculationDTO;
import com.AnkitIndia.jwtauthentication.security.services.Bom_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Process_masterService;
import com.AnkitIndia.jwtauthentication.security.services.ProdsummaryService;
import com.AnkitIndia.jwtauthentication.security.services.Production_planningService;
import com.AnkitIndia.jwtauthentication.security.services.Production_planning_shop_floor_dtls_staticService;
import com.AnkitIndia.jwtauthentication.security.services.Production_planning_special_dtls_staticService;
import com.AnkitIndia.jwtauthentication.security.services.Production_transactionService;
import com.AnkitIndia.jwtauthentication.security.services.Production_transaction_splService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_listDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_masterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_output_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_resource_costDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_output_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_spl_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_spl_output_detailsDTO;
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
public class ProductionController {
	
	/************************** Start Process_master **************************/
	@Autowired
	Process_masterService process_masterService;
	
	@GetMapping("/getPMSequenceId")
	public ResponseEntity<SequenceIdDTO> getPMSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = process_masterService.getPMSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getShiftTime/{sstime}/{shifthrs}")
	public TimeCalculationDTO getShiftTime(@PathVariable(value = "sstime") String sstime,@PathVariable(value = "shifthrs") int shifthrs)
	{
		return process_masterService.getShiftTime(sstime,shifthrs);
	}
	
	/*@PostMapping("/createProcess")
	public Process_master save(@Valid @RequestBody Process_master pMaster) {
		return process_masterService.save(pMaster);
	}*/
	@PostMapping("/createProcess")
	public Process_master save(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Process_master pMaster=objectMapper.readValue(input, Process_master.class);
		
		return process_masterService.save(pMaster,files);
	}
	
	
	//deleteProcessMaster
	@PutMapping("/deleteProcessMaster/{id}")
	public ResponseEntity<Process_master> deletePurchaseOrder(@PathVariable(value = "id") Long id,
			@Valid @RequestBody  Process_master pMaster) {
		Process_master op = process_masterService.deleteProcessMaster(pMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findAllProcess")
	public List<Process_master> findAllProcess() {
		return process_masterService.findAllProcess();
	}
	@GetMapping("/findAllProcessList")
	public List<Process_master_ListDTO> findAllProcessList() {
		return process_masterService.findAllProcessList();
	}
	
	
	@GetMapping("/getProcess")
	public List<Process_masterDTO> getProcess() {
		return process_masterService.getProcess();
	}
	
	@GetMapping("/getProcessThruBUShopFloor")
	public List<Process_masterDTO> getProcessThruBUShopFloor(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String company) {
		System.out.println("bunit:"+bunit+"//"+sfloor+"//"+company);
		return process_masterService.getProcessThruBUShopFloor(bunit,sfloor,company);
	}
	
	@GetMapping("/getProcessThruBUShopFloorRegular")
	public List<Process_masterDTO> getProcessThruBUShopFloorRegular(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String company) {
		return process_masterService.getProcessThruBUShopFloorRegular(bunit,sfloor,company);
	}
	
	@GetMapping("/getProcessThruBUShopFloorSpl")
	public List<Process_masterDTO> getProcessThruBUShopFloorSpl(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String company) {
		return process_masterService.getProcessThruBUShopFloorSpl(bunit,sfloor,company);
	}
	
	@GetMapping("/getProcessThruBUSFProDesc")
	public Process_masterDTO getProcessThruBUSFProDesc(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String pdesc,@RequestParam(defaultValue = "") String company) {
		return process_masterService.getProcessThruBUSFProDesc(bunit,sfloor,pdesc,company);
	}
	
	@GetMapping("/retriveProcess/{id}")
	public ResponseEntity<Process_master> retriveProcess(@PathVariable(value = "id") Long id) {
		Process_master op = process_masterService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/getProcessDocs/{pid}")
	public List<Process_master_docDTO> getProcessDocs(@PathVariable(value = "pid") String pid)
	{
		return process_masterService.getProcessDocs(pid);
	}
	
	
	@GetMapping("/getdocumentListwithfile/{doc_pdf}")
	public List<Process_master_docDTO> getdocumentListwithfile(@PathVariable(value = "doc_pdf") String doc_pdf)
	{
		return process_masterService.getdocumentListwithfile(doc_pdf);
	}
	
	@GetMapping("/getProcessShift/{pid}")
	public List<Process_master_shiftDTO> getProcessShift(@PathVariable(value = "pid") String pid)
	{
		return process_masterService.getProcessShift(pid);
	}
	
	@GetMapping("/getProcessShiftThruDate/{pid}/{sdate}")
	public List<Process_master_shift_infoDTO> getProcessShiftThruDate(@PathVariable(value = "pid") String pid,@PathVariable(value = "sdate") String sdate)
	{
		return process_masterService.getProcessShiftThruDate(pid,sdate);
	}
	
/*	@PutMapping("/updateProcess/{id}")
	public ResponseEntity<Process_master> updateProcess(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Process_master pMaster) {
		Process_master op = process_masterService.update(pMaster, id);
		return ResponseEntity.ok().body(op);
	}*/
	
	
	/*@PutMapping("/updateProcess")
	public ResponseEntity<Process_master> updateProcess(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {

		
		
		Process_master pMaster=new ObjectMapper().readValue(input, Process_master.class);
		Process_master ob = process_masterService.update(pMaster,files);
		return ResponseEntity.ok().body(ob);
	}*/
	@Autowired
	Process_master_docRepository process_master_docRepository;
	
	
	
	
	
	//getdeletefileSystem
	
	
	@DeleteMapping("/getdeletefileSystem/{id}")
	public ResponseEntity<Process_master_doc> getdeletefileSystem(@PathVariable(value = "id") long id) {
		Process_master_doc docpdf = process_masterService.findOneprocessdoc(id);
		if (docpdf == null) {
			return ResponseEntity.notFound().build();
		} else {
			process_masterService.deleteProcessDocument(docpdf);
			return ResponseEntity.ok().build();
		}
	}
	
	
	
	
	
	
	
	@PostMapping("/updateProcess")
	public Process_master updateProcess(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Process_master pMaster=objectMapper.readValue(input, Process_master.class);
		
		return process_masterService.update(pMaster,files);
	}
	
	
	
	/*@PutMapping("/updateCompanyBusiness")
	public ResponseEntity<Company_business_unit_master> updateCompanyBusiness(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException{
		Company_business_unit_master cUnit_master=new ObjectMapper().readValue(input, Company_business_unit_master.class);
		Company_business_unit_master ob = companyBusinessUnitMasterService.update(cUnit_master,files);
		return ResponseEntity.ok().body(ob);
	}
	*/
	
	
	
	//
	/*@PostMapping("/createProcess")
	public Process_master save(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Process_master pMaster=objectMapper.readValue(input, Process_master.class);
		
		return process_masterService.save(pMaster,files);
	}
	*/
	/************************** End Process_master **************************/
	
	/************************** Start Bom_master **************************/
	@Autowired
	Bom_masterService bom_masterService;
	
	@GetMapping("/getBMSequenceId")
	public ResponseEntity<SequenceIdDTO> getBMSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = bom_masterService.getBMSequenceId(prefix,bunit,sfloor,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createBom")
	public Bom_master createBom(@Valid @RequestBody Bom_master bom_master) {
		return bom_masterService.save(bom_master);
	}
	
	@GetMapping("/findAllBom")
	public List<Bom_master> findAllBom() {
		return bom_masterService.findAllBom();
	}
	
	@GetMapping("/findAllBomList")
	public List<Bom_listDTO> findAllBomList() {
		return bom_masterService.findAllBomList();
	}
	
	@GetMapping("/getBoms")
	public List<Bom_masterDTO> getBoms() {
		return bom_masterService.getBoms();
	}
	
	@GetMapping("/getBomThruProcess")
	public List<Bom_masterDTO> getBomThruProcess(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String company) {
		return bom_masterService.getBomThruProcess(bunit,sfloor,process,company);
	}
	
	@GetMapping("/getBomDetails")
	public Bom_masterDTO getBomDetails(@RequestParam(defaultValue = "") String bomid,@RequestParam(defaultValue = "") String company) {
		return bom_masterService.getBomDetails(bomid,company);
	}
	
	@GetMapping("/retriveBom/{id}")
	public ResponseEntity<Bom_master> retriveBom(@PathVariable(value = "id") Long id) {
		Bom_master op = bom_masterService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateBom/{id}")
	public ResponseEntity<Bom_master> updateBom(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Bom_master bom_master) {
		Bom_master op = bom_masterService.update(bom_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getBomInputDetails/{prodid}")
	public List<Bom_input_detailsDTO> getBomInputDetails(@PathVariable(value = "prodid") String prodid)
	{
		return bom_masterService.getBomInputDetails(prodid);
	}
	
	@GetMapping("/getBomInputDetailsNew/{prodid}")
	public List<Map<String,Object>> getBomInputDetailsNew(@PathVariable(value = "prodid") String prodid)
	{
		return bom_masterService.getBomInputDetailsNew(prodid);
	}
	
	/*@GetMapping("/getBomInputDtls/{prodid}/{itemid}")
	public Bom_input_detailsDTO getBomInputDtls(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "itemid") String itemid)
	{
		return bom_masterService.getBomInputDtls(prodid,itemid);
	}*/
	@GetMapping("/getBomInputDtls/{prodid}/{itemid}/{packingid}")
	public Bom_input_detailsDTO getBomInputDtls(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "itemid") String itemid,@PathVariable(value = "packingid") String packingid)
	{
		return bom_masterService.getBomInputDtls(prodid,itemid,packingid);
	}
	
	@GetMapping("/getBomOutputDetails/{prodid}")
	public List<Bom_output_detailsDTO> getBomOutputDetails(@PathVariable(value = "prodid") String prodid)
	{
		return bom_masterService.getBomOutputDetails(prodid);
	}
	
	/*@GetMapping("/getBomOutputDtls/{prodid}/{itemid}")
	public Bom_output_detailsDTO getBomOutputDtls(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "itemid") String itemid)
	{
		return bom_masterService.getBomOutputDtls(prodid,itemid);
	}*/
	
	@GetMapping("/getBomOutputDtls/{prodid}/{itemid}/{packingid}")
	public Bom_output_detailsDTO getBomOutputDtls(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "itemid") String itemid,@PathVariable(value = "packingid") String packingid)
	{
		return bom_masterService.getBomOutputDtls(prodid,itemid,packingid);
	}
	
	@GetMapping("/getBomResourceCost/{prodid}")
	public List<Bom_resource_costDTO> getBomResourceCost(@PathVariable(value = "prodid") String prodid)
	{
		return bom_masterService.getBomResourceCost(prodid);
	}
	
	@PutMapping("/deleteBomMaster/{id}")
	public ResponseEntity<Bom_master> deleteBomMaster(@PathVariable(value="id") long id,@Valid @RequestBody Bom_master bom)
	{
		Bom_master op=bom_masterService.deleteBomMaster(bom,id);
		return ResponseEntity.ok().body(op);
	}
	
	/************************** End Bom_master **************************/
	
	/************************** Start Production_planning **************************/
	@Autowired
	Production_planningService production_planningService;
	
	@Autowired
	Production_planning_shop_floor_dtls_staticService production_planning_regular_staticService;
	
	@Autowired
	Production_planning_special_dtls_staticService production_planning_special_dtls_staticService;
	
	@GetMapping("/getPPSequenceId")
	public ResponseEntity<SequenceIdDTO> getPPSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = production_planningService.getPPSequenceId(prefix,bunit,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createProdPlanning")
	public Production_planning createProdPlanning(@Valid @RequestBody Production_planning planning) throws JsonParseException, JsonMappingException, IOException {
		return production_planningService.save(planning);
	}
	
	@GetMapping("/findAllProdPlanning")
	public List<Production_planningDTO> findAllProdPlanning() {
		return production_planningService.findAllProdPlanning();
	}
	
	@GetMapping("/getProdPlanning")
	public List<Production_planningDTO> getProdPlanning() {
		return production_planningService.getProdPlanning();
	}
	
	@GetMapping("/retriveProdPlanning/{id}")
	public ResponseEntity<Production_planning> retriveProdPlanning(@PathVariable(value = "id") Long id) {
		Production_planning op = production_planningService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/deleteProductionPlanning/{id}")
	public ResponseEntity<Production_planning> deleteProductionPlanning(@PathVariable(value = "id") Long id,@Valid @RequestBody  Production_planning pPlanning) {
		Production_planning op = production_planningService.deleteProductionPlanning(pPlanning, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getProdPlanFloorDtls/{prodid}")
	public List<Production_planning_shop_floor_dtlsDTO> getProdPlanFloorDtls(@PathVariable(value = "prodid") String prodid)
	{
		return production_planningService.getProdPlanFloorDtls(prodid);
	}
	
	@GetMapping("/getProdPlanSpecialDtls/{prodid}")
	public List<Production_planning_special_dtlsDTO> getProdPlanSpecialDtls(@PathVariable(value = "prodid") String prodid)
	{
		return production_planningService.getProdPlanSpecialDtls(prodid);
	}
	
	@GetMapping("/getProdPlanPerDateDtls/{prodid}/{proddateid}")
	public List<Production_planning_periodic_date_detailsDTO> getProdPlanPerDateDtls(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "proddateid") String proddateid)
	{
		return production_planningService.getProdPlanPerDateDtls(prodid,proddateid);
	}
	
	@GetMapping("/getProdPlanPerDateDtlsShiftNo/{prodid}/{proddateid}")
	public List<Production_planning_periodic_date_details> getProdPlanPerDateDtlsShiftNo(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "proddateid") String proddateid)
	{
		return production_planningService.getProdPlanPerDateDtlsShiftNo(prodid,proddateid);
	}
	
	@GetMapping("/getProdPlanSplDateDtls/{prodid}/{proddateid}")
	public List<Production_planning_special_date_detailsDTO> getProdPlanSplDateDtls(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "proddateid") String proddateid)
	{
		return production_planningService.getProdPlanSplDateDtls(prodid,proddateid);
	}
		
	@GetMapping("/getProcessThruProPlan")
	public List<Production_planning_shop_floor_dtlsDTO> getProcessThruProPlan(@RequestParam(defaultValue = "") String pdate,@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String company) {
		return production_planningService.getProcessThruProPlan(pdate,bunit,sfloor,company);
	}
	
	@GetMapping("/getProcessThruProdPlan")
	public Production_planning_shop_floor_dtlsDTO getProcessThruProdPlan(@RequestParam(defaultValue = "") String pdate,@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String company) {
		return production_planningService.getProcessThruProdPlan(pdate,bunit,sfloor,process,company);
	}
	
	@GetMapping("/getProdPlanSplDtls")
	public List<Production_planning_special_dtlsDTO> getProdPlanSplDtls(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String company)
	{
		return production_planningService.getProdPlanSplDtls(bunit,sfloor,company);
	}
	
	@GetMapping("/getProdPlanSplProcessDtls")
	public Map<String,Object> getProdPlanSplProcessDtls(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String company)
	{
		return production_planningService.getProdPlanSplProcessDtls(bunit,sfloor,process,company);
	}
	
	@PutMapping("/updateProdPlanning/{id}")
	public ResponseEntity<Production_planning> updateProdPlanning(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Production_planning planning) throws JsonParseException, JsonMappingException, IOException {
		Production_planning op = production_planningService.update(planning, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@PostMapping("/createProdPlanningRegular")
	public Production_planning_shop_floor_dtls_static createProdPlanningRegular(@Valid @RequestBody Production_planning_shop_floor_dtls_static pDtls) throws JsonParseException, JsonMappingException, IOException {
		return production_planning_regular_staticService.save(pDtls);
	}
	
	@PutMapping("/updateProdPlanningRegular/{prodid}/{slno}")
	public ResponseEntity<Production_planning_shop_floor_dtls_static> updateProdPlanning(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "slno") int slno,
			@Valid @RequestBody Production_planning_shop_floor_dtls_static pDtls) throws JsonParseException, JsonMappingException, IOException {
		Production_planning_shop_floor_dtls_static op = production_planning_regular_staticService.update(pDtls, prodid,slno);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/updateProdPlanningSpl/{prodid}/{slno}")
	public ResponseEntity<Production_planning_special_dtls_static> updateProdPlanningSpl(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "slno") int slno,
			@Valid @RequestBody Production_planning_special_dtls_static pSpecial) throws JsonParseException, JsonMappingException, IOException {
		Production_planning_special_dtls_static op = production_planning_special_dtls_staticService.update(pSpecial, prodid,slno);
		return ResponseEntity.ok().body(op);
	}
	
	@PostMapping("/createProdPlanningSpl")
	public Production_planning_special_dtls_static createProdPlanningSpl(@Valid @RequestBody Production_planning_special_dtls_static pSpecial) throws JsonParseException, JsonMappingException, IOException {
		return production_planning_special_dtls_staticService.saveProdPlanSpl(pSpecial);
	}
	
	
	@GetMapping("/getProdPlanShiftDtls")
    public ResponseEntity<List<Production_planning_shift_detailsDTO>> getProdPlanShiftDtls(@RequestParam(defaultValue = "") String businessunit,@RequestParam(defaultValue = "") String shopfl,@RequestParam(defaultValue = "") String tdate)
    {
		System.out.println("businessunit/"+businessunit+"/shopfl/"+shopfl+"/tdate/"+tdate);
    	List<Production_planning_shift_detailsDTO> planning_shift = production_planningService.getProdPlanShiftDtls(businessunit,shopfl,tdate);
    	
	    return new ResponseEntity<List<Production_planning_shift_detailsDTO>>(planning_shift, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getProdPlanShiftDetails")
    public ResponseEntity<Production_planning_shift_detailsDTO> getProdPlanShiftDetails(@RequestParam(defaultValue = "") String shiftid)
    {
    	//System.out.println("shiftid:: "+shiftid);
    	Production_planning_shift_detailsDTO planning_shift = production_planningService.getProdPlanShiftDetails(shiftid);
    	
	    return new ResponseEntity<Production_planning_shift_detailsDTO>(planning_shift, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getProdPlanShifts")
    public ResponseEntity<List<Production_planning_shiftDTO>> getProdPlanShifts(@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String shopfl,@RequestParam(defaultValue = "") String startdate,@RequestParam(defaultValue = "") String enddate)
    {
    	List<Production_planning_shiftDTO> planning_shift = production_planningService.getProdPlanShifts(process,shopfl,startdate,enddate);
    	
	    return new ResponseEntity<List<Production_planning_shiftDTO>>(planning_shift, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getProdPlanShiftsFrom")
    public ResponseEntity<List<Production_planning_shiftDTO>> getProdPlanShiftsFrom(@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String shopfl,@RequestParam(defaultValue = "") String startdate)
    {
    	List<Production_planning_shiftDTO> planning_shift = production_planningService.getProdPlanShiftsFrom(process,shopfl,startdate);
    	
	    return new ResponseEntity<List<Production_planning_shiftDTO>>(planning_shift, new HttpHeaders(), HttpStatus.OK);
    }
	
	
	@GetMapping("/checkdaterangeupdate/{date}")
	public Production_planning_up checkdaterangeupdate(@PathVariable(value = "date") String date)
	{
		return production_planningService.checkdaterangeupdate(date);
	}
	
	@GetMapping("/getProdPlanShiftStatus")
    public ResponseEntity<StatusDTO> getProdPlanShiftStatus(@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String shopfl,@RequestParam(defaultValue = "") String sdate)
    {
		StatusDTO sDto = production_planningService.getProdPlanShiftStatus(process,shopfl,sdate);
    	
	    return new ResponseEntity<StatusDTO>(sDto, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getProdTranShiftTime")
	public List<Production_planning_shift_timeDTO> getProdTranShiftTime(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String shiftid) {
		return production_planningService.getProdTranShiftTime(bunit,sfloor,process,shiftid);
	}
	
	@GetMapping("/getProdTranShiftTimeShiftNo")
	public List<Production_planning_shift_timeDTO> getProdTranShiftTimeShiftNo(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String process,@RequestParam(defaultValue = "") String shiftid) {
		return production_planningService.getProdTranShiftTimeShiftNo(bunit,sfloor,process,shiftid);
	}
	
	/************************** End Production_planning **************************/
	
	/************************** Start Production_transaction **************************/
	@Autowired
	Production_transactionService production_transactionService;
	
	@GetMapping("/getPTSequenceId")
	public ResponseEntity<SequenceIdDTO> getPTSequenceId(@RequestParam(defaultValue = "") String businessunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = production_transactionService.getPTSequenceId(businessunit,sfloor,prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createProdTrans")
	public Production_transaction createProdTrans(@Valid @RequestBody Production_transaction pTransaction) throws JsonParseException, JsonMappingException, IOException{
		return production_transactionService.save(pTransaction);
	}
	
	@GetMapping("/findAllProdTrans")
	public List<Production_transaction> findAllProdTrans() {
		return production_transactionService.findAllProTrans();
	}
	
	@GetMapping("/findAllProdTransfast/{date}")
	public List<Map<String, Object>> findAllProdTransfast(@PathVariable(value = "date") String date) {
		return production_transactionService.findAllProdTransfast(date);
	}
	
	@GetMapping("/retriveProdTrans/{id}")
	public ResponseEntity<Production_transaction> retriveProdTrans(@PathVariable(value = "id") Long id) {
		Production_transaction op = production_transactionService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/getProdTranInputDetails/{prodid}")
	public List<Production_transaction_input_detailsDTO> getProdTranInputDetails(@PathVariable(value = "prodid") String prodid)
	{
		return production_transactionService.getProdTranInputDetails(prodid);
	}
	
	@GetMapping("/getProdTranOutputDetails/{prodid}")
	public List<Production_transaction_output_detailsDTO> getProdTranOutputDetails(@PathVariable(value = "prodid") String prodid)
	{
		return production_transactionService.getProdTranOutputDetails(prodid);
	}
	
	@PutMapping("/updateProdTrans/{id}")
	public ResponseEntity<Production_transaction> updateProdPlanning(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Production_transaction pTransaction) throws JsonParseException, JsonMappingException, IOException{
		Production_transaction op = production_transactionService.update(pTransaction, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getInputPopup/{id}/{item}")
	public List<Production_transaction_input_popup_details> getInputPopup(@PathVariable(value = "id") Long id,@PathVariable(value = "item") String item)
	{
		return production_transactionService.getInputPopup(id,item);
	}
	
	@GetMapping("/getOutputPopup/{id}/{item}")
	public List<Production_transaction_output_popup_details> getOutputPopup(@PathVariable(value = "id") Long id,@PathVariable(value = "item") String item)
	{
		return production_transactionService.getOutputPopup(id,item);
	}
	
	@PutMapping("/deleteProdTransReg/{id}")
	public ResponseEntity<Production_transaction> deleteProdTransReg(@PathVariable(value="id") long id,@Valid @RequestBody Production_transaction transid) throws JsonParseException, JsonMappingException, IOException
	{
		Production_transaction op=production_transactionService.deleteProdTransReg(transid,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getStoreconsumptiontransaction/{shop_floor}")
	public List<Map<String, Object>> getStoreconsumptiontransaction(@PathVariable(value = "shop_floor") String shop_floor)
	{
		return production_transactionService.getStoreconsumptiontransaction(shop_floor);
	}
	
	@GetMapping(value = "/searchProductionTransaction")
	public List<Map<String, Object>> searchProductionTransaction(@RequestParam(defaultValue = "") String business_unit1,@RequestParam(defaultValue = "") String shop_floor1,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate)
	 {
		return production_transactionService.searchProductionTransaction(business_unit1,shop_floor1,fromdate,todate);
	 }
	
	@GetMapping("/accountpostingproductionreg/{id}")
	public Production_transaction accountpostingproductionreg(@PathVariable(value = "id") Long id) {
		
			return production_transactionService.accountpostingproductionreg(id);
		
	}
	
	/************************** End Production_transaction **************************/
	
	/************************** Start Production_transaction Special **************************/
	@Autowired
	Production_transaction_splService production_transaction_splService;
	
	@GetMapping("/getPTSSequenceId")
	public ResponseEntity<SequenceIdDTO> getPTSSequenceId(@RequestParam(defaultValue = "") String businessunit,@RequestParam(defaultValue = "") String sfloor,@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = production_transaction_splService.getPTSSequenceId(businessunit,sfloor,prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createProdTransSpl")
	public Production_transaction_spl createProdTransSpl(@Valid @RequestBody Production_transaction_spl pTransaction) {
		return production_transaction_splService.save(pTransaction);
	}
	
	@GetMapping("/findAllProdTransSpl")
	public List<Production_transaction_spl> findAllProdTransSpl() {
		return production_transaction_splService.findAllProTrans();
	}
	
	@GetMapping("/retriveProdTransSpl/{id}")
	public ResponseEntity<Production_transaction_spl> retriveProdTransSpl(@PathVariable(value = "id") Long id) {
		Production_transaction_spl op = production_transaction_splService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/getProdTranSplInputDetails/{prodid}")
	public List<Production_transaction_spl_input_detailsDTO> getProdTranSplInputDetails(@PathVariable(value = "prodid") String prodid)
	{
		return production_transaction_splService.getProdTranInputDetails(prodid);
	}
	
	@GetMapping("/getProdTranSplOutputDetails/{prodid}")
	public List<Production_transaction_spl_output_detailsDTO> getProdTranSplOutputDetails(@PathVariable(value = "prodid") String prodid)
	{
		return production_transaction_splService.getProdTranOutputDetails(prodid);
	}
	
	@PutMapping("/updateProdTransSpl/{id}")
	public ResponseEntity<Production_transaction_spl> updateProdTransSpl(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Production_transaction_spl pTransaction) {
		Production_transaction_spl op = production_transaction_splService.update(pTransaction, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@GetMapping("/Prodtransaction_spl_Posting/{prodid}/{id}")
	public StatusDTO Prodtransaction_spl_Posting(@PathVariable(value = "prodid") String prodid,@PathVariable(value = "id") Long id)
	{
		return production_transaction_splService.Prodtransaction_spl_Posting(prodid,id);
	}
	
	@GetMapping("/prodtransaction_spl_Posting_Undo/{id}/{username}")
	public StatusDTO prodtransaction_spl_Posting_Undo(@PathVariable(value = "id") long id,@PathVariable(value="username") String username) {
		return production_transaction_splService.prodtransaction_spl_Posting_Undo(id,username);
	}
	
	/************************** End Production_transaction **************************/
	
	/************************** Start Production_Summary **************************/
	
	@Autowired
	ProdsummaryService prodsummaryService;
	
	@PostMapping("/createProdsummary")
	public Prodsummary createProdsummary(@Valid @RequestBody Prodsummary prodsummary) 
	{
		return prodsummaryService.save(prodsummary);
	}
	
	@GetMapping("/Productionsummaryitemdetails/{proddate}")
	public List<Map<String,Object>> Productionsummaryitemdetails(@PathVariable(value = "proddate") String proddate) {
		return prodsummaryService.Productionsummaryitemdetails(proddate);
	}
	
	@GetMapping("/getProductionsummurynumber/{finyear}")
	public SequenceIdDTO  getProductionsummurynumber(@PathVariable(value = "finyear") String finyear) 
	{
		return prodsummaryService.getProductionsummurynumber(finyear);
	}
	
	@GetMapping("/getProductionSummurylist")
	public List<Map<String,Object>> getProductionSummurylist() 
	{
		  return prodsummaryService.getProductionSummurylist();
	}
	
	@PutMapping("/updateProdSummary/{id}")
	public ResponseEntity<Prodsummary> updateProdSummary(@PathVariable(value = "id") Long id,@Valid @RequestBody Prodsummary prodsummary) {
		Prodsummary op = prodsummaryService.update(prodsummary, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteProdSummary/{id}")
	public ResponseEntity<Prodsummary> deleteProdSummary(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Prodsummary prodsummary) {
		Prodsummary op = prodsummaryService.delete(prodsummary, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/retriveProdSummary/{id}")
	public ResponseEntity<Prodsummary> retriveProdSummary(@PathVariable(value="id") Long id)
	{
	  Prodsummary op=prodsummaryService.findOne(id);
		if(id==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	 
	@GetMapping("/retriveProdSummaryDetails/{prod_sum_id}")
	public List<Map<String,Object>> retriveProdSummaryDetails(@PathVariable(value = "prod_sum_id") String prod_sum_id)
	{
	    return prodsummaryService.getProdSumDtls(prod_sum_id);
	}
		
	
	@GetMapping("/Productionsummarydatecheck/{date}")
	public StatusDTO Productionsummarydatecheck(@PathVariable(value = "date") String date)
	{
	    return prodsummaryService.Productionsummarydatecheck(date);
	}
	
	@GetMapping("/ProdSummaryPosting/{id}")
	public StatusDTO ProdSummaryPosting(@PathVariable(value = "id") long id)
	{
	    return prodsummaryService.ProdSummaryPosting(id);
	}
	
	@GetMapping("/prodSummaryPostingUndo/{id}/{username}")
	public StatusDTO prodSummaryPostingUndo(@PathVariable(value = "id") long id,@PathVariable(value="username") String username) {
		return prodsummaryService.prodSummaryPostingUndo(id,username);
	}
	
	/************************** End Production_Summary **************************/

}
