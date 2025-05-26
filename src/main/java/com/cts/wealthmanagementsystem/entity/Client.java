package com.cts.wealthmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer identity;

    private String fullName;
    private String emailAddress;
    private String userName;
    private String passWord;
    private String dateOfBirth; // New field added

    private boolean isApproved = false; // Draft status

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public String getFullName() {
		return fullName;
	}

	@Override
	public String toString() {
		return "FinancialAdvisor [identity=" + identity + ", fullName=" + fullName + ", emailAddress=" + emailAddress
				+ ", userName=" + userName + ", passWord=" + passWord + ", dateOfBirth=" + dateOfBirth + ", isApproved="
				+ isApproved + "]";
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isApproved() {
		return isApproved;
	}



	public void setApproved(boolean isApproved) {
		// TODO Auto-generated method stub
		this.isApproved = isApproved;
	}
	

}
