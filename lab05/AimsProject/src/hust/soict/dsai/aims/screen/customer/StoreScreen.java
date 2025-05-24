package hust.soict.dsai.aims.screen.customer;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.screen.customer.controller.ViewStoreController;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StoreScreen extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception { // [cite: 142]
        final String STORE_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Store.fxml"; // [cite: 143]
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH)); // [cite: 143]
        
        // Truyền store và cart đã khởi tạo cho ViewStoreController
        fxmlLoader.setController(new ViewStoreController(store, cart)); // [cite: 143] (đã sửa)

        Parent root = fxmlLoader.load(); // [cite: 143]
        primaryStage.setTitle("Store"); // [cite: 143]
        primaryStage.setScene(new Scene(root)); // [cite: 143]
        primaryStage.show(); // [cite: 143]
    }

    public static void main(String[] args) { // [cite: 144]
        store = new Store(); // [cite: 144]
        cart = new Cart(); // Khởi tạo cart

        // Thêm một vài media mẫu vào store
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        Book book1 = new Book("The Lord of the Rings", "Fantasy", 29.99f);
        book1.addAuthor("J.R.R. Tolkien");
        store.addMedia(book1);

        CompactDisc cd1 = new CompactDisc("Best of Pop", "Pop", 15.99f, "Various Artists");
        Track track1 = new Track("Track 01", 300); // 5 phút
        Track track2 = new Track("Track 02", 240); // 4 phút
        cd1.addTrack(track1);
        cd1.addTrack(track2);
        store.addMedia(cd1);
        
        CompactDisc cd2 = new CompactDisc("Empty CD For Test", "Test", 0f, "Test Artist"); // CD có length = 0
        store.addMedia(cd2);

        DigitalVideoDisc dvd4_empty = new DigitalVideoDisc("Empty DVD", "Test", "Director", 0, 5.0f); // DVD có length = 0
        store.addMedia(dvd4_empty);


        launch(args); // [cite: 144]
    }
}