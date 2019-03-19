import java.util.ArrayList;

public class Character extends Movement{
	public char icon;
	public String name;
	private int hp, mp, posX, posY, prevX, prevY;
	private ArrayList<Item> inventory;
	private Item weapon;
	
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
		this.inventory = new ArrayList<Item>();
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
		e.setHp(e.getHp() - 5);
		this.setHp(this.hp - 5);
		if (this.hp < 0) {
			System.exit(0);
		}
		else if (e.getHp() < 0) {
			e.delete();
		}
	}

}
