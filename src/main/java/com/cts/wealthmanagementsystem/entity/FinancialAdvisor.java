package com.cts.wealthmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FinancialAdvisor {
       @Id
       private String fullName;
       private String emailAddress;
       private String userName;
       private String passWord;
       
       
       
}
