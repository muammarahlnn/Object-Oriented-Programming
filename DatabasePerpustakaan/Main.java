// import java libarary
import java.io.*;

// import crud
import crud.*;
import static crud.Utility.sc;

class Main {

    public static void main(String[] args) throws IOException {
        boolean isContinue = true;
        while (isContinue) {
            System.out.println();
            System.out.println("================================");
            System.out.println("|| Library Information System ||");
            System.out.println("================================");
            System.out.println("|| 1 -> Show All Book         ||");
            System.out.println("|| 2 -> Search book           ||");
            System.out.println("|| 3 -> Add book              ||");
            System.out.println("|| 4 -> Edit data book        ||");
            System.out.println("|| 5 -> Delete book           ||");
            System.out.println("||                            ||");
            System.out.println("|| 0 -> Exit library          ||");
            System.out.println("================================");
    
            System.out.print("Select ur command number => ");
            int userCommand = sc.nextInt();
            sc.nextLine();
            switch (userCommand) {
                case 1:
                    System.out.println("\n===== List Book ======");
                    Operation.showListBook();
                    break;
                case 2:
                    System.out.println("\n===== Search Book ======");
                    Operation.searchBook();
                    break;
                case 3:
                    System.out.println("\n===== Add Book ======");
                    Operation.addBook();
                    break;
                case 4:
                    System.out.println("\n===== Edit data book ======");
                    Operation.updateData();
                    break;
                case 5:
                    System.out.println("\n===== Delete Book =====");
                    Operation.deleteBook();
                    break;
                case 0 :
                    System.out.println("Thank you for coming \nHope to see u again :)");
                    return;
                default:
                    System.err.println("\nCommand not found.\nPlease enter [1 - 5]");
                    break;
            }
            isContinue = Utility.getYesOrNo("Do you want to continue?");
        }
    }
    
}