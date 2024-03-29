package com.kny.exam.jpaBoard.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String email;
    private String name;
    @JsonIgnore
    private String password;

}
