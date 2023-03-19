package com.kny.exam.jpaBoard.acticle.controller;

import org.springframework.ui.Model;
import com.kny.exam.jpaBoard.acticle.dao.ArticleRepository;
import com.kny.exam.jpaBoard.acticle.domain.Article;
import com.kny.exam.jpaBoard.user.dao.UserRepository;
import com.kny.exam.jpaBoard.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/usr/article")

public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    @RequestMapping("list2")
    @ResponseBody
    public List<Article> showlist2(){
        return articleRepository.findAll();
    }

    @RequestMapping("list")
    public String showlist(Model model){
        model.addAttribute("age", 44);
        model.addAttribute("name", "폴");
        return "/usr/article/list";

    }


    @RequestMapping("detail")
    @ResponseBody
    public Article showDetail(long id){
        Optional<Article> article = articleRepository.findById(id);
        return article.get();
    }

    @RequestMapping("doModify")
    @ResponseBody
    public Article doModify(long id, String title, String body){
        Article article = articleRepository.findById(id).get();

            if( title != null) {
                article.setTitle(title);
            }

            if( body != null) {
                article.setBody(body);
            }

           articleRepository.save(article);

            return article;

    }

    @RequestMapping("doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if( title == null || title.trim().length() == 0) {
            return "제목을 입력해주세요.";
        }
        title = title.trim();

        if( body == null || body.trim().length() == 0) {
            return "내용을 입력해주세요.";
        }
        body = body.trim();

        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);

        User user = userRepository.findById(2L).get();
        article.setUser(user);

        articleRepository.save(article);

        return "%d번 게시물이 생성 되었습니다.".formatted(article.getId());

    }

    @RequestMapping("doDelete")
    @ResponseBody
    public String doDelete(long id){

        if( articleRepository.existsById(id) == false) {
            return "%d번 게시물은 이미 삭제 되었거나 존재하지 않습니다.".formatted(id);
        }
        articleRepository.deleteById(id);
        return "%d번 게시물이 삭제 되었습니다.".formatted(id);
    }

    }

