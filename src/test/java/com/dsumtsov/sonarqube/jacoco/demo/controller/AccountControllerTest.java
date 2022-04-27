package com.dsumtsov.sonarqube.jacoco.demo.controller;

import com.dsumtsov.sonarqube.jacoco.demo.entity.Account;
import com.dsumtsov.sonarqube.jacoco.demo.service.AccountService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private static final Account ACCOUNT = new Account(1L, new BigDecimal(10), "USD");
    private static final String URI = "/accounts/1/withdraw/90";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    void testWithdraw() throws Exception {

        Mockito.when(accountService.withdraw(any(), any())).thenReturn(ACCOUNT);

        MvcResult mvcResult = mvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String responseBody = mvcResult.getResponse().getContentAsString();
        Account response = new Gson().fromJson(responseBody, Account.class);

        assertEquals(1L, response.getId());
        assertEquals(new BigDecimal(10), response.getAmount());
        assertEquals("USD", response.getCcy());
    }
}
