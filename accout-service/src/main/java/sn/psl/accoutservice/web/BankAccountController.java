package sn.psl.accoutservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sn.psl.accoutservice.clients.CustomerRestClient;
import sn.psl.accoutservice.entities.BankAccount;
import sn.psl.accoutservice.models.Customer;
import sn.psl.accoutservice.repository.BankAccountRepository;

import java.util.List;

@RestController
public class BankAccountController {

    private final BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;
    public BankAccountController(BankAccountRepository accountRepository,CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> bankAccountList = accountRepository.findAll();
        bankAccountList.forEach(account ->{
            account.setCustomer(customerRestClient.getCustomerById(account.getCustomerId()));
        });
        return bankAccountList;
    }

    @GetMapping("/accounts/{accountNumber}")
    public BankAccount getAccount(@PathVariable String accountNumber) {
      BankAccount bankAccount = accountRepository.findById(accountNumber).get();
      Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
      bankAccount.setCustomer(customer);
      return bankAccount;
    }
}
