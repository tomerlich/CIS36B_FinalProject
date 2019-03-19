/**
 *
 */

public class Armor extends Equip implements Item, Comparable<Armor> {
    private double defense;
    private int durability;
    String rarity, armorType;

    /**
     * Default constructor for Armor.
     */
    public Armor() {
        this(0, 0,"Unknown Rarity", "Unknown Armor Type", false);
    }

    /**
     * Constructor for Armor.
     * @param defense the defense
     * @param durability the durability
     * @param rarity the rarity
     * @param armorType the armor type
     */
    public Armor(int defense, int durability, String rarity, String armorType, boolean equip) {
        super(durability, rarity, equip);
        this.defense = multiplier(rarity, defense);
        this.armorType = armorType;
    }

    /**
     * Gives you the amount of boosted defense the armor gives.
     * @return the defense
     */
    public double getDefense() {
        return defense;
    }

    /**
     * Assigns the defense to the piece of armor.
     * @param defense the defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Gives you the amount of durability the armor has.
     * @return the durability
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Assigns the durability to the armor.
     * @param durability the durability
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }

    /**
     * Gives you the rarity of the armor.
     * @return the rarity
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Assigns the rarity to the armor.
     * @param rarity the rarity
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * Gives you the type of armor it is.
     * @return the type of armor
     */
    public String getArmorType() {
        return armorType;
    }

    /**
     * Assigns the identification of the type of armor to the armor.
     * @param armorType the type of armor
     */
    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    /**
     * Uses the Armor.
     */
    public int use() {
        this.equipItem();
        return (int)defense;
    }

    /**
     *
     */
    public void delete() {

    }

    public int compareTo(Armor a) {
        if(this.equals(a)) {
            return 0;
        } else if(!this.armorType.equals(a.armorType)) {
            return this.armorType.compareTo(a.armorType);
        } else {
            return Double.compare(this.defense, a.defense);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Armor Type: " + this.armorType +
                "\nDefense: " + this.defense +
                super.toString();
    }

}
