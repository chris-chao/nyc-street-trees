
/**
 * The NYCStreetTrees class is the main driver that opens and reads the data file and obtains user input.
 * @author christopherchao
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*The program should run in a loop that allows the user to check popularity of different tree names. On each iteration, the user should be
prompted to enter either a name (for which the program computes the results) or the word ”quit” (any case of letters should work) to
indicate the termination of the program.*/

public class NYCStreetTrees {
	public static void main(String[] args) {

		// csvReader
		TreeList treeList = new TreeList();

		// String csvFile ="/Users/christopherchao/documents/datastructures/Project 1/all.csv";
		String csvFile = args[0];
		if (csvFile == null || csvFile.isEmpty()) {
			System.err.println("Usage Error: the program expects file name as an argument.");
			return;
		}

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				if (lineCount == 0) {
					lineCount++;
					continue;
				}

				// use comma as separator
				List<String> parsedLine = csvReader.splitCSVLine(line);

				try {
					/*
					int test = Integer.parseInt(parsedLine.get(0));
					System.out.println(0);*/
					//creates new tree object storing data from each line
					Tree tree = new Tree(Integer.parseInt(parsedLine.get(0)), Integer.parseInt(parsedLine.get(3)),
							parsedLine.get(6), parsedLine.get(7), parsedLine.get(9),
							Integer.parseInt(parsedLine.get(25)), parsedLine.get(29),
							Double.parseDouble(parsedLine.get(39)), Double.parseDouble(parsedLine.get(40)));

					//adds each object to ArrayList
					treeList.add(tree);

				} catch (IllegalArgumentException iae) {
					System.err.println("Data in csv file is not valid.");
					return;
				}

			}

		} catch (IOException e) {
			System.err.println("Error: the file " + csvFile + " cannot be opened");
			return;
		}

		Scanner input = new Scanner(System.in);
		String userInput = "";

		//main loop
		while (userInput != "quit") {
			System.out.println("Enter the tree species to learn more about it ('quit' to stop):");
			userInput = input.nextLine();
			if (userInput.toLowerCase().equals("quit")) {
				System.out.println("Goodbye.");
				break;
			} else {
				treeList.getMatchingSpecies(userInput);
				System.out.println();
			}

		}
		input.close();

	}

}