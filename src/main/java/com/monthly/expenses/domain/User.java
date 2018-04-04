
package com.monthly.expenses.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class User.
 *
 * @author G Lokesh
 */
@Entity
@Table(name = "user")
public class User implements Serializable, Comparable<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4984994125698570446L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "orginal_password")
	private String orginalPassword;

	@Column(name = "role")
	private String role;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "is_activated")
	private Boolean isActivated = false;

	@Column(name = "is_temp_password")
	private Boolean isTempPassword = false;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "password_changed_date")
	private Date passwordChangedDate;

	@Transient
	private Boolean isGeneratePassowrd = false;

	@Transient
	private MultipartFile userProfile;

	@Transient
	private MultipartFile backgroundImage;

	@Column(name = "user_profile_name")
	private String userProfileName;

	@Column(name = "background_image_name")
	private String backgroundImageName;

	@Column(name = "signature_name")
	private String signatureName;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id
	 *            the id
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param role
	 *            the role
	 */
	public User(Long id, String email, String password, String firstName, String lastName, String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @param createdDate
	 *            the created date
	 * @param updatedDate
	 *            the updated date
	 * @param role
	 *            the role
	 */
	public User(String firstName, String lastName, String email, String password, Date createdDate, Date updatedDate,
			String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
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
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role
	 *            the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the checks if is activated.
	 *
	 * @return the checks if is activated
	 */
	public Boolean getIsActivated() {
		return isActivated;
	}

	/**
	 * Sets the checks if is activated.
	 *
	 * @param isActivated
	 *            the new checks if is activated
	 */
	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * Gets the checks if is temp password.
	 *
	 * @return the checks if is temp password
	 */
	public Boolean getIsTempPassword() {
		return isTempPassword;
	}

	/**
	 * Sets the checks if is temp password.
	 *
	 * @param isTempPassword
	 *            the new checks if is temp password
	 */
	public void setIsTempPassword(Boolean isTempPassword) {
		this.isTempPassword = isTempPassword;
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

	public Date getPasswordChangedDate() {
		return passwordChangedDate;
	}

	public void setPasswordChangedDate(Date passwordChangedDate) {
		this.passwordChangedDate = passwordChangedDate;
	}

	public Boolean getIsGeneratePassowrd() {
		return isGeneratePassowrd;
	}

	public void setIsGeneratePassowrd(Boolean isGeneratePassowrd) {
		this.isGeneratePassowrd = isGeneratePassowrd;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the userProfile
	 */
	public MultipartFile getUserProfile() {
		return userProfile;
	}

	/**
	 * @param userProfile
	 *            the userProfile to set
	 */
	public void setUserProfile(MultipartFile userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * @return the backgroundImage
	 */
	public MultipartFile getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * @param backgroundImage
	 *            the backgroundImage to set
	 */
	public void setBackgroundImage(MultipartFile backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	/**
	 * @return the userProfileName
	 */
	public String getUserProfileName() {
		return userProfileName;
	}

	/**
	 * @param userProfileName
	 *            the userProfileName to set
	 */
	public void setUserProfileName(String userProfileName) {
		this.userProfileName = userProfileName;
	}

	/**
	 * @return the backgroundImageName
	 */
	public String getBackgroundImageName() {
		return backgroundImageName;
	}

	/**
	 * @param backgroundImageName
	 *            the backgroundImageName to set
	 */
	public void setBackgroundImageName(String backgroundImageName) {
		this.backgroundImageName = backgroundImageName;
	}

	public String getSignatureName() {
		return signatureName;
	}

	public void setSignatureName(String signatureName) {
		this.signatureName = signatureName;
	}

	public String getOrginalPassword() {
		return orginalPassword;
	}

	public void setOrginalPassword(String orginalPassword) {
		this.orginalPassword = orginalPassword;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", phoneNumber=" + phoneNumber + ", isActivated="
				+ isActivated + ", isTempPassword=" + isTempPassword + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", passwordChangedDate="
				+ passwordChangedDate + ", isGeneratePassowrd=" + isGeneratePassowrd + ", userProfile=" + userProfile
				+ ", backgroundImage=" + backgroundImage + ", userProfileName=" + userProfileName
				+ ", backgroundImageName=" + backgroundImageName + ", signatureName=" + signatureName + "]";
	}

	@Override
	public int compareTo(User u) {
		return this.getFirstName().compareTo(u.getFirstName());
	}

}
