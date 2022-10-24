package com.example.BootcampAPI.services;

import com.example.BootcampAPI.Controllers.DTOs.BalanceDTO;
import com.example.BootcampAPI.Controllers.DTOs.CheckingDTO;
import com.example.BootcampAPI.entity.*;
import com.example.BootcampAPI.repositories.*;
import com.example.BootcampAPI.services.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    UsersRepository usersRepository;

    public Savings getSavingsById(Long id) {
        return savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "An account with the given id does not exist"));
    }

    public Savings addSavingsAccount(Savings savings) {
        //, Long idPrimaryOwner, Long idSecondaryOwner) {
        if (savings.getPrimaryOwner().getId() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An account holder must NOT be null");
        savings.setPrimaryOwner(accountHoldersRepository.findById(savings.getPrimaryOwner().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "An account holder with the given id does not exist")));
        if (savings.getSecondaryOwner() != null && savings.getSecondaryOwner().getId() != null) savings.setSecondaryOwner(accountHoldersRepository.findById(savings.getSecondaryOwner().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "An account holder with the given id does not exist")));
            return savingsRepository.save(savings);
    }

    public CheckingDTO addCheckingAccount(CheckingDTO checkingAccount) {
        CheckingDTO checkingDTO = null;
        if (Period.between(checkingAccount.getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() > 24) {
            //Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey)
            Checking checking = new Checking(checkingAccount.getBalance(), checkingAccount.getPrimaryOwner(), checkingAccount.getSecondaryOwner(), checkingAccount.getSecretKey());
            checkingRepository.save(checking);
            checkingDTO = new CheckingDTO(checking.getId(),checking.getBalance(),checking.getPrimaryOwner(),checking.getSecondaryOwner(),checking.getSecretKey());
        } else {
            StudentChecking studentChecking = new StudentChecking(checkingAccount.getBalance(), checkingAccount.getPrimaryOwner(), checkingAccount.getSecondaryOwner(), checkingAccount.getSecretKey());
            studentCheckingRepository.save(studentChecking);
            checkingDTO = new CheckingDTO(studentChecking.getId(),studentChecking.getBalance(),studentChecking.getPrimaryOwner(),studentChecking.getSecondaryOwner(),studentChecking.getSecretKey());

        }
        return checkingDTO;
    }

    public ThirdParty addThirdPartyAccount(ThirdParty thirdParty) {
        return thirdPartyRepository.save(thirdParty);
    }

    public BalanceDTO getSavingsBalance(Long id) { return new BalanceDTO(id, savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "An account with that id does not exist")).getBalance()); }

    public BalanceDTO makeTransfer(UserDetails userDetails, Long idMakerAccount, BigDecimal transferAmount, Long idReceiverAccount, String nameReceiver) {
        AccountHolder accountHolder = accountHoldersRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "An account with that username does not exist"));
        Account maker = null;
        for (Account a: accountHolder.getPrimaryOwnerList()) { if (!a.getId().equals(idMakerAccount)) maker = accountRepository.findById(idMakerAccount).get(); }
        for (Account a: accountHolder.getSecondaryOwnerList()) { if (!a.getId().equals(idMakerAccount)) maker = accountRepository.findById(idMakerAccount).get(); }
        if(maker == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An account with that username does not exist");
        Account receiver = accountRepository.findById(idReceiverAccount).orElseThrow();
        if (maker.getBalance().decreaseAmount(transferAmount).compareTo(BigDecimal.valueOf(0)) < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Transfer amount exceeds balance");
        if (!receiver.getPrimaryOwner().getName().equals(nameReceiver) && !receiver.getSecondaryOwner().getName().equals(nameReceiver)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name doesn't match account Id");
        receiver.getBalance().increaseAmount(transferAmount);
        accountRepository.save(maker);
        accountRepository.save(receiver);
        return new BalanceDTO(idMakerAccount, maker.getBalance());
    }
    public BalanceDTO thirdPartyTransfer(String hashedKey, Long idReceiverAccount, String secretKey, BigDecimal transferAmount) {
        if (thirdPartyRepository.findByHashedKey(hashedKey) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"An account with that hashedKey does not exist");
        if (!thirdPartyRepository.findByHashedKey(hashedKey).getHashedKey().equals(hashedKey)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"An account with that hashedKey does not exist");
        Account receiver = accountRepository.findById(idReceiverAccount).orElseThrow();
        if(receiver instanceof HasSecretKey) { if (!((HasSecretKey) receiver).getSecretKey().equals(secretKey)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Secret key mismatch"); }
        receiver.getBalance().increaseAmount(transferAmount);
        accountRepository.save(receiver);
        return new BalanceDTO(idReceiverAccount, receiver.getBalance());
    }
}