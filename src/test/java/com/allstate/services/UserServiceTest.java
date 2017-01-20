package com.allstate.services;

import com.allstate.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class UserServiceTest {
    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateUser() throws Exception {
        User u = this.service.create("joe@aol.com");
        assertEquals(3, u.getId());
        assertEquals(0, u.getBalance(), 0.1);
        assertEquals("joe@aol.com", u.getEmail());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateUserDuplicateEmail() throws Exception {
        User u = this.service.create("bob@aol.com");
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void shouldNotCreateUserEmailTooShort() throws Exception {
        User u = this.service.create("a");
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateUserEmailNull() throws Exception {
        User u = this.service.create(null);
    }
}
