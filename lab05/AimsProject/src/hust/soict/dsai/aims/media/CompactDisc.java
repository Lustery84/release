package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException; // Import PlayerException
import java.util.ArrayList;
import java.util.Iterator; // [cite: 223]
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();
    // private int length; // Tính từ tổng các track, hoặc là một thuộc tính riêng của CD (Disc)


    public CompactDisc(String title, String category, float cost, String artist) {
        super(title, category, cost);
        this.artist = artist;
    }
    
    public CompactDisc(String title, String category, String director, int length, float cost, String artist) {
        super(title, category, director, length, cost); // Giả sử Disc có constructor này
        this.artist = artist;
    }


    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
        } else {
            System.out.println("Track is already in the CD.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
        } else {
            System.out.println("Track is not in the CD.");
        }
    }

    @Override
    public int getLength() {
    

        // Nếu không có length riêng cho CD, tính tổng các track
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException { // [cite: 219, 222]
        if (this.getLength() > 0) { // [cite: 219, 222]
            System.out.println("Playing CD: " + this.getTitle() + " - Artist: " + this.artist);
            System.out.println("CD Total Length: " + this.getLength());
            
            java.util.Iterator<Track> iter = tracks.iterator(); // [cite: 223]
            Track nextTrack; // [cite: 223]
            boolean hasErrorInTrack = false; // Cờ để kiểm tra lỗi track
            ArrayList<String> trackErrors = new ArrayList<>();

            while (iter.hasNext()) { // [cite: 223]
                nextTrack = iter.next(); // [cite: 223]
                try {
                    nextTrack.play(); // [cite: 223]
                } catch (PlayerException e) { // [cite: 221, 223]
                 
                    System.err.println("Error playing track: " + nextTrack.getTitle() + " - " + e.getMessage()); // [cite: 213] (System.err.println)
                  
                    hasErrorInTrack = true;
                    trackErrors.add("Track '" + nextTrack.getTitle() + "': " + e.getMessage());
                }
            }

            if (hasErrorInTrack) {
                StringBuilder errorMessage = new StringBuilder("ERROR: One or more tracks in CD '");
                errorMessage.append(this.getTitle()).append("' could not be played:\n");
                for(String error : trackErrors){
                    errorMessage.append("- ").append(error).append("\n");
                }
                throw new PlayerException(errorMessage.toString());
            }

        } else {
            System.err.println("ERROR: CD length is non-positive!"); // [cite: 213]
            throw new PlayerException("ERROR: CD length is non-positive!"); // [cite: 224]
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CD - Id: ").append(getId()).append(" - Title: ").append(getTitle());
        sb.append(" - Category: ").append(getCategory());
        if (getDirector() != null && !getDirector().isEmpty()) {
             sb.append(" - Director: ").append(getDirector());
        }
        sb.append(" - Artist: ").append(artist);
        sb.append(" - Length: ").append(getLength());
        sb.append(" - Price: ").append(getCost()).append("$");
        if (!tracks.isEmpty()) {
            sb.append("\nTracks:\n");
            for (Track t : tracks) {
                sb.append("\t").append(t.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}