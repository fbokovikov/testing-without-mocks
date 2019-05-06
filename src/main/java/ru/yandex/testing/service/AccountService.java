package ru.yandex.testing.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
