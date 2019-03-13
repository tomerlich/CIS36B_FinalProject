
public class Room {
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

	private static int numRooms = 0;

	private static int currentRoomX = 1, currentRoomY = 1;
	public int sizeX, sizeY, numDoors;
	public int[] doorPosition, adjRooms;
	public char[][] layout;
	
	public Room() {
		this(0, 0);
	}
	
	public Room(int sizeX, int sizeY) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.adjRooms = new int[]{-1, -1, -1, -1};
		this.layout = new char[this.sizeX][this.sizeY];
		this.setLayout();
	}
	
	public void addDoors() {
		for (int i = 0; i < numDoors; i++) {
			if(this.doorPosition[i] > 9)
			this.layout[this.doorPosition[i] / 10][this.doorPosition[i] % 10] = ' ';
			else
				this.layout[0][this.doorPosition[i]] = ' ';
		}
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
			for (int i = 0; i < numDoors; i++) {
				System.out.println("characterPositon " + c.getPosX() + c.getPosY());
				System.out.println("doorPositon " + doorPosition[i]);
			}
			if (this.layout[c.getPosX()][c.getPosY()] == '#')
				throw new HitWallException("you hit a wall");
			if (this.checkDoor(c.getPosX(), c.getPosY())) {
				throw new EnterNewRoomException("entered a new room");
			}
			this.layout[c.getPosX()][c.getPosY()] = c.icon;
		}
	}

	private boolean checkDoor(int posX, int posY) {
		int position = (posX * 10) + posY;
		for (int i = 0; i < this.numDoors; i++) {
			if (this.doorPosition[i] == position) {
				return true;
			}
		}
		return false;
	}
}
