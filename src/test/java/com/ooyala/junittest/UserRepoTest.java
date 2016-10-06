package com.ooyala.junittest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepoTest {

    private static UserRepo userRepo;

    @BeforeClass
    public static void initUserRepo() {
        userRepo = new UserRepo();
    }

//    @Before
//    public void beforeEachTest() {
//        System.out.println("Hello");
//    }

    @Test
    public void testAddUser() {
        User user = userRepo.create(new User("Carlos", "Wero"));
        assertEquals(user.getName(), "Carlos");
    }

//    @Test
//    public void testGetUser() {
//
//    }

}
