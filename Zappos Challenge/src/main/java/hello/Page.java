package hello;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {

	/**
	 * Simple class that stores the results of the search using
	 * the Zappos API.
	 */
	private ArrayList results;
	
	/**
	 * Method which returns the the Array List of results
	 * of the search.
	 */
	public ArrayList getResults() {
		return results;
	}
	
	/**
	 * Returns price information of the item at
	 * index x.
	 */
	public String getPriceInfo(int x) {
		String item = results.get(x).toString().replaceAll("\\s+","");
		String[] itemInfo = item.split(",");
		return itemInfo[1];
	}
	
	/**
	 * Returns product name information of the item
	 * at index x.
	 */
	public String getNameInfo(int x) {
		String[] itemInfo = results.get(x).toString().split(",");
		return itemInfo[5];
	}
}
