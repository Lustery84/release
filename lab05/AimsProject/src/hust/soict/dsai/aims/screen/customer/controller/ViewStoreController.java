package hust.soict.dsai.aims.screen.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewStoreController {

    private Store store;
    private Cart cart; // Thêm cart để truyền cho ItemController và CartController

    @FXML
    private GridPane gridPane;

    // Constructor nhận Store và Cart
    public ViewStoreController(Store store, Cart cart) { // [cite: 199] (ám chỉ việc cần cart)
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() { // [cite: 136]
        final String ITEM_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Item.fxml"; // [cite: 133] (thay dsai nếu cần)
        int column = 0; // [cite: 133]
        int row = 1; // [cite: 133]

        if (store != null && store.getItemsInStore() != null) {
            for (Media media : store.getItemsInStore()) { // [cite: 133]
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(); // [cite: 134]
                    fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH)); // [cite: 134]
                    
                    // Truyền cart vào ItemController
                    ItemController itemController = new ItemController(cart); // [cite: 134] (đã sửa để truyền cart)
                    fxmlLoader.setController(itemController); // [cite: 134]

                    AnchorPane anchorPane = fxmlLoader.load(); // [cite: 134]
                    itemController.setData(media); // [cite: 135]

                    if (column == 3) { // [cite: 135] (giả sử 3 cột mỗi hàng)
                        column = 0; // [cite: 135]
                        row++; // [cite: 135]
                    }
                    gridPane.add(anchorPane, column++, row); // [cite: 135]
                    GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10)); // [cite: 135]
                } catch (IOException e) {
                    e.printStackTrace(); // [cite: 135]
                }
            }
        }
    }

    @FXML
    void btnViewCartPressed(ActionEvent event) { // [cite: 199]
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Cart.fxml"; // [cite: 199]
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH)); // [cite: 199]
            
            // Truyền store và cart cho CartController
            fxmlLoader.setController(new CartController(store, cart)); // [cite: 200] (đã sửa)

            Parent root = fxmlLoader.load(); // [cite: 200]
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // [cite: 201, 202]
            stage.setScene(new Scene(root)); // [cite: 203]
            stage.setTitle("Cart"); // [cite: 203]
            stage.show(); // [cite: 203]
        } catch (IOException e) {
            e.printStackTrace(); // [cite: 199]
        }
    }
}