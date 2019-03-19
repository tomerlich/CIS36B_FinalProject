import java.util.ArrayList;
import java.util.Random;

//import hmmm.Character;

public class Enemy extends Movement implements EnemyBehavior{
	private int prevX, prevY;
	public Enemy() {
		this(' ', 0, 0, 0, 0, "no name");
	}
	
	public Enemy(char icon, int hp, int mp, int posX, int posY, String name) {
		super(icon, hp, mp, posX, posY, name);
		this.prevX = posX;
		this.prevY = posY;
	}
    
    public void randomMove() { 
    	Random r = new Random ();
    	r.setSeed(System.nanoTime());
    	int i = r.nextInt(5);
    	if (i == 0) {
    		moveDown();
    	}
    	else if (i == 1) {
    		moveUP();
    	}
    	else if (i == 2) {
    		moveLeft();
    	}
    	else if (i == 3) {
    		moveRight();
    	}	
    	else {
    	}
    }

    public void move(Character testPlayer) {
    	if (Math.abs(testPlayer.getPosX() - this.getPosX() + testPlayer.getPosY() - this.getPosY()) <= 1) {
    		this.pursuePlayer(testPlayer);
    	}
    	else {
    		this.randomMove();
    	}
    }
    
    public void pursuePlayer(Character testPlayer) {
    	if (Math.abs(testPlayer.getPosX() - this.getPosX()) >= Math.abs(testPlayer.getPosY() - this.getPosY())) {
    		if (testPlayer.getPosX() > this.getPosX()) {
    			this.moveRight();
    		}
    		else if (testPlayer.getPosX() < this.getPosX()) {
    			this.moveLeft();
    		}
    	}
    	else if (Math.abs(testPlayer.getPosX() - this.getPosX()) < Math.abs(testPlayer.getPosY() - this.getPosY())) {
    		if (testPlayer.getPosY() > this.getPosY()) {
    			this.moveDown();	
    		}
    		else if (testPlayer.getPosY() < this.getPosY()) {
    			this.moveUP();	
    		}
    	}
     }
    
    public static Enemy generateEnemies() {
    	Random r = new Random();
    	r.setSeed(System.nanoTime());
    	int enemyNum = 0;
    		enemyNum = r.nextInt(5);
    		switch (enemyNum){
    		case 0:
    			return new Enemy('t', 30, 0, 0, 0, "Imp");
    		case 1:
    			return new Enemy('T', 30, 0, 0, 0, "Troll");
    		case 2:
    			return new Enemy('f', 30, 0, 0, 0, "Undead");
    		case 3:
    			return new Enemy('s', 30, 0, 0, 0, "Small bones");
    		case 4:
    			return new Enemy('$', 30, 0, 0, 0, "Giant bones");
    		default:
    			return new Enemy('Z', 15, 0, r.nextInt(6), r.nextInt(6), "Zombie");
    		}
    	
    }

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pursuePlayer() {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void delete() {
		this.icon = '#';
		this.setPosX(0);
		this.setPosY(0);
	}
    
}




