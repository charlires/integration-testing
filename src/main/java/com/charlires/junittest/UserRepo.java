package com.charlires.junittest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlosandonaegui on 10/6/16.
 */
public class UserRepo {

    public List<User> users;

    public UserRepo() {
        users = new ArrayList<User>();
    }

    public User create(User user) {
        users.add(user);
        return user;
    }

    public User getById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public List<User> getAll() {
        return users;
    }

    public void remove(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                users.remove(u);
                return;
            }
        }
    }
}
