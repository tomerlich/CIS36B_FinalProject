import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Game extends Application{
	Button rightButton, downButton, leftButton, upButton;
	Text[][] map;
	Room testRoom;
	Character testPlayer;
	GridPane mapLayout, controlLayout, sceneLayout;
	VBox infoLayout, menuLayout;
	
	public static void main(String[] Args) {
		launch(Args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Angband 2019");
		mapLayout = new GridPane();
		controlLayout = new GridPane();
		sceneLayout = new GridPane();
		infoLayout = new VBox();
		menuLayout = new VBox();
		
		infoLayout.getChildren().add(new Circle(0,0, 50));
		menuLayout.getChildren().add(new Text("this is another hello"));
		
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
			map[testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveUP();
			this.setMap();
			});
		
		downButton = new Button("v");
		downButton.setOnAction(e -> {
			map[testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveDown();
			this.setMap();
			});
		
		leftButton = new Button("<");
		leftButton.setOnAction(e -> {
			map[testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveLeft();
			this.setMap();
			});
		
		rightButton = new Button(">");
		rightButton.setOnAction(e -> {
			map[testPlayer.getPosX()][testPlayer.getPosY()].setText(" ");
			testPlayer.moveRight();
			this.setMap();
			});
		
		GridPane.setConstraints(upButton, 1, 0);
		GridPane.setConstraints(downButton, 1, 1);
		GridPane.setConstraints(leftButton, 0, 1);
		GridPane.setConstraints(rightButton, 2, 1);
		
		testRoom = new Room(10,10,0,null);
		testRoom.placeObject(testPlayer);
		
		map = new Text[testRoom.sizeX][testRoom.sizeY];
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
			testRoom.placeObject(testPlayer);
			for(int i = 0; i < testRoom.sizeX; i++) {
				for (int j = 0; j < testRoom.sizeY; j++) {
					map[i][j] = new Text();
					map[i][j].setFont(new Font("Lucida Console", 18));
					map[i][j].setText(String.valueOf(testRoom.getLayout()[i][j]));
					GridPane.setConstraints(map[i][j], i, j);
					mapLayout.getChildren().add(map[i][j]);
				}
			}
		} catch (HitWallException e) {
			if (testPlayer.getPosX() == testPlayer.getPrevX()) {
				testPlayer.setPosY(testPlayer.getPrevY());
			}else
				testPlayer.setPosX(testPlayer.getPrevX());
			map[testPlayer.getPosX()][testPlayer.getPosY()].setText(String.valueOf(testPlayer.getIcon()));
		}
		
	}
}
