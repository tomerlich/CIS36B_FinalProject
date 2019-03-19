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
     * Uses the armor by equipping it and then return its defense value.
     */
    public int use() {
        this.equipItem();
        return (int)defense;
    }

    /**
     * Deletes the armor by setting its values equal to 0.
     */
    public void delete() {
        this.defense = 0;
        this.durability = 0;
        this.armorType = "DESTROYED";
        this.rarity = "NO RARITY";
    }

    /**
     *
     * @param a
     * @return
     */
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
     * Compares the two pieces of armor to see if they are the same.
     * @param o the armor to be compared with
     * @return a boolean value that equates to true if they are the same and false if they are not
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Armor)) {
            return false;
        } else {
            Armor a = (Armor) o;
            return this.defense == a.defense && this.durability == a.durability && this.armorType.equals(a.armorType) && this.rarity.equals(a.rarity) && this.isEquipped() == a.isEquipped();
        }
    }

    /**
     * Gives us the toString of the Armor class. Has information regarding the armor.
     * @return the toString of the Armor class
     */
    @Override
    public String toString() {
        return "Armor Type: " + this.armorType +
                "\nDefense: " + this.defense +
                super.toString();
    }
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
