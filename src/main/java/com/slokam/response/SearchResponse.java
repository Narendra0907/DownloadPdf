package com.slokam.response;



public class SearchResponse {

	@Override
	public String toString() {
		return "SearchResponse [name=" + name + ", mobile=" + mobile + ", email=" + email + ", gender=" + gender
				+ ", ssn=" + ssn + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public Long getSsn() {
		return ssn;
	}
	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}
	private String name;
	private Long mobile;
	private String email;
	private Character gender;
	private Long ssn;
	
	
}
