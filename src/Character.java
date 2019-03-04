
public abstract class Character {
	public char icon;
	public int hp, mp, posX, posY;
	
	public Character() {
		this(' ', 0, 0, 0, 0);
	}
	
	public Character(char icon, int hp, int mp, int posX, int posY) {
		super();
		this.icon = icon;
		this.hp = hp;
		this.mp = mp;
		this.posX = posX;
		this.posY = posY;
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
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void moveUP() {
		this.setPosY(this.getPosY() - 1);
	}
	
	public void moveDown() {
		this.setPosY(this.getPosY() + 1);
	}
	
	public void moveLeft() {
		this.setPosX(this.getPosX() - 1);
	}
	
	public void moveRight() {
		this.setPosX(this.getPosX() + 1);
	}
}
