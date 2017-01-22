package com.allstate.services;

import com.allstate.entities.Transaction;
import com.allstate.entities.User;
import com.allstate.enums.Action;
import com.allstate.models.Position;
import com.allstate.models.Stock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;

    @MockBean
    private QuoteService quoteService;

    @Before
    public void setUp() throws Exception {
        // stub
        when(this.quoteService.quote("AAPL")).thenReturn(new Stock("Apple Inc", "AAPL", 100.0));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldPurchaseStock() throws Exception {
        Transaction transaction = this.transactionService.buy(2, "AAPL", 5);
        assertEquals(12, transaction.getId());
        assertEquals(2, transaction.getUser().getId());
        assertEquals(Action.BUY, transaction.getAction());
        assertEquals(5, transaction.getQuantity());
        assertEquals("AAPL", transaction.getSymbol());
        assertEquals(500.0, transaction.getAmount(), 0.1);
    }

    @Test(expected = com.allstate.exceptions.TransactionException.class)
    public void shouldNotPurchaseStockInsufficientFunds() throws Exception {
        Transaction transaction = this.transactionService.buy(1, "AAPL", 5);
    }

    @Test
    public void shouldGetCountOfPurchasedShares() throws Exception {
        int count = this.transactionService.countSharesPurchasedOrSoldBySymbol(1, Action.BUY, "AAPL");
        assertEquals(16, count);
    }

    @Test
    public void shouldGetCountOfSoldShares() throws Exception {
        int count = this.transactionService.countSharesPurchasedOrSoldBySymbol(1, Action.SELL, "AAPL");
        assertEquals(2, count);
    }

    @Test
    public void shouldFindZeroCountForGivenSymbol() throws Exception {
        int count = this.transactionService.countSharesPurchasedOrSoldBySymbol(1, Action.BUY, "BAD");
        assertEquals(0, count);
    }

    @Test
    public void shouldSellStock() throws Exception {
        Transaction transaction = this.transactionService.sell(1, "AAPL", 13);
        assertEquals(12, transaction.getId());
        assertEquals(1, transaction.getUser().getId());
        assertEquals(Action.SELL, transaction.getAction());
        assertEquals(13, transaction.getQuantity());
        assertEquals("AAPL", transaction.getSymbol());
        assertEquals(1300.0, transaction.getAmount(), 0.1);
    }

    @Test(expected = com.allstate.exceptions.TransactionException.class)
    public void shouldNotSellStockInsufficientShares() throws Exception {
        Transaction transaction = this.transactionService.sell(1, "AAPL", 50);
    }

    @Test
    public void shouldCountAvailableSharesOfStock() throws Exception {
        int count = this.transactionService.countAvailableShares(1, "AAPL");
        assertEquals(14, count);
    }

    @Test
    public void shouldGetListOfUniqueStockSymbolsPerUser() throws Exception {
        List<String> list = this.transactionService.getUniqueStockSymbols(1);
        assertEquals(3, list.size());
    }

    @Test
    public void shouldReturnEmptyListOfStockSymbolsNotFound() throws Exception {
        List<String> list = this.transactionService.getUniqueStockSymbols(99);
        assertEquals(0, list.size());
    }
}
