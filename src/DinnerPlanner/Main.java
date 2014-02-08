package DinnerPlanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = new MainView(new MainController(new DinnerModel()));
        primaryStage.setTitle("Dinner Planner");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
