package hust.soict.dsai.test.store;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

public class StoreTest {

    public static void main(String[] args) {
        // Create a new store
        Store store = new Store();

        // Create some sample DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 121, 24.99f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(0, "Aladin",
                "Animation", 90, null, 0);

        // Test addDVD()
        System.out.println("\n--- Test addDVD() ---");
        store.addDVD(dvd1);
        store.addDVD(dvd2);
        store.addDVD(dvd3);
        store.printStore(); // Show DVDs in store

        // Test removeDVD() - Success
        System.out.println("\n--- Test removeDVD() - Success ---");
        store.removeMedia(dvd2);
        store.printStore(); // Show DVDs after removal

        // Test removeDVD() - Failure
        System.out.println("\n--- Test removeDVD() - Failure ---");
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(0, "NonExistent", "Action", 0f, null, 0);
        store.removeMedia(dvd4); // Try to remove a DVD that's not there
        store.printStore();
    }
}