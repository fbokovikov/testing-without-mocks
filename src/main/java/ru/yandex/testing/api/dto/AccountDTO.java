package ru.yandex.testing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.yandex.testing.jpa.model.Account;

import java.beans.ConstructorProperties;

/**
 * @author fbokovikov
 */
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

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }
}
