import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> shop;
    static ArrayList<Integer> priceShop, attributeShop;
    static HashMap<String, Integer> spellMap;
    static String player1Name, player1Role;
    static String player2Name, player2Role;
    static boolean isPlaying = true;

    public static void main(String[] args) {
        //util
        shop = new ArrayList<>();
        priceShop = new ArrayList<>();
        attributeShop = new ArrayList<>();
        spellMap = new HashMap<>();

        //shop
        //item attack
        shop.add("Divine Rapier    ");
        priceShop.add(3000);
        attributeShop.add(30);
        shop.add("Battle Fury      ");
        priceShop.add(2000);
        attributeShop.add(20);
        shop.add("Daedalus         ");
        priceShop.add(2500);
        attributeShop.add(24);
        shop.add("Butterfly        ");
        priceShop.add(2500);
        attributeShop.add(24);
        shop.add("Abyssal Blade    ");
        priceShop.add(3000);
        attributeShop.add(28);

        //item defend
        shop.add("Crimson Guard    ");
        priceShop.add(2500);
        attributeShop.add(20);
        shop.add("Blade Mail       ");
        priceShop.add(2000);
        attributeShop.add(20);
        shop.add("Black King Bar   ");
        priceShop.add(2500);
        attributeShop.add(20);
        shop.add("Shiva's Guard    ");
        priceShop.add(2500);
        attributeShop.add(25);
        shop.add("Heart Tarrasque ");
        priceShop.add(3000);
        attributeShop.add(30);

        //item spell
        shop.add("Healing Salve");
        priceShop.add(500);
        shop.add("Attack Booster");
        priceShop.add(500);
        shop.add("Poison");
        priceShop.add(500);



        //--------------- Gameplay ----------------------

        //create character
        System.out.println("------------------ Simple RPG Game ------------------\n");
        System.out.println("Press enter to continue");
        sc.nextLine();

        System.out.println(" -- SET PROFILE -- ");
        setProfile();
        
        Player player1 = new Player(player1Name, player1Role);
        Player player2 = new Player(player2Name, player2Role);

        player1.showStatus();
        player2.showStatus();

        while (true) {
            System.out.print("Are you sure this data is correct?\n(y/n)\n => ");
            String corrected = sc.nextLine();

            if (corrected.equals("y")) {
                break;
            } else {
                setProfile();
            }
            player1.setName(player1Name);
            player1.setRole(player1Role);

            player2.setName(player2Name);
            player2.setRole(player2Role);

            player1.showStatus();
            player2.showStatus();
        }

        // SET ITEM
        System.out.println(" -- SET ITEM -- ");
        System.out.println(player1.getName() + " is setting his item :");
        setItem(player1);

        System.out.println(player2.getName() + " is setting his item :");
        setItem(player2);

        // BATTLE
        System.out.printf(" -- %s VS %s --\n", player1.getName().toUpperCase(), player2.getName().toUpperCase());

        while (isPlaying) {
            playerTurn(player1, player2);
            playerTurn(player2, player1);
        }
    }

    // method helper

    static void setProfile() {
        System.out.print("Player 1 :\nEnter your name\n => ");
        player1Name = sc.nextLine();
        System.out.print("Select your role :\n1. Attacker\n2. Defender\n => ");
        player1Role = sc.nextLine();

        System.out.println();

        System.out.print("Player 2 :\nEnter your name\n => ");
        player2Name = sc.nextLine();
        System.out.print("Select your role :\n1. Attacker\n2. Defender\n => ");
        player2Role = sc.nextLine();
    }

    static void setItem(Player player) {
        while (true) {
            showShop();

            System.out.print("Select item you wanna buy\n => ");
            int selectedItem = sc.nextInt();
            itemInfo(selectedItem);

            System.out.println("\nYour money : Rp. " + player.getMoney());
            System.out.print("Are you sure want to buy it ?\n(y/n)\n => ");
            String wannaBuy = sc.next();
            if (wannaBuy.equals("y")) {
                player.buy(selectedItem);
                player.showStatus();
                player.showItems();
                System.out.print("Do you want to buy more item ?\n(y/n)\n => ");
                String wannaBuyMore = sc.next();
                if (wannaBuyMore.equals("y") == false) {
                    player.showStatus();
                    player.showItems();
                    break;
                }
            }
        }   
    }

    static void playerTurn(Player player, Player target) {
        if (player.isDead()) {
            System.out.println(player.getName() + " IS DEAD !!!");
            System.out.println(target.getName() + " WON !!!");
            isPlaying = false;
            return;
        } else if (target.isDead()) {
            System.out.println(target.getName() + " IS DEAD !!!");
            System.out.println(player.getName() + " WON !!!");
            isPlaying = false;
            return;
        }
        
        System.out.println(player.getName() + " turn :");
        System.out.print("1. Launch Attack\n2. Use Item\n3. Charge\n4. Quit\n => ");
        int player1Turn = sc.nextInt();
        switch (player1Turn) {
            case 1:
                player.launchAttack(target);
                break;
            case 2:
                player.showSpellItems();
                System.out.print("Select item you want to use :\n => ");
                int playerItem = sc.nextInt();
                player.useItem(playerItem, target);
                break;
            case 3:
                player.charge();
                break;
            case 4:
                System.out.println("Thank you for playing this game :)");
                isPlaying = false;
                return;
        }
    }

    static void showShop() {
        System.out.println("----- SHOP -----\n");
        System.out.println("Attack Item : ");
        for (int i = 0; i < shop.size(); i++) {
            if (i % 5 == 0 && i != 0 && i < 10) System.out.println("Defend Item : ");
            if (i % 10 == 0 && i != 0) System.out.println("Spell Item : ");
            System.out.printf("%d. %s | Rp. %d\n", i + 1, shop.get(i), priceShop.get(i));
        }
    }

    static void itemInfo(int selectedItem) {
        switch (selectedItem) {
            case 1:
                System.out.println("Divine Rapier info :");
                System.out.print("Passive : True Strike\n");
                System.out.print("Your attacks cannot miss\n");
                System.out.print("+30 Damage\n");
                break;
            case 2:
                System.out.println("Battle Fury info :");
                System.out.print("Passive : Cleave\n");
                System.out.print("Deals attack damage as physical damage in a cone up to 650 around the target.\n");
                System.out.print("+20 Damage\n");
                break;
            case 3:
                System.out.println("Daedalus info :");
                System.out.print("Passive : Critical Strike\n");
                System.out.print("Grants each attack a 30% chance to deal 225% damage.");
                System.out.print("+24 Damage\n");
                break;
            case 4:
                System.out.println("Butterfly info :");
                System.out.println("Passive : Evasion");
                System.out.println("Enemy's attack has 25% chance to miss.");
                System.out.println("+20 Damage");
                break;
            case 5:
                System.out.println("Abyssal Blade info :");
                System.out.println("Passive : Bash");
                System.out.println("Grants 25% chance on hit to stun the target for 1,5 seconds.");
                System.out.println("+28 Damage");
                break;
            case 6:
                System.out.println("Crimson Guard info :");
                System.out.println("Passive : Damage Block");
                System.out.println("Grants a 50% chance to block 70 damage from incoming attacks.");
                System.out.println("+20 HP");
                break;
            case 7:
                System.out.println("Blade Mail info :");
                System.out.println("Passive : Damage Return");
                System.out.println("Grants 15% chance to duplicate any damage taken back to the enemy.");
                System.out.println("+20 HP");
                break;
            case 8:
                System.out.println("Black King Bar info :");
                System.out.println("Active : Avatar");
                System.out.println("For 10 seconds become spell immunity.");
                System.out.println("+20 HP");
                break;
            case 9:
                System.out.println("Shiva's guard info :");
                System.out.println("Passive : Freezing Aura");
                System.out.println("Reduces enemy attack by 5.");
                System.out.println("+25 HP");
                break;
            case 10:
                System.out.println("Heart of Tarrasque :");
                System.out.println("Passive : Health Regen");
                System.out.println("Restores 5% HP every 2 second.");
                System.err.println("+30 HP");
            case 11:
                System.out.println("Healing Salve :");
                System.out.println("Restore 20% HP");
                break;
            case 12:
                System.out.println("Attack Booster :");
                System.out.println("Plus the user attack by 10%");
                break;
            case 13:
                System.out.println("Poison info : ");
                System.out.println("Reduce enemy attack's by 5%");
                break;
            default:
                System.out.println("Item not found.");
                break;
        }
    }
}
