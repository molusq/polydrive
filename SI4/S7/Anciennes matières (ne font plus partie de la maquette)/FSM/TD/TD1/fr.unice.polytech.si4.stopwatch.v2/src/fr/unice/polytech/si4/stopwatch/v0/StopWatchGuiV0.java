package fr.unice.polytech.si4.stopwatch.v0;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import stopwatch_v2.defaultsm.DefaultSMStatemachine;
import stopwatch_v2.defaultsm.IDefaultSMStatemachine.SCInterface;
/**
 * A simple StopWatch class in JavaFx to be used for state chart learning.
 * @author jdeanton
 *
 */
public class StopWatchGuiV0 extends Application{
	private Text timeValue 	   = null;
	private Timeline updateDisplay  = null;
	private Timeline chrono;
	Button leftButton;
	Button rightButton;
	
	private int mins = 0, secs = 0, millis = 0;
	private DefaultSMStatemachine sm = new DefaultSMStatemachine();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws NoSuchMethodException, SecurityException {
		sm = new DefaultSMStatemachine();
	    sm.init();
	    sm.enter();
		timeValue = new Text("00:00:000");
		
		updateDisplay = setupTimer(1, this.getClass().getMethod("updateText"));

		leftButton = new Button("Start");
		rightButton = new Button("Pause");

		SCInterface.startPlaying.subscribe(event -> doStart());
		SCInterface.startStop.subscribe(event -> doStop());
		SCInterface.startPause.subscribe(event -> doPause());
		SCInterface.resumePlaying.subscribe(event -> doResume());
		SCInterface.reset.subscribe(event -> doReset());
		
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

	/**
	 * Helper method to setup a timeline acting as a periodic timer
	 * 
	 * @param DurationInMs: the period
	 * @param toRun: the method to be call periodically
	 * @return
	 */
	private Timeline setupTimer(int DurationInMs, Method method) {
		StopWatchGuiV0 theGui = this;
		Timeline temp = new Timeline(new KeyFrame(Duration.millis(DurationInMs), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					method.invoke(theGui);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("error while invoking user method in StopWatchGuiV0::setupTimer");
					e.printStackTrace();
				}
			}
		}));
		temp.setCycleCount(Timeline.INDEFINITE);
		temp.setAutoReverse(false);
		return temp;
	}
	
	public void updateText() {
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
	
	public void doStart(){
		updateDisplay.play();

		chrono = new Timeline();
		chrono.setCycleCount(Timeline.INDEFINITE);
		chrono.getKeyFrames().add(
				new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent event) {
						millis++;
						if(millis >= 1000) {
							secs++;
							millis = 1000 - millis;
						}
						if(secs >= 60) {
							mins++;
							secs = 60 - secs;
						}
					}
				}));
		chrono.play();

		leftButton.setText("Stop");
	}

	public void doReset(){
		millis = 0;
		secs = 0;
		mins = 0;
		updateText();
		chrono.stop();
		updateDisplay.stop();
		leftButton.setText("Start");
	}

	public void doStop(){
		leftButton.setText("Restart");

		chrono.stop();
		updateDisplay.stop();
		rightButton.setText("Pause");
	}

	public void doPause(){
		updateDisplay.pause();
		rightButton.setText("Resume");
	}

	public void doResume(){
		rightButton.setText("Pause");
		updateDisplay.play();
	}
}