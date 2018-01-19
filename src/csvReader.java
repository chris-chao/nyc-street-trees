import java.util.ArrayList;

/**
 * Splits the given line of a CSV file according to commas and double quotes
 * (double quotes are used to surround multi-word entries that may contain
 * commas).
 *
 * @param textLine
 *            line of text to be parsed
 * @return an ArrayList object containing all individual entries/tokens found on
 *         the line.
 */
public class csvReader {
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;

		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				} else {
					insideQuotes = true;
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if (insideQuotes || insideEntry) {
					// add it to the current entry
					nextWord.append(nextChar);
				} else { // skip all spaces between entries
					continue;
				}
			} else if (nextChar == ',') {
				if (insideQuotes) // comma inside an entry
					nextWord.append(nextChar);
				else {
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}
		}
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
		return entries;
	}
}
