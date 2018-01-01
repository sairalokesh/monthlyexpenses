package com.monthly.expenses.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monthly.expenses.constant.AppConstants;
import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.domain.User;
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
	    public ResponseEntity<Transactions> create(@RequestBody Transactions product) {
	    	User user = UserUtil.getAuthenticatedUser();
	    	if(user!=null) {
	    		product.setCreatedBy(user.getRole());
	    		product.setCreatedDate(new Date());
	    	}
	    	if(product.getType().equals(AppConstants.INCOME)) {
	    		product.setCreditAmount(product.getAmount());
	    	}else if(product.getType().equals(AppConstants.EXPENSE)) {
	    		product.setDebitAmount(product.getAmount());
	    	}
	    	
	    	product = transactionService.save(product);
	        return new ResponseEntity<Transactions>(product, HttpStatus.OK);
	    }
	    
	    /**
	     * Update.
	     *
	     * @param product
	     *            the product
	     * @return the response entity
	     */
	    @PostMapping("/update")
	    @PreAuthorize("hasRole('USER')")
	    public ResponseEntity<Transactions> update(@RequestBody Transactions product) {
	    	User user = UserUtil.getAuthenticatedUser();
	    	if(user!=null) {
	    		product.setUpdatedBy(user.getRole());
	    		product.setUpdatedDate(new Date());
	    	}
	    	Transactions dbProduct = transactionService.update(product);
	        return new ResponseEntity<Transactions>(dbProduct, HttpStatus.OK);
	    }
	    
	    /**
	     * Delete.
	     *
	     * @param id
	     *            the id
	     * @return the response entity
	     */
	    @PostMapping("/delete")
	    @PreAuthorize("hasRole('USER')")
	    public ResponseEntity<Response> delete(@RequestBody Transactions product) {
	    	transactionService.delete(product.getId());
	        return new ResponseEntity<Response>(Response.success("Product deleted successfully"), HttpStatus.OK);
	    }


}
