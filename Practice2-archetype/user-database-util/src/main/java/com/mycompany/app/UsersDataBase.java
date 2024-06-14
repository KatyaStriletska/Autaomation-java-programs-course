package com.mycompany.app;

import java.util.HashMap;

public class UsersDataBase{
    public static HashMap<String, User> users = new HashMap<>();
    public static User getUserByLogin(String username) {
        return users.get(username);
    }
    public static boolean containsLogin(String login)
    {
        return users.containsKey(login);
    }
    public static void addUser(User user) {
        users.put(user.getLogin(), user);
    }
}
