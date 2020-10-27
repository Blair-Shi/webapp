package ic.doc;

import ic.doc.web.MarkdownResultPage;
import ic.doc.web.PDFResultPage;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;

public class ResultPageTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	private HttpServletResponse resp = context.mock(HttpServletResponse.class);

	@Test
	public void markdownFileIsCreatedInMarkdownResultPage() throws IOException {
		File tmp = new File("tmp");
		tmp.deleteOnExit();

		context.checking(new Expectations() {{
			oneOf(resp).setContentType("text/plain");
			ignoring(resp).setHeader(with(any(String.class)), with(any(String.class)));
			oneOf(resp).getWriter(); will(returnValue(new PrintWriter(tmp)));
		}});
		new MarkdownResultPage("query", "answer").writeTo(resp);

		assertTrue(new File("query.md").exists());
	}

	@Test
	public void markdownAndPDFFileAreCreatedInPDFResultPage() throws IOException {
		File tmp = new File("tmp");
		tmp.deleteOnExit();

		context.checking(new Expectations() {{
			oneOf(resp).setContentType("application/pdf");
			ignoring(resp).setHeader(with(any(String.class)), with(any(String.class)));
			oneOf(resp).getWriter(); will(returnValue(new PrintWriter(tmp)));
		}});
		new PDFResultPage("query", "answer").writeTo(resp);

		assertTrue(new File("query.md").exists());
		assertTrue(new File("query.pdf").exists());
	}
}
