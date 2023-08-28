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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


enum StopWatchState {
    STOP,
    RESET,
    IDLE
}

enum RunningState {
    RUNNING,
    PAUSED,
    IDLE
}

enum ModeState {
    MODE,
    MODE_IDLE
}

enum Event {
    LEFT_CLICK,
    RIGHT_CLICK,
}


/**
 * A simple StopWatch class in JavaFx to be used for state chart learning.
 *
 * @author jdeanton
 */
public class StopWatchGuiV0 extends Application {
    private Text timeValue = null;
    private Timeline updateDisplay = null;
    private Timeline chrono = null;
    private int mins = 0, secs = 0, millis = 0, counter = 0;

    private List<Text> textList = new ArrayList<>();

    private Button leftButton = null;
    private Button rightButton = null;
    private Button sideButton = null;

    private StopWatchState stopWatchState;
    private ModeState modeState;
    private RunningState runningState;

    private Timer timer = new Timer();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws NoSuchMethodException, SecurityException {
        stopWatchState = StopWatchState.RESET;
        modeState = ModeState.MODE_IDLE;
        runningState = RunningState.IDLE;

        timeValue = new Text("00:00:000");
        for (int i = 0; i < 5; i++) {
            textList.add(new Text("00:00:000"));
        }
        updateDisplay = setupTimer(7, this.getClass().getMethod("updateText"));

        leftButton = new Button("Start");
        leftButton.setOnAction(event -> stateMachine(Event.LEFT_CLICK));

        rightButton = new Button("Pause");
        rightButton.setOnAction(event -> stateMachine(Event.RIGHT_CLICK));

        sideButton = new Button("Mode");
        sideButton.setOnAction(event -> mode());

        setupUI(stage, leftButton, rightButton);
    }

    /**
     * Helper method to setup a timeline acting as a periodic timer
     *
     * @param DurationInMs: the period
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
        if (millis >= 1000) {
            secs++;
            millis = 1000 - millis;
        }
        if (secs >= 60) {
            mins++;
            secs = 60 - secs;
        }
        timeValue.setText((((mins / 10) == 0) ? "0" : "") + mins + ":"
                + (((secs / 10) == 0) ? "0" : "") + secs + ":"
                + (((millis / 10) == 0) ? "00" : (((millis / 100) == 0) ? "0" : "")) + millis);
    }

    private void setupUI(Stage stage, Button leftButton, Button rightButton) {
        Scene scene;
        VBox vBox;
        HBox hBox;
        hBox = new HBox(30);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(leftButton, rightButton, sideButton);
        vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(timeValue, textList.get(0), textList.get(1), textList.get(2), textList.get(3), textList.get(4), hBox);

        scene = new Scene(vBox, 750, 550);
        stage.setScene(scene);
        stage.setTitle("Stopwatch");
        //cosmetics
        String css = this.getClass().getResource("myStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        timeValue.setId("timeText");

        stage.show();
    }

    private void stateMachine(Event event) {
        switch (stopWatchState) {
            case STOP:
                if (event == Event.LEFT_CLICK) {
                    doReset();
                    stopWatchState = StopWatchState.RESET;
                }
                break;
            case RESET:
                switch (event) {
                    case LEFT_CLICK:
                        doStart();
                    case RIGHT_CLICK:
                        runningMachine(event);
                        break;
                }
                break;
            default:
                System.out.println("Error in SM");
        }
    }

    private void runningMachine(Event event) {
        switch (runningState) {
            case IDLE:
                runningState = RunningState.RUNNING;
                break;
            case RUNNING:
                switch (event) {
                    case LEFT_CLICK:
                        doStop();
                        stopWatchState = StopWatchState.STOP;
                        runningState = RunningState.IDLE;
                        break;
                    case RIGHT_CLICK:
                        doPause();
                        runningState = RunningState.PAUSED;
                }
                break;
            case PAUSED:
                switch (event) {
                    case LEFT_CLICK:
                        doStop();
                        stopWatchState = StopWatchState.STOP;
                        runningState = RunningState.IDLE;
                        break;
                    case RIGHT_CLICK:
                        doResume();
                        runningState = RunningState.RUNNING;
                }
        }
    }

    private void mode() {
        switch (modeState) {
            case MODE:
                resetTextList();
                modeState = ModeState.MODE_IDLE;
                timer.cancel();
                timer = new Timer();
                break;
            case MODE_IDLE:
                sidePressed();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                modeState = ModeState.MODE_IDLE;
                            }
                        },
                        1000
                );
                modeState = ModeState.MODE;
                break;
        }
    }

    private void doStart() {
        updateDisplay.play();

        chrono = new Timeline();
        chrono.setCycleCount(Timeline.INDEFINITE);
        chrono.getKeyFrames().add(
                new KeyFrame(Duration.millis(7), event -> {
                    millis += 7;
                    if (millis >= 1000) {
                        secs++;
                        millis = 1000 - millis;
                    }
                    if (secs >= 60) {
                        mins++;
                        secs = 60 - secs;
                    }
                }));
        chrono.play();

        leftButton.setText("Stop");
    }

    private void doReset() {
        chrono.stop();
        updateDisplay.stop();
        millis = 0;
        secs = 0;
        mins = 0;
        resetTextList();
        updateText();
        leftButton.setText("Start");
    }

    private void doStop() {
        chrono.stop();
        updateDisplay.stop();
        leftButton.setText("Restart");
        rightButton.setText("Pause");
    }

    private void doPause() {
        updateDisplay.pause();
        rightButton.setText("Resume");
    }

    private void doResume() {
        updateDisplay.play();
        rightButton.setText("Pause");
    }

    private void sidePressed() {
        updateSideText(textList.get(counter));
        counter++;
        if (counter == 5) {
            counter = 0;
        }
    }

    private void updateSideText(Text text) {
        text.setText(timeValue.getText());
    }

    private void resetTextList() {
        textList.forEach(text -> text.setText("00:00:000"));
        counter = 0;
    }
}