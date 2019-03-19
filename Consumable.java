/**
 *
 */


public class Consumable implements Item, Comparable<Consumable>{

    int healHP, statBoost;
    String size, consumableType;

    /**
     *
     */
    public Consumable() {
        this(0, 0, "Unknown Size", "Unknown Type");
    }

    /**
     *
     * @param healHP
     * @param statBoost
     * @param size
     * @param consumableType
     */
    public Consumable(int healHP, int statBoost, String size, String consumableType) {
        this.healHP = scale(size, healHP);
        this.statBoost = scale(size, statBoost);
        this.size = size;
        this.consumableType = consumableType;
    }

    /**
     *
     * @return
     */
    public int getHealHP() {
        return healHP;
    }

    /**
     *
     * @param healHP
     */
    public void setHealHP(int healHP) {
        this.healHP = healHP;
    }

    /**
     *
     * @return
     */
    public int getStatBoost() {
        return statBoost;
    }

    /**
     *
     * @param statBoost
     */
    public void setStatBoost(int statBoost) {
        this.statBoost = statBoost;
    }

    /**
     *
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

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
     *
     */
    @Override
    public int use() {
        if(consumableType.equalsIgnoreCase("Health Pot")) {
            return this.healHP;
        } else {
            return this.statBoost;
        }
    }

    /**
     *
     */
    @Override
    public void delete() {

    }

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
     *
     * @return
     */
    @Override
    public String toString() {
        return "Consumable Type: " + this.consumableType +
                "\nHP: " + this.healHP +
                "\nStat Boost: " + this.statBoost +
                "\nSize: " + this.size;
    }
}
