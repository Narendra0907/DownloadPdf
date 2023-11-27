package com.slokam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.slokam.entity.EligibilityDtls;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDtls,Integer> {

	@Query("select distinct(planName) from EligibilityDtls")
	public List<String> findPlanNames();
	
	@Query("select distinct(planStatus) from EligibilityDtls")
	public List<String> findPlanStatus();
}
