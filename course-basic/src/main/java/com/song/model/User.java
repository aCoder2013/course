package com.song.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2017/1/26.
 */
@Entity
@Data
@NoArgsConstructor // fuck JPA
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String name;

    private String email;

    private String password;

    public User(String name) {
        this.name = name;
    }

}
