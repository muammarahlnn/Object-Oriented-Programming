import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static UKMProgrammer obj = new UKMProgrammer();
    static PengurusInti memberInti;
    static Koordinator memberKoor;
    static String n, f, m, NIM;
    static int reg;
    static boolean isExit = false;

    public static void main(String[] args) {
        while(!isExit) {
            UKMProgrammer.display();
            int choice = sc.nextInt();

            displayProgram(choice);
        }
    }

    static void addMember() {
        System.out.println("===== ADD MEMBER =====");
        System.out.println("1. Pengurus Inti");
        System.out.println("2. Koordinator");
        System.out.print("What position ?\n => ");
    }

    static void inputData() {
        sc.nextLine();
        System.out.print("Input your name => ");
        n = sc.nextLine();
        System.out.print("Input your faculty => ");
        f = sc.nextLine();
        System.out.print("Input your major => ");
        m = sc.nextLine();
        System.out.print("Input your NIM => ");
        NIM = sc.nextLine();
        System.out.print("Input your register year => ");
        reg = sc.nextInt();
    } 
    
    static void displayProgram(int choice) {
        switch (choice) {
            case 1:
                addMember();
                int positionMember = sc.nextInt();
                switch (positionMember) {
                    case 1: //pengurus inti
                        inputData();
                        obj = new UKMProgrammer(n, f, m, NIM, reg);
                        memberInti = new PengurusInti(n, f, m, NIM, reg);
                        obj.addPengurusInti(memberInti);
                        break;
                    case 2 : //koordinator
                        inputData();
                        obj = new UKMProgrammer(n, f, m, NIM, reg);
                        memberKoor = new Koordinator(n, f, m, NIM, reg);
                        obj.addKoordinator(memberKoor);
                        break;
                    default:
                        System.out.println("Position not found.");
                        break;
                }   
                break;

            case 2:
                if (obj.isListEmpty()) {
                    break;
                }
                obj.showListMember();
                System.out.print("Select member you want to remove :\n => ");
                int removeMember = sc.nextInt();   
                obj.removeMember(removeMember);
                break;

            case 3 :
                if (obj.isListEmpty()) {
                    break;
                }
                obj.showListMember();
                System.out.print("Select member you want to see his/her info :\n  => ");
                int infoMember = sc.nextInt();
                obj.showInfoMember(infoMember);
                break;

            case 4:
                System.out.print("Are you sure want to exit ? (y/n)\n => ");
                String exit = sc.next();
                if (exit.equalsIgnoreCase("y")) { 
                    System.out.println("Thank you for coming :)");
                    isExit = true;
                    return;
                }
                break;
                
            default :
                System.out.println("Command not found.");
                break;
        }
        sc.nextLine();
        System.out.println("Press enter to continue ...");
        sc.nextLine();
    }
}