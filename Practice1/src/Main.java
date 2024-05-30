import Task3.BadLaptop;
import Task3.Laptop;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String[] MessageDigestAlgorithms = {"MD5", "SHA-1", "SHA-256"};
        String[] SecureRandomAlgorithms = {"DRBG", "SHA1PRNG", "Windows-PRNG"};
        String inputString = "Hello World!";
        // Task 1
        System.out.println("Task 1. Hashing with Message Digest.");
        try (FileWriter writer = new FileWriter("Task1.txt", true)) {
            for (String a : MessageDigestAlgorithms)
                hashDataMessageDigest(a, inputString, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Task 2
        System.out.println("\nTask 2. Hashing with Secure Random.");
        try (FileWriter writer2 = new FileWriter("Task2.txt", true)) {
            for (String a : SecureRandomAlgorithms)
                hashDataSecureRandom(a, inputString, writer2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Task 3
        testingOwnHash();
    }

        // Task  1
    public static void hashDataMessageDigest(String alg, String inputLine, FileWriter writer) {
        try {
            System.out.println("Algorithm: " + alg);
            System.out.println("Data before hash: " + inputLine);

            MessageDigest md = MessageDigest.getInstance(alg);
            md.update(inputLine.getBytes());
            byte[] digest = md.digest();

            String resultLine = bytesToHex(digest);
            System.out.println("Data after hash:  " + resultLine);

            writer.write(resultLine + '\n');

        } catch (NoSuchAlgorithmException | IOException e ) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Task 2
    public static void hashDataSecureRandom(String alg, String inputLine, FileWriter writer) {
        System.out.println("Algorithm: " + alg);
        System.out.println("Data before hash: " + inputLine);
        try {
            SecureRandom random = SecureRandom.getInstance(alg);
            random.setSeed(155);
            random.nextBytes(inputLine.getBytes());
            long result = random.nextLong();
            System.out.println("Data after hash:  " + result);
            writer.write(result + "\n");
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Task 3
    public static void testingOwnHash(){
        System.out.println("\nTest 3");
        System.out.println("Fill hashmap with correct class: ");
        Laptop laptop1 = new Laptop("Lenovo Thinkbook", 2021, 15.6);
        Laptop laptop2 = new Laptop("Apple MacBook Air", 2022, 13.6);
        Laptop laptop3 = new Laptop("Acer Aspire 7", 2023, 15.6);
        Laptop laptop4 = new Laptop("Lenovo Thinkbook", 2021, 15.6);

        HashMap<Laptop, Integer> countOfLaptops = new HashMap<Laptop, Integer>();

        countOfLaptops.put(laptop1, 1);
        countOfLaptops.put(laptop2, 2);
        countOfLaptops.put(laptop3, 20);
        countOfLaptops.put(laptop4, 12);

        for (Laptop l: countOfLaptops.keySet())
            System.out.println(l + ", hashCode : " + l.hashCode());

        BadLaptop badLaptop1 = new BadLaptop("Lenovo IdeaPad Gaming 3", 2019, 15);
        BadLaptop badLaptop2 = new BadLaptop("HP 15-fc0018", 2021, 15.6);
        BadLaptop badLaptop3 = new BadLaptop("Lenovo IdeaPad Gaming 3", 2019, 15);
        BadLaptop badLaptop4 = new BadLaptop("HP 15-fc0018", 2021, 15.6);

        HashMap<BadLaptop, Integer> countOfBadLaptops = new HashMap<BadLaptop, Integer>();

        countOfBadLaptops.put(badLaptop1, 1);
        countOfBadLaptops.put(badLaptop2, 2);
        countOfBadLaptops.put(badLaptop3, 20);
        countOfBadLaptops.put(badLaptop4, 12);

        for (BadLaptop l: countOfBadLaptops.keySet())
            System.out.println(l + ", hashCode : " + l.hashCode());
    }
}