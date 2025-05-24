package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException; // Import PlayerException

public class DigitalVideoDisc extends Disc implements Playable {
    // Các thuộc tính và constructor đã có từ trước
    // private String director; (đã chuyển lên Disc)
    // private int length; (đã chuyển lên Disc)

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }
    
    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }


    @Override
    public void play() throws PlayerException { // [cite: 215]
        if (this.getLength() > 0) { // [cite: 212, 215]
            // System.out.println("Playing DVD: " + this.getTitle());
            // System.out.println("DVD length: " + this.getLength());
            // Trong ứng dụng GUI, việc hiển thị sẽ được xử lý ở Controller
            // Ví dụ, Controller gọi play() và nếu không có exception, nó sẽ hiển thị dialog "Playing..."
            // Nếu có exception, nó sẽ hiển thị dialog lỗi.
            // Dòng dưới đây chỉ mang tính minh họa cho logic play thành công.
            System.out.println("Playing DVD: " + getTitle() + " - Length: " + getLength());
            // Đối với GUI, có thể hiển thị một Alert ở đây nếu play thành công,
            // nhưng thường thì việc này sẽ do Controller đảm nhiệm sau khi gọi play().
        } else {
            System.err.println("ERROR: DVD length is non-positive!"); // [cite: 213] (Tài liệu yêu cầu System.err.println)
            throw new PlayerException("ERROR: DVD length is non-positive!"); // [cite: 213, 215]
        }
    }

    @Override
    public String toString() {
        return "DVD - Id: " + getId() + " - Title: " + getTitle() +
               " - Category: " + getCategory() +
               (getDirector() != null ? " - Director: " + getDirector() : "") +
               (getLength() > 0 ? " - Length: " + getLength() : "") +
               " - Price: " + getCost() + "$";
    }
}