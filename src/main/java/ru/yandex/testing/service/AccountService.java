package ru.yandex.testing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.testing.jpa.model.Account;
import ru.yandex.testing.jpa.repository.AccountRepository;

import java.util.Optional;

/**
 * @author fbokovikov
 */
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(double amount) {
        var account = new Account();
        account.setAmount(amount);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(int id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public void deposit(int id, double amount) {
        accountRepository.deposit(id, amount);
    }
}
