package ic.doc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryProcessorTest {

	QueryProcessor queryProcessor = new QueryProcessor();

	@Test
	public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
		assertThat(queryProcessor.process("test"), is(""));
	}

	@Test
	public void knowsAboutShakespeare() throws Exception {
		assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
	}

	@Test
	public void knowsAboutAsimov() throws Exception {
		assertThat(queryProcessor.process("Asimov"), containsString("science fiction"));
	}

	@Test
	public void knowsAboutBlair() throws Exception {
		assertThat(queryProcessor.process("Blair"), containsString("student"));
	}

	@Test
	public void knowsAboutQilin() throws Exception {
		assertThat(queryProcessor.process("Qilin"), containsString("singer"));
	}

	@Test
	public void knowsAboutBloomberg() throws Exception {
		assertThat(queryProcessor.process("bloomberg"), containsString("American"));
	}

	@Test
	public void isNotCaseSensitive() throws Exception {
		assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
	}
}
