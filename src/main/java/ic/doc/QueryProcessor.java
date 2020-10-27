package ic.doc;

public class QueryProcessor {

	public String process(String query) {
		StringBuilder results = new StringBuilder();
		if (query.toLowerCase().contains("shakespeare")) {
			results.append("William Shakespeare (26 April 1564 - 23 April 1616) was an\n" +
					"English poet, playwright, and actor, widely regarded as the greatest\n" +
					"writer in the English language and the world's pre-eminent dramatist. \n");
			results.append(System.lineSeparator());
		}

		if (query.toLowerCase().contains("asimov")) {
			results.append("Isaac Asimov (2 January 1920 - 6 April 1992) was an\n" +
					"American writer and professor of Biochemistry, famous for\n" +
					"his works of hard science fiction and popular science. \n");
			results.append(System.lineSeparator());
		}

		if (query.toLowerCase().contains("blair")) {
			results.append("Blair Shi (11 January 1999 - Present) is a 2nd Year student,\n" +
					"studying Mathematics and Computer Science in Imperial College\n" +
					"London. She likes hotpot very much. \n");
			results.append(System.lineSeparator());
		}

		if (query.toLowerCase().contains("qilin")) {
			results.append("Qilin Huang (AKA Mo Lin) (21 December 2001 - Present) is a famous \n" +
					"Chinese young singer. He is also a member of Yian Music Club. His first song,\n" +
					"\"Linmoqingchun\", achieved great success in every awards. By the way, he is \n" +
					"Blair's favorite idol!\n");
			results.append(System.lineSeparator());
		}

		if (query.toLowerCase().contains("bloomberg")) {
			results.append("Michael Bloomberg (14 February 1942 - Present) is an American businessman,\n" +
					"politician, author, and philanthropist. As of June 2018, his net worth was\n" +
					"estimated at $51.8 billion, making him the 8th-richest person in the United\n" +
					"States and the 11th richest person in the world.\n");
			results.append(System.lineSeparator());
		}
		return results.toString();
	}
}
