package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static ic.doc.web.MarkdownResultPage.writeMarkdownFile;

public class PDFResultPage implements Page {

	private final String query;
	private final String answer;

	public PDFResultPage(String query, String answer) {
		this.query = query;
		this.answer = answer;
	}

	public void writeTo(HttpServletResponse resp) throws IOException {
		String md = query.replace(' ', '-') + ".md";
		String pdf = query.replace(' ', '-') + ".pdf";

		resp.setContentType("application/pdf");
		resp.setHeader("Content-Disposition", "attachment;filename=" + pdf);
		PrintWriter writer = resp.getWriter();

		File tmp = new File(md);
		PrintWriter mdWriter = new PrintWriter(tmp);
		writeMarkdownFile(mdWriter, query, answer);
		mdWriter.close();

		ProcessBuilder processBuilder = new ProcessBuilder("pandoc", md, "-s", "-o", pdf);
		try {
			processBuilder.start().waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp.deleteOnExit();
		(new File(pdf)).deleteOnExit();

		StringBuilder builder = new StringBuilder();
		FileInputStream inputStream = new FileInputStream(pdf);
		byte[] buffer = inputStream.readAllBytes();
		for (byte c : buffer) {
			//Convert bytes read to unsigned integer as Java interpret output as signed.
			builder.append((char) (c < 0 ? c + 256 : c));
		}
		writer.write(builder.toString());
	}
}
