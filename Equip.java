public class Equip implements Item {

    private int durability;
    private String rarity;

    /**
     *
     */
    public Equip() {
        this(0, "Unknown Rarity");
    }

    /**
     *
     * @param durability
     * @param rarity
     */
    public Equip(int durability, String rarity) {
        this.durability = durability;
        this.rarity = rarity;
    }

    /**
     *
     * @return
     */
    public int getDurability() {
        return durability;
    }

    /**
     *
     * @param durability
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }

    /**
     *
     * @return
     */
    public String getRarity() {
        return rarity;
    }

    /**
     *
     * @param rarity
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     *
     * @param rarity
     * @param stat
     * @return
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
     *
     * @return
     */
    public boolean broken() {
        if(durability == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void use() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String toString() {
        return "\nDurability: " + durability +
                "\nRarity: " + rarity;
    }
}
