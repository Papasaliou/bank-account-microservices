package sn.psl.accoutservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.psl.accoutservice.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
