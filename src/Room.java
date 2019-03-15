import java.util.ArrayList;

public class Room {

	private static int numRooms = 0;

	private static int currentRoomX = 0, currentRoomY = 0;
	public int sizeX, sizeY, numDoors;
	public ArrayList<Integer> doorPosition;
	public char[][] layout;
	
	public static int getCurrentRoomX() {
		return currentRoomX;
	}

	public static void setCurrentRoomX(int currentRoomX) {
		Room.currentRoomX = currentRoomX;
	}

	public static int getCurrentRoomY() {
		return currentRoomY;
	}

	public static void setCurrentRoomY(int currentRoomY) {
		Room.currentRoomY = currentRoomY;
	}
	
	public static void moveLeft() {
		Room.currentRoomY--;
	}
	public static void moveRight() {
		Room.currentRoomY++;
	}
	public static void moveUp() {
		Room.currentRoomX--;
	}
	public static void moveDown() {
		Room.currentRoomX++;
	}
	
	public Room() {
		this(0, 0);
	}
	
	public Room(int sizeX, int sizeY) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.doorPosition = new ArrayList<Integer>();
		this.layout = new char[this.sizeX][this.sizeY];
	}
	
	public void addDoors() {
		for (int i = 0; i < doorPosition.size(); i++) {
			this.layout[doorPosition.get(i) / 10][doorPosition.get(i) % 10] = ' ';
		}
	}
	
	public void addDoorLeft() {
		this.numDoors++;
		this.doorPosition.add(04);
	}
	
	public void addDoorRight() {
		this.numDoors++;
		this.doorPosition.add(84);
	}
	
	public void addDoorTop() {
		this.numDoors++;
		this.doorPosition.add(40);
	}
	
	public void addDoorBottom() {
		this.numDoors++;
		this.doorPosition.add(48);
	}
	
	public void setLayout() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (i == 0 || j == 0 || i == sizeX - 1 || j == sizeY - 1)
					this.layout[i][j] = '#';
				else
					this.layout[i][j] = ' ';
			}
		}
		this.addDoors();
	}
	
	public char[][] getLayout() {
		return this.layout;
	}
	
	public void displayRoom() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				System.out.print(this.layout[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void updateNumRooms() {
		setNumRooms(getNumRooms() + 1);
	}

	public static int getNumRooms() {
		return numRooms;
	}

	public static void setNumRooms(int numRooms) {
		Room.numRooms = numRooms;
	}
	
	public void placeObject(Object o) throws Exception{
		this.setLayout();
		if (o instanceof Character) {
			Character c = (Character) o;
			if (this.layout[c.getPosX()][c.getPosY()] == '#') {
				throw new HitWallException("\nYou hit a wall");
			}
			if (this.checkDoor(c.getPosX(), c.getPosY())) {
				throw new EnterNewRoomException("\nEntered a new room");
			}
			this.layout[c.getPosX()][c.getPosY()] = c.icon;
		}
	}

	private boolean checkDoor(int posX, int posY) {
		int position = (posX * 10) + posY;
		for (int i = 0; i < this.numDoors; i++) {
			if (this.doorPosition.get(i) == position) {
				return true;
			}
		}
		return false;
	}
}
