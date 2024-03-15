package Helpers;

import java.io.FileWriter;
import java.io.IOException;

public class EmailGenerator {
    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            System.out.println(generateEmail(6,4,2));
        }
    }
    public static void saveGeneratedMailToResources(String nameOrNum,String email) throws IOException{
        try {
            String filePath = "src/test/resources/resources.properties"; // Specify the file path in resources folder
            FileWriter writer = new FileWriter(filePath, true); // Use true to append to the file
            writer.write("\n"+nameOrNum+"=" + email); // Write the email to the file
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the email to file.");
            e.getLocalizedMessage();
        }
    }

    public static String generateEmail(int a,int b,int c)
    {
        if(a <=0 || b<= 0 || c <= 0){
            throw new IllegalArgumentException("wrong parameter"+a+"or"+b+"or"+c);
        }
        StringBuilder email = new StringBuilder();
        for (int i = 0; i <= a; i++){
            email.append(randomChar());
        }
        email.append("@");
        for (int i = 0; i <= b; i++){
            email.append(randomChar());
        }
        email.append(".");
        for (int i = 0; i <= c; i++){
            email.append(randomChar());
        }
        return email.toString();
    }

    private static char randomChar() {
        return (char) ('a'+Math.random() * ('z' - 'a'));
    }
}
