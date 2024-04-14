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

import com.AnkitIndia.jwtauthentication.model.Issuestock;
import com.AnkitIndia.jwtauthentication.model.Issuestock_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Requisition;
import com.AnkitIndia.jwtauthentication.model.Requisition_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.ShopFloorAccess;
import com.AnkitIndia.jwtauthentication.model.Store_issue_note;
import com.AnkitIndia.jwtauthentication.responseDTO.RequisitionListDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.RequistionRecordsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StocksRecordsDTO;
import com.AnkitIndia.jwtauthentication.security.services.IssuestockService;
import com.AnkitIndia.jwtauthentication.security.services.RequisitionService;
import com.AnkitIndia.jwtauthentication.security.services.ShopFloorAccessService;
import com.AnkitIndia.jwtauthentication.security.services.Store_issue_noteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class StoreController {

	@Autowired
	ShopFloorAccessService shopFloorAccessService;
	
	@Autowired
	RequisitionService requisitionService;

	@Autowired
	IssuestockService issuestockService;
	
	@Autowired
	Store_issue_noteService store_issue_noteService;
	
	/*************************************************************Shop floor Access Starts here***************************************/
	
		
	   @GetMapping(value = "/getAccesslist/{finyear}")
	   public List<ShopFloorAccess> getAccesslist(@PathVariable(value = "finyear") String finyear)
		{
			return shopFloorAccessService.getAccesslist(finyear);
		}
	   
		@PostMapping("/createFloorAccess")
		public ShopFloorAccess createFloorAccess(@Valid @RequestBody ShopFloorAccess shopFloorAccess) {
			return shopFloorAccessService.save(shopFloorAccess);
		}
		
		@GetMapping("/retriveFloorAccess/{id}")
		public ResponseEntity<ShopFloorAccess> retriveFloorAccess(@PathVariable(value="id") Long id)
		{
			ShopFloorAccess op=shopFloorAccessService.findOne(id);
			if(id==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(op);
			}
		}
		
		@PutMapping("/updateFloorAccess/{id}")
		public ResponseEntity<ShopFloorAccess> updateFloorAccess(@PathVariable(value = "id") Long id,@Valid @RequestBody ShopFloorAccess shopFloorAccess) {
			ShopFloorAccess op = shopFloorAccessService.update(shopFloorAccess, id);
			return ResponseEntity.ok().body(op);
		}
		
		@PutMapping("/deleteFloorAccess/{id}")
		public ResponseEntity<ShopFloorAccess> deleteFloorAccess(@PathVariable(value = "id") Long id,
				@Valid @RequestBody ShopFloorAccess shopFloorAccess) {
			ShopFloorAccess op = shopFloorAccessService.deleteFloorAccess(shopFloorAccess, id);
			return ResponseEntity.ok().body(op);
		}
		
		 
		/*************************************************************Shop floor Access end here***************************************/
		
		
		/**************************************************************Requisition starts here  ****************************************/
		
		
		@GetMapping(value = "/getrequisitionnumber/{finyear}")
	    public SequenceIdDTO  getrequisitionnumber(@PathVariable(value = "finyear") String finyear)
		{
			return requisitionService.getrequisitionnumber(finyear);
		}
		   
		
		@PostMapping("/createrequisition")
		public Requisition createrequisition(@Valid @RequestBody Requisition requisition) {
			return requisitionService.save(requisition);
		}
		
		@PutMapping("/updaterequisition/{id}")
		public ResponseEntity<Requisition> updaterequisition(@PathVariable(value = "id") Long id,@Valid @RequestBody Requisition requisition) {
			Requisition op = requisitionService.update(requisition, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		@PutMapping("/deleteRequisition/{id}")
		public ResponseEntity<Requisition> deleteRequisition(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Requisition requisition) {
			Requisition op = requisitionService.deleteRequisition(requisition, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		
		
		@GetMapping(value = "/listRequisition")
	    public List<RequisitionListDTO>  listRequisition()
		{
			return requisitionService.listRequisition();
		}
		
		
		
		@GetMapping(value = "/getRequisitiondetails/{id}")
	    public Requisition  getrequisitionnumber(@PathVariable(value = "id") Long id)
		{
			return requisitionService.getrequisitionnumber(id);
		}
		
		  
		@GetMapping(value = "/getRequisitionitemdetails/{requisitionno}")
	    public List<Requisition_Item_Dtls>  getRequisitionitemdetails(@PathVariable(value = "requisitionno") String requisitionno)
		{
			return requisitionService.getRequisitionitemdetails(requisitionno);
		}
		
	    
	    @GetMapping(value = "/requisitionapprove/{id}/{username}")
	    public StatusDTO  requisitionapprove(@PathVariable(value = "id") Long id,@PathVariable(value = "username") String username)
		{
			return requisitionService.requisitionapprove(id,username);
		}
	    
	    
	    @PutMapping("/setreject/{id}")
		public ResponseEntity<Requisition> setreject(@PathVariable(value = "id") Long id,@Valid @RequestBody Requisition requisition) {
			Requisition op = requisitionService.setreject(requisition, id);
			return ResponseEntity.ok().body(op);
		}
	    
	    
	    @GetMapping(value = "/getRequisitionnumberapprove/{shopfloor}")
	    public List<Requisition>  getRequisitionnumberapprove(@PathVariable(value = "shopfloor") String shopfloor)
		{
			return requisitionService.getRequisitionnumberapprove(shopfloor);
		}
	    
	    
	    @GetMapping(value = "/getRequisition/{requisitionno}")
	    public Requisition  getRequisition(@PathVariable(value = "requisitionno") String requisitionno)
		{
			return requisitionService.getRequisition(requisitionno);
		}
	    
		
	    
		/**************************************************************Requisition ends here  ****************************************/
	    /**************************************************************Issue Stock starts here  ****************************************/
	    
	    
	    @GetMapping(value = "/getIssueStocklist/{finyear}")
	    public List<Issuestock>  getIssueStocklist(@PathVariable(value = "finyear") String finyear)
		{
			return issuestockService.getIssueStocklist(finyear);
		}
	    
	    @GetMapping(value = "/getissuestocknumber/{finyear}")
	    public SequenceIdDTO  getissuestocknumber(@PathVariable(value = "finyear") String finyear)
		{
			return issuestockService.getissuestocknumber(finyear);
		}
	    
	    @PostMapping("/createIssueStock")
		public Issuestock createIssueStock(@Valid @RequestBody Issuestock issuestock) {
			return issuestockService.save(issuestock);
		}
	    
	    @GetMapping(value = "/retriveIssueStock/{id}")
	    public Issuestock retriveIssueStock(@PathVariable(value = "id") Long id)
		{
			return issuestockService.retriveIssueStock(id);
		}
		
		  
		@GetMapping(value = "/getIssueItemDetails/{issueno}")
	    public List<Issuestock_Item_Dtls> getIssueItemDetails(@PathVariable(value = "issueno") String issueno)
		{
			return issuestockService.getIssueItemDetails(issueno);
		}
	    
	    @PutMapping("/updateIssueStock/{id}")
		public ResponseEntity<Issuestock> updateIssueStock(@PathVariable(value = "id") Long id,@Valid @RequestBody Issuestock issuestock) {
	    	Issuestock op = issuestockService.update(issuestock, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		@PutMapping("/deleteIssueStock/{id}")
		public ResponseEntity<Issuestock> deleteIssueStock(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Issuestock issuestock) {
			Issuestock op = issuestockService.deleteIssueStock(issuestock, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		 @GetMapping(value = "/getaayogstocks/{itemcode}")
	    public StocksRecordsDTO getaayogstocks(@PathVariable(value = "itemcode") String itemcode)
		{
			return issuestockService.getaayogstocks(itemcode);
		}
			
		
		@GetMapping(value = "/getrequistionstockrecordbyitem/{itemcode}/{requisitionno}/{packingcode}")
	    public RequistionRecordsDTO getrequistionstockrecordbyitem(@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "requisitionno") String requisitionno,@PathVariable(value = "packingcode") String packingcode)
		{
			return issuestockService.getrequistionstockrecordbyitem(itemcode,requisitionno,packingcode);
		}
		 
	    /**************************************************************Issue Stock ends here  ****************************************/
		
		/* Store Issue Note Starts*/
		
		@GetMapping("/getstoreIssuelist")
		public List<Map<String,Object>> getstoreIssuelist()
		{
			  return store_issue_noteService.getstoreIssuelist();
		}
		
		@PostMapping("/createStoreIssueNote")
		public Store_issue_note createStoreIssueNote(@Valid @RequestBody Store_issue_note store_issue_note) {
			return store_issue_noteService.save(store_issue_note);
		}
	    
	    @GetMapping(value = "/retriveStoreIssueNote/{id}")
	    public Store_issue_note retriveStoreIssueNote(@PathVariable(value = "id") Long id)
		{
			return store_issue_noteService.retriveStoreIssueNote(id);
		}
		
		  
		@GetMapping(value = "/retriveStoreIssueNoteDetails/{store_issue_id}")
	    public List<Map<String,Object>> retriveStoreIssueNoteDetails(@PathVariable(value = "store_issue_id") String store_issue_id)
		{
			return store_issue_noteService.retriveStoreIssueNoteDetails(store_issue_id);
		}
	    
	    @PutMapping("/updateStoreIssueNote/{id}")
		public ResponseEntity<Store_issue_note> updateStoreIssueNote(@PathVariable(value = "id") Long id,@Valid @RequestBody Store_issue_note store_issue_note) {
			Store_issue_note op = store_issue_noteService.update(store_issue_note, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		@PutMapping("/deleteStoreIssueNote/{id}")
		public ResponseEntity<Store_issue_note> deleteStoreIssueNote(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Store_issue_note store_issue_note) {
			Store_issue_note op = store_issue_noteService.deleteStoreIssueNote(store_issue_note, id);
			return ResponseEntity.ok().body(op);
		}
		
		@GetMapping("/getPoStoreQty/{item}/{clasitem}/{warehouse}")
		public Map<String,Object> getPoStoreQty(@PathVariable(value = "item") String item,
												@PathVariable(value = "clasitem") String clasitem,
												@PathVariable(value = "warehouse") String warehouse)
		{
			  return store_issue_noteService.getPoStoreQty(item,clasitem,warehouse);
		}
		
		@GetMapping("/getItemThruGrn")
		public List<Map<String,Object>> getItemThruGrn()
		{
			  return store_issue_noteService.getItemThruGrn();
		}
		
		@GetMapping("/retriveClassifiedItemThruGrn/{itemid}")
		public List<Map<String, Object>> retriveClassifiedItemThruGrn(@PathVariable(value = "itemid") String itemid)
		{
			return store_issue_noteService.retriveClassifiedItemThruGrn(itemid);
		}
		
		@GetMapping("/getWarehouseFromPoStoreItem")
		public List<Map<String,Object>> getWarehouseFromPoStoreItem()
		{
			return store_issue_noteService.getWarehouseFromPoStoreItem();
		}
		
		/* Store Issue Note Ends*/
		
}
