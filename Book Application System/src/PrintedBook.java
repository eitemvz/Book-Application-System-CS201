import java.util.ArrayList;
import java.util.List;

public class PrintedBook extends Book {

    private static List<PrintedBook> printedBooks = new ArrayList<>();
    private int pages;
    private double cost;

    public PrintedBook(String title, String author, String genre, int pages) {
        super(title, author, genre);
        this.pages = pages;
        this.cost = pages * 10; // $10 per page
        printedBooks.add(this);
    }

    public void setPages(int pages) {
        this.pages = pages;
        this.cost = pages * 10; // Recalculate cost when pages change
    }

    @Override
    public double getCost() {
        return cost;
    }

    public int getPages() {
        return pages;
    }

    // Average pages of ALL printed books
    public static double getAveragePages() {
        if (printedBooks.isEmpty()) return 0;
        int totalPages = 0;
        for (PrintedBook pb : printedBooks) {
            totalPages += pb.pages;
        }
        return totalPages / (double) printedBooks.size();
    }

    // Display last 3 printed books
    public static void displayLastThreePrinted() {
        System.out.println("---- Last 3 Printed Books ----");
        int start = Math.max(0, printedBooks.size() - 3);
        for (int i = start; i < printedBooks.size(); i++) {
            System.out.println(printedBooks.get(i));
        }
    }

    @Override
    public String toString() {
        return "PRINTED " +
                super.toString() +
                ", Pages: " + pages +
                ", Cost: $" + cost;
    }
}