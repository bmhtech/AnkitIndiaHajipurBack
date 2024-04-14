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

import com.AnkitIndia.jwtauthentication.model.TaskAllocation;
import com.AnkitIndia.jwtauthentication.model.TaskProgress;
import com.AnkitIndia.jwtauthentication.security.services.TaskAllocationService;
import com.AnkitIndia.jwtauthentication.security.services.TaskProgressService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")

public class TaskManagementController {
	
	@Autowired
	TaskAllocationService taskAllocationService;
	
	@Autowired
	TaskProgressService taskProgressService;
	
	/************************************Task Allocation Starts here***************************************/
	
	@GetMapping("/getAllocationlist")
	public List<Map<String, Object>> getAllocationlist()
	{
		return taskAllocationService.getAllocationlist();				
	}
	
	@PostMapping("/createTaskAllocation")
	public TaskAllocation createTaskAllocation(@Valid @RequestBody TaskAllocation taskAllocation)
	{
		 return taskAllocationService.save(taskAllocation);				
	}

	@GetMapping("/retrivetaskAllocation/{id}")
	public ResponseEntity<TaskAllocation> retrivetaskAllocation(@PathVariable(value = "id") Long id) {
		TaskAllocation op = taskAllocationService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateTaskAllocation/{id}")
	public ResponseEntity<TaskAllocation> updateTaskAllocation(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TaskAllocation taskAllocation) {
		TaskAllocation op = taskAllocationService.update(taskAllocation, id);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/deleteTaskAllocation/{id}")
	public ResponseEntity<TaskAllocation> deleteTaskAllocation(@PathVariable(value="id") long id,
													@Valid @RequestBody TaskAllocation taskAllocation)
	{
		TaskAllocation deleteRC=taskAllocationService.delete(taskAllocation,id);
		return ResponseEntity.ok().body(deleteRC);
	}

	/************************************Task Allocation Ends here***************************************/
	
	/************************************Task Progress Starts here***************************************/
	
	@GetMapping("/getProgresslist")
	public List<Map<String, Object>> getProgresslist()
	{
		return taskProgressService.getProgresslist();				
	}
	
	@PostMapping("/createTaskProgress")
	public TaskProgress createTaskProgress(@Valid @RequestBody TaskProgress taskProgress)
	{
		 return taskProgressService.save(taskProgress);				
	}

	@GetMapping("/retrivetaskProgress/{id}")
	public ResponseEntity<TaskProgress> retrivetaskProgress(@PathVariable(value = "id") Long id) {
		TaskProgress op = taskProgressService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateTaskProgress/{id}")
	public ResponseEntity<TaskProgress> updateTaskProgress(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TaskProgress taskProgress) {
		TaskProgress op = taskProgressService.update(taskProgress, id);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/deleteTaskProgress/{id}")
	public ResponseEntity<TaskProgress> deleteTaskProgress(@PathVariable(value="id") long id,
													@Valid @RequestBody TaskProgress taskProgress)
	{
		TaskProgress deleteTP=taskProgressService.delete(taskProgress,id);
		return ResponseEntity.ok().body(deleteTP);
	}
	
	@GetMapping("/getAllocationUsernameWiselist/{currdate}/{user}")
	public List<Map<String, Object>> getAllocationUsernameWiselist(@PathVariable(value = "currdate") String currdate,@PathVariable(value = "user") String user)
	{
		return taskProgressService.getAllocationUsernameWiselist(currdate,user);				
	}
	
	@GetMapping("/getTaskNameDetails/{taskid}")
	public Map<String, Object> getTaskNameDetails(@PathVariable(value = "taskid") String taskid)
	{
		return taskProgressService.getTaskNameDetails(taskid);				
	}
	/************************************Task Progress Ends here***************************************/
	
	@GetMapping("/getTaskProgressReport/{fromdate}/{todate}")
	public List<Map<String, Object>> getTaskProgressReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
	{
		return taskProgressService.getTaskProgressReport(fromdate,todate);				
	}
}
