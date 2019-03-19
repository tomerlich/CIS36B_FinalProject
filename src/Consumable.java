/**
 *
 */

public class Consumable implements Item, Comparable<Consumable>{

    int healHP, statBoost;
    String size, consumableType;

    /**
     * Default constructor for Consumable.
     */
    public Consumable() {
        this(0, 0, "Unknown Size", "Unknown Type");
    }

    /**
     * Constructor for Consumable.
     * @param healHP the amount of HP the potion will heal
     * @param statBoost the amount of boost the stat will receive
     * @param size the size of the potion
     * @param consumableType the type of potion
     */
    public Consumable(int healHP, int statBoost, String size, String consumableType) {
        this.healHP = scale(size, healHP);
        this.statBoost = scale(size, statBoost);
        this.size = size;
        this.consumableType = consumableType;
    }

    /**
     * Gives us the amount of HP the potion will heal.
     * @return the amount of HP the potion will heal
     */
    public int getHealHP() {
        return healHP;
    }

    /**
     * Assigns the amount of HP the potion will heal to the potion.
     * @param healHP the amount of HP the potion will heal
     */
    public void setHealHP(int healHP) {
        this.healHP = healHP;
    }

    /**
     * Gives us the value of boost the stat will receive.
     * @return how much the potion will boost the stat
     */
    public int getStatBoost() {
        return statBoost;
    }

    /**
     * Assigns the value of boost that the potion will give the player.
     * @param statBoost how much the potion will boost the stat
     */
    public void setStatBoost(int statBoost) {
        this.statBoost = statBoost;
    }

    /**
     * Gives us the size of the potion.
     * @return the size of the potion
     */
    public String getSize() {
        return size;
    }

    /**
     * Assigns the size of the potion to the potion.
     * @param size the size of the potion
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Scales the potion based on the size of the potion so that a larger potion gives greater healing and boosts.
     * @param size the size of the potion
     * @param stat the stat that the potion will boost to the player
     * @return
     */
    public int scale(String size, int stat) {
        if(size.equalsIgnoreCase("Small")) {
            return stat;
        } else if(size.equalsIgnoreCase("Medium")) {
            return stat * 2;
        } else {
            return stat * 4;
        }
    }

    /**
     * Uses the potion by returning to us the value that it heals/boosts us by.
     */
    public int use() {
        if(consumableType.equalsIgnoreCase("Health Pot")) {
            return this.healHP;
        } else {
            return this.statBoost;
        }
    }

    /**
     * Deletes the consumable by assigning all of its values to 0.
     */
    @Override
    public void delete() {
        this.healHP = 0;
        this.statBoost = 0;
        this.consumableType = "ALREADY USED";
        this.size = "NO SIZE";
    }

    /**
     * Compares one consumable with another one.
     * First it compares by the type of consumable.
     * Second it compares by the healing that the potion gives (which also reflects its size).
     * Lastly it compares the stat boost, if it's not a healing potion.
     *
     * @param c the consumable to be compared
     * @return the integer value that will reveal which consumable comes before the other
     */
    public int compareTo(Consumable c) {
        if(this.equals(c)) {
            return 0;
        } else if(!this.consumableType.equals(c.consumableType)) {
            return this.consumableType.compareTo(c.consumableType);
        } else if(this.healHP != c.healHP) {
            return Double.compare(this.healHP, c.healHP);
        } else {
            return Double.compare(this.statBoost, c.statBoost);
        }
    }

    /**
     * Compares the two consumables to see if they are the same.
     * @param o the consumable to be compared with
     * @return a boolean value that equates to true if they are the same and false if they are not
     */
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Consumable)) {
            return false;
        } else {
            Consumable c = (Consumable) o;
            return this.healHP == c.healHP && this.statBoost == c.statBoost && this.size.equals(c.size) && this.consumableType.equals(c.consumableType);
        }
    }

    /**
     * Gives us the toString of the Consumable class. Has information regarding the consumable.
     * @return the toString of the Consumable class
     */
    @Override
    public String toString() {
        return "Consumable Type: " + this.consumableType +
                "\nHP: " + this.healHP +
                "\nStat Boost: " + this.statBoost +
                "\nSize: " + this.size;
    }

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}
