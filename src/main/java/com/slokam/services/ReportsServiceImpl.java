package com.slokam.services;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.slokam.entity.EligibilityDtls;
import com.slokam.repo.EligibilityDetailsRepo;
import com.slokam.request.SearchRequest;
import com.slokam.response.SearchResponse;
@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private EligibilityDetailsRepo eligrepo;

	@Override
	public List<String> getUniquePlanNames() {

		return eligrepo.findPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {

		return eligrepo.findPlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {

		List<SearchResponse> response = new ArrayList<>();
		
		EligibilityDtls querybilder=new EligibilityDtls();
		
		String planName=request.getPlanName();
		if(planName !=null && planName.equals("")) {
			querybilder.setPlanName(planName);
		}
		
		String planStatus =request.getPlanStatus();
		if(planStatus!=null && planStatus.equals("")) {
			querybilder.setPlanStatus(planStatus);
		}
		LocalDate planStartDate=request.getPlanStartDate();
		if(planStartDate!=null) {
			querybilder.setPlanStartDate(planStartDate);
		}
		LocalDate planEndDate=request.getPlanEndDate();
		if(planEndDate!=null) {
			querybilder.setPlanStartDate(planEndDate);
		}	
		Example<EligibilityDtls> example=Example.of(querybilder);
		
		
		List<EligibilityDtls> entries = eligrepo.findAll(example);

		for (EligibilityDtls entry : entries) {
			SearchResponse rs = new SearchResponse();
			BeanUtils.copyProperties(entry, rs);

		}

		return response;
	}

	@Override
	public void generateExcel(javax.servlet.http.HttpServletResponse response) throws Exception {
		List<EligibilityDtls> entries = eligrepo.findAll();
		
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet createSheet = workBook.createSheet();
		HSSFRow headerRow = createSheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Mobile");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Ssn");
		
		Integer i=1;
		for(EligibilityDtls entry:entries) {
			
		HSSFRow datarow=createSheet.createRow(i);
		datarow.createCell(0).setCellValue(entry.getName());
		datarow.createCell(1).setCellValue(entry.getEmail());
		datarow.createCell(2).setCellValue(entry.getMobile());
		datarow.createCell(3).setCellValue(entry.getGender());
		datarow.createCell(4).setCellValue(entry.getSsn());
			i++;	
		}
		
		ServletOutputStream outputstream=response.getOutputStream();
		workBook.write(outputstream);
		workBook.close();
		outputstream.close();
		
		
	}

	@Override
	public void generatePdf(javax.servlet.http.HttpServletResponse response) throws Exception {
		List<EligibilityDtls> entries = eligrepo.findAll();
		
		
		Document document = new Document(PageSize.A4);
		
		 PdfWriter.getInstance(document, response.getOutputStream());
		 document.open();
		 
		 Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		 font.setSize(18);
		 font.setColor(Color.green);
		 
		 Paragraph p=new Paragraph("Search Report",font);
		 p.setAlignment(Paragraph.ALIGN_CENTER);
		 
		 document.add(p);
		 
		 PdfPTable table =new PdfPTable(5);
		 table.setWidthPercentage(100f);
		 table.setWidths(new float[] {1.5f,3.5f,3.0f,3.0f,1.5f});
		 table.setSpacingBefore(10);
		 
		 PdfPCell cell=new PdfPCell();
		 cell.setBackgroundColor(Color.darkGray);
		 cell.setPadding(5);
		 
		 
		 font=FontFactory.getFont(FontFactory.HELVETICA);
		 font.setColor(Color.white);
		 
		 cell.setPhrase(new Phrase("Name",font));
		 table.addCell(cell);
		 
		 cell.setPhrase(new Phrase("E-mail",font));
		 table.addCell(cell);
		 
		 cell.setPhrase(new Phrase("ph-no",font));
		 table.addCell(cell);
		 
		 cell.setPhrase(new Phrase("Gender",font));
		 table.addCell(cell);
		 
		 cell.setPhrase(new Phrase("SSN",font));
		 table.addCell(cell);
		
		 
		 for(EligibilityDtls entiry:entries) {
			 
			 table.addCell(entiry.getName());
			 table.addCell(entiry.getEmail());
			 table.addCell(String.valueOf(entiry.getGender()));
			 table.addCell(String.valueOf(entiry.getMobile()));
			 table.addCell(String.valueOf(entiry.getSsn()));
			 
		 }
		 document.add(table);
		 document.close();
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	}

}
