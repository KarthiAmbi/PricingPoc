package com.poc.pricing.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.pricing.async.model.EmployeeAddress;
import com.poc.pricing.async.model.EmployeeAddresses;
import com.poc.pricing.async.model.EmployeeName;
import com.poc.pricing.async.model.EmployeeNames;
import com.poc.pricing.async.model.EmployeePhone;
import com.poc.pricing.async.model.Result;
import com.poc.pricing.async.service.AsyncService;

@RestController
@RequestMapping("/v1/async")
public class AsyncController {

	@Autowired
	private AsyncService service;

	@GetMapping("/call")
	public ResponseEntity<Object> callAsync() {

		System.out.println("fffffffffffffffffffffffffffffffffffffff");

		try {
			CompletableFuture<EmployeeAddresses> employeeAddress = service.getEmployeeAddress();
			CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName();
			CompletableFuture<EmployeePhone> employeePhone = service.getEmployeePhone();
			CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();
			List<EmployeeName> empList = employeeName.get().getEmployeeNameList();

			List<EmployeeAddress> empAddressList = employeeAddress.get().getEmployeeAddressList();

			List<String> empPhList = employeePhone.get().getPhoneNumbers();

			Result result = new Result(empList, empAddressList, empPhList);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
