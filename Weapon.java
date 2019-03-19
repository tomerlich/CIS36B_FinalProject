
public class Weapon extends Equip implements Item, Comparable<Weapon>{
	private double attack;
	private  int durability;
	private String rarity, weaponType;
	private boolean mainWeapon;

	/**
	 * Default constructor for Weapon.
	 */
	public Weapon() {
		this(0, 0, "Unknown Rarity", "Unknown Weapon Type", false, false);
	}

	/**
	 * Constructor for Weapon.
	 * @param attack the attack
	 * @param durability the durability
	 * @param rarity the rarity
	 * @param weaponType the weapon type
	 * @param mainWeapon identifies whether it's the main or sub weapon
	 */
	public Weapon(int attack, int durability, String rarity, String weaponType, boolean mainWeapon, boolean equip) {
		super(durability, rarity, equip);
		this.attack = multiplier(rarity, attack);
		this.weaponType = weaponType;
		this.mainWeapon = mainWeapon;
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
	 * @param w
	 * @return
	 */
	@Override
	public int compareTo(Weapon w) {
		if(this.equals(w)) {
			return 0;
		} else if(!this.weaponType.equals(w.weaponType)) {
			return this.weaponType.compareTo(w.weaponType);
		} else {
			return Double.compare(this.attack, w.attack);
		}
	}

	/**
	 *
	 */
	@Override
	public int use() {
		this.equipItem();
		return (int) this.attack;
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
				super.toString() +
				"\nMain Weapon: " + this.mainWeapon;
	}
}
