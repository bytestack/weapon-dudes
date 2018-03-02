package com.trivadis.blockchain.rest;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Marco Facetti
 */
@Controller
public class WeaponTransactionController {

    @GetMapping("/transactions")
    public String getAllWeaponTransactions() {

        return "test";
    }

    @PostMapping("/transactions")
    public String putWeaponTransaction() {

        return "redirect:/transactions";
    }


}
