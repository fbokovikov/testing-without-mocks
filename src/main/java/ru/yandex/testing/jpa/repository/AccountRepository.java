package ru.yandex.testing.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.testing.jpa.model.Account;

/**
 * @author fbokovikov
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
