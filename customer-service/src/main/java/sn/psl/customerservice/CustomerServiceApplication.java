package sn.psl.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import sn.psl.customerservice.config.GlobalConfig;
import sn.psl.customerservice.entities.Customer;
import sn.psl.customerservice.repository.CustomerRepository;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository ) {
		return args -> {
			List<Customer> customerList=List.of(
			 Customer.builder()
					.firstName("Abdou Khoudoss")
					.lastName("GUEYE")
					 .email("abdou@gmail.com")
					.build(),
			 Customer.builder()
					.firstName("Maty")
					.lastName("DIOP")
					 .email("maty@gmail.com")
					.build(),
			Customer.builder()
					.firstName("Faty")
					.lastName("MBAYE")
					.email("faty@gmail.com")
					.build(),
			Customer.builder()
					.firstName("Amy")
					.lastName("LO")
					.email("amy@gmail.com")
					.build());
			customerRepository.saveAll(customerList);
		};
	}

}
