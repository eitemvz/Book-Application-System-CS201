import java.util.ArrayList;
import java.util.List;

public class AudioBook extends Book {

    private static List<AudioBook> audioBooks = new ArrayList<>();
    private int lengthMinutes;
    private double cost;

    public AudioBook(String title, String author, String genre, int lengthMinutes) {
        super(title, author, genre);
        this.lengthMinutes = lengthMinutes;

        // Each minute costs half of a printed page cost = $5
        this.cost = lengthMinutes * 5;

        audioBooks.add(this);
    }

    public void setLength(int lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
        this.cost = lengthMinutes * 5; // Recalculate cost when length changes
    }

    @Override
    public double getCost() {
        return cost;
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }

    // Average length of audiobooks
    public static double getAverageLength() {
        if (audioBooks.isEmpty()) return 0;
        int total = 0;
        for (AudioBook ab : audioBooks) {
            total += ab.lengthMinutes;
        }
        return total / (double) audioBooks.size();
    }

    // Display last 3 audiobooks
    public static void displayLastThreeAudio() {
        System.out.println("---- Last 3 Audiobooks ----");
        int start = Math.max(0, audioBooks.size() - 3);
        for (int i = start; i < audioBooks.size(); i++) {
            System.out.println(audioBooks.get(i));
        }
    }

    @Override
    public String toString() {
        return "AUDIO " +
                super.toString() +
                ", Length: " + lengthMinutes + " mins" +
                ", Cost: $" + cost;
    }
}