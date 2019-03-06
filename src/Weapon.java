
public class Weapon implements Item, Comparable<Weapon>{
	private int durability, attack, defend;

	public Weapon(int durability, int attack, int defend) {
		super();
		this.durability = durability;
		this.attack = attack;
		this.defend = defend;
	}

	public Weapon() {
		this(0, 0, 0);
	}

	@Override
	public int compareTo(Weapon arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
