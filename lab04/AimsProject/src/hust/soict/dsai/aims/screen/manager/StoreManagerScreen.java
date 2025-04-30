package hust.soict.dsai.aims.screen.manager;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Media;

import hust.soict.dsai.aims.screen.manager.MediaStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.ArrayList; 
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class StoreManagerScreen extends JFrame {
    private Store store;
    private JPanel centerPanel;

    private AddBookToStoreScreen addBookScreen;
    private AddDigitalVideoDiscToStoreScreen addDVDScreen;
    private AddCompactDiscToStoreScreen addCDScreen;


    public StoreManagerScreen(Store store) {
        this.store = store;

        addBookScreen = new AddBookToStoreScreen(store);
        addDVDScreen = new AddDigitalVideoDiscToStoreScreen(store);
        addCDScreen = new AddCompactDiscToStoreScreen(store);


        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        centerPanel = createCenter();
        cp.add(centerPanel, BorderLayout.CENTER);

        setTitle("Store Manager");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookScreen.setVisible(true);
            }
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCDScreen.setVisible(true);
            }
        });
        smUpdateStore.add(addCDItem);

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDVDScreen.setVisible(true);
            }
        });
        smUpdateStore.add(addDVDItem);

        menu.add(smUpdateStore);
        menu.addSeparator();

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreManagerScreen.this.setVisible(true);
                addBookScreen.setVisible(false);
                addCDScreen.setVisible(false);
                addDVDScreen.setVisible(false);
                refreshStoreView();
            }
        });
        menu.add(viewStoreItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        
        List<Media> mediaInStore = store.getItemsInStore(); 

        int numColumns = 3;
        
        int numItems = (mediaInStore != null) ? mediaInStore.size() : 0;
        int numRows = (numItems == 0) ? 1 : (int) Math.ceil((double) numItems / numColumns);

        center.setLayout(new GridLayout(numRows, numColumns, 3, 3));

        if (mediaInStore != null) {
            for (Media media : mediaInStore) {
                if (media != null) {
                  
                   MediaStore cell = new MediaStore(media);
                   center.add(cell);
                }
            }
        }

        int totalCells = numRows * numColumns;
        int actualAddedItems = center.getComponentCount();
        for (int i = actualAddedItems; i < totalCells; i++) {
            JPanel emptyCell = new JPanel();
            emptyCell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            center.add(emptyCell);
        }

        return center;
    }

    void refreshStoreView() {
        Container cp = getContentPane();
        if (centerPanel != null) {
            cp.remove(centerPanel);
        }
        centerPanel = createCenter(); // Tạo lại panel với dữ liệu mới nhất
        cp.add(centerPanel, BorderLayout.CENTER);
        cp.revalidate();
        cp.repaint();
        System.out.println("Store view refreshed.");
    }

}