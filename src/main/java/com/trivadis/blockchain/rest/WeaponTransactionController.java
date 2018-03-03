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
import com.trivadis.blockchain.model.WeaponTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Marco Facetti
 */
@Controller
public class WeaponTransactionController {

    final static Logger log = LoggerFactory.getLogger(WeaponTransactionController.class);

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
    public String putWeaponTransaction(@ModelAttribute WeaponTransactionDto weaponTransactionDto) throws JsonProcessingException {
        ChainDto chainDto = buildChainDtoObject(weaponTransactionDto);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(HOST + CHAINCODE_ENDPOINT, chainDto, String.class);

        log.info("Status: " + stringResponseEntity.getStatusCode().toString());

        return "redirect:/transactions";
    }

    static ChainDto buildChainDtoObject(@ModelAttribute WeaponTransactionDto weaponTransactionDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // FIXME load existing weapon from blockchain
        Weapon weapon = new Weapon();

        List<WeaponTransaction> transactions = weapon.getTransactions();
        if (!CollectionUtils.isEmpty(transactions)) {
            // check if weapon belong the the seller of the transaction
            if (weaponTransactionDto.getSellerWeaponRegisterNumber().equals(transactions.get(transactions.size() - 1).getBuyer().getWeaponRegisterNumber())) {
                // create and add new weapon transaction
                WeaponTransaction newWeaponTx = createNewWeaponTransaction(weaponTransactionDto);
                transactions.add(newWeaponTx);

                // TODO save the weapon to the blockchain

            }
        } else {
            // complete new weapon
            weapon.setTransactions(new ArrayList<>());


        }

        String transaktionJson = mapper.writeValueAsString(weapon);

        ChaincodeID chaincodeID = new ChaincodeID();
        chaincodeID.setName(CHAINCODE_ID);

        CtorMsg ctorMsg = new CtorMsg();
        ctorMsg.setArgs(new ArrayList<>(Arrays.asList(
                "put",
                weaponTransactionDto.getBuyerWeaponRegisterNumber(),
                transaktionJson
        )));

        Params params = new Params();
        params.setChaincodeID(chaincodeID);
        params.setCtorMsg(ctorMsg);
        params.setType(1);

        ChainDto chainDto = new ChainDto();
        chainDto.setId(1);
        chainDto.setJsonrpc("2.0");
        chainDto.setMethod("invoke");
        chainDto.setParams(params);
        return chainDto;
    }

    private static WeaponTransaction createNewWeaponTransaction(@ModelAttribute WeaponTransactionDto weaponTransactionDto) {
        WeaponTransaction newWeaponTx = new WeaponTransaction();
        newWeaponTx.setSeller(Person.builder().weaponRegisterNumber(weaponTransactionDto.getSellerWeaponRegisterNumber()).build());
        newWeaponTx.setBuyer(Person.builder().weaponRegisterNumber(weaponTransactionDto.getBuyerWeaponRegisterNumber()).build());
        newWeaponTx.setTimestamp(OffsetDateTime.now());
        return newWeaponTx;
    }

}
