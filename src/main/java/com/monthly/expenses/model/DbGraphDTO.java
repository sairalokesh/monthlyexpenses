package com.monthly.expenses.model;

public class DbGraphDTO {

	private String category;
	private Double income;
	private Double expenses;
	private String monthYear;

	public DbGraphDTO(String category, Double income, Double expenses, String monthYear) {
		super();
		this.category = category;
		this.income = income;
		this.expenses = expenses;
		this.monthYear = monthYear;
	}

	public DbGraphDTO(String category, Double expenses) {
		super();
		this.category = category;
		this.expenses = expenses;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * @return the expenses
	 */
	public Double getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses
	 *            the expenses to set
	 */
	public void setExpenses(Double expenses) {
		this.expenses = expenses;
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

}
