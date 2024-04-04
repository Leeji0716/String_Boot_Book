package com.example.jpa_string_boot_2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {
}
