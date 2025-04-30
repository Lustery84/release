package hust.soict.dsai.aims.screen.manager;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store, "Add Book to Store");
        // Không cần gọi lại các phương thức add nữa vì lớp cha đã xử lý ID
    }

    @Override
    void addSpecificFields() {
        JPanel authorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        authorsPanel.add(new JLabel("Authors: "));
        tfAuthors = new JTextField(30);
        authorsPanel.add(tfAuthors);
        // Thêm vào vị trí phù hợp, ví dụ sau các trường chung
        mainPanel.add(authorsPanel, mainPanel.getComponentCount() - 2); // Chèn trước VerticalGlue và ButtonPanel
    }

    @Override
    void addItemAction() {
        String idStr = tfId.getText(); // tfId được kế thừa từ cha
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        String costStr = tfCost.getText();
        String authorsStr = tfAuthors.getText();

        if (idStr.isEmpty() || title.isEmpty() || costStr.isEmpty() || authorsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID, Title, Cost, and Authors cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
             if (id <= 0) {
                 JOptionPane.showMessageDialog(this, "ID must be a positive integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            if (store.searchMediaById(id) != null) {
                 JOptionPane.showMessageDialog(this, "Media with ID " + id + " already exists.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }

            float cost = Float.parseFloat(costStr);
            if (cost < 0) {
                 JOptionPane.showMessageDialog(this, "Cost cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }

            String[] authorsArray = authorsStr.split(",");
            List<String> authorsList = new ArrayList<>();
            for (String author : authorsArray) {
                String trimmedAuthor = author.trim();
                if (!trimmedAuthor.isEmpty()) {
                    authorsList.add(trimmedAuthor);
                }
            }
            if (authorsList.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "At least one author must be provided.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }

            Book newBook = new Book(id, title, category, cost);
            for (String author : authorsList) {
                newBook.addAuthor(author);
            }

            store.addMedia(newBook);

            JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            tfId.setText("");
            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfAuthors.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID or Cost format. Please enter numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
