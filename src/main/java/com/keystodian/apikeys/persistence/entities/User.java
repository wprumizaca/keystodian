package com.keystodian.apikeys.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;
    private String user_password;
}
