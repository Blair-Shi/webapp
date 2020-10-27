package ic.doc;

import ic.doc.web.ErrorPage;
import ic.doc.web.HTMLResultPage;
import ic.doc.web.IndexPage;
import ic.doc.web.MarkdownResultPage;
import ic.doc.web.PDFResultPage;
import ic.doc.web.Page;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class WebServer {

	public WebServer() throws Exception {
		Server server = new Server(Integer.valueOf(System.getenv("PORT")));

		ServletHandler handler = new ServletHandler();
		handler.addServletWithMapping(new ServletHolder(new Website()), "/*");
		server.setHandler(handler);

		server.start();
	}

	static class Website extends HttpServlet {

		HashMap<String, Class<? extends Page>> resultPage = new HashMap<>() {{
			put("html", HTMLResultPage.class);
			put("md", MarkdownResultPage.class);
			put("pdf", PDFResultPage.class);
		}};

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			String query = req.getParameter("q");
			if (query == null) {
				new IndexPage().writeTo(resp);
			} else {
				try {
					query = query.isEmpty() ? "_null_" : query;
					resultPage.getOrDefault(req.getParameter("r"), HTMLResultPage.class)
						.getConstructor(String.class, String.class)
						.newInstance(query, new QueryProcessor().process(query)).writeTo(resp);
				} catch (Exception e) {
					new ErrorPage(e).writeTo(resp);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new WebServer();
	}
}

