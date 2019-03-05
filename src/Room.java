
public class Room {
	private static int numRooms = 0;
	public int sizeX, sizeY, numDoors;
	public int[] doorPosition;
	public char[][] layout;
	
	public Room() {
		this(0, 0, 0, null);
	}
	
	public Room(int sizeX, int sizeY, int numDoors, int[] doorPosition) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.numDoors = numDoors;
		this.doorPosition = new int[this.numDoors];
		for (int i = 0; i < numDoors; i++) {
			this.doorPosition[i] = doorPosition[i];
		}
		this.layout = new char[this.sizeX][this.sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (i == 0 || j == 0 || i == sizeX - 1 || j == sizeY - 1)
					this.layout[i][j] = '#';
				else
					this.layout[i][j] = '*';
			}
		}
		for (int i = 0; i < numDoors; i++) {
			this.layout[this.doorPosition[i] / 10][this.doorPosition[i] % 10] = ' ';
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
	
	
	public static void main(String[] Args) {
		int[] doors = {01, 04, 11};
		Room test = new Room(5, 5, 2, doors);
		for (int i = 0; i < test.sizeX; i++) {
			for(int j = 0; j < test.sizeY; j++) {
				System.out.print(test.layout[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}
