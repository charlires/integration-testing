package com.charlires.junittest;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserRepoTest {

    private static UserRepo userRepo;

    @BeforeClass
    public static void initUserRepo() {
        userRepo = new UserRepo();
    }

    @Test
    public void testAddUser() {
        User user = userRepo.create(new User(1, "Carlos", "Wero"));
        assertEquals(user.getName(), "Carlos");
    }

    @Test
    public void testGetAll() {
        List<User> users = userRepo.getAll();
        assertEquals(users.size(), 1);
    }

    @Test
    public void testRemove() {
        userRepo.remove(1);
        List<User> users = userRepo.getAll();
        assertEquals(users.size(), 0);
    }

}
