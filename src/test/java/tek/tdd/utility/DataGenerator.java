package tek.tdd.utility;

public class DataGenerator {

    public static   String emailGenerator(String name){
        int randomNumber = (int)(Math.random() * 10000);
        return name + randomNumber + "@gmail.com";
    }

}
