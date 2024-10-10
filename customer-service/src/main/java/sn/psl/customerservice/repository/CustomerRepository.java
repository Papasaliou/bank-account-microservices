package sn.psl.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.psl.customerservice.entities.Customer;

//@RepositoryRestResource //c'est une anotation de SpringDataRest
// permet de demarrer un web service rest full qui permet degerer les customer
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
