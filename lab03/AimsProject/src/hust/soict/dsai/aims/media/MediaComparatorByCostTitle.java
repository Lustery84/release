package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media o1, Media o2) {
        int costComparison = Float.compare(o2.getCost(), o1.getCost());
        if (costComparison != 0) {
            return costComparison; // Sắp xếp theo giá (giảm dần)
        }
        return o1.getTitle().compareToIgnoreCase(o2.getTitle()); // Nếu giá giống nhau, sắp xếp theo tiêu đề
    }
}