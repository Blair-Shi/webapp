package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkdownResultPage implements Page {

	private final String query;
	private final String answer;

	public MarkdownResultPage(String query, String answer) {
		this.query = query;
		this.answer = answer;
	}

	public void writeTo(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setHeader("Content-Disposition", 
				"attachment;filename=" + query.replace(' ', '-') + ".md");
		PrintWriter writer = resp.getWriter();
		writeMarkdownFile(writer, query, answer);
	}

	static void writeMarkdownFile(PrintWriter writer, String query, String answer) {
		if (answer == null || answer.isEmpty()) {
			writer.println("#Sorry");
			writer.write("Sorry, we didn't understand " + query);
		} else {
			writer.println("#" + query + "\n" + answer);
		}
	}
}
