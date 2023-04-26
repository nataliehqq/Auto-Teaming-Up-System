package teaming;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class for storing statistics result
 */
public class Statistics {
	
	/*
	 * Statistics class: Help showing data in the table
	 * Do not touch anything in this file
	 */
	
	private final SimpleStringProperty rowindex;
	private final SimpleStringProperty entry;
	private final SimpleStringProperty value;

	/**
	 * Constructor for initialising Statistics
	 * @param rIndex row index
	 * @param fName first name
	 * @param lName last name
	 */
	public Statistics(String rIndex, String fName, String lName) {
		this.rowindex = new SimpleStringProperty(rIndex);
		this.entry = new SimpleStringProperty(fName);
		this.value = new SimpleStringProperty(lName);
	}

	/**
	 * get row index
	 * @return row index
	 */
	public String getRowindex() {
		return rowindex.get();
	}
	/**
	 * set row index
	 * @param val row index
	 */
	public void setRowindex(String val) {
		rowindex.set(val);
	}
	/**
	 * get first name
	 * @return first name
	 */
	public String getEntry() {
		return entry.get();
	}
	/**
	 * set first name
	 * @param val first name
	 */
	public void setEntry(String val) {
		entry.set(val);
	}
	/**
	 * get last name
	 * @return last name
	 */
	public String getValue() {
		return value.get();
	}
	/**
	 * set last name
	 * @param val last name
	 */
	public void setValue(String val) {
		value.set(val);
	}
}