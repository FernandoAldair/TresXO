import controller.MainWindows;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaz.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

//
//        MainWindows mainWindows = loader.getController();
//        mainWindows.setScene(scene);
//        mainWindows.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
