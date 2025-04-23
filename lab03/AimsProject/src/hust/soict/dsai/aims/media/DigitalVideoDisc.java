package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisc(String title, String director, String string, int length, float f) {
        super(length, title, director, cost, director, length); // Gọi constructor của lớp Disc
        this.length = length;
    }
    public DigitalVideoDisc(int id, String title, String category, float cost, String director, int length) {
        super(id, title, category, cost, director, length);
        this.director = director;
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "DigitalVideoDisc{" +
                "director='" + director + '\'' +
                ", length=" + length +
                "} " + super.toString();
    }

    @Override
    public void play() {
        if (getLength() > 0) {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("DVD length: " + getLength());
        } else {
            System.out.println("ERROR: DVD " + getTitle() + " cannot be played.");
        }
    }
}