package com.mycompany.app;

import org.mindrot.jbcrypt.BCrypt;

import static com.mycompany.app.UsersDataBase.*;


public class UserService {
    public static void main(String[] args){
        String password = "test1";
        String candidate = "test1";
        String pepper ="securerandomstring";
        // Hash a password for the first time
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt() + pepper);
        String hashed2 = BCrypt.hashpw(candidate, BCrypt.gensalt() + pepper);
        System.out.println(hashed2 + " " + hashed);
// gensalt's log_rounds parameter determines the complexity
// the work factor is 2**log_rounds, and the default is 10
        //String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

// Check that an unencrypted password matches one that has
// previously been hashed
        if (BCrypt.checkpw(candidate, hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

    public static boolean addNewUser(String login,String password, int age, String name){
        if(containsLogin(login))
            return false;
        User newUser = new User(login, toHash(password), age, name);
        addUser(newUser);
        return true;
    }
    public static boolean containsUser(String login, String inputPassword) {
        if (users.containsKey(login)) {
            String userHashedPassword = users.get(login).getPassword();
            return (BCrypt.checkpw(inputPassword, userHashedPassword));
        }
        return false;
    }
    public static void fillUsers(){
        User user1 = new User("user1", toHash("12345"), 19, "Viktoriia Pushka");
        User user2 = new User("user2", toHash("54321"), 21, "Polina Tkach");
        User user3 = new User("user3", toHash("543245"), 21, "Mariia Nepyivoda");

        UsersDataBase.addUser(user1);
        UsersDataBase.addUser(user2);
        UsersDataBase.addUser(user3);
    }
    private static String toHash(String password){
        String pepper ="securerandomstring";
        return  BCrypt.hashpw(password, BCrypt.gensalt()+pepper);
    }
}