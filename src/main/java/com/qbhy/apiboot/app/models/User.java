package com.qbhy.apiboot.app.models;


import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable, AuthenticateAble {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String avatar;

    public User(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String getAuthIdentifierName() {
        return "id";
    }

    @Override
    public Object getAuthIdentifier() {
        return this.id;
    }

    @Override
    public String getAuthPassword() {
        return null;
    }

    @Override
    public String getRememberToken() {
        return null;
    }

    @Override
    public void setRememberToken(String value) {

    }

    @Override
    public String getRememberTokenName() {
        return null;
    }
}
