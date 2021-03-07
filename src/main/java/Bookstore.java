
import java.util.ArrayList; 
import java.sql.*;
import java.util.logging.Logger;

class Book
{
    String title;
    String author;
    float price;
    int qty;

    public Book(String title, String author, float price, int qty)
    {
        this.title = title;
        this.author = author;
        this.price = price;
        this.qty = qty;
    }
}

class Bookstore
{
     Connection conn;
     Logger log = Logger.getLogger("bookstore");

     public void connect() throws IllegalAccessException, ClassNotFoundException, InstantiationException, SQLException {
         //Class.forName("com.mysql.jdbc.Driver").newInstance();
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://db:3306/ebookshop", "java", "3kla912kj");
     }

     public ArrayList<Book> getBooksByAuthor(String authorQuery) throws Exception{
         Statement stmt = conn.createStatement();
         String sqlStr = "SELECT * FROM books WHERE author LIKE '%" + authorQuery + "%'";
         log.info(sqlStr);
         ResultSet rset = stmt.executeQuery(sqlStr);
         ArrayList<Book> resultList = new ArrayList<Book>();
         while (rset.next()) {
             Book book = new Book(rset.getString("title"),
                                  rset.getString("author"),
                                  rset.getFloat("price"),
                                  rset.getInt("qty"));
             resultList.add(book);
         }
         rset.close();
         stmt.close();
         conn.close();
         
         return resultList;
    }
}
