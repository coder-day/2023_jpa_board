package com.kny.exam.jpaBoard.acticle.domain;

import com.kny.exam.jpaBoard.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String title;
    private String body ;
    @ManyToOne
    private User user;
}
