package Helpers;

import java.util.Random;

public class PhoneNumberGenerator {
    public static void main(String[] args) {
//        for(int i=0; i<10; i++)
//        {
//            System.out.println(generatePhoneNumber());
//        }
    }

    private static final int MIN_LENGTH = 10;
    private static final int MAX_LENGTH = 15;

    public static String generatePhoneNumber()
    {
        Random random = new Random();
        int length = random.nextInt(MAX_LENGTH-MIN_LENGTH-1)+MIN_LENGTH;
        StringBuilder phoneNumber = new StringBuilder();
        for(int i=0; i<length; i++)
        {
            if(i==0)
            {
             phoneNumber.append(random.nextInt(7)+2);
            }
            else {phoneNumber.append(random.nextInt(9));}
        }
        return phoneNumber.toString();
    }
}
