package com.dsumtsov.sonarqube.jacoco.demo.service;

import com.dsumtsov.sonarqube.jacoco.demo.entity.Account;
import com.dsumtsov.sonarqube.jacoco.demo.repository.AccountRepository;
import com.dsumtsov.sonarqube.jacoco.demo.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private static final Account ACCOUNT = new Account(1L, new BigDecimal(100), "USD");

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void testWithdraw() {

        when(accountRepository.getById(any())).thenReturn(ACCOUNT);

        Account result = accountService.withdraw(1L, new BigDecimal(90));

        assertEquals(1L, result.getId());
        assertEquals(new BigDecimal(10), result.getAmount());
        assertEquals("USD", result.getCcy());
    }
}
