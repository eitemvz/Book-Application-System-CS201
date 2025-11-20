import java.util.ArrayList;
import java.util.List;

public abstract class Book implements BookInterface {

    protected String title;
    protected String author;
    protected String genre;
    protected double cost;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        cost = getCost();
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    // Abstract method to compute cost (printed or audio)
    public abstract double getCost();

    // Total cost of ALL books (implemented from interface)
    @Override
    public double getTotalCost(java.util.List<Book> allBooks) {
        double cost = 0;

        for (Book book : allBooks) {
            cost += book.getCost(); // child classes will override behavior
        }

        return cost;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public int numBookPerGenre(java.util.List<Book> allBooks) {
        List<String> genres = new ArrayList<>();

        for (Book b : allBooks) {
            if (!genres.contains(b.getGenre())) {
                genres.add(b.getGenre());
            }
        }

        for (String g : genres) {
            int count = 0;

            for (Book allBook : allBooks) {
                if (g.equalsIgnoreCase(allBook.getGenre())) {
                    count++;
                }
            }
            System.out.println("Books in genre '" + g + "': " + count);
        }

        return genres.size();
    }

    @Override
    public String toString() {
        return "Title: " + title +
                ", Author: " + author +
                ", Genre: " + genre;
    }
}