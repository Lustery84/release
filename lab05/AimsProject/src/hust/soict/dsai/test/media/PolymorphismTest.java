package hust.soict.dsai.test.media;

import java.util.ArrayList;
import java.util.List;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class PolymorphismTest {

    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<Media>();

        // Tạo các đối tượng Media
        Media cd = new CompactDisc(1, "Abbey Road", "Rock", 15.0f, "George Martin", 47, "The Beatles");
        Media dvd = new DigitalVideoDisc(2, "Inception", "Sci-Fi", 22.0f, "Christopher Nolan", 148);
        Media book = new Book(3, "The Lord of the Rings", "Fantasy", 20.0f);

        // Thêm các đối tượng vào danh sách
        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);

        // In ra thông tin của từng Media
        for (Media m : mediae) {
            System.out.println(m.toString());
        }
    }
}