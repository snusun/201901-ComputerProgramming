import java.io.*;
import java.util.*;
interface Repulsor {
    int repulsorDamage();   //have initial health +0 | deals additional 5 damage
}

interface FireBlaster {
    int fireBlasterDamage();//have initial health +0 | deals additional 15 damage, but damaged 5 health
}

interface WhipFlash {
    int whipFlashDamage();   //have initial health -30 | deals additional 3 damage, and heals 3 health
}

interface HulkBuster {
    int hulkBusterDamage();   //have initial health +50 | deals additional -2 damage
}


class Ironman {
    protected String name;
    protected int health;

    public Ironman(){}

    public Ironman(String name) {
        this.name = name;
        setHealth();
    }

    public String getName() {
        return name;
    }

    public int deal() {
        return 5;
    }

    protected void setHealth() {
        this.health = 50;
    }

    public void damaged(int damage) {
        this.health -= damage;
    }

    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public String toString() {
        return name + "[" + health + "]";
    }
}

public class Ex6Console {
    private final static int MAX_FIELD_SIZE = 5;
    private static ArrayList<Ironman> armyList = new ArrayList<>();

    private static String printEnemyHealth(int enemyHealth) {
        return "Enemy Health : " + enemyHealth;
    }

    private static String printMyHealth(int myHealth) {
        return "My Health : " + myHealth;
    }

    private final static String ACTION_STR =
            "====Select Action====\n"
                    + "1. Add Army\n"
                    + "2. Deal Enemy\n"
                    + "3. Quit\n"
                    + "=====================\n>>>";
    private final static String ARMOR_LIST_STR =
            "=======Armor List========\n"
                    + "0. Basic\n"
                    + "1. Repulsor\n"
                    + "2. Whip Flash\n"
                    + "3. Fire Blaster\n"
                    + "4. Repulsor & Whip Flash\n"
                    + "5. Repulsor & Fire Blaster\n"
                    + "6. Hulk Buster\n"
                    + "=========================\n>>>";

    private static void summon(int summonTarget, String name) {
        switch (summonTarget) {
            case 0:
                armyList.add(new Ironman(name));
                break;
            case 1:
                armyList.add(new IronmanRepulsor(name));
                break;
            case 2:
                armyList.add(new IronmanWhipFlash(name));
                break;
            case 3:
                armyList.add(new IronmanFireBlaster(name));
                break;
            case 4:
                armyList.add(new IronmanRepulsorWhipFlash(name));
                break;
            case 5:
                armyList.add(new IronmanRepulsorFireBlaster(name));
                break;
            case 6:
                armyList.add(new IronmanHulkBuster(name));
                break;
        }
    }


    private static int deal() {
        int totalDealing = 0;
        for (Ironman a : armyList) {
            totalDealing += a.deal();
        }
        return totalDealing;
    }

    private static int damaged(int myHealth) {
        if (armyList.isEmpty()) {
            return myHealth - 20;
        }
        else {
            for (Ironman armor : armyList) {
                armor.damaged(10);
            }
            return myHealth;
        }
    }

    private static String printAllArmy() {
        String ret = "====All Army List====\n";
        for (Ironman armor : armyList) {
            ret += armor + "\n";
        }
        ret += "=====================";
        return ret;
    }


    public static void main(String[] args) {
        int enemyHealth = 200;
        int myHealth = 200;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println(printEnemyHealth(enemyHealth));
                System.out.println(printMyHealth(myHealth));
                System.out.println(printAllArmy());
                System.out.print(ACTION_STR);

                int input = Integer.parseInt(br.readLine());

                switch (input) {
                    case 3:
                        return;

                    case 1:
                        if (armyList.size() >= MAX_FIELD_SIZE) {
                            System.out.println("Field is full");
                            continue;
                        }
                        else {
                            System.out.print(ARMOR_LIST_STR);

                            int summon_target = Integer.parseInt(br.readLine());
                            System.out.print("Name: ");
                            String name = br.readLine();

                            summon(summon_target, name);

                            System.out.println("New Armor Added!");
                        }
                        break;

                    case 2:
                        int d = deal();
                        enemyHealth -= d;
                        System.out.println("Total " + Integer.toString(d) + " damage dealed");
                        break;

                    default:
                        System.out.println("WRONG INPUT");
                        continue;
                }
                myHealth = damaged(myHealth);

                ArrayList<Ironman> removeList = new ArrayList<>();

                for (Ironman armor : armyList) {
                    if (armor.isDead()) {
                        System.out.println(armor.getName() + " has fallen");
                        removeList.add(armor);
                    }
                }

                armyList.removeAll(removeList);

                if (enemyHealth <= 0) {
                    System.out.println("YOU WIN!");
                    break;
                }
                else if (myHealth <= 0) {
                    System.out.println("YOU LOSE..");
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// TODO
class IronmanRepulsor extends Ironman implements Repulsor {

    public IronmanRepulsor(String name){
        this.name = name;
        setHealth();
    }
    public int deal() {
        return repulsorDamage();
    }

    public int repulsorDamage(){
        return 10;
    }

    public String toString() {
        return name + "[" + health + "]_ArmorType[Repulsor]";
    }
}


// TODO
class IronmanFireBlaster extends Ironman implements FireBlaster {
    public int deal() {
        return fireBlasterDamage();
    }

    public IronmanFireBlaster(String name){
        this.name = name;
        setHealth();
    }

    public int fireBlasterDamage(){
        damaged(5);
        return 20;
    }

    public String toString() {
        return name + "[" + health + "]_ArmorType[FireBlaster]";
    }

}


// TODO
class IronmanWhipFlash extends Ironman implements WhipFlash {
    protected void setHealth() {
        this.health = 20;
    }
    public int deal() {
        return whipFlashDamage();
    }

    public IronmanWhipFlash(String name){
        this.name = name;
        setHealth();
    }
    public int whipFlashDamage(){
        damaged(-3);
        return 8;
    }

    public String toString() {
        return name + "[" + health + "]_ArmorType[WhipFlash]";
    }

}


// TODO
class IronmanRepulsorFireBlaster extends Ironman  {
    public int deal() {
        damaged(10);
        return 15;
    }

    public IronmanRepulsorFireBlaster(String name){
        this.name = name;
        setHealth();
    }

    public String toString() {
        return name + "[" + health + "]_ArmorType[Repulsor][FireBlaster]";
    }

}


// TODO
class IronmanRepulsorWhipFlash extends Ironman {
    public int deal() {
        return 15;
    }

    public IronmanRepulsorWhipFlash(String name){
        this.name = name;
        setHealth();
    }

    public String toString() {
        return name + "[" + health + "]_ArmorType[Repulsor][WhipFlash]";
    }
}


// TODO
class IronmanHulkBuster extends Ironman implements HulkBuster {
    public int deal() {
        return hulkBusterDamage();
    }

    protected void setHealth() {
        this.health = 100;
    }

    public IronmanHulkBuster(String name){
        this.name = name;
        setHealth();
    }

    public int hulkBusterDamage(){
        return 3;
    }

    public String toString() {
        return name + "[" + health + "]_ArmorType[HulkBuster]";
    }
}
