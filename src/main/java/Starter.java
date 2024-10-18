import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
       // stage.setScene(new Scene(FXMLLoader.load((getClass().getResource("view/employee_form.fxml")))));
       //stage.show();

        //stage.setScene(new Scene(FXMLLoader.load((getClass().getResource("view/supplier_form.fxml")))));
      //  stage.show();

      //  stage.setScene(new Scene(FXMLLoader.load((getClass().getResource("view/add_items_form.fxml")))));
      //  stage.show();

        //  stage.setScene(new Scene(FXMLLoader.load((getClass().getResource("view/order_form.fxml")))));
        //  stage.show();

        stage.setScene(new Scene(FXMLLoader.load((getClass().getResource("view/dash_board_form.fxml")))));
        stage.show();

    }
}
