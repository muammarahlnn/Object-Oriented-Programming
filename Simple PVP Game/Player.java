import java.util.*;

public class Player {
    private String name, role;
    private int money;
    private double damage, hp, energy;
    private List<Item> item;
    private List<String> spellItem = new ArrayList<>();

    
    //constructor
    public Player(String name, String role) {
        this.name = name;
        this.role = role.toLowerCase();
        this.money = 10000;
        this.energy = 100;

        if (this.role.equals("attacker")) {
            this.damage = 150;
            this.hp = 850;
        } else if (this.role.equals("defender")) {
            this.damage = 70;
            this.hp = 1200;
        } else {
            System.out.println("Role not found.");
        }

        this.item = new ArrayList<>();
        this.item.add(new Item("Healing Salve"));
        this.spellItem.add("Healing Salve");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
        if (this.role.equals("attacker")) {
            this.damage = 150;
            this.hp = 850;
        } else if (this.role.equals("defender")) {
            this.damage = 70;
            this.hp = 1200;
        }
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public int getMoney() {
        return this.money;
    }


    public double getDamage() {
        return this.damage;
    }

    public double getHp() {
        return this.hp;
    }

    public double getEnergy() {
        return this.energy;
    }

    public void showStatus() {
        System.out.printf("%s's STATUS\n", this.name);
        System.out.printf("Role      : %s\n", this.role);
        System.out.printf("Damage    : %s\n", this.damage);
        System.out.printf("HP        : %s\n", isDead() ? 0 : this.hp);
        System.out.printf("Energy    : %s\n", this.getEnergy() <= 0 ? 0 : this.energy);
        System.out.printf("Money     : Rp. %s\n\n", this.money);
    }

    public void showItems() {
        System.out.println(this.name + " item :");
        for (int i = 0; i < this.item.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.item.get(i).getName());
        }
        System.out.println();
    }

    public void showSpellItems() {
        System.out.println("List item :");
        for (int i = 0; i < this.spellItem.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.spellItem.get(i));
        }
        System.out.println();
    }

    public void buy(int selectedItem) {
        if (this.money - Main.priceShop.get(selectedItem - 1) < 0) {
            System.out.println("Your money is not enough :(");
            return;
        } else {
            this.money -= Main.priceShop.get(selectedItem - 1);
            this.item.add(new Item(Main.shop.get(selectedItem - 1), Main.priceShop.get(selectedItem - 1)));

            if (selectedItem <= 5) {
                this.damage += Main.attributeShop.get(selectedItem - 1);
            }
            if (selectedItem > 5 && selectedItem <= 10) {
                this.hp += Main.attributeShop.get(selectedItem - 1);
            }
            if (selectedItem > 10) {
                this.spellItem.add(Main.shop.get(selectedItem - 1));
            }   
        
            System.out.println("You just bought " + Main.shop.get(selectedItem - 1));
        }
        
    }

    public void launchAttack(Player target) {
        if (this.energy <= 0) {
            System.out.println("Your energy is not enough, please charge ur energy");
        } else {
            this.energy -= 25;
            target.hp -= this.damage;
            System.out.println(this.name + " is attacking " + target.getName() + " !!!");
        }

        target.showStatus();
    }

    public void charge() {
        this.energy += 50;
        System.out.println(this.name + " is charging!");
        this.showStatus();
    }

    public boolean isDead() {
        if (this.hp <= 0) return true;
        else return false;
    }

    public void useItem(int playerItem, Player target) {
        if (this.spellItem.get(playerItem - 1).equalsIgnoreCase("Healing Salve")) {
            this.spellItem.remove(playerItem - 1);
            this.hp += this.hp * 0.2;
            System.out.println(this.name + " is using Healing Salve");
            this.showStatus();
        } else if (this.spellItem.get(playerItem - 1).equalsIgnoreCase("Attack Booster")) {
            this.spellItem.remove(playerItem - 1);
            this.damage += this.damage * 0.1;
            System.out.println(this.name + " is using Attack Booster");
            this.showStatus();
        } else if (this.spellItem.get(playerItem - 1).equalsIgnoreCase("Poison")) {
            this.spellItem.remove(playerItem - 1);
            target.damage -= target.damage * 0.05;
            System.out.println(this.name + " is using Poison");
            target.showStatus();
        }
    }
}