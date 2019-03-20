import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * 
 * @author tomer
 *
 */
public class Game extends Application {
	private Button rightButton, downButton, leftButton, upButton;
	private Text[][][][] map;
	private Text logText;
	private Room[][] testRoom;
	private Character testPlayer;
	private Enemy[][][] testEnemy;
	private Chest[][][] testChest;
	private GridPane mapLayout, controlLayout, sceneLayout, menuControlsLayout;
	private ScrollPane infoLayout;
	private Scene scene;
	private VBox menuLayout, menuInfoLayout;
	private static boolean[][] dungeonLayout;
	private static Random r;

	public static void main(String[] Args) {
		r = new Random();
		r.setSeed(System.currentTimeMillis());
		int layoutIndex = r.nextInt(6);

		switch (layoutIndex) {
		case 0:
			dungeonLayout = new boolean[][] { { true, false, true, true, true }, { true, false, true, false, true },
					{ true, false, true, false, true }, { true, false, true, false, true },
					{ true, true, true, false, true } };
			break;
		case 1:
			dungeonLayout = new boolean[][] { { true, true, true, true, true }, { true, false, true, false, true },
					{ true, false, true, false, true }, { true, false, true, false, true },
					{ true, true, true, true, true } };
			break;
		case 2:
			dungeonLayout = new boolean[][] { { true, true, true, true, true }, { false, false, false, false, true },
					{ true, true, true, true, true }, { true, false, false, false, false },
					{ true, true, true, true, true } };
			break;
		case 3:
			dungeonLayout = new boolean[][] { { true, true, true, true, true }, { true, false, false, false, true },
					{ true, true, true, true, true }, { true, false, false, false, true },
					{ true, true, true, true, true } };
			break;
		case 4:
			dungeonLayout = new boolean[][] { { true, true, true, true, true }, { false, false, true, false, false },
					{ true, true, true, true, true }, { false, false, true, false, true },
					{ true, true, true, false, true } };
			break;
		case 5:
			dungeonLayout = new boolean[][] { { true, false, false, false, false }, { true, false, true, true, false },
					{ true, false, false, true, false }, { true, false, false, true, false },
					{ true, true, true, true, false } };
			break;
		}

		File file = new File("Mozart - Lacrimosa.mp3");
		Player player = new Player(file.toURI().toString());
		player.mediaPlayer.play();
		launch(Args);
	}

	@Override
	/**
	 * Override the start method from the application class is called by the launch
	 * method in main also sets all of our scene elements
	 * 
	 * @param Stage the window provided by the application class
	 */
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Angband 2019");
		mapLayout = new GridPane();
		testPlayer = new Character('@', 500, 100, 4, 4, "name");
		testEnemy = new Enemy[5][5][1];
		testChest = new Chest[5][5][1];
		for (int i = 0; i < testEnemy.length; i++) {
			for (int j = 0; j < testEnemy[i].length; j++) {
				for (int g = 0; g < testEnemy[i][j].length; g++) {
					testEnemy[i][j][g] = Enemy.generateEnemies();
					testEnemy[i][j][g].setPosX(5);
					testEnemy[i][j][g].setPosY(g + 2);
					testChest[i][j][g] = new Chest();
					int chestSeed = r.nextInt(10000);
					if ((chestSeed % 2) == 0) {
						testChest[i][j][g].fillChest();
					}
				}
			}
		}
		testRoom = new Room[5][5];

		menuLayout = new VBox();
		menuControlsLayout = new GridPane();
		menuInfoLayout = new VBox();

		controlLayout = new GridPane();
		controlLayout.setAlignment(Pos.CENTER);

		sceneLayout = new GridPane();
		sceneLayout.setAlignment(Pos.CENTER);

		infoLayout = new ScrollPane();

		logText = new Text("Action Log:");

		this.setMapLayout();

		this.setControlLayout();

		this.setInfoLayout();

		this.setMenuInfoLayout();

		sceneLayout.setPadding(new Insets(10, 10, 10, 10));
		sceneLayout.setVgap(10);
		sceneLayout.setHgap(10);

		GridPane.setConstraints(mapLayout, 0, 0);
		GridPane.setConstraints(controlLayout, 1, 0);
		GridPane.setConstraints(infoLayout, 0, 1);
		GridPane.setConstraints(menuLayout, 1, 1);
		controlLayout.getChildren().addAll(upButton, downButton, leftButton, rightButton);

		sceneLayout.getChildren().addAll(mapLayout, controlLayout, infoLayout, menuLayout);

		scene = new Scene(sceneLayout, 900, 900, Color.BLACK);
		this.setKeyboardControls();

		arg0.setScene(scene);
		arg0.show();
	}

	/**
	 * sets the layout for our menu pane in the bottom right corner
	 */
	private void setMenuInfoLayout() {
		Button openBestiary = new Button("Open Bestiary");
		Button openInventory = new Button("Open Inventory");
		Button equipItem = new Button("Equip Item");
		Button useItem = new Button("Use Item");
		Text menuInfoText = new Text();
		TextField userChoiceText = new TextField("");

		userChoiceText.setOnMouseClicked(e -> userChoiceText.clear());
		
		openBestiary.setOnAction(e -> {
			for (Enemy l : testEnemy[Room.getCurrentRoomY()][Room.getCurrentRoomY()]) {
				menuInfoText.setText(l.toString() + "\n");
			}
		});

		openInventory.setOnAction(e -> {
			if (testPlayer.getInventory().size() != 0) {
				for (int i = 0; i < testPlayer.getInventory().size(); i++) {
					if (i == 0)
						menuInfoText.setText(testPlayer.getInventory().get(i).toString() + "\n");
					else
						menuInfoText
								.setText(menuInfoText.getText() + testPlayer.getInventory().get(i).toString() + "\n");
					menuInfoText.setText(menuInfoText.getText() + "Item index: " + (i + 1) + "\n");
				}
			} else
				menuInfoText.setText("You dont have anything in your inventory try opening a chest");
		});

		equipItem.setOnAction(e -> {
			if ((testPlayer.getInventory().size() != 0) && !(userChoiceText.getText().equals(""))){
				logText.setText(logText.getText() + "\nYou equipped an item");
				testPlayer.equip(Integer.parseInt(userChoiceText.getText()) - 1);
			} else if(testPlayer.getInventory().size() == 0)
				menuInfoText.setText("You dont have anything in your inventory try opening a chest");
			else 
				menuInfoText.setText("Enter an item index first\nOpen inventory to view item indeces");
		});
		
		useItem.setOnAction(e -> {
			if ((testPlayer.getInventory().size() != 0) && !(userChoiceText.getText().equals(""))){
				logText.setText(logText.getText() + "\nYou used an item");
				testPlayer.equip(Integer.parseInt(userChoiceText.getText()) - 1);
			} else if(testPlayer.getInventory().size() == 0)
				menuInfoText.setText("You dont have anything in your inventory try opening a chest");
			else 
				menuInfoText.setText("Enter an item index first\nOpen inventory to view item indeces");
		});
		
		GridPane.setConstraints(openBestiary, 0, 0);
		GridPane.setConstraints(openInventory, 1, 0);
		GridPane.setConstraints(equipItem, 2, 0);
		GridPane.setConstraints(useItem, 3, 0);
		menuInfoLayout.setMaxHeight(100);
		menuInfoLayout.setMinHeight(100);
		menuControlsLayout.getChildren().addAll(openBestiary, openInventory, equipItem, useItem);
		menuInfoLayout.getChildren().addAll(userChoiceText, menuInfoText);
		menuLayout.getChildren().addAll(menuControlsLayout, menuInfoLayout);
	}
	

	/**
	 * creates the keyboard controls for the game also listens for key presses
	 */
	private void setKeyboardControls() {
		scene.setOnKeyPressed(ke -> {
			KeyCode keyCode = ke.getCode();
			if (keyCode.equals(KeyCode.W)) {
				map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
						.setText(" ");
				testPlayer.moveUP();
				this.clearMapLayout();
				this.setMapLayout();
			}
			if (keyCode.equals(KeyCode.A)) {
				map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
						.setText(" ");
				testPlayer.moveLeft();
				this.clearMapLayout();
				this.setMapLayout();
			}
			if (keyCode.equals(KeyCode.S)) {
				map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
						.setText(" ");
				testPlayer.moveDown();
				this.clearMapLayout();
				this.setMapLayout();
			}
			if (keyCode.equals(KeyCode.D)) {
				map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
						.setText(" ");
				testPlayer.moveRight();
				this.clearMapLayout();
				this.setMapLayout();
			}
		});

	}

	/**
	 * render the map and the current room
	 */
	private void setMapLayout() {
		mapLayout.setPadding(new Insets(10, 10, 10, 10));
		mapLayout.setHgap(5);

		for (int i = 0; i < testRoom.length; i++) {
			for (int j = 0; j < testRoom[i].length; j++) {
				if (dungeonLayout[i][j] == true) {
					testRoom[i][j] = new Room(9, 9);
					testRoom[i][j].setLayout();
				} else
					testRoom[i][j] = new Room();
			}
		}

		this.addDoorsToRooms();
		testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].setLayout();
		testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()]
				.placeChest(testChest[Room.getCurrentRoomY()][Room.getCurrentRoomX()][0]);
		this.updateEnemies();

		try {
			testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].placeObject(testPlayer);
		} catch (HitWallException e) {
			if (testPlayer.getPosX() == testPlayer.getPrevX()) {
				testPlayer.setPosY(testPlayer.getPrevY());
			} else
				testPlayer.setPosX(testPlayer.getPrevX());
			testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[testPlayer.getPosX()][testPlayer
					.getPosY()] = testPlayer.icon;
			logText.setText(logText.getText() + e.getMessage());
		} catch (ChestException e) {
			if (testPlayer.getPosX() == testPlayer.getPrevX()) {
				testPlayer.setPosY(testPlayer.getPrevY());
			} else
				testPlayer.setPosX(testPlayer.getPrevX());
			testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[testPlayer.getPosX()][testPlayer
					.getPosY()] = testPlayer.icon;
			testPlayer.addItems(testChest[Room.getCurrentRoomY()][Room.getCurrentRoomX()][0]);
			testChest[Room.getCurrentRoomY()][Room.getCurrentRoomX()][0].openChest();
			logText.setText(logText.getText() + e.getMessage());
		} catch (EnterNewRoomException e) {
			this.loadRoom(e.getMessage());
		} catch (Exception e1) {
		}

		map = new Text[testRoom.length][][][];
		for (int i = 0; i < map.length; i++) {
			map[i] = new Text[testRoom[i].length][][];
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Text[testRoom[i][j].sizeX][testRoom[i][j].sizeY];
				for (int g = 0; g < map[i][j].length; g++) {
					for (int h = 0; h < map[i][j][g].length; h++) {
						map[i][j][g][h] = new Text(String
								.valueOf(testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[g][h]));
						map[i][j][g][h].setFont(new Font("Lucida Console", 18));

					}
				}
			}
		}

		for (int i = 0; i < map[Room.getCurrentRoomY()][Room.getCurrentRoomX()].length; i++) {
			for (int j = 0; j < map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][i].length; j++) {
				GridPane.setConstraints(map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][i][j], i, j);
				mapLayout.getChildren().add(map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][i][j]);
			}
		}
	}

	private void updateEnemies() {
		for (Enemy l : testEnemy[Room.getCurrentRoomY()][Room.getCurrentRoomX()]) {
			if (l.getHp() > 0) {
				try {
					l.move(testPlayer);
					if (l.getPosX() == testPlayer.getPosX() && l.getPosY() == testPlayer.getPosY())
						throw new CombatException("\nYou got in a fight with " + l.getName());
					testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].placeObject(l);
				} catch (CombatException e) {
					testPlayer.combatEvent(l);
					if (l.getPosX() == l.getPrevX()) {
						l.setPosY(l.getPrevY());
					} else
						l.setPosX(l.getPrevX());
					testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[l.getPosX()][l
							.getPosY()] = l.icon;
					logText.setText(logText.getText() + e.getMessage());
				} catch (Exception e) {
					if (l.getPosX() == l.getPrevX()) {
						l.setPosY(l.getPrevY());
					} else
						l.setPosX(l.getPrevX());
					testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[l.getPosX()][l
							.getPosY()] = l.icon;
				}
			}
		}
	}

	/**
	 * add doors to room according to our dungeon layout array
	 */
	private void addDoorsToRooms() {
		for (int j = 0; j < dungeonLayout.length; j++) {
			for (int i = 0; i < dungeonLayout[j].length; i++) {
				if (i == 0) {
					if (dungeonLayout[i + 1][j])
						testRoom[i][j].addDoorBottom();
				} else if (i == (dungeonLayout.length - 1)) {
					if (dungeonLayout[i - 1][j])
						testRoom[i][j].addDoorTop();
					;
				} else {
					if (dungeonLayout[i + 1][j])
						testRoom[i][j].addDoorBottom();
					if (dungeonLayout[i - 1][j])
						testRoom[i][j].addDoorTop();
					;
				}
			}
		}
		for (int j = 0; j < dungeonLayout.length; j++) {
			for (int i = 0; i < dungeonLayout[j].length; i++) {
				if (i == 0) {
					if (dungeonLayout[j][i + 1])
						testRoom[j][i].addDoorRight();
				} else if (i == (dungeonLayout.length - 1)) {
					if (dungeonLayout[j][i - 1])
						testRoom[j][i].addDoorLeft();
					;
				} else {
					if (dungeonLayout[j][i + 1])
						testRoom[j][i].addDoorRight();
					if (dungeonLayout[j][i - 1])
						testRoom[j][i].addDoorLeft();
					;
				}
			}
		}
	}

	/**
	 * reset the map
	 */
	private void clearMapLayout() {
		for (int i = 0; i < testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].sizeX; i++) {
			for (int j = 0; j < testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].sizeY; j++) {
				map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][i][j].setText(" ");
			}
		}
	}

	/**
	 * load the room from our room array
	 * 
	 * @param message message for the program log
	 */
	public void loadRoom(String message) {
		logText.setText(logText.getText() + message);
		if (testPlayer.getPosX() == testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].sizeX - 1) {
			Room.moveRight();
			testPlayer.setPosX(1);
			testPlayer.setPosY(4);
		} else if (testPlayer.getPosX() == 0) {
			Room.moveLeft();
			testPlayer.setPosX(7);
			testPlayer.setPosY(4);
		} else if (testPlayer.getPosY() == 0) {
			Room.moveUp();
			testPlayer.setPosX(4);
			testPlayer.setPosY(7);
		} else if (testPlayer.getPosY() == testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].sizeX - 1) {
			Room.moveDown();
			testPlayer.setPosX(4);
			testPlayer.setPosY(1);
		}

		testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].setLayout();
		testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[testPlayer.getPosX()][testPlayer
				.getPosY()] = testPlayer.icon;
		for (Enemy e : testEnemy[Room.getCurrentRoomY()][Room.getCurrentRoomX()]) {
			if (e.getHp() > 0) {
				testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[e.getPosX()][e.getPosY()] = e.icon;
			}	
		}
		for (Chest e : testChest[Room.getCurrentRoomY()][Room.getCurrentRoomX()]) {
			if (e.isFilled()) {
				testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[7][7] = 'C';
			}
		}
	}

	/**
	 * initialize the on screen controls for the game
	 */
	private void setControlLayout() {
		controlLayout.setPadding(new Insets(10, 10, 10, 10));
		controlLayout.setVgap(10);
		controlLayout.setHgap(10);

		upButton = new Button("^");
		upButton.setOnAction(e -> {
			map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
					.setText(" ");
			testPlayer.moveUP();
			this.clearMapLayout();
			this.setMapLayout();
		});

		downButton = new Button("v");
		downButton.setOnAction(e -> {
			map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
					.setText(" ");
			testPlayer.moveDown();
			this.clearMapLayout();
			this.setMapLayout();
		});

		leftButton = new Button("<");
		leftButton.setOnAction(e -> {
			map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
					.setText(" ");
			testPlayer.moveLeft();
			this.clearMapLayout();
			this.setMapLayout();
		});

		rightButton = new Button(">");
		rightButton.setOnAction(e -> {
			map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][testPlayer.getPosX()][testPlayer.getPosY()]
					.setText(" ");
			testPlayer.moveRight();
			this.clearMapLayout();
			this.setMapLayout();
		});
		upButton.setFont(new Font("Lucida Console", 18));
		downButton.setFont(new Font("Lucida Console", 18));
		leftButton.setFont(new Font("Lucida Console", 18));
		rightButton.setFont(new Font("Lucida Console", 18));

		GridPane.setConstraints(upButton, 1, 0);
		GridPane.setConstraints(downButton, 1, 1);
		GridPane.setConstraints(leftButton, 0, 1);
		GridPane.setConstraints(rightButton, 2, 1);
	}

	/**
	 * set the game log.
	 */
	private void setInfoLayout() {
		infoLayout.setPrefViewportHeight(500);
		infoLayout.setPrefViewportWidth(200);
		infoLayout.setContent(logText);
	}
}
