package com.trivadis.blockchain.service;

import com.trivadis.blockchain.model.Person;
import com.trivadis.blockchain.model.Weapon;
import com.trivadis.blockchain.model.WeaponTransaction;
import com.trivadis.blockchain.rest.WeaponTransactionDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class WeaponService {

    public Person getCurrentOwner(Weapon weapon) {
        if (weapon != null) {
            List<WeaponTransaction> transactions = weapon.getTransactions();
            if (!CollectionUtils.isEmpty(transactions)) {
                return transactions.get(transactions.size() - 1).getBuyer();
            }
        }
        return null;
    }

    public boolean weaponBelongsToOwner(Weapon weapon, String owerWeaponRegisterNumber) {
        if (weapon != null) {
            Person currentOwner = getCurrentOwner(weapon);
            if (currentOwner != null) {
                return currentOwner.getWeaponRegisterNumber().equals(owerWeaponRegisterNumber);
            }
        }

        return true;
    }

    public WeaponTransaction createNewWeaponTransaction(WeaponTransactionDto weaponTransactionDto) {
        WeaponTransaction newWeaponTx = new WeaponTransaction();
        newWeaponTx.setSeller(Person.builder().weaponRegisterNumber(weaponTransactionDto.getSellerWeaponRegisterNumber()).build());
        newWeaponTx.setBuyer(Person.builder().weaponRegisterNumber(weaponTransactionDto.getBuyerWeaponRegisterNumber()).build());
        newWeaponTx.setTimestamp(OffsetDateTime.now());
        return newWeaponTx;
    }

}
