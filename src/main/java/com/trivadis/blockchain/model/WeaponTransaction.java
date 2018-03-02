package com.trivadis.blockchain.model;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import java.time.OffsetDateTime;

/**
 * @author Marco Facetti
 */
public class WeaponTransaction {

    private OffsetDateTime timestamp;

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
