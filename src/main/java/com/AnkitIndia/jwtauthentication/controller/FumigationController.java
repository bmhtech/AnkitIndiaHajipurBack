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

import com.AnkitIndia.jwtauthentication.model.Wheat_fumigation;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.security.services.Wheat_fumigationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")

public class FumigationController {

	@Autowired
	Wheat_fumigationService wheat_fumigationService;
	
	/*********************************************Wheat Fumigation Starts here***************************************/
	
	@GetMapping("/getWheatFumigationList/{finyear}")
	public List<Map<String, Object>> getWheatFumigationList(@PathVariable(value = "finyear") String finyear)
	{
		return wheat_fumigationService.getWheatFumigationList(finyear);
	}
	
	@GetMapping("/getFumigationSequenceId/{company}")
	public SequenceIdDTO getFumigationSequenceId(@PathVariable(value = "company") String company)
	{
		return wheat_fumigationService.getFumigationSequenceId(company);
	}
	
	@PostMapping("/createWheatFumigation")
	public Wheat_fumigation createWheatFumigation(@Valid @RequestBody Wheat_fumigation wf)
	{
		return 	wheat_fumigationService.save(wf);
	}
	
	@GetMapping("/retriveWheatFumigation/{id}")
	public ResponseEntity<Wheat_fumigation> retriveWheatFumigation(@PathVariable(value="id") long id )
	{
		Wheat_fumigation wf=wheat_fumigationService.findOne(id);
		if(wf == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(wf);
		}
	}
	
	@GetMapping("/getWheatFumigationDetails/{fumigation_id}")
	public List<Map<String,Object>> getWheatFumigationDetails(@PathVariable(value="fumigation_id") String fumigation_id)
	{
		return wheat_fumigationService.getWheatFumigationDetails(fumigation_id);
	}
	
	@PutMapping("/updateWheatFumigation/{id}")
	public ResponseEntity<Wheat_fumigation> updateWheatFumigation(@Valid @RequestBody Wheat_fumigation wf,@PathVariable(value="id") long id)
	{
		Wheat_fumigation updateWF=wheat_fumigationService.update(wf, id);
		return ResponseEntity.ok().body(updateWF);
	}
	
	@PutMapping("/deleteWheatFumigation/{id}")
	public ResponseEntity<Wheat_fumigation> deleteWheatFumigation(@Valid @RequestBody Wheat_fumigation wf,
																		@PathVariable(value="id") Long id)
	{
		Wheat_fumigation deleteWF=wheat_fumigationService.delete(wf, id);
		return ResponseEntity.ok().body(deleteWF);
	}
	
	@GetMapping("/getAllWheatFumiDtlsList/{type}")
	public List<Map<String,Object>> getAllWheatFumiDtlsList(@PathVariable(value="type") String type)
	{
		return wheat_fumigationService.getAllWheatFumiDtlsList(type);
	}
	
	@GetMapping("/updateWheatFumiDetails/{id}/{fumigation_id}/{allocate_date}/{company}/{finyear}/{user}/{action}/{allocate}/{pcmw_sign_name}/{supervisor_sign_name}/{lab_sign_name}/{manpower}/{degassing_date}/{degassing_time}/{wheat_fumi_qc}")
	public ResponseEntity<StatusDTO> updateWheatFumiDetails(@PathVariable(value="id") Long id,
			@PathVariable(value="fumigation_id") String fumigation_id,
			@PathVariable(value = "allocate_date") String allocate_date,
			@PathVariable(value = "company") String company,
			@PathVariable(value = "finyear") String finyear,
			@PathVariable(value = "user") String user,
			@PathVariable(value = "action") String action,
			@PathVariable(value = "allocate") String allocate,
			@PathVariable(value = "pcmw_sign_name") String pcmw_sign_name,
			@PathVariable(value = "supervisor_sign_name") String supervisor_sign_name,
			@PathVariable(value = "lab_sign_name") String lab_sign_name,
			@PathVariable(value = "manpower") String manpower,
			@PathVariable(value = "degassing_date") String degassing_date,
			@PathVariable(value = "degassing_time") String degassing_time,
			@PathVariable(value = "wheat_fumi_qc") String wheat_fumi_qc)
	{
		StatusDTO check=wheat_fumigationService.updateWheatFumiDetails(id,fumigation_id,allocate_date,company,finyear,user,action,allocate,pcmw_sign_name,supervisor_sign_name,lab_sign_name,manpower,degassing_date,degassing_time,wheat_fumi_qc);
		return ResponseEntity.ok().body(check);
	}
	
	/*********************************************Wheat Fumigation Ends here***************************************/
}