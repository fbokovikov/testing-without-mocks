package ru.yandex.testing.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.yandex.testing.jpa.entity.Account;

import javax.persistence.LockModeType;

/**
 * @author fbokovikov
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Query("update Account set amount = amount + :amount where id = :id")
    void deposit(@Param("id") int accountId, @Param("amount") double amount);

    @Modifying
    @Query("update Account set amount = :amount where id = :id")
    void updateAmount(@Param("id") int accountId, @Param("amount") double amount);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select a from Account a where id = :id")
    Account lockAccount(@Param("id") int id);
}
