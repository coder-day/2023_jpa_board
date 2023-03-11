package com.kny.exam.jpaBoard.acticle.controller;

import com.kny.exam.jpaBoard.acticle.dao.ArticleRepository;
import com.kny.exam.jpaBoard.acticle.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/usr/article")

public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @RequestMapping("list")
    @ResponseBody
    public List<Article> showlist(){
        return articleRepository.findAll();
    }
}
