package com.cts.wealthmanagementsystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Client {
	   
	 
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer id;
	    private String name;
        private String email;
        private String password;
        private Integer phoneNumber;
        private String address;
        private LocalDate dateOfBirth;
        
       
       
       
}
