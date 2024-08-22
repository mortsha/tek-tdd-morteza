package tek.tdd.utility;

public class DataGenerator {

    public static   String emailGenerator(String name){
        int randomNumber = (int)(Math.random() * 10000);
        return name + randomNumber + "@gmail.com";
    }
    public static String getRandomPhoneNumber(){
        StringBuilder randomPhone = new StringBuilder();
        for(int i = 0; i<10; i++){
            int random = (int) (Math.random() *10);
            randomPhone.append(String.valueOf(random));
        }
        return randomPhone.toString();
    }
}
