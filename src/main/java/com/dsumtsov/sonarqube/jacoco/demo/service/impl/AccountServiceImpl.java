package com.dsumtsov.sonarqube.jacoco.demo.service.impl;

import com.dsumtsov.sonarqube.jacoco.demo.entity.Account;
import com.dsumtsov.sonarqube.jacoco.demo.repository.AccountRepository;
import com.dsumtsov.sonarqube.jacoco.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    @Transactional
    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = repository.getById(accountId);
        BigDecimal result = account.getAmount().subtract(amount);
        account.setAmount(result);
        return account;
    }
}
