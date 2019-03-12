import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Game extends Application{
	private Button rightButton, downButton, leftButton, upButton;
	private Text[][][] map;
	private Text logText;
	private Room[] testRoom;
	private Character testPlayer;
	private GridPane mapLayout, controlLayout, sceneLayout;
	private ScrollPane infoLayout;
	private VBox menuLayout;
	private int currentRoom;
	
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
		controlLayout = new GridPane();
		controlLayout.setAlignment(Pos.CENTER);
		sceneLayout = new GridPane();
		infoLayout = new ScrollPane();
		menuLayout = new VBox();
		logText = new Text("Action Log:");
		sceneLayout.setAlignment(Pos.CENTER);
		
		menuLayout.getChildren().add(new Text("this is another hello"));
		infoLayout.setPrefViewportHeight(100);
		infoLayout.setPrefViewportWidth(150);
		infoLayout.setContent(logText);
		
		controlLayout.setPadding(new Insets(10,10,10,10));
		controlLayout.setVgap(10);
		controlLayout.setHgap(10);
		
		sceneLayout.setPadding(new Insets(10,10,10,10));
		sceneLayout.setVgap(10);
		sceneLayout.setHgap(10);
		
		mapLayout.setPadding(new Insets(10,10,10,10));
		mapLayout.setVgap(10);
		mapLayout.setHgap(10);
		
		testPlayer = new Character('@', 0, 0, 1, 1, "name");
		
		upButton = new Button("^");
		upButton.setOnAction(e -> {
			map[currentRoom][testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveUP();
			this.setMap();
			});
		
		downButton = new Button("v");
		downButton.setOnAction(e -> {
			map[currentRoom][testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveDown();
			this.setMap();
			});
		
		leftButton = new Button("<");
		leftButton.setOnAction(e -> {
			map[currentRoom][testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveLeft();
			this.setMap();
			});
		
		rightButton = new Button(">");
		rightButton.setOnAction(e -> {
			map[currentRoom][testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveRight();
			this.setMap();
			});
		
		GridPane.setConstraints(upButton, 1, 0);
		GridPane.setConstraints(downButton, 1, 1);
		GridPane.setConstraints(leftButton, 0, 1);
		GridPane.setConstraints(rightButton, 2, 1);
		
		testRoom = new Room[5];
		for(int i = 0; i < testRoom.length; i++) {
			
			if(i != 0) {
				testRoom[i] = new Room(10, 10, 0, null);
				testRoom[i].placeObject(testPlayer);
			}else {
				testRoom[i] = new Room(5, 5, 1, new int[]{01});
				testRoom[i].placeObject(testPlayer);
			}
		}
		
		map = new Text[testRoom.length][][];
		for (int i = 0; i < map.length; i++) {
			map[i] = new Text[testRoom[i].sizeX][testRoom[i].sizeY];
		}
		this.setMap();
		
		GridPane.setConstraints(controlLayout, 1, 0);
		GridPane.setConstraints(infoLayout, 0, 1);
		GridPane.setConstraints(menuLayout, 1, 1);
		controlLayout.getChildren().addAll(upButton, downButton, leftButton, rightButton);
		
		sceneLayout.getChildren().addAll(mapLayout, controlLayout, infoLayout, menuLayout);
		
		Scene scene = new Scene(sceneLayout, 500, 500);
		arg0.setScene(scene);
		arg0.show();
	}
	
	public void setMap() {
		try {
			testRoom[currentRoom].placeObject(testPlayer);
			for(int i = 0; i < testRoom[currentRoom].sizeX; i++) {
				for (int j = 0; j < testRoom[currentRoom].sizeY; j++) {
					map[currentRoom][i][j] = new Text();
					map[currentRoom][i][j].setFont(new Font("Lucida Console", 18));
					map[currentRoom][i][j].setText(String.valueOf(testRoom[currentRoom].getLayout()[i][j]));
					GridPane.setConstraints(map[currentRoom][i][j], i, j);
					mapLayout.getChildren().add(map[currentRoom][i][j]);
				}
			}
		} catch (HitWallException e) {
			if (testPlayer.getPosX() == testPlayer.getPrevX()) {
				testPlayer.setPosY(testPlayer.getPrevY());
			}else
				testPlayer.setPosX(testPlayer.getPrevX());
			map[currentRoom][testPlayer.getPosX()][testPlayer.getPosY()].setText(String.valueOf(testPlayer.getIcon()));
			logText.setText(logText.getText() + "\nYou cant go through a wall!!");
			infoLayout.setContent(logText);
		} catch (EnterNewRoomException e) {
			testPlayer.setPosX(1);
			testPlayer.setPosY(1);
			logText.setText(logText.getText() + "\nYou're in the next Room");
			loadRoom();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadRoom() {
		for (int i = 0; i < testRoom[currentRoom].sizeX; i++) {
			for (int j = 0; j < testRoom[currentRoom].sizeY; j++) {
				map[currentRoom][i][j].setText(String.valueOf(' '));
			}
		}
		currentRoom++;
		this.setMap();
	}
}
