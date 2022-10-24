package com.example.BootcampAPI.Controllers;

import com.example.BootcampAPI.Controllers.DTOs.BalanceDTO;
import com.example.BootcampAPI.Controllers.DTOs.CheckingDTO;
import com.example.BootcampAPI.Controllers.interfaces.AccountControllerInterface;
import com.example.BootcampAPI.embeddables.Money;
import com.example.BootcampAPI.entity.*;
import com.example.BootcampAPI.repositories.*;
import com.example.BootcampAPI.services.AccountService;
import com.example.BootcampAPI.services.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@RestController
public class AccountController implements AccountControllerInterface {
    @Autowired
    AccountServiceInterface accountServiceInterface;

    @PostMapping("/admin/addSavingsAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addSavingsAccount(@RequestBody @Valid Savings savingsAccount) {
        return accountServiceInterface.addSavingsAccount(savingsAccount);
    }

    @PostMapping("/accounts/makeTransfer")
    @ResponseStatus(HttpStatus.CREATED)
    public BalanceDTO makeTransfer(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam Long idMakerAccount,
                                @RequestParam BigDecimal transferAmount,
                                @RequestParam Long idReceiverAccount,
                                @RequestParam String nameReceiver
                                ) {
        return accountServiceInterface.makeTransfer(userDetails, idMakerAccount, transferAmount, idReceiverAccount, nameReceiver);
    }

    @PostMapping("/accounts/thirdPartyTransfer")
    @ResponseStatus(HttpStatus.OK)
    public BalanceDTO thirdPartyTransfer(@RequestHeader(value = "hashedKey") String hashedKey,
                                         @RequestParam BigDecimal transferAmount,
                                         @RequestParam Long idReceiverAccount,
                                         @RequestParam String secretKey) {
        return accountServiceInterface.thirdPartyTransfer(hashedKey, idReceiverAccount, secretKey, transferAmount);
    }


    @PostMapping("/admin/addCheckingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckingDTO addCheckingAccount(@RequestBody CheckingDTO checkingAccount) {
        return accountServiceInterface.addCheckingAccount(checkingAccount);
    }

    @PostMapping("/admin/addThirdPartyAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdPartyAccount(@RequestBody ThirdParty thirdParty) {
        return accountServiceInterface.addThirdPartyAccount(thirdParty);
    }

    @RequestMapping(value = "/admin/getSavingsAccount/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Savings getSavingsAccount(@PathVariable Long id) {
        return accountServiceInterface.getSavingsById(id);
    }



//    @GetMapping("/admin/getSavingsBalance/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public BalanceDTO getSavingsBalance(@PathVariable Long id) {
//        return new BalanceDTO(id, savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getBalance());
//    }

}
/*
    public Account addAccount(@RequestParam(required = true) String accountType,
                              @RequestBody Account account) {
        switch(accountType.toLowerCase()) {
            case "savings":
                break;
        }
        return account;
    }
 */

/*Account Access
Admins
Admins should be able to access the balance for any account and to modify it.

AccountHolders
AccountHolders should be able to access their own account balance
Account holders should be able to transfer money from any of their accounts to any
other account (regardless of owner). The transfer should only be processed if the
account has sufficient funds. The user must provide the Primary or Secondary owner
name and the id of the account that should receive the transfer.

Third-Party Users
There must be a way for third-party users to receive and send money to other accounts.
Third-party users must be added to the database by an admin.
In order to receive and send money, Third-Party Users must provide their hashed key in
the header of the HTTP request. They also must provide the amount, the Account id and
the account secret key.
 */