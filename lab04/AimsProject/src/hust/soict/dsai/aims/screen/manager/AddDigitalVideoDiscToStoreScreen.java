package hust.soict.dsai.aims.screen.manager;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import java.awt.*;
import javax.swing.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store, "Add DVD to Store");
        // Không cần gọi lại các phương thức add nữa
    }

    @Override
    void addSpecificFields() {
        JPanel directorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        directorPanel.add(new JLabel("Director: "));
        tfDirector = new JTextField(30);
        directorPanel.add(tfDirector);
        mainPanel.add(directorPanel, mainPanel.getComponentCount() - 2); // Chèn trước Glue và Button

        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.add(new JLabel("Length:   "));
        tfLength = new JTextField(30);
        lengthPanel.add(tfLength);
        mainPanel.add(lengthPanel, mainPanel.getComponentCount() - 2); // Chèn trước Glue và Button
    }

    @Override
    void addItemAction() {
        String idStr = tfId.getText();
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        String costStr = tfCost.getText();
        String director = tfDirector.getText();
        String lengthStr = tfLength.getText();

        if (idStr.isEmpty() || title.isEmpty() || costStr.isEmpty() || director.isEmpty() || lengthStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields (ID, Title, Cost, Director, Length) cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
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

            int length = Integer.parseInt(lengthStr);
             if (length <= 0) {
                 JOptionPane.showMessageDialog(this, "Length must be a positive integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }

            // Đảm bảo thứ tự tham số đúng với constructor DigitalVideoDisc của bạn
            DigitalVideoDisc newDvd = new DigitalVideoDisc(id, title, category, cost, director, length);

            store.addMedia(newDvd);

            JOptionPane.showMessageDialog(this, "DVD added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            tfId.setText("");
            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfDirector.setText("");
            tfLength.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID, Cost, or Length format. Please enter numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
