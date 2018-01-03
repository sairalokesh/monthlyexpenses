package com.monthly.expenses.model;

import java.util.Date;

import com.monthly.expenses.domain.User;

public class StatisticDTO {

	private Date startDate;
	private Date endDate;
	private Double income = 0.00;
	private Double expenses = 0.00;
	private String invoiceNumber;
	private String dbStartDate;
	private String dbEndDate;
	private User user;

	public StatisticDTO() {
		super();
	}

	public StatisticDTO(Double expenses) {
		super();
		this.expenses = expenses;
	}

	public StatisticDTO(Double income, Double expenses) {
		super();
		this.income = income;
		this.expenses = expenses;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *            the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the dbStartDate
	 */
	public String getDbStartDate() {
		return dbStartDate;
	}

	/**
	 * @param dbStartDate
	 *            the dbStartDate to set
	 */
	public void setDbStartDate(String dbStartDate) {
		this.dbStartDate = dbStartDate;
	}

	/**
	 * @return the dbEndDate
	 */
	public String getDbEndDate() {
		return dbEndDate;
	}

	/**
	 * @param dbEndDate
	 *            the dbEndDate to set
	 */
	public void setDbEndDate(String dbEndDate) {
		this.dbEndDate = dbEndDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
