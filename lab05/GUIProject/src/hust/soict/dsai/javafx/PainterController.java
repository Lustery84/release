package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadioButton; // Thêm cho phần Eraser

    @FXML
    private RadioButton eraserRadioButton; // Thêm cho phần Eraser

    @FXML
    private ToggleGroup toolsToggleGroup; // Thêm cho phần Eraser

    private Color currentColor = Color.BLACK; // Mặc định là Pen

    @FXML
    void initialize() { // Được gọi sau khi các trường @FXML được inject
        // Thiết lập mặc định cho tool là Pen (nếu bạn thêm RadioButton)
        if (penRadioButton != null) {
            penRadioButton.setSelected(true);
            currentColor = Color.BLACK;
        }

        // Listener cho sự thay đổi tool (Pen/Eraser)
        if (toolsToggleGroup != null) {
            toolsToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (penRadioButton.isSelected()) {
                    currentColor = Color.BLACK;
                } else if (eraserRadioButton.isSelected()) {
                    currentColor = Color.WHITE; // Màu của Eraser giống màu nền Pane
                }
            });
        }
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear(); // [cite: 71, 72]
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Lấy màu dựa trên công cụ được chọn (pen/eraser)
        // Nếu không có RadioButton, mặc định là vẽ màu đen
        Color drawColor = Color.BLACK;
        if (eraserRadioButton != null && eraserRadioButton.isSelected()) {
            drawColor = Color.WHITE; // Màu của tẩy (giả sử nền trắng)
        }

        Circle newCircle = new Circle(event.getX(), event.getY(), 4, drawColor); // [cite: 68, 69]
        drawingAreaPane.getChildren().add(newCircle); // [cite: 69]
    }

    // Phương thức này được thêm cho bài 3.4.1
    // Nó sẽ được gọi khi chuột được nhấn (không chỉ kéo)
    // Trong SceneBuilder, bạn cần gán phương thức này cho sự kiện "On Mouse Pressed" của drawingAreaPane
    @FXML
    void drawingAreaMousePressed(MouseEvent event) {
        // Lấy màu dựa trên công cụ được chọn (pen/eraser)
        Color drawColor = Color.BLACK;
        if (eraserRadioButton != null && eraserRadioButton.isSelected()) {
            drawColor = Color.WHITE;
        }
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, drawColor);
        drawingAreaPane.getChildren().add(newCircle);
    }

    // Phương thức này được gọi khi người dùng chọn công cụ Pen
    @FXML
    void penToolSelected(ActionEvent event) {
        currentColor = Color.BLACK;
    }

    // Phương thức này được gọi khi người dùng chọn công cụ Eraser
    @FXML
    void eraserToolSelected(ActionEvent event) {
        // Giả sử màu nền của drawingAreaPane là trắng
        currentColor = Color.WHITE;
    }
}