import java.io.*;
import java.util.*;

public class Main {

    private static final List<Book> allBooks = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome! What file would you like to open?");
        System.out.print("File: ");

        String file = sc.nextLine();

        initializeLists(file);

        boolean running = true;

        while (running) {
            System.out.println("\n=== BOOK APPLICATION ===");
            System.out.println("1.  Add Printed Book");
            System.out.println("2.  Add Audio Book");
            System.out.println("3.  Display Last 6 Books");
            System.out.println("4.  Display Last 3 Printed Books");
            System.out.println("5.  Display Last 3 Audio Books");
            System.out.println("6.  Show Total Cost of All Books");
            System.out.println("7.  Count Books by Genre");
            System.out.println("8.  Save Books to File");
            System.out.println("9.  Read Books from File");
            System.out.println("10. Show Average Length of All Audio Books");
            System.out.println("11. Show Average Pages of All Printed Books");
            System.out.println("0.  Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addPrintedBook();
                case 2 -> addAudioBook();
                case 3 -> displayLastSix();
                case 4 -> PrintedBook.displayLastThreePrinted();
                case 5 -> AudioBook.displayLastThreeAudio();
                case 6 -> showTotalCost();
                case 7 -> countGenres();
                case 8 -> saveToFile(file);
                case 9 -> readFromFile(file);
                case 10 -> averageLength();
                case 11 -> averagePages();
                case 0 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void initializeLists(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineDivided = line.trim().split(",");

                if (lineDivided.length < 7) continue;

                String title = lineDivided[0].trim();
                String author = lineDivided[1].trim();
                String genre = lineDivided[2].trim();
                String type = lineDivided[6].trim();

                int pages;
                int length;

                if (type.equalsIgnoreCase("printed")) {
                    pages = Integer.parseInt(lineDivided[5]);
                    PrintedBook b = new PrintedBook(title, author, genre, pages);
                    allBooks.add(b);
                } else if (type.equalsIgnoreCase("audio")) {
                    length = Integer.parseInt(lineDivided[4]);
                    AudioBook b = new AudioBook(title, author, genre, length);
                    allBooks.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    private static void addPrintedBook() {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Pages: ");
        int pages = sc.nextInt();
        sc.nextLine();

        PrintedBook pb = new PrintedBook(title, author, genre, pages);
        allBooks.add(pb);
        System.out.println("Printed book added!");
    }

    private static void addAudioBook() {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Length (minutes): ");
        int length = sc.nextInt();
        sc.nextLine();

        AudioBook ab = new AudioBook(title, author, genre, length);
        allBooks.add(ab);
        System.out.println("Audio book added!");
    }

    private static void displayLastSix() {
        if (allBooks.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        allBooks.getFirst().displayLastSixBooks(allBooks);
    }

    private static void showTotalCost()  {
        System.out.println("Total cost: $" + allBooks.getFirst().getTotalCost(allBooks));
    }

    private static void countGenres() {
            allBooks.getFirst().numBookPerGenre(allBooks);
    }

    private static void saveToFile(String file) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Book b : allBooks) {
                int pageOrLength;
                int other = 0;

                if (b.toString().contains("AUDIO")) {
                    AudioBook ab = (AudioBook) b;
                    pageOrLength = ab.getLengthMinutes();
                    pw.printf("%s,%s,%s,%s,%s,%s,AUDIO\n", b.title, b.author, b.genre, b.getCost(), pageOrLength, other);
                }
                else if (b.toString().contains("PRINTED")) {
                    PrintedBook pb = (PrintedBook) b;
                    pageOrLength = pb.getPages();
                    pw.printf("%s,%s,%s,%s,%s,%s,PRINTED\n", b.title, b.author, b.genre, b.getCost(), other, pageOrLength);
                }
            }
            System.out.println("Books saved to " + file + "!");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    private static void readFromFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("\n--- File Contents ---");
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    private static void averageLength() {
        System.out.printf("Average length of audio books: %.2f mins\n", AudioBook.getAverageLength());
    }

    private static void averagePages() {
        System.out.printf("Average page amount of printed books: %.2f\n", PrintedBook.getAveragePages());
    }
}