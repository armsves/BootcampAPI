package com.example.BootcampAPI.services.interfaces;

import com.example.BootcampAPI.Controllers.DTOs.BalanceDTO;
import com.example.BootcampAPI.Controllers.DTOs.CheckingDTO;
import com.example.BootcampAPI.entity.Checking;
import com.example.BootcampAPI.entity.Savings;
import com.example.BootcampAPI.entity.ThirdParty;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;

public interface AccountServiceInterface {
    Savings getSavingsById(Long id);
    Savings addSavingsAccount(Savings savings);
    ThirdParty addThirdPartyAccount(ThirdParty thirdParty);
    BalanceDTO getSavingsBalance(Long id);
    BalanceDTO makeTransfer(UserDetails userDetails, Long idMakerAccount, BigDecimal transferAmount, Long idReceiverAccount, String nameReceiver);
    BalanceDTO thirdPartyTransfer(String hashedKey, Long idReceiverAccount, String secretKey, BigDecimal transferAmount);

    CheckingDTO addCheckingAccount(CheckingDTO checkingAccount);
}
