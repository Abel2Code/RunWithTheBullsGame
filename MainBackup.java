package application;
	
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class MainBackup extends Application{
	private Boolean gameStarted = false;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			MazeGUIPane maze = new MazeGUIPane();
			BorderPane bp = new BorderPane();
			
			HBox title = new HBox();
			HBox center = new HBox();
			HBox bottom = new HBox();
			
			bp.getStyleClass().add("grid");
			Label titleName = new Label("Map of Pamplona");
			titleName.setTextFill(Color.CHARTREUSE);
			title.getChildren().add(titleName);
			title.setAlignment(Pos.TOP_CENTER);
			center.getChildren().add(maze);
			bottom.getStyleClass().add("title");
			
			Button run = initRunButton();
		
			Button reset = new Button("Reset");
			reset.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
				@Override
				public void handle(Event event){
					gameStarted = false;
					maze.startGame();
				}
			});
			bottom.getChildren().addAll(run, new Label("        "), reset);
			bottom.setAlignment(Pos.BOTTOM_CENTER);
			title.getStyleClass().add("title");
			center.getStyleClass().add("center");
			bp.setTop(title);
			bp.setCenter(center);
			bp.setBottom(bottom);
			Scene scene = new Scene(bp,650,650);
			//Scene scene = new Scene(maze,500,600);
			scene.getStylesheets().add("application/application.css");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			maze.startGame();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Button initRunButton(){
		Button run = new Button("Run");
		final EventHandler keyEventHandler = new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(gameStarted == true){
					if(event.getCode() == KeyCode.DOWN){
						System.out.println("Down");
					} else if(event.getCode() == KeyCode.RIGHT){
						System.out.println("Right");
					} else if(event.getCode() == KeyCode.UP){
						System.out.println("Up");
					} else if(event.getCode() == KeyCode.LEFT){
						System.out.println("Left");
					}
				}
			}
		};
		
		run.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			public void handle(Event event) {
				gameStarted = true;
				run.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);
				//Initialize bulls here
			}
		});
		
		return run;
	}
}