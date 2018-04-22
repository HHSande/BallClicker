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

public class Game extends Application{

	int newX;
	int newY;
	int score = 0;
	final Random rand = new Random();
	final int sceneX = 300;
	final int sceneY = 300;
	final int randomizeX = 220;
	final int randomizeY = 220;
	@Override
	public void start(Stage primaryStage) throws Exception{

		Button btn = new Button();
		Pane root = new Pane();
		btn.setLayoutX(150);
		btn.setLayoutY(150);
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, sceneX, sceneY));

		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				score++;
				System.out.println(score);
				placeButton(btn);
			}
		});

		primaryStage.show();

	}

	public void placeButton(Button btn){

		newX = rand.nextInt(randomizeX + 60);
		newY = rand.nextInt(randomizeY + 60);
		btn.setLayoutX(newX);
		btn.setLayoutY(newY);
		System.out.println("Shuffeled");
	}


}