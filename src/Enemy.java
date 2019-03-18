import java.util.ArrayList;
import java.util.Random;

import hmmm.Character;

public class Enemy extends Movement implements EnemyBehavior{
	public char icon;
	public String name;
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
    	Character testPlayer = new Character();
    	Enemy testEnemy = new Enemy();
    	if (Math.abs(testPlayer.getPosX() - testEnemy.getPosX() + testPlayer.getPosY() - testEnemy.getPosY()) <= 5) {
    		testEnemy.pursuePlayer();
    	}
    	else {
    		testEnemy.randomMove();
    	}
        
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
    
}




