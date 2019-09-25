package com.poc.pricing.async.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.pricing.async.model.EmployeeAddress;
import com.poc.pricing.async.model.EmployeeAddresses;
import com.poc.pricing.async.model.EmployeeName;
import com.poc.pricing.async.model.EmployeeNames;
import com.poc.pricing.async.model.EmployeePhone;

@Service
public class AsyncService {

	private static Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeNames> getEmployeeName() throws InterruptedException {
		log.info("getEmployeeName Starts");
		EmployeeNames employeeNameData = new EmployeeNames();
		EmployeeName empName = new EmployeeName("kk", "aa");
		employeeNameData.getEmployeeNameList().add(empName);
		log.info("employeeNameData, {}", employeeNameData);
		Thread.sleep(1000L); // Intentional delay
		log.info("employeeNameData completed");
		return CompletableFuture.completedFuture(employeeNameData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeAddresses> getEmployeeAddress() throws InterruptedException {
		log.info("getEmployeeAddress Starts");
		EmployeeAddresses employeeAddressData = new EmployeeAddresses();
		employeeAddressData.getEmployeeAddressList().add(new EmployeeAddress("1", "1", "1"));
		log.info("employeeAddressData, {}", employeeAddressData);
		Thread.sleep(1000L); // Intentional delay
		log.info("employeeAddressData completed");
		return CompletableFuture.completedFuture(employeeAddressData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeePhone> getEmployeePhone() throws InterruptedException {
		log.info("getEmployeePhone Starts");
		EmployeePhone employeePhoneData = new EmployeePhone();
		employeePhoneData.getPhoneNumbers().add("35352352352353532");
		log.info("employeePhoneData, {}", employeePhoneData);
		Thread.sleep(1000L); // Intentional delay
		log.info("employeePhoneData completed");
		return CompletableFuture.completedFuture(employeePhoneData);
	}

}
