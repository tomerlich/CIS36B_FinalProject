import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player{
	Media media;
	MediaPlayer mediaPlayer;
	
	public Player() {
		
	}
	
	public Player(String title) {
		media = new Media(title);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(5);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}
}
