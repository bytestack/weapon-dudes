package com.trivadis.blockchain.model;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import java.util.List;

/**
 * @author Marco Facetti
 */
public class Weapon {

    private String serialNumber;

    private List<WeaponTransaction> transactions;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public List<WeaponTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WeaponTransaction> transactions) {
        this.transactions = transactions;
    }
}
