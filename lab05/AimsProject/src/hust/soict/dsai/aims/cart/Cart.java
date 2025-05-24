package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.exception.LimitExceededException; // Import exception
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    // private List<Media> itemsOrdered = new ArrayList<Media>(); // Thay thế dòng này [cite: 174]
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList(); // [cite: 174, 175]

    public ObservableList<Media> getItemsOrdered() { // [cite: 174]
        return itemsOrdered;
    }
    
    // Phương thức addMedia với Exception Handling theo Hình 48 [cite: 210]
    public void addMedia(Media media) throws LimitExceededException { // [cite: 210]
        if (itemsOrdered.contains(media)) {
            // Tùy chọn: throw exception hoặc thông báo item đã tồn tại
            // System.out.println("Item is already in the cart.");
            // Hoặc throw new IllegalArgumentException("Item '" + media.getTitle() + "' is already in the cart.");
             throw new LimitExceededException("Item '" + media.getTitle() + "' is already in the cart."); // Tạm dùng LimitExceededException cho tiện
        }
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) { // [cite: 210]
            itemsOrdered.add(media);
            // System.out.println("The media has been added to the cart."); // Bỏ qua vì sẽ có thông báo GUI
        } else {
            throw new LimitExceededException("ERROR: The number of media has reached its limit of " + MAX_NUMBERS_ORDERED); // [cite: 210]
        }
    }

    // Các phương thức khác của Cart (removeMedia, totalCost, makeEmpty,...)
    // giữ nguyên hoặc điều chỉnh nếu cần.

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            // System.out.println("The media has been removed from the cart.");
        } else {
            System.out.println("The media is not in the cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }
    
    public void makeEmpty() {
        itemsOrdered.clear();
    }
    
    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }
}