package hust.soict.dsai.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Track{" +
                "title='" + title + '\'' +
                ", length=" + length +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Track)) {
            return false;
        }
        Track track = (Track) obj;
        return title.equalsIgnoreCase(track.title) && length == track.length; // So sánh tiêu đề và độ dài
    }

    @Override
    public void play() {
        if (getLength() > 0) {
            System.out.println("Playing Track: " + getTitle());
            System.out.println("Track length: " + getLength());
        } else {
            System.out.println("ERROR: Track " + getTitle() + " cannot be played.");
        }
    }
    
}