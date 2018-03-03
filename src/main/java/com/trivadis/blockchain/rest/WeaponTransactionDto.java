package com.trivadis.blockchain.rest;

import lombok.Data;

@Data
public class WeaponTransactionDto {

    private String buyerWeaponRegisterNumber;

    private String sellerWeaponRegisterNumber;

    private String weaponSerialNumber;

}
