package crud;

import java.util.Scanner;

public class Utility {
    public static Scanner sc = new Scanner(System.in);

    public static boolean getYesOrNo(String message) {
        System.out.print("\n" + message + " (y/n) => ");
        String yesOrNo = sc.nextLine();

        while (!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n")) {
            System.err.print("Please input 'y' or 'n' => ");
            yesOrNo = sc.next();
        }
        return yesOrNo.equalsIgnoreCase("y");
    }

}