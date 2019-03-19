/**
 *
 */

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
	 * Give the attack strength that the weapon provides.
	 * @return the attack
	 */
	public double getAttack() {
		return attack;
	}

	/**
	 * Assigns the attack to the weapon.
	 * @param attack the attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Gives the durability of the weapon.
	 * @return the durability
	 */
	public int getDurability() {
		return durability;
	}

	/**
	 * Assigns the durability to the weapon.
	 * @param durability the durability
	 */
	public void setDurability(int durability) {
		this.durability = durability;
	}

	/**
	 * Gives the rarity of the weapon.
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * Assigns the rarity to the weapon.
	 * @param rarity the rarity
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	/**
	 * Gives us the type of weapon it is.
	 * @return the weapon type
	 */
	public String getWeaponType() {
		return weaponType;
	}

	/**
	 * Assigns the type of weapon to the weapon.
	 * @param weaponType the weapon type
	 */
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	/**
	 * Gives us the information to figure out whether the weapon is a main weapon or sub weapon.
	 * @return the boolean value for if it is a main weapon
	 */
	public boolean isMainWeapon() {
		return mainWeapon;
	}

	/**
	 * Assigns whether it is a main weapon or not to main weapon.
	 * @param mainWeapon the boolean value for if it is a main weapon
	 */
	public void setMainWeapon(boolean mainWeapon) {
		this.mainWeapon = mainWeapon;
	}

	/**
	 * Compares another weapon to the current one.
	 * First compares it by the names/type of the weapon.
	 * Second compares it by the damage that the weapon deals.
	 * (The higher the damage the rarer the weapon is.)
	 *
	 * @param w the weapon that is to be compared
	 * @return the integer value that will reveal which weapon comes before the other
	 */
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
	 * Uses the weapon by equipping it and then return its attack value.
	 */
	public int use() {
		this.equipItem();
		return (int) this.attack;
	}

	/**
	 * Deletes the weapon by setting its values equal to 0.
	 */
	@Override
	public void delete() {
		this.attack = 0;
		this.durability = 0;
		this.weaponType = "DESTROYED";
		this.rarity = "NO RARITY";
		this.mainWeapon = false;
	}

	/**
	 * Compares the two weapons to see if they are the same.
	 * @param o the weapon to be compared with
	 * @return a boolean value that equates to true if they are the same and false if they are not
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		} else if(!(o instanceof Weapon)) {
			return false;
		} else {
			Weapon w = (Weapon) o;
			return this.attack == w.attack && this.durability == w.durability && this.weaponType.equals(w.weaponType) && this.rarity.equals(w.rarity) && this.mainWeapon == w.mainWeapon && this.isEquipped() == w.isEquipped();
		}
	}

	/**
	 * Gives us the toString of the Weapon class. Has information regarding the weapon.
	 * @return the toString of the Weapon class
	 */
	@Override
	public String toString() {
		return "Weapon Type: " + this.weaponType +
				"\nAttack: " + this.attack +
				super.toString() +
				"\nMain Weapon: " + this.mainWeapon;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}
