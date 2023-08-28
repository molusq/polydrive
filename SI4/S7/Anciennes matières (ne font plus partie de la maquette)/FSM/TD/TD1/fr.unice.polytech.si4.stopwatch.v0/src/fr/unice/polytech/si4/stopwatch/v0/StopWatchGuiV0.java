package fr.unice.polytech.si4.stopwatch.v0;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A simple StopWatch class in JavaFx to be used for state chart learning.
 *
 * @author jdeanton
 */
public class StopWatchGuiV0 extends Application {
	private Text timeValue = null;
	private Timeline updateDisplay = null;
	private int mins = 0, secs = 0, millis = 0;
	private Timeline chrono;
	private boolean running = false;
	private boolean pause = false;
	private boolean reset = false;
	private static Button leftButton = new Button("Start");
	private static Button rightButton = new Button("Pause");
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws NoSuchMethodException, SecurityException {
		timeValue = new Text("00:00:000");

		updateDisplay = setupTimer(10, this.getClass().getMethod("updateText"));

		leftButton.setOnAction(event -> {
			//TODO: add correct stopwatch logic here instead of the crazy behavior that follows
			((Button) event.getSource()).setText("Stop");
			if (reset) {
				reset = false;
				mins = 0;
				secs = 0;
				millis = 0;
				updateText();
				leftButton.setText("Start");
				rightButton.setText("Pause");
				pause = false;
				return;
			}
			updateDisplay.play();
			if (running) {
				updateDisplay.pause();
				chrono.pause();
				leftButton.setText("Reset");
				running = false;
				reset = true;
				return;
			}
			if (!running) {
				running = true;
				chrono = new Timeline(new KeyFrame(Duration.millis(1), event4 -> {
					try {
						millis++;
						updateTime();
					} catch (Exception e) {
						System.err.println("error while invoking user method in StopWatchGuiV0::setupTimer");
						e.printStackTrace();
					}
				}));
				chrono.setCycleCount(Timeline.INDEFINITE);
				chrono.setAutoReverse(false);
				chrono.play();
			}
		});

		rightButton.setOnAction(event -> {
			//TODO : add correct stopwatch logic here instead of the crazy behavior that follows
			if(running) {
				updateDisplay.pause();
				pause = !pause;
				if (pause) {
					rightButton.setText("Resume");
				} else {
					rightButton.setText("Pause");
					updateDisplay.play();
				}
			}
		});

		setupUI(stage, leftButton, rightButton);
	}

	/**
	 * Helper method to setup a timeline acting as a periodic timer
	 *
	 * @param DurationInMs: the period
	 * @param toRun:        the method to be call periodically
	 * @return
	 */
	private Timeline setupTimer(int DurationInMs, Method method) {
		StopWatchGuiV0 theGui = this;
		Timeline temp = new Timeline(new KeyFrame(Duration.millis(DurationInMs), event -> {
			try {
				method.invoke(theGui);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.err.println("error while invoking user method in StopWatchGuiV0::setupTimer");
				e.printStackTrace();
			}
		}));
		temp.setCycleCount(Timeline.INDEFINITE);
		temp.setAutoReverse(false);
		return temp;
	}

	public void updateText() {
		timeValue.setText((((mins / 10) == 0) ? "0" : "") + mins + ":"
				+ (((secs / 10) == 0) ? "0" : "") + secs + ":"
				+ (((millis / 10) == 0) ? "00" : (((millis / 100) == 0) ? "0" : "")) + millis);
	}

	public void updateTime() {
		if (millis >= 1000) {
			secs++;
			millis = 1000 - millis;
		}
		if (secs >= 60) {
			mins++;
			secs = 60 - secs;
		}
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