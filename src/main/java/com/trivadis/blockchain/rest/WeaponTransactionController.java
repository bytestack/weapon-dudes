package com.trivadis.blockchain.rest;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trivadis.blockchain.dto.ChainDto;
import com.trivadis.blockchain.dto.ChaincodeID;
import com.trivadis.blockchain.dto.CtorMsg;
import com.trivadis.blockchain.dto.Params;
import com.trivadis.blockchain.model.Person;
import com.trivadis.blockchain.model.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Marco Facetti
 */
@Controller
public class WeaponTransactionController {

    public static final String HOST = "http://localhost:7050";
    public static final String CHAINCODE_ENDPOINT = "/chaincode";
    public static final String CHAINCODE_ID = "weapondudes";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/transactions")
    public String getAllWeaponTransactions() {

        return "transactions/list";
    }

    @GetMapping("/transactions/new")
    public String getNewWeaponTransaction(Model model) {
        model.addAttribute("weaponTransaction", new WeaponTransactionDto());

        return "transactions/new";
    }

    @PostMapping("/transactions")
    public String putWeaponTransaction(Person seller, Person buyer, Weapon weapon) throws JsonProcessingException {

        ChainDto chainDto = buildChainDtoObject(seller, buyer, weapon);

        restTemplate.postForObject(HOST + CHAINCODE_ENDPOINT, chainDto, ChainDto.class);

        return "redirect:/transactions";
    }

    static ChainDto buildChainDtoObject(Person seller, Person buyer, Weapon weapon) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String transaktionJson = mapper.writeValueAsString(weapon);

        ChaincodeID chaincodeID = new ChaincodeID();
        chaincodeID.setName(CHAINCODE_ID);

        CtorMsg ctorMsg = new CtorMsg();
        ctorMsg.setArgs(new ArrayList<>(Arrays.asList(
                seller.getWeaponRegisterNumber(),
                buyer.getWeaponRegisterNumber(),
                transaktionJson
        )));

        Params params = new Params();
        params.setChaincodeID(chaincodeID);
        params.setCtorMsg(ctorMsg);

        ChainDto chainDto = new ChainDto();
        chainDto.setId(1);
        chainDto.setJsonrpc("2.0");
        chainDto.setMethod("invoke");
        chainDto.setParams(params);
        return chainDto;
    }
}
