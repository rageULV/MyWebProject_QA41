package examples;
import java.util.ArrayList;

public class LambdaExample {
    // lambda for stream
    //(//https://www.baeldung.com/java-8-streams-introduction)
    public static void main(String[] args) {
        ArrayList<Integer> arrL = new ArrayList<>();
        arrL.add(1);
        arrL.add(2);
        arrL.add(3);
        arrL.add(4);

        // Using lambda expression to print all elements
        // of arrL
        arrL.forEach(n -> System.out.println(n));
        arrL.forEach(n -> System.out.println(n=(n+5)));// just + 5 for each one
        arrL.forEach(System.out::println); // the same thing bit with ::

        //-
        for(int x : arrL){
            System.out.println(x);
        }


        //all this 3 methods do the same thing
        System.out.println("-----------------------------------------------------------");

        // Using lambda expression to print even elements
        // of arrL
        arrL.forEach(n -> {
            if (n % 2 == 0) System.out.println(n);
        });
        //----
        for(int x:arrL)
        {
            if(x % 2 == 0)
            {
                System.out.println(x);
            }
        }

    }
}
