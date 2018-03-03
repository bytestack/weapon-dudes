package com.trivadis.blockchain.model;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * @author Marco Facetti
 */
@Data
public class WeaponTransaction {

    private OffsetDateTime timestamp;

    private Person buyer;

    private Person seller;


}
