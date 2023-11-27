package com.slokam.services;

import java.util.List;

import com.slokam.request.SearchRequest;
import com.slokam.response.SearchResponse;



public interface ReportsService {

	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
	
	public List<SearchResponse> search(SearchRequest request);
	
	public void generateExcel(javax.servlet.http.HttpServletResponse response) throws Exception;
	
	public void generatePdf(javax.servlet.http.HttpServletResponse response) throws Exception;
}
