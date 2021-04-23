package konyvtar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import konyvtar.gui.Controller;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
            Controller controller = loader.getController();
            AnchorPane root = loader.load();
            Scene scene = new Scene(root, 1200, 600);
            primaryStage.setTitle("Library Application");
            primaryStage.setScene(scene);
            // show the GUI
            primaryStage.show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
