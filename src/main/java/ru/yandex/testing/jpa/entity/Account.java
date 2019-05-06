package ru.yandex.testing.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fbokovikov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Account")
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;
}
