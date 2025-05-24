package hust.soict.dsai.aims.media;

import java.util.Comparator;
import java.util.Objects; // Import Objects for equals and hashCode

public abstract class Media implements Comparable<Media> { // Implement Comparable
    private int id;
    private String title;
    private String category;
    private float cost;

    private static int nbMedia = 0; // Để tự động tạo id

    // Comparators (Tùy chọn, nhưng hữu ích cho việc sắp xếp linh hoạt)
    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
        new Comparator<Media>() {
            @Override
            public int compare(Media m1, Media m2) {
                int titleCompare = m1.getTitle().compareTo(m2.getTitle());
                if (titleCompare != 0) {
                    return titleCompare;
                }
                return Float.compare(m1.getCost(), m2.getCost());
            }
        };

    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
        new Comparator<Media>() {
            @Override
            public int compare(Media m1, Media m2) {
                int costCompare = Float.compare(m1.getCost(), m2.getCost());
                if (costCompare != 0) {
                    return costCompare;
                }
                return m1.getTitle().compareTo(m2.getTitle());
            }
        };


    // Constructors
    public Media(String title) {
        this.title = title;
        this.id = ++nbMedia;
    }

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.id = ++nbMedia;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    // Phương thức equals() theo yêu cầu của Phần 12 [cite: 234]
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Nếu cùng một đối tượng trong bộ nhớ
        }
        if (obj == null) { // Kiểm tra null [cite: 234]
            return false;
        }
        // Kiểm tra ClassCastException bằng instanceof [cite: 234]
        if (!(obj instanceof Media)) { 
            return false;
        }
        Media otherMedia = (Media) obj;
        // Hai media bằng nhau nếu có cùng title và cost [cite: 234]
        return Objects.equals(this.title, otherMedia.title) && 
               Float.compare(this.cost, otherMedia.cost) == 0;
    }

   
    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }


    
    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare Media to null.");
        }
        // So sánh dựa trên title
        int titleComparison = this.title.compareToIgnoreCase(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        }
        // Nếu title giống nhau, so sánh dựa trên cost
        return Float.compare(this.cost, other.cost);
    }
    
    // Phương thức toString() (tùy chỉnh nếu cần)
    @Override
    public String toString() {
        return "Id: " + id + " - Media: " + title + " - Category: " + category + " - Cost: " + cost + "$";
    }
}