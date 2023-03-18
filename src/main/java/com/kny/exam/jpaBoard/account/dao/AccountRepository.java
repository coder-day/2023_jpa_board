package com.kny.exam.jpaBoard.account.dao;

import com.kny.exam.jpaBoard.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
