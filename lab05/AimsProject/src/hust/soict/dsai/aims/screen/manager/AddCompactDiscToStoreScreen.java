package hust.soict.dsai.aims.screen.manager;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track; 
import hust.soict.dsai.aims.store.Store;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    private JTextField tfArtist;
    private JTextField tfTracks; 

    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add CD to Store");
      
    }

    @Override
    void addSpecificFields() {
        JPanel artistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        artistPanel.add(new JLabel("Artist:    ")); // Căn chỉnh label
        tfArtist = new JTextField(30);
        artistPanel.add(tfArtist);
        mainPanel.add(artistPanel, mainPanel.getComponentCount() - 2);

     
    }

    @Override
    void addItemAction() {
        String idStr = tfId.getText();
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        String costStr = tfCost.getText();
        String artist = tfArtist.getText();
       
        if (idStr.isEmpty() || title.isEmpty() || costStr.isEmpty() || artist.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID, Title, Cost, and Artist cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
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

         
            CompactDisc newCd = new CompactDisc(id, title, category, cost, artist, id, artist);

         

            store.addMedia(newCd);

            JOptionPane.showMessageDialog(this, "CD added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            tfId.setText("");
            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfArtist.setText("");
           

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID or Cost format. Please enter numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
