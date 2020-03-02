package com.skillbox.sw.domain;

import com.skillbox.sw.domain.enums.TypeUser;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    private String name;

    @Column(name = "e_mail")
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('MODERATOR', 'ADMIN')")
    private TypeUser type;
}
