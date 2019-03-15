import java.awt.Paint;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
	private Button rightButton, downButton, leftButton, upButton;
	private Text[][][][] map;
	private Text logText;
	private Room[][] testRoom;
	private Character testPlayer;
	private GridPane mapLayout, controlLayout, sceneLayout;
	private ScrollPane infoLayout;
	private Scene scene;
	private VBox menuLayout;
	private boolean[][] dungeonLayout;
	private boolean refresh = true;

	public Button getRightButton() {
		return rightButton;
	}

	public void setRightButton(Button rightButton) {
		this.rightButton = rightButton;
	}

	public Button getDownButton() {
		return downButton;
	}

	public void setDownButton(Button downButton) {
		this.downButton = downButton;
	}

	public Button getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(Button leftButton) {
		this.leftButton = leftButton;
	}

	public Button getUpButton() {
		return upButton;
	}

	public void setUpButton(Button upButton) {
		this.upButton = upButton;
	}

	public Text getLogText() {
		return logText;
	}

	public void setLogText(Text logText) {
		this.logText = logText;
	}

	public Character getTestPlayer() {
		return testPlayer;
	}

	public void setTestPlayer(Character testPlayer) {
		this.testPlayer = testPlayer;
	}

	public GridPane getMapLayout() {
		return mapLayout;
	}

	public void setMapLayout(GridPane mapLayout) {
		this.mapLayout = mapLayout;
	}

	public GridPane getControlLayout() {
		return controlLayout;
	}

	public void setControlLayout(GridPane controlLayout) {
		this.controlLayout = controlLayout;
	}

	public GridPane getSceneLayout() {
		return sceneLayout;
	}

	public void setSceneLayout(GridPane sceneLayout) {
		this.sceneLayout = sceneLayout;
	}

	public ScrollPane getInfoLayout() {
		return infoLayout;
	}

	public void setInfoLayout(ScrollPane infoLayout) {
		this.infoLayout = infoLayout;
	}

	public VBox getMenuLayout() {
		return menuLayout;
	}

	public void setMenuLayout(VBox menuLayout) {
		this.menuLayout = menuLayout;
	}

	public static void main(String[] Args) {
		launch(Args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Angband 2019");
		mapLayout = new GridPane();
		testPlayer = new Character('@', 0, 0, 1, 1, "name");
		testRoom = new Room[3][3];

		controlLayout = new GridPane();
		controlLayout.setAlignment(Pos.CENTER);

		sceneLayout = new GridPane();
		sceneLayout.setAlignment(Pos.CENTER);

		infoLayout = new ScrollPane();

		menuLayout = new VBox();

		logText = new Text("Action Log:");

		this.setMapLayout();

		this.setControlLayout();

		this.setInfoLayout();

		menuLayout.getChildren().add(new Text("this is another hello"));

		sceneLayout.setPadding(new Insets(10, 10, 10, 10));
		sceneLayout.setVgap(10);
		sceneLayout.setHgap(10);

		GridPane.setConstraints(mapLayout, 0, 0);
		GridPane.setConstraints(controlLayout, 1, 0);
		GridPane.setConstraints(infoLayout, 0, 1);
		GridPane.setConstraints(menuLayout, 1, 1);
		controlLayout.getChildren().addAll(upButton, downButton, leftButton, rightButton);

		sceneLayout.getChildren().addAll(mapLayout, controlLayout, infoLayout, menuLayout);

		scene = new Scene(sceneLayout, 500, 500, Color.BLACK);
		this.setKeyboardControls();

		arg0.setScene(scene);
		arg0.show();
	}

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

	private void setMapLayout() {
		mapLayout.setPadding(new Insets(10, 10, 10, 10));
		mapLayout.setHgap(5);

		dungeonLayout = new boolean[][] { { true, true, true }, { true, true, true }, { true, true, true } };

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
					if (dungeonLayout[i][i - 1])
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

	private void clearMapLayout() {
		for (int i = 0; i < testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].sizeX; i++) {
			for (int j = 0; j < testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].sizeY; j++) {
				map[Room.getCurrentRoomY()][Room.getCurrentRoomX()][i][j].setText(" ");
			}
		}
	}

	public void loadRoom(String message) {
		logText.setText(logText.getText() + message);
		if (testPlayer.getPosX() == 8) {
			Room.moveRight();
		}
		else if (testPlayer.getPosX() == 0) {
			Room.moveLeft();
		}
		else if (testPlayer.getPosY() == 0) {
			Room.moveUp();
		}
		else if (testPlayer.getPosY() == 8){
			Room.moveDown();
		}
		System.out.print(testPlayer.getPosX() + " " + testPlayer.getPosY() + "\n");
		System.out.print(Room.getCurrentRoomX() + " " + Room.getCurrentRoomY() + "\n");
		Room.setCurrentRoomX(1);
		Room.setCurrentRoomY(1);
		testPlayer.setPosX(1);
		testPlayer.setPosY(1);
		testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].setLayout();
		testRoom[Room.getCurrentRoomY()][Room.getCurrentRoomX()].getLayout()[testPlayer.getPosX()][testPlayer
			.getPosY()] = testPlayer.icon;
	}

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

	private void setInfoLayout() {
		infoLayout.setPrefViewportHeight(100);
		infoLayout.setPrefViewportWidth(150);
		infoLayout.setContent(logText);
	}
}
