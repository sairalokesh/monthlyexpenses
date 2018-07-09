package com.monthly.expenses.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The Class Transactions.
 *
 * @author G Lokesh
 */
@Entity
@Table(name = "transactions")
public class Transactions implements Serializable, Comparable<Transactions> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1214080525247624469L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "category")
	private String category;

	@Column(name = "transaction_description")
	private String description;

	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "credit_amount")
	private double creditAmount;

	@Column(name = "debit_amount")
	private double debitAmount;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "type")
	private String type;

	@Column(name = "location")
	private String location;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Transient
	private double amount;

	@Transient
	private String monthYear;

	@Transient
	private String dbTransactionDate;

	/**
	 * Instantiates a new Transaction.
	 */
	public Transactions() {
		super();
	}

	/**
	 * Instantiates a new Transaction.
	 *
	 * @param id
	 *            the id
	 * @param category
	 *            the category
	 * @param description
	 *            the description
	 */
	public Transactions(Long id, String category, String description) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
	}

	public Transactions(Long id, String description, Date transactionDate, String location, Double latitude,
			Double longitude, double amount) {
		super();
		this.id = id;
		this.description = description;
		this.transactionDate = transactionDate;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.amount = amount;
	}

	/**
	 * Instantiates a new Transaction.
	 *
	 * @param category
	 *            the category
	 * @param description
	 *            the description
	 * @param createdDate
	 *            the created date
	 * @param updatedDate
	 *            the updated date
	 */
	public Transactions(String category, String description, Date createdDate, Date updatedDate) {
		super();
		this.category = category;
		this.description = description;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Transactions(Long id, String category, String description, Date transactionDate, double creditAmount,
			double debitAmount, Date createdDate, Date updatedDate, String createdBy, String updatedBy, String type,
			double amount) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
		this.transactionDate = transactionDate;
		this.creditAmount = creditAmount;
		this.debitAmount = debitAmount;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.type = type;
		this.amount = amount;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate
	 *            the new updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy
	 *            the new updated by
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the creditAmount
	 */
	public double getCreditAmount() {
		return creditAmount;
	}

	/**
	 * @param creditAmount
	 *            the creditAmount to set
	 */
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * @return the debitAmount
	 */
	public double getDebitAmount() {
		return debitAmount;
	}

	/**
	 * @param debitAmount
	 *            the debitAmount to set
	 */
	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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

	/**
	 * @return the dbTransactionDate
	 */
	public String getDbTransactionDate() {
		return dbTransactionDate;
	}

	/**
	 * @param dbTransactionDate
	 *            the dbTransactionDate to set
	 */
	public void setDbTransactionDate(String dbTransactionDate) {
		this.dbTransactionDate = dbTransactionDate;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", category=" + category + ", description=" + description
				+ ", transactionDate=" + transactionDate + ", creditAmount=" + creditAmount + ", debitAmount="
				+ debitAmount + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", type=" + type + ", location=" + location + ", amount="
				+ amount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Transactions p) {
		return this.getCategory().compareTo(p.getCategory());
	}
}
