
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
		this.setLayout();
	}
	
	public void addDoors() {
		for (int i = 0; i < numDoors; i++) {
			this.layout[this.doorPosition[i] % 10][this.doorPosition[i] / 10] = ' ';
		}
	}
	
	public void setLayout() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (i == 0 || j == 0 || i == sizeX - 1 || j == sizeY - 1)
					this.layout[i][j] = '#';
				else
					this.layout[i][j] = '*';
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
}
