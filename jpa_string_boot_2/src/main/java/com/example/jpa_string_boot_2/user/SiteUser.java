package com.example.jpa_string_boot_2.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) //중복 불가
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}
