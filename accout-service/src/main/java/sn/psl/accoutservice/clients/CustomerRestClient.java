package sn.psl.accoutservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.psl.accoutservice.models.Customer;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{customerId}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long customerId);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultAllCustomers")
    List<Customer> getAllCustomers();

    default Customer getDefaultCustomer(Long customerId,Exception exception) {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("not vailable");
        customer.setLastName("not vailable");
        customer.setEmail("not vailable");
        return customer;
    }

    default List<Customer> getDefaultAllCustomers(Exception exception) {
        return List.of();
    }
}
