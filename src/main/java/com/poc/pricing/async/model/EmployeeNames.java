package com.poc.pricing.async.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeNames implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1773599508061743940L;
	public List<EmployeeName> employeeNameList;

	public List<EmployeeName> getEmployeeNameList() {
		if (employeeNameList == null) {
			employeeNameList = new ArrayList<EmployeeName>();
		}
		return employeeNameList;
	}

	public void setEmployeeNameList(List<EmployeeName> employeeNameList) {

		this.employeeNameList = employeeNameList;
	}

	@Override
	public String toString() {
		return "EmployeeNames [employeeNameList=" + employeeNameList + "]";
	}

}
