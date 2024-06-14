package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.io.IOException;

import static org.example.UsersDataBase.*;


public class UserService {
    public static void main(String[] args){
        String password = "test1";
        String candidate = "test1";
        String pepper ="securerandomstring";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt() + pepper);
        String hashed2 = BCrypt.hashpw(candidate, BCrypt.gensalt() + pepper);
        System.out.println(hashed2 + " " + hashed);

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
    public static void fillUsers() throws IOException {
        User user1 = new User("user1", toHash("12345"), 19, "Viktoriia Pushka");
        User user2 = new User("user2", toHash("54321"), 21, "Polina Tkach");
        User user3 = new User("user3", toHash("543245"), 21, "Mariia Nepyivoda");
        FileWriter fw = new FileWriter("C:\\Users\\user\\IdeaProjects\\SecondCourse\\AdditionalTerm\\Practice2\\user-interface-util\\src\\main\\resources\\users.txt");
        fw.write(user1.toString() + '\n' + user2.toString() + '\n' + user3.toString());
        fw.close();
        UsersDataBase.addUser(user1);
        UsersDataBase.addUser(user2);
        UsersDataBase.addUser(user3);
    }
    private static String toHash(String password){
        String pepper ="securerandomstring";
        return  BCrypt.hashpw(password, BCrypt.gensalt()+pepper);
    }
}