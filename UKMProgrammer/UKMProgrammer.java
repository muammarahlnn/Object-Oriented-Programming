import java.util.ArrayList;
import java.util.List;

public class UKMProgrammer {
    static List<PengurusInti> listPengurusInti = new ArrayList<>();
    static List<Koordinator> listKoordinator = new ArrayList<>();

    String name, faculty, major, NIM;
    int registYear;

    public UKMProgrammer(String name, String faculty, String major
                        , String NIM, int registYear) {
        this.name = name;
        this.faculty = faculty;
        this.major = major;
        this.NIM = NIM;
        this.registYear = registYear;
    }

    public UKMProgrammer(){
        this.name = null;
        this.faculty = null;
        this.major = null;
        this.NIM = null;
        this.registYear = 0;
    }
    
    public static void display() {
        System.out.println("================================================================");
        System.out.println("||                    UKM PROGRAMMER UNHAS                    ||");
        System.out.println("================================================================");
        System.out.println("1. Add member");
        System.out.println("2. Remove member");
        System.out.println("3. Show info member");
        System.out.println("4. Exit");
        System.out.print("Select what u wanna do :\n => ");
    }

    public void addKoordinator(Koordinator member) {
        listKoordinator.add(new Koordinator(
            member.name,
            member.faculty,
            member.major,
            member.NIM,
            member.registYear));

    }

    public void addPengurusInti(PengurusInti member) {
        listPengurusInti.add(new PengurusInti(
            member.name,
            member.faculty,
            member.major,
            member.NIM,
            member.registYear));
    }

    public boolean isListEmpty() {
        if (listPengurusInti.isEmpty() && listKoordinator.isEmpty()){
            System.out.println("UKM Programmer Unhas doesn't has any member yet.");
            return true;
        }
        return false;
    }

    public void showListMember() {
        if (listPengurusInti.size() != 0) {
            System.out.println("===== PENGURUS INTI =====");
            for (int i = 0; i < listPengurusInti.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, listPengurusInti.get(i).name);
            }
        }
        if (listKoordinator.size() != 0) {
            System.out.println("=====  KOORDINATOR  =====");
            for (int i = 0; i < listKoordinator.size(); i++) {
                System.out.printf("%d. %s\n", i + 1 + listPengurusInti.size(),
                                    listKoordinator.get(i).name);
            }
        }        
    }

    public void removeMember(int selected) {
        if (selected <= listPengurusInti.size()) {
            System.out.println(listPengurusInti.get(selected - 1).name +
                                " has been removed.");
            listPengurusInti.remove(selected - 1);
        } else {
            System.out.println(listKoordinator.get(selected - listPengurusInti.size() - 1).name +
                                " has been removed.");
            listKoordinator.remove(selected - listPengurusInti.size() - 1);
        }
    }

    public void showInfoMember(int selected) {
        System.out.printf("Name             : %s\n", selected <= listPengurusInti.size() ?
                                                    listPengurusInti.get(selected - 1).name : 
                                                    listKoordinator.get(selected - listPengurusInti.size() - 1).name);
        System.out.printf("Faculty          : %s\n", selected <= listPengurusInti.size() ?
                                                    listPengurusInti.get(selected - 1).faculty : 
                                                    listKoordinator.get(selected - listPengurusInti.size() - 1).faculty);
        System.out.printf("Major            : %s\n", selected <= listPengurusInti.size() ?
                                                    listPengurusInti.get(selected - 1).major : 
                                                    listKoordinator.get(selected - listPengurusInti.size() - 1).major);
        System.out.printf("NIM              : %s\n", selected <= listPengurusInti.size() ?
                                                    listPengurusInti.get(selected - 1).NIM : 
                                                    listKoordinator.get(selected - listPengurusInti.size() - 1).NIM);
        System.out.printf("Register Year    : %d\n", selected <= listPengurusInti.size() ?
                                                    listPengurusInti.get(selected - 1).registYear : 
                                                    listKoordinator.get(selected - listPengurusInti.size() - 1).registYear);
        System.out.printf("Position         : %s\n", selected <= listPengurusInti.size() ? "Core management" : "Coordinator");
    }
}
