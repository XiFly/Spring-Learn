package com.example.accessingdatajpa;

import com.example.accessingdatajpa.pojo.Customer;
import com.example.accessingdatajpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            repository.save(new Customer("Wang","Xiangfei"));
            repository.save(new Customer("Wang","Xiangwei"));
            repository.save(new Customer("Wang","Bin"));
            repository.save(new Customer("Wang","Honggui"));
            repository.save(new Customer("Feng","Lan"));
            repository.save(new Customer("Zeng","Danlian"));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('xiangfei'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Xiangfei").forEach(xiangfei -> {
                log.info(xiangfei.toString());
            });
            // for (Customer bauer : repository.findByLastName("xiangfei")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
