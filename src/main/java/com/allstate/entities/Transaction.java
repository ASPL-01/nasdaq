package com.allstate.entities;

import com.allstate.enums.Action;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    private int id;
    private int version;
    private Action action;
    private String symbol;
    private int quantity;
    private double amount;
    private User user;
    private Date created;
    private Date modified;

    public Transaction() {
    }
    public Transaction(Action action, String symbol, int quantity, double amount, User user) {
        this.action = action;
        this.symbol = symbol;
        this.quantity = quantity;
        this.amount = amount;
        this.user = user;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Column(nullable = false, columnDefinition = "ENUM('BUY', 'SELL')")
    @Enumerated(EnumType.STRING)
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }

    @Column(nullable = false)
    @Size(min = 1)
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(nullable = false)
    @Min(value = 1)
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(nullable = false)
    @DecimalMin(value = "0")
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @CreationTimestamp
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    @UpdateTimestamp
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
}
