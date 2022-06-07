package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("../display/restaurant.fxml"));
       primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene =new Scene(root );
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
