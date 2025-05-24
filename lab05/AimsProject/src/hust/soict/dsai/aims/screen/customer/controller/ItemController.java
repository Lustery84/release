package hust.soict.dsai.aims.screen.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemController {

    private Media media;
    private Cart cart; 

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private HBox hboxMedia;

    // Constructor để nhận Cart
    public ItemController(Cart cart) {
        this.cart = cart;
    }

    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle()); // [cite: 124]
        lblCost.setText(media.getCost() + " $"); // [cite: 124]

        if (media instanceof Playable) { // [cite: 124]
            btnPlay.setVisible(true); // [cite: 124]
        } else {
            btnPlay.setVisible(false); // [cite: 124]
           
            HBox.setMargin(btnAddToCart, new Insets(0,0,0,0)); // Reset margin
         
            if(hboxMedia != null){
                 HBox parent = (HBox) btnAddToCart.getParent();
                 if(parent != null && !btnPlay.isVisible()){
                   
                 }
            }

        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        try {
            cart.addMedia(media);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(media.getTitle() + " has been added to cart.");
            alert.showAndWait();
        } catch (LimitExceededException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot add media to cart");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot add media to cart");
            alert.setContentText("An unexpected error occurred: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Playing " + media.getTitle());
                // For DVDs with non-positive length, an error should be shown via PlayerException
                // This is handled within the play() method of the media itself.
                alert.showAndWait();

            } catch (Exception e) { // Bắt PlayerException hoặc các lỗi khác
                System.err.println("Error playing media: " + media.getTitle());
                e.printStackTrace();
                // Hiển thị dialog lỗi theo Hình 51
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Media Player Error");
                errorAlert.setHeaderText("Error playing " + media.getTitle());
                errorAlert.setContentText(e.getMessage()); // Thông điệp từ PlayerException
                errorAlert.showAndWait();
            }
        }
    }
}