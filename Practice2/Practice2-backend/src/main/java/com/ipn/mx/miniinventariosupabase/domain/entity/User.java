package com.ipn.mx.miniinventariosupabase.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id_user;

    @Column(name = "user_name", nullable = true, length = 500)
    private String user_name;

    @Column(name = "last_name", nullable = true, length = 100)
    private String last_name;

    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Column(name = "user_u", nullable = true, length = 100)
    private String user_u;

    @Column(name = "password_u", nullable = true, length = 100)
    private String password_u;

    @Column(name = "creation_date", nullable = true)
    private Timestamp creation_date;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "gender", nullable = true, length = 100)
    private String gender;
}
