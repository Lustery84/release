package hust.soict.dsai.aims.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

public class Cart {
	private List<Media> itemsOrdered = new ArrayList<>();

	public List<Media> getItemsOrdered() {
	    return itemsOrdered;
	}

	public void setItemsOrdered(List<Media> itemsOrdered) {
	    this.itemsOrdered = itemsOrdered;
	}
    public static final int MAX_NUMBERS_ORDERED = 20;

    public void addMedia(Media media) {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("Cart is full! Cannot add more.");
            return;
        }
        itemsOrdered.add(media);
        System.out.println("Added " + media.getTitle() + " to the cart.");
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed " + media.getTitle() + " from the cart.");
        } else {
            System.out.println(media.getTitle() + " is not in the cart.");
        }
    }

    public float totalCost() {
        float total = 0.0f;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        int i = 1;
        for (Media media : itemsOrdered) {
            System.out.println(i + ". " + media.toString());
            i++;
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    public Media searchMediaById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        System.out.println("No media with ID " + id + " found in cart.");
        return null;
    }

    public List<Media> searchMediaByTitle(String title) {
        List<Media> result = new ArrayList<>();
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                result.add(media);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No media with title \"" + title + "\" found in cart.");
        }
        return result;
    }

    public void sortByTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        printCart(); // Print the cart after sorting
    }

    public void sortByCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        printCart(); // Print the cart after sorting
    }

	public void addDigitalVideoDisc(DigitalVideoDisc dvd1) {
		// TODO Auto-generated method stub
		
	}

	

	
}