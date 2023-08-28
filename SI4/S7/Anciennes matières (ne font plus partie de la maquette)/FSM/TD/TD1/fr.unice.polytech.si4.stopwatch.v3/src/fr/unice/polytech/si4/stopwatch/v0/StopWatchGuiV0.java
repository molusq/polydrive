package fr.unice.polytech.si4.stopwatch.v0;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stopwatch_v3.TimerService;
import stopwatch_v3.defaultsm.DefaultSMStatemachine;
import stopwatch_v3.defaultsm.IDefaultSMStatemachine.SCInterface;
/**
 * A simple StopWatch class in JavaFx to be used for state chart learning.
 * @author jdeanton
 *
 */
public class StopWatchGuiV0 extends Application{
	private Text timeValue 	   = null;
	private Button leftButton;
	private Button rightButton;
	
	private int mins = 0, secs = 0, millis = 0;
	private DefaultSMStatemachine sm = new DefaultSMStatemachine();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws NoSuchMethodException, SecurityException {
		
		sm = new DefaultSMStatemachine();
		sm.setTimer(new TimerService());
	    sm.init();
	    sm.enter();
	    
	    
	    
		timeValue = new Text("00:00:000");
		
		

		leftButton = new Button("Start");
		rightButton = new Button("Pause");
		
		SCInterface.updateDisplay.subscribe(event -> updateText());

		SCInterface.startPlaying.subscribe(event -> {
			leftButton.setText("Stop");
		});
		
		SCInterface.startStop.subscribe(event -> 
			leftButton.setText("Reset"));
		 
		SCInterface.reset.subscribe(event -> {
			mins = 0;
			secs = 0;
			millis = 0;
			timeValue.setText("00:00:000");
			leftButton.setText("Start");
			rightButton.setText("Pause");});
		 
		 SCInterface.startPause.subscribe(event -> {
			rightButton.setText("Resume");
		 });
		 
		 SCInterface.resumePlaying.subscribe(event -> {
			rightButton.setText("Pause");
		 });
		 
		leftButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sm.getSCInterface().raiseLeftButton();
            }
        });
		
		rightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sm.getSCInterface().raiseRightButton();
            }
		});
		
		
		setupUI(stage, leftButton, rightButton);
	}
	
	public void updateText() {
		millis+=10;
		if(millis >= 1000) {
			secs++;
			millis = 1000 - millis;
		}
		if(secs >= 60) {
			mins++;
			secs = 60 - secs;
		}
	
		timeValue.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
				+ (((secs/10) == 0) ? "0" : "") + secs + ":" 
				+ (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis);
	
	}


	private void setupUI(Stage stage, Button leftButton, Button rightButton) {
		Scene scene = null;
		VBox vBox = null;
		HBox hBox = null;
		hBox = new HBox(300);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(leftButton, rightButton);
		vBox = new VBox(30);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(timeValue, hBox);
		
		scene = new Scene(vBox, 550, 250);
		stage.setScene(scene);
        stage.setTitle("Stopwatch");
    	//cosmetics
		String css = this.getClass().getResource("myStyle.css").toExternalForm();
		scene.getStylesheets().add(css);
		timeValue.setId("timeText");
		
		stage.show();
		
	
	}
}