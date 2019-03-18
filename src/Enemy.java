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

    public void move() {
    	//Character testPlayer = new Character();
    	//Enemy testEnemy = new Enemy();
    	//if (Math.abs(testPlayer.getPosX() - testEnemy.getPosX() + testPlayer.getPosY() - testEnemy.getPosY()) <= 5) {
    		//testEnemy.pursuePlayer();
    	//}
    	//else {
    		//testEnemy.randomMove();
    	//}
    	this.randomMove();
        
    }
    
    public void pursuePlayer() {
    	Character testPlayer = new Character();
    	Enemy testEnemy = new Enemy();
    	if (Math.abs(testPlayer.getPosX() - testEnemy.getPosX()) >= Math.abs(testPlayer.getPosY() - testEnemy.getPosY())) {
    		if (testPlayer.getPosX() > testEnemy.getPosX()) {
    			testEnemy.moveLeft();
    		}
    		else if (testPlayer.getPosX() < testEnemy.getPosX()) {
    			testEnemy.moveRight();
    		}
    	}
    	else if (Math.abs(testPlayer.getPosX() - testEnemy.getPosX()) < Math.abs(testPlayer.getPosY() - testEnemy.getPosY())) {
    		if (testPlayer.getPosY() > testEnemy.getPosY()) {
    			testEnemy.moveUP();	
    		}
    		else if (testPlayer.getPosY() < testEnemy.getPosY()) {
    			testEnemy.moveDown();	
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
    
}




