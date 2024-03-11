package Helpers;

import java.util.Random;

public class mailGenerator {

    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            String email = pickRandomEmailGenerator();
            System.out.println(email);
        }
        }
    public static String pickRandomEmailGenerator() {
        Random random = new Random();
        int choice = random.nextInt(3); // Randomly choose one of the three methods
        switch (choice) {
            case 0:
                return createRandomEmailRussian();
            case 1:
                return createRandomEmail();
            case 2:
                return createRandomEmailChinese();
            default:
                return "";
        }
    }
    public static String createRandomEmail() {
        Random random = new Random();
        String[] domains = {"@gmail.com", "@mail.ru", "@mail.org", "@walla.co.il"};
        String[] symbolsAndNumbers = {"!", "#", "$", "&", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        // Generate random recipient name
        StringBuilder recipientBuilder = new StringBuilder();
        boolean hasLetter = false;
        int nameLength = random.nextInt(10) + 5; // Random name length between 5 and 18 characters
        char randomChar = (char) (random.nextInt(26) + 'a'); // Random lowercase letter for the first character
        recipientBuilder.append(randomChar);
        for (int i = 1; i < nameLength; i++) {
            int type;
            if (!hasLetter && i >= 3 && random.nextBoolean()) {
                type = 0; // Force a letter after 3 characters
                hasLetter = true;
            } else {
                type = random.nextInt(4); // Random type: 0 for lowercase letter, 1 for symbol, 2 for number, 3 for uppercase letter
            }
            switch (type) {
                case 0:
                    randomChar = (char) (random.nextInt(26) + 'a'); // Random lowercase letter
                    recipientBuilder.append(randomChar);
                    break;
                case 1:
                    recipientBuilder.append(symbolsAndNumbers[random.nextInt(symbolsAndNumbers.length)]);
                    break;
                case 2:
                    recipientBuilder.append(symbolsAndNumbers[random.nextInt(symbolsAndNumbers.length)]);
                    break;
                case 3:
                    randomChar = (char) (random.nextInt(26) + 'A'); // Random uppercase letter
                    recipientBuilder.append(randomChar);
                    break;
            }
        }

        // Select random domain
        String domain = domains[random.nextInt(domains.length)];

        return recipientBuilder.toString() + domain;
    }
    public static String createRandomEmailRussian() {
        Random random = new Random();
        String[] domains = {"@gmail.com", "@mail.ru", "@mail.org", "@walla.co.il"};
        String[] symbolsAndNumbers = {"!", "#", "$", "%", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] russianLetters = {
                "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м",
                "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ",
                "ы", "ь", "э", "ю", "я"
        };

        // Generate random recipient name with Russian letters
        StringBuilder recipientBuilder = new StringBuilder();
        boolean hasLetter = false;
        int nameLength = random.nextInt(10) + 5; // Random name length between 5 and 18 characters
        String randomChar = russianLetters[random.nextInt(russianLetters.length)]; // Random Russian letter for the first character
        recipientBuilder.append(randomChar);
        for (int i = 1; i < nameLength; i++) {
            int type;
            if (!hasLetter && i >= 3 && random.nextBoolean()) {
                type = 0; // Force a letter after 3 characters
                hasLetter = true;
            } else {
                type = random.nextInt(4); // Random type: 0 for Russian letter, 1 for symbol, 2 for number, 3 for uppercase Russian letter
            }
            switch (type) {
                case 0:
                    randomChar = russianLetters[random.nextInt(russianLetters.length)]; // Random Russian letter
                    recipientBuilder.append(randomChar);
                    break;
                case 1:
                    recipientBuilder.append(symbolsAndNumbers[random.nextInt(symbolsAndNumbers.length)]);
                    break;
                case 2:
                    recipientBuilder.append(symbolsAndNumbers[random.nextInt(symbolsAndNumbers.length)]);
                    break;
                case 3:
                    randomChar = String.valueOf((char) (random.nextInt(32) + 1072)); // Random uppercase Russian letter
                    recipientBuilder.append(randomChar);
                    break;
            }
        }

        // Select random domain
        String domain = domains[random.nextInt(domains.length)];

        return recipientBuilder.toString() + domain;
    }
    public static String createRandomEmailChinese() {
        Random random = new Random();
        String[] domains = {"@gmail.com", "@mail.ru", "@mail.org", "@walla.co.il"};
        String[] symbolsAndNumbers = {"!", "$", "%", "#",  "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        // Generate random recipient name with Chinese characters
        StringBuilder recipientBuilder = new StringBuilder();
        boolean hasLetter = false;
        int nameLength = random.nextInt(7) + 5; // Random name length between 5 and 18 characters
        char randomChar = (char) (random.nextInt(20902 - 19968 + 1) + 19968); // Random Chinese character for the first character
        recipientBuilder.append(randomChar);
        for (int i = 1; i < nameLength; i++) {
            int type;
            if (!hasLetter && i >= 3 && random.nextBoolean()) {
                type = 0; // Force a letter after 3 characters
                hasLetter = true;
            } else {
                type = random.nextInt(4); // Random type: 0 for Chinese character, 1 for symbol, 2 for number, 3 for uppercase Chinese character
            }
            switch (type) {
                case 0:
                    randomChar = (char) (random.nextInt(20902 - 19968 + 1) + 19968); // Random Chinese character
                    recipientBuilder.append(randomChar);
                    break;
                case 1:
                    recipientBuilder.append(symbolsAndNumbers[random.nextInt(symbolsAndNumbers.length)]);
                    break;
                case 2:
                    recipientBuilder.append(symbolsAndNumbers[random.nextInt(symbolsAndNumbers.length)]);
                    break;
                case 3:
                    randomChar = (char) (random.nextInt(22235 - 12288 + 1) + 12288); // Random uppercase Chinese character
                    recipientBuilder.append(randomChar);
                    break;
            }
        }

        // Select random domain
        String domain = domains[random.nextInt(domains.length)];

        return recipientBuilder.toString() + domain;
    }
}
