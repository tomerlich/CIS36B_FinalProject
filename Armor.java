/**
 *
 */

public class Armor extends Equip implements Item {
    private double totHP, totMana, totDefense, totSpeed, totDodge;
    private double HP, mana, defense, speed, dodge;
    private int durability;
    String rarity, armorType;

    /**
     *
     */
    public Armor() {
        this(0, 0, 0,0, 0, 0,"Unknown Rarity", "Unknown Armor Type");
    }

    /**
     *
     * @param HP
     * @param mana
     * @param defense
     * @param speed
     * @param dodge
     * @param durability
     * @param rarity
     * @param armorType
     */
    public Armor(int HP, int mana, int defense, int speed, int dodge, int durability, String rarity, String armorType) {
        super(durability, rarity);
        this.HP = multiplier(rarity, HP);
        this.mana = multiplier(rarity, mana);
        this.defense = multiplier(rarity, defense);
        this.speed = multiplier(rarity, speed);
        this.dodge = multiplier(rarity, dodge);
        this.armorType = armorType;
    }

    /**
     *
     * @return
     */
    public double getTotHP() {
        return totHP;
    }

    /**
     *
     * @param totHP
     */
    public void setTotHP(int totHP) {
        this.totHP = totHP;
    }

    /**
     *
     * @return
     */
    public double getTotMana() {
        return totMana;
    }

    /**
     *
     * @param totMana
     */
    public void setTotMana(int totMana) {
        this.totMana = totMana;
    }

    /**
     *
     * @return
     */
    public double getTotDefense() {
        return totDefense;
    }

    /**
     *
     * @param totDefense
     */
    public void setTotDefense(int totDefense) {
        this.totDefense = totDefense;
    }

    /**
     *
     * @return
     */
    public double getTotSpeed() {
        return totSpeed;
    }

    /**
     *
     * @param totSpeed
     */
    public void setTotSpeed(int totSpeed) {
        this.totSpeed = totSpeed;
    }

    /**
     *
     * @return
     */
    public double getTotDodge() {
        return totDodge;
    }

    /**
     *
     * @param totDodge
     */
    public void setTotDodge(int totDodge) {
        this.totDodge = totDodge;
    }

    /**
     *
     * @return
     */
    public double getHP() {
        return HP;
    }

    /**
     *
     * @param HP
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     *
     * @return
     */
    public double getMana() {
        return mana;
    }

    /**
     *
     * @param mana
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     *
     * @return
     */
    public double getDefense() {
        return defense;
    }

    /**
     *
     * @param defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     *
     * @return
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     */
    public double getDodge() {
        return dodge;
    }

    /**
     *
     * @param dodge
     */
    public void setDodge(int dodge) {
        this.dodge = dodge;
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
     * @return
     */
    public String getArmorType() {
        return armorType;
    }

    /**
     *
     * @param armorType
     */
    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    /**
     *
     */
    public void use() {

    }

    /**
     *
     */
    public void delete() {

    }

    /**
     *
     * @return
     */
    public int count() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Armor Type: " + this.armorType +
                "\nHP: " + this.HP +
                "\nMana: " + this.mana +
                "\nDefense: " + this.defense +
                "\nSpeed: " + this.speed +
                "\nDodge: " + this.dodge +
                super.toString();
    }

}
