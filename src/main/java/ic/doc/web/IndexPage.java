package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexPage implements Page {

	public void writeTo(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();

		// Header
		writer.println("<html>");
		writer.println("<head><title>Welcome</title></head>");
		writer.println("<body>");

		// Content
		writer.println(
				"<h1>Welcome you!!</h1>" +
				"<p>" + 
				"Enter your query in the box below: " +
				"<form>" +
				"<input type=\"text\" name=\"q\" />" +
				"<br><br>" +
				"<input type=\"radio\" name=\"r\" value=\"html\" />Show Webpage" +
				"<input type=\"radio\" name=\"r\" value=\"md\" />Download Markdown" +
				"<input type=\"radio\" name=\"r\" value=\"pdf\" />Download PDF" +
				"<br>" + 
				"<input type=\"submit\" value=\"Search\">" +
				"</form>" +
				"</p>");

		// Footer
		writer.println("</body>");
		writer.println("</html>");
	}

}
