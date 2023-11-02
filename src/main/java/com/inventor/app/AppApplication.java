package com.inventor.app;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.PersistenceContext;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AppApplication.class, args);
	}


  
}
