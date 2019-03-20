import java.util.ArrayList;
import java.util.Random;

public class Chest {

	private boolean open = false;
	private ArrayList<Item> chestItems = new ArrayList<>();
	private Random rand = new Random(System.nanoTime());
	private int itemsInChest, counter;
	private int weaponType, armorType, consumableType;
	private int ratePercentage;
	private String rarity, size;
	private boolean isFilled;

	/**
	 * Default constructor for Chest.
	 */
	public Chest() {
		this(false, false);
	}

	/**
	 * Constructor for Chest.
	 * 
	 * @param open the status of whether the chest is open or not
	 */
	public Chest(boolean open, boolean filled) {
		this.open = open;
		this.isFilled = filled;
		if (this.isFilled) {
			this.fillChest();
		}
	}

	/**
	 * Assigns some chest items to the chest.
	 * 
	 * @param chestItems an Item arraylist
	 */
	public void setChestItems(ArrayList<Item> chestItems) {
		this.chestItems = chestItems;
	}

	/**
	 * Gives us the chest items arraylist.
	 * 
	 * @return the chest item arraylist
	 */
	public ArrayList<Item> getChestItems() {
		return chestItems;
	}

	/**
	 * Opens up the chest.
	 */
	public void openChest() {
		open = true;
		isFilled = false;
	}

	/**
	 * Checks to see if chest is open or not.
	 * 
	 * @return the status of whether the chest is open or not
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Fills the chest up with items.
	 */
	public void fillChest() {

		counter = 0;
		quantityItems();

		while (counter < itemsInChest) {

			ratePercentage = rand.nextInt(100) + 1;
			//System.out.println("Item Type: " + ratePercentage);

			if (ratePercentage <= 25) {
				weaponType = rand.nextInt(100) + 1;
				rarityType();

				if (weaponType <= 25) {
					Weapon sword = new Weapon(25, 100, rarity, "Sword", true, false);
					chestItems.add(sword);
				} else if (weaponType <= 50) {
					Weapon spear = new Weapon(25, 100, rarity, "Spear", true, false);
					chestItems.add(spear);
				} else if (weaponType <= 70) {
					Weapon mace = new Weapon(25, 100, rarity, "Mace", true, false);
					chestItems.add(mace);
				} else if (weaponType <= 80) {
					Weapon dagger = new Weapon(10, 100, rarity, "Dagger", false, false);
					chestItems.add(dagger);
				} else if (weaponType <= 90) {
					Weapon brassKnuckles = new Weapon(10, 100, rarity, "Brass Knuckles", false, false);
					chestItems.add(brassKnuckles);
				} else {
					Weapon hammer = new Weapon(10, 100, rarity, "Hammer", false, false);
					chestItems.add(hammer);
				}

			} else if (ratePercentage <= 50) {
				armorType = rand.nextInt(100) + 1;
				rarityType();

				if (armorType <= 25) {
					Armor helmet = new Armor(10, 100, rarity, "Helmet", false);
					chestItems.add(helmet);
				} else if (armorType <= 50) {
					Armor chestArmor = new Armor(20, 100, rarity, "Chest Armor", false);
					chestItems.add(chestArmor);
				} else if (armorType <= 75) {
					Armor legArmor = new Armor(10, 100, rarity, "Leg Armor", false);
					chestItems.add(legArmor);
				} else {
					Armor boots = new Armor(10, 100, rarity, "Boots", false);
					chestItems.add(boots);
				}

			} else {
				consumableType = rand.nextInt(100) + 1;
				sizeType();

				if (consumableType <= 80) {
					Consumable healthPot = new Consumable(100, 0, size, "Health Pot");
					chestItems.add(healthPot);
				} else {
					Consumable statBooster = new Consumable(0, 2, size, "Stat Boost Drink");
					chestItems.add(statBooster);
				}
			}

			counter++;
		}
		this.isFilled = true;
	}

	/**
	 * Randomizes the amount of items that will be in the chest.
	 */
	public void quantityItems() {
		ratePercentage = rand.nextInt(100) + 1;

		if (ratePercentage <= 20) {
			itemsInChest = 1;
		} else if (ratePercentage <= 70) {
			itemsInChest = 2;
		} else if (ratePercentage <= 90) {
			itemsInChest = 3;
		} else if (ratePercentage <= 99) {
			itemsInChest = 4;
		} else {
			itemsInChest = 5;
		}
	}

	/**
	 * Randomizes the rarity of the weapon and armor equipment.
	 */
	public void rarityType() {
		ratePercentage = rand.nextInt(100) + 1;

		if (ratePercentage <= 40) {
			rarity = "Common";
		} else if (ratePercentage <= 70) {
			rarity = "Uncommon";
		} else if (ratePercentage <= 90) {
			rarity = "Rare";
		} else if (ratePercentage <= 99) {
			rarity = "Epic";
		} else {
			rarity = "Legendary";
		}
	}

	/**
	 * Randomizes the size of the consumable items.
	 */
	public void sizeType() {
		ratePercentage = rand.nextInt(100) + 1;

		if (ratePercentage <= 50) {
			size = "Small";
		} else if (ratePercentage <= 80) {
			size = "Medium";
		} else {
			size = "Large";
		}
	}


	/**
	 * @return if the chest is filled or not
	 */
	public boolean isFilled() {
		return this.isFilled;
	}
}
