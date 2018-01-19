import java.util.Arrays;
import java.util.List;

/**
 * The Tree class represents each individual Tree object that stores the
 * relevant data types.
 * 
 * @author christopherchao
 */
public class Tree implements Comparable {
	private int id;
	private int dbh;
	private String status;
	private String health;
	private String spc;
	private int zip;
	private String boro;
	private double x;
	private double y;
	private int idTree;
	private String spcTree;
	private final List<String> VALID_BOROUGHS = Arrays.asList("nyc", "manhattan", "bronx", "brooklyn", "queens",
			"staten island", null);
	private final List<String> VALID_STATUS = Arrays.asList("Alive", "Dead", "Stump");
	private final List<String> VALID_HEALTH = Arrays.asList("good", "Good", "fair", "Fair", "poor", "Poor");

	/**
	 * Creates tree object with given parameters:
	 * 
	 * @param id
	 * @param dbh
	 * @param status
	 * @param health
	 * @param spc
	 * @param zip
	 * @param boro
	 * @param x
	 * @param y
	 */
	public Tree(int id, int dbh, String status, String health, String spc, int zip, String boro, double x, double y) {
		if (id < 0) {
			throw new IllegalArgumentException("Id must be non-negative integer.");
		}
		if (dbh < 0) {
			throw new IllegalArgumentException("Dbh must be non-negative integer.");
		}

		if (!VALID_STATUS.contains(status) && !status.isEmpty() && status != null) {
			throw new IllegalArgumentException("Status must be 'Alive', 'Dead', 'Stump', an empty string, or null.");

		}

		if (!VALID_HEALTH.contains(health) && !health.isEmpty() && health != null) {
			throw new IllegalArgumentException("Status must be 'Alive', 'Dead', 'Stump', an empty string, or null.");

		}

		if (spc == null) {
			throw new IllegalArgumentException("Species must be a string; cannot be null.");
		}
		if (zip < 0 || zip > 99999) {
			throw new IllegalArgumentException("Zipcode must be a positive five digit integer. ");
		}

		if (!VALID_BOROUGHS.contains(boro.toLowerCase())) {
			throw new IllegalArgumentException(
					"Boro must be 'Manhattan', 'Bronx', 'Brooklyn', 'Queens', or 'Staten Island'.");
		}

		/*
		 * there is no need to check if x and y are doubles because I casted
		 * them to a double when creating a new Tree object in the
		 * NYCStreetTrees Class
		 */

		this.id = id;
		this.dbh = dbh;
		this.status = status;
		this.health = health;
		this.spc = spc;
		this.zip = zip;
		this.boro = boro;
		this.x = x;
		this.y = y;

	}

	/**
	 * Returns the stored id.
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the stored DBH.
	 * 
	 * @return
	 */
	public int getDbh() {
		return dbh;
	}

	/**
	 * Returns the stored status.
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Returns the stored health.
	 * 
	 * @return
	 */
	public String getHealth() {
		return health;
	}

	/**
	 * Returns the stored species.
	 * 
	 * @return
	 */
	public String getSpc() {
		return spc;
	}

	/**
	 * Returns the stored zipcode.
	 * 
	 * @return
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * Returns the stored borough.
	 * 
	 * @return
	 */
	public String getBoro() {
		return boro;
	}

	/**
	 * Returns the stored X value.
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the stored Y value.
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * Turns each data into a meaningful String representation.
	 * 
	 * @return
	 */
	public String toString() {
		return id + " " + dbh + " " + status + " " + health + " " + spc + " " + zip + " " + boro + " " + x + " " + y;

	}

	/**
	 * Checks to see if two tree object ids are equal.
	 * 
	 * @param tree
	 * @return true if equal and false if not
	 */
	public boolean equals(Tree tree) {

		if (this.getId() == tree.getId()) {
			if (!this.getSpc().toLowerCase().equals(tree.getSpc().toLowerCase())) {
				throw new IllegalArgumentException(
						"The tree id’s should be unique, so there should not be any possibility of creating two Treeobjects with identical id’s but different species names");
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(Object o) {
		Tree t = (Tree) o;

		if (this.getSpc().toLowerCase() == (t.getSpc().toLowerCase())) {
			if (this.getId() == t.getId()) {
				return 0;
			} else if (this.getId() > t.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
		return this.getSpc().toLowerCase().compareTo(t.getSpc().toLowerCase());

	}
}
