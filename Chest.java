import java.util.ArrayList;
import java.util.Random;

public class Chest {
    private final int attack = 25;
    private final int magicAttack = 25;
    private final int HP = 100;
    private final int mana = 50;
    private final int defense = 50;
    private final int speed = 20;
    private final int dodge = 20;
    private final int durability = 100;

    private boolean open = false;
    private ArrayList<Item> chestItems = new ArrayList<>();
    private Random rand = new Random(System.nanoTime());
    private int itemsInChest, counter;
    private int weaponType, armorType, consumableType;
    private int ratePercentage;
    private String rarity, size;

    /**
     *
     */
    public Chest() {
        this(false);
    }

    /**
     *
     * @param open
     */
    public Chest(boolean open) {
        this.open = open;
    }

    /**
     *
     * @param chestItems
     */
    public void setChestItems(ArrayList<Item> chestItems) {
        this.chestItems = chestItems;
    }

    /**
     *
     * @return
     */
    public ArrayList<Item> getChestItems() {
        return chestItems;
    }

    /**
     * Opens up the chest
     */
    public void openChest() {
        open = true;
    }

    /**
     * Checks to see if chest is open or not
     * @return the chest is open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Fills the chest up with items
     */
    public void fillChest() {

        counter = 0;
        quantityItems();

        while(counter < itemsInChest) {

            ratePercentage = rand.nextInt(100) + 1;
            System.out.println("Item Type: " + ratePercentage);

            if(ratePercentage <= 25) {
                weaponType = rand.nextInt(100) + 1;
                rarityType();

                if(weaponType <= 25) {
                    Equip sword = new Weapon(attack, 0, durability, rarity, "Sword", true);
                    chestItems.add(sword);
                } else if(weaponType <= 45) {
                    Equip staff = new Weapon(0, magicAttack, durability, rarity, "Staff", true);
                    chestItems.add(staff);
                } else if(weaponType <= 65) {
                    Equip bowArrow = new Weapon(attack - 10, magicAttack - 15, durability, rarity, "Bow & Arrow", true);
                    chestItems.add(bowArrow);
                } else if(weaponType <= 75) {
                    Equip dagger = new Weapon(attack - 15, 0, durability, rarity, "Dagger", false);
                    chestItems.add(dagger);
                } else if(weaponType <= 90) {
                    Equip grimoire = new Weapon(0, magicAttack - 15, durability, rarity, "Grimoire", false);
                    chestItems.add(grimoire);
                } else {
                    Equip slingshot = new Weapon(attack - 15, magicAttack - 20, durability, rarity, "Slingshot", false);
                    chestItems.add(slingshot);
                }

            } else if(ratePercentage <= 50) {
                armorType = rand.nextInt(100) + 1;
                rarityType();

                if(armorType <= 25) {
                    Equip helmet = new Armor(HP - 95,  mana, defense - 25, 0, 0, durability, rarity, "Helmet");
                    chestItems.add(helmet);
                } else if(armorType <= 50) {
                    Equip chestArmor = new Armor(HP, 0 ,defense, 0, 0, durability, rarity, "Chest Armor");
                    chestItems.add(chestArmor);
                } else if(armorType <= 75) {
                    Equip legArmor = new Armor(HP - 85, mana - 40, defense - 25, 0, dodge, durability, rarity, "Leg Armor");
                    chestItems.add(legArmor);
                } else {
                    Equip boots = new Armor(HP - 95, 0,defense - 25, speed, 0, durability, rarity, "Boots");
                    chestItems.add(boots);
                }

            } else {
                consumableType = rand.nextInt(100) + 1;
                sizeType();

                if(consumableType <= 35) {
                    Consumable healthPot = new Consumable(100, 0, 0, size, "Health Pot");
                    chestItems.add(healthPot);
                } else if(consumableType <= 70) {
                    Consumable manaPot = new Consumable(0, 100, 0, size, "Mana Pot");
                    chestItems.add(manaPot);
                } else {
                    Consumable statBooster = new Consumable(0, 0, 10, size, "Stat Boost Drink");
                    chestItems.add(statBooster);
                }
            }

            counter++;
        }
    }

    /**
     *
     */
    public void quantityItems() {
        ratePercentage = rand.nextInt(100) + 1;

        if(ratePercentage <= 5) {
            itemsInChest = 1;
        } else if(ratePercentage <= 25) {
            itemsInChest = 2;
        } else if(ratePercentage <= 75) {
            itemsInChest = 3;
        } else if(ratePercentage <= 95) {
            itemsInChest = 4;
        } else {
            itemsInChest = 5;
        }
    }

    /**
     *
     */
    public void rarityType() {
        ratePercentage = rand.nextInt(100) + 1;

        if(ratePercentage <= 40) {
            rarity = "Common";
        } else if(ratePercentage <= 70) {
            rarity = "Uncommon";
        } else if(ratePercentage <= 80) {
            rarity = "Rare";
        } else if(ratePercentage <= 99) {
            rarity = "Epic";
        } else {
            rarity = "Legendary";
        }
    }

    /**
     *
     */
    public void sizeType() {
        ratePercentage = rand.nextInt(100) + 1;

        if(ratePercentage <= 50) {
            size = "Small";
        } else if(ratePercentage <= 80) {
            size = "Medium";
        } else {
            size = "Large";
        }
    }
}
