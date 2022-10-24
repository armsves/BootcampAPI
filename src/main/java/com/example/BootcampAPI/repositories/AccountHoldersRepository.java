package com.example.BootcampAPI.repositories;

import com.example.BootcampAPI.entity.AccountHolder;
import com.example.BootcampAPI.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHoldersRepository extends JpaRepository<AccountHolder, Long> {
    Optional<AccountHolder> findByUsername(String username);
}
