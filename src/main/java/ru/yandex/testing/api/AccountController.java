package ru.yandex.testing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import ru.yandex.testing.api.dto.AccountDTO;
import ru.yandex.testing.jpa.model.Account;
import ru.yandex.testing.service.AccountService;

/**
 * @author fbokovikov
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountDTO createAccount(@RequestParam double amount) {
        Account account = accountService.createAccount(amount);
        return new AccountDTO(account);
    }

    @GetMapping("/{id}")
    public AccountDTO getAccount(@PathVariable int id) {
        Account account = accountService.getAccount(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        return new AccountDTO(account);
    }
}
