
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class QueryServlet extends HttpServlet
{

    public void init() throws ServletException
    {
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
              throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        try {
            String[] authors = request.getParameterValues("author");
            String author = authors[0];
            author = author.replaceAll("<script>", "");
            author = author.replaceAll("</script>", "");

            Bookstore bookstore = new Bookstore();
            bookstore.connect();

            writer.println("<p>searching for '" + author + "'");
            writer.println("<table border=\"1\">");
            writer.println("<tr><th>Title</th><th>Author</th><th>Price</th><th>Anz</th></tr>");
            if (authors != null) {
                for(Book book : bookstore.getBooksByAuthor(author)) {
                    writer.println("<tr><td>");
                    writer.println(book.title);
                    writer.println("</td><td>");
                    writer.println(book.author);
                    writer.println("</td><td>");
                    writer.println(book.price);
                    writer.println("</td><td>");
                    writer.println(book.qty);
                    writer.println("</td></tr>");
                }
            }
            writer.println("</table>");
        } catch (Exception e) {
            writer.println(e.toString());
        }
    }

    public void destroy()
    {
        // do nothing.
    }
}
