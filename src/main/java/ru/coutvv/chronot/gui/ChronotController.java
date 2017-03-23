package ru.coutvv.chronot.gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.coutvv.detime.DeTime;
import ru.coutvv.detime.util.DeTimer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author coutvv
 */
public class ChronotController {

    public static Stage main;

    public Label time;
    public TextField taskName;
    public ProgressBar progress;

    private Consumer<DeTime> updater = (detime) -> {
        time.setText(detime.toString());
    };

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void start(ActionEvent actionEvent) {
        if(main!=null) {
//            main.setIconified(true);
        }
        taskName.setEditable(false);
        Task<Void>  task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                Platform.runLater(new DeTimer(0, 0, 10, updater));

                return null;
            }
        };

        executorService.submit(task);
    }

    public void rest(ActionEvent actionEvent) {

    }

    public void stop(ActionEvent actionEvent) {
//        exec.shutdown();
//        while(!exec.isShutdown()) {
//            System.out.println("Ещё не завершено :(");
//        }
        time.setText("00:00");
    }


}
