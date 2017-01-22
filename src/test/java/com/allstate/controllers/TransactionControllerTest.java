package com.allstate.controllers;

import com.allstate.entities.Transaction;
import com.allstate.entities.User;
import com.allstate.enums.Action;
import com.allstate.enums.Funds;
import com.allstate.services.TransactionService;
import com.allstate.services.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldBuyStock() throws Exception {
        // stub
        Transaction transaction = new Transaction();
        transaction.setId(21);
        transaction.setAction(Action.BUY);
        transaction.setSymbol("AAPL");
        transaction.setQuantity(5);
        when(this.service.buy(1, "AAPL", 5)).thenReturn(transaction);

        // request
        MockHttpServletRequestBuilder request = post("/transactions/buy/1/AAPL/5");

        // assertion
        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(21)))
                .andExpect(jsonPath("$.quantity", is(5)))
                .andExpect(jsonPath("$.action", is("BUY")))
                .andExpect(jsonPath("$.symbol", is("AAPL")));
    }
}
