package ru.yandex.testing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.testing.jpa.entity.Account;
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

    @Transactional
    public void transfer(int fromId, int toId, double amount) {
        int less = fromId < toId ? fromId : toId;
        int more = fromId < toId ? toId : fromId;

        Account lessAccount = accountRepository.lockAccount(less);
        Account moreAccount = accountRepository.lockAccount(more);

        Account from = less == fromId ? lessAccount : moreAccount;
        Account to = more == toId ? moreAccount : lessAccount;

        if (from.getAmount() < amount) {
            throw new IllegalStateException("Not enough money for transfer");
        }

        accountRepository.updateAmount(fromId, from.getAmount() - amount);
        accountRepository.updateAmount(toId, to.getAmount() + amount);
    }
}
