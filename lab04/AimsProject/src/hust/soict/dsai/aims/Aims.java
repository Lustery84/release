//package hust.soict.dsai.aims;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
//import hust.soict.dsai.aims.cart.Cart;
//import hust.soict.dsai.aims.media.Book;
//import hust.soict.dsai.aims.media.CompactDisc;
//import hust.soict.dsai.aims.media.DigitalVideoDisc;
//import hust.soict.dsai.aims.media.Media;
//import hust.soict.dsai.aims.store.Store;
//
//public class Aims {
//
//    private static Store store = new Store();
//    private static Cart cart = new Cart();
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        // Thêm một vài media mẫu vào cửa hàng
//        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, "Roger Allers", 87));
//        store.addMedia(new Book(2, "The Hobbit", "Fantasy", 15.0f));
//        store.addMedia(new CompactDisc(3, "Abbey Road", "Rock", 12.5f, "George Martin", 47, "The Beatles"));
//
//        showMenu();
//    }
//
//    public static void showMenu() {
//        System.out.println("AIMS:");
//        System.out.println("--------------------------------");
//        System.out.println("1. View store");
//        System.out.println("2. Update store");
//        System.out.println("3. See current cart");
//        System.out.println("0. Exit");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-2-3");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline character
//
//        switch (choice) {
//            case 1:
//                viewStoreMenu();
//                break;
//            case 2:
//                updateStoreMenu();
//                break;
//            case 3:
//                cartMenu();
//                break;
//            case 0:
//                System.out.println("Goodbye!");
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                showMenu();
//        }
//    }
//
//    public static void viewStoreMenu() {
//        store.printStore(); // Hiển thị các mặt hàng trong cửa hàng
//
//        System.out.println("Options:");
//        System.out.println("--------------------------------");
//        System.out.println("1. See a media's details");
//        System.out.println("2. Add a media to cart");
//        System.out.println("3. Play a media");
//        System.out.println("4. See current cart");
//        System.out.println("0. Back");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-2-3-4");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1:
//                seeMediaDetails();
//                break;
//            case 2:
//                addMediaToCart();
//                break;
//            case 3:
//                playMedia();
//                break;
//            case 4:
//                cartMenu();
//                break;
//            case 0:
//                showMenu();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                viewStoreMenu();
//        }
//    }
//
//    public static void seeMediaDetails() {
//        System.out.println("Enter the title of the media:");
//        String title = scanner.nextLine();
//
//        List<Media> foundMedia = store.searchMediaByTitle(title);
//        if (foundMedia != null && !foundMedia.isEmpty()) {
//            System.out.println(foundMedia.get(0).toString()); // Hiển thị chi tiết media đầu tiên
//            mediaDetailsMenu(foundMedia.get(0)); // Pass the media object
//        } else {
//            System.out.println("Media not found.");
//            viewStoreMenu();
//        }
//    }
//
//    public static void mediaDetailsMenu(Media media) {
//        System.out.println("Options:");
//        System.out.println("--------------------------------");
//        System.out.println("1. Add to cart");
//        if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
//            System.out.println("2. Play"); // Chỉ hiển thị nếu là CD hoặc DVD
//        }
//        System.out.println("0. Back");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-" + (media instanceof DigitalVideoDisc || media instanceof CompactDisc ? "2" : ""));
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1:
//                addMediaToCart();
//                break;
//            case 2:
//                if (media instanceof DigitalVideoDisc) {
//                    ((DigitalVideoDisc) media).play();
//                } else if (media instanceof CompactDisc) {
//                    ((CompactDisc) media).play();
//                }
//                break;
//            case 0:
//                viewStoreMenu();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                mediaDetailsMenu(media);
//        }
//    }
//
//    public static void addMediaToCart() {
//        System.out.println("Enter the title of the media to add to cart:");
//        String title = scanner.nextLine();
//
//        List<Media> foundMedia = store.searchMediaByTitle(title);
//        if (foundMedia != null && !foundMedia.isEmpty()) {
//            cart.addMedia(foundMedia.get(0)); // Thêm media đầu tiên vào giỏ hàng
//            System.out.println("Number of items in cart: " + cart.getItemsOrdered().size());
//        } else {
//            System.out.println("Media not found.");
//        }
//        viewStoreMenu();
//    }
//
//    public static void playMedia() {
//        System.out.println("Enter the title of the media to play:");
//        String title = scanner.nextLine();
//
//        List<Media> foundMedia = store.searchMediaByTitle(title);
//        if (!foundMedia.isEmpty()) {
//            Media media = foundMedia.get(0); // Lấy media đầu tiên (có thể cần xử lý nhiều kết quả)
//            if (media instanceof DigitalVideoDisc) {
//                ((DigitalVideoDisc) media).play();
//            } else if (media instanceof CompactDisc) {
//                ((CompactDisc) media).play();
//            } else {
//                System.out.println("This media type cannot be played.");
//            }
//        } else {
//            System.out.println("Media not found.");
//        }
//        viewStoreMenu();
//    }
//
//    public static void updateStoreMenu() {
//        System.out.println("Options:");
//        System.out.println("--------------------------------");
//        System.out.println("1. Add a media to store");
//        System.out.println("2. Remove a media from store");
//        System.out.println("0. Back");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-2");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1:
//                addMediaToStore();
//                break;
//            case 2:
//                removeMediaFromStore();
//                break;
//            case 0:
//                showMenu();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                updateStoreMenu();
//        }
//    }
//
//    public static void addMediaToStore() {
//        System.out.println("Options:");
//        System.out.println("--------------------------------");
//        System.out.println("1. Add a Book");
//        System.out.println("2. Add a DigitalVideoDisc");
//        System.out.println("3. Add a CompactDisc");
//        System.out.println("0. Back");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-2-3");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1:
//                addBookToStore();
//                break;
//            case 2:
//                addDigitalVideoDiscToStore();
//                break;
//            case 3:
//                addCompactDiscToStore();
//                break;
//            case 0:
//                updateStoreMenu();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                addMediaToStore();
//        }
//    }
//
//    public static void addBookToStore() {
//        System.out.println("Enter book details (id, title, category, cost, authors):");
//        System.out.println("Id: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Title: ");
//        String title = scanner.nextLine();
//        System.out.println("Category: ");
//        String category = scanner.nextLine();
//        System.out.println("Cost: ");
//        float cost = scanner.nextFloat();
//        scanner.nextLine();
//        System.out.println("Authors (comma-separated): ");
//        String authors = scanner.nextLine();
//        String[] authorsArray = authors.split(",");
//        List<String> authorsList = new ArrayList<>();
//        for (String author : authorsArray) {
//            authorsList.add(author.trim());
//        }
//
//        Book newBook = new Book(id, title, category, cost);
//        for (String author : authorsList) {
//            newBook.addAuthor(author);
//        }
//        store.addMedia(newBook);
//        System.out.println("Book added to store.");
//        updateStoreMenu();
//    }
//
//    public static void addDigitalVideoDiscToStore() {
//        System.out.println("Enter DVD details (id, title, category, cost, director, length):");
//        System.out.println("Id: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Title: ");
//        String title = scanner.nextLine();
//        System.out.println("Category: ");
//        String category = scanner.nextLine();
//        System.out.println("Cost: ");
//        float cost = scanner.nextFloat();
//        scanner.nextLine();
//        System.out.println("Director: ");
//        String director = scanner.nextLine();
//        System.out.println("Length: ");
//        int length = scanner.nextInt();
//        scanner.nextLine();
//
//        DigitalVideoDisc newDvd = new DigitalVideoDisc(id, title, category, cost, director, length);
//        store.addMedia(newDvd);
//        System.out.println("DVD added to store.");
//        updateStoreMenu();
//    }
//
//    public static void addCompactDiscToStore() {
//        System.out.println("Enter CD details (id, title, category, cost, director, length, artist):");
//        System.out.println("Id: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Title: ");
//        String title = scanner.nextLine();
//        System.out.println("Category: ");
//        String category = scanner.nextLine();
//        System.out.println("Cost: ");
//        float cost = scanner.nextFloat();
//        scanner.nextLine();
//        System.out.println("Director: ");
//        String director = scanner.nextLine();
//        System.out.println("Length: ");
//        int length = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Artist: ");
//        String artist = scanner.nextLine();
//
//        CompactDisc newCd = new CompactDisc(id, title, category, cost, director, length, artist);
//        store.addMedia(newCd);
//        System.out.println("CD added to store.");
//        updateStoreMenu();
//    }
//
//    public static void removeMediaFromStore() {
//        System.out.println("Enter the title of the media to remove:");
//        String title = scanner.nextLine();
//
//        List<Media> foundMedia = store.searchMediaByTitle(title);
//        if (foundMedia != null && !foundMedia.isEmpty()) {
//            store.removeMedia(foundMedia.get(0)); // Remove the first matching media
//            System.out.println("Media removed from store.");
//        } else {
//            System.out.println("Media not found in store.");
//        }
//        updateStoreMenu();
//    }
//
//    public static void cartMenu() {
//        cart.printCart(); // Hiển thị thông tin giỏ hàng
//
//        System.out.println("Options:");
//        System.out.println("--------------------------------");
//        System.out.println("1. Filter media in cart");
//        System.out.println("2. Sort media in cart");
//        System.out.println("3. Remove media from cart");
//        System.out.println("4. Play a media");
//        System.out.println("5. Place order");
//        System.out.println("0. Back");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-2-3-4-5");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1:
//                filterMediaInCartMenu();
//                break;
//            case 2:
//                sortMediaInCartMenu();
//                break;
//            case 3:
//                removeMediaFromCart();
//                break;
//            case 4:
//                playMedia();
//                break;
//            case 5:
//                placeOrder();
//                break;
//            case 0:
//                showMenu();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                cartMenu();
//        }
//    }
//
//    public static void filterMediaInCartMenu() {
//        System.out.println("Options:");
//        System.out.println("--------------------------------");
//        System.out.println("1. Filter by id");
//        System.out.println("2. Filter by title");
//        System.out.println("0. Back");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-2");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1:
//                filterMediaById();
//                break;
//            case 2:
//                filterMediaByTitle();
//                break;
//            case 0:
//                cartMenu();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                filterMediaInCartMenu();
//        }
//    }
//
//    public static void filterMediaById() {
//        System.out.println("Enter the ID to filter by:");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        Media foundMedia = cart.searchMediaById(id);
//        if (foundMedia != null) {
//            System.out.println(foundMedia.toString());
//        } else {
//            System.out.println("No media with ID " + id + " found in cart.");
//        }
//        cartMenu();
//    }
//
//    public static void filterMediaByTitle() {
//        System.out.println("Enter the title to filter by:");
//        String title = scanner.nextLine();
//
//        List<Media> foundMedia = cart.searchMediaByTitle(title);
//        if (!foundMedia.isEmpty()) {
//            for (Media media : foundMedia) {
//                System.out.println(media.toString());
//            }
//        } else {
//            System.out.println("No media with title \"" + title + "\" found in cart.");
//        }
//        cartMenu();
//    }
//
//    public static void sortMediaInCartMenu() {
//        System.out.println("Options:");
//        System.out.println("--------------------------------");
//        System.out.println("1. Sort by title");
//        System.out.println("2. Sort by cost");
//        System.out.println("0. Back");
//        System.out.println("--------------------------------");
//        System.out.println("Please choose a number: 0-1-2");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1:
//                cart.sortByTitle();
//                break;
//            case 2:
//                cart.sortByCost();
//                break;
//            case 0:
//                cartMenu();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                sortMediaInCartMenu();
//        }
//    }
//
//    public static void removeMediaFromCart() {
//        System.out.println("Enter the title of the media to remove:");
//        String title = scanner.nextLine();
//
//        List<Media> foundMedia = cart.searchMediaByTitle(title);
//        if (!foundMedia.isEmpty()) {
//            cart.removeMedia(foundMedia.get(0)); // Remove the first matching media
//        } else {
//            System.out.println("Media not found in cart.");
//        }
//        cartMenu();
//    }
//
//    public static void placeOrder() {
//        System.out.println("An order is created.");
//        cart.getItemsOrdered().clear(); // Xóa tất cả các mặt hàng khỏi giỏ hàng
//        System.out.println("Cart is now empty.");
//        showMenu();
//    }
//}

package hust.soict.dsai.aims;


import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media; 
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.screen.manager.StoreManagerScreen; 
import javax.swing.SwingUtilities;

public class Aims {

    // Giữ nguyên khai báo store và cart từ code gốc của bạn
    private static Store store = new Store();
    private static Cart cart = new Cart();
    // private static Scanner scanner = new Scanner(System.in); // Loại bỏ Scanner

    public static void main(String[] args) {
        // Giữ nguyên phần thêm media mẫu vào cửa hàng từ code gốc của bạn
        // Đảm bảo các constructor này khớp với định nghĩa lớp của bạn
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, "Roger Allers", 87)); // Giữ nguyên director, length
        store.addMedia(new Book(2, "The Hobbit", "Fantasy", 15.0f));
        // Giữ nguyên constructor CD từ code gốc của bạn (id, title, category, cost, director, length, artist)
        store.addMedia(new CompactDisc(3, "Abbey Road", "Rock", 12.5f, "George Martin", 47, "The Beatles"));

        // Khởi chạy giao diện đồ họa StoreManagerScreen thay vì gọi showMenu()
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Tạo đối tượng màn hình quản lý, truyền store vào
                StoreManagerScreen screen = new StoreManagerScreen(store);
                // Hiển thị màn hình
                screen.setVisible(true);
            }
        });

      
    }


} 

