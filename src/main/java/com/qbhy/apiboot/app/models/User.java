package com.qbhy.apiboot.app.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String avatar;

    public User(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }
}
