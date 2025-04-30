package hust.soict.dsai.test.cart;
import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

public class CartTest {

    public static void main(String[] args) {

        // Create a new cart
        Cart cart = new Cart();

        // Create new dvd objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(0, "Aladin",
                "Animation", (int) 18.99f, null, 0);
        cart.addDigitalVideoDisc(dvd3);

        // Test the print method
        System.out.println("\n--- Test printCart() ---");
        cart.printCart();

        // To-do: Test the search methods here
        System.out.println("\n--- Test searchDVDById() ---");
        Media foundDVD = cart.searchMediaById(dvd2.getId());
        if (foundDVD != null) {
            System.out.println("Found DVD: " + foundDVD.toString());
        }

        System.out.println("\n--- Test searchDVDById() - Not Found ---");
        cart.searchMediaById(99); // Search for a non-existent ID

        System.out.println("\n--- Test searchByTitle() ---");
        List<Media> foundDVDs = cart.searchMediaByTitle("Star Wars");
        if (!foundDVDs.isEmpty()) {
            System.out.println("Found DVDs:");
            for (Media dvd : foundDVDs) {
                System.out.println(dvd.toString());
            }
        }

        System.out.println("\n--- Test searchByTitle() - Multiple Matches ---");
        cart.searchMediaByTitle("Aladin"); // Assuming you have "Aladin"

        System.out.println("\n--- Test searchByTitle() - Not Found ---");
        cart.searchMediaByTitle("NonExistentMovie");
    }
}