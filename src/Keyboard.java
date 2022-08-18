import java.util.Scanner;

public class Keyboard {
    private static Scanner kb = new Scanner(System.in);

    public static String nextLine(){
        return kb.nextLine();
    }
    public static String nextLine(boolean clearScreen){
        String str = kb.nextLine();
        if(clearScreen)
            Utils.clearScreen();
        return str;
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

    public static int ensureNextInt(){
        try{
            return nextInt();
        }catch (NumberFormatException e){
            Utils.println("invalid_number");
            return ensureNextInt();
        }
    }

    public static int ensureNextInt(int minValue, int maxValue){
        int x = ensureNextInt();
        if(x >= minValue && x <= maxValue) return x;
        Utils.println("out_of_range", minValue, maxValue);
        return ensureNextInt(minValue, maxValue);
    }

    public static int ensureNextInt(int minValue){
        int x = ensureNextInt();
        if(x >= minValue) return x;
        Utils.println("under_range", minValue);
        return ensureNextInt(minValue);
    }

    public static void close(){
        kb.close();
    }
}
