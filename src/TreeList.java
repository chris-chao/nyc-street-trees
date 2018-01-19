
/**
 * The TreeList class stores all the Tree objects and contains the methods that provides functionality to the program. 
 * @author christopherchao
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeList extends ArrayList<Tree> {
	private ArrayList<Tree> treeList;
	private final List<String> VALID_BOROUGHS = Arrays.asList("NYC", "Manhattan", "Bronx", "Brooklyn", "Queens",
			"Staten Island");

	/**
	 * Creates and initializes the ArrayList that stores all the Tree objects.
	 */
	public TreeList() {
		this.treeList = new ArrayList<Tree>();
	}

	/**
	 * Returns the number of trees in the csv file.
	 * 
	 * @return the size of the ArrayList.
	 */
	public int getTotalNumberOfTrees() {
		return treeList.size();

	}

	/**
	 * Adds each Tree object to the ArrayList treeList.
	 * 
	 * @param tree
	 *            an object of type tree that contains relevant data for each
	 *            individual line of the csv file
	 */
	public boolean add(Tree tree) {
		return treeList.add(tree);
	}

	/**
	 * Finds the total number of tree species specified by the user in the
	 * ArrayList
	 * 
	 * @param speciesName
	 *            the user inputed species name
	 * @return the total number of tree species specified by the user in the
	 *         ArrayList
	 */
	public int getCountByTreeSpecies(String speciesName) {
		int count = 0;
		for (int i = 0; i < treeList.size(); i++) {
			if (treeList.get(i).getSpc().toLowerCase().contains(speciesName.toLowerCase())) {
				count++;
			}
		}
		return count;

	}

	/**
	 * Finds the total number of trees in the specified borough.
	 * 
	 * @param boroName
	 *            one of the five boroughs in the array BOROUGHS
	 * @return the total number of trees in the specified borough
	 */
	public int getCountByBorough(String boroName) {
		int count = 0;
		for (int i = 0; i < treeList.size(); i++) {
			if (treeList.get(i).getBoro().toLowerCase().contains(boroName.toLowerCase())) {
				count++;
			}
		}
		return count;

	}

	/**
	 * Finds the total number of tree species specified by the user in the
	 * specified borough.
	 * 
	 * @param speciesName
	 *            the user inputed species name
	 * @param boroName
	 *            one of the five boroughs in the array BOROUGHS
	 * @return the total number of tree species specified by the user in the
	 *         specified borough
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName) {
		int count = 0;
		for (int i = 0; i < treeList.size(); i++) {
			if (treeList.get(i).getBoro().toLowerCase().contains(boroName.toLowerCase())
					&& treeList.get(i).getSpc().toLowerCase().contains(speciesName.toLowerCase())) {
				count++;

			}
		}
		return count;
	}

	/**
	 * Finds and prints all matching species specified by the user in the
	 * ArrayList.
	 * 
	 * @param speciesName
	 *            the user inputed species name
	 * 
	 */
	public void getMatchingSpecies(String speciesName) {
		// creates an empty list of matching species
		List<String> matchingSpecies = new ArrayList<String>();
		for (int i = 0; i < treeList.size(); i++) {
			String currentSpecies = treeList.get(i).getSpc();
			// only add the species to the matching list once
			if (currentSpecies.toLowerCase().contains(speciesName.toLowerCase())
					&& !matchingSpecies.contains(currentSpecies)) {
				matchingSpecies.add(currentSpecies);
			}
		}

		if (matchingSpecies.isEmpty()) {
			System.out.println("There are no records of " + speciesName + " on NYC streets");
			return;
		}

		System.out.println("All matching species:");
		for (int i = 0; i < matchingSpecies.size(); i++) {
			System.out.println(matchingSpecies.get(i).toLowerCase());
		}

		showPopularity(speciesName);

	}

	/**
	 * Calculates the ratio of the specified tree species to the total number of
	 * trees in the specified borough.
	 * 
	 * @param userInput
	 *            the user inputed species name
	 * @param borough
	 *            one of the five boroughs in the array BOROUGHS
	 * @return the ratio of the specified tree species to the total number of
	 *         trees in the specified borough
	 */
	public float popularityCalculator(String userInput, String borough) {
		if (borough == "NYC") {
			return ((float) getCountByTreeSpecies(userInput) / getTotalNumberOfTrees());
		} else {
			if(getCountByBorough(borough) == 0){
				return 0;
			}

			return ((float) getCountByTreeSpeciesBorough(userInput, borough) / getCountByBorough(borough));
		}

	}

	/**
	 * Displays the popularity of the specified tree species in each borough.
	 * 
	 * @param userInput
	 *            the user inputed species name
	 */
	private void showPopularity(String speciesName) {
		System.out.println("Popularity in the City: ");
		System.out.printf("	NYC	      : %5d (%d)	%.2f%%\n", getCountByTreeSpecies(speciesName),
				getTotalNumberOfTrees(), popularityCalculator(speciesName, VALID_BOROUGHS.get(0)) * 100);

		System.out.printf("	Manhattan     : %5d (%d)	%.2f%%\n",
				getCountByTreeSpeciesBorough(speciesName, VALID_BOROUGHS.get(1)),
				getCountByBorough(VALID_BOROUGHS.get(1)),
				popularityCalculator(speciesName, VALID_BOROUGHS.get(1)) * 100);

		System.out.printf("	Bronx	      : %5d (%d)	%.2f%%\n",
				getCountByTreeSpeciesBorough(speciesName, VALID_BOROUGHS.get(2)),
				getCountByBorough(VALID_BOROUGHS.get(2)),
				popularityCalculator(speciesName, VALID_BOROUGHS.get(2)) * 100);

		System.out.printf("	Brooklyn      : %5d (%d)	%.2f%%\n",
				getCountByTreeSpeciesBorough(speciesName, VALID_BOROUGHS.get(3)),
				getCountByBorough(VALID_BOROUGHS.get(3)),
				popularityCalculator(speciesName, VALID_BOROUGHS.get(3)) * 100);

		System.out.printf("	Queens	      : %5d (%d)	%.2f%%\n",
				getCountByTreeSpeciesBorough(speciesName, VALID_BOROUGHS.get(4)),
				getCountByBorough(VALID_BOROUGHS.get(4)),
				popularityCalculator(speciesName, VALID_BOROUGHS.get(4)) * 100);

		System.out.printf("	Staten Island : %5d (%d)	%.2f%%\n",
				getCountByTreeSpeciesBorough(speciesName, VALID_BOROUGHS.get(5)),
				getCountByBorough(VALID_BOROUGHS.get(5)),
				popularityCalculator(speciesName, VALID_BOROUGHS.get(5)) * 100);
	}

	/**
	 * Turns each data into a meaningful String representation.
	 * 
	 * @return
	 */
	public String toString(Tree tree) {
		return tree.getId() + " " + tree.getDbh() + " " + tree.getStatus() + " " + tree.getHealth() + " "
				+ tree.getSpc() + " " + tree.getZip() + " " + tree.getBoro() + " " + tree.getX() + " " + tree.getY();

	}
}
