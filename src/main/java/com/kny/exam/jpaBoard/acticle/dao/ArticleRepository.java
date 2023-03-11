package com.kny.exam.jpaBoard.acticle.dao;

import com.kny.exam.jpaBoard.acticle.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}