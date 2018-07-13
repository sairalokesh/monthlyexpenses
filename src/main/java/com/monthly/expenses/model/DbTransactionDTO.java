package com.monthly.expenses.model;

public class DbTransactionDTO {

	private Double income;
	private Double expense;
	private String monthYear;
	private Double amount;
	
	public DbTransactionDTO(Double income, Double expense, String monthYear) {
		super();
		this.income = income;
		this.expense = expense;
		this.monthYear = monthYear;
	}
	
	
	public DbTransactionDTO(Double amount,String monthYear) {
		super();
		this.monthYear = monthYear;
		this.amount = amount;
	}



	/**
	 * @return the income
	 */
	public Double getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(Double income) {
		this.income = income;
	}

	/**
	 * @return the expense
	 */
	public Double getExpense() {
		return expense;
	}

	/**
	 * @param expense
	 *            the expense to set
	 */
	public void setExpense(Double expense) {
		this.expense = expense;
	}

	/**
	 * @return the monthYear
	 */
	public String getMonthYear() {
		return monthYear;
	}

	/**
	 * @param monthYear
	 *            the monthYear to set
	 */
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
