package GUI;

import Controller.Controller;
import CustomException.TypecheckException;
import Model.ProgramState;
import Model.Statement.Statement;
import Repository.Repository;
import Repository.memoryRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramSelectionController {

    @FXML
    private ListView<Statement> programsListView;

    @FXML
    private Button programSelectionButton;

    @FXML
    public void initialize(){
        ArrayList<Statement> programsList = HardcodedPrograms.getHardcodedPrograms();
        programsListView.setItems(FXCollections.observableArrayList(programsList));
        programsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        programSelectionButton.setOnAction(actionEvent -> {
            try {
                int selectedIndex = programsListView.getSelectionModel().getSelectedIndex();
                if(selectedIndex < 0) {
                    return;
                }
                ProgramState programState = new ProgramState(programsList.get(selectedIndex));
                Repository repository = new memoryRepository(programState, "log" + (selectedIndex + 1) + ".txt");
                Controller controller = new Controller(repository);
                controller.typecheck();

                Rectangle2D screenBounds = Screen.getPrimary().getBounds();
                FXMLLoader mainWindowLoader = new FXMLLoader();
                mainWindowLoader.setLocation(getClass().getResource("mainWindow.fxml"));
                Parent mainWindowRoot = mainWindowLoader.load();
                MainProgramController mainProgramController = mainWindowLoader.getController();

                mainProgramController.setController(controller);
                Stage stage = new Stage();
                stage.setTitle("Main Window");
                stage.setX(screenBounds.getMinX() + screenBounds.getWidth()/3);
                stage.setScene(new Scene(mainWindowRoot, screenBounds.getWidth() * 2 / 3, screenBounds.getHeight()));
                stage.show();
            }
            catch(TypecheckException typecheckException){
                Alert alert = new Alert(Alert.AlertType.ERROR, typecheckException.getMessage(), ButtonType.CLOSE);
                alert.showAndWait();
            }

            catch(IOException exception){
                exception.printStackTrace();
            }
        });
    }
}
