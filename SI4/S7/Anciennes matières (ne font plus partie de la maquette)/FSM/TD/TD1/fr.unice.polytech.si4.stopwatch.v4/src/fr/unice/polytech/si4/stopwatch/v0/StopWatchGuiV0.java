package fr.unice.polytech.si4.stopwatch.v0;

import java.util.ArrayList;
import java.util.List;

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
import stopwatch_v4.TimerService;
import stopwatch_v4.defaultsm.DefaultSMStatemachine;
import stopwatch_v4.defaultsm.IDefaultSMStatemachine.SCInterface;
/**
 * A simple StopWatch class in JavaFx to be used for state chart learning.
 * @author jdeanton
 *
 */
public class StopWatchGuiV0 extends Application{
	private Text timeValue = null;
	private List<Text> textList = new ArrayList<>();
	private int counter = 0;

	Button leftButton;
	Button rightButton;
	Button sideButton;
	
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
		for(int i = 0; i < 5; i++) {
			textList.add(new Text("00:00:000"));
		}

		leftButton = new Button("Start");
		rightButton = new Button("Pause");
		sideButton = new Button("Mode");

		SCInterface.startPlaying.subscribe(event -> startWatch());
		SCInterface.startStop.subscribe(event -> stopWatch());
		SCInterface.startPause.subscribe(event -> pause());
		SCInterface.resumePlaying.subscribe(event -> resume());
		SCInterface.reset.subscribe(event -> resetTimer());
		SCInterface.sidePressed.subscribe(event -> sidePressed());
		SCInterface.sideIdle.subscribe(event -> resetTextList());
		SCInterface.updateCounter.subscribe(event -> updateCounter());
		SCInterface.updateDisplay.subscribe(event -> updateText());

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
		
		sideButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sm.getSCInterface().raiseSideButton();
            }
		});
		
		setupUI(stage, leftButton, rightButton, sideButton);
	}
	
	public void updateText() {
		timeValue.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
			 + (((secs/10) == 0) ? "0" : "") + secs + ":" 
				+ (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis);
	}


	private void setupUI(Stage stage, Button leftButton, Button rightButton, Button middleButton) {
		Scene scene = null;
		VBox vBox = null;
		HBox hBox = null;
		hBox = new HBox(30);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(leftButton, rightButton, middleButton);
		vBox = new VBox(30);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(timeValue, textList.get(0), textList.get(1), textList.get(2), textList.get(3), textList.get(4), hBox);
		
		scene = new Scene(vBox, 550, 520);
		stage.setScene(scene);
        stage.setTitle("Stopwatch");
    	//cosmetics
		String css = this.getClass().getResource("myStyle.css").toExternalForm();
		scene.getStylesheets().add(css);
		timeValue.setId("timeText");
		stage.show();
	}
	
	private void startWatch(){
		leftButton.setText("Stop");
	}

	private void resetTimer(){
		millis = 0;
		secs = 0;
		mins = 0;
		updateText();
		resetTextList();
		leftButton.setText("Start");
	}

	private void stopWatch(){
		leftButton.setText("Reset");
		rightButton.setText("Pause");
	}

	private void pause(){
		rightButton.setText("Resume");
	}

	private void resume(){
		rightButton.setText("Pause");
	}
	
	private void sidePressed() {
		updateSideText(textList.get(counter));
		counter++;
		if(counter == 5) {
			counter = 0;
		}
	}
	
	public void updateSideText(Text text) {
		text.setText(timeValue.getText());
	}
	
	private void resetTextList() {
		for(int i = 0; i < 5; i++) {
			textList.get(i).setText("00:00:000");
		}
		counter = 0;
	}
	
	public void updateCounter() {
		millis+=7;
		if(millis >= 1000) {
			secs++;
			millis = 1000 - millis;
		}
		if(secs >= 60) {
			mins++;
			secs = 60 - secs;
		}
	}
	
}