

class Testsuite
{
    public static void main(String[] args) {
        Bookstore bookstore;
        try {
            bookstore = new Bookstore();
            bookstore.connect();
            for(Book book : bookstore.getBooksByAuthor("")) {
                System.out.println(book.title);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

