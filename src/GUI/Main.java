package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{
        HardcodedPrograms.deletePreviousLogs();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("programSelectionWindow.fxml"));
        Parent root = loader.load();
        loader.getController();
        primaryStage.setTitle("Program Selection Window");
        primaryStage.setScene(new Scene(root, screenBounds.getWidth() / 3, screenBounds.getHeight()));
        primaryStage.setX(0);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
