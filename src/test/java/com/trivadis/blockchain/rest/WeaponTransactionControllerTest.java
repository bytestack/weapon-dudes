package com.trivadis.blockchain.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeaponTransactionControllerTest {

    @Autowired
    WeaponTransactionController testee;

    @Test
    public void transaktion_putWeaponTransaction_ok() throws JsonProcessingException {
        //given
        WeaponTransactionDto dto = new WeaponTransactionDto();
        dto.setBuyerWeaponRegisterNumber("123");
        dto.setSellerWeaponRegisterNumber("234");
        dto.setWeaponSerialNumber("999");

        //when
        final String result = testee.putWeaponTransaction(dto);

        //then
        assertThat(result, Matchers.equalTo("redirect:/transactions"));
    }
}