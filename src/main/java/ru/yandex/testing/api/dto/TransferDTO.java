package ru.yandex.testing.api.dto;

import lombok.Value;

import java.beans.ConstructorProperties;

/**
 * @author fbokovikov
 */
@Value
public class TransferDTO {

    private final int fromId;
    private final int toId;
    private final double amount;

    @ConstructorProperties({"fromId", "toId", "amount"})
    public TransferDTO(int fromId, int toId, double amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }
}
