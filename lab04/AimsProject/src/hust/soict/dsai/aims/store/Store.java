package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

public class Store {
    private List<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        itemsInStore.add(media);
        System.out.println("Added " + media.getTitle() + " to the store.");
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Removed " + media.getTitle() + " from the store.");
        } else {
            System.out.println(media.getTitle() + " is not in the store.");
        }
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        System.out.println("Items in Store:");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
            }
        }
        System.out.println("***************************************************");
    }

    public List<Media> searchMediaByTitle(String title) {
        List<Media> result = new ArrayList<>();
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                result.add(media);
            }
        }
        return result;
    }

    public Media searchMediaById(int id) {
        for (Media media : itemsInStore) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

	public void addDVD(DigitalVideoDisc dvd1) {
		// TODO Auto-generated method stub
		
	}
	 public List<Media> getItemsInStore() {
	        
	        return this.itemsInStore;
	    }
}