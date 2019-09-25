package com.poc.pricing.async.model;

import java.util.List;

public class Result {

	private List<EmployeeName> empList;

	private List<EmployeeAddress> empAddressList;

	private List<String> empPhList;

	public Result(List<EmployeeName> empList, List<EmployeeAddress> empAddressList, List<String> empPhList) {
		super();
		this.empList = empList;
		this.empAddressList = empAddressList;
		this.empPhList = empPhList;
	}

	public List<EmployeeName> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeName> empList) {
		this.empList = empList;
	}

	public List<EmployeeAddress> getEmpAddressList() {
		return empAddressList;
	}

	public void setEmpAddressList(List<EmployeeAddress> empAddressList) {
		this.empAddressList = empAddressList;
	}

	public List<String> getEmpPhList() {
		return empPhList;
	}

	public void setEmpPhList(List<String> empPhList) {
		this.empPhList = empPhList;
	}

}
