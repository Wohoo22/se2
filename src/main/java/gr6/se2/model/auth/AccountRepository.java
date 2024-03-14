package gr6.se2.model.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Integer, Account> {
    Account findByUsername(String username);
}
