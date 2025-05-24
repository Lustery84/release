package hust.soict.dsai.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Painter extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Đường dẫn đến tệp FXML phải chính xác
        // Nó tính từ thư mục gốc của classpath (thường là thư mục chứa package hust)
        Parent root = FXMLLoader.load(getClass().getResource("/hust/soict/dsai/javafx/Painter.fxml")); // [cite: 76]
        
        Scene scene = new Scene(root); // [cite: 76]
        stage.setTitle("Painter"); // [cite: 77]
        stage.setScene(scene); // [cite: 77]
        stage.show(); // [cite: 77]
    }

    public static void main(String[] args) {
        launch(args); // [cite: 79]
    }
}