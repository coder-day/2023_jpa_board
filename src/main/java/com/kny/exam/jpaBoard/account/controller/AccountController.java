package com.kny.exam.jpaBoard.account.controller;


import com.kny.exam.jpaBoard.account.dao.AccountRepository;
import com.kny.exam.jpaBoard.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/account")

public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("list")
    @ResponseBody
    public List<Account> showlist(){

        return accountRepository.findAll();
    }

    @RequestMapping("detail")
    @ResponseBody
    public Account showDetail(long id){
        Optional<Account> account = accountRepository.findById(id);
        return account.get();
    }

    @RequestMapping("doModify")
    @ResponseBody
    public Account showModify(long id, String acname, String name, String telnumber) {
        Account account = accountRepository.findById(id).get();

        if(acname != null) {
            account.setAcname(acname);
        }

        if(name != null) {
            account.setName(name);
        }

        if(telnumber != null) {
            account.setTelnumber(telnumber);
        }

        return account;
    }


}
