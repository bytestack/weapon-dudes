package com.trivadis.blockchain.model;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import lombok.Data;

import java.util.List;

/**
 * @author Marco Facetti
 */
@Data
public class Weapon {

    private String serialNumber;

    private List<WeaponTransaction> transactions;
}
