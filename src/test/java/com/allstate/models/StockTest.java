package com.allstate.models;

import com.allstate.services.QuoteService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldGetStockObject() throws Exception {
        QuoteService quoteService = new QuoteService();
        Stock stock = quoteService.quote("AAPL");
        assertTrue(stock.getLastPrice() > 0);
        assertEquals("AAPL", stock.getSymbol());
        assertEquals("Apple Inc", stock.getName());
    }
}
