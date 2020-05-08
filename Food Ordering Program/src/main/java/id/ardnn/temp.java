package id.ardnn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class temp {
    public static void main(String[] args) {
        List<List<String>> lsMakanan = new ArrayList<>();
        System.out.println(lsMakanan);
        
        for (int i = 0; i < 5; i++) {
            lsMakanan.add(new ArrayList<>());
            lsMakanan.get(i).add("you");
        }
        System.out.println(lsMakanan);

        lsMakanan.clear();
        System.out.println(lsMakanan);
    }
}