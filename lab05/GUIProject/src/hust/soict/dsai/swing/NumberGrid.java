package hust.soict.dsai.swing;

// Import các lớp cần thiết từ AWT và Swing
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    // Constructor để thiết lập GUI và xử lý sự kiện
    public NumberGrid() {
        // Khởi tạo và cấu hình ô hiển thị JTextField
        tfDisplay = new JTextField();
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // Căn lề phải
        tfDisplay.setEditable(false); // Không cho phép sửa trực tiếp trên ô hiển thị

        // Tạo JPanel chứa các nút bấm với GridLayout 4x3
        // Thêm khoảng cách 5px giữa các hàng và cột của grid
        JPanel panelButtons = new JPanel(new GridLayout(4, 3, 5, 5));
        addButtons(panelButtons); // Gọi phương thức để thêm các nút vào panel

        // Lấy content pane của JFrame và đặt layout là BorderLayout
        Container cp = getContentPane();
        // Thêm khoảng cách 5px giữa các vùng của border layout
        cp.setLayout(new BorderLayout(5, 5));
        // Thêm ô hiển thị vào vị trí NORTH
        cp.add(tfDisplay, BorderLayout.NORTH);
        // Thêm panel chứa các nút vào vị trí CENTER
        cp.add(panelButtons, BorderLayout.CENTER);

        // Thiết lập hoạt động khi đóng cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Đặt tiêu đề cửa sổ
        setTitle("Number Grid");
        // Tự động điều chỉnh kích thước cửa sổ cho vừa với nội dung
        pack();
        // Đặt cửa sổ ở giữa màn hình
        setLocationRelativeTo(null);
        // Hiển thị cửa sổ
        setVisible(true);
    }

    // Phương thức thêm các nút vào panel
    void addButtons(JPanel panelButtons) {
        ButtonListener btnListener = new ButtonListener(); // Tạo listener chung cho các nút

        // Thêm các nút số từ 1 đến 9
        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton("" + i); // Tạo nút với nhãn là số i
            panelButtons.add(btnNumbers[i]); // Thêm nút vào panel
            btnNumbers[i].addActionListener(btnListener); // Gán listener cho nút
        }

        // Thêm nút DEL
        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(btnListener); // Gán listener

        // Thêm nút số 0
        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(btnListener); // Gán listener

        // Thêm nút C
        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(btnListener); // Gán listener
    }

    // Lớp nội (inner class) để xử lý sự kiện nhấn nút
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand(); // Lấy nhãn của nút được nhấn
            String currentText = tfDisplay.getText(); // Lấy nội dung hiện tại của ô hiển thị

            if (button.charAt(0) >= '0' && button.charAt(0) <= '9') { // Nếu là nút số
                tfDisplay.setText(currentText + button); // Nối số vào cuối ô hiển thị
            } else if (button.equals("DEL")) { // Nếu là nút DEL
                // Kiểm tra xem ô hiển thị có nội dung hay không
                if (!currentText.isEmpty()) {
                    // Xóa ký tự cuối cùng bằng cách lấy chuỗi con
                    tfDisplay.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (button.equals("C")) { // Nếu là nút C
                // Xóa toàn bộ nội dung bằng cách đặt thành chuỗi rỗng
                tfDisplay.setText("");
            }
        }
    }

    // Phương thức main để chạy ứng dụng
    public static void main(String[] args) {
        // Đảm bảo việc tạo và hiển thị GUI được thực hiện trên Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGrid(); // Tạo một đối tượng NumberGrid để hiển thị cửa sổ
            }
        });
    }
}