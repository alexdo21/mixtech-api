package io.alexdo.mixtech.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String uname;
    private String email;
    private String password;

    public UserEntity() {}

    public UserEntity(String uname, String email, String password) {
        this.uname = uname;
        this.email = email;
        this.password = password;
    }
}
