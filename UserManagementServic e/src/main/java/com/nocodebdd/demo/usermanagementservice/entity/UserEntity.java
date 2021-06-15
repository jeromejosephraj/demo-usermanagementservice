package com.nocodebdd.demo.usermanagementservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userName")
    private String userName;

}