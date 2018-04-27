import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import java.util.concurrent.Executors;
import java.lang.InterruptedException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.stage.WindowEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;
import java.io.File;


public class Game extends Application{

	final Random rand = new Random();
	final int sceneX = 300;
	final int sceneY = 300;
	final int randomizeX = 220;
	final int randomizeY = 220;

	Text points = new Text("Score: 0");
	ExecutorService executor = Executors.newFixedThreadPool(1);
	int newX;
	int newY;
	int score;
	double seconds;
	double timer;
	DecimalFormat number = new DecimalFormat("#.0");
	Text time = new Text();
	@Override
	public void start(Stage primaryStage) throws Exception{
		/* Add your sound effect if you want
		String musicFile = "yourSoundName.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		*/
		Button btn = new Button();
		Button restart = new Button();
		Runnable task = () -> {
			seconds = 20.0;
			timer = 0.0;
			score = 0;
			while(seconds >= 0.00005){
			 	try{
					TimeUnit.MILLISECONDS.sleep(100);
				}catch(InterruptedException exe){
					executor.shutdownNow();
					break;
				}
				try{
					seconds -= 0.1;
					timer += 0.1;
					if(timer > 0.7){
						placeButton(btn);
						timer = 0.0;
					}
					time.setText(number.format(seconds));
			 	}catch(Exception exe){
			 		System.out.println("Excetion oppsto");
			 	}
		 	}
		 	time.setText("0.0");
		 	btn.setVisible(false);
		 	restart.setVisible(true);
		};

		points.setLayoutX(130);
		points.setLayoutY(20);
		time.setLayoutX(130);
		time.setLayoutY(280);
		restart.setLayoutX(130);
		restart.setLayoutY(160);
		restart.setVisible(false);
		restart.setText("Restart");
		Image image = new Image("file:pink-circle-md.png");
		ImageView iv = new ImageView(image);
		iv.setFitHeight(10);
		iv.setFitWidth(10);
		btn.setGraphic(iv);
		Pane root = new Pane();
		root.setStyle("-fx-background-color: #7ec0ee");
		btn.setLayoutX(150);
		btn.setLayoutY(150);
		root.getChildren().add(btn);
		root.getChildren().add(points);
		root.getChildren().add(time);
		root.getChildren().add(restart);
		primaryStage.setScene(new Scene(root, sceneX, sceneY));

		
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				mediaPlayer.stop();
				mediaPlayer.play();
				points.setText("Score: " + (++score));
				timer = 0.0;
				placeButton(btn);
			}
		});
		
		restart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				executor.execute(task);
				restart.setVisible(false);
				btn.setVisible(true);
				points.setText("Score: 0");
			}
		});
		
		executor.execute(task);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we){
				executor.shutdownNow();
			}
		});

	}

	public void placeButton(Button btn){

		newX = rand.nextInt(randomizeX + 60);
		newY = rand.nextInt(randomizeY + 60);
		btn.setLayoutX(newX);
		btn.setLayoutY(newY);
	}


}