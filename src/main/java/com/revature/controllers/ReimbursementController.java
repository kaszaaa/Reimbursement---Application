package com.revature.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.models.Reimbursement;
import com.revature.requests.CreateReimbursementRequest;
import com.revature.requests.UpdateReimbursementRequest;
import com.revature.services.ReimbursementService;


@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ReimbursementController {
	
	private ReimbursementService reimbursementService;
	
	@Autowired
	public ReimbursementController(ReimbursementService reimbursementService) {
		super();
		this.reimbursementService = reimbursementService;
	}
	
	
	@PostMapping("/addreimbursement")
	public ResponseEntity<Reimbursement> addReimbursement(@RequestBody CreateReimbursementRequest req) {
		reimbursementService.addNewReimbursement(req);
		return ResponseEntity.status(200).build();
	}
	
	@PutMapping("/updatereimbursement")
	public ResponseEntity<UpdateReimbursementRequest> updateReimbursement(@RequestBody UpdateReimbursementRequest req, HttpSession session){ // po przecinku powinno byc jeszcze HttpSession session w request body
		System.out.println("Put method");
		if((boolean)session.getAttribute("logged in")==true) {
			reimbursementService.update(req);
			//System.out.println(user);
			return ResponseEntity.status(200).body(req);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
//	@PostMapping("/addReimbursement")
//	public ResponseEntity<Reimbursement> addReimbursement(@RequestBody CreateReimbursementRequest req, HttpSession session) {
//		if(session.getAttribute("logged in")!=null&&(Boolean)session.getAttribute("logged in")) {
//			User user = (User)session.getAttribute("role");
//			if (req != null) {
//				reimbursementService.addNewReimbursement(req);
//				return ResponseEntity.status(HttpStatus.CREATED).build();
//			}
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	


}
