package ic.doc.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class ErrorPage implements Page {

	private Exception e;

	public ErrorPage(Exception e) {
		this.e = e;
	}

	public void writeTo(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();

		// Header
		writer.println("<html>");
		writer.println("<head><title>Error</title></head>");
		writer.println("<body>");

		// Content
		writer.print("<h1>A problem occurred during the process</h1><p>ERROR: " + e + "</p>");
		for (StackTraceElement element : e.getStackTrace()) {
			writer.print(element);
			writer.print("<br>");
		}
		writer.println("<p><a href=\"/\">Back to Search Page</a></p>");

		// Footer
		writer.println("</body>");
		writer.println("</html>");
	}
}
