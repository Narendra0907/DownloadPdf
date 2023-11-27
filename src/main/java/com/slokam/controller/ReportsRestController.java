package com.slokam.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.request.SearchRequest;
import com.slokam.response.SearchResponse;
import com.slokam.services.ReportsService;

@RestController
public class ReportsRestController {

	@Autowired
	private ReportsService service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> uniquePlanNames = service.getUniquePlanNames();		
		return new ResponseEntity<>(uniquePlanNames,HttpStatus.OK);
	}
	@GetMapping("/status")
	public ResponseEntity<List<String>> getPlanStatus(){
		List<String> uniquePlanStatus=service.getUniquePlanStatus();
		return new ResponseEntity<>(uniquePlanStatus,HttpStatus.OK);
	}
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search( @RequestBody SearchRequest request){
		List<SearchResponse> response = service.search(request);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=data.xls";
		response.setHeader(headerKey,headerValue);
		service.generateExcel(response);
	
	}
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception  {
		response.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=data.pdf";
		response.setHeader(headerKey,headerValue);
		service.generatePdf(response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
