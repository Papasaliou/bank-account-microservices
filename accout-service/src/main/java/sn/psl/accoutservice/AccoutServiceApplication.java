package sn.psl.accoutservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import sn.psl.accoutservice.clients.CustomerRestClient;
import sn.psl.accoutservice.entities.AccountType;
import sn.psl.accoutservice.entities.BankAccount;
import sn.psl.accoutservice.models.Customer;
import sn.psl.accoutservice.repository.BankAccountRepository;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccoutServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccoutServiceApplication.class, args);

    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        return args -> {
            customerRestClient.getAllCustomers().forEach(customer -> {
                BankAccount bankAccount1 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .balance(Math.random()*800000)
                        .currency("MAD")
                        .type(AccountType.CURRENT_ACCOUNT)
                        .date(LocalDate.now())
                        .customerId(customer.getId())
                        .build();
                BankAccount bankAccount2 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .balance(Math.random()*800000)
                        .currency("PAD")
                        .type(AccountType.SAVING_ACCOUNT)
                        .date(LocalDate.now())
                        .customerId(customer.getId())
                        .build();
                bankAccountRepository.save(bankAccount1);
                bankAccountRepository.save(bankAccount2);
            });

        };
    }

}
