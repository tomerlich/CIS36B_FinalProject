



public class Consumable implements Item{

    int healHP, healMana, statBoost;
    String size, consumableType;

    /**
     *
     */
    public Consumable() {
        this(0, 0, 0, "Unknown Size", "Unknown Type");
    }

    /**
     *
     * @param healHP
     * @param healMana
     * @param statBoost
     * @param size
     * @param consumableType
     */
    public Consumable(int healHP, int healMana, int statBoost, String size, String consumableType) {
        this.healHP = scale(size, healHP);
        this.healMana = scale(size, healMana);
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
    public int getHealMana() {
        return healMana;
    }

    /**
     *
     * @param healMana
     */
    public void setHealMana(int healMana) {
        this.healMana = healMana;
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
     * @return
     */
    @Override
    public int count() {
        return 0;
    }

    /**
     *
     */
    @Override
    public void use() {

    }

    /**
     *
     */
    @Override
    public void delete() {

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Consumable Type: " + this.consumableType +
                "\nHP: " + this.healHP +
                "\nMana: " + this.healMana +
                "\nStat Boost: " + this.statBoost +
                "\nSize: " + this.size;
    }
}
