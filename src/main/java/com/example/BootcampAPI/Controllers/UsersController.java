package com.example.BootcampAPI.Controllers;

import com.example.BootcampAPI.Controllers.interfaces.UsersControllerInterface;
import com.example.BootcampAPI.entity.AccountHolder;
import com.example.BootcampAPI.entity.Admins;
import com.example.BootcampAPI.entity.ThirdParty;
import com.example.BootcampAPI.entity.Users;
import com.example.BootcampAPI.repositories.*;
import com.example.BootcampAPI.security.CustomUserDetails;
import com.example.BootcampAPI.services.CustomUserDetailsService;
import com.example.BootcampAPI.services.interfaces.UsersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController implements UsersControllerInterface {
    @Autowired
    UsersServiceInterface usersServiceInterface;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    AdminsRepository adminsRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/accounts/find")
    public List<Users> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
//        userDetails.
        //findByUsername(userDetails.getUsername().get
//        customUserDetailsService.loadUserByUsername(userDetails.getUsername());
        return usersRepository.findAll();
    }

    @PostMapping("/admin/addAccountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
        return accountHoldersRepository.save(accountHolder);
    }

    @PostMapping("/admin/addAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admins addAdmins(@RequestBody Admins admins) {
        return adminsRepository.save(admins);
    }

    @PostMapping("/admin/addThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdParty(@RequestBody ThirdParty thirdParty) {
        return thirdPartyRepository.save(thirdParty);
    }
}
    /*
    Admins
Admins should be able to access the balance for any account and to modify it.
Admins can create new accounts. When creating a new account they can create Checking, Savings, or CreditCard Accounts.
Third-party users must be added to the database by an admin.
TODO descongelar opcional

AccountHolders
AccountHolders should be able to access their own account balance
getbalance
todo modificar su balance opcional
Account holders should be able to transfer money from any of their accounts to any other account (regardless of owner).
The transfer should only be processed if the account has sufficient funds.
The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.

Third-Party Users
There must be a way for third-party users to receive and send money to other accounts.
Third-party users must be added to the database by an admin.
In order to receive and send money, Third-Party Users must provide their hashed key in the header of the HTTP request.
They also must provide the amount, the Account id and the account secret key.
*/