import java.util.Scanner;

public class Keyboard {
    private static Scanner kb = new Scanner(System.in);

    public static String nextLine(){
        return kb.nextLine();
    }

    public static int nextInt(){
        return Integer.parseInt(nextLine());
    }

    public static int nextInt(int defaultValue){
        try{
            return nextInt();
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static void close(){
        kb.close();
    }
}
