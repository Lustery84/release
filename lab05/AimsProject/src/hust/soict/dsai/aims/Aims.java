package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;



public class Aims {

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        // Tạo media
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2_error = new DigitalVideoDisc("DVD Error Test", "Test", "Director Test", 0, 5.0f); // DVD có length = 0 [cite: 212]
        
        CompactDisc cd1 = new CompactDisc("Good CD", "Music", 20.5f, "Artist A");
        Track track1_ok = new Track("Track 1 OK", 180);
        Track track2_error = new Track("Track 2 Error", 0); // Track có length = 0 [cite: 212]
        Track track3_ok = new Track("Track 3 OK", 200);
        cd1.addTrack(track1_ok);
        cd1.addTrack(track2_error);
        cd1.addTrack(track3_ok);

        CompactDisc cd2_error_length = new CompactDisc("CD Error Length", "Music", "Some Director", 0, 15.0f, "Artist B"); // CD có length = 0 [cite: 219]


        // Thêm vào store
        store.addMedia(dvd1);
        store.addMedia(dvd2_error);
        store.addMedia(cd1);
        store.addMedia(cd2_error_length);

        System.out.println("--- Testing Playable Media ---");
        for (Media m : store.getItemsInStore()) {
            if (m instanceof DigitalVideoDisc) {
                System.out.println("Trying to play DVD: " + m.getTitle());
                try {
                    ((DigitalVideoDisc) m).play(); // [cite: 228]
                } catch (PlayerException e) { // [cite: 228]
                    System.err.println("Error playing DVD '" + m.getTitle() + "': " + e.getMessage()); // [cite: 231]
             
                    System.out.println("toString() of exception: " + e.toString()); // [cite: 231]

                }
            } else if (m instanceof CompactDisc) {
                System.out.println("Trying to play CD: " + m.getTitle());
                try {
                    ((CompactDisc) m).play(); // [cite: 228]
                } catch (PlayerException e) { // [cite: 228]
                    System.err.println("Error playing CD '" + m.getTitle() + "': " + e.getMessage()); // [cite: 231]
                    // e.printStackTrace(); // [cite: 231]
                    // JOptionPane.showMessageDialog(null, e.getMessage(), "CD Playback Error", JOptionPane.ERROR_MESSAGE); // [cite: 231, 232]
                     System.out.println("toString() of exception: " + e.toString()); // [cite: 231]
                }
            }
        }
        

    }
}