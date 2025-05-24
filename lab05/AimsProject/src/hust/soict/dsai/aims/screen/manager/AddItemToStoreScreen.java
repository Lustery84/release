package hust.soict.dsai.aims.screen.manager;

import hust.soict.dsai.aims.store.Store;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public abstract class AddItemToStoreScreen extends JFrame {

    protected Store store;
    protected JPanel mainPanel;
    // Thêm tfId vào đây để các lớp con có thể truy cập
    protected JTextField tfId, tfTitle, tfCategory, tfCost;

    public AddItemToStoreScreen(Store store, String screenTitle) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createMenuBar(), BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addCommonFields();
        addSpecificFields();
        addButtons();

        cp.add(mainPanel, BorderLayout.CENTER);

        setTitle(screenTitle);
        // Đặt kích thước cố định hoặc dùng pack() nếu muốn tự điều chỉnh
        // setSize(500, 400);
        pack(); // Tự động điều chỉnh kích thước dựa trên nội dung
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Chỉ ẩn cửa sổ
        setVisible(false);
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        JMenuItem addCDItem = new JMenuItem("Add CD");
        JMenuItem addDVDItem = new JMenuItem("Add DVD");

        // Tạm thời không cần action listener phức tạp ở đây
        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);
        menu.add(smUpdateStore);
        menu.addSeparator();
        JMenuItem viewStoreItem = new JMenuItem("View Store");
        viewStoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItemToStoreScreen.this.setVisible(false);
                // Cần cơ chế để gọi lại StoreManagerScreen.setVisible(true)
                // Hoặc đơn giản là người dùng tự quay lại cửa sổ chính.
            }
        });
        menu.add(viewStoreItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    // Phương thức này giờ bao gồm cả ID
    void addCommonFields() {
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("ID:         "));
        tfId = new JTextField(30);
        idPanel.add(tfId);
        mainPanel.add(idPanel);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(new JLabel("Title:      "));
        tfTitle = new JTextField(30);
        titlePanel.add(tfTitle);
        mainPanel.add(titlePanel);

        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.add(new JLabel("Category:"));
        tfCategory = new JTextField(30);
        categoryPanel.add(tfCategory);
        mainPanel.add(categoryPanel);

        JPanel costPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        costPanel.add(new JLabel("Cost:       "));
        tfCost = new JTextField(30);
        costPanel.add(tfCost);
        mainPanel.add(costPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    abstract void addSpecificFields();

    void addButtons() {
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Item to Store");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemAction();
            }
        });
        buttonPanel.add(addButton);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
    }

    abstract void addItemAction();

}
