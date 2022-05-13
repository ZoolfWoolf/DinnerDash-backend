package com.dinnerdash.backend;

import java.util.List;

import com.dinnerdash.backend.models.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbc;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        String sql = "SELECT * FROM Customer";
        List<Customer> customers = jdbc.query(sql,
                BeanPropertyRowMapper.newInstance(Customer.class));
        customers.forEach(System.out :: println);
    }
}
