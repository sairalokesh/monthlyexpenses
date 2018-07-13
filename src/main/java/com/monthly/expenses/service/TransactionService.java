package com.monthly.expenses.service;

import java.util.Date;
import java.util.List;

import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.model.GraphDTO;
import com.monthly.expenses.model.PieGraphDTO;
import com.monthly.expenses.model.StatisticDTO;
import com.monthly.expenses.model.TransactionDTO;

public interface TransactionService {
	
	/* Start Dashboard Functionality */
	
	Long getStatisticsCount(Date startDate, Date endDate, Long userId);
	StatisticDTO getStatistics(Date startDate, Date endDate, Long userId);
	GraphDTO getMonthlyransaction(Date startDate, Date endDate, Long userId);
	GraphDTO getYearlyTransaction(Long userId);
	PieGraphDTO getMonthlyChart(Date startDate, Date endDate, Long userId);
	List<Transactions> getTransactions(Date startDate, Date endDate, Long userId);
	
	/* End Dashboard Functionality */
	
	/* Start Transaction Tab Functionality */
	
	List<TransactionDTO> findAll(Long userId);
    Transactions save(Transactions transaction);
    GraphDTO getMonthlytransactionsCount(Date startDate, Date endDate, Long userId);
    void delete(Long id);
    Transactions findById(Long transactionId);
    Transactions update(Transactions transaction);
    
    /* End Transaction Tab Functionality */
	
	/* Start Categories Tab Functionality */
    
    List<TransactionDTO> getSelectedCategory(String category, Long userId);
    GraphDTO getMonthcategorytransactionsCount(String category,StatisticDTO dateRange, Long userId);
    List<Transactions> getCategoryTransactions(String category,StatisticDTO dateRange, Long userId);
    StatisticDTO getSelectedCategoryMonthly(String category, Date startDate, Date endDate, Long userId);
    
    /* End Categories Functionality */
    
    /* Start  Range Transactions Functionality */
    
    GraphDTO getRangetransactionsCount(Date startDate, Date endDate, Long userId);
	
    
    /* End Range Transaction Functionality */
	


	
	

}
