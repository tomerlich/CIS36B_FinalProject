
public class Weapon extends Equip implements Item, Comparable<Weapon>{
	private double totAttack, totMagicAttack;
	private double attack, magicAttack;
	private  int durability;
	private String rarity, weaponType;
	private boolean mainWeapon;

	/**
	 *
	 */
	public Weapon() {
		this(0, 0, 0, "Unknown Rarity", "Unknown Weapon Type", false);
	}

	/**
	 *
	 * @param attack
	 * @param magicAttack
	 * @param durability
	 * @param rarity
	 * @param weaponType
	 * @param mainWeapon
	 */
	public Weapon(int attack, int magicAttack, int durability, String rarity, String weaponType, boolean mainWeapon) {
		super(durability, rarity);
		this.attack = multiplier(rarity, attack);
		this.magicAttack = multiplier(rarity, magicAttack);
		this.weaponType = weaponType;
		this.mainWeapon = mainWeapon;
	}

	/**
	 *
	 * @return
	 */
	public double getTotAttack() {
		return totAttack;
	}

	/**
	 *
	 * @param totAttack
	 */
	public void setTotAttack(int totAttack) {
		this.totAttack = totAttack;
	}

	/**
	 *
	 * @return
	 */
	public double getTotMagicAttack() {
		return totMagicAttack;
	}

	/**
	 *
	 * @param totMagicAttack
	 */
	public void setTotMagicAttack(int totMagicAttack) {
		this.totMagicAttack = totMagicAttack;
	}

	/**
	 *
	 * @return
	 */
	public double getAttack() {
		return attack;
	}

	/**
	 *
	 * @param attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 *
	 * @return
	 */
	public double getMagicAttack() {
		return magicAttack;
	}

	/**
	 *
	 * @param magicAttack
	 */
	public void setMagicAttack(int magicAttack) {
		this.magicAttack = magicAttack;
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
	public String getWeaponType() {
		return weaponType;
	}

	/**
	 *
	 * @param weaponType
	 */
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	/**
	 *
	 * @return
	 */
	public boolean isMainWeapon() {
		return mainWeapon;
	}

	/**
	 *
	 * @param mainWeapon
	 */
	public void setMainWeapon(boolean mainWeapon) {
		this.mainWeapon = mainWeapon;
	}

	/**
	 *
	 * @param arg0
	 * @return
	 */
	@Override
	public int compareTo(Weapon arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 *
	 */
	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 *
	 */
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "Weapon Type: " + this.weaponType +
				"\nAttack: " + this.attack +
				"\nMagic Attack: " + this.magicAttack +
				super.toString() +
				"\nMain Weapon: " + this.mainWeapon;
	}
}
