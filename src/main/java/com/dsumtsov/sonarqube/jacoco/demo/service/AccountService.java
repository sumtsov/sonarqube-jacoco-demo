package com.dsumtsov.sonarqube.jacoco.demo.service;

import com.dsumtsov.sonarqube.jacoco.demo.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account withdraw(Long accountId, BigDecimal amount);
}
