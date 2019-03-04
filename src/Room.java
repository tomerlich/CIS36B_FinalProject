
public abstract class Room {
	private static int numRooms = 0;
	public int sizeX, sizeY, numDoors;
	public String[] doorPosition;
	public char[][] layout;
	
	public Room() {
		this(0, 0, 0, null);
	}
	
	public Room(int sizeX, int sizeY, int numDoors, String[] doorPosition) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.numDoors = numDoors;
		this.doorPosition = new String[this.numDoors];
		this.layout = new char[this.sizeX][this.sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (i == 0 || j == 0 || i == sizeX - 1 || j == sizeY - 1)
					this.layout[i][j] = '#';
				else
					this.layout[i][j] = '*';
			}
		}
	}
	
	public void updateNumRooms() {
		setNumRooms(getNumRooms() + 1);
	}

	public static int getNumRooms() {
		return numRooms;
	}

	public static void setNumRooms(int numRooms) {
		Room.numRooms = numRooms;
	}
	
}
