package hust.soict.dsai.aims.screen.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException; // Cần import PlayerException
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store; // Cần Store để quay lại
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Predicate;

public class CartController {

    private Cart cart;
    private Store store; // Thêm store để có thể quay lại StoreScreen

    @FXML
    private TableView<Media> tblMedia; //

    @FXML
    private TableColumn<Media, Integer> colMediaId; //

    @FXML
    private TableColumn<Media, String> colMediaTitle; //

    @FXML
    private TableColumn<Media, String> colMediaCategory; //

    @FXML
    private TableColumn<Media, Float> colMediaCost; //

    @FXML
    private Button btnPlay; //

    @FXML
    private Button btnRemove; //

    @FXML
    private Label costLabel; //

    @FXML
    private TextField tfFilter; //

    @FXML
    private RadioButton radioBtnFilterId; //

    @FXML
    private RadioButton radioBtnFilterTitle; //

    @FXML
    private ToggleGroup filterCategory; //

    private FilteredList<Media> filteredData; // Để filter

    // Constructor
    public CartController(Store store, Cart cart) { //
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() { //
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id")); //
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title")); //
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category")); //
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost")); //
        
        // Kiểm tra cart.getItemsOrdered() có null không trước khi set vào TableView
        if (cart.getItemsOrdered() != null) { //
             // Khởi tạo FilteredList
            filteredData = new FilteredList<>(cart.getItemsOrdered(), p -> true); // Ban đầu hiển thị tất cả
            tblMedia.setItems(filteredData); //
        } else {
            // Nếu getItemsOrdered() là null, tạo một ObservableList rỗng để tránh lỗi
            tblMedia.setItems(FXCollections.observableArrayList());
        }


        btnPlay.setVisible(false); //
        btnRemove.setVisible(false); //

        tblMedia.getSelectionModel().selectedItemProperty().addListener( //
                (observable, oldValue, newValue) -> {
                    updateButtonBar(newValue); //
                }
        );

        // Tính tổng chi phí và cập nhật label
        // Sử dụng Bindings để tự động cập nhật tổng chi phí
        // Điều này yêu cầu cart.totalCost() trả về một ObservableNumberValue hoặc bạn tự cập nhật nó.
        // Hoặc cách đơn giản hơn là cập nhật thủ công mỗi khi cart thay đổi.
        // Tài liệu không nói rõ cách cập nhật total cost label, nên ta sẽ làm thủ công
        updateTotalCostLabel();


        // Listener cho TextField filter
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia(newValue); //
        });

        // Listener cho RadioButton filter (để cập nhật filter khi thay đổi lựa chọn)
        if (filterCategory != null) {
            filterCategory.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                showFilteredMedia(tfFilter.getText());
            });
        }
    }

    void updateButtonBar(Media media) { //
        if (media == null) { //
            btnPlay.setVisible(false); //
            btnRemove.setVisible(false); //
        } else {
            btnRemove.setVisible(true); //
            if (media instanceof Playable) { //
                btnPlay.setVisible(true); //
            } else {
                btnPlay.setVisible(false); //
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) { //
        Media media = tblMedia.getSelectionModel().getSelectedItem(); //
        if (media != null) {
            cart.removeMedia(media); //
            // Không cần update TableView vì ObservableList tự làm
            updateTotalCostLabel(); // Cập nhật tổng chi phí sau khi xóa
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null && media instanceof Playable) {
            try {
                ((Playable) media).play();
                // Hiện dialog như Hình 51 (sẽ hoàn thiện khi làm PlayerException)
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Playing " + media.getTitle());
                alert.showAndWait();

            } catch (PlayerException e) {
                 System.err.println("Error playing media: " + media.getTitle());
                 e.printStackTrace();
                 Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                 errorAlert.setTitle("Media Player Error");
                 errorAlert.setHeaderText("Error playing " + media.getTitle());
                 errorAlert.setContentText(e.getMessage());
                 errorAlert.showAndWait();
            }
        }
    }
    
    private void updateTotalCostLabel() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Please add items before placing an order.");
            alert.showAndWait();
            return;
        }

        // Xử lý đặt hàng
        // Ví dụ: hiển thị thông báo và xóa giỏ hàng
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully! Total cost: " + String.format("%.2f $", cart.totalCost()));
        alert.showAndWait();

        cart.makeEmpty(); // Xóa tất cả media trong giỏ hàng
        updateTotalCostLabel(); // Cập nhật lại tổng chi phí (sẽ là 0)
        // tblMedia.setItems(cart.getItemsOrdered()); // ObservableList tự cập nhật TableView
    }


    @FXML
    void btnViewStorePressed(ActionEvent event) { //
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Store.fxml"; //
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH)); //
            
            // Truyền store và cart cho ViewStoreController
            fxmlLoader.setController(new ViewStoreController(store, cart)); //

            Parent root = fxmlLoader.load(); //
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //
            stage.setScene(new Scene(root)); //
            stage.setTitle("Store"); //
            stage.show(); //
        } catch (IOException e) {
            e.printStackTrace(); //
        }
    }

    // Phần này cho bài tập tùy chọn 6.6 Filter items in cart
    private void showFilteredMedia(String filterText) { //
        String lowerCaseFilter = filterText.toLowerCase();

        // filteredData.setPredicate(media -> { // comment ra do chưa rõ cách hoạt động của predicate
        //     if (lowerCaseFilter == null || lowerCaseFilter.isEmpty()) {
        //         return true; // Hiển thị tất cả nếu filter rỗng
        //     }

        //     if (radioBtnFilterTitle.isSelected()) {
        //         return media.getTitle().toLowerCase().contains(lowerCaseFilter);
        //     } else if (radioBtnFilterId.isSelected()) {
        //         return String.valueOf(media.getId()).equals(lowerCaseFilter);
        //     }
        //     return false; // Mặc định không hiển thị nếu không có radio button nào được chọn (không nên xảy ra)
        // });
        Predicate<Media> predicate = media -> {
            if (lowerCaseFilter == null || lowerCaseFilter.isEmpty()) {
                return true;
            }
            if (radioBtnFilterTitle.isSelected() && media.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (radioBtnFilterId.isSelected() && String.valueOf(media.getId()).equals(lowerCaseFilter)) {
                return true;
            }
            return false;
        };
        filteredData.setPredicate(predicate); //
    }

}