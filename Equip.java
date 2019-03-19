public class Equip {

    private int durability;
    private String rarity;
    private boolean equip;

    /**
     * Default constructor for Equip.
     */
    public Equip() {
        this(0, "Unknown Rarity", false);
    }

    /**
     * Constructor for Equip.
     * @param durability the durability of an item, how long an item will last
     * @param rarity the rarity of an item, how hard an item is to get
     */
    public Equip(int durability, String rarity, boolean equip) {
        this.durability = durability;
        this.rarity = rarity;
        this.equip = equip;
    }

    /**
     * Gives us the durability of the equipment.
     * @return the durability
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Assigns the durability to the equipment.
     * @param durability the durability
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }

    /**
     * Gives us the rarity of the equipment.
     * @return the rarity
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Assigns the rarity to the equipment.
     * @param rarity the rarity
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * Multiplies the stat of the equipment by a certain value based on how rare the
     * item is to obtain.
     * @param rarity the rarity of the item
     * @param stat the stat of the item
     * @return the stat after the multiplier has been applied
     */
    public double multiplier(String rarity, int stat) {
        if(rarity.equalsIgnoreCase("Common")) {
            return stat;
        } else if(rarity.equalsIgnoreCase("Uncommon")) {
            return stat * 1.5;
        } else if(rarity.equalsIgnoreCase("Rare")) {
            return stat * 2;
        } else if(rarity.equalsIgnoreCase("Epic")) {
            return stat * 3;
        } else {
            return stat * 5;
        }
    }

    /**
     * Checks to see whether the equipment is broken or not.
     * @return the status of the equipment's durability
     */
    public boolean broken() {
        if(durability == 0) {
            return true;
        }
        return false;
    }

    /**
     * Equips the item.
     */
    public void equipItem() {
        equip = true;
    }

    /**
     * Checks to see if the equipment is equipped to the player.
     * @return the status of the item being equipped
     */
    public boolean isEquipped() {
        return equip;
    }

    /**
     * Gives us the string for durability and rarity.
     * @return the string of durability and rarity
     */
    @Override
    public String toString() {
        return "\nDurability: " + durability +
                "\nRarity: " + rarity;
    }


}
