package com.allstate.services;

import com.allstate.entities.User;
import com.allstate.enums.Funds;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private QuoteService quoteService;

    @Before
    public void setUp() throws Exception {
        // stub
        when(this.quoteService.quote("AAPL")).thenReturn(new Stock("Apple Inc", "AAPL", 100.0));
        when(this.quoteService.quote("AMZN")).thenReturn(new Stock("Amazon", "AMZN", 250.0));
        when(this.quoteService.quote("GOOG")).thenReturn(new Stock("Google", "GOOG", 325.0));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateUser() throws Exception {
        User u = this.userService.create("joe@aol.com");
        assertEquals(4, u.getId());
        assertEquals(0, u.getBalance(), 0.1);
        assertEquals("joe@aol.com", u.getEmail());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateUserDuplicateEmail() throws Exception {
        User u = this.userService.create("bob@aol.com");
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void shouldNotCreateUserEmailTooShort() throws Exception {
        User u = this.userService.create("a");
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateUserEmailNull() throws Exception {
        User u = this.userService.create(null);
    }

    @Test
    public void shouldDepositFunds() throws Exception {
        double balance = this.userService.changeFunds(1, 50, Funds.DEPOSIT);
        assertEquals(60, balance, 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDepositFundsBadUserId() throws Exception {
        this.userService.changeFunds(99, 100, Funds.DEPOSIT);
    }

    @Test
    public void shouldWithdrawFunds() throws Exception {
        this.userService.changeFunds(1, 100, Funds.DEPOSIT);
        double balance = this.userService.changeFunds(1, 25, Funds.WITHDRAW);
        assertEquals(85, balance, 0.1);
    }

    @Test(expected = org.springframework.transaction.TransactionSystemException.class)
    public void shouldNotWithdrawFundsInsufficientFunds() throws Exception {
        this.userService.changeFunds(1, 100, Funds.DEPOSIT);
        double balance = this.userService.changeFunds(1, 125, Funds.WITHDRAW);
    }

    @Test
    public void shouldGetPosition() throws Exception {
        Position position = this.userService.getPosition(1);
        assertEquals(10, position.getCash(), 0.1);
        assertEquals(3, position.getPositionDetails().size());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void shouldGetPositionForNonexistantUser() throws Exception {
        Position position = this.userService.getPosition(99);
    }

    @Test
    public void shouldGetPositionForUserWithNoTransactions() throws Exception {
        Position position = this.userService.getPosition(3);
        assertEquals(210, position.getCash(), 0.1);
        assertEquals(0, position.getPositionDetails().size());
    }
}
