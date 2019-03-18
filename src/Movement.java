import java.util.ArrayList;

public class Movement {

	public char icon;

	public String name;

	private int hp, mp, posX, posY, prevX, prevY;

	private ArrayList<Item> inventory;

	public Movement() {

		this(' ', 0, 0, 0, 0, "no name");

	}

	public Movement(char icon, int hp, int mp, int posX, int posY, String name) {
		//super();
		this.icon = icon;
		this.hp = hp;
		this.mp = mp;
		this.posX = posX;
		this.prevX = posX;
		this.posY = posY;
		this.prevY = posY;
		this.name = name;
	}

	public int getPrevX() {
		return prevX;
	}

	public int getPrevY() {
		return prevY;
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

	public int calculatePos() {
		return (this.posX * this.posY) + (this.posY - 1);
	}

}
