package com.trivadis.blockchain.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trivadis.blockchain.model.Person;
import com.trivadis.blockchain.model.Weapon;
import com.trivadis.blockchain.model.WeaponTransaction;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeaponTransactionControllerTest {

    @Autowired
    WeaponTransactionController testee;

    @Test
    public void transaktion_putWeaponTransaction_ok() throws JsonProcessingException {
        //given
        Person seller = new Person();
        seller.setWeaponRegisterNumber("123");

        Person buyer = new Person();
        buyer.setWeaponRegisterNumber("234");

        WeaponTransaction weaponTransaction = new WeaponTransaction();
        weaponTransaction.setTimestamp(OffsetDateTime.now());

        Weapon weapon = new Weapon();
        weapon.setSerialNumber("999");
        weapon.setTransactions(Collections.singletonList(weaponTransaction));

        //when
        final String result = testee.putWeaponTransaction(seller, buyer, weapon);

        //then
        assertThat(result, Matchers.equalTo("redirect:/transactions"));

    }


}