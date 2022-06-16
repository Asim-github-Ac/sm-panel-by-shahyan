package com.mytech.smmpanel.Admins.user;

import java.io.Serializable;

public class UserModel implements Serializable {

    String id, user, email, skype, balance, spent, status, created, lastAuth, rates, actions;

    public UserModel(String id, String user, String email, String skype, String balance, String spent, String status, String created, String lastAuth, String rates, String actions) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.skype = skype;
        this.balance = balance;
        this.spent = spent;
        this.status = status;
        this.created = created;
        this.lastAuth = lastAuth;
        this.rates = rates;
        this.actions = actions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSpent() {
        return spent;
    }

    public void setSpent(String spent) {
        this.spent = spent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastAuth() {
        return lastAuth;
    }

    public void setLastAuth(String lastAuth) {
        this.lastAuth = lastAuth;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
}
