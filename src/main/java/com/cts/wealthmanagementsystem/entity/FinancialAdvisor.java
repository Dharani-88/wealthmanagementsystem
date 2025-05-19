package com.cts.wealthmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FinancialAdvisor {
	   
	   private String fullName;
	  
	    private String emailAddress;
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer identity;
	
       private String userName;
       private String passWord;
       
       
       
}
