package com.monthly.expenses.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monthly.expenses.constant.AppConstants;
import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.domain.User;
import com.monthly.expenses.exception.UnauthorizedException;
import com.monthly.expenses.model.Response;
import com.monthly.expenses.model.TransactionDTO;
import com.monthly.expenses.service.TransactionService;
import com.monthly.expenses.util.UserUtil;

/**
 * The Class ProductController.
 *
 * @author G Lokesh
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/transactions")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<TransactionDTO>> products(@RequestBody User user) {
		List<TransactionDTO> transactions = transactionService.findAll(user.getId());
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	/**
	 * Creates the.
	 *
	 * @param product
	 *            the product
	 * @return the response entity
	 */
	@PostMapping("/create")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Transactions> create(@RequestBody Transactions transactions) {
		User user = UserUtil.getAuthenticatedUser();
		if (user != null) {
			transactions.setCreatedBy(user.getRole());
			transactions.setCreatedDate(new Date());
		}
		if (transactions.getType().equals(AppConstants.INCOME)) {
			transactions.setCreditAmount(transactions.getAmount());
			transactions.setDebitAmount(0);
		} else if (transactions.getType().equals(AppConstants.EXPENSE)) {
			transactions.setDebitAmount(transactions.getAmount());
			transactions.setCreditAmount(0);
		}

		transactions = transactionService.save(transactions);
		return new ResponseEntity<Transactions>(transactions, HttpStatus.OK);
	}
	
	@PostMapping("/mobilecreate")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Transactions> mobilecreate(@RequestBody Transactions transactions) {
		User user = UserUtil.getAuthenticatedUser();
		if (user != null) {
			transactions.setCreatedBy(user.getRole());
			transactions.setCreatedDate(new Date());
		}
		
		if (transactions.getType().equals(AppConstants.INCOME)) {
			transactions.setCreditAmount(transactions.getAmount());
			transactions.setDebitAmount(0);
		} else if (transactions.getType().equals(AppConstants.EXPENSE)) {
			transactions.setDebitAmount(transactions.getAmount());
			transactions.setCreditAmount(0);
		}
		
		if(StringUtils.hasText(transactions.getDbTransactionDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date transactionDate = null;
			try {
				String date = transactions.getDbTransactionDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				transactionDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			transactions.setTransactionDate(transactionDate);
		}

		transactions = transactionService.save(transactions);
		return new ResponseEntity<Transactions>(transactions, HttpStatus.OK);
	}
	

	/**
	 * Creates the.
	 *
	 * @param product
	 *            the product
	 * @return the response entity
	 */
	@PostMapping("/monthlycreate")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Transactions> monthlycreate(@RequestBody Transactions transactions) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String formatDate = format.format(transactions.getTransactionDate());
		System.out.println(formatDate);
		if (!StringUtils.hasText(transactions.getMonthYear())) {
			throw new UnauthorizedException("Some thing Went Wrong, Please go back to Transactions");
		}

		if (!formatDate.equalsIgnoreCase(transactions.getMonthYear())) {
			throw new UnauthorizedException(
					"Please choose " + transactions.getMonthYear() + " Month & Year Dates Only");
		}

		User user = UserUtil.getAuthenticatedUser();
		if (user != null) {
			transactions.setCreatedBy(user.getRole());
			transactions.setCreatedDate(new Date());
		}
		
		if (transactions.getType().equals(AppConstants.INCOME)) {
			transactions.setCreditAmount(transactions.getAmount());
			transactions.setDebitAmount(0);
		} else if (transactions.getType().equals(AppConstants.EXPENSE)) {
			transactions.setDebitAmount(transactions.getAmount());
			transactions.setCreditAmount(0);
		}

		transactions = transactionService.save(transactions);
		return new ResponseEntity<Transactions>(transactions, HttpStatus.OK);
	}

	@PostMapping("/editMonthlyTransaction")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Transactions> editMonthlyTransaction(@RequestBody Transactions transactions) {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
		String dbTransactionDate = format.format(transactions.getTransactionDate());
		transactions.setDbTransactionDate(dbTransactionDate);
		System.out.println(dbTransactionDate);
		
		if (transactions.getType().equals(AppConstants.INCOME)) {
			transactions.setAmount(transactions.getCreditAmount());
		} else if (transactions.getType().equals(AppConstants.EXPENSE)) {
			transactions.setAmount(transactions.getDebitAmount());
		}
		return new ResponseEntity<Transactions>(transactions, HttpStatus.OK);
	}
	
	
	@GetMapping("/editMobileMonthlyTransaction/{transactionId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Transactions> editMobileMonthlyTransaction(@PathVariable Long transactionId) {
		Transactions transactions = transactionService.findById(transactionId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dbTransactionDate = format.format(transactions.getTransactionDate());
		transactions.setDbTransactionDate(dbTransactionDate);
		System.out.println(dbTransactionDate);
		if (transactions.getType().equals(AppConstants.INCOME)) {
			transactions.setAmount(transactions.getCreditAmount());
		} else if (transactions.getType().equals(AppConstants.EXPENSE)) {
			transactions.setAmount(transactions.getDebitAmount());
		}
		return new ResponseEntity<Transactions>(transactions, HttpStatus.OK);
	}
	
	
	/**
	 * Update.
	 *
	 * @param product
	 *            the product
	 * @return the response entity
	 */
	@PostMapping("/updateMonthlyTransaction")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Transactions> updateMonthlyTransaction(@RequestBody Transactions transactions) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String formatDate = format.format(transactions.getTransactionDate());
		System.out.println(formatDate);
		if (!StringUtils.hasText(transactions.getMonthYear())) {
			throw new UnauthorizedException("Some thing Went Wrong, Please go back to Transactions");
		}

		if (!formatDate.equalsIgnoreCase(transactions.getMonthYear())) {
			throw new UnauthorizedException(
					"Please choose " + transactions.getMonthYear() + " Month & Year Dates Only");
		}

		User user = UserUtil.getAuthenticatedUser();
		if (user != null) {
			transactions.setUpdatedBy(user.getRole());
			transactions.setUpdatedDate(new Date());
		}
		
		if (transactions.getType().equals(AppConstants.INCOME)) {
			transactions.setCreditAmount(transactions.getAmount());
			transactions.setDebitAmount(0);
		} else if (transactions.getType().equals(AppConstants.EXPENSE)) {
			transactions.setDebitAmount(transactions.getAmount());
			transactions.setCreditAmount(0);
		}

		Transactions dbProduct = transactionService.update(transactions);
		return new ResponseEntity<Transactions>(dbProduct, HttpStatus.OK);
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@PostMapping("/deleteMonthlyTransaction")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response> delete(@RequestBody Transactions transactions) {
		transactionService.delete(transactions.getId());
		return new ResponseEntity<Response>(Response.success("Transaction deleted successfully"), HttpStatus.OK);
	}
	
	
	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@GetMapping("/deleteMobileMonthlyTransaction/{transactionId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response> deleteMobileMonthlyTransaction(@PathVariable Long transactionId) {
		transactionService.delete(transactionId);
		return new ResponseEntity<Response>(Response.success("Transaction deleted successfully"), HttpStatus.OK);
	}

}
