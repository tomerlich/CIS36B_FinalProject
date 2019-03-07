import javafx.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Game extends Application{

	Button button;
	TextArea map;
	Room testRoom;
	Character testPlayer;
	
	public static void main(String[] Args) {
		launch(Args);
		

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.setTitle("This is a title");
		
		testPlayer = new Character();
		testPlayer.setIcon('@');
		testPlayer.setPosX(1);
		testPlayer.setPosY(1);
		
		button = new Button("Click me");
		button.setOnAction(e -> this.updateMap());
		
		testRoom = new Room(5,6,0,null);
		
		map = new TextArea();
		map.setEditable(false);
		map.setFont(new Font("Lucida Console", 18));
		for(int i = 0; i < testRoom.sizeX; i++) {
			for (int j = 0; j < testRoom.sizeY; j++) {
				map.appendText(String.valueOf(testRoom.getLayout()[i][j]));
			}
			map.appendText("\n");
		}
		
		GridPane.setConstraints(map, 1, 0);
		GridPane.setConstraints(button, 1, 1);
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10,10,10,10));
		layout.setVgap(10);
		layout.setHgap(10);
		
		layout.getChildren().addAll(map, button);
		
		Scene scene = new Scene(layout, 300, 300);
		arg0.setScene(scene);
		arg0.show();
	}
	
	
	public void updateMap() {
		testPlayer.moveRight();
		map.insertText(testPlayer.calculatePos(), String.valueOf(testPlayer.getIcon()));
		map.deleteText(testPlayer.calculatePos() - 1, testPlayer.calculatePos());
	}
}
