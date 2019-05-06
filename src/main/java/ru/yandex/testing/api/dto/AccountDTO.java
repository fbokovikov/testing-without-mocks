package ru.yandex.testing.api.dto;

import lombok.Value;
import ru.yandex.testing.jpa.model.Account;

import java.beans.ConstructorProperties;

/**
 * @author fbokovikov
 */
@Value
public class AccountDTO {

    private final int id;
    private final double amount;

    @ConstructorProperties({"id", "amount"})
    public AccountDTO(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public AccountDTO(Account account) {
        this.amount = account.getAmount();
        this.id = account.getId();
    }
}
