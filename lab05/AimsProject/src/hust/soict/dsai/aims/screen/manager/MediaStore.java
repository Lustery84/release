package hust.soict.dsai.aims.screen.manager; 

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable; 

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MediaStore extends JPanel {
    private Media media; 
    public MediaStore(Media media) {
        this.media = media;
        // Sử dụng BoxLayout để sắp xếp các thành phần con theo chiều dọc (Y_AXIS)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Tạo JLabel để hiển thị tiêu đề của Media
        JLabel title = new JLabel(media.getTitle());
        // Đặt font chữ cho tiêu đề
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        // Căn giữa tiêu đề theo chiều ngang
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Tạo JLabel để hiển thị giá của Media
        JLabel cost = new JLabel("" + media.getCost() + " $");
        // Căn giữa giá theo chiều ngang
        cost.setAlignmentX(CENTER_ALIGNMENT);

        // Tạo JPanel con để chứa các nút tương tác (ví dụ: nút Play)
        JPanel container = new JPanel();
        // Sử dụng FlowLayout để các nút nằm trên cùng một hàng và căn giữa
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Kiểm tra xem Media có phải là một đối tượng Playable không
        if (media instanceof Playable) {
            // Nếu là Playable, tạo nút "Play"
            JButton playButton = new JButton("Play");
            // Thêm ActionListener cho nút Play (Xử lý sự kiện cơ bản cho phần 3.2)
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Tạo và hiển thị một JDialog đơn giản khi nhấn nút Play
                    JDialog playDialog = new JDialog();
                    playDialog.setTitle("Playing " + media.getTitle());
                    playDialog.setSize(300, 200);
                    // Hiển thị dialog ở giữa thành phần MediaStore đã nhấn nút
                    playDialog.setLocationRelativeTo(MediaStore.this);
                    JLabel playingLabel = new JLabel("Playing media: " + media.getTitle(), SwingConstants.CENTER);
                    playDialog.add(playingLabel);
                    playDialog.setModal(true); // Chặn tương tác với cửa sổ chính khi dialog mở
                    playDialog.setVisible(true);
                }
            });
            // Thêm nút Play vào JPanel container
            container.add(playButton);
        }

        // Thêm các thành phần vào JPanel MediaStore theo thứ tự
        this.add(Box.createVerticalGlue()); // Thêm khoảng trống linh hoạt ở trên cùng
        this.add(title);                    // Thêm tiêu đề
        this.add(cost);                     // Thêm giá
        this.add(Box.createVerticalGlue()); // Thêm khoảng trống linh hoạt ở giữa
        this.add(container);                // Thêm panel chứa các nút

        // Đặt đường viền màu đen xung quanh JPanel MediaStore
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
