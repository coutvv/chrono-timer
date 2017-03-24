package ru.coutvv.chronot.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.coutvv.detime.DeTime;
import ru.coutvv.detime.util.DeTimer;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author coutvv
 */
public class ChronotController implements Initializable {

    public static Stage main;

    public Label time;
    public TextField taskName;
    public ProgressBar progress;

    /**
     * Buttons!!!!
     */
    public Button stopButton;
    public Button startButton;
    public Button restButton;

    private double target = 0;
    private ExecutorService executorService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        restButton.setDisable(false);
    }

    /**
     * Running Chronos
     * @param actionEvent
     */
    public void start(ActionEvent actionEvent) {
//        if(main!=null) {
//            main.setIconified(true);
//        }
        startTimer(25);
    }

    /**
     * Running Rest Part
     * @param actionEvent
     */
    public void rest(ActionEvent actionEvent) {

        startTimer(5);
    }

    /**
     * Stop timer
     * @param actionEvent
     * @throws InterruptedException
     */
    public void stop(ActionEvent actionEvent) throws InterruptedException {
        executorService.shutdownNow();
        time.setText("00:00:00");
        startButton.setDisable(false);
        stopButton.setDisable(true);
        restButton.setDisable(false);
    }


    private Consumer<DeTime> updater = (detime) -> {
        Platform.runLater(() -> {
            time.setText(detime.toString());
            double secs = detime.getSeconds() + detime.getMinute()*100 + detime.getHour()*10000;
            System.out.println(secs + " " + target);
            progress.setProgress((target - secs)/target);
            if(time.getText().equals("00:00:00")) {
                main.setIconified(false);
            }
        });

    };

    private void createExecutor() {
        executorService = Executors.newSingleThreadExecutor((r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }



    private void startTimer(int min) {
        this.target = min * 100;
        createExecutor();
        executorService.execute(new DeTimer(0, min, 0, updater));
        startButton.setDisable(true);
        stopButton.setDisable(false);
        restButton.setDisable(true);
    }

}
