package com.monthly.expenses.service;

import java.util.Date;
import java.util.List;

import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.model.GraphDTO;
import com.monthly.expenses.model.PieGraphDTO;
import com.monthly.expenses.model.StatisticDTO;
import com.monthly.expenses.model.TransactionDTO;

public interface TransactionService {
	
	 /**
     * Find all.
     *
     * @return the list
     */
    List<TransactionDTO> findAll(Long userId);
	
	 /**
     * Save.
     *
     * @param Transactions
     *            the Product
     * @return the Product
     */
    Transactions save(Transactions product);
	

    /**
     * Update Product.
     *
     * @param Transactions
     *            the Product
     * @return the Product
     */
    Transactions update(Transactions product);
    
    /**
     * Delete.
     *
     * @param id
     *            the id
     */
    void delete(Long id);

	StatisticDTO getStatistics(Date startDate, Date endDate, Long userId);

	List<Transactions> getTransactions(Date startDate, Date endDate, Long userId);

	GraphDTO getYearlyTransaction(Long userId);

	GraphDTO getMonthlyransaction(Date startDate, Date endDate, Long userId);

	PieGraphDTO getMonthlyChart(Date startDate, Date endDate, Long userId);

	GraphDTO getMonthlytransactionsCount(Date startDate, Date endDate, Long userId);

   

   

    

	
	
	
	

}
