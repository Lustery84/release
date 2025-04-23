package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private String director;
    private int length;

    public Disc(int id, String title, String category, float cost, String director, int length) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Disc{" +
                "director='" + director + '\'' +
                ", length=" + length +
                "} " + super.toString();
    }
}