package com.slokam.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.slokam.entity.EligibilityDtls;
import com.slokam.repo.EligibilityDetailsRepo;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	private EligibilityDetailsRepo repo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		EligibilityDtls details=new EligibilityDtls();
		details.setEligId(1);
		details.setName("narendra");
		details.setEmail("123@gmail.com");
		details.setMobile(4574983l);
		details.setGender('M');
		details.setSsn(23456789l);
		details.setPlanName("SNAP");
		details.setPlanStatus("active");
		repo.save(details);
		EligibilityDtls details1=new EligibilityDtls();
		details1.setEligId(2);
		details1.setName("surendra");
		details1.setEmail("qwe@gmail.com");
		details1.setMobile(4574983l);
		details1.setGender('M');
		details1.setSsn(23456789l);
		details1.setPlanName("Medical");
		details1.setPlanStatus("terminated");
		repo.save(details1);
		EligibilityDtls details2=new EligibilityDtls();
		details2.setEligId(3);
		details2.setName("pavani");
		details2.setEmail("fds@gmail.com");
		details2.setMobile(4574983l);
		details2.setGender('M');
		details2.setSsn(23456789l);
		details2.setPlanName("food");
		details2.setPlanStatus("Deactive");
		repo.save(details2);
		
		
	}

}
