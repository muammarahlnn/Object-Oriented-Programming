import java.util.HashMap;

public class Item {
    private String name;
    private int price;
    HashMap<String, Integer> spellMap = new HashMap<>();

    

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price; 
    }

    public void setPrice(int price) {
        this.price = price;
    }    
}