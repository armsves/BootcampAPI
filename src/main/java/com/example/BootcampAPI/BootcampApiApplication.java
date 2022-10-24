package com.example.BootcampAPI;

import com.example.BootcampAPI.embeddables.Address;
import com.example.BootcampAPI.embeddables.Money;
import com.example.BootcampAPI.entity.AccountHolder;
import com.example.BootcampAPI.entity.Savings;
import com.example.BootcampAPI.entity.ThirdParty;
import com.example.BootcampAPI.entity.Role;
import com.example.BootcampAPI.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BootcampApiApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(BootcampApiApplication.class, args);
	}

	@Autowired
	SavingsRepository savingsRepository;
	@Autowired
	AccountHoldersRepository accountHoldersRepository;
	@Autowired
	ThirdPartyRepository thirdPartyRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UsersRepository usersRepository;

	@Override
	public void run(String... args) throws Exception {

//		User user = userRepository.save(new User("jose",passwordEncoder.encode("1234")));
//		roleRepository.save(new Role("USER",user));

		//Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money penaltyFee, String secretKey, Money minimumBalance, Status status, BigDecimal interestRate) {
		//public AccountHolder(String name, int securityLevel, LocalDate dateOfBirth, Address address, Address mailingAddress) {
//		postAddress city postalCode

		AccountHolder accountHolder = new AccountHolder("Armando Medina","mando",passwordEncoder.encode("1234"), LocalDate.of(1982,9,16), new Address("carrer santander","barcelona",8020), null);
		AccountHolder accountHolder2 = new AccountHolder("Jose Caro","jose",passwordEncoder.encode("4321"), LocalDate.of(1990,4,10), new Address("carrer mallorca","rubi",8016), null);
		AccountHolder accountHolder3 = new AccountHolder("Jaume Sanchez","jaume",passwordEncoder.encode("5432"), LocalDate.of(1985,5,26), new Address("avinguda diagonal","terrasa",8018), null);
		AccountHolder accountHolder4 = new AccountHolder("Alejandro Martinez","alejo",passwordEncoder.encode("2345"), LocalDate.of(1989,1,20), new Address("carrer arago","cerdanyola",8015), null);
//
//		accountHoldersRepository.saveAll(List.of(accountHolder,accountHolder2));
		accountHoldersRepository.saveAll(List.of(accountHolder,accountHolder2,accountHolder3,accountHolder4));

		roleRepository.save(new Role("USER", usersRepository.findById(accountHolder.getId()).get()));


		//public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Money minimumBalance, BigDecimal interestRate) {
		List<Savings> savings = savingsRepository.saveAll(List.of(
//				new Savings(new Money(BigDecimal.valueOf(1000)), accountHolder, accountHolder3,"443dsaggdfsgdsas", new Money(BigDecimal.valueOf(20)), BigDecimal.valueOf(0.45))
//				new Savings(new Money(BigDecimal.valueOf(5000)), new AccountHolder("Jose Caro", LocalDate.of(1990,4,10), new Address("carrer mallorca","rubi",8016), null), new AccountHolder("Jaume Sanchez", LocalDate.of(1985,5,26), new Address("avinguda diagonal","terrasa",8018), null),"fasdsaggdfsgdsas", new Money(BigDecimal.valueOf(20)), BigDecimal.valueOf(1))
				new Savings(new Money(BigDecimal.valueOf(2000)), accountHolder , accountHolder4,"443dsaggdfsgdsas", new Money(BigDecimal.valueOf(20)), BigDecimal.valueOf(0.45)),
				new Savings(new Money(BigDecimal.valueOf(5000)), accountHolder2 , null,"fasdsaggdfsgdsas", new Money(BigDecimal.valueOf(20)), BigDecimal.valueOf(0.35)),
				new Savings(new Money(BigDecimal.valueOf(3000)), accountHolder3 , accountHolder4, "gsadghsfjryhdjfgkjhsas",new Money(BigDecimal.valueOf(20)), BigDecimal.valueOf(0.44)),
				new Savings(new Money(BigDecimal.valueOf(1500)), accountHolder2 , null, "sedfghjkjthdrgetsrdhfjgk",new Money(BigDecimal.valueOf(30)),BigDecimal.valueOf(0.5))
		));

		//ThirdParty thirdParty3 = null;
		ThirdParty thirdParty = new ThirdParty("TPV","1234567890");
		ThirdParty thirdParty2 = new ThirdParty("TPV Virtual","0987654321");
		thirdPartyRepository.saveAll(List.of(thirdParty,thirdParty2));

	}
}

/*
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = userRepository.save(new User("jose",passwordEncoder.encode("1234")));
		roleRepository.save(new Role("USER",user));

	}
 */