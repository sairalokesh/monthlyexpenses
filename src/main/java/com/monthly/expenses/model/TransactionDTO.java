package com.monthly.expenses.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionDTO {

	private String year;
	private List<DbTransactionDTO> transactions = new ArrayList<DbTransactionDTO>();

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the transactions
	 */
	public List<DbTransactionDTO> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions
	 *            the transactions to set
	 */
	public void setTransactions(List<DbTransactionDTO> transactions) {
		this.transactions = transactions;
	}

}
