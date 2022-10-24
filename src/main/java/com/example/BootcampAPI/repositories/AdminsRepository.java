package com.example.BootcampAPI.repositories;

import com.example.BootcampAPI.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Long> {
}
