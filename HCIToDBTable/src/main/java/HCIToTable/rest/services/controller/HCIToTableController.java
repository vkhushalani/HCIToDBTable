package HCIToTable.rest.services.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HCIToTable.rest.services.model.RBPEmployees;
import HCIToTable.rest.services.model.RBPGroups;
import HCIToTable.rest.services.service.RBPEmployeesService;
import HCIToTable.rest.services.service.RBPGroupsService;



@RestController
@RequestMapping("/HCIToHana")
public class HCIToTableController {
	Logger logger = LoggerFactory.getLogger(HCIToTableController.class);
	 @Autowired
	 RBPEmployeesService RBPEmployeesService;
	 @Autowired
	 RBPGroupsService RBPGroupsService;
	 
	 @PostMapping(value = "/merge/employees",consumes = "text/plain")
	 public ResponseEntity<?> uploadEmployees(@RequestBody String value) throws IOException {
		 String lines[] = value.split("\\r?\\n");
		

//		 InputStream file = new ByteArrayInputStream(uploadfile.getBytes());
		 String [] parts;
		 RBPEmployees employee;
		 RBPEmployees employeeExist = null;
//		 BufferedReader b = new BufferedReader(new InputStreamReader(file));
		 
		 for (String line : lines) {
			  parts = line.split(",");
			  employee = new RBPEmployees();
			  employee.setGrp(Integer.valueOf(parts[0]));
			  employee.setUserId(parts[1]);
			  employeeExist = RBPEmployeesService.findByKey(Integer.valueOf(parts[0]), parts[1]);
			  if(employeeExist == null)
			  {
				  employee.setToday(false);
				  employee.setNext(true);
				  RBPEmployeesService.create(employee);
			  }
			  else
			  {
				  if(employeeExist.getToday() && !employeeExist.getNext())
				  {
					  employee.setToday(true);
					  employee.setNext(true);
					  RBPEmployeesService.update(employee);
					  }
			  }
			  
			 
         }
		return ResponseEntity.ok().body("Success");
	 }
	 
//	 @GetMapping("/commit/employees")
//	 public ResponseEntity<?> commitEmployees(){
//		 
//		 RBPEmployeesService.deleteFlagged();
//		 RBPEmployeesService.updateAll();
//		 return ResponseEntity.ok().body("Success");
//	 }
	 
	 @GetMapping("/commit/tables")
	 public ResponseEntity<?> commitTables(){
		 
		 RBPEmployeesService.deleteFlagged();
		 RBPGroupsService.deleteFlagged();
		 RBPEmployeesService.updateAll();
		 RBPGroupsService.updateAll();
		 return ResponseEntity.ok().body("Success");
	 }
	 
	 @PostMapping(value = "/merge/groups",consumes = "text/plain")
	 public ResponseEntity<?> uploadGroups(@RequestBody String value) throws IOException {
		 String lines[] = value.split("\\r?\\n");
//		 InputStream file = new ByteArrayInputStream(uploadfile.getBytes());
		 String [] parts;
		 RBPGroups groups;
		 RBPGroups groupsExist;
//		 BufferedReader b = new BufferedReader(new InputStreamReader(file));
//		 String readLine = "";
		 for (String line : lines) {
			  parts = line.split(",");
			  groups = new RBPGroups();
			  groups.setGrp(Integer.valueOf(parts[2]));
			  groups.setRole(parts[1]);
			  groups.setUserId(parts[0]);
			  groupsExist = RBPGroupsService.findByKey(parts[0],parts[1],Integer.valueOf(parts[2]));
			  if(groupsExist == null)
			  {
				  groups.setToday(false);
				  groups.setNext(true);
				  RBPGroupsService.create(groups);
			  }
			  else
			  {
				  if(groupsExist.getToday() && !groupsExist.getNext())
				  {
					  groups.setToday(true);
					  groups.setNext(true);
					  RBPGroupsService.update(groups);
					  }
			  }
			  
			 
         }
		return ResponseEntity.ok().body("success");
	 }
	 
	 @GetMapping("/lastcommit/tables")
	 public ResponseEntity<?> lastCommitTables(){
		 
		 RBPEmployeesService.deleteTodayFlagged();
		 RBPGroupsService.deleteTodayFlagged();
		 RBPEmployeesService.updateAll();
		 RBPGroupsService.updateAll();
		 return ResponseEntity.ok().body("Success");
	 }
	 
//	 @GetMapping("/commit/groups")
//	 public ResponseEntity<?> commitGroups(){
//		 
//		 RBPGroupsService.deleteFlagged();
//		 RBPGroupsService.updateAll();
//		 return ResponseEntity.ok().body("success");
//	 }

}
