package Helpers;

import java.util.Random;

public class AddressGenerator {
    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            System.out.println(generateAddress());
        }
    }
    private static final String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
    private static final String[] streets = {"Main", "Broadway", "First", "Second", "Third", "Fourth", "Fifth", "Park", "Oak", "Pine"};
    private static final String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

//    private static final Random random = new Random();
    public static String generateAddress(){
        Random random = new Random();

            String city = cities[random.nextInt(cities.length)];
            String street = streets[random.nextInt(streets.length)];
            int number = random.nextInt(60);
            String state = states[random.nextInt(states.length)];

        return city+" "+street+" "+number+" "+state;
    }
}
