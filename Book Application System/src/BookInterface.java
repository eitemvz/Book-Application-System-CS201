public interface BookInterface {

    // Default method: display last 6 books
    default void displayLastSixBooks(java.util.List<Book> allBooks) {
        System.out.println("---- Last Six Books ----");
        int start = Math.max(0, allBooks.size() - 6);
        for (int i = start; i < allBooks.size(); i++) {
            System.out.println(allBooks.get(i));
        }
    }

    int numBookPerGenre(java.util.List<Book> allBooks);
    double getTotalCost(java.util.List<Book> allBooks);
}