
public class Game {	
	public static void main(String[] Args) {
		Room testRoom = new Room(5, 5, 0, null);
		Character testChar = new Character();
		
		testChar.setPosX(2);
		testChar.setPosY(2);
		testChar.setIcon('@');
		
		testRoom.getLayout()[testChar.getPosX()][testChar.getPosY()] = testChar.getIcon();
	
		testRoom.displayRoom();
	}
}
