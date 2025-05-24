package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException; // Import PlayerException

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

    @Override
    public void play() throws PlayerException { // [cite: 217]
        if (this.getLength() > 0) { // [cite: 212]
            // System.out.println("Playing track: " + this.getTitle());
            // System.out.println("Track length: " + this.getLength());
            System.out.println("Playing Track: " + getTitle() + " - Length: " + getLength());
        } else {
            System.err.println("ERROR: Track length is non-positive for track: " + this.getTitle()); // [cite: 213]
            throw new PlayerException("ERROR: Track length is non-positive for track: " + this.getTitle()); // [cite: 213]
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return length == track.length && title.equals(track.title);
    }

    @Override
    public String toString() {
        return "Track: " + title + " - Length: " + length;
    }
}