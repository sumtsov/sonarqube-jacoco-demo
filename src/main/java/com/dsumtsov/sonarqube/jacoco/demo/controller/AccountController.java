package com.dsumtsov.sonarqube.jacoco.demo.controller;

import com.dsumtsov.sonarqube.jacoco.demo.entity.Account;
import com.dsumtsov.sonarqube.jacoco.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("{accountId}/withdraw/{amount}")
    public Account withdraw(@PathVariable Long accountId,
                            @PathVariable String amount) {

        return service.withdraw(accountId, new BigDecimal(amount));
    }
}