import java.util.ArrayList;

public class Character extends Movement{
	public char icon;
	public String name;
	private int hp, mp, posX, posY, prevX, prevY;
	private ArrayList<Item> inventory;
	private Item weapon, helmet, curraiss, gloves, shoes;
	
	public Character() {
		this(' ', 0, 0, 0, 0, "no name");
	}
	
	public Character(char icon, int hp, int mp, int posX, int posY, String name) {
		super();
		this.icon = icon;
		this.hp = hp;
		this.mp = mp;
		this.posX = posX;
		this.prevX = posX;
		this.posY = posY;
		this.prevY = posY;
		this.name = name;
		this.inventory = new ArrayList<Item>(0);
		helmet = new Armor();
		curraiss = new Armor();
		shoes = new Armor();
	}

	public int getPrevX() {
		return prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public char getIcon() {
		return icon;
	}

	public void setIcon(char icon) {
		this.icon = icon;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		prevX = this.posX;
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		prevY = this.posY;
		this.posY = posY;
	}
	
	public void moveUP() {
		this.setPosX(this.getPosX());
		this.setPosY(this.getPosY() - 1);
	}
	
	public void moveDown() {
		this.setPosX(this.getPosX());
		this.setPosY(this.getPosY() + 1);
	}
	
	public void moveLeft() {
		this.setPosY(this.getPosY());
		this.setPosX(this.getPosX() - 1);
	}
	
	public void moveRight() {
		this.setPosY(this.getPosY());
		this.setPosX(this.getPosX() + 1);
	}

	public void combatEvent(Enemy e) {
		Weapon w = (Weapon) this.weapon;
		Armor helmet = (Armor) this.helmet;
		Armor chest = (Armor) this.curraiss;
		Armor shoes = (Armor) this.shoes;
		int defenseCalc = (int) (20 - (helmet.getDefense() + chest.getDefense() + shoes.getDefense()));
		if (w != null) {
			e.setHp((int) (e.getHp() - w.getAttack()));
		}
		if (!(defenseCalc < 0))
			this.setHp(this.hp - defenseCalc);
		if (this.hp < 0) {
			System.exit(0);
		}
		else if (e.getHp() < 0) {
			e.delete();
		}
	}

	public void addItems(Chest c) {
		for (int i = 0; i < c.getChestItems().size(); i++) {
			this.inventory.add(c.getChestItems().get(i));
		}
	}

	public void equip(int userChoice) {
		if (this.inventory.get(userChoice) instanceof Weapon) {
			this.weapon = this.inventory.get(userChoice);
		} else if (this.inventory.get(userChoice) instanceof Armor) {
			Armor a = (Armor) this.inventory.get(userChoice);
			if (a.getArmorType().equals("Helmet"))
				this.helmet = a;
			else if(a.getArmorType().equals("Leg Armor"))
				this.shoes = a;
			else if(a.getArmorType().equals("Chest Armor"))
				this.curraiss = a;
		}

	}
	
	public void useConsumable(int userChoice) {
		Consumable c = (Consumable) this.inventory.get(userChoice);
		if (c.consumableType.equalsIgnoreCase("Health Pot"))
			this.setHp(this.getHp() + c.use());
	}

}
