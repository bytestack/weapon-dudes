package com.trivadis.blockchain.model;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Marco Facetti
 */
@Data
public class Weapon {
    @JsonProperty
    private String serialNumber;

    @JsonProperty
    private List<WeaponTransaction> transactions;
}
